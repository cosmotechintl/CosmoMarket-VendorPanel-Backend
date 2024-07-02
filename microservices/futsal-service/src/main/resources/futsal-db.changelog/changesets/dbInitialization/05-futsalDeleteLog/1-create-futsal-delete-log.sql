-- liquibase formatted sql
--changeset amritkthapa:1
--preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS `futsal_delete_log`
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    version     BIGINT                NOT NULL,
    remarks     VARCHAR(255)          NOT NULL,
    futsal       BIGINT               NOT NULL,
    deleted_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_futsal_delete_log PRIMARY KEY (id)
    );