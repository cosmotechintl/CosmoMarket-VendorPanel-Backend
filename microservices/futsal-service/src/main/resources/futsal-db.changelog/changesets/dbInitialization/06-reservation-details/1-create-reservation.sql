-- liquibase formatted sql
-- changeset samir.ghimire:1
-- preconditions onFail:CONTINUE onError:HALT

CREATE TABLE IF NOT EXISTS `reservation_details`
(
    id           BIGINT AUTO_INCREMENT   NOT NULL,
    vendor_code  VARCHAR(255)            NOT NULL,
    futsal_uuid  VARCHAR(255)            NOT NULL,
    version      BIGINT                  NOT NULL,
    code         VARCHAR(255)            NOT NULL,
    date         DATE                    NOT NULL,
    start_time   TIME                    NOT NULL,
    end_time     TIME                    NOT NULL,
    day          INT                     NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (futsal_uuid) REFERENCES futsal(uuid)
    );
