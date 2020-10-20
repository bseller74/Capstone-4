BEGIN TRANSACTION;

DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS brewery_favorites;
DROP TABLE IF EXISTS reply_review;
DROP TABLE IF EXISTS brewery_news;
DROP TABLE IF EXISTS pending_brewery_request;
DROP TABLE IF EXISTS brewer_request;
Drop Table if exists Review;
Drop Table if exists beers;
Drop Table if exists breweries;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS brewery_news;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('brewerBob', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_BREWER');
INSERT INTO users (username,password_hash,role) VALUES ('test', '$2a$10$UAjEa4eYIsQj7TF9MJpqz.MZptrdKkXm5bO9Trv4oe/bSqaA4WU16', 'ROLE_ADMIN');
INSERT INTO users (user_id, username, password_hash, role) VALUES (100, 'User2', '$2a$10$UAjEa4eYIsQj7TF9MJpqz.MZptrdKkXm5bO9Trv4oe/bSqaA4WU16', 'ROLE_USER');
INSERT INTO users (user_id, username, password_hash, role) VALUES (101, 'User3', '$2a$10$UAjEa4eYIsQj7TF9MJpqz.MZptrdKkXm5bO9Trv4oe/bSqaA4WU16', 'ROLE_USER');
INSERT INTO users (user_id, username, password_hash, role) VALUES (102, 'NewBrewer', '$2a$10$UAjEa4eYIsQj7TF9MJpqz.MZptrdKkXm5bO9Trv4oe/bSqaA4WU16', 'ROLE_BREWER');
INSERT INTO users (user_id, username, password_hash, role) VALUES (103, 'NewBrewer2', '$2a$10$UAjEa4eYIsQj7TF9MJpqz.MZptrdKkXm5bO9Trv4oe/bSqaA4WU16', 'ROLE_BREWER');

CREATE Table Breweries (

id serial, 
name varchar,
address varchar,
description varchar,
image varchar,
brewer_id int,
brewery_url varchar,
phone bigint,
hours varchar,
active boolean,

constraint pk_Breweries primary key (id),
constraint fk_Breweries_Brewer foreign key (brewer_id) references users (user_id)
);

INSERT INTO Breweries (name, address, description, image, brewer_id, brewery_url, phone, hours, active) VALUES 
('Denver Beer Company', '1695 Platte St, Denver, CO 80202', 'Brewery-based taproom with an industrial vibe, dog-friendly outdoor beer garden & food trucks', 'https://denverbeerco.com/wp-content/uploads/2014/07/logo_final3.png', 3,
'https://denverbeerco.com/', 3034332739, '11am to 11pm', true);
INSERT INTO Breweries (name, address, description, image, brewer_id, brewery_url, phone, hours, active) VALUES 
('TRVE Brewing Company', '227 Broadway, Denver, CO 80203', 'Small local brewery & taproom featuring eclectic housemade beers in heavy metal–inspired environs.', 'https://pbs.twimg.com/profile_images/707978949037436929/MItXQjYg.jpg', 101, 'https://www.trvebrewing.com/ontap/', '3033511021', '3pm to 11pm', true);


CREATE TABLE Beers
(
beer_id serial,
beer_name varchar(64) not null,
brewery_id int not null,
beer_type varchar(64) not null,
description varchar(500) not null,
picture varchar,
abv decimal,
ibu decimal,
rating decimal,
available boolean,

constraint pk_Beers primary key (beer_id),
constraint fk_Beers_Breweries foreign key (brewery_id) references Breweries (id)

);

INSERT INTO Beers (beer_name, brewery_id, beer_type, description, picture, abv, ibu, available) VALUES ('Juicy Freak Juicy IPA', 1, 'IPA', 'Juicy Freak carries a bright citrus punch and smooth tropical hop bliss. Exploding with rich American hop notes of orange, mango, pear, and stone fruit, this tropical journey is smooth and creamy. Light on bitterness, big on juicy citrus, for living your best life.', 'https://denverbeerco.com/wp-content/uploads/2014/07/jf1.jpg',
6.5, null, true);
INSERT INTO Beers (beer_name, brewery_id, beer_type, description, picture, abv, ibu, rating, available) VALUES ('COSMIC CRYPT', 2, 'Pale Ale', 'Farmhouse Pale Ale fermented with Belgian Farmhouse yeast and dry hopped with Mosaic and Mandarina Bavaria hop', 'https://untappd.akamaized.net/photos/2020_02_15/e86cf7227ce4d64a9dfc6f8cc3c323f0_640x640.jpeg',
5.6, null, 4, true);


INSERT INTO breweries (name, address, description, image, brewery_url, phone, hours, active) 
VALUES ('Great Divide Brewing Co', '2201 Arapahoe St, Denver, CO 80205', 'Intimate, lively joint offering tastes of their own brews, growlers to go & food truck options.', 'https://scontent-ort2-1.xx.fbcdn.net/v/t31.0-8/10448544_10152289140259607_4574139355597030316_o.jpg?_nc_cat=107&_nc_sid=09cbfe&_nc_ohc=GKtPNs7W3J8AX_vclnS&_nc_ht=scontent-ort2-1.xx&oh=cfcb8070e20f81d455ed55c42232ff96&oe=5FA18D89', 'https://greatdivide.com/', 3032969460, '3 PM - 9PM', true);
INSERT INTO breweries (name, address, description, image, brewery_url, phone, hours, active) 
VALUES ('Bruz Off Fax', '1495 York St, Denver, CO 80206', 'Opened in 2019, Bruz Off Fax is located in the heart of the Colfax Avenue corridor, giving Bruz a really fun city environment Located at the southwest corner of Colfax and York, the tasting room is just blocks from some of Denver’s best attractions like the Botanic Gardens, City Park, Fillmore Auditorium, among others. We are proud to be a part of this vibrant community.', 'https://www.coloradobrewerylist.com/wp-content/uploads/2015/06/bruz-beers.jpg', 'https://www.bruzbeers.com/', 3039975144, '2 PM - 9 PM', true);
INSERT INTO breweries (name, address, description, image, brewery_url, phone, hours, active) 
VALUES ('Jagged Mountain Craft Brewery', '1139 20th St, Denver, CO 80202', 'Hopping, no-nonsense brewpub offering an array of housemade craft beers in an industrial space.', 'data:image/jpeg;base64,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', 'http://www.jaggedmountainbrewery.com/', 7206892337, '2 PM - 9PM', true);
INSERT INTO breweries (name, address, description, image, brewer_id, brewery_url, phone, hours, active) 
VALUES ('The Grateful Gnome', '4369 Stuart Street, Denver, CO 80212', 'The Grateful Gnome Sandwich Shoppe & Brewery is a casual, neighborhood brewpub like no other… a brewpub where an authentic NJ/NY Italian-style delicatessen is combined with a progressive American Microbrewery. ', 'https://brewerydb-images.s3.amazonaws.com/brewery/eZyIef/upload_XIYDso-squareLarge.png', 103,
'https://www.thegratefulgnome.com/', 7205986863, '11am to 9pm', true);


INSERT INTO beers (beer_name, brewery_id, beer_type, description, abv, ibu, available) 
VALUES ('Four Pass Pilsner', 5, 'German Pilsner', 'A classic German lager.  Dry, crisp and clean with a touch of beady malt and a balanced bitterness from German grown Noble Hallertau hops. ', 5.0, 20, true);
INSERT INTO beers (beer_name, brewery_id, beer_type, description, abv, ibu, available) 
VALUES ('Dinger Claw', 5, 'Wild Berry and Ube Hard Seltzer', 'Hard Seltzer with blueberry, blackberry, raspberry, strawberry and ube (purple sweet potato).', 5.0, 0, true);
INSERT INTO beers (beer_name, brewery_id, beer_type, description, abv, rating, available) 
VALUES ('COLD', 2, 'Kellerpils ', 'It is cold?', 4.9, 5, true);
INSERT INTO beers (beer_name, brewery_id, beer_type, description, abv, rating, available) 
VALUES ('Pumpkin Spice Yeti', 3, 'Stout', 'When the air gets crisper and the foliage turns brilliant shades of red and orange, the Yeti yens for a libation that captures the quintessential flavors of fall. Rich and roasty with added layers of decadent sweetness, PUMPKIN SPICE YETI is brewed with seasonal spices and a unique selection of coffee from our friends at Pablo’s Coffee. Pairs perfectly with hayrides, pumpkin carving and leaf peeping.', 9.5, 5, true);
INSERT INTO beers (beer_name, brewery_id, beer_type, description, abv, ibu, available) 
VALUES ('Strawberry Rhubarb', 3, 'Sour', 'Strawberry Rhubarb Sour is a gift for the senses. Strawberry is the yin to rhubarb’s yang—the sweet to the tart. This beautiful partnership creates a beer that glows ruby in the glass, bursts with strawberry aroma, and bites with every sip. Bright and effervescent, this sour is a fun and refreshing, lip-puckering treat perfect for every occasion, any time of the year.', 6.4, 12, true);
INSERT INTO beers (beer_name, brewery_id, beer_type, description, picture, abv, available) 
VALUES ('Peanut Butter Graham Cracker Porter', 1, 'Porter', 'Malt flavors of biscuit, caramel, and chocolate are combined with real peanut butter and graham crackers in this fun and flavorful version of a traditional brown porter. The result is a bit like a smore plus a peanut butter cup in beer form. You are welcome.', 'https://denverbeerco.com/wp-content/uploads/2014/07/PeanutButterGrahamCracker_01-600x600.jpg', 5.8, true);
INSERT INTO beers (beer_name, brewery_id, beer_type, description, abv, available) 
VALUES ('Gnomeboy', 4, 'Belgian-style Golden Specialty Ale', 'Gnomeboy is specialty ale with roots in the Ardennes Mountains of Belgium. It is a spicy, peppery beer with a touch of sweetness, fruit notes and a dry finish. Gnomeboy is clean, complex and flavorful.', 8.2, true);
INSERT INTO beers (beer_name, brewery_id, beer_type, description, abv, available) 
VALUES ('Serenite Grand Cru', 4, 'Belgian-style Quadrupel', 'Silver Medal Winner GABF 2019 - Deep amber in color, a nose of malt, fruit and a touch of smoke. Taste notes of brown sugar, plums, grapes, cherries, raisins, coriander and chocolate. Medium-full bodied with a long, dry fruity finish.', 13.2, true);


INSERT INTO Beers (beer_name, brewery_id, beer_type, description, picture, abv, ibu, rating, available) VALUES ('Tart Delight Citrus Sour', 1, 'Sour', 'This one is so. . .tartsy. Whatcha tryna do, be a fancy margarita or something? Fermented with lime juice and a touch of pink himalayan sea salt for a thirst-quenching tart tickle, our lime sour was brewed with wheat and munich malts for a clean and crisp finish. Easy drinking, refreshingly tart, it’s a delight!', 'https://denverbeerco.com/wp-content/uploads/2014/07/dbc-vhh-6.jpg',
5.0, null, 4, true);
INSERT INTO Beers (beer_name, brewery_id, beer_type, description, picture, abv, ibu, available) VALUES ('Princess Yum Yum', 1, 'Raspberry Kolsch', 'We take a traditional Kolsch and ferment it with real raspberries. The result is a ruby red fruity summer ale that finishes tart and dry, perfectly thirst quenching on a hot day.', 'https://denverbeerco.com/wp-content/uploads/2014/07/drinkpink-2.jpg',
4.8, null, true);
INSERT INTO Beers (beer_name, brewery_id, beer_type, description, picture, abv, ibu, rating, available) VALUES ('Incredible Pedal IPA ', 1, 'IPA', 'This American IPA greets you with lush floral, citrus, and tropical fruit on the nose. A medium bodied ale with a touch of sweetness, she will gear you up for a finish full of tangerine and grapefruit. Incredible Pedal is a hoppy beauty that takes you for a ride!', 'https://denverbeerco.com/wp-content/uploads/2017/07/IMG_4440.jpg',
7.0, null, 4, true);
INSERT INTO Beers (beer_name, brewery_id, beer_type, description, picture, abv, ibu, available) VALUES ('Pretzel Assassin', 1, 'Amber Lager', 'This classic Vienna Lager is crisp and easy drinking. Munich and caramel malts are used to create a malty flavor with a touch of breadiness and a light sweetness. German noble hops round out the flavor profile of this clean, authentic amber lager. No pretzels were harmed in the making of this beer, but we can’t vouch for pretzel safety while you imbibe!', 'https://denverbeerco.com/wp-content/uploads/2014/07/pretzel-assassin-1.jpg',
5.0, null, true);
INSERT INTO Beers (beer_name, brewery_id, beer_type, description, picture, abv, ibu, available) VALUES ('CURSED', 2, 'Pale Ale', 'Mixed Culture Sour Pale Ale Hopped with Falconers Flight', 'https://cdn.powered-by-nitrosell.com/product_images/23/5662/large-trvecursed.jpg',
5.6, null, true);
INSERT INTO Beers (beer_name, brewery_id, beer_type, description, picture, abv, ibu, available) VALUES ('STARVING GODS OF OLD', 2, 'Sour', 'Mixed Culture Sour Aged on Colorado Peaches', 'https://untappd.akamaized.net/photos/2020_07_04/3d84b58d2614c1ca8cf27a09eac9a0ab_640x640.jpg',
5.4, null, true);
INSERT INTO Beers (beer_name, brewery_id, beer_type, description, picture, abv, ibu, available) VALUES ('EXHUMATION', 2, 'Pale Ale', 'East Coast Pale Ale with Citra and Mosaic', 'https://untappd.akamaized.net/photos/2020_09_19/4890682bceb049e05b12e76449b77be4_640x640.jpg',
4.5, null, false);
INSERT INTO Beers (beer_name, brewery_id, beer_type, description, picture, abv, ibu, available) VALUES ('Giggity IPA', 6, 'IPA', 'West Coast style IPA featuring heavy doses of Amarillo, Centennial, and Chinook hops.','https://untappd.akamaized.net/photo/2020_09_04/a94910178a8effc1e11a67a81cd25e4b_c_937884059_640x640.jpeg',
6.8, null, true);
INSERT INTO Beers (beer_name, brewery_id, beer_type, description, picture, abv, ibu, available) VALUES ('Onyx', 4, 'Belgian-style Stout', 'Bruz Onyx Stout is a big flavorful Belgian-style Strong Stout. Seven malts give it a roasty, grainy aroma and flavor profile, with distinct coffee and dark chocolate notes. Flavors of dark fall fruits, raisons and dates contribute to Onyxs complex and dry finish.','https://untappd.akamaized.net/photos/2020_07_26/4e5e654b0bbaa74001233d8152434e73_640x640.jpg',
7.9, null, true);
INSERT INTO Beers (beer_name, brewery_id, beer_type, description, picture, abv, ibu, available) VALUES ('Tropical Staycation', 5, 'Passion Fruit Guava Sour', 'Kettle soured summer crusher with passion fruit and guava.', 'https://untappd.akamaized.net/photos/2020_10_04/2f68c591d8dc6e068b97142568702bbe_raw.jpg', 4.2, 10, true);
INSERT INTO Beers (beer_name, brewery_id, beer_type, description, picture, abv, ibu, available) VALUES ('Gnome English Mild', 6, 'Mild', 'Dark English style mild with notes of burnt caramel, biscuit malt, and warm apple pie.' ,'https://untappd.akamaized.net/photos/2019_07_30/366db23ec29b16466d8c62e4b41aaba3_raw.jpg',
4.6, null, true);
INSERT INTO Beers (beer_name, brewery_id, beer_type, description, picture, abv, ibu, available) VALUES ('Big Red Rock', 5, 'Red Lager', 'Lagered Red Cream Ale showcasing Red X malt. Slightly bready with a creamy body and crisp finish.', 'https://untappd.akamaized.net/photos/2020_09_01/279b37b3903f660a5fc59af99c0e0715_640x640.jpg', 5.0, 30, true);
INSERT INTO Beers (beer_name, brewery_id, beer_type, description, picture, abv, ibu, available) VALUES ('Bases Loaded', 5, 'Kolsch', 'Pilsner malts give way to a clean, slightly fruity, lightly hoppy profile. Crisp, clean and deliciously refreshing.', 'https://untappd.akamaized.net/photos/2020_10_06/635c21fb87afcbb1a9ce5800946c6e73_raw.jpg', 5.0, 15, true);
INSERT INTO Beers (beer_name, brewery_id, beer_type, description, picture, abv, ibu, available) VALUES ('Diablo Dorado', 4, 'Belgian-style Golden Strong Ale', 'Diablo Dorado (Golden Devil) is a traditional Belgian-style Golden Strong ale that is a pale blond beer with a dense white head. Pale malt, apple, pear, ripe peach and honey notes come together in this crisp, clean beer that finishes dry and slightly bitter with lingering fruit.','https://www.facebook.com/bruzbeers/photos/a.1696328073984615/1782186765398745/?type=3&eid=ARD_QPt1H_bDtZ0TGR4wz42q_hDl74KVjbbkBIK2gOcSqMXozq_vBkkjIHklg9lj4rpLaTVHNWajNgOG&__xts__%5B0%5D=68.ARBbXiK6g5b8D275SdpxQ8oj7cuh-XUrfjGImLvkB6kcDo7cw-x7DKA1ARhjsDEfY7lfaqV7yyrvPfNkrHQMUo4ibF325RrcJQ7ziwYmLn9o6nvL-Zef63-m980RmLP4XRUV-cjaOz70nUPSXNuBgBVgh_P9bKxW0rdOpbkKkyMbcIviLsjPkE7rbg0NozSb5UcH9DMtiMY95HvD5rmpfOC8Tctr9piDOPT5n3KOI2150qZe_HVrelC-lFpyBzi2ppj8gjavA7G9S1gJa-DyLHWLThlsaqik1WN32Wm85lYAEyJ5tb_JWSKd3RvBqxT3D6YBPzKmqLBV7H1AMNe_2uOqqMWZ4KysV-HNb3x5czDR8AZTVXvbff1dzdZqD21keWvY2IPLJoZ6IxT2XQvrxSP486HBPU2Fvuj_8jEa0v48S7nx9gKf&__tn__=EEHH-R',
8.7, null, true);
INSERT INTO Beers (beer_name, brewery_id, beer_type, description, picture, abv, ibu, available) VALUES ('Hellraiser', 4, 'Belgian-style Golden Strong Ale', 'Hellraiser is a powerhouse of a beer. It is a Belgian-style Golden Strong Ale amped up in strength and vibrant aromatics. Its aroma and flavors deliver bready malts, spicy hops and a touch of bitter orange peel and coriander. Hellraiser is a beer that truly lives up to its name.','https://www.porchdrinking.com/wp-content/uploads/2019/11/Wheatwine-700x325.jpg',
12.5, null, true);
INSERT INTO Beers (beer_name, brewery_id, beer_type, description, picture, abv, ibu, available) VALUES ('ESB', 6, 'ESB', 'Traditional British style ESB. Rich copper colour, slight yeasty sweetness on the aroma with a striking balance of malt and hops for an endlessly crushable pint.','https://untappd.akamaized.net/photos/2020_03_13/523e0e273f3760afbf402d3999aa0e9d_640x640.jpg',
5.8, null, true);
INSERT INTO Beers (beer_name, brewery_id, beer_type, description, picture, abv, ibu, available) VALUES ('Hazy IPA', 3, 'IPA', 'Juicy and balanced mix of citrus hops that bring out the aromatic characteristics of papaya and mango and finishes with a hint of ripe strawberries. At almost 6 pounds of hops per barrel, Hazy IPA is a hop bomb that delivers the bitterness without the bite.', 'https://greatdivide.com/wp-content/uploads/2017/04/Hazy-IPA-12oz-can-2020-SB-mockup.png',
6.2, null, true);
INSERT INTO Beers (beer_name, brewery_id, beer_type, description, picture, abv, ibu, available) VALUES ('Velvet Yeti', 3, 'Nitro Stout', 'Surprisingly velvety and rich mouthfeel of the nitro pour rounds out the unmistakeable roasty, caramel & toffee notes that make Yeti such a beast.', 'https://greatdivide.com/wp-content/uploads/2016/11/Velvet-Yeti-Beer-page-e1531856741662.jpg',
5.0, null, true);
INSERT INTO Beers (beer_name, brewery_id, beer_type, description, picture, abv, ibu, available) VALUES ('Colette', 3, 'Ale', 'Brewed with barley, wheat and rice and fermented at high temperatures with a special blend of four different yeast strains, COLETTE is fruity and slightly tart, with a dry finish that makes it that rarest of treats — a beer as refreshing as it is complex', 'https://greatdivide.com/wp-content/uploads/2016/06/Colette_2017_Front_HR.png',
7.3, null, true);



CREATE Table Review (

id serial, 
title varchar,
overall varchar,
color varchar,
taste varchar,
head varchar,
smell varchar,
beerId bigint,
rating int,
userId bigint,
reviewImgUrl varchar,
reviewDate date,
breweryId bigint,

constraint pk_Review primary key (id),
constraint fk_Beers_Breweries foreign key (breweryId) references Breweries (id),
constraint fk_Breweries_Beer foreign key (beerId) references beers (beer_id)
);

INSERT INTO Review (title, overall, color, taste, head, smell, beerID, rating, userId, reviewImgURL, reviewDate, breweryId) VALUES ('awesome beer', 'I would recommend to anyone', 'dark colored ale', 'somewhat bitter', 'small head', 'smells amazing', 2, 4, 3, null, '2020-10-07', 2);
INSERT INTO Review (title, overall, color, taste, head, smell, beerID, rating, userId, reviewImgURL, reviewDate, breweryId) VALUES ('beer smells funny', 'I would not recommend to anyone', 'colored ale', 'a little bitter', 'massive head', 'smells aweful', 1, 1, 3, null, '2020-10-07', 1);
INSERT INTO Review (title, overall, color, taste, head, smell, beerID, rating, userId, reviewImgURL, reviewDate, breweryId) VALUES ('Fantastic', 'I would  recommend to anyone that drinks beer', 'light ale', 'balanced bitterness', 'normal head', 'smells like Germany', 3, 4, 1, null, '2020-10-07', 5);
INSERT INTO Review (title, overall, color, taste, head, smell, beerID, rating, userId, reviewImgURL, reviewDate, breweryId) VALUES ('tastes like potatoes', 'I would  recommend to anyone that likes potatoes', 'clear', 'tastes like a fruit tree that grows potatoes', 'normal head', 'smells like fruit with potatoes ',4, 3, 1, null, '2020-10-07', 5);
INSERT INTO Review (title, overall, color, taste, head, smell, beerID, rating, userId, reviewImgURL, reviewDate, breweryId) VALUES ('So so cold', 'Very cold', 'cold color', 'tastes cold', 'cold head', 'smells like ice ',5, 5, 1, null, '2020-10-07', 2);
INSERT INTO Review (title, overall, color, taste, head, smell, beerID, rating, userId, reviewImgURL, reviewDate, breweryId) VALUES ('I love Pumpkin', 'tastes like pumkin in a glass in a beer', 'dark color', 'strong bitterness', 'small head', 'smells like pumpkin patch ',6, 5, 1, null, '2020-10-07', 3);
INSERT INTO Review (title, overall, color, taste, head, smell, beerID, rating, userId, reviewImgURL, reviewDate, breweryId) VALUES ('Strong Rhubarb smell', 'great smell but tastes flat', 'dark red color', 'strong bitterness', 'large head', 'Rhubarb smell ',7, 3, 1, null, '2020-10-07', 3);
INSERT INTO Review (title, overall, color, taste, head, smell, beerID, rating, userId, reviewImgURL, reviewDate, breweryId) VALUES ('Campfire', 'Reminds me of eating smores by a campfire with a peanut butter cup', 'dark color', 'mild bitterness', 'normal head', 'smells like peanuts ',8, 3, 1, null, '2020-10-07', 1);
INSERT INTO Review (title, overall, color, taste, head, smell, beerID, rating, userId, reviewImgURL, reviewDate, breweryId) VALUES ('Delicious beer with minor issues ', 'Was attacked by gnomes shortly after', 'Golden color', 'mild bitterness', 'great head', 'smells like pepper and other spices ',9, 4, 1, null, '2020-10-07', 4);
INSERT INTO Review (title, overall, color, taste, head, smell, beerID, rating, userId, reviewImgURL, reviewDate, breweryId) VALUES ('Serenite Grand Cru', 'full-bodied and dry finish', 'Deep Amber Color', 'tastes like brown sugar', 'fantastic head', 'nose of malty fruit ',10, 4, 1, null, '2020-10-07', 4);
INSERT INTO Review (title, overall, color, taste, head, smell, beerID, rating, userId, reviewImgURL, reviewDate, breweryId) VALUES ('Very sour', 'Sour for a sour beer', 'Sour Color', 'refreshingly tart', 'wonderful head', 'nose of malty wheat ',11, 4, 1, null, '2020-10-07', 1);
INSERT INTO Review (title, overall, color, taste, head, smell, beerID, rating, userId, reviewImgURL, reviewDate, breweryId) VALUES ('Not what I expected', 'Margarita  taste will have you wondering what you are drinking ', 'Ruby Red', 'sour with a hint of lime', 'terrible head', 'nose of the Dead Sea ',12, 4, 1, null, '2020-10-07', 1);
INSERT INTO Review (title, overall, color, taste, head, smell, beerID, rating, userId, reviewImgURL, reviewDate, breweryId) VALUES ('Pedal IPA', 'Absolutely Delightful ', 'Normal Beer Color', 'medium bodied with a little sweetness', 'Incredible head', 'nose of tropical fruit ',13, 4, 1, null, '2020-10-07', 1);
INSERT INTO Review (title, overall, color, taste, head, smell, beerID, rating, userId, reviewImgURL, reviewDate, breweryId) VALUES ('Pretzels Destroyed', 'Easy drinking  ', 'Amber Beer Color', 'Classic lager taste', 'Incredible head', 'nose of tropical fruit ',14, 5, 1, null, '2020-10-07', 1);
INSERT INTO Review (title, overall, color, taste, head, smell, beerID, rating, userId, reviewImgURL, reviewDate, breweryId) VALUES ('beer smells old running shoes', 'I would not recommend to anything on earth', 'colored ale', 'Tastes like a dumpster fire', 'massive head', 'smells like a dead fish', 1, 5, 3, null, '2020-11-08', 1);

CREATE TABLE brewer_request (
        id serial,
	user_id int NOT NULL,
	username varchar(50) NOT NULL,
	breweryId int NOT NULL,
	processed boolean,
	CONSTRAINT PK_brewer_request PRIMARY KEY (id),
	CONSTRAINT fk_breweries foreign key (breweryId) references Breweries (id),
        CONSTRAINT fk_users foreign key (user_id) references users (user_id)
);

INSERT INTO brewer_request (user_id, username, breweryid, processed) VALUES (100, 'User2', 1, false);
INSERT INTO brewer_request (user_id, username, breweryid, processed) VALUES (101, 'User3', 2, false);
INSERT INTO brewer_request (user_id, username, breweryid, processed) VALUES (102, 'New Brewer3', 3, true);
INSERT INTO brewer_request (user_id, username, breweryid, processed) VALUES (103, 'New Brewer4', 4, false);

CREATE TABLE pending_brewery_request (
        id serial,
	user_id int NOT NULL,
	name varchar(50) NOT NULL,
	address varchar,
        description varchar,
        image varchar,
        brewery_url varchar,
        phone bigint,
        hours varchar,
	processed boolean,
	CONSTRAINT PK_pending_brewery_request PRIMARY KEY (id),
        CONSTRAINT fk_users foreign key (user_id) references users (user_id)
);

INSERT INTO pending_brewery_request (user_id, name, address, description, image, brewery_url, phone, hours, processed) VALUES (100, 'RIPPON Brewing Company', '161 Rock Lane', 'Drink a Beer, Climb a Rock', 'data:image/jpeg;base64,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','theRock.com',9499999888, '24 hours 7 Days a Week', false);
INSERT INTO pending_brewery_request (user_id, name, address, description, image, brewery_url, phone, hours, processed) VALUES (3, 'Denver Beer Company', '1695 Platte St, Denver, CO 80202', 'Brewery-based taproom with an industrial vibe, dog-friendly outdoor beer garden & food trucks', 'https://denverbeerco.com/wp-content/uploads/2014/07/logo_final3.png','https://denverbeerco.com/',3034332739, '11am to 11pm', true);
INSERT INTO pending_brewery_request (user_id, name, address, description, image, brewery_url, phone, hours, processed) VALUES (101, 'The Brewing Network', '555 South Lane', 'Im Not Having A Glass Of Beer, Im Having Six! Its Called A Tasting & Its Classy!', 'https://99designs-blog.imgix.net/blog/wp-content/uploads/2017/11/attachment_53626929-e1509447577625.png?auto=format&q=60&fit=max&w=930','tegrity.com',9495679888, '1am to 12pm', false);

CREATE TABLE brewery_news (
        newsId serial,
	breweryId int NOT NULL,
	newsTitle varchar(50) NOT NULL,
	body varchar,
	newsImageUrl varchar,
        
       
	CONSTRAINT PK_brewery_news PRIMARY KEY (newsId),
        CONSTRAINT fk_breweries foreign key (breweryId) references breweries (id)
);

INSERT INTO brewery_news (breweryId, newsTitle, body, newsImageUrl) VALUES (1, 'Runaway cow crashes window', 'Hey all,  we will remain open while we fix the front window...think about it, now we have a breezy area for you to enjoy.  Watch out for the glass!!', 'https://ewscripps.brightspotcdn.com/dims4/default/69a81a7/2147483647/strip/true/crop/930x523+0+88/resize/1280x720!/quality/90/?url=http%3A%2F%2Fewscripps-brightspot.s3.amazonaws.com%2F8a%2Fcd%2F64760e484066abb0816015cb164d%2Fscreen-shot-2019-08-20-at-3.02.57%20PM.png');
INSERT INTO brewery_news (breweryId, newsTitle, body, newsImageUrl) VALUES (2, '2nd best Brewery in Denver', 'Second is the first loser...We are coming for you Denver Beer Company for our rightfuld #1 spot. PS: watch your front window!', 'https://i.ytimg.com/vi/_WKQXlEbC1s/hqdefault.jpg');

CREATE TABLE reply_review (
        id serial,
	user_id int NOT NULL,
        review_id int not null,
        title varchar,
        reply varchar,

	CONSTRAINT PK_reply_review PRIMARY KEY (id),
        CONSTRAINT fk_users foreign key (user_id) references users (user_id),
        CONSTRAINT fk_review foreign key (review_id) references review (id)
);

INSERT INTO reply_review (user_id, review_id, title, reply) VALUES (101,2, 'Never Seen', 'This man has never been to this brewery');
INSERT INTO reply_review (user_id, review_id, title, reply) VALUES (102,2, 'Take a Pic', 'Pictures or it didnt happen');
INSERT INTO reply_review (user_id, review_id, title, reply) VALUES (3,2, 'Ok next time', 'I will be sure to take a pic of the smell next time');
INSERT INTO reply_review (user_id, review_id, title, reply) VALUES (3,1, 'Thanks', 'Thanks for stopping by');
INSERT INTO reply_review (user_id, review_id, title, reply) VALUES (103,4, 'Thank you', 'I enjoy potatoes');

CREATE TABLE brewery_favorites (
        user_id int NOT NULL, 
        brewery_id int NOT NULL,
        
CONSTRAINT pk_brewery_favorites PRIMARY KEY (user_id, brewery_id),
CONSTRAINT fk_brewery_favorites_user FOREIGN KEY (user_id) references users (user_id),
CONSTRAINT fk_brewery_favorites_brewery FOREIGN KEY (brewery_id) references breweries (id)
);

INSERT INTO brewery_favorites (user_id, brewery_id) VALUES (3, 6);



CREATE TABLE events
(
event_id serial,
brewery_id int not null,
event_date varchar(65),
event_title varchar(65),
description varchar(500) not null,
picture varchar,


constraint pk_events primary key (event_id),
constraint fk_Beers_Breweries foreign key (brewery_id) references Breweries (id)

);

INSERT INTO events (brewery_id, event_title, event_date, description, picture) VALUES (1, 'Grand Re-opening', '10/16/2020', 'Our Grand reopening! We will be doing a silent auction and 50\50 raffle.', 'https://www.chicagotribune.com/resizer/Pr0bKQcAAc3k9r3ZAu6153ZAlMo=/800x450/top/www.trbimg.com/img-5a988d8d/turbine/ct-1519947143-hi4cw0r4bq-snap-image');
INSERT INTO events (brewery_id, event_title, event_date, description, picture) VALUES (2, 'Broncos vs Eagles', '10/18/2020', 'Come enjoy a pint with friends while the game is on', 'https://pbs.twimg.com/profile_images/1306062984427364353/LvHV6KGB_400x400.jpg');

COMMIT TRANSACTION;



