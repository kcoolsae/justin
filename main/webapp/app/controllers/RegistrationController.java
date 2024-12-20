/*
 * RegistrationController.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package controllers;

import common.CheckLoggedIn;
import common.DataAccessController;
import common.LoggedInController;
import deputies.RegistrationDeputy;
import play.libs.mailer.MailerClient;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.With;

import javax.inject.Inject;

/**
 * Handles everything for which registration privilege is required.
 */
@With(CheckLoggedIn.class)
public class RegistrationController extends DataAccessController<RegistrationDeputy> {

    @Inject
    private MailerClient mailerClient;

    public RegistrationController() {
        super(RegistrationDeputy::new);
    }

    @Override
    protected RegistrationDeputy createDeputy(Http.Request request) {
        RegistrationDeputy deputy = super.createDeputy(request);
        deputy.setMailerClient(mailerClient);
        return deputy;
    }

    public Result showRegistration (Http.Request request) {
        return createDeputy(request).showRegistration();
    }

    public Result register (Http.Request request) {
        return createDeputy(request).register();
    }
}
