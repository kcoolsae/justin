/*
 * FormDeputy.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package deputies;

import be.ugent.justin.db.dao.ElementDao;
import be.ugent.justin.db.dto.*;
import common.LoggedInDeputy;
import controllers.routes;
import lombok.Getter;
import lombok.Setter;
import play.data.Form;
import play.mvc.Result;
import play.twirl.api.Html;

import java.util.Map;

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
                pageNr,
                dac().getElementDao().listElements(formId, pageNr),
                this
        ));
    }

    public Html additionalContents(Element element) {
        return element.accept(new AdditionalContent(this));
    }

    @Getter
    @Setter
    public static class PageData {
        public Map<Integer, Integer> number;     // for multiple choice
        public Map<Integer, String> string;      // for text fields and others
        public Map<Integer, Map<Integer, Boolean>> check; // for checkboxes
    }

    public Result submitPage(int formId, int pageNr) {

        Form<PageData> form = formFromRequest(PageData.class);
        PageData pageData = form.get();
        ValueToString valueToString = new ValueToString(pageData);

        // TODO no database calls if nothing changed on the page
        ElementDao dao = dac().getElementDao();
        for (Element element : dao.listElements(formId, pageNr)) {
            String value = element.accept(valueToString);
            if (value != null) {
                dao.setAnswer(element.getId(), value);
            }
        }

        return redirect(routes.FormController.showPage(formId, pageNr + 1)); // TODO check action
    }
}
