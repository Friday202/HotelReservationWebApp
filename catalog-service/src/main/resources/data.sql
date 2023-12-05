INSERT INTO hotels (name, location, user_rating, description, coordinates) VALUES
('Grand Plaza Hotel and Congress Centre', 'Bratislavska cesta 8, 1000 Ljubljana', 5.0, 'Description ', 'Coords'),
('Hotel B', 'Location B', 3.8, 'Some description', 'Coords'),
('Hotel C', 'Location C', 4.2, 'Some description', 'Coords');

INSERT INTO hotel_amenities (hotel_id, amenity) VALUES
(1, 'Free Wi-Fi'),
(2, 'Parking'),
(3, 'Swimming Pool'),
(2, 'Gym'),
(1, 'Restaurant');
