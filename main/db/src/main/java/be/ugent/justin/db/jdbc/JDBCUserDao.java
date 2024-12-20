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
import be.ugent.justin.db.dto.PrivilegeType;
import be.ugent.justin.db.dto.Registration;
import be.ugent.justin.db.dto.User;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

class JDBCUserDao extends JDBCAbstractDao implements UserDao {

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
    public String createRegistrationToken(String email, String country, boolean temporary) {
        String token = UUID.randomUUID().toString();
        Instant expires = Instant.now().plusSeconds(48 * 3600); // TODO make configurable
        insertOrUpdateInto("registrations")
                .key("registration_email", email.toLowerCase().strip())
                .value("registration_token", token)
                .value("registration_country", country)
                .value("registration_as_temporary", temporary)
                .value("registration_expires", expires)
                .execute();
        return token;
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

    @Override
    public User getCurrentUser() {
        return getUserById(getUserId());
    }

    @Override
    public void updateName(String name) {
        update("users").set("user_name", name).where("user_id", getUserId()).execute();
    }

    @Override
    public List<PrivilegeType> listPrivilegeTypes(int userId) {
        return select("privilege_type")
                .from("privileges")
                .where("user_id", userId)
                .getList(rs -> PrivilegeType.valueOf(rs.getString("privilege_type")));
    }

    @Override
    public boolean emailExists(String email) {
        return !select("1")
                .from("users")
                .where("user_email", email.toLowerCase().strip())
                .isEmpty();
    }

    @Override
    public Registration findAndDeleteRegistration(String e, String token) {
        String email = e.toLowerCase().strip();
        Optional<Registration> result =
                select("registration_country, registration_as_temporary")
                        .from("registrations")
                        .where("registration_email", email)
                        .where("registration_token", token)
                        .findObject(rs -> new Registration(
                                email,
                                rs.getString("registration_country"),
                                rs.getBoolean("registration_as_temporary")
                        ));
        if (result.isPresent()) {
            deleteFrom("registrations")
                    .where("registration_email", email)
                    .where("registration_token", token)
                    .execute();
            return result.get();
        } else {
            return null;
        }
    }

    @Override
    public void createUser(String email, String name, String country) {
        insertInto("users")
                .value("user_email", email.toLowerCase().strip())
                .value("user_name", name)
                .value("user_country", country)
                .execute();
    }

    @Override
    public boolean isValidRegistration(String token) {
        return !select("1")
                .from("registrations")
                .where("registration_token", token)
                .where("registration_expires > NOW()")
                .isEmpty();
    }
}
