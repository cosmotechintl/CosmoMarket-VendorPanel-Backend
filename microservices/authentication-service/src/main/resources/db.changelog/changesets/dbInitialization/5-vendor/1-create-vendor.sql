-- liquibase formatted sql
--changeset suman.gajmer:1
--preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS `vendor`
(
    id                           BIGINT AUTO_INCREMENT NOT NULL,
    version                      BIGINT                NOT NULL,
    name                         VARCHAR(255)          NOT NULL,
    password                     VARCHAR(255)          NULL,
    username                     VARCHAR(255)          NOT NULL,
    is_active                    BIT(1)                NULL,
    email                        VARCHAR(255)          NOT NULL,
    mobile_number                VARCHAR(255)          NULL,
    address                      VARCHAR(255)          NULL,
    status_id                    BIGINT                NOT NULL,
    access_group                 BIGINT                NOT NULL,
    password_changed_date        datetime              NULL,
    last_logged_in_time          datetime              NULL,
    wrong_password_attempt_count INT                   NULL,
    profile_picture_name         VARCHAR(255)          NULL,
    otp_auth_secret              VARCHAR(255)          NULL,
    two_factor_enabled           BIT(1)                NOT NULL,
    wrong_oto_auth_attempt_count INT                   NULL,
    is_super_admin               BIT(1)                NULL,
    CONSTRAINT pk_vendor PRIMARY KEY (id)
    );


--changeset suman.gajmer:2
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0  SELECT COUNT(*) FROM information_schema.table_constraints WHERE constraint_schema = (SELECT DATABASE()) AND table_name = 'vendor' AND constraint_name = 'FK_vendor_ON_ACCESS_GROUP'
ALTER TABLE `vendor`
    ADD CONSTRAINT FK_vendor_ON_ACCESS_GROUP FOREIGN KEY (access_group) REFERENCES access_group (id);



