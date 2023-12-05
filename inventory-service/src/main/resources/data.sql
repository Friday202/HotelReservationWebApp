INSERT INTO t_hotels (id) VALUES (1), (2), (3), (4);

INSERT INTO t_rooms (room_number, hotel_id) VALUES (1, 1), (2, 1), (1,2), (2,2);

INSERT INTO t_taken_dates (hotel_id, room_id, start_date, end_date) VALUES (1,1,'2.2.2002', '3.2.2002');