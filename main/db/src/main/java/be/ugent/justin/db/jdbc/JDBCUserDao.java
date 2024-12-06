/*
 * JDBCUserDao.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.jdbc;

import be.ugent.justin.db.dao.UserDao;

import java.util.UUID;

public class JDBCUserDao extends JDBCAbstractDao implements UserDao {

    public JDBCUserDao(JDBCDataAccessContext context) {
        super(context);
    }

    @Override
    public String createToken(String email) {
        int userId = select("user_id").from("users")
                .where("user_email = ?", email.toLowerCase().strip())
                .getInt();
        if (userId == 0) {
            return null;
        } else {
            String token = UUID.randomUUID().toString();
            insertOrUpdateInto("tokens")
                    .key("user_id", userId)
                    .value("token_text", token)
                    .execute();
            return token;
        }
    }
}
