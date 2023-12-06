--
-- author: waamir 104  
-- email: waamirdev@gmail.com
-- date: 2023-11-28
-- -----------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `role_id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) DEFAULT NULL,
  `permissions` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `UK_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `enabled` bit(1) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `role_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_email` (`email`),
  KEY `FK_users_roles` (`role_id`),
  CONSTRAINT `FK_users_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `account_verifications`
--

CREATE TABLE `account_verifications` (
  `account_verification_id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `url` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`account_verification_id`),
  UNIQUE KEY `UK_user_id` (`user_id`),
  UNIQUE KEY `UK_url` (`url`),
  CONSTRAINT `FK_account_verifications_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `tokens`
--

CREATE TABLE `tokens` (
  `token_id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `token` VARCHAR(255) DEFAULT NULL,
  `expired` BOOLEAN DEFAULT FALSE,
  `revoked` BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (`token_id`),
  CONSTRAINT `FK_tokens_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `payment_types`
--

CREATE TABLE `payment_types` (
  `payment_type_id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`payment_type_id`),
  UNIQUE KEY `UK_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `guests`
--

CREATE TABLE `guests` (
  `birth_date` date DEFAULT NULL,
  `guest_id` BIGINT NOT NULL AUTO_INCREMENT,
  `id_number` BIGINT NULL,
  `phone_number` BIGINT DEFAULT NULL,
  `email` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) DEFAULT NULL,
  `name` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`guest_id`),
  UNIQUE KEY `UK_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `room_types`
--

CREATE TABLE `room_types` (
  `daily_price` double NOT NULL,
  `room_type_id` BIGINT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) DEFAULT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`room_type_id`),
  UNIQUE KEY `UK_b70k1tp1aa52elkkxht660u36` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `number` BIGINT DEFAULT NULL,
  `room_id` BIGINT NOT NULL AUTO_INCREMENT,
  `room_type_id` BIGINT NOT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`room_id`),
  UNIQUE KEY `UK_number` (`number`),
  KEY `FK_rooms_room_types` (`room_type_id`),
  CONSTRAINT `FK_rooms_room_types` FOREIGN KEY (`room_type_id`) REFERENCES `room_types` (`room_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `bookings`
--

CREATE TABLE `bookings` (
  `check_in` date NOT NULL,
  `check_out` date DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `booking_id` BIGINT NOT NULL AUTO_INCREMENT,
  `guest_id` BIGINT NOT NULL,
  `room_id` BIGINT NOT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `FK_bookings_guests` (`guest_id`),
  KEY `FK_bookings_rooms` (`room_id`),
  CONSTRAINT `FK_bookings_guests` FOREIGN KEY (`guest_id`) REFERENCES `guests` (`guest_id`),
  CONSTRAINT `FK_bookings_rooms` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `booking_payment_types`
--

CREATE TABLE `booking_payment_types` (
  `payment_percentage` double NOT NULL,
  `booking_id` BIGINT NOT NULL,
  `booking_payment_type_id` BIGINT NOT NULL AUTO_INCREMENT,
  `payment_type_id` BIGINT NOT NULL,
  PRIMARY KEY (`booking_payment_type_id`),
  KEY `FK_booking_payment_types_bookings` (`booking_id`),
  KEY `FK_booking_payment_types_payment_types` (`payment_type_id`),
  CONSTRAINT `FK_booking_payment_types_bookings` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`booking_id`),
  CONSTRAINT `FK_booking_payment_types_payment_types` FOREIGN KEY (`payment_type_id`) REFERENCES `payment_types` (`payment_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
