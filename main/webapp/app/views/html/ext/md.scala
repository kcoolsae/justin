/*
 * md.scala
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package views.html.ext

import play.twirl.api.Html
import common.LoggedInDeputy

object md {

  /**
   * Convert markdown string to Html.
   */
  def apply(string: String)(implicit deputy : LoggedInDeputy): Html
        = Html(deputy.toHtml(string))

}
