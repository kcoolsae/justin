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
import java.util.Optional;

public interface EventDao {

    /**
     * Return the list of events visible to the user. Also contains the participation status of the current user.
     * Ordered most recent first.
     */
    List<Event> getVisibleEvents();

    /**
     * Get the event with the given ID
     */
    Event getEvent(String eventId);

    /**
     * Change the participation status of the current user for the given event.
     */
    void setParticipation(String eventId, boolean participating);

}
