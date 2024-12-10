/*
 * LoggedInController.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package common;

import play.mvc.With;

import java.util.function.Supplier;

/**
 * Common super class of all controllers that need the user to be logged in
 */
@With(CheckLoggedIn.class)
public class LoggedInController<D extends LoggedInDeputy> extends DataAccessController<D> {

    public LoggedInController(Supplier<D> deputyFactory) {
        super(deputyFactory);
    }
}
