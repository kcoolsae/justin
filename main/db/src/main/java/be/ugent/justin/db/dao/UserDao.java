/*
 * UserDao.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.dao;

import be.ugent.justin.db.dto.PrivilegeType;
import be.ugent.justin.db.dto.Registration;
import be.ugent.justin.db.dto.User;

import java.util.List;

public interface UserDao {

    /**
     * Create a token for the user with the given email. Returns null
     * when the user does not exist.
     */
    String createToken (String email);

    /**
     * Create a registration token for the user with the given email.
     */
    String createRegistrationToken (String email, String country, boolean temporary);

    /**
     * Return the user ID for the given (login) token. Returns 0 when not found
     * or when the token is expired.
     */
    int getUserIdForLoginToken (String token);

    /**
     * Return the user for the given ID. Returns null when not found.
     */
    User getUserById (int userId);

    /**
     * Return information about the current user.
     */
    User getCurrentUser ();

    /**
     * Update the name of the current user with the given ID.
     */
    void updateName (String name);

    /**
     * Return all privilege types for the given user
     */
    List<PrivilegeType> listPrivilegeTypes (int userId);

    /**
     * Return whether the email address corresponds to a user with an account
     */
    boolean emailExists (String email);

    /**
     * Find a registration record and delete it when found.
     */
    Registration findAndDeleteRegistration (String email, String token);

    /**
     * Create a new user with the given email, name and country. Returns the user ID.
     */
    void createUser (String email, String name, String country);

    /**
     * Are these valid registration credentials?
     */
    boolean isValidRegistration (String token);
}
