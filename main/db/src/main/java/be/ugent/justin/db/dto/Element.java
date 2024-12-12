/*
 * Element.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Common superclass for all form elements
 */
@Getter
@AllArgsConstructor
public abstract class Element {

    private final int id;
    private final String key;
    private final String title;
    private final String description;

    <R> R visit(ElementVisitor<R> visitor) {
        return visitor.visitElement(this);
    }

}
