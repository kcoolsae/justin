--  forms.sql
--  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
--  Copyright © 2024 Kris Coolsaet (Universiteit Gent)
--
--  This software is distributed under the MIT License - see files LICENSE and AUTHORS
--  in the top level project directory.

CREATE TABLE forms (
    form_id       INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    event_id      INTEGER REFERENCES events,
    form_key      TEXT UNIQUE,
    form_label    TEXT NOT NULL, -- for checkboxes on event pages
    form_seq_nr   INTEGER GENERATED BY DEFAULT AS IDENTITY, -- sequence number for ordering on page
    form_deadline DATE
);

-- When present in this table, the user has indicated to 'participate' in the form
CREATE TABLE user_forms (
    form_id      INTEGER REFERENCES forms,
    user_id      INTEGER REFERENCES users,
    PRIMARY KEY (form_id, user_id)
)