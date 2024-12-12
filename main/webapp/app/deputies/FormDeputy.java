/*
 * FormDeputy.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package deputies;

import common.LoggedInDeputy;
import play.mvc.Result;

public class FormDeputy extends LoggedInDeputy {

    /**
     * Ajax call to change participation in a form for the current user
     */
    public Result changeParticipation(int formId, boolean participation) {
        dac().getFormDao().setParticipation(formId, participation);
        return ok();
    }

    public Result landing(int formId) {
        return ok("Landing page for form " + formId);
    }
}
