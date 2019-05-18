CREATE TABLE `Friend` (
  `friend_id` bigint(20) NOT NULL,
  `name` varchar(250) NOT NULL,
  `amount_to_pay` decimal(4,0) NOT NULL,
  `amount_paid` decimal(4,0) NOT NULL,
  PRIMARY KEY (`friend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;