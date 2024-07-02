-- liquibase formatted sql

-- changeset samir.ghimire:1
-- preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS product_category (
    id             BIGINT AUTO_INCREMENT    NOT NULL,
    version      BIGINT                     NOT NULL,
    name           VARCHAR(255)             NOT NULL,
    description    VARCHAR(255)             NOT NULL,
    code           VARCHAR(255)             NOT NULL,
    created_at     datetime                 NULL,
    updated_at     datetime                 NULL,
    futsalStatus          BIGINT                  NOT NULL,
    FOREIGN KEY (futsalStatus) REFERENCES futsalStatus(id),
    PRIMARY KEY (id)
);