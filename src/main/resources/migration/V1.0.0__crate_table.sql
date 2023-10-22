CREATE SCHEMA IF NOT EXISTS 'osbb' DEFAULT CHARACTER SET utf8;
USE 'osbb';

CREATE TABLE IF NOT EXISTS builders (id INT AUTO_INCREMENT PRIMARY KEY, address VARCHAR(100));

CREATE TABLE IF NOT EXISTS apartments (id INT AUTO_INCREMENT PRIMARY KEY, number SMALLINT, area SMALLINT);

CREATE TABLE IF NOT EXISTS builders_to_apartments (id INT AUTO_INCREMENT PRIMARY KEY, builder_id INT, apartment_id INT,
                                     FOREIGN KEY (builder_id) REFERENCES builders(id),
                                     FOREIGN KEY (apartment_id) REFERENCES apartments(id));

CREATE TABLE IF NOT EXISTS participants_OSBB (id INT PRIMARY KEY NOT NULL, name VARCHAR(100) NOT NULL);

CREATE TABLE IF NOT EXISTS residents (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100) NOT NULL , sname VARCHAR(100) NOT NULL,email VARCHAR(100) NOT NULL,
    CONSTRAINT chk_email_format CHECK (email REGEXP '^[A-Za-z0-9+_.-]+@(.+)$'), drive_into_the_territory TINYINT DEFAULT 0, participant_OSBB_id INT,
	FOREIGN KEY (participant_OSBB_id) REFERENCES participants_OSBB(id));

CREATE TABLE IF NOT EXISTS property_rights (id INT PRIMARY KEY, property_right VARCHAR(100));

CREATE TABLE IF NOT EXISTS residents_to_apartments (id INT, resident_id INT, apartment_id INT,
                                      FOREIGN KEY (resident_id) REFERENCES residents(id),
                                      FOREIGN KEY (apartment_id) REFERENCES apartments(id));

CREATE TABLE IF NOT EXISTS property_rights_to_residents (id INT PRIMARY KEY, resident_id INT, property_right_id INT,
                                           FOREIGN KEY (resident_id) REFERENCES residents(id),
                                           FOREIGN KEY (property_right_id) REFERENCES property_rights(id));

CREATE TABLE IF NOT EXISTS participant_to_apartments (id INT, apartment_id INT, participant_OSBB_id INT,
                                        FOREIGN KEY (apartment_id) REFERENCES apartments(id),
                                        FOREIGN KEY (participant_OSBB_id) REFERENCES participants_OSBB(id));


