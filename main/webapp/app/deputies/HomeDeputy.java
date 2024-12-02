/*
 * HomeDeputy.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2023-2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package deputies;

import be.ugent.caagt.play.deputies.Deputy;
import play.mvc.Result;

public class HomeDeputy extends Deputy {

    /**
     * Login page
     */
    public Result index() {
        return ok(views.html.auth.sign_in.render());
    }

    /**
     * Page explaining that this part of the application is
     * not yet implemented.
     */
    public Result todo() {
        return ok(views.html.other.todo.render());
    }
}
