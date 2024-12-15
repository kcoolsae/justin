/*
 * ElementDao.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.dao;

import be.ugent.justin.db.dto.Element;

import java.util.Collection;

public interface ElementDao {

    /**
     * List elements on the given form page.
     */
    Collection<Element> listElements(int formId, int pageId);

    /**
     * Register the answer of the current user for the given element.
     */
    void setAnswer(int elementId, String answer);

}
