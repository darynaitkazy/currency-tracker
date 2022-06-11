DROP TABLE IF EXISTS eur;
DROP TABLE IF EXISTS usd;
DROP TABLE IF EXISTS rub;
DROP TABLE IF EXISTS chat_ids;
DROP SEQUENCE IF EXISTS global_seq;
CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE eur
(
    id         INTEGER PRIMARY KEY  DEFAULT nextval('global_seq'),
    date       VARCHAR                       NOT NULL,
    price       VARCHAR                       NOT NULL,
    difference       VARCHAR                       NOT NULL
);
CREATE TABLE usd
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    date       VARCHAR                       NOT NULL,
    price       VARCHAR                       NOT NULL,
    difference       VARCHAR                       NOT NULL
);
CREATE TABLE rub
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    date       VARCHAR                       NOT NULL,
    price       VARCHAR                       NOT NULL,
    difference       VARCHAR                       NOT NULL
);
CREATE TABLE chat_ids
(
    id         INTEGER PRIMARY KEY  DEFAULT nextval('global_seq'),
    chat_id       VARCHAR                       NOT NULL
);
