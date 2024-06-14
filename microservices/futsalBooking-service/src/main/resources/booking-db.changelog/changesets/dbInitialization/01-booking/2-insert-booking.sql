-- liquibase formatted sql
--changeset amritkthapa:1

--preconditions onFail:CONTINUE onError:HALT
INSERT INTO booking (customerName, mobileNumber, start_time, end_time)
VALUES ('[Customer Name]', '[Mobile Number]', '06:00:00', '07:00:00');
