/*
 * ProfileDeputy.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package deputies;

import common.LoggedInDeputy;
import common.Session;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import play.data.Form;
import play.data.validation.Constraints;
import play.mvc.Result;

/**
 * Allows name and email address to be changed
 */
public class ProfileDeputy extends LoggedInDeputy {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class NameData {

        @Constraints.Required
        public String name;

        public NameData(String name) {
            this.name = name;
        }

    }

    public Result showChangeName () {
        return ok(views.html.profile.change_name.render(
                formFromData(new NameData(getFromSession(Session.NAME))),
                this
        ));
    }

    public Result changeName () {
        Form<NameData> form = formFromRequest(NameData.class);
        if (form.hasErrors()) {
            return badRequest(views.html.profile.change_name.render(form, this));
        } else {
            String name = form.get().name;
            dac().getUserDao().updateName(name);
            // need to update the session
            success("Name succesfully changed");
            return redirectToIndex().addingToSession(request,Session.NAME, name);
        }
    }

    public Result showChangeEmail() {
        return ok (views.html.profile.change_email.render(this));
    }

    public Result changeEmail() {
        return ok ("changeEmail");
    }

}
