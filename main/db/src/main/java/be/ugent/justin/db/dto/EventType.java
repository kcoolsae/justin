/*
 * EventType.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.dto;

public enum EventType {
    PENDING,    // not yet visible to the user
    PUBLISHED,  // visible, can be subscribed to, but no forms yet
    OPEN,       // forms can be filled in
    CLOSED,     // forms can no longer be filled in
    ARCHIVED    // no longer visible to the user
}
