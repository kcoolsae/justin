/*
 * CheckRole.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package common;

import be.ugent.justin.db.dto.PrivilegeType;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CompletableFuture;


/**
 * Checks whether the session is still active, and redirects to a login page if it is not. In addition
 * it checks whether the user has one of the provide privileges.
 */
public abstract class CheckPrivileges extends Action.Simple {

    private final PrivilegeType[] privileges;

    protected CheckPrivileges(PrivilegeType... privileges) {
        this.privileges = privileges;
    }

    private boolean hasPrivilege(Optional<String> privilege) {
        if (privileges.length == 0) {
            return true;
        } else if (privilege.isEmpty()) {
            return false;
        } else {
            for (PrivilegeType p : privileges) {
                int pos = privilege.get().indexOf(p.getCode());
                if (pos >= 0) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public CompletionStage<Result> call(Http.Request request) {
        Http.Session session = request.session();
        if (session == null || session.get(Session.ID).isEmpty()) {
            return CompletableFuture.completedFuture(
                    redirect(controllers.routes.InitialController.index()).flashing(
                            "warning", "Your session has expired, please sign in again."
                    )
            );
        } else if (hasPrivilege(session.get(Session.PRIVILEGES))) {
            return delegate.call(request);
        } else {
            return CompletableFuture.completedFuture(unauthorized());
        }
    }




}
