-- liquibase formatted sql
--changeset amritkthapa:1

--preconditions onFail:CONTINUE onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM vendor_user where username='bigmartadmin'
INSERT INTO `vendor_user` (name, password, username, is_active, email, mobile_number,address, status_id, access_group, wrong_password_attempt_count, two_factor_enabled, wrong_oto_auth_attempt_count, is_admin,version,vendor)
VALUES
    ('Futsal Admin', '$2a$10$h/Fm04H01xFqs1iZ8LEVPeg6YfEi/uRz1cLBI9i4KgoRKL0EHctsy', 'futsaladmin', true, 'futsaladmin@gmail.com', '9843388449', 'Jorpati', 1, 1, 0, false, 0, true,0,1),
    ('Cricksal Admin', '$2a$10$h/Fm04H01xFqs1iZ8LEVPeg6YfEi/uRz1cLBI9i4KgoRKL0EHctsy', 'cricksaladmin', true, 'cricksal@gmail.com', '9843388449', 'Jorpati', 1, 1, 0, false, 0, false,0,2);
