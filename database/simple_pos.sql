SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE DATABASE IF NOT EXISTS `simple_pos` DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci;
USE `simple_pos`;

DELIMITER $$
DROP PROCEDURE IF EXISTS `addNewProduct`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `addNewProduct` (IN `inAddByEMPID` INT, IN `inProductName` VARCHAR(255), IN `inProductDesc` TEXT, IN `inProductPrice` DECIMAL(10,2), IN `inProductStock` INT)   BEGIN
    INSERT INTO product (add_by_employee_id, name, description, price, total_available, created_at, updated_at)
    VALUES (inAddByEMPID, inProductName, inProductDesc, inProductPrice, inProductStock, NOW(), NOW());
END$$

DROP PROCEDURE IF EXISTS `deleteProductById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteProductById` (IN `inProductId` INT)   BEGIN
  UPDATE product SET deleted_at = NOW() WHERE product_id = inProductId;
END$$

DROP PROCEDURE IF EXISTS `getProducts`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getProducts` ()   BEGIN
    SELECT * FROM product;
END$$

DROP PROCEDURE IF EXISTS `loginUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `loginUser` (IN `inEmail` VARCHAR(255), IN `inPassword` VARCHAR(255))   BEGIN
    DECLARE user_count INT;
    SET user_count = (SELECT COUNT(*) FROM employee WHERE email = inEmail AND password = inPassword);
    
    IF user_count = 1 THEN
        SELECT employee_id, firstname, lastname, role, position, salary 
        FROM employee WHERE inEmail = email AND inPassword = password;
    ELSE
        SELECT 'Invalid email or password' AS message;
    END IF;
END$$

DROP PROCEDURE IF EXISTS `updateProduct`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateProduct` (IN `inProductId` INT, IN `inProductName` VARCHAR(255), IN `inProductDesc` TEXT, IN `inProductPrice` DECIMAL(10,2), IN `inProductStock` INT)   BEGIN
    UPDATE product
    SET name = inProductName, description = inProductDesc, price = inProductPrice, total_available = inProductStock
    WHERE product_id = inProductId;
END$$

DELIMITER ;

DROP TABLE IF EXISTS `add_product_logs`;
CREATE TABLE `add_product_logs` (
  `log_id` int(11) NOT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO `add_product_logs` (`log_id`, `employee_id`, `product_id`, `created_at`) VALUES
(0, 5, 0, '2024-03-23 02:56:57');

DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
  `customer_id` int(11) NOT NULL,
  `firstname` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `lastname` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `point` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO `customers` (`customer_id`, `firstname`, `lastname`, `tel`, `point`, `created_at`, `updated_at`) VALUES
(1, 'John', 'Doe', '1234567890', 100, '2024-03-23 02:45:36', '2024-03-23 02:45:36'),
(2, 'Jane', 'Smith', '0987654321', 50, '2024-03-23 02:45:36', '2024-03-23 02:45:36'),
(3, 'Alice', 'Johnson', '5555555555', 75, '2024-03-23 02:45:36', '2024-03-23 02:45:36'),
(4, 'Sarah', 'Lee', '7777777777', 120, '2024-03-23 02:45:36', '2024-03-23 02:45:36'),
(5, 'Robert', 'Smith', '9999999999', 90, '2024-03-23 02:45:36', '2024-03-23 02:45:36'),
(6, 'Emma', 'Davis', '4444444444', 80, '2024-03-23 02:45:36', '2024-03-23 02:45:36');

DROP TABLE IF EXISTS `customer_addresses`;
CREATE TABLE `customer_addresses` (
  `customer_address_id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `label` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `country` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `distract` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `sub_district` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `postal_code` int(11) DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO `customer_addresses` (`customer_address_id`, `customer_id`, `label`, `country`, `distract`, `sub_district`, `postal_code`, `tel`, `created_at`, `updated_at`) VALUES
