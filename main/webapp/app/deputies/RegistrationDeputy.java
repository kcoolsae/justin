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
import lombok.Getter;
import lombok.Setter;
import play.data.Form;
import play.data.validation.Constraints;
import play.mvc.Result;

public class RegistrationDeputy extends EmailSendingDeputy {

    public Result showRegistration() {
        return ok(views.html.auth.registration.render(emptyForm(RegistrationData.class),this));
    }

    public Result showRegistrationAll() {
        return ok(views.html.auth.registration_external.render(emptyForm(RegistrationData.class), this));
    }

    @Getter
    @Setter
    public static class RegistrationData {
        @Constraints.Required
        @Constraints.Email
        public String email;
        public boolean temporary;

        public String country;
    }

    private boolean isValidCountryCode(String country) {
        if (country == null || country.length() != 2) {
            return false;
        }
        for (int i = 0; i < country.length(); i++) {
            if (country.charAt(i) < 'A' || country.charAt(i) > 'Z') {
                return false;
            }
        }
        return true;
    }

    public Result register() {
        Form<RegistrationData> form = formFromRequest(RegistrationData.class);
        if (!form.hasErrors()) {
            RegistrationData data = form.get();
            UserDao dao = dac().getUserDao();
            if (dao.emailExists(data.email)) {
                form = form.withError("email", "Email address already in use");
            } else {
                return sendRegistrationInvite(data.email, getCountry(), data.temporary);
            }
        }
        return badRequest(views.html.auth.registration.render(form, this));
    }

    public Result registerExternal() {
        Form<RegistrationData> form = formFromRequest(RegistrationData.class);
        if (!form.hasErrors()) {
            RegistrationData data = form.get();
            // check whether email address is already in use
            UserDao dao = dac().getUserDao();
            if (dao.emailExists(data.email)) {
                form = form.withError("email", "Email address already in use");
            } else if (isValidCountryCode(data.country)) {
                return sendRegistrationInvite(data.email, data.country, data.temporary);
            } else {
                form = form.withError("country", "Invalid code");
            }
        }
        return badRequest(views.html.auth.registration_external.render(form, this));
    }

    private Result sendRegistrationInvite(String email, String country, boolean temporary) {
        String token = dac().getUserDao().createRegistrationToken(email, country, temporary);
        sendEmail("Bebras Justin - Complete registration",
                email,
                views.txt.mail.register.render(hostUri(), token).body());
        success("A registration invite was sent");
        return redirectToIndex();
    }

}
