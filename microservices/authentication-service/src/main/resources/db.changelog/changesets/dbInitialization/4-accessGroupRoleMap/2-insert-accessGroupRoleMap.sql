-- liquibase formatted sql
--changeset suman.gajmer:1

--preconditions onFail:CONTINUE onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM access_group_role_map
INSERT INTO access_group_role_map (access_group, is_active, roles,version)
select (Select id from access_group where name='Admin'),
       true, id,0
from roles;
