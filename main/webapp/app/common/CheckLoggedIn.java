/*
 * CheckLoggedIn.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2023-2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package common;

import play.api.mvc.Call;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * Checks whether the session is still active, and redirects to the index page if it is not.
 * To be used in a @@With-annotation
 */
public class CheckLoggedIn extends Action.Simple{

    private Call loginCall() {
        return controllers.routes.InitialController.index();
    }

    @Override
    public CompletionStage<Result> call(Http.Request request) {
        Http.Session session = request.session();
        if (session == null || session.get(Session.ID).isEmpty()) {
            return CompletableFuture.completedFuture(
                    redirect(loginCall()).flashing(
                            "warning", "Your session has expired, please sign in again."
                    )
            );
        } else {
            return delegate.call(request);
        }
    }
}
