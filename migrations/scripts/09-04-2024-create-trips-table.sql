CREATE SEQUENCE IF NOT EXISTS trips_seq;

CREATE TABLE IF NOT EXISTS trips
(
    id            BIGINT                                                   DEFAULT nextval('trips_seq') PRIMARY KEY,
    owner_id      BIGINT REFERENCES users (id) ON DELETE CASCADE  NOT NULL,
    boss_id       BIGINT REFERENCES users (id) ON DELETE RESTRICT NOT NULL,
    creation_date TIMESTAMP                                       NOT NULL DEFAULT now(),
    start_date    TIMESTAMP                                       NOT NULL,
    end_date      TIMESTAMP                                       NOT NULL,
    destination   VARCHAR(32)                                     NOT NULL,
    goal          TEXT                                            NOT NULL,
    status        VARCHAR(16)                                     NOT NULL DEFAULT 'WAITING',
    comment       TEXT
);

ALTER SEQUENCE trips_seq OWNED BY trips.id;
