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
    form_title    TEXT NOT NULL, -- for the form pages
    form_description TEXT,       -- markdown is allowed
    form_seq_nr   INTEGER GENERATED BY DEFAULT AS IDENTITY, -- sequence number for ordering on page
    form_deadline DATE,
    UNIQUE (event_id, form_key)
);

-- When present in this table, the user has indicated to 'participate' in the form
CREATE TABLE user_forms (
    form_id      INTEGER REFERENCES forms,
    user_id      INTEGER REFERENCES users,
    PRIMARY KEY (form_id, user_id)
);

CREATE TYPE element_type
    AS ENUM ('RADIO', 'BUTTONS', 'SELECT', 'CHECKBOXES',
             'TEXT', 'TEXT_AREA', 'DATE',
             'INFO'); -- upper case for java enums

-- Form elements
CREATE TABLE elements (
    element_id    INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    form_id       INTEGER REFERENCES forms,
    element_key   TEXT,
    element_title TEXT,
    element_description TEXT,
    element_type  element_type,
    element_page_nr INTEGER NOT NULL,
    element_seq_nr INTEGER GENERATED BY DEFAULT AS IDENTITY, -- sequence number for ordering witin form
    element_required BOOLEAN NOT NULL DEFAULT FALSE,
    element_default TEXT,  -- default value expression
    element_extra TEXT,    -- extra information for some types, e.g., other
    UNIQUE (form_id, element_key)
);

-- Form element options
CREATE TABLE options (
    option_id     INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    element_id    INTEGER REFERENCES elements,
    option_key    TEXT NOT NULL,
    option_text   TEXT NOT NULL,
    option_seq_nr INTEGER GENERATED BY DEFAULT AS IDENTITY, -- sequence number for ordering within element
    UNIQUE (element_id, option_key)
);
