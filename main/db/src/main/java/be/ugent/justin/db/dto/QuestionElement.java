/*
 * QuestionElement.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.dto;

import lombok.Getter;

@Getter
public abstract class QuestionElement extends Element {

    private final boolean required;
    private final String answer; // answer by the current user

    public QuestionElement(int id, String key, String title, String description, boolean required, String answer) {
        super(id, key, title, description);
        this.required = required;
        this.answer = answer;
    }
}
