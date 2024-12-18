/*
 * FormController.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package controllers;

import common.LoggedInController;
import deputies.FormDeputy;
import play.mvc.Http;
import play.mvc.Result;

public class FormController extends LoggedInController<FormDeputy> {

    public FormController() {
        super(FormDeputy::new);
    }

    public Result changeParticipations(Http.Request request, int eventId) {
        return createDeputy(request).changeParticipations(eventId);
    }

    public Result landing(Http.Request request, int formId) {
        return createDeputy(request).landing(formId);
    }

    public Result showPage(Http.Request request, int formId, int pageNr) {
        return createDeputy(request).showPage(formId, pageNr);
    }

    public Result submitPage(Http.Request request, int formId, int pageNr) {
        return createDeputy(request).submitPage(formId, pageNr);
    }
}
