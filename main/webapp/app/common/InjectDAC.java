/*
 * InjectDAC.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package common;

import be.ugent.justin.db.DataAccessContext;
import be.ugent.justin.db.DataAccessProvider;
import play.libs.typedmap.TypedKey;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

/**
 * Used with the @@With-annotation to ensure that makes all action methods in such a controller run in a database transaction
 * and provides a data access context for them.
 */
public class InjectDAC extends Action.Simple {

    public static final TypedKey<DataAccessContext> DATA_ACCESS_CONTEXT = TypedKey.create("DataAccessContext");

    @Inject
    DataAccessProvider dap;

    @Override
    public CompletionStage<Result> call(Http.Request request) {
        Optional<String> id = request.session().get(Session.ID);
        try (DataAccessContext dac = dap.getContext(
                Integer.parseInt(id.orElse("0"))
        )) {
            try {
                dac.begin();
                CompletionStage<Result> result = delegate.call(request.addAttr(DATA_ACCESS_CONTEXT, dac));
                dac.commit();
                return result;
            } catch (Exception ex) {
                dac.rollback();
                throw ex;
            }
        } catch (RuntimeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("Action terminated by checked exception", ex);
        }
    }

}

