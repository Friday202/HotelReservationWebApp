INSERT INTO hotels (name, location, user_rating, description, coordinates) VALUES
('Grand Plaza Hotel and Congress Centre', 'Bratislavska cesta 8, 1000 Ljubljana', 9.1, 'Grand Plaza Hotel and Congress centre Ljubljana 5* Superior is located in Ljubljana city centre, opens a beautiful view of Ljubljana castle and offers an immense comfort of accomodation. The hotel is equipped with eight congress halls with abundance of natural light that can accomodate up to 1.000 guests. At the Grand Plaza guests can experience excellent cuisine at the hotel restaurant, while at the lobby bar they can enjoy in a variety of coffee and tea selection, classic coctails, local beer and regional wines.', '46.06;14.55'),
('Hotel Alpina', 'Vitranska Ulica 12, 4280 Kranjska Gora', 8.3, 'Dobrodošli v Hotelu Alpina. Naš družinam prijazni hotel se nahaja tik ob smučišču v Kranjski Gori in hkrati v neposredni bližini mesta. za aktivna in kulturna doživetja, ki jih ponujajo okoliški kraji in ljudje.', '46.48;13.77'),
('Grand Hotel Bernardin', 'Obala 2, 6320 Portorož', 4.5, 'The prestigious Grand Hotel Bernardin is located directly by the sea, between Portoroz and Piran. It offers an excellent wellness center Paradise Spa with saunas and various massages and an indoor swimming pool with heated sea water. All rooms have private balconies overlooking the sea. The hotel has a luxurious private beach.', '45.51;13.57'),
('Resort Village Majer', 'Krasinec 1, 8332 Gradac', 5.0, 'Bela Krajina has a unique character with closely intertwined nature and culture. The area of developed countryside is spread over rugged terrain with various natural heritage sites along with vineyards, birch forests with fern and the pristine Kolpa River.', '45.58;15.27'),
('Hotel Bau', 'Limbuška cesta 85, 2000 Maribor', 3.7, 'Hotel Bau Maribor **** is located in a quiet part of Maribor, from where you can observe the city on one side and the beautiful Pohorje hills on the other. Hotel Bau Maribor **** combines the comfort of a modern city hotel with the homeliness of the Styrian countryside, which guests first taste with a rich breakfast buffet.', '46.54;15.62'),
('Hotel Zvezda', 'Trg zmage 8, 9000 Murska Sobota', 4.2, 'Prav gotovo vas je pot že kdaj zanesla v severovzhodni del Slovenije, kjer se stikata prekmurska ravnica in prelepo Goričko. Dobrodošli v Murski Soboti, v kompleksu Zvezda, ki predstavlja: restavracijo in pivnico, salon Murska Republika, pivovarno ter hotel Zvezda***. Kompleks Zvezda se nahaja v samem središču mesta Murska Sobota in je tradicionalno stičišče domačinov kakor tudi idealno izhodišče za poslovneže, izletnike ter turiste. V prijetnem ambientu restavracije in pivnice nudimo bogato ponudbo tradicionalnih prekmurskih jedi, kakor tudi dnevno ponudbo malic, kosil in ostalih jedi po naročilu. V spomladanskih in poletnih mesecih boste lahko uživali v razkošju letnega vrta, v senci stoletnih kostanjev, kjer vam bomo postregli z našo celotno gostinsko ponudbo. Poskrbeli bomo za prijetno in sproščeno vzdušje v sklopu poslovnega in prireditvenega dela kompleksa - Salon Murska Republika, po napornem delovniku ali izletu v bližnjo ter daljno okolico pa si boste lahko odpočili v sodobno opremljenih sobah v celoti prenovljenega hotela Zvezda***.', '46.65;16.16'),
('Mond, Resort & Entertainment', 'Sadjarska pot 15, 2212 Šentilj v Slov. goricah', 2.8, 'The four star superior hotel has 35 rooms, 3 superior suites and 2 deluxe suites; a wine bar and a hotel restaurant with a summer terrace; a wellness centre with saunas, 2 hot tubs, an outdoor terrace and pampering massage shower enclosures; an event hall for business and social events. All rooms are spacious with covered balconies and furnished to a high standard, air-conditioning, TV, mini bar, telephone, fast internet.', '46.66;15.66');

INSERT INTO hotel_amenities (hotel_id, amenity) VALUES
(1, 'Free Wi-Fi'),
(1, 'Private parking'),
(1, 'Fitness center'),
(1, 'Bar'),
(1, 'Airport shuttle'),
(1, 'Restaurant'),

(2, 'Spa'),
(2, 'Free parking'),
(2, 'Free Wi-Fi'),
(2, 'Restaurant'),
(2, 'Skiing'),
(2, 'Bar'),
(2, 'Fitness center'),

(3, 'Indoor swimming pool'),
(3, 'Spa'),
(3, 'Free Wi-Fi'),
(3, 'Restaurant'),
(3, 'Free parking'),
(3, 'Bar'),
(3, 'Fitness center'),
(3, 'Beachfront'),

(4, 'Free Wi-Fi'),
(4, 'Restaurant'),
(4, 'Free parking'),
(4, 'Bar'),

(5, 'Free Wi-Fi'),
(5, 'Restaurant'),
(5, 'Bar'),
(5, 'Facilities for disabled guests'),

(6, 'Free Wi-Fi'),
(6, 'Restaurant'),
(6, 'Bar'),

(7, 'Free Wi-Fi'),
(7, 'Restaurant'),
(7, 'Free parking'),
(7, 'Bar'),
(7, 'Spa and wellness'),
(7, 'Casino');
