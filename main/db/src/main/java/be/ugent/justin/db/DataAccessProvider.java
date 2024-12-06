/*
 * DataAccessProvider.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db;

/**
 * Abstraction of a database. Provides data access contexts.
 */
public interface DataAccessProvider {

    /**
     * Provides a data access context for a given user.
     *
     * @param userId   User ID of the user, or 0 if unknown, irrelevant or anonymous.
     */
    DataAccessContext getContext(int userId);
}
