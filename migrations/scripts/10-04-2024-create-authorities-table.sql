CREATE SEQUENCE IF NOT EXISTS authorities_seq;

CREATE TABLE IF NOT EXISTS authorities
(
    id        BIGINT DEFAULT nextval('authorities_seq') PRIMARY KEY,
    username  VARCHAR NOT NULL UNIQUE,
    authority VARCHAR NOT NULL
);

ALTER SEQUENCE authorities_seq OWNED BY authorities.id;