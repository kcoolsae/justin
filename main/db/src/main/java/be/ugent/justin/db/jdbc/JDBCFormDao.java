/*
 * JDBCFormDao.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.jdbc;

import be.ugent.justin.db.dao.FormDao;
import be.ugent.justin.db.dto.Form;

import java.util.List;

class JDBCFormDao extends JDBCAbstractDao implements FormDao {

    protected JDBCFormDao(JDBCDataAccessContext context) {
        super(context);
    }

    @Override
    public List<Form> listFormsRestricted(int eventId) {
        return select("forms.form_id, form_label, form_deadline, user_id IS NOT NULL as participation_status")
                .from("forms LEFT JOIN user_forms " +
                        "ON (forms.form_id = user_forms.form_id AND user_forms.user_id = ?)")
                .parameter(getUserId())
                .where("event_id", eventId)
                .getList(rs -> new Form(
                        rs.getInt("form_id"),
                        rs.getString("form_label"),
                        rs.getDate("form_deadline").toLocalDate(),
                        rs.getBoolean("participation_status")
                ));
    }

    @Override
    public void setParticipation(int formId, boolean participate) {
        if (participate) {
            insertOrUpdateInto("user_forms")
                    .key("form_id", formId)
                    .key("user_id", getUserId())
                    .execute();
        } else {
            deleteFrom("user_forms")
                    .where("form_id", formId)
                    .where("user_id", getUserId())
                    .execute();
        }
    }
}
