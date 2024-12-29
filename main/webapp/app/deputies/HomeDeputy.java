/*
 * HomeDeputy.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2023-2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package deputies;

import be.ugent.justin.db.dao.EventDao;
import be.ugent.justin.db.dao.FormDao;
import be.ugent.justin.db.dto.Event;
import be.ugent.justin.db.dto.FormLabel;
import common.LoggedInDeputy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import play.mvc.Result;
import views.html.home.*;

import java.util.ArrayList;
import java.util.List;

public class HomeDeputy extends LoggedInDeputy {

    public record FormInfo (FormLabel label, boolean unanswered) {
    }

    private List<FormInfo> listFormInfo(int eventId) {
        // TODO do this on the database side
        FormDao dao = dac().getFormDao();
        List<FormLabel> formLabels = dao.listFormsRestricted(eventId);
        List<FormInfo> result = new ArrayList<>();
        for (FormLabel label : formLabels) {
            boolean unanswered = label.participation() && dao.hasUnansweredMandatoryQuestions(label.id());
            result.add(new FormInfo(label, unanswered));
        }
        return result;
    }

    /**
     * Landing page for a registered user.
     */
    public Result landing() {
        EventDao dao = dac().getEventDao();
        List<Event> events = dao.getVisibleEvents();
        if (events.isEmpty()) {
            return ok(no_event.render(this));
        } else {
            if (events.size() > 1) {
                warning ("Only the most recent event is shown.");
            }
            Event event = events.getFirst();
            if (event.participation() == null) {
                return ok(change_participation.render(
                        event,
                        emptyForm(ParticipationData.class),
                        dao.getUsers(event.id(), getCountry()),
                        this
                ));
            } else {
                return ok (switch (event.status()) {
                    case PUBLISHED -> published.render(
                            event,
                            dao.getUsers(event.id(), getCountry()),
                            this);
                    case OPEN -> open.render(
                            event,
                            listFormInfo(event.id()),
                            dao.getUsers(event.id(), getCountry()),
                            this
                    );
                    case CLOSED -> closed.render(event, this);
                    default -> no_event.render(this);
                });
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


    /**
     * Show the form to change participation
     */
    public Result showChangeParticipation(int eventId) {
        EventDao dao = dac().getEventDao();
        Event event = dao.getEvent(eventId);
        return ok(change_participation.render(
                event,
                formFromData(new ParticipationData(event.participation())),
                dao.getUsers(eventId, getCountry()),
                this
        ));
    }

    /**
     * Change whether a user participates in an event or not
     */
    public Result changeParticipation(int eventId) {
        Boolean participation = formFromRequest(ParticipationData.class).get().participation;
        if (participation != null) {
            // TODO only when event is open or published
            dac().getEventDao().setParticipation(eventId, participation);
        }
        return redirectToIndex();
    }

}
