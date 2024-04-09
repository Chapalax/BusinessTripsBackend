CREATE SEQUENCE IF NOT EXISTS users_seq;

CREATE TABLE IF NOT EXISTS users
(
    id          BIGINT DEFAULT nextval('users_seq') PRIMARY KEY,
    cred_id     BIGINT REFERENCES credentials (id) ON DELETE CASCADE NOT NULL,
    first_name  TEXT                                                 NOT NULL,
    last_name   TEXT                                                 NOT NULL,
    father_name TEXT                                                 NOT NULL,
    grade       TEXT                                                 NOT NULL,
    birth_date  TIMESTAMP                                            NOT NULL,
    phone       VARCHAR(32)                                          NOT NULL,
    passport    TEXT                                                 NOT NULL
);

ALTER SEQUENCE users_seq OWNED BY users.id;
