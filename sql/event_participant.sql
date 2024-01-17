CREATE TABLE `event_participant` (
  `id` int NOT NULL AUTO_INCREMENT,
  `event_id` int NOT NULL,
  `participant_id` int NOT NULL,
  `participant_type` enum('team','user') NOT NULL, --delete
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_participant` (`event_id`,`participant_id`)
)