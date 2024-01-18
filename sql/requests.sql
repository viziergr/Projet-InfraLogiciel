CREATE TABLE `requests` (
  `id` int(11) NOT NULL,
  `team_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `status` enum('en_attente','acceptee','refusee') NOT NULL DEFAULT 'en_attente'
)