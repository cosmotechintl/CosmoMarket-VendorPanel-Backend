-- liquibase formatted sql
--changeset manjul.tamang:1
--preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS vendor
(
    id            BIGINT  AUTO_INCREMENT NOT NULL,
    version       BIGINT                NOT NULL,
    name          VARCHAR(255)          NOT NULL,
    logo          VARCHAR(255)          NULL,
    address       VARCHAR(255)          NULL,
    email         VARCHAR(255)          NOT NULL,
    phone_number  VARCHAR(255)          NOT NULL,
    pan_number    VARCHAR(255)          NOT NULL,
    is_active     BIT(1)                NULL,
    code          VARCHAR(255)         NOT NULL,
    category      BIGINT                NOT NULL,
    status        BIGINT                NOT NULL,
    created_at    datetime              NULL,
    updated_at    datetime              NULL,
    CONSTRAINT     pk_vendor PRIMARY KEY (id)
    );

--changeset manjul.tamang:2
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0  SELECT COUNT(*) FROM information_schema.table_constraints WHERE constraint_schema = (SELECT DATABASE()) AND table_name = 'vendor' AND constraint_name = 'FK_VENDOR_ON_CATEGORY'
ALTER TABLE vendor
    ADD CONSTRAINT FK_VENDOR_ON_CATEGORY FOREIGN KEY (category) REFERENCES category (id);

--changeset manjul.tamang:3
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0  SELECT COUNT(*) FROM information_schema.table_constraints WHERE constraint_schema = (SELECT DATABASE()) AND table_name = 'vendor' AND constraint_name = 'FK_VENDOR_ON_STATUS'
ALTER TABLE vendor
    ADD CONSTRAINT FK_VENDOR_ON_STATUS FOREIGN KEY (status) REFERENCES status (id);

--changeset manjul.tamang:4
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0  SELECT COUNT(*) FROM information_schema.table_constraints WHERE constraint_schema = (SELECT DATABASE()) AND table_name = 'vendor' AND constraint_name = 'uc_vendor_pan_number'
ALTER TABLE vendor
    ADD CONSTRAINT uc_vendor_pan_number UNIQUE (pan_number);

--changeset manjul.tamang:5
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0  SELECT COUNT(*) FROM information_schema.table_constraints WHERE constraint_schema = (SELECT DATABASE()) AND table_name = 'vendor' AND constraint_name = 'uc_vendor_code'
ALTER TABLE vendor
    ADD CONSTRAINT uc_vendor_code UNIQUE (code);






