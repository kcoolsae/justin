/*
 * LoggedInDeputy.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package common;

import be.ugent.caagt.play.controllers.Controller;

/**
 * Base class for deputies of {@link LoggedInController}. Provides a data access context and some helper methods
 */
public class LoggedInDeputy extends DataAccessDeputy {

    @Override
    public void setParent(Controller<?> parent) throws RuntimeException {
        if (!(parent instanceof LoggedInController)) {
            throw new RuntimeException("Parent of " + getClass() + " should be instance of LoggedInController");
        }
    }

    /**
     * ID of current user taken from session.
     */
    public int getCurrentUserId() {
        return Integer.parseInt(findInSession(Session.ID));
    }

}
