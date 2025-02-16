CREATE DATABASE active_pharmacy_v2;

use active_pharmacy_v2;

CREATE TABLE `t_drug_category` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) NOT NULL,
  `is_active` TINYINT NOT NULL DEFAULT 1,
  `is_deleted` TINYINT NOT NULL DEFAULT 0,
  `created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_name` (`category_name`)
);


CREATE TABLE `t_drug_class` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `class_name` varchar(100) NOT NULL,
  `category_id` int unsigned NOT NULL,
  `is_active` TINYINT NOT NULL DEFAULT 1,
  `is_deleted` TINYINT NOT NULL DEFAULT 0,
  `created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `class_name` (`class_name`)
);


CREATE TABLE `t_drug` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `drug_label_name` varchar(100) NOT NULL,
  `category_id` int unsigned NOT NULL,
  `class_id` int unsigned NOT NULL,
  `is_active` TINYINT NOT NULL DEFAULT 1,
  `is_deleted` TINYINT NOT NULL DEFAULT 0,
  `created_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `drug_label_name` (`drug_label_name`)
);


CREATE TABLE `t_user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(40) NOT NULL,
  `role` VARCHAR(24) NOT NULL,
  `first_name` VARCHAR(40) NOT NULL,
  `last_name` VARCHAR(40) NOT NULL,  
  `password` VARCHAR(120) NOT NULL,
  `phone_number` VARCHAR(16),
  `address_id` INT UNSIGNED,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
);

CREATE TABLE `t_user_address` (
 `id` INT unsigned NOT NULL AUTO_INCREMENT,
 `user_id` INT unsigned NOT NULL,
 `address_1` VARCHAR(40),
 `address_2` VARCHAR(40),
 `city` VARCHAR(40),
 `state` VARCHAR(40),
 `country` VARCHAR(40),
 `zip_code` VARCHAR(40),
 `email` VARCHAR(40) NOT NULL,
 `phone_number` VARCHAR(16), 
 PRIMARY KEY (`id`)
);


INSERT INTO t_drug(drug_label_name, category_id, class_id)
VALUES
('Amoxicillin Oral Chew 125 mg', 1, 1 ),
('Amoxicillin Oral Chew 250 mg', 1, 1 ),
('Penicillin G Pot Soln 40000 unit/ml', 1, 2 ),
('Penicillin G Pot Soln 60000 unit/ml', 1, 2 ),
('Amoxicillin-pot Clavulanate Oral Chew 400 mg', 1, 3 ),
('Amoxicillin-pot Clavulanate Oral Tabs 250 mg', 1, 3 ),
('ACTHIB INTRAMUSCULAR SOLR VIAL', 2, 4 ),
('BCG Vaccine Injection Solr 50 mg Vial', 2, 4 ),
('ENGERIX-B INJECTION SUSP 10 MCG/0.5ML SYRINGE', 2, 5 ),
('GARDASIL 9 INTRAMUSCULAR SUSP VIAL', 2, 5 );




