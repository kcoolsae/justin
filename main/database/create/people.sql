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
    user_country  CHAR(2) NOT NULL -- may reference a country table
);

CREATE TABLE tokens (
    user_id       INTEGER PRIMARY KEY REFERENCES users,
    token_text    TEXT UNIQUE NOT NULL,
    token_expires TIMESTAMP   NOT NULL
);


