-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema G222_B_BD3
-- -----------------------------------------------------
USE `G222_B_BD3` ;

-- -----------------------------------------------------
-- Table `G222_B_BD3`.`CompteSystem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `G222_B_BD3`.`CompteSystem` (
  `Login` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`Login`),
  UNIQUE INDEX `Login_UNIQUE` (`Login` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `G222_B_BD3`.`Compte`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `G222_B_BD3`.`Compte` (
  `Login` INT(11) NOT NULL,
  `masterPassword` VARCHAR(100) NOT NULL,
  `Index` INT(11) NOT NULL AUTO_INCREMENT,
  `domainHash` INT(11) NOT NULL,
  `passwordLength` VARCHAR(45) NOT NULL,
  `CompteSystem_Login` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Index`),
  INDEX `fk_Compte_CompteSystem_idx` (`CompteSystem_Login` ASC),
  CONSTRAINT `fk_Compte_CompteSystem`
    FOREIGN KEY (`CompteSystem_Login`)
    REFERENCES `G222_B_BD3`.`CompteSystem` (`Login`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 76
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `G222_B_BD3`.`Session`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `G222_B_BD3`.`Session` (
  `index` INT(11) NOT NULL AUTO_INCREMENT,
  `sucess` TINYINT(4) NULL DEFAULT NULL,
  `Compte_Index` INT(11) NOT NULL,
  PRIMARY KEY (`index`),
  INDEX `fk_Session_Compte1_idx` (`Compte_Index` ASC),
  CONSTRAINT `fk_Session_Compte1`
    FOREIGN KEY (`Compte_Index`)
    REFERENCES `G222_B_BD3`.`Compte` (`Index`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 66
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `G222_B_BD3`.`Entree`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `G222_B_BD3`.`Entree` (
  `Index` INT(11) NOT NULL AUTO_INCREMENT,
  `Local` VARCHAR(45) NULL DEFAULT NULL,
  `try` INT(11) NULL DEFAULT NULL,
  `Session_index` INT(11) NOT NULL,
  PRIMARY KEY (`Index`),
  INDEX `fk_Entree_Session1_idx` (`Session_index` ASC),
  CONSTRAINT `fk_Entree_Session1`
    FOREIGN KEY (`Session_index`)
    REFERENCES `G222_B_BD3`.`Session` (`index`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 660
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `G222_B_BD3`.`Touche`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `G222_B_BD3`.`Touche` (
  `timeUp` VARCHAR(45) NULL DEFAULT NULL,
  `timeDown` VARCHAR(45) NULL DEFAULT NULL,
  `pressure` VARCHAR(45) NULL DEFAULT NULL,
  `modifierSequence` VARCHAR(45) NULL DEFAULT NULL,
  `shiftUp` VARCHAR(45) NULL DEFAULT NULL,
  `shiftDown` VARCHAR(45) NULL DEFAULT NULL,
  `shiftLocation` VARCHAR(45) NULL DEFAULT NULL,
  `ctrlUp` VARCHAR(45) NULL DEFAULT NULL,
  `ctrlDown` VARCHAR(45) NULL DEFAULT NULL,
  `ctrlLocation` VARCHAR(45) NULL DEFAULT NULL,
  `altUp` VARCHAR(45) NULL DEFAULT NULL,
  `altDown` VARCHAR(45) NULL DEFAULT NULL,
  `altLocation` VARCHAR(45) NULL DEFAULT NULL,
  `capslockUp` VARCHAR(45) NULL DEFAULT NULL,
  `capslockDown` VARCHAR(45) NULL DEFAULT NULL,
  `Entree_Index` INT(11) NOT NULL,
  INDEX `fk_Touche_Entree1_idx` (`Entree_Index` ASC),
  CONSTRAINT `fk_Touche_Entree1`
    FOREIGN KEY (`Entree_Index`)
    REFERENCES `G222_B_BD3`.`Entree` (`Index`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
