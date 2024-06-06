-- liquibase formatted sql
--changeset suman.gajmer:1

--preconditions onFail:CONTINUE onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM access_group
INSERT INTO access_group (name, description, created_at, status, is_super_admin_group, remarks,version)
VALUES
    ('Admin', 'System group with admin access', now(), 1, false, 'Admin group',0),
    ('User', 'System group with user access', now(), 1, false, 'User group',0);

