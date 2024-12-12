/*
 * JDBCDataAccessContext.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.jdbc;

import be.ugent.caagt.dao.helper.BaseDAC;
import be.ugent.justin.db.DataAccessContext;
import be.ugent.justin.db.dao.EventDao;
import be.ugent.justin.db.dao.FormDao;
import be.ugent.justin.db.dao.UserDao;
import lombok.Getter;

import java.sql.Connection;

@Getter
public class JDBCDataAccessContext extends BaseDAC implements DataAccessContext {

    private final int userId;

    public JDBCDataAccessContext(Connection connection, int userId) {
        super(connection);
        this.userId = userId;
    }

    @Override
    public UserDao getUserDao() {
        return new JDBCUserDao(this);
    }

    @Override
    public EventDao getEventDao() {
        return new JDBCEventDao(this);
    }

    @Override
    public FormDao getFormDao() {
        return new JDBCFormDao(this);
    }
}
