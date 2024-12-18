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
import be.ugent.justin.db.dao.FormDao;
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

    public Result landing(int formId) {
        // TODO present an instruction/warning page when no answer have yet been entered for this form
        return redirect(routes.FormController.showPage(formId, dac().getFormDao().nextPage(formId, 0)));
    }

    public Result showPage(int formId, int pageNr) {

        FormDao dao = dac().getFormDao();
        return ok(views.html.form.page.render(
                dao.getForm(formId),
                pageNr,
                dao.nextPage(formId, pageNr),
                dao.previousPage(formId, pageNr),
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
        public int nextPage; // hidden field in form
    }

    public Result submitPage(int formId, int pageNr) {

        PageData data = formFromRequest(PageData.class).get();
        ValueToString valueToString = new ValueToString(data);

        // TODO no database calls if nothing changed on the page
        ElementDao dao = dac().getElementDao();
        for (Element element : dao.listElements(formId, pageNr)) {
            String value = element.accept(valueToString);
            if (value != null) {
                dao.setAnswer(element.getId(), value);
            }
        }
        if (data.nextPage == 0) {
            return redirectToIndex();
        } else {
            return redirect(routes.FormController.showPage(formId, data.nextPage));
        }
    }

    @Getter
    @Setter
    public static class ParticipationData {
        public Map<Integer,Boolean> participation;
    }

    public Result changeParticipations(int eventId) {
        Map<Integer,Boolean> map = formFromRequest(ParticipationData.class).get().participation;
        FormDao dao = dac().getFormDao();
        for (Integer formId : dao.listFormIds(eventId)) {
            boolean participation = map != null && map.getOrDefault(formId,false);
            dao.setParticipation(formId, participation);
        }
        return redirectToIndex();
    }

}
