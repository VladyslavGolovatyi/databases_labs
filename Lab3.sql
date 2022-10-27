CREATE DATABASE IF NOT EXISTS `golovatyi`;
USE `golovatyi` ;

DROP TABLE IF EXISTS `connected_flights`;
DROP TABLE IF EXISTS `bought_tickets`;
DROP TABLE IF EXISTS `flights`;
DROP TABLE IF EXISTS `base_airports`;
DROP TABLE IF EXISTS `planes`;
DROP TABLE IF EXISTS `airlines`;
DROP TABLE IF EXISTS `airports`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `airlines` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `foundation_year` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name`))
ENGINE = InnoDB;

CREATE TABLE `planes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `airline_id` INT NULL DEFAULT NULL,
  `max_passengers` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_airline_idx` (`airline_id`),
  CONSTRAINT `fk_planes_airline_id`
    FOREIGN KEY (`airline_id`)
    REFERENCES `airlines` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE `airports` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE `flights` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `src_airport_id` INT NULL DEFAULT NULL,
  `dest_airport_id` INT NULL DEFAULT NULL,
  `plane_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_src_airport_idx` (`src_airport_id`),
  INDEX `fk_dest_airport_idx` (`dest_airport_id`),
  INDEX `fk_flights_plane_idx` (`plane_id`),
  CONSTRAINT `fk_flights_dest_airport`
    FOREIGN KEY (`dest_airport_id`)
    REFERENCES `airports` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_flights_plane`
    FOREIGN KEY (`plane_id`)
    REFERENCES `planes` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_flights_src_airport`
    FOREIGN KEY (`src_airport_id`)
    REFERENCES `airports` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE `connected_flights` (
  `first_flight_id` INT NOT NULL,
  `second_flight_id` INT NOT NULL,
  PRIMARY KEY (`first_flight_id`, `second_flight_id`),
  CONSTRAINT `fk_connflights_first_flight`
    FOREIGN KEY (`first_flight_id`)
    REFERENCES `flights` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_connflights_second_flight`
    FOREIGN KEY (`second_flight_id`)
    REFERENCES `flights` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE `users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `full_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email`) )
ENGINE = InnoDB;

CREATE TABLE `bought_tickets` (
    `user_id` INT NOT NULL,
    `flight_id` INT NOT NULL,
    `purchase_time` DATETIME NULL DEFAULT NULL,
    PRIMARY KEY (`user_id` , `flight_id`),
    INDEX `fk_flight_id_idx` (`flight_id`),
    CONSTRAINT `fk_tickets_user_id` FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_tikets_flight_id` FOREIGN KEY (`flight_id`)
        REFERENCES `flights` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
)  
ENGINE=InnoDB;

CREATE TABLE `base_airports` (
  `airline_id` INT NOT NULL,
  `airport_id` INT NOT NULL,
  PRIMARY KEY (`airline_id`, `airport_id`),
  INDEX `fk_airport_id_idx` (`airport_id`),
  CONSTRAINT `fk_base_airports_airline`
    FOREIGN KEY (`airline_id`)
    REFERENCES `airlines` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_base_airports_airport_id`
    FOREIGN KEY (`airport_id`)
    REFERENCES `airports` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

INSERT INTO `airlines` (`id`,`name`,`foundation_year`) VALUES 
(1,'airline1',NULL),
(2,'airline2',NULL),
(3,'Turkish airlines',2000),
(4,'airline4',NULL),
(5,'airline5',NULL),
(6,'airline6',NULL),
(7,'airline7',NULL),
(8,'Qatar airways',NULL),
(9,'United airlines',NULL),
(10,'airline10',1980);

INSERT INTO `airports` (`id`,`name`) VALUES
(1,NULL),
(2,'airport2'),
(3,NULL),
(4,NULL),
(5,NULL),
(6,NULL),
(7,'airport7'),
(8,NULL),
(9,NULL),
(10,NULL);

INSERT INTO `base_airports` (`airline_id`,`airport_id`) VALUES 
(3,1),
(1,2),
(1,3),
(1,4),
(4,5),
(5,6),
(1,7),
(7,8),
(7,9),
(7,10);

INSERT INTO `users` (`id`,`full_name`,`email`,`password`) VALUES 
(1,'user1','email1','12'),
(2,'user2','email2','123'),
(3,'user3','email3','1234'),
(4,'user4','email4','123'),
(5,'user5','email5','1234'),
(6,'user6','email6','123'),
(7,'Vladyslav Golovatyi','vladyslavgolovatyi@gmail.com','1234'),
(8,'user8','email8','123'),
(9,'user9','email9','1234'),
(10,'user10','email10','123');

INSERT INTO `planes` (`id`,`airline_id`,`max_passengers`) VALUES
(1,1,100),
(2,1,150),
(3,1,200),
(4,1,200),
(5,1,100),
(6,2,NULL),
(7,2,NULL),
(8,2,NULL),
(9,3,140),
(10,3,NULL);

INSERT INTO `flights` (`id`,`src_airport_id`,`dest_airport_id`,`plane_id`) VALUES
(1,1,2,1),
(2,1,3,2),
(3,1,4,3),
(4,1,6,4),
(5,1,7,5),
(6,2,1,6),
(7,2,8,5),
(8,2,9,4),
(9,3,8,3),
(10,4,10,1);

INSERT INTO `connected_flights` (`first_flight_id`,`second_flight_id`) VALUES 
(1,2),
(1,3),
(2,3),
(1,4),
(2,4),
(3,4),
(1,5),
(3,5),
(2,7),
(1,8);

INSERT INTO `bought_tickets` (`user_id`,`flight_id`,`purchase_time`) VALUES 
(1,1,'2022-09-03 21:23:00'),
(1,2,'2022-08-03 21:23:00'),
(1,3,NULL),
(2,1,NULL),
(3,1,NULL),
(4,1,NULL),
(5,1,NULL),
(6,1,NULL),
(7,1,NULL);
