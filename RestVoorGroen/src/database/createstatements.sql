-- MySQL Script generated by MySQL Workbench
-- 03/20/15 13:32:33
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`activity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`activity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_activity` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `inlognaam` VARCHAR(45) NOT NULL,
  `wachtwoord` VARCHAR(45) NOT NULL,
  `fitbitid` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`sponsor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`sponsor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `naam` VARCHAR(45) NOT NULL,
  `emailadres` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`achievements`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`achievements` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `value` DOUBLE NOT NULL,
  `activity_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_achievements_activity_idx` (`activity_id` ASC),
  CONSTRAINT `fk_achievements_activity`
    FOREIGN KEY (`activity_id`)
    REFERENCES `mydb`.`activity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`user_achievements`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user_achievements` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `achievements_id` INT NOT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_achievements_achievements1_idx` (`achievements_id` ASC),
  INDEX `fk_user_achievements_users1_idx` (`users_id` ASC),
  CONSTRAINT `fk_user_achievements_achievements1`
    FOREIGN KEY (`achievements_id`)
    REFERENCES `mydb`.`achievements` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_achievements_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`milestones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`milestones` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `value` DOUBLE NOT NULL,
  `bedrag` DOUBLE NOT NULL,
  `activity_id` INT NOT NULL,
  `sponsor_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_milestones_activity1_idx` (`activity_id` ASC),
  INDEX `fk_milestones_sponsor1_idx` (`sponsor_id` ASC),
  CONSTRAINT `fk_milestones_activity1`
    FOREIGN KEY (`activity_id`)
    REFERENCES `mydb`.`activity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_milestones_sponsor1`
    FOREIGN KEY (`sponsor_id`)
    REFERENCES `mydb`.`sponsor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`user_activity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user_activity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `activity_id` INT NOT NULL,
  `users_id` INT NOT NULL,
  `value` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_activity_activity1_idx` (`activity_id` ASC),
  INDEX `fk_user_activity_users1_idx` (`users_id` ASC),
  CONSTRAINT `fk_user_activity_activity1`
    FOREIGN KEY (`activity_id`)
    REFERENCES `mydb`.`activity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_activity_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;