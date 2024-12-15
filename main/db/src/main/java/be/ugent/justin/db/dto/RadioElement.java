/*
 * Radio.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.dto;

import lombok.Getter;

@Getter
public class RadioElement extends MultipleChoiceElement {

    private final boolean hasOther;

    public boolean hasOther() {
        return hasOther;
    }

    public RadioElement(int id, String key, String title, String description, boolean required, boolean hasOther, String answer) {
        super(id, key, title, description, required, answer);
        this.hasOther = hasOther;
    }

    @Override
    public <R> R accept(ElementVisitor<R> visitor) {
        return visitor.visitRadio(this);
    }
}
