/*
 * DataAccessContext.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db;

/* Abstraction of a database connection. Provides data access objects. */

import be.ugent.justin.db.dao.UserDao;

public interface DataAccessContext extends AutoCloseable {

    UserDao getUserDao();

    void begin();

    void commit();

    void rollback();

    @Override
    void close(); // overridden not to throw an exception
}
