-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 18, 2022 at 11:53 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `basketball_db`
--
CREATE DATABASE IF NOT EXISTS `basketball_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `basketball_db`;

-- --------------------------------------------------------

--
-- Table structure for table `championship`
--

CREATE TABLE `championship` (
  `id` int(11) NOT NULL,
  `name` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE `city` (
  `id` int(11) NOT NULL,
  `name_en` varchar(32) NOT NULL,
  `name_gr` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `event_info`
--

CREATE TABLE `event_info` (
  `id` int(11) NOT NULL,
  `template_text` varchar(1024) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE `game` (
  `id` int(11) NOT NULL,
  `championship_id` int(11) NOT NULL,
  `round_id` int(11) NOT NULL,
  `home_team_id` int(11) NOT NULL,
  `away_team_id` int(11) NOT NULL,
  `game_status` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `game_event`
--

CREATE TABLE `game_event` (
  `uid` int(11) NOT NULL,
  `championship_id` int(11) NOT NULL,
  `round_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `minute` int(11) NOT NULL,
  `additional_player_id` int(11) DEFAULT NULL,
  `last_modified` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ongoing_game_player_stats`
--

CREATE TABLE `ongoing_game_player_stats` (
  `championship_id` int(11) NOT NULL,
  `round_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `quarter` int(11) NOT NULL,
  `team_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  `freethrows_in` int(11) NOT NULL DEFAULT 0,
  `freethrows_out` int(11) NOT NULL DEFAULT 0,
  `two_points_in` int(11) NOT NULL DEFAULT 0,
  `two_points_out` int(11) NOT NULL DEFAULT 0,
  `three_points_in` int(11) NOT NULL DEFAULT 0,
  `three_points_out` int(11) NOT NULL DEFAULT 0,
  `offensive_rebounds` int(11) NOT NULL DEFAULT 0,
  `defensive_rebounds` int(11) NOT NULL DEFAULT 0,
  `assists` int(11) NOT NULL DEFAULT 0,
  `blocks` int(11) NOT NULL DEFAULT 0,
  `steals` int(11) NOT NULL DEFAULT 0,
  `turnovers` int(11) NOT NULL DEFAULT 0,
  `fouls` int(11) NOT NULL DEFAULT 0,
  `last_modified` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ongoing_match_time`
--

