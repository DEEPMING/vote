-- MySQL Script generated by MySQL Workbench
-- Sat Apr  4 17:30:47 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema voteDB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `voteDB` ;

-- -----------------------------------------------------
-- Schema voteDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `voteDB` DEFAULT CHARACTER SET utf8 ;
USE `voteDB` ;

-- -----------------------------------------------------
-- Table `voteDB`.`personnel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voteDB`.`personnel` ;

CREATE TABLE IF NOT EXISTS `voteDB`.`personnel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `identity` VARCHAR(45) NULL,
  `up` VARCHAR(45) NULL,
  `code` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `identityCode` VARCHAR(45) NULL,
  `beizhu` BLOB NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `voteDB`.`personnel_login`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voteDB`.`personnel_login` ;

CREATE TABLE IF NOT EXISTS `voteDB`.`personnel_login` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `mainId` VARCHAR(45) NULL,
  `userName` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `identity` VARCHAR(45) NULL,
  `meetingId` VARCHAR(45) NULL,
  `finished` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `voteDB`.`meeting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voteDB`.`meeting` ;

CREATE TABLE IF NOT EXISTS `voteDB`.`meeting` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `time` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `organizer` VARCHAR(45) NULL,
  `mainId` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `voteDB`.`info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voteDB`.`info` ;

CREATE TABLE IF NOT EXISTS `voteDB`.`info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `meetingId` VARCHAR(45) NULL,
  `content` BLOB NULL,
  `class` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `voteDB`.`file`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voteDB`.`file` ;

CREATE TABLE IF NOT EXISTS `voteDB`.`file` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `meetingId` VARCHAR(45) NULL,
  `fileName` VARCHAR(100) NULL,
  `filePath` VARCHAR(100) NULL,
  `fileClass` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `voteDB`.`selector`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voteDB`.`selector` ;

CREATE TABLE IF NOT EXISTS `voteDB`.`selector` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `meetingId` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `content` BLOB NULL,
  `sum` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `voteDB`.`personnel_selector`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `voteDB`.`personnel_selector` ;

CREATE TABLE IF NOT EXISTS `voteDB`.`personnel_selector` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `mainId` VARCHAR(45) NULL,
  `selectorId` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
