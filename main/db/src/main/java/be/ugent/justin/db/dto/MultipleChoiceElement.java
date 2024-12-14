/*
 * MultipleChoice.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.dto;

import lombok.Getter;

import java.util.List;
import java.util.ArrayList;

@Getter
public class MultipleChoiceElement extends QuestionElement {

    private final List<Option> options;

    public MultipleChoiceElement(int id, String key, String title, String description, boolean required) {
        super(id, key, title, description, required);
        options = new ArrayList<>();
    }

    @Override
    public <R> R accept(ElementVisitor<R> visitor) {
        return visitor.visitMultipleChoice(this);
    }

    public void addOption(Option option) {
        options.add(option);
    }
}
