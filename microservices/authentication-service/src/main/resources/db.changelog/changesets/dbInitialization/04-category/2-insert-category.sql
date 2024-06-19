-- liquibase formatted sql
--changeset manjul.tamang:1

--preconditions onFail:CONTINUE onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM category
INSERT INTO category (name,description, version)
VALUES
    ('FUTSAL','FUTSAL',0),
    ('RESTAURANT','RESTAURANT',0),
    ('HOTEL','HOTEL',0),
    ('MART','MART',0);
