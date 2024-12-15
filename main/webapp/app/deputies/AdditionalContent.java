/*
 * AdditionalContent.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package deputies;

import be.ugent.justin.db.dto.*;
import play.twirl.api.Html;

/**
 * Visitor that produces the HTML for additional content to an element that depends on the type of the element.
 */
class AdditionalContent extends ElementVisitor<Html> {

    private final FormDeputy formDeputy;

    public AdditionalContent(FormDeputy formDeputy) {
        this.formDeputy = formDeputy;
    }

    @Override
    public Html visitElement(Element element) {
        return views.html.form.unknown.render(element, formDeputy);
    }

    @Override
    public Html visitInfo(InfoElement element) {
        return new Html("");
    }

    @Override
    public Html visitText(TextElement element) {
        return views.html.form.text.render(element, formDeputy);
    }

    @Override
    public Html visitRadio(RadioElement element) {
        return views.html.form.radio.render(element, formDeputy);
    }

    @Override
    public Html visitDate(DateElement element) {
        return views.html.form.date.render(element, formDeputy);
    }

    @Override
    public Html visitTextArea(TextAreaElement element) {
        return views.html.form.text_area.render(element, formDeputy);
    }

    @Override
    public Html visitCheckboxes(CheckboxesElement element) {
        return views.html.form.checkboxes.render(element, formDeputy);
    }

    @Override
    public Html visitSelect(SelectElement element) {
        return views.html.form.select.render(element, formDeputy);
    }

    @Override
    public Html visitButtons(ButtonsElement element) {
        return views.html.form.buttons.render(element, formDeputy);
    }
}
