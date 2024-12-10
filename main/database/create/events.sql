--  events.sql
--  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
--  Copyright © 2024 Kris Coolsaet (Universiteit Gent)
--
--  This software is distributed under the MIT License - see files LICENSE and AUTHORS
--  in the top level project directory.

CREATE TYPE event_type
    AS ENUM ('PENDING', 'PUBLISHED', 'OPEN', 'CLOSED', 'ARCHIVED'); -- upper case for java enums

CREATE TABLE events (
    event_id          TEXT PRIMARY KEY,
    event_name        TEXT,
    event_description TEXT, -- allows markdown
    event_type        event_type
);