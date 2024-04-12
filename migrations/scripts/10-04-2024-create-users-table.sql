CREATE SEQUENCE IF NOT EXISTS users_seq;

CREATE TABLE IF NOT EXISTS users
(
    id          BIGINT               DEFAULT nextval('users_seq') PRIMARY KEY,
    username    VARCHAR     NOT NULL UNIQUE,
    password    VARCHAR     NOT NULL,
    role        VARCHAR     NOT NULL,
    enabled     BOOLEAN     NOT NULL DEFAULT TRUE,
    first_name  TEXT        NOT NULL,
    last_name   TEXT        NOT NULL,
    father_name TEXT        NOT NULL,
    grade       TEXT        NOT NULL,
    birth_date  TIMESTAMP   NOT NULL,
    email       TEXT        NOT NULL,
    phone       VARCHAR(32) NOT NULL,
    passport    TEXT        NOT NULL
);

ALTER SEQUENCE users_seq OWNED BY users.id;