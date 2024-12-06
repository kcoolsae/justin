--  people.sql
--  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
--  Copyright © 2024 Kris Coolsaet (Universiteit Gent)
--
--  This software is distributed under the MIT License - see files LICENSE and AUTHORS
--  in the top level project directory.

CREATE TABLE users (
    user_id       SERIAL PRIMARY KEY,
    user_email    TEXT UNIQUE NOT NULL -- always trimmed lower case
);

CREATE TABLE tokens (
    user_id       INTEGER PRIMARY KEY REFERENCES users,
    token_text    TEXT UNIQUE NOT NULL,
    token_expires TIMESTAMP   NOT NULL
);


