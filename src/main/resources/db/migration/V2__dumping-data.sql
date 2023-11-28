--
-- author: waamir 104  
-- email: waamirdev@gmail.com
-- date: 2023-11-28
-- -----------------------------------

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`role_id`, `name`) VALUES 
    (1,'GUEST'),
    (2,'ADMIN'),
    (3,'WORKER');

ALTER TABLE `roles`
    MODIFY `role_id` BIGINT NOT NULL AUTO_INCREMENT, AUTO_INCREMENT = 4;

--
-- Dumping data for table `payment_types`
--

INSERT INTO `payment_types` (`payment_type_id`, `name`) VALUES
    (1, 'Cash'),
    (2, 'Debit Card'),
    (3, 'Credit Card'),
    (4, 'Bank Transaction');

ALTER TABLE `payment_types` 
    MODIFY `payment_type_id` BIGINT NOT NULL AUTO_INCREMENT, AUTO_INCREMENT = 5;

--
-- Dumping data for table `room_types`
--

INSERT INTO `room_types` (`room_type_id`, `name`, `daily_price`, `description`) VALUES
    (1, 'Double', 40000, 'A room with a double bed, for two people.'),
    (2, 'Single', 30000, 'A room for one person with a single bed.'),
    (3, 'Twin', 60000, 'A room with two separate beds, suitable for two individuals.'),
    (4, 'Connecting', 50000, 'Two single rooms with a connecting door, convinient for families or groups.'),
    (5, 'Family', 80000, 'A room designed to accommodate a family, with a double bed, a single bed and a sofa bed.'),
    (6, 'Suite', 100000, 'A larger and more luxurious room with separate living and sleeping areas.'),
    (7, 'Penthouse', 50000, 'A luxurious suite located on the highest floor of the hotel.');

ALTER TABLE `room_types`
    MODIFY `room_type_id` BIGINT NOT NULL AUTO_INCREMENT, AUTO_INCREMENT = 8;
