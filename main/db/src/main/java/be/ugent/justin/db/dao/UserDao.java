/*
 * UserDao.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.dao;

public interface UserDao {

    /**
     * Create a token for the user with the given email. Returns null
     * when the user does not exist.
     */
    String createToken (String email);
}
