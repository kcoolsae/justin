/*
 * EventDao.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.dao;

import be.ugent.justin.db.dto.Event;

import java.util.List;

public interface EventDao {

    /**
     * Return the list of events visible to the user.
     */
    List<Event> getVisibleEvents();

}
