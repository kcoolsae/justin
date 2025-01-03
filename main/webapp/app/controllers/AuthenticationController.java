/*
 * AuthenticationController.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2023-2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package controllers;

import common.DataAccessController;
import common.CheckLoggedIn;
import deputies.AuthenticationDeputy;
import play.libs.mailer.MailerClient;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import play.mvc.With;

public class AuthenticationController extends DataAccessController<AuthenticationDeputy> {

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

    public Result login (Http.Request request, String token) {
        return createDeputy(request).login(token);
    }

    public Result logout (Http.Request request) {
        return createDeputy(request).logout();
    }

    public Result notRegistered  (Http.Request request) {
        return createDeputy(request).notRegistered();
    }

    public Result showCompleteRegistration(Http.Request request, String token) {
        return createDeputy(request).showCompleteRegistration(token);
    }

    public Result  completeRegistration(Http.Request request, String token) {
        return createDeputy(request).completeRegistration(token);
    }

    @With(CheckLoggedIn.class)
    public Result sendEmailLink (Http.Request request) {
        return createDeputy(request).sendEmailLink();
    }

    public Result showNewEmail (Http.Request request, String token) {
        return createDeputy(request).showNewEmail(token);
    }

    public Result completeChangeEmail (Http.Request request, String token) {
        return createDeputy(request).completeChangeEmail(token);
    }
}


