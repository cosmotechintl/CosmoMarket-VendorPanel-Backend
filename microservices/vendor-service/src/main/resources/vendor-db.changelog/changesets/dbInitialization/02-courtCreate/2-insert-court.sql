-- changeset samirghimire:2
-- preconditions onFail:CONTINUE onError:HALT
INSERT INTO court_details (name, image, description, status,version) VALUES
    ('machapuchare', 'court1.jpeg','lengthandbreadth' , 1,0),
    ('sagarmatha', 'court2.jpeg','lengthandbreadth are larger than machapuchre' , 1,0),
    ('kanchanjunga', 'court2.jpeg','lengthandbreadth largest among all' , 1,0);
