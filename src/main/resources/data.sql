INSERT INTO venues
VALUES (default, 'Koh Pich City Hall', 'Chamkar Mon'),
       (default, 'Olympic Stadium Arena', '7 Makara'),
       (default, 'KSHRD Center', 'Boeng Kok II'),
       (default, 'Aeon Mall 3 Convention Center', 'Meanchey'),
       (default, 'Garden City Hotel', 'Chroy Changvar'),
       (default, 'Riverside Exhibition Center', 'Daun Penh'),
       (default, 'TK Avenue Plaza', 'Tuol Kork'),
       (default, 'National Museum Hall', 'Daun Penh'),
       (default, 'The Factory Phnom Penh', 'Meanchey'),
       (default, 'Sofitel Phokeethra', 'Chamkar Mon'),
       (default, 'Rosewood Meeting Suite', 'Daun Penh'),
       (default, 'Sen Sok Wedding Hall', 'Sen Sok'),
       (default, 'Royal University Hall', 'Tuol Kork'),
       (default, 'Diamond Island Convention Center', 'Chamkar Mon'),
       (default, 'Vattanac Capital Tower', 'Daun Penh'),
       (default, 'Chaktomuk Conference Hall', 'Daun Penh');

INSERT INTO events
VALUES (default, 'Monthly party', '11-04-2026', 3),
       (default, 'Tech Networking Night', '2026-04-15', 1),
       (default, 'Music Festival 2026', '2026-04-20', 2),
       (default, 'Startup Pitch Deck', '2026-04-25', 4),
       (default, 'Career Fair Expo', '2026-05-01', 12),
       (default, 'Digital Art Gallery', '2026-05-05', 8),
       (default, 'E-Sports Tournament', '2026-05-10', 2),
       (default, 'Java Developers Workshop', '2026-05-15', 13),
       (default, 'Charity Gala Dinner', '2026-05-20', 9),
       (default, 'Product Launch Event', '2026-06-01', 14),
       (default, 'Business Leaders Summit', '2026-06-05', 10),
       (default, 'Wedding Celebration', '2026-06-10', 11),
       (default, 'Photography Workshop', '2026-06-15', 5),
       (default, 'Food & Beverage Expo', '2026-06-20', 3),
       (default, 'Innovation Marathon', '2026-07-01', 15),
       (default, 'International Trade Fair', '2026-07-05', 6);

INSERT INTO attendees
VALUES (default, 'Sok kanha', 'nha.s@gmail.com'),
       (default, 'Chhay Virak', 'virak.chhay@gmail.com'),
       (default, 'Meas Sreypov', 'pov.meas@yahoo.com'),
       (default, 'Keo Samnang', 'samnang.keo@hotmail.com'),
       (default, 'Ly Davin', 'davin.ly@gmail.com'),
       (default, 'Seng Rotha', 'rotha.seng@outlook.com'),
       (default, 'Chan Thida', 'thida.chan@gmail.com'),
       (default, 'Ouk Dara', 'dara.ouk@live.com'),
       (default, 'Heng Leakhena', 'leakhena.h@gmail.com'),
       (default, 'Pech Sophea', 'sophea.pech@yahoo.com'),
       (default, 'Vannak Borin', 'borin.v@gmail.com'),
       (default, 'Lim Sreynet', 'sreynet.lim@gmail.com'),
       (default, 'Noun Kosal', 'kosal.noun@outlook.com'),
       (default, 'Ros Piseth', 'piseth.ros@gmail.com'),
       (default, 'Tith Malis', 'malis.tith@yahoo.com'),
       (default, 'Chea Sovann', 'sovann.chea@gmail.com');

INSERT INTO event_attendee
VALUES (1, 3),
       (1, 6),
       (5, 12),
       (3, 8),
       (10, 2);

SELECT * FROM attendees a
INNER JOIN event_attendee ea ON a.attendee_id = ea.attendee_id
WHERE ea.event_id = 1;

SELECT * FROM events;

DELETE FROM events WHERE event_id BETWEEN 18 AND 21;

ALTER TABLE events ADD CONSTRAINT uk_event_name_date UNIQUE (event_name, event_date);