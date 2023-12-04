INSERT INTO hotels (name, location, user_rating, description, coordinates) VALUES
('Hotel A', 'Location A', 4.5, 'Some description', 'Coords'),
('Hotel B', 'Location B', 3.8, 'Some description', 'Coords'),
('Hotel C', 'Location C', 4.2, 'Some description', 'Coords');

INSERT INTO hotel_amenities (hotel_id, amenity) VALUES
(1, 'Free Wi-Fi'),
(2, 'Parking'),
(3, 'Swimming Pool'),
(2, 'Gym'),
(1, 'Restaurant');
