/*
 * FormDeputy.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package deputies;

import be.ugent.justin.db.dto.*;
import common.LoggedInDeputy;
import play.mvc.Result;
import play.twirl.api.Html;

public class FormDeputy extends LoggedInDeputy {

    /**
     * Ajax call to change participation in a form for the current user
     */
    public Result changeParticipation(int formId, boolean participation) {
        dac().getFormDao().setParticipation(formId, participation);
        return ok();
    }

    public Result landing(int formId) {
        // TODO should use lowest page nr, not 1?
        return showPage(formId, 1);
    }

    public Result showPage(int formId, int pageNr) {
        return ok(views.html.form.page.render(
                dac().getFormDao().getForm(formId),
                dac().getElementDao().listElements(formId, pageNr),
                this
        ));
    }

    private class AdditionalContentVisitor extends ElementVisitor<Html> {

        @Override
        public Html visitElement(Element element) {
            return views.html.form.unknown.render(element, FormDeputy.this);
        }

        @Override
        public Html visitInfo(InfoElement element) {
            return new Html("");
        }

        @Override
        public Html visitText(TextElement element) {
            return views.html.form.text.render(element, FormDeputy.this);
        }

        @Override
        public Html visitRadio(RadioElement element) {
            return views.html.form.radio.render(element, FormDeputy.this);
        }

        @Override
        public Html visitDate(DateElement element) {
            return views.html.form.date.render(element, FormDeputy.this);
        }

        @Override
        public Html visitTextArea(TextAreaElement element) {
            return views.html.form.text_area.render(element, FormDeputy.this);
        }

        @Override
        public Html visitCheckboxes(CheckboxesElement element) {
            return views.html.form.checkboxes.render(element, FormDeputy.this);
        }

        @Override
        public Html visitSelect(SelectElement element) {
            return views.html.form.select.render(element, FormDeputy.this);
        }

        @Override
        public Html visitButtons(ButtonsElement element) {
            return views.html.form.buttons.render(element, FormDeputy.this);
        }
    }

    public Html additionalContents(Element element) {
        return element.accept(new AdditionalContentVisitor());
    }
}
