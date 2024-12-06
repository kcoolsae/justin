/*
 * HomeDeputy.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2023-2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package deputies;

import common.Session;
import play.mvc.Result;
import views.html.auth.sign_in;

public class HomeDeputy extends Deputy {

    /**
     * Starting page
     */
    public Result index() {
        if (findInSession(Session.ID) != null) {
            return landing();
        } else {
            return ok(sign_in.render(emptyForm(AuthenticationDeputy.EmailData.class), this));
        }
    }

    /**
     * Landing page for a registered user.
     */
    private Result landing() {
        return ok(views.html.home.landing.render(this));
    }

    /**
     * Page explaining that this part of the application is
     * not yet implemented.
     */
    public Result todo() {
        return ok(views.html.other.todo.render());
    }
}
