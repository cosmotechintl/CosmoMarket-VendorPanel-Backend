-- liquibase formatted sql
-- changeset samirghimire:1
-- preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS `court_details`
(
    id           BIGINT AUTO_INCREMENT   NOT NULL,
    version      BIGINT                 NOT NULL,
    name         VARCHAR(255)            NOT NULL,
    image        VARCHAR(255)            NOT NULL,
    description  VARCHAR(255)            NOT NULL,
    status      BIGINT                  NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (status) REFERENCES status(id)
    );
