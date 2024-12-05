/*
 * Deputy.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package deputies;

import play.mvc.Call;
import play.mvc.Result;

/**
 * Common super class of all deputies
 */
public class Deputy extends be.ugent.caagt.play.deputies.Deputy {

    // Array with error/warning/success/info messages, in that order
    private final String[] statuses = new String[4];

    /**
     * Will flash a success message, unless a warning or error message is already in the flash.
     */
    protected void success(String message, Object... args) {
        statuses[2] = i18n(message, args);
    }

    /**
     * Will flash an error message.
     */
    protected void error(String message, Object... args) {
        statuses[0] = i18n(message, args);
    }

    /**
     * Will flash a warning message, unless an error message is already in the flash.
     */
    protected void warning(String message, Object... args) {
        statuses[1] = i18n(message, args);
    }

    /**
     * Will flash an informational message, unless a success, warning or error message
     * is already in the flash.
     */
    protected void info(String message, Object... args) {
        statuses[3] = i18n(message, args);
    }

    private static final String[] STATUS_STRINGS = {"danger", "warning", "success", "info"};

    /**
     * The type of the most serious error stored with this handler, or null if none.
     *
     * @return String describing the error type, according to Bootstrap 5 conventions.
     */
    public String getStatusType() {
        int i = 0;
        while (i < statuses.length && statuses[i] == null) {
            i++;
        }
        if (i < statuses.length) {
            return STATUS_STRINGS[i];
        } else {
            return null;
        }
    }

    /**
     * The localized message for the most serious error stored with this handler, or null if none.
     */
    public String getStatusMessage() {
        int i = 0;
        while (i < statuses.length && statuses[i] == null) {
            i++;
        }
        if (i < statuses.length) {
            return statuses[i];
        } else {
            return null;
        }
    }

    /**
     * Redirects to the given call, where the most serious error message stored in this handler is added to the flash.
     */
    @Override
    protected Result redirect(Call call) {
        Result result = super.redirect(call);
        String type = getStatusType();
        if (type != null) {
            result = result.flashing(type, getStatusMessage());
        }
        return result;
    }

}
