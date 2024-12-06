/*
 * Module.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

import be.ugent.justin.db.DataAccessProvider;
import be.ugent.justin.db.jdbc.JDBCDataAccessProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import play.db.Database;

import javax.inject.Singleton;

/**
 * Provides the injected database
 */
public class Module extends AbstractModule {

    @Provides
    @Singleton
    public DataAccessProvider createDataAccessProvider(Database database) {
        return new JDBCDataAccessProvider(database.getDataSource());
    }

}
