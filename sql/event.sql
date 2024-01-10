CREATE TABLE `event` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `location` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_private` tinyint(1) NOT NULL,
  `creator_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `creator_id` (`creator_id`),
--   CONSTRAINT `event_ibfk_1` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`)
) 