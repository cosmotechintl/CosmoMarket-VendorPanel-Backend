-- liquibase formatted sql
--changeset kirankhanal:1
--preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS `registration_email_log`
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    version     BIGINT                NOT NULL,
    email       VARCHAR(255)          NOT NULL,
    vendor_user       BIGINT                NOT NULL,
    message     TEXT                  NOT NULL,
    is_sent     BOOLEAN,
    is_expired  BOOLEAN DEFAULT FALSE,
    uuid        VARCHAR(255)          NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_registration_email_log PRIMARY KEY (id)
    );

--changeset kirankhanal:2
--preconditions onFail:CONTINUE onError:HALT
CREATE EVENT IF NOT EXISTS update_is_expired
ON SCHEDULE EVERY 1 MINUTE
DO
BEGIN
UPDATE registration_email_log
SET is_expired = TRUE
WHERE created_at <= NOW() - INTERVAL 24 HOUR AND is_expired = FALSE;
END;

--changeset kirankhanal:3
--preconditions onFail:CONTINUE onError:HALT
SET GLOBAL event_scheduler = ON;
