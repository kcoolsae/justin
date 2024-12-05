/*
 * AuthenticationController.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2023-2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package controllers;

import be.ugent.caagt.play.controllers.Controller;
import deputies.AuthenticationDeputy;
import play.libs.mailer.MailerClient;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;

public class AuthenticationController extends Controller<AuthenticationDeputy> {

    @Inject
    private MailerClient mailerClient;

    @Override
    protected AuthenticationDeputy createDeputy(Http.Request request) {
        AuthenticationDeputy deputy = super.createDeputy(request);
        deputy.setMailerClient(mailerClient);
        return deputy;
    }

    public AuthenticationController() {
        super(AuthenticationDeputy::new);
    }

    public Result signIn(Http.Request request) {
        return createDeputy(request).signIn();
    }
}
