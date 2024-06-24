-- liquibase formatted sql
--changeset suman.gajmer:1

--preconditions onFail:CONTINUE onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM roles
INSERT INTO roles (description, icon, name, navigation, parent_name, permission, position, ui_group_name, version)
VALUES ('Root', '', 'Root', 'NONE', 'ROOT', 'NONE', 0, 'NONE',0),
       ('vendorUsers','FiUsers', 'Users', '/vendorUser', 'Root', 'VENDOR_USER', 1, 'NONE',0),
       ('Create VendorUser', 'FiUsers', 'Create User', 'create', 'Users', 'CREATE_VENDOR_USER', 1, 'VendorUsers',0),
       ('View VendorUser', 'FiUsers', 'View User', 'view', 'Users', 'VIEW_VENDOR_USER', 2, 'VendorUsers',0),
       ('Modify VendorUser', 'FiUsers', 'Modify User', 'edit', 'Users', 'MODIFY_VENDOR_USER', 3, 'VendorUsers',0),
       ('Delete VendorUser', 'FiUsers', 'Delete User', 'delete', 'Users', 'DELETE_VENDOR_USER', 4, 'VendorUsers',0),
       ('Block VendorUser', 'FiUsers', 'Block User', 'block', 'Users', 'BLOCK_VENDOR_USER', 5, 'VendorUsers',0),
       ('UnBlock VendorUser', 'FiUsers', 'UnBlock User', 'unblock', 'Users', 'UNBLOCK_VENDOR_USER', 6, 'VendorUsers',0),
       ('Business Hour','LiaBusinessTimeSolid', 'Business Hours', '/businessHour', 'Root', 'BUSINESS_HOUR', 2, 'NONE',0),
       ('Create Business Hour', 'LiaBusinessTimeSolid', 'Create Business Hours', 'create', 'Business Hours', 'CREATE_BUSINESS_HOUR', 1, 'Business Hours',0),
       ('View VendorUser', 'LiaBusinessTimeSolid', 'View Business Hours', 'view', 'Business Hours', 'VIEW_BUSINESS_HOUR', 2, 'Business Hours',0),
       ('Settings','CiSettings', 'Settings', '/setting', 'Root', 'SETTINGS', 3, 'NONE',0);
--changeset suman.gajmer:2
UPDATE roles AS r
    INNER JOIN roles AS parent ON r.parent_name = parent.name
    SET r.parent_role = parent.id;


