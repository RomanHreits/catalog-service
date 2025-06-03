CREATE TABLE book (
    id BIGSERIAL NOT NULL,
    version BIGINT NOT NULL DEFAULT 0,
    isbn VARCHAR(20) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    price float8 NOT NULL,
    created_date timestamp NOT NULL,
    last_modified_date timestamp NOT NULL,
    PRIMARY KEY (id)
);