(1, 1, 'Home', 'USA', 'New York', 'Manhattan', 10001, '1234567890', '2024-03-23 02:45:36', '2024-03-23 02:45:36'),
(2, 1, 'Work', 'USA', 'California', 'Los Angeles', 90001, '0987654321', '2024-03-23 02:45:36', '2024-03-23 02:45:36'),
(3, 2, 'Home', 'USA', 'Texas', 'Houston', 77001, '5555555555', '2024-03-23 02:45:36', '2024-03-23 02:45:36'),
(4, 4, 'Home', 'USA', 'California', 'San Francisco', 94101, '7777777777', '2024-03-23 02:45:36', '2024-03-23 02:45:36'),
(5, 5, 'Home', 'USA', 'New York', 'Brooklyn', 10002, '9999999999', '2024-03-23 02:45:36', '2024-03-23 02:45:36'),
(6, 6, 'Home', 'USA', 'Florida', 'Miami', 33101, '4444444444', '2024-03-23 02:45:36', '2024-03-23 02:45:36');

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL,
  `citizen_id` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `firstname` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `lastname` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `role` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `position` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `salary` decimal(10,0) DEFAULT NULL,
  `email` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `password` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO `employee` (`employee_id`, `citizen_id`, `firstname`, `lastname`, `tel`, `role`, `position`, `salary`, `email`, `password`, `created_at`, `updated_at`) VALUES
(1, '1234567890123', 'Michael', 'Johnson', '1111111111', 'Manager', 'Sales Manager', 50000, 'manager@example.com', 'manager_password', '2024-03-23 02:45:36', '2024-03-23 02:45:36'),
(2, '9876543210987', 'Emily', 'Williams', '2222222222', 'Staff', 'Sales Assistant', 30000, 'assistant@example.com', 'assistant_password', '2024-03-23 02:45:36', '2024-03-23 02:45:36'),
(3, '5555555555555', 'David', 'Brown', '3333333333', 'Staff', 'Warehouse Manager', 35000, 'warehouse@example.com', 'warehouse_password', '2024-03-23 02:45:36', '2024-03-23 02:45:36'),
(4, '4444444444444', 'Jennifer', 'Martinez', '4444444444', 'Staff', 'Sales Assistant', 30000, 'assistant2@example.com', 'assistant_password2', '2024-03-23 02:45:36', '2024-03-23 02:45:36'),
(5, '5555555555555', 'Daniel', 'Taylor', '5555555555', 'Staff', 'Warehouse Staff', 32000, 'warehouse2@example.com', 'warehouse_password2', '2024-03-23 02:45:36', '2024-03-23 02:45:36'),
(6, '6666666666666', 'Sophia', 'Garcia', '6666666666', 'Manager', 'HR Manager', 52000, 'hr@example.com', 'hr_password', '2024-03-23 02:45:36', '2024-03-23 02:45:36');

