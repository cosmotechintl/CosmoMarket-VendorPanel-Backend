-- liquibase formatted sql
--changeset suman.gajmer:1

--preconditions onFail:CONTINUE onError:HALT
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM vendor where username='superadmin'
INSERT INTO `vendor` (name, password, username, is_active, email, mobile_number, status_id, access_group, wrong_password_attempt_count, two_factor_enabled, wrong_oto_auth_attempt_count, is_super_admin,version)
VALUES
    ('BigMart', '$2a$10$h/Fm04H01xFqs1iZ8LEVPeg6YfEi/uRz1cLBI9i4KgoRKL0EHctsy', 'bigmart', true, 'bigmart@gmail.com', '9843388449', 1, 1, 0, false, 0, true,0);

