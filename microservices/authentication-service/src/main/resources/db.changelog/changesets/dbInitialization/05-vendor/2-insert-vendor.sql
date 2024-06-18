-- liquibase formatted sql
--changeset manjul.tamang:1

--preconditions onFail:CONTINUE onError:HALT
INSERT INTO vendor (vendor_name, version,logo, address, email, mobile_number,pan_number, is_active,code,category,status)
VALUES
    ('Himalayan Technologies', 0, 'himalayan_tech.png', 'Kathmandu, Bagmati, Nepal', 'info@himalayantech.com', '9801234567', 'NP1234567890',true, 'HT001', 2, 1),
    ('Everest Handicrafts', 0, 'everest_handicrafts.jpg', 'Bhaktapur, Bagmati, Nepal', 'contact@everesthandicrafts.com', '9812345678', 'NP0987654321',true,'EH002', 2, 1),
    ('Nepal Organic Farms', 0, 'nepal_organic.png', 'Pokhara, Gandaki, Nepal', 'support@nepalorganicfarms.com', '9823456789', 'NP1122334455',true, 'NOF003', 3, 1);