/*
 * AuthenticationDeputy.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2023-2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package deputies;

import be.ugent.justin.db.dao.UserDao;
import be.ugent.justin.db.dto.PrivilegeType;
import be.ugent.justin.db.dto.Registration;
import be.ugent.justin.db.dto.User;
import common.Session;
import lombok.Getter;
import lombok.Setter;
import play.data.Form;
import play.data.validation.Constraints;
import play.mvc.Result;
import views.html.auth.sign_in;

import java.util.Map;

public class AuthenticationDeputy extends EmailSendingDeputy {

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
                sendEmail("Bebras Justin - Sign-in",
                        email,
                        views.txt.mail.login.render(hostUri(),token).body());
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
            User user = dac().getUserDao().getUserById(userId); // TODO combine with getUserIdForLoginToken ?
            StringBuilder privilegeTypes = new StringBuilder();
            for (PrivilegeType pt : dac().getUserDao().listPrivilegeTypes(userId)) {
                privilegeTypes.append(pt.getCode());
            }
            return redirectToIndex().withSession(
                    Map.of(Session.ID, userId + "",
                            Session.NAME, user.name(),
                            Session.COUNTRY, user.country(),
                            Session.PRIVILEGES, privilegeTypes.toString()
                    ));
        }
    }

    /**
     * Logout
     */
    public Result logout() {
        success("Successfully logged out.");
        return redirectToIndex().withNewSession();
    }

    public Result notRegistered() {
        return ok(views.html.auth.not_registered.render(this));
    }

    @Getter
    @Setter
    public static class RegistrationData {
        @Constraints.Required
        @Constraints.Email
        public String email;

        @Constraints.Required
        public String name;
    }

    public Result showCompleteRegistration(String token) {
        if (dac().getUserDao().isValidRegistration(token)) {
            Form<RegistrationData> form = emptyForm(RegistrationData.class);
            return ok(views.html.auth.registration_info.render(
                    form, token, this
            ));
        } else {
            return ok(views.html.auth.registration_expired.render(this));
        }
    }

    public Result completeRegistration(String token) {
        Form<RegistrationData> form = formFromRequest(RegistrationData.class);
        if (form.hasErrors()) {
            return badRequest(views.html.auth.registration_info.render(
                    form, token, this
            ));
        } else {
            RegistrationData data = form.get();
            UserDao dao = dac().getUserDao();
            Registration registration = dao.findAndDeleteRegistration(data.email, token);
            if (registration == null) {
                return badRequest(views.html.auth.registration_info.render(
                        form.withError("email", "Email address not the same as in the registration invite"),
                        token,
                        this
                ));
            } else {
                // TODO use temporary flag
                dao.createUser(data.email, data.name, registration.country());
                success("Registration was successfully completed. You can now sign in.");
                return redirectToIndex();
            }
        }
    }

}
