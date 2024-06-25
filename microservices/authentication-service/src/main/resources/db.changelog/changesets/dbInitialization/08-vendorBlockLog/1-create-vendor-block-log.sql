-- liquibase formatted sql
--changeset manjul.tamang:1
--preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS `vendor_block_log`
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    version     BIGINT                NOT NULL,
    remarks     VARCHAR(255)          NOT NULL,
    vendor       BIGINT               NOT NULL,
    blocked_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_vendor_block_log PRIMARY KEY (id)
    );