-- liquibase formatted sql
--changeset manjul.tamang:1
--preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS vendor_category
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    version       BIGINT                NOT NULL,
    name          VARCHAR(255)          NOT NULL,
    `description` VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
    );

--changeset manjul.tamang:2
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0  SELECT COUNT(*) FROM information_schema.table_constraints WHERE constraint_schema = (SELECT DATABASE()) AND table_name = 'vendor_category' AND constraint_name = 'uc_category_name'
ALTER TABLE vendor_category
    ADD CONSTRAINT uc_category_name UNIQUE (name);