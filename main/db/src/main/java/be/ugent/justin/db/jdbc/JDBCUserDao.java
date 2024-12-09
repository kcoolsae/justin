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
import be.ugent.justin.db.dto.User;

import java.time.Instant;
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
            String token = UUID.randomUUID() + "-" + (1234 + userId);
            Instant expires = Instant.now().plusSeconds(60 * 30); // TODO make configurable
            insertOrUpdateInto("tokens")
                    .key("user_id", userId)
                    .value("token_text", token)
                    .value("token_expires", expires)
                    .execute();
            return token;
        }
    }

    @Override
    public int getUserIdForLoginToken(String token) {
        return select("user_id").from("tokens")
                .where("token_text", token)
                .where("token_expires > NOW()")
                .getInt();
    }

    @Override
    public User getUserById(int userId) {
        return select("user_email, user_name, user_country")
                .from("users")
                .where("user_id", userId)
                .getObject(rs -> new User(
                        userId,
                        rs.getString("user_email"),
                        rs.getString("user_name"),
                        rs.getString("user_country")
                ));
    }
}
