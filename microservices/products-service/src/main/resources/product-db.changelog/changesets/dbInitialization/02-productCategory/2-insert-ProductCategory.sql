-- liquibase formatted sql

-- changeset samir.ghimire:2
-- preconditions onFail:CONTINUE onError:HALT
INSERT INTO product_category (name,version,description, code,status) VALUES
    ('clothes',0,'all the clothing product' , uuid(),1),
    ('fruits',0,'all the fruits product' , uuid(),1),
    ('cosmetic',0,'all the cosmetic product' , uuid(),1);
