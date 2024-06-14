--liquibase formatted sql
--changeset amritkthapa:1
--preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS `vendor`
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    version         BIGINT                NOT NULL,
    vendor_name     VARCHAR(255)          NOT NULL,
    category        VARCHAR(255)          NOT NULL,
    logo            VARCHAR(255),
    address         VARCHAR(255)          NOT NULL,
    email           VARCHAR(255)          NOT NULL,
    phone_number    VARCHAR(255)          NOT NULL,
    CONSTRAINT      pk_vendor PRIMARY KEY (id)
);