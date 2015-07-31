# ************************************************************
# Sequel Pro SQL dump
# Version 4135
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.24)
# Database: gamestore
# Generation Time: 2015-07-31 01:20:18 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;

INSERT INTO `category` (`id`, `name`)
VALUES
	(150064,'Top'),
	(150071,'Estrenos');

/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table download
# ------------------------------------------------------------

DROP TABLE IF EXISTS `download`;

CREATE TABLE `download` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned DEFAULT NULL,
  `game_id` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_game` (`game_id`),
  KEY `fk_user2` (`user_id`),
  CONSTRAINT `fk_game` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table game
# ------------------------------------------------------------

DROP TABLE IF EXISTS `game`;

CREATE TABLE `game` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` text,
  `licensor_name` varchar(100) DEFAULT '',
  `category_id` int(11) unsigned NOT NULL,
  `default_image_url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_key` (`category_id`),
  CONSTRAINT `category_key` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;

INSERT INTO `game` (`id`, `name`, `description`, `licensor_name`, `category_id`, `default_image_url`)
VALUES
	(1,'Le petit Nicolas','¡La adaptación oficial de la serie de animación El pequeño Nicolás llega por fin a Android! ¡El pequeño Nicolás continúa haciendo travesuras! Ayúdale a escapar en bicicleta lo más rápido posible, antes de que El Caldo lo atrape.','DigitalVirgo',150071,NULL),
	(2,'Saving Private Sheep','La guerra entre lobos y ovejas causa estragos.','DigitalVirgo',150071,NULL),
	(3,'Magnetic Sport Soccer','Magnetic Sports Soccer (Fútbol) se basa en el juego de mesa clásico de fútbol donde los jugadores se montaron en un muelle en el centro de una cancha de mesa. Hemos implementado fuerzas magnéticas para un juego más dinámico y espectacular.','DigitalVirgo',150071,NULL),
	(4,'Championship Rally','2012 Championship Rally 3D ... ¡Abróchate el cinturón, va a ser un viaje movidito!','Handson (MForma)',150064,NULL),
	(5,'Real Football 2015','¡El juego de fútbol más realista y social! ¡Conquista la Copa Internacional con gráficos increíbles!','Gameloft',150064,NULL),
	(6,'Finn Saltarín','¡UN NUEVO JUEGO DE HORA DE AVENTURA! Â¡El Rey Helado ha secuestrado a Dulce Princesa y la tiene prisionera en su castillo! ¿Cómo llegaran Finn y Jake tan lejos en tan poco tiempo para evitar la boda? ¡Con una buena patada!','Turner',150064,NULL);

/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `number` int(9) NOT NULL,
  `plan` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `number`, `plan`, `password`)
VALUES
	(1,997635323,'postpago','123456');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_subscription
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_subscription`;

CREATE TABLE `user_subscription` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user` (`user_id`),
  CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
