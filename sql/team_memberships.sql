CREATE TABLE `team_memberships` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `team_id` int NOT NULL,
  `role` enum('Leader','Co-leader','Elder','Member') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `team_id_idx` (`team_id`),
  KEY `user_id_idx` (`user_id`),
)