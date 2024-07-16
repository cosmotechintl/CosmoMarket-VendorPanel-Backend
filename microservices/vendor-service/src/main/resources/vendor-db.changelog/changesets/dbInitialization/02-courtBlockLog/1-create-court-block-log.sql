-- liquibase formatted sql
--changeset manjul.tamang:1
--preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS `court_block_log`
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    version     BIGINT                NOT NULL,
    remarks     VARCHAR(255)          NOT NULL,
    court       BIGINT               NOT NULL,
    blocked_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_court_block_log PRIMARY KEY (id)
    );