CREATE TABLE users
(
    id        SERIAL PRIMARY KEY,
    user_name VARCHAR NOT NULL UNIQUE,
    email     VARCHAR NOT NULL UNIQUE,
    password  VARCHAR NOT NULL
);
-- DROP TABLE users;