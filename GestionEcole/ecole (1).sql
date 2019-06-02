-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mer. 29 mai 2019 à 14:42
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `anneescolaire`
--

INSERT INTO `anneescolaire` (`id`, `annee`) VALUES
(1, '2018'),
(2, '2016'),
(3, '2017'),
(4, '2019');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `bulletin`
--

INSERT INTO `bulletin` (`id`, `fk_trimestre`, `fk_inscription`, `fk_eleve`, `appreciation`) VALUES
(1, 1, 1, 1, 'Felicitation'),
(2, 1, 2, 2, 'Pas terrible');

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

DROP TABLE IF EXISTS `classe`;
CREATE TABLE IF NOT EXISTS `classe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `fk_niveau` int(11) NOT NULL,
  `fk_anneescolaire` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ecole.id` (`fk_niveau`,`fk_anneescolaire`),
  KEY `anneescolaire.id` (`fk_anneescolaire`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `classe`
--

INSERT INTO `classe` (`id`, `nom`, `fk_niveau`, `fk_anneescolaire`) VALUES
(1, 'TD1', 1, 1),
(2, 'TD7', 3, 1);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `detailbulletin`
--

INSERT INTO `detailbulletin` (`id`, `fk_bulletin`, `fk_enseignement`, `appreciation`) VALUES
(1, 1, 1, 'tres doué en physique'),
(2, 1, 2, 'très bon résultas en maths'),
(3, 2, 2, 'moyen moyen leo hein ');

-- --------------------------------------------------------

--
-- Structure de la table `discipline`
--

DROP TABLE IF EXISTS `discipline`;
CREATE TABLE IF NOT EXISTS `discipline` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

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
  `fk_classe` int(11) NOT NULL,
  `fk_discipline` int(11) NOT NULL,
  `fk_personne` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `classe.id` (`fk_classe`),
  KEY `discipline.id` (`fk_discipline`),
  KEY `personne.id` (`fk_personne`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `enseignement`
--

INSERT INTO `enseignement` (`id`, `fk_classe`, `fk_discipline`, `fk_personne`) VALUES
(1, 2, 2, 3),
(2, 2, 1, 4),
(3, 1, 2, 3);

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
  PRIMARY KEY (`id`),
  KEY `detailbulletin.id` (`fk_detailbulletin`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `evaluation`
--

INSERT INTO `evaluation` (`id`, `fk_detailbulletin`, `note`, `appreciation`) VALUES
(1, 1, '20', 'bon devoir'),
(2, 3, '5', 'décevant');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `inscription`
--

INSERT INTO `inscription` (`id`, `fk_classe`, `fk_personne`) VALUES
(1, 2, 1),
(2, 2, 2),
(3, 1, 5);

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `personne`
--

INSERT INTO `personne` (`id`, `nom`, `prenom`, `type`) VALUES
(1, 'Eisenbarth', 'Florian', '0'),
(2, 'Beillevaire', 'Leo', '0'),
(3, 'Mhoualli', 'Waleed', '1'),
(4, 'Chary', 'Aniss', '1'),
(5, 'Delagardette', 'kieran', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `trimestre`
--

INSERT INTO `trimestre` (`id`, `numero`, `debut`, `fin`, `fk_anneescolaire`) VALUES
(1, 1, 'septembre', 'novembre', 1),
(2, 1, 'septembre', 'novembre', 1);

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
