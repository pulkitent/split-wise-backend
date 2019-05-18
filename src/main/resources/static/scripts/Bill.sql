CREATE TABLE `Bill` (
  `bill_id` bigint(20) NOT NULL,
  `amount` decimal(20,0) NOT NULL,
  `type` varchar(250) NOT NULL,
  PRIMARY KEY (`bill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;