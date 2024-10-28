# Информация о проекте
 Необходимо организовать систему учета для питомника в котором живут
 домашниеивьючныеживотные.

 #  Задание
 1. Используя команду cat в терминале операционной системы Linux, создать
 два файла Домашние животные(заполнив файл собаками, кошками,
 хомяками) и Вьючные животные (заполнив файл Лошадьми, верблюдамии
 ослами), а затем объединить их. Просмотреть содержимое созданного файла.
 Переименовать файл, дав ему новое имя(Друзья человека).
```
cat > pet
cat > pack_animal
cat pet pack_animal > all_animals
cat all_animals
mv all_animals human_friends
```

 3. Создать директорию, переместить файл туда.

```
mkdir animal_database
mv human_friends /animal_database
```
  
 4. Подключить дополнительный репозиторий MySQL. Установить любой пакет
 из этого репозитория.

```
sudo add-apt-repository 'deb http://repo.mysql.com/apt/ubuntu/ jammy mysql-8.0'
sudo apt install mysql-client-8.0
```

 5. Установить и удалить deb-пакет с помощью dpkg.
 ```
sudo dpkg -i Downloads/steam_latest.deb 
sudo apt -f install 
sudo dpkg -r steam-launcher
```
 6. Выложить историю команд втерминале ubuntu
  
 7. Нарисовать диаграмму, в которой есть класс родительский класс, домашние
 животные и вьючные животные, в составы которых в случае домашних
 животных войдут классы: собаки, кошки, хомяки, а в класс вьючные животные
 войдут: Лошади, верблюды и ослы).
  ![animals drawio](https://github.com/user-attachments/assets/a309219f-0eb0-4c12-b4e1-fcb88d04e28c)

 8. В подключенном MySQL репозитории создать базуданных “Друзья
 человека”

 9. Создать таблицы с иерархией из диаграммы в БД
  
  ![db](https://github.com/user-attachments/assets/e74c70f0-d5f1-4771-9d05-5cad553ab16a)

 9. Заполнить низкоуровневые таблицы именами(животных), командами
 которые они выполняют и датами рождения

```sql
  USE human_friends;
  
  CREATE TABLE kind_of_animal (
  	id INT AUTO_INCREMENT PRIMARY KEY,
   	kind VARCHAR(50)
  );
  
  INSERT INTO kind_of_animal (kind) VALUES
  	('pet'), ('pack_animal');
  			
  CREATE TABLE pet (
  	id INT AUTO_INCREMENT PRIMARY KEY,
  	animal_type VARCHAR(50),
  	kind_id INT,
  	FOREIGN KEY (kind_id) REFERENCES kind_of_animal (id)
  );
  
  INSERT INTO pet (animal_type, kind_id)  VALUES
  	('Cat', 1), ('Dog', 1), ('Hamster', 1);
  	
  CREATE TABLE pack_animal (
  	id INT AUTO_INCREMENT PRIMARY KEY,
  	animal_type VARCHAR(50),
  	kind_id INT,
  	FOREIGN KEY (kind_id) REFERENCES kind_of_animal (id)
  );
  
  INSERT INTO pack_animal (animal_type, kind_id) VALUES
  	('Horse', 2), ('Camel', 2), ('Donkey', 2);
  	
  CREATE TABLE cat (
  	id INT AUTO_INCREMENT PRIMARY KEY,
  	name VARCHAR(50),
  	birth_date DATE,
  	commands VARCHAR(100),
  	pet_id INT,
  	FOREIGN KEY (pet_id) REFERENCES pet (id)
  );
  
  INSERT INTO cat (name, birth_date, commands, pet_id) VALUES
  	('Fasol', '2015-09-13', 'Meow, Pounce, Jump', 1),
  	('Forest', '2009-10-25', 'Sit, Pounce, Scratch', 1),
  	('Sunny', '2021-05-11', 'Pounce, Jump', 1);
  
  CREATE TABLE dog (
  	id INT AUTO_INCREMENT PRIMARY KEY,
  	name VARCHAR(50),
  	birth_date DATE,
  	commands VARCHAR(100),
  	pet_id INT,
  	FOREIGN KEY (pet_id) REFERENCES pet (id)
  );
  
  INSERT INTO dog (name, birth_date, commands, pet_id) VALUES
  	('Elsa', '2023-01-14', 'Sit, Stay, Fetch, Roll, Paw, Bark', 2),
  	('Cujo', '2015-07-15', 'Sit, Paw, Bark', 2),
  	('Balto', '2017-11-21', 'Sit, Stay, Roll', 2);
  
  CREATE TABLE hamster (
  	id INT AUTO_INCREMENT PRIMARY KEY,
  	name VARCHAR(50),
  	birth_date DATE,
  	commands VARCHAR(100),
  	pet_id INT,
  	FOREIGN KEY (pet_id) REFERENCES pet (id)
  );
  
  INSERT INTO hamster (name, birth_date, commands, pet_id) VALUES
  	('Johny', '2022-12-21', 'Hide', 3),
  	('Ham', '2023-08-22', 'Roll', 3),
  	('Shisha', '2024-01-11', 'Spin', 3);
  	
  CREATE TABLE horse (
  	id INT AUTO_INCREMENT PRIMARY KEY,
  	name VARCHAR(50),
  	birth_date DATE,
  	commands VARCHAR(100),
  	pack_animal_id INT,
  	FOREIGN KEY (pack_animal_id) REFERENCES pack_animal (id)
  );
  
  INSERT INTO horse (name, birth_date, commands, pack_animal_id) VALUES
  	('Girl', '2018-02-14', 'Trot, Canter, Gallop', 1),
  	('Fog', '2013-05-09', 'Trot, Jump, Cante', 1),
  	('Sparkle', '2017-11-01', 'Trot, Jump, Gallop', 1);
  
  CREATE TABLE camel (
  	id INT AUTO_INCREMENT PRIMARY KEY,
  	name VARCHAR(50),
  	birth_date DATE,
  	commands VARCHAR(100),
  	pack_animal_id INT,
  	FOREIGN KEY (pack_animal_id) REFERENCES pack_animal (id)
  );
  
  INSERT INTO camel (name, birth_date, commands, pack_animal_id) VALUES
  	('Cowboy', '2014-07-14', 'Walk, Sit', 2),
  	('Oasis', '2013-05-29', 'Walk, Carry Load', 2),
  	('Alladin', '2011-07-08', 'Walk, Run', 2);
  	
  CREATE TABLE donkey (
  	id INT AUTO_INCREMENT PRIMARY KEY,
  	name VARCHAR(50),
  	birth_date DATE,
  	commands VARCHAR(100),
  	pack_animal_id INT,
  	FOREIGN KEY (pack_animal_id) REFERENCES pack_animal (id)
  );
  
  INSERT INTO donkey (name, birth_date, commands, pack_animal_id) VALUES
  	('Moisey', '2017-09-13', 'Walk, Carry Load, Bray, Kick', 3),
  	('Ia', '2015-02-20', 'Walk, Bray, Kick', 3),
  	('Morty', '2020-09-09', 'Walk, Carry Load, Bray', 3);
  ```

 11. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой
 питомник на зимовку. Объединить таблицы лошади и ослы в одну таблицу.
 ```sql
  DELETE FROM camel;

 SELECT name, birth_date,commands FROM horse
 UNION SELECT name, birth_date,commands FROM donkey;
 ```

 13. Создать новую таблицу “молодые животные” в которую попадут все
 животные старше 1года, но младше 3лет и в отдельном столбце с точностью
 до месяца подсчитать возраст животных в новой таблице
 ```sql
  CREATE TEMPORARY TABLE all_animals AS
  SELECT * FROM cat
  		UNION SELECT * FROM dog
  		UNION SELECT * FROM hamster 
  		UNION SELECT * FROM horse
  		UNION SELECT * FROM donkey;
  	
  CREATE TABLE young_animal AS
  SELECT name, birth_date, commands, TIMESTAMPDIFF(MONTH, birth_date, CURDATE()) AS age_in_months
  FROM all_animals WHERE birth_date BETWEEN ADDDATE(curdate(), INTERVAL -3 YEAR) AND ADDDATE(CURDATE(), INTERVAL -1 YEAR);
 ```
 
 15. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на
 прошлую принадлежность кстарым таблицам.
 ```sql
  SELECT c.name, c.birth_date, c.commands, ya.age_in_months, p.animal_type  
	FROM cat c
	LEFT JOIN pet p ON p.id = c.pet_id 
	LEFT JOIN young_animal ya ON ya.name = c.name
	UNION
	SELECT d.name, d.birth_date, d.commands, ya.age_in_months, p.animal_type
	FROM dog d
	LEFT JOIN pet p ON p.id = d.pet_id 
	LEFT JOIN young_animal ya ON ya.name = d.name
	UNION
	SELECT h.name, h.birth_date, h.commands, ya.age_in_months, p.animal_type
	FROM hamster h
	LEFT JOIN pet p ON p.id = h.pet_id 
	LEFT JOIN young_animal ya ON ya.name = h.name
	UNION
	SELECT h2.name, h2.birth_date, h2.commands, ya.age_in_months, pa.animal_type
	FROM horse h2 
	LEFT JOIN pack_animal pa ON pa.id = h2.pack_animal_id 
	LEFT JOIN young_animal ya ON ya.name = h2.name
	UNION
	SELECT c2.name, c2.birth_date, c2.commands, ya.age_in_months, pa.animal_type
	FROM camel c2 
	LEFT JOIN pack_animal pa ON pa.id = c2.pack_animal_id 
	LEFT JOIN young_animal ya ON ya.name = c2.name
	UNION
	SELECT d2.name, d2.birth_date, d2.commands, ya.age_in_months, pa.animal_type
	FROM donkey d2 
	LEFT JOIN pack_animal pa ON pa.id = d2.pack_animal_id 
	LEFT JOIN young_animal ya ON ya.name = d2.name;
 ```
 16. Создать класс с Инкапсуляцией методов и наследованием по диаграмме.
 
 17. Написать программу, имитирующую работу реестра домашних животных.
