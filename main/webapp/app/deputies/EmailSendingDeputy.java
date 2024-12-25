/*
 * EmailSendingDeputy.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package deputies;

import common.DataAccessDeputy;
import lombok.Setter;
import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;

@Setter
public class EmailSendingDeputy extends DataAccessDeputy {
    private MailerClient mailerClient; // injected by controller!

    protected void sendEmail(String subject, String to, String text) {
        Email email = new Email()
                .setSubject(subject)
                .setFrom(config.getString("justin.no-reply-address"))
                .addTo(to)
                .setBodyText(text);
        mailerClient.send(email);
    }

    protected String hostUri() {
        // TODO is there a more reliable way to do this?
        String host = request.host();
        if (host.endsWith("443")) {
            return "https://" + host;
        } else {
            return "http://" + host;
        }
    }

}
