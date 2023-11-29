--
-- author: waamir 104  
-- email: waamirdev@gmail.com
-- date: 2023-11-28
-- -----------------------------------

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`name`) VALUES 
    ('GUEST'),
    ('ADMIN'),
    ('WORKER');

ALTER TABLE `roles`
    MODIFY `role_id` BIGINT NOT NULL AUTO_INCREMENT, AUTO_INCREMENT = 4;

--
-- Dumping data for table `payment_types`
--

INSERT INTO `payment_types` (`name`) VALUES
    ('Cash'),
    ('Debit Card'),
    ('Credit Card'),
    ('Bank Transaction');

--
-- Dumping data for table `room_types`
--

INSERT INTO `room_types` (`name`, `daily_price`, `description`) VALUES
    ('Double', 40000, 'A room with a double bed, for two people.'),
    ('Single', 30000, 'A room for one person with a single bed.'),
    ('Twin', 60000, 'A room with two separate beds, suitable for two individuals.'),
    ('Connecting', 50000, 'Two single rooms with a connecting door, convinient for families or groups.'),
    ('Family', 80000, 'A room designed to accommodate a family, with a double bed, a single bed and a sofa bed.'),
    ('Suite', 100000, 'A larger and more luxurious room with separate living and sleeping areas.'),
    ('Penthouse', 50000, 'A luxurious suite located on the highest floor of the hotel.');



--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`number`, `room_type_id`, `description`)
VALUES
    (101, 1, 'Double room with a view of the city.'),
    (102, 2, 'Single room with a cozy ambiance.'),
    (103, 3, 'Twin room with comfortable beds.'),
    (104, 4, 'Connecting rooms for families.'),
    (105, 5, 'Family room with ample space for everyone.'),
    (201, 4, 'Connecting rooms with a shared lounge area.'),
    (202, 1, 'Double room with a fireplace.'),
    (203, 1, 'Double room with a balcony.'),
    (204, 2, 'Single room with a workspace.'),
    (205, 3, 'Twin room with a modern design.'),
    (301, 6, 'Luxurious suite with separate living and sleeping areas.'),
    (302, 5, 'Family room with a play area for kids.'),
    (303, 6, 'Suite with a private jacuzzi.'),
    (304, 7, 'Penthouse with a private terrace.'),
    (305, 7, 'Penthouse with panoramic views.');
