CREATE TABLE `Settlement_Amount` (
  `settlement_amount_id` bigint(20) NOT NULL,
  `to_be_paid_to` bigint(20) NOT NULL,
  `value` decimal(4,0) NOT NULL,
  `to_be_paid_by` bigint(20) NOT NULL,
  PRIMARY KEY (`settlement_amount_id`),
  KEY `to_be_paid_to` (`to_be_paid_to`),
  KEY `to_be_paid_by` (`to_be_paid_by`),
  CONSTRAINT `to_be_paid_by` FOREIGN KEY (`to_be_paid_by`) REFERENCES `friend` (`friend_id`),
  CONSTRAINT `to_be_paid_to` FOREIGN KEY (`to_be_paid_to`) REFERENCES `friend` (`friend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
