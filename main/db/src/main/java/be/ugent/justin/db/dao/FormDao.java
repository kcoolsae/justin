/*
 * FormDao.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.dao;

import be.ugent.justin.db.dto.Form;

import java.util.List;

public interface FormDao {

    /**
     * List of all forms for a given event. Includes only id, label and deadline.
     */
    List<Form> listFormsRestricted(int eventId);

    /**
     * Indicate that the current user will 'participate' in the form, or not.
     */
    void setParticipation(int formId, boolean participate);

}
