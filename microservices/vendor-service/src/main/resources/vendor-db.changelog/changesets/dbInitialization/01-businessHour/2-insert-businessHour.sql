-- liquibase formatted sql

-- changeset amritkthapa:3
-- preconditions onFail:CONTINUE onError:HALT
INSERT INTO business_hours (vendor, day, start_time, end_time, closed,version) VALUES
    (1, 6, '06:00:00', '20:00:00', FALSE,0),
    (1, 0, '06:00:00', '20:00:00', FALSE,0),
    (1, 1, '06:00:00', '20:00:00', FALSE,0),
    (1, 2, '06:00:00', '20:00:00', FALSE,0),
    (1, 3, '06:00:00', '20:00:00', FALSE,0),
    (1, 4, '06:00:00', '20:00:00', FALSE,0),
    (1, 5, '06:00:00', '20:00:00', FALSE,0);