/*
 * JDBCEventDao.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.jdbc;

import be.ugent.justin.db.dao.EventDao;
import be.ugent.justin.db.dto.Event;
import be.ugent.justin.db.dto.EventType;

import java.util.List;

public class JDBCEventDao extends JDBCAbstractDao implements EventDao {

    public JDBCEventDao(JDBCDataAccessContext context) {
        super(context);
    }

    @Override
    public List<Event> getVisibleEvents() {
        return select("event_id, event_name, event_description, event_type")
                .from("events")
                .where("event_type != 'ARCHIVED'")
                .where("event_type != 'PENDING'")
                .orderBy("event_id", false)
                .getList(rs -> new Event(
                        rs.getString("event_id"),
                        rs.getString("event_name"),
                        rs.getString("event_description"),
                        EventType.valueOf(rs.getString("event_type"))
                ));
    }

}
