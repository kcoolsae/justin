/*
 * Session.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package common;

/**
 * Defines and documents all session variables
 */
public final class Session {

    /**
     * ID of the user currently logged in.
     * Not present when nobody is logged in. Non-zero integer.
     */
    public static final String ID = "id";

    /**
     * Name of the user currently logged in. Not present when nobody is logged in.
     */
    public static final String NAME = "name";

    /**
     * Country of the user currently logged in. Not present when nobody is logged in.
     */
    public static final String COUNTRY = "country";

}
