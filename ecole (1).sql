-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 09 juin 2019 à 14:36
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `ecole`
--
CREATE DATABASE IF NOT EXISTS `ecole` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `ecole`;

-- --------------------------------------------------------

--
-- Structure de la table `anneescolaire`
--

DROP TABLE IF EXISTS `anneescolaire`;
CREATE TABLE IF NOT EXISTS `anneescolaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `annee` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `anneescolaire`
--

INSERT INTO `anneescolaire` (`id`, `annee`) VALUES
(1, '2018'),
(2, '2016'),
(3, '2017'),
(4, '2019'),
(5, '2020'),
(6, '2021'),
(7, '2022'),
(8, '2023'),
(9, '2024');

-- --------------------------------------------------------

--
-- Structure de la table `bulletin`
--

DROP TABLE IF EXISTS `bulletin`;
CREATE TABLE IF NOT EXISTS `bulletin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_trimestre` int(11) NOT NULL,
  `fk_inscription` int(11) NOT NULL,
  `fk_eleve` int(11) NOT NULL,
  `appreciation` varchar(500) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `inscription.id` (`fk_inscription`),
  KEY `trimestre.id` (`fk_trimestre`),
  KEY `fk_eleve` (`fk_eleve`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `bulletin`
--

INSERT INTO `bulletin` (`id`, `fk_trimestre`, `fk_inscription`, `fk_eleve`, `appreciation`) VALUES
(1, 1, 1, 1, 'Felicitation'),
(2, 1, 2, 2, 'Pas terrible'),
(24, 1, 12, 15, ' '),
(25, 2, 12, 15, ' '),
(26, 3, 12, 15, ' '),
(27, 1, 13, 16, ' '),
(28, 2, 13, 16, ' '),
(29, 3, 13, 16, ' '),
(33, 1, 15, 19, ' '),
(34, 2, 15, 19, ' '),
(35, 3, 15, 19, ' '),
(36, 1, 16, 31, ' '),
(37, 2, 16, 31, ' '),
(38, 3, 16, 31, ' ');

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

DROP TABLE IF EXISTS `classe`;
CREATE TABLE IF NOT EXISTS `classe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `fk_niveau` int(11) DEFAULT NULL,
  `fk_anneescolaire` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ecole.id` (`fk_niveau`,`fk_anneescolaire`),
  KEY `anneescolaire.id` (`fk_anneescolaire`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `classe`
--

INSERT INTO `classe` (`id`, `nom`, `fk_niveau`, `fk_anneescolaire`) VALUES
(1, 'TD1', 1, 1),
(2, 'TD7', 3, 1),
(9, 'TD10', 1, 4),
(10, 'TD3', 2, 4),
(13, 'TD3', 2, 1),
(14, 'TD8', 4, 4);

-- --------------------------------------------------------

--
-- Structure de la table `detailbulletin`
--

DROP TABLE IF EXISTS `detailbulletin`;
CREATE TABLE IF NOT EXISTS `detailbulletin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_bulletin` int(11) NOT NULL,
  `fk_enseignement` int(11) NOT NULL,
  `appreciation` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bulletin.id` (`fk_bulletin`),
  KEY `enseignement.id` (`fk_enseignement`)
) ENGINE=InnoDB AUTO_INCREMENT=294 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `detailbulletin`
--

INSERT INTO `detailbulletin` (`id`, `fk_bulletin`, `fk_enseignement`, `appreciation`) VALUES
(1, 1, 1, 'bon résultats en physique'),
(2, 1, 2, 'très bon résultas en maths'),
(3, 2, 2, 'moyen moyen leo hein '),
(4, 2, 1, 'peut mieux faire'),
(41, 24, 1, ' bon résultats en physique'),
(42, 24, 16, ' '),
(43, 25, 1, ' '),
(44, 25, 16, ' '),
(47, 26, 1, ' '),
(48, 26, 16, ' '),
(53, 27, 1, ' '),
(54, 27, 16, ' '),
(55, 28, 1, ' '),
(56, 28, 16, ' '),
(59, 29, 1, ' '),
(60, 29, 16, ' '),
(214, 33, 1, ' '),
(215, 33, 16, ' '),
(217, 34, 1, ' '),
(218, 34, 16, ' '),
(220, 35, 1, ' '),
(221, 35, 16, ' '),
(266, 1, 47, ' '),
(267, 2, 47, ' '),
(268, 24, 47, ' '),
(269, 25, 47, ' '),
(270, 26, 47, ' '),
(271, 27, 47, ' '),
(272, 28, 47, ' '),
(273, 29, 47, ' '),
(277, 33, 47, ' '),
(278, 34, 47, ' '),
(279, 35, 47, ' '),
(280, 1, 49, ' '),
(281, 2, 49, ' '),
(282, 24, 49, ' '),
(283, 25, 49, ' '),
(284, 26, 49, ' '),
(285, 27, 49, ' '),
(286, 28, 49, ' '),
(287, 29, 49, ' '),
(291, 33, 49, ' '),
(292, 34, 49, ' '),
(293, 35, 49, ' ');

