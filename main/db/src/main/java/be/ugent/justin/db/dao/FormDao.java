/*
 * FormDao.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.dao;

import be.ugent.justin.db.dto.FormHeader;
import be.ugent.justin.db.dto.FormLabel;

import java.util.List;

public interface FormDao {

    /**
     * List of all forms for a given event. Includes only id, label and deadline.
     */
    List<FormLabel> listFormsRestricted(int eventId);

    /**
     * Indicate that the current user will 'participate' in the form, or not.
     */
    void setParticipation(int formId, boolean participate);

    /**
     * Return form information for a given form.
     */
    FormHeader getForm(int formId);

    /**
     * Next page of the form. Returns zero when there is no next page. Can also
     * be used to find the first page by using 0 for parameter pageNr.
     */
    int nextPage(int formId, int pageNr);

    /**
     * Previous page of the form. Returns zero when there is no previous page.
     */
    int previousPage(int formId, int pageNr);

}
