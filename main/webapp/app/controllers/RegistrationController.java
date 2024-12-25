/*
 * RegistrationController.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package controllers;

import be.ugent.justin.db.dto.PrivilegeType;
import common.CheckPrivileges;
import common.DataAccessController;
import deputies.RegistrationDeputy;
import play.libs.mailer.MailerClient;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.With;

import javax.inject.Inject;

/**
 * Handles everything for which registration privilege is required.
 */
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

    static class HasRegistrationOwnPrivilege extends CheckPrivileges {
        public HasRegistrationOwnPrivilege() {
            super(PrivilegeType.REGISTER_OWN);
        }
    }

    @With(HasRegistrationOwnPrivilege.class)
    public Result showRegistration(Http.Request request) {
        return createDeputy(request).showRegistration();
    }

    @With(HasRegistrationOwnPrivilege.class)
    public Result register(Http.Request request) {
        return createDeputy(request).register();
    }

    static class HasRegistrationAllPrivilege extends CheckPrivileges {
        public HasRegistrationAllPrivilege() {
            super(PrivilegeType.REGISTER_ALL);
        }
    }

    @With(HasRegistrationAllPrivilege.class)
    public Result showRegistrationAll(Http.Request request) {
        return createDeputy(request).showRegistrationAll();
    }

    @With(HasRegistrationAllPrivilege.class)
    public Result registerExternal(Http.Request request) {
        return createDeputy(request).registerExternal();
    }


}
