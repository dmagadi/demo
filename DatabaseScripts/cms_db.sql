-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';



-- -----------------------------------------------------
-- Table `cms`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cms`.`users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `FirstName` VARCHAR(255) NULL DEFAULT NULL,
  `LastName` VARCHAR(255) NOT NULL,
  `createdts` DATETIME NULL DEFAULT NULL,
  `modifiedts` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `cms`.`contact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cms`.`contact` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `LastName` VARCHAR(255) NOT NULL,
  `FirstName` VARCHAR(255) NOT NULL,
  `DateOfBirth` DATETIME NULL DEFAULT NULL,
  `Gender` VARCHAR(1) NULL DEFAULT NULL,
  `createts` DATETIME NULL DEFAULT NULL,
  `modifiedts` DATETIME NULL DEFAULT NULL,
  `userid` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_contact_userid_idx` (`userid` ASC),
  CONSTRAINT `fk_contact_userid`
    FOREIGN KEY (`userid`)
    REFERENCES `cms`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `cms`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cms`.`address` (
  `id` INT(11) NOT NULL,
  `street address` VARCHAR(45) NULL DEFAULT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  `state` VARCHAR(45) NULL DEFAULT NULL,
  `zipcode` VARCHAR(45) NULL DEFAULT NULL,
  `contact_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_address_contact1_idx` (`contact_id` ASC),
  CONSTRAINT `fk_address_contact1`
    FOREIGN KEY (`contact_id`)
    REFERENCES `cms`.`contact` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `cms`.`emails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cms`.`emails` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `email_type` VARCHAR(45) NULL DEFAULT NULL,
  `contact_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_contact_email_idx` (`contact_id` ASC),
  CONSTRAINT `fk_contact_email`
    FOREIGN KEY (`contact_id`)
    REFERENCES `cms`.`contact` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `cms`.`phone_numbers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cms`.`phone_numbers` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `phone_number` VARCHAR(45) NOT NULL,
  `phone_type` VARCHAR(45) NOT NULL,
  `contact_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_contact_id_idx` (`contact_id` ASC),
  CONSTRAINT `fk_contact_id`
    FOREIGN KEY (`contact_id`)
    REFERENCES `cms`.`contact` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
