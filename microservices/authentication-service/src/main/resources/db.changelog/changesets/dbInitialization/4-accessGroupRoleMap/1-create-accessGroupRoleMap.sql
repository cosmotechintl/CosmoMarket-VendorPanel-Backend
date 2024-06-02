-- liquibase formatted sql
--changeset suman.gajmer:1
--preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS access_group_role_map
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    version      BIGINT                NOT NULL,
    access_group BIGINT                NOT NULL,
    is_active    BIT(1)                NOT NULL,
    roles        BIGINT                NOT NULL,
    CONSTRAINT pk_access_group_role_map PRIMARY KEY (id)
    );


--changeset suman.gajmer:2
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0  SELECT COUNT(*) FROM information_schema.table_constraints WHERE constraint_schema = (SELECT DATABASE()) AND table_name = 'access_group_role_map' AND constraint_name = 'FK_ACCESS_GROUP_ROLE_MAP_ON_ACCESS_GROUP'
ALTER TABLE access_group_role_map
    ADD CONSTRAINT FK_ACCESS_GROUP_ROLE_MAP_ON_ACCESS_GROUP FOREIGN KEY (access_group) REFERENCES access_group (id);


--changeset suman.gajmer:3
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0  SELECT COUNT(*) FROM information_schema.table_constraints WHERE constraint_schema = (SELECT DATABASE()) AND table_name = 'access_group_role_map' AND constraint_name = 'FK_ACCESS_GROUP_ROLE_MAP_ON_ROLES'
ALTER TABLE access_group_role_map
    ADD CONSTRAINT FK_ACCESS_GROUP_ROLE_MAP_ON_ROLES FOREIGN KEY (roles) REFERENCES roles (id);
