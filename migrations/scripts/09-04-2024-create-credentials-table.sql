CREATE SEQUENCE IF NOT EXISTS credentials_seq;

CREATE TABLE IF NOT EXISTS credentials
(
    id       BIGINT            DEFAULT nextval('credentials_seq') PRIMARY KEY,
    login    TEXT     NOT NULL UNIQUE,
    password TEXT     NOT NULL,
    role     SMALLINT NOT NULL DEFAULT 2
);

ALTER SEQUENCE credentials_seq OWNED BY credentials.id;