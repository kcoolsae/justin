/*
 * Checkboxes.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.dto;

public class CheckboxesElement extends OptionsElement {

    private final boolean hasOther;

    public boolean hasOther() {
        return hasOther;
    }

    public CheckboxesElement(int id, String key, String title, String description, boolean hasOther, String answer) {
        super(id, key, title, description, false, answer); // required makes no sense for checkboxes
        this.hasOther = hasOther;
    }

    @Override
    public <R> R accept(ElementVisitor<R> visitor) {
        return visitor.visitCheckboxes(this);
    }
}
