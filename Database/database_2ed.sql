-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ESAKE_Management_App
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ESAKE_Management_App
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ESAKE_Management_App` DEFAULT CHARACTER SET utf8 ;
USE `ESAKE_Management_App` ;

-- -----------------------------------------------------
-- Table `ESAKE_Management_App`.`championship`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ESAKE_Management_App`.`championship` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `championship_id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ESAKE_Management_App`.`round`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ESAKE_Management_App`.`round` (
  `id` INT NOT NULL,
  `championship_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `championship_id`),
  INDEX `fk_season_championship1_idx` (`championship_id` ASC),
  CONSTRAINT `fk_season_championship1`
    FOREIGN KEY (`championship_id`)
    REFERENCES `ESAKE_Management_App`.`championship` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ESAKE_Management_App`.`city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ESAKE_Management_App`.`city` (
  `city_code` VARCHAR(4) CHARACTER SET 'utf8' NOT NULL,
  `city_name_en` VARCHAR(64) CHARACTER SET 'utf8' NULL,
  `city_name_gr` VARCHAR(64) CHARACTER SET 'utf8' NULL,
  PRIMARY KEY (`city_code`),
  UNIQUE INDEX `city_code_UNIQUE` (`city_code` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ESAKE_Management_App`.`player_position`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ESAKE_Management_App`.`player_position` (
  `position_code` VARCHAR(4) CHARACTER SET 'utf8' NOT NULL,
  `position_name` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`position_code`),
  UNIQUE INDEX `position_code_UNIQUE` (`position_code` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ESAKE_Management_App`.`team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ESAKE_Management_App`.`team` (
  `team_code` VARCHAR(4) CHARACTER SET 'utf8' NOT NULL,
  `name_en` VARCHAR(64) CHARACTER SET 'utf8' NULL,
  `name_gr` VARCHAR(64) NULL,
  `city_code` VARCHAR(4) CHARACTER SET 'utf8' NOT NULL,
  `logo` BLOB NULL,
  `last_modified` TIMESTAMP NULL,
  PRIMARY KEY (`team_code`),
  UNIQUE INDEX `team_code_UNIQUE` (`team_code` ASC),
  INDEX `fk_team_city1_idx` (`city_code` ASC),
  CONSTRAINT `fk_team_city1`
    FOREIGN KEY (`city_code`)
    REFERENCES `ESAKE_Management_App`.`city` (`city_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ESAKE_Management_App`.`game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ESAKE_Management_App`.`game` (
  `game_id` INT NOT NULL,
  `day_id` INT NOT NULL,
  `championship_id` INT UNSIGNED NOT NULL,
  `team_home` VARCHAR(4) CHARACTER SET 'utf8' NOT NULL,
  `team_away` VARCHAR(4) CHARACTER SET 'utf8' NOT NULL,
  `last_modified` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`game_id`, `day_id`, `championship_id`),
  INDEX `fk_game_season1_idx` (`day_id` ASC),
  INDEX `fk_game_championship1_idx` (`championship_id` ASC),
  INDEX `fk_game_team1_idx` (`team_home` ASC),
  INDEX `fk_game_team2_idx` (`team_away` ASC),
  CONSTRAINT `fk_game_season1`
    FOREIGN KEY (`day_id`)
    REFERENCES `ESAKE_Management_App`.`round` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_championship1`
    FOREIGN KEY (`championship_id`)
    REFERENCES `ESAKE_Management_App`.`championship` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_team1`
    FOREIGN KEY (`team_home`)
    REFERENCES `ESAKE_Management_App`.`team` (`team_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_team2`
    FOREIGN KEY (`team_away`)
    REFERENCES `ESAKE_Management_App`.`team` (`team_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ESAKE_Management_App`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ESAKE_Management_App`.`user` (
  `username` VARCHAR(16) CHARACTER SET 'utf8' NOT NULL,
  `email` VARCHAR(255) CHARACTER SET 'utf8' NOT NULL,
  `password` VARCHAR(32) CHARACTER SET 'utf8' NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `user_type` INT NULL,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));


-- -----------------------------------------------------
-- Table `ESAKE_Management_App`.`player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ESAKE_Management_App`.`player` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name_en` VARCHAR(64) CHARACTER SET 'utf8' NULL,
  `surname_en` VARCHAR(64) CHARACTER SET 'utf8' NULL,
  `name_gr` VARCHAR(64) CHARACTER SET 'utf8' NULL,
  `surname_gr` VARCHAR(64) NULL,
  `player_position_code` VARCHAR(4) NOT NULL,
  `team_code` VARCHAR(4) NOT NULL,
  `img` BLOB NULL,
  `number` INT NULL,
  `last_modified` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  PRIMARY KEY (`id`),
  INDEX `fk_player_player_position1_idx` (`player_position_code` ASC),
  INDEX `fk_player_team1_idx` (`team_code` ASC),
  CONSTRAINT `fk_player_player_position1`
    FOREIGN KEY (`player_position_code`)
    REFERENCES `ESAKE_Management_App`.`player_position` (`position_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_player_team1`
    FOREIGN KEY (`team_code`)
    REFERENCES `ESAKE_Management_App`.`team` (`team_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ESAKE_Management_App`.`player_stats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ESAKE_Management_App`.`player_stats` (
  `player_id` INT NOT NULL,
  `game_id` INT NOT NULL,
  `freethrows_in` INT ZEROFILL NULL,
  `freethrows_out` INT ZEROFILL NULL,
  `two_points_in` INT ZEROFILL NULL,
  `two_points_out` INT ZEROFILL NULL,
  `three_points_in` INT ZEROFILL NULL,
  `three_points_out` INT ZEROFILL NULL,
  `offensive_rebounds` INT ZEROFILL NULL,
  `defensive_rebounds` INT ZEROFILL NULL,
  `assists` INT ZEROFILL NULL,
  `blocks` INT ZEROFILL NULL,
  `steals` INT ZEROFILL NULL,
  `turnovers` INT ZEROFILL NULL,
  `fouls` INT ZEROFILL NULL,
  `last_modified` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  INDEX `fk_player_stats_game1_idx` (`game_id` ASC),
  PRIMARY KEY (`player_id`),
  CONSTRAINT `fk_player_stats_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `ESAKE_Management_App`.`game` (`game_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_player_stats_player1`
    FOREIGN KEY (`player_id`)
    REFERENCES `ESAKE_Management_App`.`player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
