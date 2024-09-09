DROP DATABASE IF EXISTS interview_nhinguyen;
CREATE DATABASE IF NOT EXISTS interview_nhinguyen;
USE interview_nhinguyen;

--
-- Table structure for table `user`
--
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NULL DEFAULT NULL,
	`email` VARCHAR(255) NOT NULL,
	`password` VARCHAR(255) NOT NULL,
	`phone` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `wallet`;
CREATE TABLE `wallet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wallet_address` varchar(45) NOT NULL,
  `balance` float DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `wallet_address_UNIQUE` (`wallet_address`),
  UNIQUE KEY `id_UNIQUE` (`id`)
);

DROP TABLE IF EXISTS `crypto_pair`;
CREATE TABLE `crypto_pair` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crypto_pair_name` varchar(8) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `crypto_pair_name_UNIQUE` (`crypto_pair_name`)
);

DROP TABLE IF EXISTS `tenant`;
CREATE TABLE `tenant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
);

DROP TABLE IF EXISTS `crypto_price`;
CREATE TABLE `crypto_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crypto_pair_id` int(11) NOT NULL,
  `bid_price` float NOT NULL,
  `ask_price` float NOT NULL,
  `tenant_id` int(11) NOT NULL,
  `bid_quantity` float NOT NULL,
  `ask_quantity` float NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
);

DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
  `id` int(11) NOT NULL,
  `wallet_id` int(11) NOT NULL,
  `crypto_pair_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `trading_type` varchar(4) NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
);

--
-- Initial data
--
INSERT INTO `interview_nhinguyen`.`crypto_pair`(`crypto_pair_name`) VALUES ('BTCUSDT');
INSERT INTO `interview_nhinguyen`.`crypto_pair`(`crypto_pair_name`) VALUES ('ETHUSDT');


INSERT INTO `interview_nhinguyen`.`tenant` (`name`) VALUES ('binance');
INSERT INTO `interview_nhinguyen`.`tenant` (`name`) VALUES ('houbi');