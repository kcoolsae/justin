/*
 * ProfileController.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package controllers;

import common.LoggedInController;
import deputies.ProfileDeputy;
import play.mvc.Http;
import play.mvc.Result;

public class ProfileController extends LoggedInController<ProfileDeputy> {

    public ProfileController() {
        super(ProfileDeputy::new);
    }

    public Result showChangeName (Http.Request request) {
        return createDeputy(request).showChangeName();
    }

    public Result changeName (Http.Request request) {
        return createDeputy(request).changeName();
    }

    public Result showChangeEmail (Http.Request request) {
        return createDeputy(request).showChangeEmail();
    }

    public Result changeEmail (Http.Request request) {
        return createDeputy(request).changeEmail();
    }

}
