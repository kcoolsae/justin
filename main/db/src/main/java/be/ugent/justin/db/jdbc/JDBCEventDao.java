/*
 * JDBCEventDao.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.jdbc;

import be.ugent.caagt.dao.helper.SelectSQLStatement;
import be.ugent.justin.db.dao.EventDao;
import be.ugent.justin.db.dto.Event;
import be.ugent.justin.db.dto.EventType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JDBCEventDao extends JDBCAbstractDao implements EventDao {

    public JDBCEventDao(JDBCDataAccessContext context) {
        super(context);
    }

    private static Event makeEvent(ResultSet rs) throws SQLException {
        return new Event(
                rs.getString("event_id"),
                rs.getString("event_name"),
                rs.getString("event_description"),
                EventType.valueOf(rs.getString("event_type")),
                rs.getObject("participation_status", Boolean.class)
        );
    }

    private SelectSQLStatement selectEvent() {
        return select("event_id, event_name, event_description, event_type, participation_status")
                .from("events LEFT JOIN participations USING (event_id)");
    }

    @Override
    public Event getEvent(String eventId) {
        return selectEvent()
                .where("event_id", eventId)
                .getObject(JDBCEventDao::makeEvent);
    }

    @Override
    public List<Event> getVisibleEvents() {
        return selectEvent()
                .where("event_type != 'ARCHIVED'")
                .where("event_type != 'PENDING'")
                .orderBy("when_created", false)
                .getList(JDBCEventDao::makeEvent);
    }

    @Override
    public void setParticipation(String eventId, boolean participating) {
        insertOrUpdateInto("participations")
                .key("event_id", eventId)
                .key("user_id", getUserId())
                .value("participation_status", participating)
                .execute();
    }
}
