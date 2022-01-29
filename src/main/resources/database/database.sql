CREATE DATABASE bookcase_db ENCODING 'UTF-8';

CREATE TABLE users
(
    login    VARCHAR(100) NOT NULL PRIMARY KEY,
    password VARCHAR(160) NOT NULL,
    status   varchar(20)  NOT NULL,
    role     varchar(20)  NOT NULL
);

CREATE TABLE books
(
    book_id SERIAL PRIMARY KEY,
    title   VARCHAR(100) NOT NULL,
    author  VARCHAR(100) NOT NULL,
    read    BOOL         NOT NULL,
    path    VARCHAR(100) NOT NULL,
    user_id VARCHAR(100) REFERENCES users (login)
);