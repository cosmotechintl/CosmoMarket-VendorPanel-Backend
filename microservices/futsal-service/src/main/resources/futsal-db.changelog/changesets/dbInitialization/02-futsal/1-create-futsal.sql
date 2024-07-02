-- liquibase formatted sql
--changeset manjul.tamang:1
--preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS `futsal`
(
    id                           BIGINT AUTO_INCREMENT NOT NULL,
    version                      BIGINT                NOT NULL,
    name                         VARCHAR(255)          NOT NULL,
    description                  VARCHAR(255)          NOT NULL,
    image                        VARCHAR(255)          NOT NULL,
    price                        DOUBLE                NOT NULL,
    location                     VARCHAR(255)          NOT NULL,
    uuid                         VARCHAR(255)          NOT NULL,
    status                       BIGINT                NOT NULL,
    vendor_code                  VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_futsal PRIMARY KEY (id)
)

--changeset manjul.tamang:2
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0  SELECT COUNT(*) FROM information_schema.table_constraints WHERE constraint_schema = (SELECT DATABASE()) AND table_name = 'futsal' AND constraint_name = 'uc_type_uuid'
ALTER TABLE futsal
    ADD CONSTRAINT uc_type_uuid UNIQUE (uuid);

-- --changeset manjul.tamang:2
-- --preconditions onFail:MARK_RAN
-- --precondition-sql-check expectedResult:0  SELECT COUNT(*) FROM information_schema.table_constraints WHERE constraint_schema = (SELECT DATABASE()) AND table_name = 'futsal' AND constraint_name = 'FK_FUTSAL_ON_STATUS'
-- ALTER TABLE `futsal`
--     ADD CONSTRAINT FK_FUTSAL_ON_STATUS FOREIGN KEY (status) REFERENCES status (id);