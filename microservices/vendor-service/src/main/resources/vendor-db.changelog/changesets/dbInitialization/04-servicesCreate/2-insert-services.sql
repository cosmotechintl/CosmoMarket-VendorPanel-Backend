-- changeset samirghimire:4
-- preconditions onFail:CONTINUE onError:HALT
INSERT INTO services_details (name, description, status,version,uuid) VALUES
    ('Futsal','you can book futsal as per your need' , 1,0,uuid()),
    ('Vehicle Rental','you can vehicle as per your need' , 1,0,uuid()),
    ('Hotel Booking','you can book hotel rooms as per your need' , 1,0,uuid()),
    ('Restaurant Services','you can access restaurant services like booking table, home delivering food' , 1,0,uuid()),
    ('Shopping Mart','you can shop your bucket list items' , 1,0,uuid()),
    ('Banquet','you can book banquet for every events' , 1,0,uuid());
