/*
 * HomeDeputy.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2023-2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package deputies;

import common.LoggedInDeputy;
import play.mvc.Result;

public class HomeDeputy extends LoggedInDeputy {

    /**
     * Landing page for a registered user.
     */
    public Result landing() {
        return ok(views.html.home.landing.render(this));
    }

}
