/*
 * InfoElement.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.dto;

import lombok.Getter;

@Getter
public class InfoElement extends Element {

    private final boolean startsPage;

    public InfoElement(int id, String key, String title, String description, boolean startsPage) {
        super(id, key, title, description);
        this.startsPage = startsPage;
    }

    @Override
    <R> R visit(ElementVisitor<R> visitor) {
        return visitor.visitInfo(this);
    }
}
