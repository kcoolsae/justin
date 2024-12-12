/*
 * Checkboxes.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.dto;

public class CheckboxesElement extends QuestionElement {

    private final boolean hasOther;

    public CheckboxesElement(int id, String key, String title, String description, boolean required, boolean hasOther) {
        super(id, key, title, description, required);
        this.hasOther = hasOther;
    }

    @Override
    <R> R visit(ElementVisitor<R> visitor) {
        return visitor.visitCheckboxes(this);
    }
}