-- --------------------------------------------------------

--
-- Structure de la table `discipline`
--

DROP TABLE IF EXISTS `discipline`;
CREATE TABLE IF NOT EXISTS `discipline` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `discipline`
--

INSERT INTO `discipline` (`id`, `nom`) VALUES
(1, 'Mathématique'),
(2, 'Physique'),
(3, 'Informatique'),
(4, 'Electronique'),
(5, 'Thermodynamique');

-- --------------------------------------------------------

--
-- Structure de la table `enseignement`
--

DROP TABLE IF EXISTS `enseignement`;
CREATE TABLE IF NOT EXISTS `enseignement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_classe` int(11) DEFAULT NULL,
  `fk_discipline` int(11) NOT NULL,
  `fk_personne` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `classe.id` (`fk_classe`),
  KEY `discipline.id` (`fk_discipline`),
  KEY `personne.id` (`fk_personne`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `enseignement`
--

INSERT INTO `enseignement` (`id`, `fk_classe`, `fk_discipline`, `fk_personne`) VALUES
(1, 2, 2, 3),
(2, 1, 1, 4),
(3, 1, 2, 3),
(16, 2, 1, 4),
(44, NULL, 3, 29),
(47, 2, 3, 29),
(48, NULL, 5, 30),
(49, 2, 5, 30),
(50, NULL, 2, 32);

-- --------------------------------------------------------

--
-- Structure de la table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
CREATE TABLE IF NOT EXISTS `evaluation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_detailbulletin` int(11) NOT NULL,
  `note` varchar(255) NOT NULL,
  `appreciation` varchar(255) NOT NULL,
  `nom` varchar(500) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `detailbulletin.id` (`fk_detailbulletin`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `evaluation`
--

INSERT INTO `evaluation` (`id`, `fk_detailbulletin`, `note`, `appreciation`, `nom`) VALUES
(1, 1, '20', 'bon devoir', 'DS1'),
(2, 3, '5', 'décevant', 'DS1'),
(3, 1, '15', 'bien', 'DS2'),
(4, 4, '8', 'bof bof ', 'DS2'),
(5, 4, '14', 'bien ', 'NS1'),
(6, 1, '15', 'good', 'NS1'),
(7, 4, '12', 'assez bien', 'NS2'),
(12, 1, '18', 'bon travail', 'DS4'),
(26, 2, '15', 'bien', 'DS1'),
(27, 2, '18', 'tres bein', 'DS2'),
(28, 4, '2', 'tu es nul', 'DS8'),
(29, 41, '18', 'bon travail', 'DS1'),
(30, 41, '15', 'bien', 'DS2'),
(31, 41, '13', 'assez bien', 'DS3'),
(32, 41, '20', 'tres bien', 'NS1'),
(33, 268, '15', 'Bien', 'DS1'),
(34, 271, '10', 'suffisant', 'DS1'),
(35, 269, '14', 'Bien', 'DS1');

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

DROP TABLE IF EXISTS `inscription`;
CREATE TABLE IF NOT EXISTS `inscription` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_classe` int(11) NOT NULL,
  `fk_personne` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `classe.id` (`fk_classe`),
  KEY `personne.id` (`fk_personne`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `inscription`
--

INSERT INTO `inscription` (`id`, `fk_classe`, `fk_personne`) VALUES
(1, 2, 1),
(2, 2, 2),
(3, 1, 5),
(12, 2, 15),
(13, 2, 16),
(15, 2, 19),
(16, 10, 31);

-- --------------------------------------------------------

--
-- Structure de la table `niveau`
--

DROP TABLE IF EXISTS `niveau`;
CREATE TABLE IF NOT EXISTS `niveau` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `niveau`
--

INSERT INTO `niveau` (`id`, `nom`) VALUES
(1, 'ING1'),
(2, 'ING2'),
(3, 'ING3'),
(4, 'ING4'),
(5, 'ING5');

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

DROP TABLE IF EXISTS `personne`;
CREATE TABLE IF NOT EXISTS `personne` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `photo` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `personne`
--

INSERT INTO `personne` (`id`, `nom`, `prenom`, `type`, `photo`) VALUES
(1, 'Eisenbarth', 'Florian', '0', 'https://yt3.ggpht.com/a-/AN66SAzwbywOy5uKV7oHSu6zWSjKcG3E829r5gQ08Q=s900-mo-c-c0xffffffff-rj-k-no'),
(2, 'Beillevaire', 'Leo', '0', 'http://fr.web.img2.acsta.net/videothumbnails/16/07/08/11/03/598936.jpg'),
(3, 'Mhoualli', 'Waleed', '1', 'https://image-uniservice.linternaute.com/image/450/4/1770870342/6507611.jpg'),
(4, 'Charry', 'Annis', '1', 'https://sciencetonnante.files.wordpress.com/2010/11/pi_universe-small.jpg'),
(5, 'Delagardette', 'kieran', '0', 'data:image/jpeg;base64,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'),
(15, 'Leblond', 'Mathilde', '0', 'https://dok7xy59qfw9h.cloudfront.net/632/531/172/-239996977-1tr2rm4-23a4i62q02a9b7f/original/file.jpg'),
(16, 'Lanister', 'Thirion', '0', 'https://www.mediacritik.com/wp-content/uploads/2019/04/rs_1024x759-170407142939-1024.Peter-Dinklage-Game-of-Thrones.kg_.040717-1024x759.jpg'),
(19, 'Eisenbarth', 'Nicola', '0', ''),
(29, 'Segado', 'JP', '1', 'http://pauillac.inria.fr/~ddr/th2rives/2004/images/alouette.jpg'),
(30, 'Gallenti', 'Serina', '1', 'https://imgjy.com/EV18059/v/robe-princesse-rouge-encolure-bardot-pour-mariage-gala-soiree-persun.jpg'),
(31, 'Partouche', 'Raphael', '0', 'https://media.licdn.com/dms/image/C4E03AQGSaG2dX9Hfew/profile-displayphoto-shrink_200_200/0?e=1564617600&v=beta&t=EHPCHwsPiLzBfkZoAd-q_cG7ouPLW1w4x3NFkb66Z_g'),
(32, 'Muller', 'Francois', '1', 'https://cdn.vox-cdn.com/thumbor/cBoUzhRufkApLNKbwJU5gBlFLYE=/691x0:5024x3349/1200x800/filters:focal(2277x333:3079x1135)/cdn.vox-cdn.com/uploads/chorus_image/image/60436207/GettyImages-961663050.0.0.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `trimestre`
--

DROP TABLE IF EXISTS `trimestre`;
CREATE TABLE IF NOT EXISTS `trimestre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numero` int(11) NOT NULL,
  `debut` varchar(255) NOT NULL,
  `fin` varchar(255) NOT NULL,
  `fk_anneescolaire` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Anneescolaire.id` (`fk_anneescolaire`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `trimestre`
--

INSERT INTO `trimestre` (`id`, `numero`, `debut`, `fin`, `fk_anneescolaire`) VALUES
(1, 1, 'septembre', 'novembre', 4),
(2, 2, 'decembre', 'fevrier', 4),
(3, 3, 'mai', 'Juillet', 4);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `bulletin`
--
ALTER TABLE `bulletin`
  ADD CONSTRAINT `bulletin_ibfk_1` FOREIGN KEY (`fk_inscription`) REFERENCES `inscription` (`id`),
  ADD CONSTRAINT `bulletin_ibfk_2` FOREIGN KEY (`fk_trimestre`) REFERENCES `trimestre` (`id`),
  ADD CONSTRAINT `bulletin_ibfk_3` FOREIGN KEY (`fk_eleve`) REFERENCES `personne` (`id`);

--
-- Contraintes pour la table `classe`
--
ALTER TABLE `classe`
  ADD CONSTRAINT `classe_ibfk_1` FOREIGN KEY (`fk_anneescolaire`) REFERENCES `anneescolaire` (`id`),
  ADD CONSTRAINT `classe_ibfk_2` FOREIGN KEY (`fk_niveau`) REFERENCES `niveau` (`id`);

--
-- Contraintes pour la table `detailbulletin`
--
ALTER TABLE `detailbulletin`
  ADD CONSTRAINT `detailbulletin_ibfk_1` FOREIGN KEY (`fk_bulletin`) REFERENCES `bulletin` (`id`),
  ADD CONSTRAINT `detailbulletin_ibfk_2` FOREIGN KEY (`fk_enseignement`) REFERENCES `enseignement` (`id`);

--
-- Contraintes pour la table `enseignement`
--
ALTER TABLE `enseignement`
  ADD CONSTRAINT `enseignement_ibfk_1` FOREIGN KEY (`fk_classe`) REFERENCES `classe` (`id`),
  ADD CONSTRAINT `enseignement_ibfk_2` FOREIGN KEY (`fk_discipline`) REFERENCES `discipline` (`id`),
  ADD CONSTRAINT `enseignement_ibfk_3` FOREIGN KEY (`fk_personne`) REFERENCES `personne` (`id`);

--
-- Contraintes pour la table `evaluation`
--
ALTER TABLE `evaluation`
  ADD CONSTRAINT `evaluation_ibfk_1` FOREIGN KEY (`fk_detailbulletin`) REFERENCES `detailbulletin` (`id`);

--
-- Contraintes pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD CONSTRAINT `inscription_ibfk_1` FOREIGN KEY (`fk_classe`) REFERENCES `classe` (`id`),
  ADD CONSTRAINT `inscription_ibfk_2` FOREIGN KEY (`fk_personne`) REFERENCES `personne` (`id`);

--
-- Contraintes pour la table `trimestre`
--
ALTER TABLE `trimestre`
  ADD CONSTRAINT `trimestre_ibfk_1` FOREIGN KEY (`fk_anneescolaire`) REFERENCES `anneescolaire` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
