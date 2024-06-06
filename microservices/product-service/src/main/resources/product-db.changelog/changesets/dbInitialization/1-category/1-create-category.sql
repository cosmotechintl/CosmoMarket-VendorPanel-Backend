-- liquibase formatted sql
--changeset amritkthapa:1
CREATE TABLE IF NOT EXISTS category
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255)          NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE category ADD COLUMN version INT;