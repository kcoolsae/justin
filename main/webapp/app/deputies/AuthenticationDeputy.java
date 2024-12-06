/*
 * AuthenticationDeputy.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2023-2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package deputies;

import common.DataAccessDeputy;
import common.Session;
import lombok.Getter;
import lombok.Setter;
import play.data.Form;
import play.data.validation.Constraints;
import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;
import play.mvc.Result;
import views.html.auth.sign_in;

import java.util.Map;

@Setter
public class AuthenticationDeputy extends DataAccessDeputy {

    private MailerClient mailerClient; // injected by controller!

    private void sendEmail(String subject, String to, String text) {
        Email email = new Email()
                .setSubject(subject)
                .setFrom(config.getString("justin.no-reply-address"))
                .addTo(to)
                .setBodyText(text);
        mailerClient.send(email);
    }

    private String hostUri() {
        // TODO is there a more reliable way to do this?
        String host = request.host();
        if (host.endsWith("443")) {
            return "https://" + host;
        } else {
            return "http://" + host;
        }
    }

    @Getter
    @Setter
    public static class EmailData {
        @Constraints.Required
        @Constraints.Email
        public String email;
    }

    /**
     * Processes the sign-in form.
     */
    public Result signIn() {
        Form<EmailData> form = formFromRequest(EmailData.class);
        if (form.hasErrors()) {
            return badRequest(sign_in.render(form, this));
        } else {
            String email = form.get().email;
            String token = dac().getUserDao().createToken(email);
            if (token != null) {
                String message = """
                        Dear user,
                        
                        Please use the link below to log in to the system.  (The link is valid for 30 minutes only!)
                        
                        """
                        + hostUri() + controllers.routes.AuthenticationController.login(token);
                sendEmail("Bebras Justin - Sign-in", email, message);
                success("An email was sent to the given address.");
            }
            return redirectToIndex();
        }
    }

    /**
     * Token login by registered user.
     */
    public Result login(String token) {
        int userId = dac().getUserDao().getUserIdForLoginToken(token);
        if (userId == 0) {
            error("""
                    The link you used to access the system is either invalid or
                    has expired. Please sign in again to continue.""");
            return redirectToIndex();
        } else {
            return redirectToIndex().withSession(
                    Map.of(Session.ID, userId + "")
            );
        }
    }

    /**
     * Logout
     */
    public Result logout() {
        success("Successfully logged out.");
        return redirectToIndex().withNewSession();
    }
}
