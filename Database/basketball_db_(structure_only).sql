-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Basketball_DB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Basketball_DB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Basketball_DB` DEFAULT CHARACTER SET utf8 ;
USE `Basketball_DB` ;

-- -----------------------------------------------------
-- Table `Basketball_DB`.`city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basketball_DB`.`city` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name_en` VARCHAR(32) NOT NULL,
  `name_gr` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Basketball_DB`.`team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basketball_DB`.`team` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `city_id` INT NOT NULL,
  `name_en` VARCHAR(45) NOT NULL,
  `name_gr` VARCHAR(45) NOT NULL,
  `short_name_en` VARCHAR(4) NOT NULL,
  `short_name_gr` VARCHAR(4) NOT NULL,
  `logo_path` VARCHAR(1024) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_team_city_idx` (`city_id` ASC),
  CONSTRAINT `fk_team_city`
    FOREIGN KEY (`city_id`)
    REFERENCES `Basketball_DB`.`city` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Basketball_DB`.`championship`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basketball_DB`.`championship` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Basketball_DB`.`player_position`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basketball_DB`.`player_position` (
  `code` VARCHAR(2) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Basketball_DB`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basketball_DB`.`user` (
  `username` VARCHAR(16) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`username`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Basketball_DB`.`round`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basketball_DB`.`round` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `championship_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_round_championship1_idx` (`championship_id` ASC),
  CONSTRAINT `fk_round_championship1`
    FOREIGN KEY (`championship_id`)
    REFERENCES `Basketball_DB`.`championship` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Basketball_DB`.`game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basketball_DB`.`game` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `championship_id` INT NOT NULL,
  `round_id` INT NOT NULL,
  `home_team_id` INT NOT NULL,
  `away_team_id` INT NOT NULL,
  `game_status` INT(2) NOT NULL,
  PRIMARY KEY (`id`, `championship_id`, `round_id`),
  INDEX `fk_game_round1_idx` (`round_id` ASC),
  INDEX `fk_game_championship1_idx` (`championship_id` ASC),
  INDEX `fk_game_team1_idx` (`home_team_id` ASC),
  INDEX `fk_game_team2_idx` (`away_team_id` ASC),
  CONSTRAINT `fk_game_round1`
    FOREIGN KEY (`round_id`)
    REFERENCES `Basketball_DB`.`round` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_championship1`
    FOREIGN KEY (`championship_id`)
    REFERENCES `Basketball_DB`.`championship` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_team1`
    FOREIGN KEY (`home_team_id`)
    REFERENCES `Basketball_DB`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_team2`
    FOREIGN KEY (`away_team_id`)
    REFERENCES `Basketball_DB`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Basketball_DB`.`player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basketball_DB`.`player` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `team_id` INT NOT NULL,
  `name_en` VARCHAR(64) NOT NULL,
  `surname_en` VARCHAR(64) NOT NULL,
  `name_gr` VARCHAR(64) NOT NULL,
  `surname_gr` VARCHAR(64) NOT NULL,
  `player_position_code` VARCHAR(2) NOT NULL,
  `img_path` VARCHAR(1024) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_player_team1_idx` (`team_id` ASC),
  INDEX `fk_player_player_position1_idx` (`player_position_code` ASC),
  CONSTRAINT `fk_player_team1`
    FOREIGN KEY (`team_id`)
    REFERENCES `Basketball_DB`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_player_player_position1`
    FOREIGN KEY (`player_position_code`)
    REFERENCES `Basketball_DB`.`player_position` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Basketball_DB`.`player_stats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basketball_DB`.`player_stats` (
  `championship_id` INT NOT NULL,
  `round_id` INT NOT NULL,
  `game_id` INT NOT NULL,
  `team_id` INT NOT NULL,
  `player_id` INT NOT NULL,
  `freethrows_in` INT NOT NULL DEFAULT 0,
  `freethrows_out` INT NOT NULL DEFAULT 0,
  `two_points_in` INT NOT NULL DEFAULT 0,
  `two_points_out` INT NOT NULL DEFAULT 0,
  `three_points_in` INT NOT NULL DEFAULT 0,
  `three_points_out` INT NOT NULL DEFAULT 0,
  `offensive_rebounds` INT NOT NULL DEFAULT 0,
  `defensive_rebounds` INT NOT NULL DEFAULT 0,
  `assists` INT NOT NULL DEFAULT 0,
  `blocks` INT NOT NULL DEFAULT 0,
  `steals` INT NOT NULL DEFAULT 0,
  `turnovers` INT NOT NULL DEFAULT 0,
  `fouls` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`championship_id`, `round_id`, `game_id`, `team_id`, `player_id`),
  INDEX `fk_player_stats_player1_idx` (`player_id` ASC),
  INDEX `fk_player_stats_team1_idx` (`team_id` ASC),
  INDEX `fk_player_stats_game1_idx` (`game_id` ASC, `championship_id` ASC, `round_id` ASC),
  CONSTRAINT `fk_player_stats_player1`
    FOREIGN KEY (`player_id`)
    REFERENCES `Basketball_DB`.`player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_player_stats_team1`
    FOREIGN KEY (`team_id`)
    REFERENCES `Basketball_DB`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_player_stats_game1`
    FOREIGN KEY (`game_id` , `championship_id` , `round_id`)
    REFERENCES `Basketball_DB`.`game` (`id` , `championship_id` , `round_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Basketball_DB`.`ongoing_game_player_stats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basketball_DB`.`ongoing_game_player_stats` (
  `championship_id` INT NOT NULL,
  `round_id` INT NOT NULL,
  `game_id` INT NOT NULL,
  `quarter` INT NOT NULL,
  `team_id` INT NOT NULL,
  `player_id` INT NOT NULL,
  `freethrows_in` INT NOT NULL DEFAULT 0,
  `freethrows_out` INT NOT NULL DEFAULT 0,
  `two_points_in` INT NOT NULL DEFAULT 0,
  `two_points_out` INT NOT NULL DEFAULT 0,
  `three_points_in` INT NOT NULL DEFAULT 0,
  `three_points_out` INT NOT NULL DEFAULT 0,
  `offensive_rebounds` INT NOT NULL DEFAULT 0,
  `defensive_rebounds` INT NOT NULL DEFAULT 0,
  `assists` INT NOT NULL DEFAULT 0,
  `blocks` INT NOT NULL DEFAULT 0,
  `steals` INT NOT NULL DEFAULT 0,
  `turnovers` INT NOT NULL DEFAULT 0,
  `fouls` INT NOT NULL DEFAULT 0,
  `last_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`championship_id`, `round_id`, `game_id`, `quarter`, `team_id`, `player_id`),
  INDEX `fk_ongoing_game_player_stats_player1_idx` (`player_id` ASC),
  INDEX `fk_ongoing_game_player_stats_team1_idx` (`team_id` ASC),
  INDEX `fk_ongoing_game_player_stats_game1_idx` (`game_id` ASC, `championship_id` ASC, `round_id` ASC),
  CONSTRAINT `fk_ongoing_game_player_stats_player1`
    FOREIGN KEY (`player_id`)
    REFERENCES `Basketball_DB`.`player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ongoing_game_player_stats_team1`
    FOREIGN KEY (`team_id`)
    REFERENCES `Basketball_DB`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ongoing_game_player_stats_game1`
    FOREIGN KEY (`game_id` , `championship_id` , `round_id`)
    REFERENCES `Basketball_DB`.`game` (`id` , `championship_id` , `round_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Basketball_DB`.`event_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basketball_DB`.`event_info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `template_text` VARCHAR(1024) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Basketball_DB`.`game_event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basketball_DB`.`game_event` (
  `championship_id` INT NOT NULL,
  `round_id` INT NOT NULL,
  `game_id` INT NOT NULL,
  `player_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  `minute` INT NOT NULL,
  `event_counter_in_game` INT NOT NULL,
  `parameters` VARCHAR(256) NULL,
  PRIMARY KEY (`championship_id`, `round_id`, `game_id`, `player_id`, `event_id`),
  INDEX `fk_game_event_player1_idx` (`player_id` ASC),
  INDEX `fk_game_event_event_info1_idx` (`event_id` ASC),
  INDEX `fk_game_event_game1_idx` (`game_id` ASC, `championship_id` ASC, `round_id` ASC),
  CONSTRAINT `fk_game_event_player1`
    FOREIGN KEY (`player_id`)
    REFERENCES `Basketball_DB`.`player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_event_event_info1`
    FOREIGN KEY (`event_id`)
    REFERENCES `Basketball_DB`.`event_info` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_event_game1`
    FOREIGN KEY (`game_id` , `championship_id` , `round_id`)
    REFERENCES `Basketball_DB`.`game` (`id` , `championship_id` , `round_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Basketball_DB`.`team_score_per_quarter`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Basketball_DB`.`team_score_per_quarter` (
  `championship_id` INT NOT NULL,
  `round_id` INT NOT NULL,
  `game_id` INT NOT NULL,
  `quarter` INT(1) NOT NULL,
  `team_id` INT NOT NULL,
  `quarter_score` INT NOT NULL,
  PRIMARY KEY (`championship_id`, `round_id`, `game_id`, `quarter`, `team_id`),
  INDEX `fk_team_score_per_quarter_team1_idx` (`team_id` ASC),
  INDEX `fk_team_score_per_quarter_game1_idx` (`game_id` ASC, `championship_id` ASC, `round_id` ASC),
  CONSTRAINT `fk_team_score_per_quarter_team1`
    FOREIGN KEY (`team_id`)
    REFERENCES `Basketball_DB`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_team_score_per_quarter_game1`
    FOREIGN KEY (`game_id` , `championship_id` , `round_id`)
    REFERENCES `Basketball_DB`.`game` (`id` , `championship_id` , `round_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
