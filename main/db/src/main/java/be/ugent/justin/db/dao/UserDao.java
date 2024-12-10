/*
 * UserDao.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.dao;

import be.ugent.justin.db.dto.User;

public interface UserDao {

    /**
     * Create a token for the user with the given email. Returns null
     * when the user does not exist.
     */
    String createToken (String email);

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
}
