-- liquibase formatted sql
--changeset suman.gajmer:1

--preconditions onFail:CONTINUE onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM roles
INSERT INTO roles (description, icon, name, navigation, parent_name, permission, position, ui_group_name, version)
VALUES ('Root', '', 'Root', 'NONE', 'ROOT', 'NONE', 0, 'NONE',0);


--changeset suman.gajmer:2
UPDATE roles AS r
    INNER JOIN roles AS parent ON r.parent_name = parent.name
    SET r.parent_role = parent.id;


