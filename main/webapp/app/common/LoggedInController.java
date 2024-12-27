/*
 * LoggedInController.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package common;

import play.mvc.Http;
import play.mvc.With;

import javax.inject.Inject;
import java.util.function.Supplier;

/**
 * Common super class of all controllers that need the user to be logged in.
 * Also provides access to the markdown service.
 */
@With(CheckLoggedIn.class)
public class LoggedInController<D extends LoggedInDeputy> extends DataAccessController<D> {

    @Inject
    private MarkdownService markdownService;

    public LoggedInController(Supplier<D> deputyFactory) {
        super(deputyFactory);
    }

    @Override
    protected D createDeputy(Http.Request request) {
        D deputy = super.createDeputy(request);
        deputy.setMarkdownService(markdownService);
        return deputy;
    }
}
