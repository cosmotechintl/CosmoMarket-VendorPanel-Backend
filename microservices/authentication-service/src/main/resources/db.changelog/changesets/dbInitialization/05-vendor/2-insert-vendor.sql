-- liquibase formatted sql
--changeset amritkthapa:1

--preconditions onFail:CONTINUE onError:HALT
INSERT INTO vendor (vendor_name, version,category, logo, address, email, phone_number)
VALUES ('Shiroe futsal',0,'Sports', 'logo1.png', 'boudha', 'shiroesal1@example.com', '014470159'),
        ('Amrit Cricksal',0,'Sports', 'logo1.png', 'boudha','cricksal@mail.com', '014470159');
