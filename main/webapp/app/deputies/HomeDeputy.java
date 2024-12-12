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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import play.data.Form;
import play.mvc.Result;

import views.html.home.*;

import java.util.List;

public class HomeDeputy extends LoggedInDeputy {

    /**
     * Landing page for a registered user.
     */
    public Result landing() {
        List<Event> events = dac().getEventDao().getVisibleEvents();
        if (events.isEmpty()) {
            return ok(no_event.render(this));
        } else {
            if (events.size() > 1) {
                warning ("Only the most recent event is shown.");
            }
            Event event = events.getFirst();
            if (event.participation() != null) {
                return ok(landing.render(event, this));
            } else {
                return ok(change_participation.render(
                        event,
                        emptyForm(ParticipationData.class),
                        this
                ));
            }
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ParticipationData {
        public Boolean participation; // default null
    }

    // used in template
    public Form<ParticipationData> getEmptyParticipationForm() {
        return emptyForm(ParticipationData.class);
    }

    /**
     * Show the form to change participation
     */
    public Result showChangeParticipation(String eventId) {
        Event event = dac().getEventDao().getEvent(eventId);
        return ok(change_participation.render(
                event,
                formFromData(new ParticipationData(event.participation())),
                this
        ));
    }

    /**
     * Change whether a user participates in an event or not
     */
    public Result changeParticipation(String eventId) {
        Boolean participation = formFromRequest(ParticipationData.class).get().participation;
        if (participation != null) {
            dac().getEventDao().setParticipation(eventId, participation);
        }
        return redirectToIndex();
    }

}
