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

    public InfoElement(int id, String key, String title, String description) {
        super(id, key, title, description);
    }

    @Override
    <R> R visit(ElementVisitor<R> visitor) {
        return visitor.visitInfo(this);
    }
}
