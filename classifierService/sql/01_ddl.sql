CREATE SCHEMA classifier AUTHORIZATION postgres;

CREATE TABLE classifier.currency
(
    uuid UUID PRIMARY KEY,
    dt_create TIMESTAMP NOT NULL,
    dt_update TIMESTAMP,
    title VARCHAR NOT NULL,
    description VARCHAR,
    CONSTRAINT currency_unique_title UNIQUE (title)
);


ALTER TABLE IF EXISTS classifier.currency OWNER TO postgres;


CREATE TABLE classifier.operation_category
(
    uuid UUID PRIMARY KEY,
    dt_create TIMESTAMP NOT NULL,
    dt_update TIMESTAMP,
    title VARCHAR NOT NULL,
    CONSTRAINT operation_category_unique_title UNIQUE (title)
);


ALTER TABLE IF EXISTS classifier.operation_category OWNER TO postgres;
