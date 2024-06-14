-- liquibase formatted sql
--changeset suman.gajmer:1

--preconditions onFail:CONTINUE onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM roles
INSERT INTO roles (description, icon, name, navigation, parent_name, permission, position, ui_group_name, version)
VALUES ('Root', '', 'Root', 'NONE', 'ROOT', 'NONE', 0, 'NONE',0),
       ('Create VendorUser', '', 'Create VendorUser', 'create', 'VendorUsers', 'CREATE_VENDOR_USER', 1, 'VendorUsers',0),
       ('View VendorUser', '', 'View VendorUser', 'view', 'VendorUsers', 'VIEW_VENDOR_USER', 2, 'VendorUsers',0),
       ('Modify VendorUser', '', 'Modify VendorUser', 'edit', 'VendorUsers', 'MODIFY_VENDOR_USER', 3, 'VendorUsers',0),
       ('Delete VendorUser', '', 'Delete VendorUser', 'delete', 'VendorUsers', 'DELETE_VENDOR_USER', 4, 'VendorUsers',0),
       ('Block VendorUser', '', 'Block VendorUser', 'block', 'VendorUsers', 'BLOCK_VENDOR_USER', 5, 'VendorUsers',0),
       ('UnBlock VendorUser', '', 'UnBlock VendorUser', 'unblock', 'VendorUsers', 'UNBLOCK_VENDOR_USER', 6, 'VendorUsers',0);


--changeset suman.gajmer:2
UPDATE roles AS r
    INNER JOIN roles AS parent ON r.parent_name = parent.name
    SET r.parent_role = parent.id;


