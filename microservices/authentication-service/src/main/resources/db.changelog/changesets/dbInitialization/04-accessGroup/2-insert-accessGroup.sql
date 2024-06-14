-- liquibase formatted sql
--changeset suman.gajmer:1

--preconditions onFail:CONTINUE onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM access_group
INSERT INTO access_group (name, description, created_at, status, is_admin_group, remarks,type,version)
VALUES
    ('Futsal Admin', 'System group with full access', now(), 1, true, 'Super admin group',1,0);

