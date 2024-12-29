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
     * Return the list of events visible to the user. Also contains the participation status of the current user.
     * Ordered most recent first.
     */
    List<Event> getVisibleEvents();

    /**
     * Get the event with the given ID
     */
    Event getEvent(int eventId);

    /**
     * Change the participation status of the current user for the given event.
     */
    void setParticipation(int eventId, boolean participating);

    record UserParticipation(String name, boolean participating, boolean temporary) {
    }

    /**
     * Get the list of users with a known participation status for the given event.
     */
    List<UserParticipation> getUsers(int eventId, String country);
}