DROP TABLE IF EXISTS `employee_addresses`;
CREATE TABLE `employee_addresses` (
  `employee_addresses_id` int(11) NOT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `country` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `distract` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `sub_district` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `postal_code` int(11) DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO `employee_addresses` (`employee_addresses_id`, `employee_id`, `country`, `distract`, `sub_district`, `postal_code`, `tel`, `created_at`, `updated_at`) VALUES
(1, 1, 'USA', 'New York', 'Manhattan', 10001, '1111111111', '2024-03-23 02:45:36', '2024-03-23 02:45:36'),
(2, 2, 'USA', 'California', 'Los Angeles', 90001, '2222222222', '2024-03-23 02:45:36', '2024-03-23 02:45:36'),
(3, 3, 'USA', 'Texas', 'Houston', 77001, '3333333333', '2024-03-23 02:45:36', '2024-03-23 02:45:36'),
(4, 4, 'USA', 'California', 'Los Angeles', 90001, '4444444444', '2024-03-23 02:45:36', '2024-03-23 02:45:36'),
(5, 5, 'USA', 'New York', 'Manhattan', 10001, '5555555555', '2024-03-23 02:45:36', '2024-03-23 02:45:36'),
(6, 6, 'USA', 'Florida', 'Orlando', 32801, '6666666666', '2024-03-23 02:45:36', '2024-03-23 02:45:36');

DROP TABLE IF EXISTS `invoice`;
CREATE TABLE `invoice` (
  `invoice_id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `tax` int(11) DEFAULT NULL,
  `total_price` int(11) DEFAULT NULL,
  `total_point` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=sjis COLLATE=sjis_bin;

INSERT INTO `invoice` (`invoice_id`, `customer_id`, `employee_id`, `tax`, `total_price`, `total_point`, `created_at`) VALUES
(1, 1, 1, 7, 1070, 10, '2024-03-23 02:45:36'),
(2, 2, 2, 5, 840, 8, '2024-03-23 02:45:36'),
(3, 3, 3, 10, 220, 2, '2024-03-23 02:45:36'),
(4, 4, 2, 9, 660, 6, '2024-03-23 02:45:36'),
(5, 5, 1, 8, 920, 7, '2024-03-23 02:45:36'),
(6, 6, 3, 12, 1440, 10, '2024-03-23 02:45:36');

DROP TABLE IF EXISTS `invoice_item`;
CREATE TABLE `invoice_item` (
  `invoice_item_id` int(11) NOT NULL,
  `invoice_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price_per` decimal(10,0) DEFAULT NULL,
  `total_price` decimal(10,0) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO `invoice_item` (`invoice_item_id`, `invoice_id`, `product_id`, `quantity`, `price_per`, `total_price`, `created_at`) VALUES
(1, 1, 1, 1, 1000, 1000, '2024-03-23 02:45:36'),
(2, 1, 3, 1, 70, 70, '2024-03-23 02:45:36'),
(3, 2, 2, 1, 800, 800, '2024-03-23 02:45:36'),
(4, 3, 3, 1, 200, 200, '2024-03-23 02:45:36'),
(5, 4, 4, 1, 600, 600, '2024-03-23 02:45:36'),
(6, 5, 5, 2, 250, 500, '2024-03-23 02:45:36'),
(7, 6, 6, 1, 1200, 1200, '2024-03-23 02:45:36');

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `product_id` int(11) NOT NULL,
  `add_by_employee_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `total_available` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO `product` (`product_id`, `add_by_employee_id`, `name`, `description`, `price`, `total_available`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 3, 'Laptop', 'High-performance laptop', 1000, 10, '2024-03-23 03:03:40', '2024-03-23 03:03:40', NULL),
(2, 4, 'Smartphone', 'Latest smartphone model', 800, 20, '2024-03-23 03:03:40', '2024-03-23 03:03:40', NULL),
(3, 2, 'Headphones', 'Wireless noise-canceling headphones', 200, 15, '2024-03-23 03:03:40', '2024-03-23 03:03:40', '2024-03-23 10:04:41'),
(4, 1, 'Tablet', 'High-resolution tablet', 500, 15, '2024-03-23 03:03:40', '2024-03-23 03:03:40', NULL),
(5, 4, 'Smartwatch', 'Fitness and activity tracker', 250, 30, '2024-03-23 03:03:40', '2024-03-23 03:03:40', '2024-03-23 10:05:46'),
(6, 5, 'Camera', 'Digital camera with advanced features', 50000, 10, '2024-03-23 03:03:40', '2024-03-23 03:03:40', NULL),
(7, 6, 'New Item', 'New item description.', 50, 10, '2024-03-23 10:12:05', '2024-03-23 10:12:05', NULL);


ALTER TABLE `add_product_logs`
  ADD PRIMARY KEY (`log_id`);

ALTER TABLE `customers`
  ADD PRIMARY KEY (`customer_id`);

ALTER TABLE `customer_addresses`
  ADD PRIMARY KEY (`customer_address_id`),
  ADD KEY `customer_id` (`customer_id`);

ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`);

ALTER TABLE `employee_addresses`
  ADD PRIMARY KEY (`employee_addresses_id`),
  ADD KEY `employee_id` (`employee_id`);

ALTER TABLE `invoice`
  ADD PRIMARY KEY (`invoice_id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `employee_id` (`employee_id`);

ALTER TABLE `invoice_item`
  ADD PRIMARY KEY (`invoice_item_id`),
  ADD KEY `invoice_id` (`invoice_id`),
  ADD KEY `product_id` (`product_id`);

ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
