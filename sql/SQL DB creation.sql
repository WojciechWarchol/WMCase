DROP SCHEMA IF EXISTS `wm_case`;

CREATE SCHEMA `wm_case`;

USE `wm_case`; 


DROP TABLE IF EXISTS `clients`;
CREATE TABLE `clients` (
	`id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(40) NOT NULL,
	`surname` varchar(40) NOT NULL,
	`email` varchar(40) NOT NULL,
	`tel` varchar(40) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY (email, tel)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
	`id` int NOT NULL AUTO_INCREMENT,
	`comments` text,
	`charge` decimal(9,2) NOT NULL,
	`orderstatus` varchar(40) NOT NULL,
	`dt` date,
	`client_id` int NOT NULL,
	PRIMARY KEY (`id`),
	FOREIGN KEY (`client_id`) REFERENCES clients(`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `cases`;
CREATE TABLE `cases` (
	`id` int NOT NULL AUTO_INCREMENT,
	`length` int NOT NULL,
	`width` int NOT NULL,
	`height` int NOT NULL,
	`surface` decimal(9,2) NOT NULL,
	`type` varchar(40) ,
	`material` varchar(40),
	`color` varchar(40) ,
	`filling` varchar(40) ,
	`handle` varchar(40) ,
	`handle_num` int NOT NULL,
	`wheels` boolean DEFAULT false,
	`wheel_num` int DEFAULT 0,
	`locks` varchar(40) ,
	`comments` text,
	`price` decimal(9,2) NOT NULL,
	`order_id` int DEFAULT 1,
	PRIMARY KEY (`id`),
	FOREIGN KEY (`order_id`) REFERENCES orders(`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `quantities`;
CREATE TABLE `quantities` (
    `order_id` int NOT NULL,
    `case_id` int NOT NULL,
    `quantity` int NOT NULL DEFAULT 1,
    FOREIGN KEY (`order_id`) REFERENCES orders(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`case_id`) REFERENCES cases(`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;
