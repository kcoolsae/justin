/*
 * RegistrationDeputy.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package deputies;

import be.ugent.justin.db.dao.UserDao;
import common.LoggedInDeputy;
import lombok.Getter;
import lombok.Setter;
import play.data.Form;
import play.data.validation.Constraints;
import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;
import play.mvc.Result;

@Setter
public class RegistrationDeputy extends LoggedInDeputy {

    private MailerClient mailerClient; // injected by controller!

    // TODO avoid duplication with AuthenticationDeputy
    private void sendEmail(String subject, String to, String text) {
        Email email = new Email()
                .setSubject(subject)
                .setFrom(config.getString("justin.no-reply-address"))
                .addTo(to)
                .setBodyText(text);
        mailerClient.send(email);
    }

    // TODO avoid duplication with AuthenticationDeputy
    private String hostUri() {
        // TODO is there a more reliable way to do this?Auth
        String host = request.host();
        if (host.endsWith("443")) {
            return "https://" + host;
        } else {
            return "http://" + host;
        }
    }

    public Result showRegistration() {
        return ok(views.html.auth.registration.render(this));
    }

    @Getter
    @Setter
    public static class RegistrationData {
        @Constraints.Required
        @Constraints.Email
        public String email;
        public boolean temporary;
    }

    public Result register() {
        Form<RegistrationData> form = formFromRequest(RegistrationData.class);
        if (form.hasErrors()) {
            error ("Invalid email address");
        } else {
            RegistrationData data = form.get();
            // check whether email address is already in use
            UserDao dao = dac().getUserDao();
            if (dao.emailExists(data.email)) {
                error("Email address already in use");
            } else {
                String token = dao.createRegistrationToken(data.email, getCountry(), data.temporary);
                String message = """
                        Dear user,
                        
                        Please use the following link to complete your registration.
                        
                        """
                        + hostUri() + controllers.routes.AuthenticationController.showCompleteRegistration(token).url();
                sendEmail("Bebras Justin - Registration", data.email, message);
                success("A registration invite was sent");
                return redirectToIndex();
            }
        }
        return badRequest(views.html.auth.registration.render(this));
    }

}
