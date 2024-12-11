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
import lombok.Setter;

/**
 * Base class for deputies of {@link LoggedInController}.
 * Provides data access, markdown service and will only be used
 * when a user is logged in.
 */
@Setter
public class LoggedInDeputy extends DataAccessDeputy {

    private MarkdownService markdownService; // injected

    public String toHtml(String str) {
        return markdownService.toHtml(str);
    }

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
