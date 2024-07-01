-- liquibase formatted sql
--changeset manjul.tamang:1

--preconditions onFail:CONTINUE onError:HALT
INSERT INTO futsal (name,description, image,price,location,status,vendor_code,version)
VALUES
    ('ABC','ABC Futsal','ABC',1000.00,'shantinagar',1,uuid(),0),
    ('XYZ','XYZ Futsal','XYZ',1200.00,'buddhanagar',1,uuid(),0);