CREATE TABLE `ongoing_match_time` (
  `championship_id` int(11) NOT NULL,
  `round_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `current_minute` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `player`
--

CREATE TABLE `player` (
  `id` int(11) NOT NULL,
  `team_id` int(11) NOT NULL,
  `name_en` varchar(64) NOT NULL,
  `surname_en` varchar(64) NOT NULL,
  `name_gr` varchar(64) NOT NULL,
  `surname_gr` varchar(64) NOT NULL,
  `player_position_code` varchar(2) NOT NULL,
  `img_path` varchar(1024) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `player_position`
--

CREATE TABLE `player_position` (
  `code` varchar(2) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `player_stats`
--

CREATE TABLE `player_stats` (
  `championship_id` int(11) NOT NULL,
  `round_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `team_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  `freethrows_in` int(11) NOT NULL DEFAULT 0,
  `freethrows_out` int(11) NOT NULL DEFAULT 0,
  `two_points_in` int(11) NOT NULL DEFAULT 0,
  `two_points_out` int(11) NOT NULL DEFAULT 0,
  `three_points_in` int(11) NOT NULL DEFAULT 0,
  `three_points_out` int(11) NOT NULL DEFAULT 0,
  `offensive_rebounds` int(11) NOT NULL DEFAULT 0,
  `defensive_rebounds` int(11) NOT NULL DEFAULT 0,
  `assists` int(11) NOT NULL DEFAULT 0,
  `blocks` int(11) NOT NULL DEFAULT 0,
  `steals` int(11) NOT NULL DEFAULT 0,
  `turnovers` int(11) NOT NULL DEFAULT 0,
  `fouls` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `round`
--

CREATE TABLE `round` (
  `uid` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `championship_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `team`
--

CREATE TABLE `team` (
  `id` int(11) NOT NULL,
  `city_id` int(11) NOT NULL,
  `name_en` varchar(45) NOT NULL,
  `name_gr` varchar(45) NOT NULL,
  `short_name_en` varchar(4) NOT NULL,
  `short_name_gr` varchar(4) NOT NULL,
  `logo_path` varchar(1024) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `team_score_per_quarter`
--

CREATE TABLE `team_score_per_quarter` (
  `championship_id` int(11) NOT NULL,
  `round_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `quarter` int(1) NOT NULL,
  `team_id` int(11) NOT NULL,
  `quarter_score` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(16) NOT NULL,
  `password` varchar(64) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `championship`
--
ALTER TABLE `championship`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `event_info`
--
ALTER TABLE `event_info`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`id`,`championship_id`,`round_id`),
  ADD KEY `fk_game_round1_idx` (`round_id`),
  ADD KEY `fk_game_championship1_idx` (`championship_id`),
  ADD KEY `fk_game_team1_idx` (`home_team_id`),
  ADD KEY `fk_game_team2_idx` (`away_team_id`);

--
-- Indexes for table `game_event`
--
ALTER TABLE `game_event`
  ADD PRIMARY KEY (`uid`),
  ADD KEY `fk_game_event_player1_idx` (`player_id`),
  ADD KEY `fk_game_event_event_info1_idx` (`event_id`),
  ADD KEY `fk_game_event_game1_idx` (`game_id`,`championship_id`,`round_id`);

--
-- Indexes for table `ongoing_game_player_stats`
--
ALTER TABLE `ongoing_game_player_stats`
  ADD PRIMARY KEY (`championship_id`,`round_id`,`game_id`,`quarter`,`team_id`,`player_id`),
  ADD KEY `fk_ongoing_game_player_stats_player1_idx` (`player_id`),
  ADD KEY `fk_ongoing_game_player_stats_team1_idx` (`team_id`),
  ADD KEY `fk_ongoing_game_player_stats_game1_idx` (`game_id`,`championship_id`,`round_id`);

--
-- Indexes for table `ongoing_match_time`
--
ALTER TABLE `ongoing_match_time`
  ADD PRIMARY KEY (`championship_id`,`round_id`,`game_id`),
  ADD KEY `fk_round_id1` (`round_id`),
  ADD KEY `fk_game_id1` (`game_id`);

--
-- Indexes for table `player`
--
ALTER TABLE `player`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_player_team1_idx` (`team_id`),
  ADD KEY `fk_player_player_position1_idx` (`player_position_code`);

--
-- Indexes for table `player_position`
--
ALTER TABLE `player_position`
  ADD PRIMARY KEY (`code`);

--
-- Indexes for table `player_stats`
--
ALTER TABLE `player_stats`
  ADD PRIMARY KEY (`championship_id`,`round_id`,`game_id`,`team_id`,`player_id`),
  ADD KEY `fk_player_stats_player1_idx` (`player_id`),
  ADD KEY `fk_player_stats_team1_idx` (`team_id`),
  ADD KEY `fk_player_stats_game1_idx` (`game_id`,`championship_id`,`round_id`);

--
-- Indexes for table `round`
--
ALTER TABLE `round`
  ADD PRIMARY KEY (`uid`),
  ADD KEY `fk_round_championship1_idx` (`championship_id`);

--
-- Indexes for table `team`
--
ALTER TABLE `team`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_team_city_idx` (`city_id`);

--
-- Indexes for table `team_score_per_quarter`
--
ALTER TABLE `team_score_per_quarter`
  ADD PRIMARY KEY (`championship_id`,`round_id`,`game_id`,`quarter`,`team_id`),
  ADD KEY `fk_team_score_per_quarter_team1_idx` (`team_id`),
  ADD KEY `fk_team_score_per_quarter_game1_idx` (`game_id`,`championship_id`,`round_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `championship`
--
ALTER TABLE `championship`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `city`
--
ALTER TABLE `city`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `event_info`
--
ALTER TABLE `event_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `game`
--
ALTER TABLE `game`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `game_event`
--
ALTER TABLE `game_event`
  MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `player`
--
ALTER TABLE `player`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `round`
--
ALTER TABLE `round`
  MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `team`
--
ALTER TABLE `team`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `game`
--
ALTER TABLE `game`
  ADD CONSTRAINT `fk_game_championship1` FOREIGN KEY (`championship_id`) REFERENCES `championship` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_game_round1` FOREIGN KEY (`round_id`) REFERENCES `round` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_game_team1` FOREIGN KEY (`home_team_id`) REFERENCES `team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_game_team2` FOREIGN KEY (`away_team_id`) REFERENCES `team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `game_event`
--
ALTER TABLE `game_event`
  ADD CONSTRAINT `fk_game_event_event_info1` FOREIGN KEY (`event_id`) REFERENCES `event_info` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_game_event_game1` FOREIGN KEY (`game_id`,`championship_id`,`round_id`) REFERENCES `game` (`id`, `championship_id`, `round_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_game_event_player1` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `ongoing_game_player_stats`
--
ALTER TABLE `ongoing_game_player_stats`
  ADD CONSTRAINT `fk_ongoing_game_player_stats_game1` FOREIGN KEY (`game_id`,`championship_id`,`round_id`) REFERENCES `game` (`id`, `championship_id`, `round_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_ongoing_game_player_stats_player1` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_ongoing_game_player_stats_team1` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `ongoing_match_time`
--
ALTER TABLE `ongoing_match_time`
  ADD CONSTRAINT `fk_championship_id1` FOREIGN KEY (`championship_id`) REFERENCES `game` (`championship_id`),
  ADD CONSTRAINT `fk_game_id1` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`),
  ADD CONSTRAINT `fk_round_id1` FOREIGN KEY (`round_id`) REFERENCES `game` (`round_id`);

--
-- Constraints for table `player`
--
ALTER TABLE `player`
  ADD CONSTRAINT `fk_player_player_position1` FOREIGN KEY (`player_position_code`) REFERENCES `player_position` (`code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_player_team1` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `player_stats`
--
ALTER TABLE `player_stats`
  ADD CONSTRAINT `fk_player_stats_game1` FOREIGN KEY (`game_id`,`championship_id`,`round_id`) REFERENCES `game` (`id`, `championship_id`, `round_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_player_stats_player1` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_player_stats_team1` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `round`
--
ALTER TABLE `round`
  ADD CONSTRAINT `fk_round_championship1` FOREIGN KEY (`championship_id`) REFERENCES `championship` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `team`
--
ALTER TABLE `team`
  ADD CONSTRAINT `fk_team_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `team_score_per_quarter`
--
ALTER TABLE `team_score_per_quarter`
  ADD CONSTRAINT `fk_team_score_per_quarter_game1` FOREIGN KEY (`game_id`,`championship_id`,`round_id`) REFERENCES `game` (`id`, `championship_id`, `round_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_team_score_per_quarter_team1` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
