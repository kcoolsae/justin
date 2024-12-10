/*
 * InitialController.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package controllers;

import be.ugent.caagt.play.controllers.Controller;
import deputies.InitialDeputy;
import play.mvc.Http;
import play.mvc.Result;

/**
 * Contains the few actions that do not need a database or user that is logged in.
 */
public class InitialController extends Controller<InitialDeputy> {

    public InitialController () {
        super (InitialDeputy::new);
    }

    public Result index(Http.Request request) {
        return createDeputy(request).index();
    }

    public Result todo(Http.Request request) {
        return createDeputy(request).todo();
    }

}
