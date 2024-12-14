/*
 * ElementVisitor.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package be.ugent.justin.db.dto;

public abstract class ElementVisitor<R> {

    public R visitElement(Element element) {
        return null;
    }

    public R visitQuestionElement(Element element) {
        return visitElement(element);
    }

    public R visitOptions(OptionsElement element) {
        return visitQuestionElement(element);
    }

    public R visitMultipleChoice(MultipleChoiceElement element) {
        return visitOptions(element);
    }

    public R visitRadio(RadioElement element) {
        return visitMultipleChoice(element);
    }

    public R visitButtons(ButtonsElement element) {
        return visitMultipleChoice(element);
    }

    public R visitSelect(SelectElement element) {
        return visitMultipleChoice(element);
    }

    public R visitCheckboxes(CheckboxesElement element) {
        return visitOptions(element);
    }

    public R visitText(TextElement element) {
        return visitQuestionElement(element);
    }

    public R visitTextArea(TextAreaElement element) {
        return visitQuestionElement(element);
    }

    public R visitDate(DateElement element) {
        return visitQuestionElement(element);
    }

    public R visitInfo(InfoElement element) {
        return visitElement(element);
    }

}
