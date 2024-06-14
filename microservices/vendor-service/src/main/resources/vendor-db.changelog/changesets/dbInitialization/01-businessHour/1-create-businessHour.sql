-- liquibase formatted sql

-- changeset amritkthapa:1
-- preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS business_hours (
    id          BIGINT AUTO_INCREMENT   NOT NULL,
    vendor_id   BIGINT,
    day         VARCHAR(10)             NOT NULL,
    version      BIGINT                 NOT NULL,
    start_time  TIME,
    end_time    TIME,
    closed      BOOLEAN,
    PRIMARY KEY (id),
    FOREIGN KEY (vendor_id) REFERENCES vendor(id)
);