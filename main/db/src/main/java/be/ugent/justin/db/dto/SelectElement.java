/*
 * SelectElement.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.dto;

public class SelectElement extends MultipleChoiceElement{

    public SelectElement(int id, String key, String title, String description, boolean required) {
        super(id, key, title, description, required);
    }

    @Override
    public <R> R accept(ElementVisitor<R> visitor) {
            return visitor.visitSelect(this);
        }
}
