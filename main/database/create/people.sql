--  people.sql
--  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
--  Copyright © 2024 Kris Coolsaet (Universiteit Gent)
--
--  This software is distributed under the MIT License - see files LICENSE and AUTHORS
--  in the top level project directory.

CREATE TABLE users (
    user_id       SERIAL PRIMARY KEY,
    user_email    TEXT UNIQUE NOT NULL, -- always trimmed lower case
    user_name     TEXT NOT NULL,
    user_country  CHAR(2) NOT NULL, -- may reference a country table
    user_temporary BOOLEAN NOT NULL DEFAULT FALSE -- temporary users are deleted after an event
);

-- to be extended
CREATE TYPE privilege_types
AS ENUM ('REGISTER_OWN', 'REGISTER_ALL'); -- upper case for java enums

CREATE TABLE privileges (
    user_id INTEGER REFERENCES users,
    privilege_type privilege_types,
    privilege_detail TEXT -- e.g, category for which edit rights are granted
);

-- used for logging in and changing email address
CREATE TABLE tokens (
    user_id       INTEGER PRIMARY KEY REFERENCES users,
    token_text    TEXT UNIQUE NOT NULL,
    token_expires TIMESTAMP   NOT NULL,
    token_for_email_change   BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE registrations (
    registration_email   TEXT UNIQUE NOT NULL, -- always trimmed lower case
    registration_country CHAR(2)     NOT NULL,
    registration_as_temporary BOOLEAN NOT NULL DEFAULT FALSE,
    registration_token   TEXT        NOT NULL,
    registration_expires TIMESTAMP   NOT NULL
);


