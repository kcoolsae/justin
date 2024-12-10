/*
 * HomeDeputy.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2023-2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package deputies;

import be.ugent.justin.db.dto.Event;
import common.LoggedInDeputy;
import play.mvc.Result;

import java.util.List;

public class HomeDeputy extends LoggedInDeputy {

    /**
     * Landing page for a registered user.
     */
    public Result landing() {
        List<Event> events = dac().getEventDao().getVisibleEvents();
        return ok(views.html.home.landing.render(events,this));
    }

}
