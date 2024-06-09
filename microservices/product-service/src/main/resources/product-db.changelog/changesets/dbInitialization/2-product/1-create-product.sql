-- liquibase formatted sql
--changeset amritkthapa:1
--preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS product
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    name            VARCHAR(255)          NOT NULL,
    price           DECIMAL(10, 2)       NOT NULL,
    description     VARCHAR(255)          NOT NULL,
    brand           VARCHAR(255),
    size            VARCHAR(255),
    color           VARCHAR(255),
    in_stock        BOOLEAN               NOT NULL,
    quantity        INT                   NOT NULL,
    category_id     BIGINT                NOT NULL,
    image           VARCHAR(255),
    updated_by      VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES category(id)
);

ALTER TABLE product ADD COLUMN version INT;


