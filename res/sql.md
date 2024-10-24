<!-- Создание и заполнение -->
USE Human_Friends;

CREATE TABLE kind_of_animal (
	id INT AUTO_INCREMENT PRIMARY KEY,
 	kind VARCHAR(50)
);

INSERT INTO kind_of_animal (kind) VALUES
	('pet'), ('pack_animal');
			
CREATE TABLE pets (
	id INT AUTO_INCREMENT PRIMARY KEY,
	animal_type VARCHAR(50),
	kind_id INT,
	FOREIGN KEY (kind_id) REFERENCES kind_of_animal (id)
);

INSERT INTO pets (animal_type, kind_id)  VALUES
	('Cat', 1), ('Dog', 1), ('Hamster', 1);
	
CREATE TABLE pack_animals (
	id INT AUTO_INCREMENT PRIMARY KEY,
	animal_type VARCHAR(50),
	kind_id INT,
	FOREIGN KEY (kind_id) REFERENCES kind_of_animal (id)
);

INSERT INTO pack_animals (animal_type, kind_id) VALUES
	('Horse', 2), ('Camel', 2), ('Donkey', 2);
	
CREATE TABLE cats (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50),
	birth_date DATE,
	commands VARCHAR(100),
	pet_id INT,
	FOREIGN KEY (pet_id) REFERENCES pets (id)
);

INSERT INTO cats (name, birth_date, commands, pet_id) VALUES
	('Fasol', '2015-09-13', 'Meow, Pounce, Jump', 1),
	('Forest', '2009-10-25', 'Sit, Pounce, Scratch', 1),
	('Sunny', '2021-05-11', 'Pounce, Jump', 1);

CREATE TABLE dogs (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50),
	birth_date DATE,
	commands VARCHAR(100),
	pet_id INT,
	FOREIGN KEY (pet_id) REFERENCES pets (id)
);

INSERT INTO dogs (name, birth_date, commands, pet_id) VALUES
	('Elsa', '2023-01-14', 'Sit, Stay, Fetch, Roll, Paw, Bark', 2),
	('Cujo', '2015-07-15', 'Sit, Paw, Bark', 2),
	('Balto', '2017-11-21', 'Sit, Stay, Roll', 2);

CREATE TABLE hamsters (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50),
	birth_date DATE,
	commands VARCHAR(100),
	pet_id INT,
	FOREIGN KEY (pet_id) REFERENCES pets (id)
);

INSERT INTO hamsters (name, birth_date, commands, pet_id) VALUES
	('Johny', '2022-12-21', 'Hide', 3),
	('Ham', '2023-08-22', 'Roll', 3),
	('Shisha', '2024-01-11', 'Spin', 3);
	
CREATE TABLE horses (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50),
	birth_date DATE,
	commands VARCHAR(100),
	pack_animal_id INT,
	FOREIGN KEY (pack_animal_id) REFERENCES pack_animals (id)
);

INSERT INTO horses (name, birth_date, commands, pack_animal_id) VALUES
	('Girl', '2018-02-14', 'Trot, Canter, Gallop', 1),
	('Fog', '2013-05-09', 'Trot, Jump, Cante', 1),
	('Sparkle', '2017-11-01', 'Trot, Jump, Gallop', 1);

CREATE TABLE camels (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50),
	birth_date DATE,
	commands VARCHAR(100),
	pack_animal_id INT,
	FOREIGN KEY (pack_animal_id) REFERENCES pack_animals (id)
);

INSERT INTO camels (name, birth_date, commands, pack_animal_id) VALUES
	('Cowboy', '2014-07-14', 'Walk, Sit', 2),
	('Oasis', '2013-05-29', 'Walk, Carry Load', 2),
	('Alladin', '2011-07-08', 'Walk, Run', 2);
	
CREATE TABLE donkeys (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50),
	birth_date DATE,
	commands VARCHAR(100),
	pack_animal_id INT,
	FOREIGN KEY (pack_animal_id) REFERENCES pack_animals (id)
);

INSERT INTO donkeys(name, birth_date, commands, pack_animal_id) VALUES
	('Moisey', '2017-09-13', 'Walk, Carry Load, Bray, Kick', 3),
	('Ia', '2015-02-20', 'Walk, Bray, Kick', 3),
	('Morty', '2020-09-09', 'Walk, Carry Load, Bray', 3);

<!-- Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой
питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу -->

DELETE FROM camels;

SELECT name, birth_date,commands FROM horses
UNION SELECT name, birth_date,commands FROM donkeys;