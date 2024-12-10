/*
 * HomeController.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package controllers;

import common.LoggedInController;
import deputies.HomeDeputy;
import play.mvc.Result;
import play.mvc.Http;

/**
 * Provides the index page and other pages that
 * need no database support
 */
public class HomeController extends LoggedInController<HomeDeputy> {

    public HomeController() {
        super (HomeDeputy::new);
    }

    public Result landing (Http.Request request) {
        return createDeputy(request).landing();
    }

}
