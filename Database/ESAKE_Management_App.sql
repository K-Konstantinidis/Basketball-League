-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 25, 2022 at 11:58 AM
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
-- Database: `esake_management_app`
--
CREATE DATABASE IF NOT EXISTS `esake_management_app` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `esake_management_app`;

-- --------------------------------------------------------

--
-- Table structure for table `championship`
--

CREATE TABLE `championship` (
  `id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `championship`
--

INSERT INTO `championship` (`id`, `name`) VALUES
(1, 'Esake');

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE `city` (
  `id` int(11) NOT NULL,
  `name_en` varchar(32) DEFAULT NULL,
  `name_gr` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `city`
--

INSERT INTO `city` (`id`, `name_en`, `name_gr`) VALUES
(1, 'PIRAEUS', 'ΠΕΙΡΑΙΑΣ'),
(2, 'ATHENS', 'ΑΘΗΝΑ'),
(3, 'THESSALONIKI', 'ΘΕΣΣΑΛΟΝΙΚΗ'),
(4, 'PATRA', 'ΠΑΤΡΑ'),
(5, 'LARISSA', 'ΛΑΡΙΣΑ'),
(6, 'LAVRIO', 'ΛΑΥΡΙΟ'),
(7, 'PIRAEUS', 'ΠΕΙΡΑΙΑΣ'),
(8, 'ATHENS', 'ΑΘΗΝΑ'),
(9, 'THESSALONIKI', 'ΘΕΣΣΑΛΟΝΙΚΗ'),
(10, 'PATRA', 'ΠΑΤΡΑ'),
(11, 'LARISSA', 'ΛΑΡΙΣΑ'),
(12, 'LAVRIO', 'ΛΑΥΡΙΟ');

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE `game` (
  `game_id` int(11) NOT NULL,
  `round_id` int(11) NOT NULL,
  `championship_id` int(11) NOT NULL,
  `home_team_id` int(11) NOT NULL,
  `away_team_id` int(11) NOT NULL,
  `last_modified` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `game`
--

INSERT INTO `game` (`game_id`, `round_id`, `championship_id`, `home_team_id`, `away_team_id`, `last_modified`) VALUES
(1, 1, 1, 3, 4, '2022-05-25 09:34:59'),
(1, 2, 1, 10, 3, '2022-05-25 09:35:58'),
(1, 3, 1, 10, 2, '2022-05-25 09:35:58'),
(1, 4, 1, 5, 2, '2022-05-25 09:35:58'),
(1, 5, 1, 7, 3, '2022-05-25 09:35:58'),
(1, 6, 1, 6, 9, '2022-05-25 09:35:58'),
(1, 7, 1, 7, 6, '2022-05-25 09:35:58'),
(1, 8, 1, 2, 1, '2022-05-25 09:35:58'),
(1, 9, 1, 1, 7, '2022-05-25 09:35:58'),
(1, 10, 1, 4, 3, '2022-05-25 09:35:58'),
(1, 11, 1, 3, 10, '2022-05-25 09:35:58'),
(1, 12, 1, 2, 10, '2022-05-25 09:35:58'),
(1, 13, 1, 2, 5, '2022-05-25 09:35:58'),
(1, 14, 1, 3, 7, '2022-05-25 09:35:58'),
(1, 15, 1, 9, 6, '2022-05-25 09:35:58'),
(1, 16, 1, 6, 7, '2022-05-25 09:35:58'),
(1, 17, 1, 1, 2, '2022-05-25 09:35:58'),
(1, 18, 1, 7, 1, '2022-05-25 09:35:58'),
(2, 1, 1, 5, 8, '2022-05-25 09:35:27'),
(2, 2, 1, 8, 7, '2022-05-25 09:35:58'),
(2, 3, 1, 7, 9, '2022-05-25 09:35:58'),
(2, 4, 1, 3, 6, '2022-05-25 09:35:58'),
(2, 5, 1, 2, 4, '2022-05-25 09:35:58'),
(2, 6, 1, 1, 8, '2022-05-25 09:35:58'),
(2, 7, 1, 10, 8, '2022-05-25 09:35:58'),
(2, 8, 1, 3, 8, '2022-05-25 09:35:58'),
(2, 9, 1, 6, 10, '2022-05-25 09:35:58'),
(2, 10, 1, 8, 5, '2022-05-25 09:35:58'),
(2, 11, 1, 7, 8, '2022-05-25 09:35:58'),
(2, 12, 1, 9, 7, '2022-05-25 09:35:58'),
(2, 13, 1, 6, 3, '2022-05-25 09:35:58'),
(2, 14, 1, 4, 2, '2022-05-25 09:35:58'),
(2, 15, 1, 8, 1, '2022-05-25 09:35:58'),
(2, 16, 1, 8, 10, '2022-05-25 09:35:58'),
(2, 17, 1, 8, 3, '2022-05-25 09:35:58'),
(2, 18, 1, 10, 6, '2022-05-25 09:35:58'),
(3, 1, 1, 6, 1, '2022-05-25 09:35:29'),
(3, 2, 1, 6, 2, '2022-05-25 09:35:58'),
(3, 3, 1, 5, 6, '2022-05-25 09:35:58'),
(3, 4, 1, 10, 1, '2022-05-25 09:35:58'),
(3, 5, 1, 9, 1, '2022-05-25 09:35:58'),
(3, 6, 1, 3, 2, '2022-05-25 09:35:58'),
(3, 7, 1, 9, 2, '2022-05-25 09:35:58'),
(3, 8, 1, 9, 5, '2022-05-25 09:35:58'),
(3, 9, 1, 9, 3, '2022-05-25 09:35:58'),
(3, 10, 1, 1, 6, '2022-05-25 09:35:58'),
(3, 11, 1, 2, 6, '2022-05-25 09:35:58'),
(3, 12, 1, 6, 5, '2022-05-25 09:35:58'),
(3, 13, 1, 1, 10, '2022-05-25 09:35:58'),
(3, 14, 1, 1, 9, '2022-05-25 09:35:58'),
(3, 15, 1, 2, 3, '2022-05-25 09:35:58'),
(3, 16, 1, 2, 9, '2022-05-25 09:35:58'),
(3, 17, 1, 5, 9, '2022-05-25 09:35:58'),
(3, 18, 1, 3, 9, '2022-05-25 09:35:58'),
(4, 1, 1, 7, 2, '2022-05-25 09:35:30'),
(4, 2, 1, 1, 5, '2022-05-25 09:35:58'),
(4, 3, 1, 8, 4, '2022-05-25 09:35:58'),
(4, 4, 1, 9, 8, '2022-05-25 09:35:58'),
(4, 5, 1, 5, 10, '2022-05-25 09:35:58'),
(4, 6, 1, 4, 10, '2022-05-25 09:35:58'),
(4, 7, 1, 5, 3, '2022-05-25 09:35:58'),
(4, 8, 1, 4, 6, '2022-05-25 09:35:58'),
(4, 9, 1, 8, 2, '2022-05-25 09:35:58'),
(4, 10, 1, 2, 7, '2022-05-25 09:35:58'),
(4, 11, 1, 5, 1, '2022-05-25 09:35:58'),
(4, 12, 1, 4, 8, '2022-05-25 09:35:58'),
(4, 13, 1, 8, 9, '2022-05-25 09:35:58'),
(4, 14, 1, 10, 5, '2022-05-25 09:35:58'),
(4, 15, 1, 10, 4, '2022-05-25 09:35:58'),
(4, 16, 1, 3, 5, '2022-05-25 09:35:58'),
(4, 17, 1, 6, 4, '2022-05-25 09:35:58'),
(4, 18, 1, 2, 8, '2022-05-25 09:35:58'),
(5, 1, 1, 9, 10, '2022-05-25 09:35:31'),
(5, 2, 1, 4, 9, '2022-05-25 09:35:58'),
(5, 3, 1, 1, 3, '2022-05-25 09:35:58'),
(5, 4, 1, 7, 4, '2022-05-25 09:35:58'),
(5, 5, 1, 6, 8, '2022-05-25 09:35:58'),
(5, 6, 1, 7, 5, '2022-05-25 09:35:58'),
(5, 7, 1, 1, 4, '2022-05-25 09:35:58'),
(5, 8, 1, 10, 7, '2022-05-25 09:35:58'),
(5, 9, 1, 4, 5, '2022-05-25 09:35:58'),
(5, 10, 1, 10, 9, '2022-05-25 09:35:58'),
(5, 11, 1, 9, 4, '2022-05-25 09:35:58'),
(5, 12, 1, 3, 1, '2022-05-25 09:35:58'),
(5, 13, 1, 4, 7, '2022-05-25 09:35:58'),
(5, 14, 1, 8, 6, '2022-05-25 09:35:58'),
(5, 15, 1, 5, 7, '2022-05-25 09:35:58'),
(5, 16, 1, 4, 1, '2022-05-25 09:35:58'),
(5, 17, 1, 7, 10, '2022-05-25 09:35:58'),
(5, 18, 1, 5, 4, '2022-05-25 09:35:58');

-- --------------------------------------------------------

--
-- Table structure for table `player`
--

CREATE TABLE `player` (
  `id` int(11) NOT NULL,
  `name_en` varchar(64) DEFAULT NULL,
  `surname_en` varchar(64) DEFAULT NULL,
  `name_gr` varchar(64) DEFAULT NULL,
  `surname_gr` varchar(64) DEFAULT NULL,
  `team_id` int(11) NOT NULL,
  `player_position_code` varchar(4) NOT NULL,
  `img_path` varchar(1024) DEFAULT NULL,
  `number` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `player`
--

INSERT INTO `player` (`id`, `name_en`, `surname_en`, `name_gr`, `surname_gr`, `team_id`, `player_position_code`, `img_path`, `number`) VALUES
(1, 'KOSTAS', 'SLOUKAS', 'ΚΩΣΤΑΣ', 'ΣΛΟΥΚΑΣ', 1, 'PG', '......1', NULL),
(2, 'THOMAS', 'WALKUP', 'ΤΟΜΑΣ', 'ΓΟΥΟΚΑΠ ', 1, 'PG', '......2', NULL),
(3, 'TYLER', 'DORSEY', 'ΤΑΙΡΕΛ', 'ΝΤΟΡΣΕΪ ', 1, 'SG', '......3', NULL),
(4, 'MICHAEL', 'LOUNTZIS', 'ΜΙΧΑΛΗΣ', 'ΛΟΥΝΤΖΗΣ ', 1, 'SG', '......4', NULL),
(5, 'GIANNOULIS', 'LARENTZAKIS', 'ΓΙΑΝΝΟΥΛΗΣ', 'ΛΑΡΕΝΤΖΑΚΗΣ', 1, 'SG', '......5', NULL),
(6, 'KOSTAS', 'PAPANIKOLAOY', 'ΚΩΣΤΑΣ', 'ΠΑΠΑΝΙΚΟΛΑΟΥ', 1, 'SF', '......6', NULL),
(7, 'SHAQUIELLE', 'MCKISSIC', 'ΣΑΚΙΛ', 'ΜΑΚ ΚΙΣΙΚ', 1, 'SF', '......7', NULL),
(8, 'SASHA', 'VEZENKOV', 'ΑΛΕΞΑΝΔΡΟΣ', 'ΒΕΖΕΝΚΟΦ', 1, 'PF', '......8', NULL),
(9, 'GIORGOS', 'PRINTEZIS', 'ΓΙΩΡΓΟΣ', 'ΠΡΙΝΤΕΖΗΣ', 1, 'PF', '......9', NULL),
(10, 'MOUSTAPHA', 'FALL', 'ΜΟΥΣΤΑΦΑ', 'ΦΑΛ', 1, 'C', '......10', NULL),
(11, 'HASSAN', 'MARTIN', 'ΧΑΣΑΝ', 'ΜΑΡΤΙΝ', 1, 'C', '......11', NULL),
(12, 'STAVROS', 'SARANTONIDIS', 'ΣΤΑΥΡΟΣ', 'ΣΑΡΑΝΤΩΝΙΔΗΣ', 1, 'C', '......12', NULL),
(13, 'KEITH', 'LANGFORD', 'ΚΙΘ', 'ΛΑΝΓΚΦΟΡΝΤ', 2, 'PG', '......13', NULL),
(14, 'ANTONIS', 'KONIARIS', 'ΑΝΤΩΝΗΣ', 'ΚΟΝΙΑΡΗΣ', 2, 'PG', '......14', NULL),
(15, 'NIKOS', 'PAPPAS', 'ΝΙΚΟΣ', 'ΠΑΠΠΑΣ', 2, 'PG', '......15', NULL),
(16, 'QUINO', 'COLOM', 'ΚΙΝΟ', 'ΚΟΛΟΜ', 2, 'SG', '......16', NULL),
(17, 'ANDY', 'RAUTINS', 'ΑΝΤΙ', 'ΡΑΟΥΤΙΝΣ', 2, 'SG', '......17', NULL),
(18, 'BRAIAN', 'ANGOLA', 'ΜΠΡΑΙΑΝ', 'ΑΝΓΚΟΛΑ', 2, 'SF', '......18', NULL),
(19, 'PANAGIOTIS', 'FILIPPAKOS', 'ΠΑΝΑΓΙΩΤΗΣ', 'ΦΙΛΙΠΠΑΚΟΣ', 2, 'SF', '......19', NULL),
(20, 'GIANNIS', 'KOUZELOGLOY', 'ΓΙΑΝΝΗΣ', 'ΚΟΥΖΕΛΟΓΛΟΥ', 2, 'PF', '......20', NULL),
(21, 'IAN', 'HUMMER', 'ΙΑΝ', 'ΧΑΜΕΡ', 2, 'PF', '......21', NULL),
(22, 'ERIC', 'GRIFFIN', 'ΕΡΙΚ', 'ΓΚΡΙΦΙΝ', 2, 'C', '......22', NULL),
(23, 'DIMITRIS', 'MAVROEIDIS', 'ΔΗΜΗΤΡΗΣ', 'ΜΑΥΡΟΕΙΔΗΣ', 2, 'C', '......23', NULL),
(24, 'ODISSEAS', 'MOUZOURAKIS', 'ΟΔΥΣΣΕΑΣ', 'ΜΟΥΖΟΥΡΑΚΗΣ', 2, 'C', '......24', NULL),
(25, 'PHIL', 'GREENE', 'ΦΙΛ', 'ΓΚΡΙΝ', 3, 'PG', '......25', NULL),
(26, 'STAVROS', 'VARGEMEZIS', 'ΣΤΑΥΡΟΣ', 'ΒΑΡΓΕΜΕΖΗΣ', 3, 'PG', '......26', NULL),
(27, 'VAGGELIS', 'MANTZARIS', 'ΒΑΓΓΕΛΗΣ', 'ΜΑΝΤΖΑΡΗΣ', 3, 'PG', '......27', NULL),
(28, 'JERMAINE', 'LOVE', 'ΖΕΡΜΕΙΝ', 'ΛΟΒ', 3, 'SG', '......28', NULL),
(29, 'THEODOROS', 'KONSTANIDIS', 'ΘΕΟΔΩΡΟΣ', 'ΚΩΝΣΤΑΝΤΙΝΙΔΗΣ', 3, 'SG', '......29', NULL),
(30, 'DAVID', 'DILEO', 'ΝΤΕΙΒΙΝΤ', 'ΝΤΙ ΛΕΟ', 3, 'SF', '......30', NULL),
(31, 'NIKOS', 'KAMARIANOS', 'ΝΙΚΟΣ', 'ΚΑΜΑΡΙΑΝΟΣ', 3, 'SF', '......31', NULL),
(32, 'VLADIMIROS', 'JANKOVIC', 'ΒΛΑΔΙΜΗΡΟΣ', 'ΓΙΑΝΚΟΒΙΤΣ', 3, 'PF', '......32', NULL),
(33, 'DEMETRE', 'RIVERS', 'ΝΤΙΜΙΤΡΙ', 'ΡΙΒΕΡΣ', 3, 'PF', '......33', NULL),
(34, 'ANTONY', 'LEE ', 'ΑΝΤΟΝΙ', 'ΛΙ ', 3, 'C', '......34', NULL),
(35, 'STRATOS', 'KALLIONTZIS', 'ΣΤΡΑΤΟΣ', 'ΚΑΛΛΙΟΝΤΖΗΣ', 3, 'C', '......35', NULL),
(36, 'NATE', 'RENFRO', 'ΝΕΙΤ', 'ΡΕΝΦΡΟ', 3, 'C', '......36', NULL),
(37, 'ANTONY', 'COWAN', 'ΑΝΤΟΝΙ', 'ΚΑΟΥΑΝ', 4, 'PG', '......37', NULL),
(38, 'OMIROS', 'NETZIPOGLOY', 'ΟΜΗΡΟΣ', 'ΝΕΤΖΗΠΟΓΛΟΥ', 4, 'PG', '......38', NULL),
(39, 'STELIOS', 'POYLIANTIS', 'ΣΤΕΛΙΟΣ', 'ΠΟΥΛΙΑΝΙΤΗΣ', 4, 'PG', '......39', NULL),
(40, 'OLIVIER ', 'HANLAN', 'ΟΛΙΒΙΕ', 'ΧΑΝΛΑΝ', 4, 'SG', '......40', NULL),
(41, 'STAVROS', 'SXIZAS', 'ΣΤΑΥΡΟΣ', 'ΣΧΙΖΑΣ', 4, 'SG', '......41', NULL),
(42, 'ERIC', 'LOCKETT', 'ΕΡΙΚ', 'ΛΟΚΕΤ', 4, 'SF', '......42', NULL),
(43, 'GIANNIS', 'SIDIROILIAS', 'ΓΙΑΝΝΗΣ ', 'ΣΙΔΗΡΟΗΛΙΑΣ', 4, 'SF', '......43', NULL),
(44, 'XEYRIUS', 'WILLIAMS', 'ΖΙΡΙΟΥΣ', 'ΟΥΙΛΙΑΜΣ', 4, 'PF', '......44', NULL),
(45, 'KYRIAKOS', 'PETANIDIS', 'ΚΥΡΙΑΚΟΣ ', 'ΠΕΤΑΝΙΔΗΣ', 4, 'PF', '......45', NULL),
(46, 'SHAKUR ', 'JUISTON', 'ΣΑΚΟΥΡ', 'ΤΖΟΥΣΤΟΝ', 4, 'C', '......46', NULL),
(47, 'THOMAS', 'KOTTAS', 'ΘΩΜΑΣ', 'ΚΩΤΤΑΣ', 4, 'C', '......47', NULL),
(48, 'GIORGOS', 'KARSLIDIS', 'ΓΙΩΡΓΟΣ', 'ΚΑΡΣΛΙΔΗΣ', 4, 'C', '......48', NULL),
(49, 'NIKOS', 'TSIAKMAS', 'ΝΙΚΟΣ', 'ΤΣΙΑΚΜΑΣ', 5, 'PG', '......49', NULL),
(50, 'NIKOS', 'DIPLAROS', 'ΝΙΚΟΣ', 'ΔΙΠΛΑΡΟΣ', 5, 'PG', '......50', NULL),
(51, 'ELAIJA', 'MITROU-LONG', 'ΕΛΑΙΖΑ', 'ΜΗΤΡΟΥ-ΛΟΝΓΚ', 5, 'PG', '......51', NULL),
(52, 'FOTIS', 'ZOUMPOS', 'ΦΩΤΗΣ', 'ΖΟΥΜΠΟΣ', 5, 'SG', '......52', NULL),
(53, 'GIANNIS', 'MOLFETAS', 'ΓΙΑΝΝΗΣ', 'ΜΟΛΦΕΤΑΣ', 5, 'SG', '......53', NULL),
(54, 'FOTIS', 'PAPAFOTIOY', 'ΦΩΤΗΣ', 'ΠΑΠΑΦΩΤΙΟΥ', 5, 'SF', '......54', NULL),
(55, 'TRE', 'MCLIN', 'ΤΡΕ', 'ΜΑΚΛΙΝ', 5, 'SF', '......55', NULL),
(56, 'GIORGOS', 'KOGIONIS', 'ΓΙΩΡΓΟΣ', 'ΚΟΓΙΩΝΗΣ', 5, 'PF', '......56', NULL),
(57, 'DEZON', 'DAVIS', 'ΝΤΕΖΟΝ', 'ΝΤΕΙΒΙΣ', 5, 'PF', '......57', NULL),
(58, 'GIORGOS', 'TSALMPOURIS', 'ΓΙΩΡΓΟΣ', 'ΤΣΑΛΜΠΟΥΡΗΣ', 5, 'C', '......58', NULL),
(59, 'DIMITRIS', 'KARADIMAS', 'ΔΗΜΗΤΡΗΣ', 'ΚΑΡΑΔΗΜΑΣ', 5, 'C', '......59', NULL),
(60, 'GIORGOS', 'BOGRIS', 'ΓΙΩΡΓΟΣ', 'ΜΠΟΓΡΗΣ', 5, 'C', '......60', NULL),
(61, 'LEFTERIS', 'BOHORIDIS', 'ΛΕΥΤΕΡΗΣ', 'ΜΠΟΧΩΡΙΔΗΣ', 6, 'PG', '......61', NULL),
(62, 'NEOKLIS', 'AVDALAS', 'ΝΕΟΚΛΗΣ', 'ΑΒΔΑΛΑΣ', 6, 'PG', '......62', NULL),
(63, 'NTARIL', 'MEIKON', 'ΝΤΑΡΙΛ', 'ΜΕΪΚΟΝ ', 6, 'SG', '......63', NULL),
(64, 'NEMANJA', 'NEDOVIC', 'ΝΕΜΑΝΙΑ', 'ΝΕΝΤΟΒΙΤΣ', 6, 'SG', '......64', NULL),
(65, 'HOWARD', 'SANT-ROS', 'ΧΑΟΥΑΡΝΤ', 'ΣΑΝΤ-ΡΟΣ', 6, 'SG', '......65', NULL),
(66, 'IOANNIS', 'PAPAPETROY', 'ΙΩΑΝΝΗΣ', 'ΠΑΠΑΠΕΤΡΟΥ', 6, 'SF', '......66', NULL),
(67, 'LEONIDAS', 'KASELAKIS', 'ΛΕΩΝΙΔΑΣ', 'ΚΑΣΕΛΑΚΗΣ', 6, 'SF', '......67', NULL),
(68, 'LEFTERIS', 'MANTZOUKAS', 'ΛΕΥΤΕΡΗΣ', 'ΜΑΝΤΖΟΥΚΑΣ', 6, 'SF', '......68', NULL),
(69, 'OKARO', 'WHITE', 'ΟΚΑΡΟ', 'ΟΥΑΪΤ', 6, 'PF', '......69', NULL),
(70, 'JEREMY', 'EVANS', 'ΤΖΕΡΕΜΙ', 'ΕΒΑΝΣ', 6, 'PF', '......70', NULL),
(71, 'GIORGOS', 'PAPAGIANNIS', 'ΓΙΩΡΓΟΣ', 'ΠΑΠΑΓΙΑΝΝΗΣ', 6, 'C', '......71', NULL),
(72, 'VASILIS', 'KAVVADAS', 'ΒΑΣΙΛΗΣ', 'ΚΑΒΒΑΔΑΣ', 6, 'C', '......72', NULL),
(73, 'STEFAN', 'MOODY', 'ΣΤΕΦΑΝ', 'ΜΟΥΝΤΙ', 7, 'PG', '......73', NULL),
(74, 'DEVONTE', 'GREEN', 'ΝΤΕΒΟΝΤΕ', 'ΓΚΡΙΝ', 7, 'PG', '......74', NULL),
(75, 'ZOIS', 'KARAMPELAS', 'ΖΩΗΣ', 'ΚΑΡΑΜΠΕΛΑΣ', 7, 'PG', '......75', NULL),
(76, 'IOANNIS', 'KOSTIDIS', 'ΓΙΑΝΝΗΣ', 'ΚΩΣΤΙΔΗΣ', 7, 'SG', '......76', NULL),
(77, 'TILEMACHOS', 'VISSARIOY', 'ΤΗΛΕΜΑΧΟΣ', 'ΒΗΣΣΑΡΙΟΥ', 7, 'SG', '......77', NULL),
(78, 'ASTERIOS', 'ASTERIADIS', 'ΑΣΤΕΡΙΟΣ', 'ΑΣΤΕΡΙΑΔΗΣ', 7, 'SG', '......78', NULL),
(79, 'JALEN', 'HUDSON', 'ΤΖΕΙΛΕΝ', 'ΧΑΝΤΣΟΝ', 7, 'SF', '......79', NULL),
(80, 'OUSMAN', 'KRUBALLY', 'ΟΥΣΜΑΝ', 'ΚΡΟΥΜΠΑΛΙ', 7, 'SF', '......80', NULL),
(81, 'ANASTASIOS', 'SPIROPOULOS', 'ΑΝΑΣΤΑΣΙΟΣ', 'ΣΠΥΡΟΠΟΥΛΟΣ', 7, 'PF', '......81', NULL),
(82, 'JANIS', 'BERZINS', 'ΓΙΑΝΙΣ', 'ΜΠΕΡΖΙΝΣ', 7, 'PF', '......82', NULL),
(83, 'MICHAEL', 'TSAIRELIS', 'ΜΙΧΑΛΗΣ', 'ΤΣΑΪΡΕΛΗΣ', 7, 'C', '......83', NULL),
(84, 'MARIN', 'MARIC', 'ΜΑΡΙΝ', 'ΜΑΡΙΤΣ', 7, 'C', '......84', NULL),
(85, 'NIKOS', 'GKIKAS', 'ΝΙΚΟΣ', 'ΓΚΙΚΑΣ', 8, 'PG', '......85', NULL),
(86, 'KENDRICK', 'RAY', 'ΚΕΝΤΡΙΚ', 'ΡΕΙ', 8, 'PG', '......86', NULL),
(87, 'MARCUS', 'FOSTER', 'ΜΑΡΚΟΥΣ', 'ΦΟΣΤΕΡ', 8, 'PG', '......87', NULL),
(88, 'NIKOS', 'ROGKAVOPOULOS', 'ΝΙΚΟΣ', 'ΡΟΓΚΑΒΟΠΟΥΛΟΣ   ', 8, 'SG', '......88', NULL),
(89, 'TREVIS', 'SIMPSON', 'ΤΡΕΒΙΣ', 'ΣΙΜΠΣΟΝ', 8, 'SG', '......89', NULL),
(90, 'IOSIF', 'SAAD', 'ΙΩΣΗΦ', 'ΣΑΑΝΤ', 8, 'SG', '......90', NULL),
(91, 'MIHAJLO', 'ANDRIC', 'ΜΙΧΑΙΛΟ', 'ΑΝΤΡΙΤΣ', 8, 'SF', '......91', NULL),
(92, 'GIANNIS', 'AGRAVANIS', 'ΓΙΑΝΝΗΣ', 'ΑΓΡΑΒΑΝΗΣ', 8, 'SF', '......92', NULL),
(93, 'JERAI', 'GRANT', 'ΤΖΕΡΑΙ', 'ΓΚΡΑΝΤ', 8, 'PF', '......93', NULL),
(94, 'GIORGOS', 'TANOULIS', 'ΓΙΩΡΓΟΣ', 'ΤΑΝΟΥΛΗΣ', 8, 'PF', '......94', NULL),
(95, 'DIMITRIS', 'AGRAVANIS', 'ΔΗΜΗΤΡΗΣ', 'ΑΓΡΑΒΑΝΗΣ', 8, 'C', '......95', NULL),
(96, 'DARIO', 'HUNT', 'ΝΤΑΡΙΟ', 'ΧΑΝΤ', 8, 'C', '......96', NULL),
(97, 'ALEXANDROS', 'NIKOLAIDIS', 'ΑΛΕΞΑΝΔΡΟΣ', 'ΝΙΚΟΛΑΙΔΗΣ', 9, 'PG', '......97', NULL),
(98, 'VASILIS', 'MOURATOS', 'ΒΑΣΙΛΗΣ', 'ΜΟΥΡΑΤΟΣ', 9, 'PG', '......98', NULL),
(99, 'KOSTAS', 'KONSTANTINIDIS', 'ΚΩΣΤΑΣ', 'KΩΝΣΤΑΝΤΙΝΙΔΗΣ', 9, 'PG', '......99', NULL),
(100, 'MATT', 'WILLIAMS', 'ΜΑΤ ΤΖΟΥΝΙΟΡ', 'ΟΥΙΛΙΑΜΣ', 9, 'SG', '......100', NULL),
(101, 'NIKOS', 'PERSIDIS', 'ΝΙΚΟΣ', 'ΠΕΡΣΙΔΗΣ', 9, 'SG', '......101', NULL),
(102, 'PETROS', 'GEROMIHALOS', 'ΠΕΤΡΟΣ', 'ΓΕΡΟΜΙΧΑΛΟΣ', 9, 'SF', '......102', NULL),
(103, 'ALFIS', 'PILAVIOS', 'ΑΛΦΗΣ', 'ΠΙΛΑΒΙΟΣ', 9, 'SF', '......103', NULL),
(104, 'JARED', 'SAVAGE', 'ΤΖΑΡΕΝΤ', 'ΣΑΒΑΤΖ', 9, 'PF', '......104', NULL),
(105, 'MICHAEL', 'SPRINTZIOS', 'ΜΙΧΑΛΗΣ', 'ΣΠΡΙΝΤΖΙΟΣ', 9, 'PF', '......105', NULL),
(106, 'DIMITRIS', 'KAKLAMANAKIS', 'ΔΗΜΗΤΡΗΣ', 'ΚΑΚΛΑΜΑΝΑΚΗΣ', 9, 'C', '......106', NULL),
(107, 'STRATOS', 'VOULGAROPOULOS', 'ΣΤΡΑΤΟΣ', 'ΒΟΥΛΓΑΡΟΠΟΥΛΟΣ', 9, 'C', '......107', NULL),
(108, 'AL-WAJID', 'AMINU', 'ΑΛ ΟΥΑΖΙΝΤ', 'ΑΜΙΝΟΥ', 9, 'C', '......108', NULL),
(109, 'GLEN', 'KOZI', 'ΓΚΛΕΝ', 'ΚΟΖΙ', 10, 'PG', '......109', NULL),
(110, 'DIMITRIS', 'MORAITIS', 'ΔΗΜΗΤΡΗΣ', 'ΜΩΡΑΙΤΗΣ', 10, 'PG', '......110', NULL),
(111, 'DIMITRIS', 'KATSIVELIS', 'ΔΗΜΗΤΡΗΣ', 'ΚΑΤΣΙΒΕΛΗΣ ', 10, 'SG', '......111', NULL),
(112, 'STEVEN', 'GRAY', 'ΣΤΙΒΕΝ', 'ΓΚΡΕΙ', 10, 'SG', '......112', NULL),
(113, 'TERAN', 'PETEWAY', 'ΤΕΡΑΝ', 'ΠΕΤΕΓΟΥΕΙ', 10, 'SF', '......113', NULL),
(114, 'CHRISTOS', 'SALOUSTROS', 'ΧΡΗΣΤΟΣ', 'ΣΑΛΟΥΣΤΡΟΣ', 10, 'SF', '......114', NULL),
(115, 'HARIS', 'GIANNOPOULOS', 'ΧΑΡΗΣ', 'ΓΙΑΝΝΟΠΟΥΛΟΣ', 10, 'SF', '......115', NULL),
(116, 'LINOS', 'CHRYSIKOPOULOS', 'ΛΙΝΟΣ', 'ΧΡΥΣΙΚΟΠΟΥΛΟΣ', 10, 'PF', '......116', NULL),
(117, 'TOMASZ', 'GIELO', 'ΤΟΜΑΣ', 'ΓΚΙΕΛΟ', 10, 'PF', '......117', NULL),
(118, 'DANNY', 'AGBELESE', 'ΝΤΑΝΙ', 'ΑΓΚΜΠΕΛΙΣ', 10, 'C', '......118', NULL),
(119, 'VAGGELIS', 'DAVIOS', 'ΒΑΓΓΕΛΗΣ', 'ΝΤΑΒΙΟΣ', 10, 'C', '......119', NULL),
(120, 'CHAD', 'BROWN', 'ΤΣΑΝΤ', 'ΜΠΡΑΟΥΝ', 10, 'C', '......120', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `player_position`
--

CREATE TABLE `player_position` (
  `position_code` varchar(2) NOT NULL,
  `position_name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `player_position`
--

INSERT INTO `player_position` (`position_code`, `position_name`) VALUES
('C', 'CENTER'),
('PF', 'POWER FORWARD'),
('PG', 'POINT GUARD'),
('SF', 'SMALL FORWARD'),
('SG', 'SHOOTING GUARD');

-- --------------------------------------------------------

--
-- Table structure for table `player_stats`
--

CREATE TABLE `player_stats` (
  `player_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `freethrows_in` int(10) UNSIGNED ZEROFILL DEFAULT NULL,
  `freethrows_out` int(10) UNSIGNED ZEROFILL DEFAULT NULL,
  `two_points_in` int(10) UNSIGNED ZEROFILL DEFAULT NULL,
  `two_points_out` int(10) UNSIGNED ZEROFILL DEFAULT NULL,
  `three_points_in` int(10) UNSIGNED ZEROFILL DEFAULT NULL,
  `three_points_out` int(10) UNSIGNED ZEROFILL DEFAULT NULL,
  `offensive_rebounds` int(10) UNSIGNED ZEROFILL DEFAULT NULL,
  `defensive_rebounds` int(10) UNSIGNED ZEROFILL DEFAULT NULL,
  `assists` int(10) UNSIGNED ZEROFILL DEFAULT NULL,
  `blocks` int(10) UNSIGNED ZEROFILL DEFAULT NULL,
  `steals` int(10) UNSIGNED ZEROFILL DEFAULT NULL,
  `turnovers` int(10) UNSIGNED ZEROFILL DEFAULT NULL,
  `fouls` int(10) UNSIGNED ZEROFILL DEFAULT NULL,
  `last_modified` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `round`
--

CREATE TABLE `round` (
  `id` int(11) NOT NULL,
  `championship_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `round`
--

INSERT INTO `round` (`id`, `championship_id`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(10, 1),
(11, 1),
(12, 1),
(13, 1),
(14, 1),
(15, 1),
(16, 1),
(17, 1),
(18, 1);

-- --------------------------------------------------------

--
-- Table structure for table `team`
--

CREATE TABLE `team` (
  `id` int(11) NOT NULL,
  `city_id` int(11) NOT NULL,
  `name_en` varchar(64) DEFAULT NULL,
  `name_gr` varchar(64) DEFAULT NULL,
  `short_name_en` varchar(4) DEFAULT NULL,
  `short_name_gr` varchar(4) DEFAULT NULL,
  `logo_path` varchar(1024) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `team`
--

INSERT INTO `team` (`id`, `city_id`, `name_en`, `name_gr`, `short_name_en`, `short_name_gr`, `logo_path`) VALUES
(1, 1, 'OLYMPIACOS', 'ΟΛΥΜΠΙΑΚΟΣ', 'OLY', 'ΟΛΥ', NULL),
(2, 2, 'AEK', 'ΑΕΚ', 'AEK', 'ΑΕΚ', NULL),
(3, 3, 'PAOK', 'ΠΑΟΚ', 'PAOK', 'ΠΑΟΚ', NULL),
(4, 3, 'ARIS', 'ΑΡΗΣ', 'ARIS', 'ΑΡΗΣ', NULL),
(5, 4, 'APOLLON', 'ΑΠΟΛΛΩΝ', 'APOL', 'ΑΠΟΛ', NULL),
(6, 2, 'PANATHINAIKOS', 'ΠΑΝΑΘΗΝΑΙΚΟΣ', 'PAO', 'ΠΑΟ', NULL),
(7, 5, 'LARISSA BC', 'ΛΑΡΙΣΑ BC', 'LAR', 'ΛΑΡ', NULL),
(8, 4, 'PROMITHEAS', 'ΠΡΟΜΗΘΕΑΣ', 'PROM', 'ΠΡΟΜ', NULL),
(9, 6, 'LAVRIO BC', 'ΛΑΥΡΙΟ BC', 'LAV', 'ΛΑΥ', NULL),
(10, 2, 'PERISTERI BC', 'ΠΕΡΙΣΤΕΡΙ BC', 'PER', 'ΠΕΡ', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(16) NOT NULL,
  `password` varchar(32) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `championship`
--
ALTER TABLE `championship`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `championship_id_UNIQUE` (`id`),
  ADD UNIQUE KEY `name_UNIQUE` (`name`);

--
-- Indexes for table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `city_code_UNIQUE` (`id`);

--
-- Indexes for table `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`game_id`,`round_id`,`championship_id`),
  ADD KEY `fk_game_team1_idx` (`home_team_id`),
  ADD KEY `fk_game_team2_idx` (`away_team_id`),
  ADD KEY `fk_game_round1_idx` (`round_id`,`championship_id`);

--
-- Indexes for table `player`
--
ALTER TABLE `player`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`),
  ADD KEY `fk_player_player_position1_idx` (`player_position_code`),
  ADD KEY `fk_player_team1_idx` (`team_id`);

--
-- Indexes for table `player_position`
--
ALTER TABLE `player_position`
  ADD PRIMARY KEY (`position_code`),
  ADD UNIQUE KEY `position_code_UNIQUE` (`position_code`);

--
-- Indexes for table `player_stats`
--
ALTER TABLE `player_stats`
  ADD PRIMARY KEY (`game_id`,`player_id`),
  ADD KEY `fk_player_stats_game1_idx` (`game_id`),
  ADD KEY `fk_player_stats_player1` (`player_id`);

--
-- Indexes for table `round`
--
ALTER TABLE `round`
  ADD PRIMARY KEY (`id`,`championship_id`),
  ADD KEY `fk_season_championship1_idx` (`championship_id`);

--
-- Indexes for table `team`
--
ALTER TABLE `team`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `team_code_UNIQUE` (`id`),
  ADD KEY `fk_team_city1_idx` (`city_id`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `city`
--
ALTER TABLE `city`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `player`
--
ALTER TABLE `player`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=121;

--
-- AUTO_INCREMENT for table `round`
--
ALTER TABLE `round`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `team`
--
ALTER TABLE `team`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `game`
--
ALTER TABLE `game`
  ADD CONSTRAINT `fk_game_round1` FOREIGN KEY (`round_id`,`championship_id`) REFERENCES `round` (`id`, `championship_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_game_team1` FOREIGN KEY (`home_team_id`) REFERENCES `team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_game_team2` FOREIGN KEY (`away_team_id`) REFERENCES `team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `player`
--
ALTER TABLE `player`
  ADD CONSTRAINT `fk_player_player_position1` FOREIGN KEY (`player_position_code`) REFERENCES `player_position` (`position_code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_player_team1` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `player_stats`
--
ALTER TABLE `player_stats`
  ADD CONSTRAINT `fk_player_stats_game1` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_player_stats_player1` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `round`
--
ALTER TABLE `round`
  ADD CONSTRAINT `fk_season_championship1` FOREIGN KEY (`championship_id`) REFERENCES `championship` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `team`
--
ALTER TABLE `team`
  ADD CONSTRAINT `fk_team_city1` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
