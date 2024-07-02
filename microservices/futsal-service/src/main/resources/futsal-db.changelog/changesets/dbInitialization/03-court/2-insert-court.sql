-- liquibase formatted sql
-- changeset amritkthapa:1
-- preconditions onFail:CONTINUE onError:HALT
INSERT INTO court_details (name, image, description, status,vendor_code, version) VALUES
    ('machapuchare', 'court1.jpeg','lengthandbreadth' , 1,'21c7ed6f-36a5-11ef-80bc-005056c00001',0),
    ('sagarmatha', 'court2.jpeg','lengthandbreadth are larger than machapuchre' , 1,'21c7ed6f-36a5-11ef-80bc-005056c00001',0),
    ('kanchanjunga', 'court2.jpeg','lengthandbreadth largest among all' , 1,'21c7ed6f-36a5-11ef-80bc-005056c00001',0);
