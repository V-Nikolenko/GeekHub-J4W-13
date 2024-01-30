CREATE TABLE IF NOT EXISTS books
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    author      VARCHAR(255) NOT NULL,
    publishDate TIMESTAMP
)
