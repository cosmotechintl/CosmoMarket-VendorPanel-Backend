-- liquibase formatted sql
--changeset suman.gajmer:1
--preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS access_group
(
    id                   BIGINT AUTO_INCREMENT NOT NULL,
    version              BIGINT                NOT NULL,
    name                 VARCHAR(255)          NOT NULL,
    `description`        VARCHAR(255)          NULL,
    created_at           datetime              NULL,
    updated_at           datetime              NULL,
    status               BIGINT                NOT NULL,
    is_admin_group BIT(1)                NOT NULL,
    remarks              VARCHAR(255)          NULL,
    type                 BIGINT          NOT NULL,
    CONSTRAINT pk_access_group PRIMARY KEY (id)
    );



--changeset suman.gajmer:2
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0  SELECT COUNT(*) FROM information_schema.table_constraints WHERE constraint_schema = (SELECT DATABASE()) AND table_name = 'access_group' AND constraint_name = 'FK_ACCESS_GROUP_ON_STATUS'
ALTER TABLE access_group
    ADD CONSTRAINT FK_ACCESS_GROUP_ON_STATUS FOREIGN KEY (status) REFERENCES status (id);


--changeset suman.gajmer:3
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0  SELECT COUNT(*) FROM information_schema.table_constraints WHERE constraint_schema = (SELECT DATABASE()) AND table_name = 'roles' AND constraint_name = 'FK_ROLES_ON_PARENT_ROLE'
ALTER TABLE access_group
    ADD CONSTRAINT FK_ACCESS_GROUP_ON_STATUS FOREIGN KEY (futsalStatus) REFERENCES futsalStatus (id);

--changeset suman.gajmer:4
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0  SELECT COUNT(*) FROM information_schema.table_constraints WHERE constraint_schema = (SELECT DATABASE()) AND table_name = 'access_group' AND constraint_name = 'FK_ACCESS_GROUP_ON_TYPE'
ALTER TABLE access_group
    ADD CONSTRAINT FK_ACCESS_GROUP_ON_TYPE FOREIGN KEY (type) REFERENCES type (id);



