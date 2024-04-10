CREATE SEQUENCE IF NOT EXISTS users_data_seq;

CREATE TABLE IF NOT EXISTS users_data
(
    id          BIGINT DEFAULT nextval('users_data_seq') PRIMARY KEY,
    user_id     BIGINT REFERENCES users (id) ON DELETE CASCADE NOT NULL,
    first_name  TEXT                                           NOT NULL,
    last_name   TEXT                                           NOT NULL,
    father_name TEXT                                           NOT NULL,
    grade       TEXT                                           NOT NULL,
    birth_date  TIMESTAMP                                      NOT NULL,
    phone       VARCHAR(32)                                    NOT NULL,
    passport    TEXT                                           NOT NULL
);

ALTER SEQUENCE users_data_seq OWNED BY users_data.id;
