-- liquibase formatted sql

-- changeset amritkthapa:1
-- preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS business_hours (
    id          BIGINT AUTO_INCREMENT   NOT NULL,
    vendor      BIGINT,
    day         TINYINT                 NOT NULL,
    version      BIGINT                 NOT NULL,
    start_time  TIME,
    end_time    TIME,
    closed      BOOLEAN,
    PRIMARY KEY (id),
    FOREIGN KEY (vendor) REFERENCES vendor(id)
);