-- liquibase formatted sql
--changeset manjul.tamang:1
--preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS `product`
(
    id                           BIGINT AUTO_INCREMENT      NOT NULL,
    version                      BIGINT                     NOT NULL,
    name                         VARCHAR(255)               NOT NULL,
    description                   VARCHAR(255)               NOT NULL,
    price                        DOUBLE                    NULL,
    brand                        VARCHAR(255)               NOT NULL,
    color                        VARCHAR(255)               NOT NULL,
    image                        VARCHAR(255)               NOT NULL,
    size                         VARCHAR(255)               NOT NULL,
    code                         VARCHAR(255)               NOT NULL,
    vendor_code                  VARCHAR(255)               NOT NULL,
    in_stock                     BIT(1)                     NULL,
    quantity                     INT                        NOT NULL,
    product_category             BIGINT                     NOT NULL,
    status                       BIGINT                     NOT NULL,
    created_at                   datetime                   NULL,
    updated_at                   datetime                   NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
    );

--changeset manjul.tamang:2
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0  SELECT COUNT(*) FROM information_schema.table_constraints WHERE constraint_schema = (SELECT DATABASE()) AND table_name = 'product' AND constraint_name = 'uc_product_code'
ALTER TABLE product
    ADD CONSTRAINT uc_product_code UNIQUE (code);

--changeset manjul.tamang:3
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0  SELECT COUNT(*) FROM information_schema.table_constraints WHERE constraint_schema = (SELECT DATABASE()) AND table_name = 'product' AND constraint_name = 'FK_PRODUCT_ON_CATEGORY'
ALTER TABLE `product`
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (product_category) REFERENCES product_category (id);

--changeset manjul.tamang:4
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0  SELECT COUNT(*) FROM information_schema.table_constraints WHERE constraint_schema = (SELECT DATABASE()) AND table_name = 'product' AND constraint_name = 'FK_PRODUCT_ON_STATUS'
ALTER TABLE `product`
    ADD CONSTRAINT FK_PRODUCT_ON_STATUS FOREIGN KEY (status) REFERENCES status (id);