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
import be.ugent.justin.db.dto.EventStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

class JDBCEventDao extends JDBCAbstractDao implements EventDao {

    public JDBCEventDao(JDBCDataAccessContext context) {
        super(context);
    }

    private static Event makeEvent(ResultSet rs) throws SQLException {
        return new Event(
                rs.getInt("event_id"),
                rs.getString("event_key"),
                rs.getString("event_name"),
                rs.getString("event_description"),
                EventStatus.valueOf(rs.getString("event_status")),
                rs.getObject("participation_status", Boolean.class)
        );
    }

    private SelectSQLStatement selectEvent() {
        return select("events.event_id as event_id, event_key, event_name, event_description, event_status, participation_status")
                .from("events LEFT JOIN participations ON events.event_id = participations.event_id AND participations.user_id = ?")
                .parameter(getUserId());
    }

    @Override
    public Event getEvent(int eventId) {
        return selectEvent()
                .where("events.event_id", eventId)
                .getObject(JDBCEventDao::makeEvent);
    }

    @Override
    public List<Event> getVisibleEvents() {
        return selectEvent()
                .where("event_status != 'ARCHIVED'")
                .where("event_status != 'PENDING'")
                .orderBy("when_created", false)
                .getList(JDBCEventDao::makeEvent);
    }

    @Override
    public void setParticipation(int eventId, boolean participating) {
        insertOrUpdateInto("participations")
                .key("event_id", eventId)
                .key("user_id", getUserId())
                .value("participation_status", participating)
                .execute();
    }

    @Override
    public List<UserParticipation> getUsers(int eventId, String country) {
        return select("user_name, participation_status, user_temporary")
                .from("participations JOIN users USING(user_id)")
                .where("event_id", eventId)
                .where("user_country", country)
                .getList(rs -> new UserParticipation(
                        rs.getString("user_name"),
                        rs.getBoolean("participation_status"),
                        rs.getBoolean("user_temporary")
                ));
    }
}
