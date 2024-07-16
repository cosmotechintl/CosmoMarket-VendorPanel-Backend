-- liquibase formatted sql

-- changeset amritkthapa:1
-- preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS business_hours (
    id          BIGINT AUTO_INCREMENT   NOT NULL,
    vendor_code VARCHAR(255),
    day         TINYINT                 NOT NULL,
    version      BIGINT                 NOT NULL,
    start_time  TIME,
    end_time    TIME,
    closed      BOOLEAN,
    uuid        VARCHAR(255)          NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (vendor_code) REFERENCES vendor(code)
);