-- liquibase formatted sql
--changeset suman.gajmer:1
--preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS roles
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    version       BIGINT                NOT NULL,
    name          VARCHAR(255)          NOT NULL,
    `description` VARCHAR(255)          NULL,
    icon          VARCHAR(255)          NULL,
    navigation    VARCHAR(255)          NULL,
    position      INT                   NULL,
    ui_group_name VARCHAR(255)          NULL,
    parent_role   BIGINT                NULL,
    parent_name   VARCHAR(255)          NULL,
    permission    VARCHAR(255)          NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
    );


--changeset suman.gajmer:2
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0  SELECT COUNT(*) FROM information_schema.table_constraints WHERE constraint_schema = (SELECT DATABASE()) AND table_name = 'roles' AND constraint_name = 'FK_ROLES_ON_PARENT_ROLE'
ALTER TABLE roles
    ADD CONSTRAINT FK_ROLES_ON_PARENT_ROLE FOREIGN KEY (parent_role) REFERENCES roles (id);



