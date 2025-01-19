INSERT INTO Customer VALUES (5234567890, '1, 4', 'John', 'Doe', '123 Main St');
INSERT INTO Customer VALUES (3345678901, '2', 'Jane', 'Smith','456 Elm St');
INSERT INTO Customer VALUES (2456789012, '3, 5', 'Lou', 'Willson', '789 Oak St');
INSERT INTO Customer VALUES (4567890123, NULL, 'Bob', 'Robert', '321 Pine St');
INSERT INTO Customer VALUES (1678901234, NULL, 'Emily', 'Axford', '654 Maple St');

INSERT INTO Fee VALUES ('Late', 5.00);
INSERT INTO Fee VALUES ('Dmg1', 2.00);
INSERT INTO Fee VALUES ('Lost', 15.00);
INSERT INTO Fee VALUES ('Rwnd', 2.00);
INSERT INTO Fee VALUES ('Dmg2', 10.00);

INSERT INTO Invoices VALUES (001, 5234567890, 50.00, 5.00, '2023-01-10', 'Late', '2023-01-15', 0, 0, 1);
INSERT INTO Invoices VALUES (002, 3345678901, 60.00, 6.00, '2023-02-20', 'Lost', '2023-02-25', 0, 1, 1);
INSERT INTO Invoices VALUES (003, 2456789012, 70.00, 7.00, '2023-03-20', 'Rwnd', '2023-03-25', 1, 0, 0);
INSERT INTO Invoices VALUES (004, 5234567890, 80.00, 8.00, '2023-04-20', 'Dmg1', '2023-04-25', 0, 1, 0);
INSERT INTO Invoices VALUES (005, 2456789012, 90.00, 9.00, '2023-05-20', 'Dmg2', '2023-05-25', 0, 1, 0);

INSERT INTO Distributor VALUES ('disc', 'Discovery', '5%');
INSERT INTO Distributor VALUES ('hlmk', 'Hallmark', '8%');
INSERT INTO Distributor VALUES ('21st', '21st Century', '9%');
INSERT INTO Distributor VALUES ('disn', 'Disney', '10%');
INSERT INTO Distributor VALUES ('ntfx', 'NetFlix', '4%');

INSERT INTO Award VALUES ('BVE15', 'Oppenheimer');
INSERT INTO Award VALUES ('BP95', NULL);
INSERT INTO Award VALUES ('BD19', 'Joss Whedon');
INSERT INTO Award VALUES ('BA22', 'Brennan Lee Mul');
INSERT INTO Award VALUES ('BOSP', 'Knives Out');

INSERT INTO Sale VALUES ('July4', 'Action', 0.10);
INSERT INTO Sale VALUES ('mngr', NULL, 0.25);
INSERT INTO Sale VALUES ('Summer', NULL, 0.15);
INSERT INTO Sale VALUES ('FRI13', 'Horror', 0.25);
INSERT INTO Sale VALUES ('empl', NULL, 0.10);

INSERT INTO Catalog VALUES ('Dph4', 'disn', 'The Avengers, Avengers: AoU, Avengers: IW, etc...');
INSERT INTO Catalog VALUES ('2109', '21st', 'The Hangover, etc...');
INSERT INTO Catalog VALUES ('2195', '21st', 'The Shawshank Redemption, etc...');
INSERT INTO Catalog VALUES ('ntfx', 'ntfx', 'The Shining, etc...');
INSERT INTO Catalog VALUES ('2114', '21st', 'Interstellar, etc...');

INSERT INTO Movie VALUES ('avng01', 'The Avengers', 'Dph4', 'July4', 'Action',
'Robert Downey Jr., Chris Evans, Scarlett Johansson', 143, 8, '2012-05-04', 'Joss Whedon', NULL);
INSERT INTO Movie VALUES ('tho01', 'The Hangover', '2109', 'Summer', 'Comedy',
'Zach Galifianakis, Bradley Cooper, Justin Bartha', 100, 7, '2009-06-05', 'Todd Phillips', NULL);
INSERT INTO Movie VALUES ('ssr01', 'The Shawshank Redemption', '2195', NULL, 'Drama',
'Tim Robbins, Morgan Freeman, Bob Gunton', 142, 9, '1994-10-14', 'Frank Darabont', NULL);
INSERT INTO Movie VALUES ('shng01', 'The Shining', 'ntfx', 'FRI13', 'Horror',
'Jack Nicholson, Shelley Duvall, Danny Lloyd', 146, 8, '1980-05-23', 'Stanley Kubrick', NULL);
INSERT INTO Movie VALUES ('instl01', 'Interstellar', '2114', 'empl', 'Sci-Fi',
'Matthew McConaughey, Anne Hathaway, Jessica Chastain', 169, 8, '2014-11-07', 'Christopher Nolan', 'BVE15');

INSERT INTO Item VALUES ('av1d', 'disn', 'avng01', 10.00, 'DVD');
INSERT INTO Item VALUES ('sr1v', '21st', 'ssr01', 15.00, 'VHS');
INSERT INTO Item VALUES ('ho1d', '21st', 'tho01', 20.00, 'DVD');
INSERT INTO Item VALUES ('sh1v', '21st', 'shng01', 25.00, 'VHS');
INSERT INTO Item VALUES ('av2d', 'disn', 'avng01', 30.00, 'DVD');

INSERT INTO Purchase_Order VALUES (202356, 'disn', 300.00, 'avng01', 15);
INSERT INTO Purchase_Order VALUES (202357, '21st', 60.00, 'tho01', 3);
INSERT INTO Purchase_Order VALUES (202358, 'disn', 100.00, 'avng01', 5);
INSERT INTO Purchase_Order VALUES (202359, 'ntfx', 60.00, 'shng01', 4);
INSERT INTO Purchase_Order VALUES (202360, 'disn', 40.00, 'avng01', 2);
