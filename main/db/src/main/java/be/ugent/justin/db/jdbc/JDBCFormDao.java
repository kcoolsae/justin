/*
 * JDBCFormDao.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.jdbc;

import be.ugent.caagt.dao.helper.ResultSetConverter;
import be.ugent.justin.db.dao.FormDao;
import be.ugent.justin.db.dto.FormHeader;
import be.ugent.justin.db.dto.FormLabel;

import java.util.List;

class JDBCFormDao extends JDBCAbstractDao implements FormDao {

    protected JDBCFormDao(JDBCDataAccessContext context) {
        super(context);
    }

    @Override
    public List<FormLabel> listFormsRestricted(int eventId) {
        return select("forms.form_id, form_label, form_deadline, user_id IS NOT NULL as participation_status")
                .from("forms LEFT JOIN user_forms " +
                        "ON (forms.form_id = user_forms.form_id AND user_forms.user_id = ?)")
                .parameter(getUserId())
                .where("event_id", eventId)
                .getList(rs -> new FormLabel(
                        rs.getInt("form_id"),
                        rs.getString("form_label"),
                        rs.getDate("form_deadline").toLocalDate(),
                        rs.getBoolean("participation_status")
                ));
    }

    @Override
    public List<Integer> listFormIds(int eventId) {
        return select("form_id")
                .from("forms")
                .where("event_id", eventId)
                .getList(ResultSetConverter.FIRST_INTEGER);
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

    @Override
    public FormHeader getForm(int formId) {
        return select("form_id, form_title, form_description")
                .from("forms")
                .where("form_id", formId)
                .getObject(rs -> new FormHeader(
                        rs.getInt("form_id"),
                        rs.getString("form_title"),
                        rs.getString("form_description")
                ));
    }


    @Override
    public int nextPage(int formId, int pageNr) {
        return select("MIN(element_page_nr)")
                .from("elements")
                .where("form_id", formId)
                .where("element_page_nr > ?", pageNr)
                .getInt();
    }

    @Override
    public int previousPage(int formId, int pageNr) {
        return select("MAX(element_page_nr)")
                .from("elements")
                .where("form_id", formId)
                .where("element_page_nr < ?", pageNr)
                .getInt();
    }
}
