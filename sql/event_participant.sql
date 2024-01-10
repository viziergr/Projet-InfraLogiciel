CREATE TABLE `event_participant` (
  `id` int NOT NULL AUTO_INCREMENT,
  `event_id` int NOT NULL,
  `participant_id` int NOT NULL,
  `participant_type` enum('team','user') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_participant` (`event_id`,`participant_id`),
  KEY `event_participant_ibfk_2` (`participant_id`),
--   CONSTRAINT `event_participant_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`),
--   CONSTRAINT `event_participant_ibfk_2` FOREIGN KEY (`participant_id`) REFERENCES `user` (`id`)
)