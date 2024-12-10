/*
 * DataAccessDeputy.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package common;

import be.ugent.caagt.play.controllers.Controller;
import be.ugent.justin.db.DataAccessContext;
import deputies.Deputy;

/**
 * Base class for deputies of {@link DataAccessController}. Provides a data access context and some helper methods
 */
public class DataAccessDeputy extends Deputy {

    protected DataAccessContext dac() {
        return request.attrs().get(InjectDAC.DATA_ACCESS_CONTEXT);
    }

    @Override
    public void setParent(Controller<?> parent) throws RuntimeException {
        if (!(parent instanceof DataAccessController)) {
            throw new RuntimeException("Parent of " + getClass() + " should be instance of DataAccessController");
        }
    }

}
