/*
 * MarkdownService.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package common;

import com.google.inject.ImplementedBy;

@ImplementedBy(MarkdownServiceImpl.class)
public interface MarkdownService {

    /**
     * Converts the string form markdown to Html.
     */
    String toHtml(String str);

}
