-- liquibase formatted sql

-- changeset amritkthapa:3
-- preconditions onFail:CONTINUE onError:HALT
INSERT INTO business_hours (vendor, day, start_time, end_time, closed,version) VALUES
    ("bf8ab347-3912-11ef-80bc-005056c00001", 6, '06:00:00', '20:00:00', FALSE,0),
    ("bf8ab347-3912-11ef-80bc-005056c00001", 0, '06:00:00', '20:00:00', FALSE,0),
    ("bf8ab347-3912-11ef-80bc-005056c00001", 1, '06:00:00', '20:00:00', FALSE,0),
    ("bf8ab347-3912-11ef-80bc-005056c00001", 2, '06:00:00', '20:00:00', FALSE,0),
    ("bf8ab347-3912-11ef-80bc-005056c00001", 3, '06:00:00', '20:00:00', FALSE,0),
    ("bf8ab347-3912-11ef-80bc-005056c00001", 4, '06:00:00', '20:00:00', FALSE,0),
    ("bf8ab347-3912-11ef-80bc-005056c00001", 5, '06:00:00', '20:00:00', FALSE,0);