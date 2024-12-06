/*
 * JDBCDataAccessProvider.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.jdbc;

import be.ugent.caagt.dao.DACException;
import be.ugent.justin.db.DataAccessContext;
import be.ugent.justin.db.DataAccessProvider;

import javax.sql.DataSource;
import java.sql.SQLException;

public class JDBCDataAccessProvider implements DataAccessProvider {

    private final DataSource dataSource;

    public JDBCDataAccessProvider(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public DataAccessContext getContext(int userId) {
        try {
            return new JDBCDataAccessContext(dataSource.getConnection(), userId);
        } catch (SQLException ex) {
            throw new DACException("Could not obtain connection from data source", ex);
        }
    }
}
