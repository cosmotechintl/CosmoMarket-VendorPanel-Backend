-- liquibase formatted sql
--changeset manjul.tamang:1

--preconditions onFail:CONTINUE onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM type
INSERT INTO type (name,description, version)
VALUES
    ('VENDOR','VENDOR',0),
    ('VENDOR_ADMIN','VENDOR_ADMIN',0),
    ('VENDOR_STAFF','VENDOR_STAFF',0);
