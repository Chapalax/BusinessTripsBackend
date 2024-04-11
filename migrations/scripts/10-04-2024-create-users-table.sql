CREATE SEQUENCE IF NOT EXISTS users_seq;

CREATE TABLE IF NOT EXISTS users (
    id       BIGINT           DEFAULT nextval('users_seq') PRIMARY KEY,
    username VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL,
    roles VARCHAR NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE
);

ALTER SEQUENCE users_seq OWNED BY users.id;