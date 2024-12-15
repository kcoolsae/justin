/*
 * JDBCElementDao.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.jdbc;

import be.ugent.caagt.dao.helper.SelectSQLStatement;
import be.ugent.justin.db.dao.ElementDao;
import be.ugent.justin.db.dto.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

class JDBCElementDao extends JDBCAbstractDao implements ElementDao {

    public JDBCElementDao(JDBCDataAccessContext context) {
        super(context);
    }

    public static Element makeElement (ResultSet rs) throws SQLException {
        int id = rs.getInt("element_id");
        String key = rs.getString("element_key");
        String title = rs.getString("element_title");
        String description = rs.getString("element_description");
        boolean required = rs.getBoolean("element_required");
        String extra = rs.getString("element_extra");
        String answer = rs.getString("answer_text");
        return switch (ElementType.valueOf(rs.getString("element_type"))) {
            case RADIO -> new RadioElement(id, key, title, description, required, "other".equals(extra), answer);
            case BUTTONS -> new ButtonsElement(id, key, title, description, required, answer);
            case SELECT -> new SelectElement(id, key, title, description, required, answer);
            case CHECKBOXES -> new CheckboxesElement(id, key, title, description, required, "other".equals(extra), answer);
            case TEXT -> new TextElement(id, key, title, description, required, answer);
            case TEXT_AREA -> new TextAreaElement(id, key, title, description, required, answer);
            case DATE -> new DateElement(id, key, title, description, required, answer);
            case INFO -> new InfoElement(id, key, title, description);
        };
    }

    public static Option makeOption(ResultSet rs) throws SQLException {
        return new Option(rs.getInt("option_id"), rs.getString("option_key"), rs.getString("option_text"));
    }

    public SelectSQLStatement selectElement() {
        return select("elements.element_id, element_key, element_title, element_description, element_required, element_type, element_extra, answer_text")
                .from("elements LEFT JOIN answers " +
                        "ON (elements.element_id = answers.element_id AND answers.user_id = ?)")
                .parameter(getUserId());
    }

    @Override
    public Collection<Element> listElements(int formId, int pageNr) {
        // without options
        Map<Integer,Element> map = selectElement()
                .where("form_id", formId)
                .where("element_page_nr", pageNr)
                .orderBy("element_seq_nr")
                .getMap(JDBCElementDao::makeElement);

        // now retrieve options
        select("element_id, option_id, option_key, option_text")
                .from("options JOIN elements USING (element_id)")
                .where("element_page_nr", pageNr)
                .orderBy("element_id")
                .orderBy("option_seq_nr")
                .processMap(map,
                        (el,rs) -> ((OptionsElement)el).addOption(makeOption(rs))
                );

        return List.copyOf(map.values());
    }

    @Override
    public void setAnswer(int elementId, String answer) {
        insertOrUpdateInto("answers")
                .key("element_id", elementId)
                .key("user_id", getUserId())
                .value("answer_text", answer)
                .execute();
    }
}
