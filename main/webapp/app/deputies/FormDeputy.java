/*
 * FormDeputy.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package deputies;

import be.ugent.justin.db.dto.Element;
import be.ugent.justin.db.dto.ElementVisitor;
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
            return views.html.form.testing.render(element, FormDeputy.this);
        }
    }

    public Html additionalContents(Element element) {
        return element.accept(new AdditionalContentVisitor());
    }
}
