/*
 * OptionsElement.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OptionsElement extends QuestionElement {

    protected final List<Option> options;

    public OptionsElement(int id, String key, String title, String description, boolean required) {
        super(id, key, title, description, required);
        options = new ArrayList<>();
    }

    @Override
    public <R> R accept(ElementVisitor<R> visitor) {
        return visitor.visitOptions(this);
    }

    public void addOption(Option option) {
        options.add(option);
    }
}
