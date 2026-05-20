CREATE TABLE users
(
    id           BIGSERIAL PRIMARY KEY,
    username     VARCHAR(50) UNIQUE  NOT NULL,
    password     VARCHAR(255),
    email        VARCHAR(255) UNIQUE NOT NULL,
    display_name VARCHAR(255),
    dob          DATE,
    gender       VARCHAR(50),
    role         VARCHAR(50),
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);