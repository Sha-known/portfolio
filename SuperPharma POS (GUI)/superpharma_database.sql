-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 28, 2023 at 09:42 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `superpharma_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `dprod_sold`
--

CREATE TABLE `dprod_sold` (
  `product_id` int(20) NOT NULL,
  `product_name` varchar(40) NOT NULL,
  `price` int(10) NOT NULL,
  `stock_sold` int(20) NOT NULL,
  `remaining_stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dprod_sold`
--

INSERT INTO `dprod_sold` (`product_id`, `product_name`, `price`, `stock_sold`, `remaining_stock`) VALUES
(2, ' CEELING PLUS SYRUP 120ML', 140, 10, 73),
(41, 'APCEE 500MG', 3, 2, 98),
(43, 'BEAU-C', 3, 2, 98),
(56, 'CALACTATE 650MG', 144, 1, 99),
(6, 'CEELIN CHEWABLES 30\'S', 138, 2, 96),
(5, 'CEELIN PLUS DROPS 15ML', 82, 3, 96),
(4, 'CEELIN PLUS DROPS 30ML', 143, 1, 92),
(1, 'CEELIN PLUS SYRUP 250ML', 260, 85, -3),
(3, 'CEELIN PLUS SYRUP 60ML', 74, 2, 91),
(7, 'CEELIN SYRUP 250ML', 217, 1, 99),
(16, 'CENTRUM ADVANCE', 10, 4, 95),
(17, 'CENTRUM SILVER ADVANCE', 12, 1, 98),
(29, 'CHERIFER DROPS 15ML', 108, 1, 97),
(69, 'DOLAN FP ORAL DROPS 15ML', 80, 1, 99),
(14, 'ENERVON BOTTLE 30\'S', 185, 1, 99),
(28, 'GROWEE PEDTECT DROPS 15ML', 81, 2, 98),
(45, 'NEUROGEN-E 7+1', 92, 1, 99),
(19, 'NUTRILIN DROPS 15ML', 78, 1, 99),
(18, 'NUTROPLEX SYRUP 60ML', 79, 4, 96),
(15, 'REVICON FORTE', 5, 1, 98),
(30, 'RM CGF ORANGE FLAVOR 120', 105, 2, 96),
(42, 'SM-CEE 562.43MG', 6, 1, 97),
(44, 'SOVIT-CEE', 6, 2, 96),
(54, 'STRESSTABS BOTTLE 30\'S', 285, 1, 99),
(31, 'TIKI-TIKI STAR SYRUP 120ML', 108, 1, 93),
(32, 'TIKI-TIKI STAR SYRUP 60ML', 59, 3, 97);

-- --------------------------------------------------------

--
-- Table structure for table `product_list`
--

CREATE TABLE `product_list` (
  `product_id` int(20) NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `price` int(10) NOT NULL,
  `stock_quantity` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product_list`
--

INSERT INTO `product_list` (`product_id`, `product_name`, `price`, `stock_quantity`) VALUES
(1, 'CEELIN PLUS SYRUP 250ML', 260, -3),
(2, ' CEELING PLUS SYRUP 120ML', 140, 73),
(3, 'CEELIN PLUS SYRUP 60ML', 74, 91),
(4, 'CEELIN PLUS DROPS 30ML', 143, 92),
(5, 'CEELIN PLUS DROPS 15ML', 82, 96),
(6, 'CEELIN CHEWABLES 30\'S', 138, 96),
(7, 'CEELIN SYRUP 250ML', 217, 99),
(8, 'CEELIN SYRUP 120ML', 116, 100),
(9, 'CEELIN SYRUP 60ML', 63, 100),
(10, 'CEELIN DROPS 30ML', 124, 100),
(11, 'ENERVON SYRUP 500ML', 354, 100),
(12, 'ENERVON SYRUP 250ML', 235, 100),
(13, 'ENERVON-C FLEX TAB', 6, 100),
(14, 'ENERVON BOTTLE 30\'S', 185, 99),
(15, 'REVICON FORTE', 5, 98),
(16, 'CENTRUM ADVANCE', 10, 95),
(17, 'CENTRUM SILVER ADVANCE', 12, 98),
(18, 'NUTROPLEX SYRUP 60ML', 79, 96),
(19, 'NUTRILIN DROPS 15ML', 78, 99),
(20, 'APPEBON KID WITH IRON SYRUP 60ML', 100, 100),
(21, 'APPEBON WITH IRON', 19, 100),
(22, 'PROPAN TLC SYRUP 250ML', 272, 100),
(23, 'PROPAN TLC SYRUP 120ML', 152, 100),
(24, 'PROPAN TLC SYRUP 60ML', 92, 100),
(25, 'PROPAN TLC DROPS 15ML', 70, 100),
(26, 'PROPAN WITH IRON CAPS', 19, 92),
(27, 'GROWEE SYRUP 250ML', 281, 100),
(28, 'GROWEE PEDTECT DROPS 15ML', 81, 98),
(29, 'CHERIFER DROPS 15ML', 108, 97),
(30, 'RM CGF ORANGE FLAVOR 120', 105, 96),
(31, 'TIKI-TIKI STAR SYRUP 120ML', 108, 93),
(32, 'TIKI-TIKI STAR SYRUP 60ML', 59, 97),
(33, 'TIKI-TIKI PLUS DROPS 30ML', 78, 100),
(34, 'IMMUNO MAX SYRUP 120ML', 323, 99),
(35, 'IMMUNPRO 500MG', 7, 100),
(36, 'RM ZINC-C SYRUP 120ML', 106, 100),
(37, 'E-ZINC DROPS 15ML', 92, 100),
(38, 'E-ZINC SYRUP 60ML', 96, 100),
(39, 'CEETAB DAILY 500MG', 2, 100),
(40, 'RM ASCORBIC ACID 500MG', 2, 99),
(41, 'APCEE 500MG', 3, 98),
(42, 'SM-CEE 562.43MG', 6, 97),
(43, 'BEAU-C', 3, 98),
(44, 'SOVIT-CEE', 6, 96),
(45, 'NEUROGEN-E 7+1', 92, 99),
(46, 'RM VITAMIN B-COMPLEX', 4, 99),
(47, 'PHAREX VITAMIN B-COMPLEX', 72, 100),
(48, 'REVITAPLEX', 1, 100),
(49, 'DOLO NEUROBION', 24, 100),
(50, 'MAXVIT SOFTGEL CAPSULE', 13, 100),
(51, 'MYRA 400IU', 11, 100),
(52, 'MYRA 400IU BOTTLE 30\'S', 334, 100),
(53, 'STRESSTABS WITH IRON', 10, 100),
(54, 'STRESSTABS BOTTLE 30\'S', 285, 99),
(55, 'CALACTATE 325MG', 88, 100),
(56, 'CALACTATE 650MG', 144, 99),
(57, 'CALVIT PLAIN TAB', 7, 97),
(58, 'CALVIT GOLD 400IU + 600MG', 7, 100),
(59, '4G ANTIOXIDANT', 20, 100),
(60, 'LIVERMARIN 350MG', 8, 100),
(61, 'KIDNEY CARE', 16, 100),
(62, 'MX3 500MG', 16, 100),
(63, 'MX3 COFFEE MIX', 17, 100),
(64, 'C-LIUM FIBRE CAPSULE', 5, 100),
(65, 'EYE BERRY', 16, 100),
(66, 'SANGOBION KIDS SYRUP 100ML', 297, 100),
(67, 'SANGOBION BABY DROPS 15ML', 180, 100),
(68, 'HEMARATE FA', 21, 100),
(69, 'DOLAN FP ORAL DROPS 15ML', 80, 99),
(70, 'DOLAN FP 60ML (SUSPENSION)', 83, 99),
(71, 'DOLAN FP FORTE 200MG/5ML SUSPENSION 60ML', 131, 100),
(72, 'ADVIL SUSP 60ML', 81, 100),
(73, 'ADVIL LIQUID GEL 200MG', 9, 100),
(74, 'MEDICOL ADVANCE 400', 10, 92),
(75, 'MEDICOL 200MG', 6, 100),
(76, 'PANAIDE 200MG/325MG', 1, 100),
(77, 'ALAXAN FR CAP', 8, 100),
(78, 'ALAXAN FR 5+1 PROMO', 38, 100),
(79, 'SARIDON TRIPLE ACTION', 6, 100),
(80, 'NORGESIC FORTE TAB', 33, 100),
(81, 'RM MEFENAMIC 250MG', 4, 100),
(82, 'RM MEFENAMIC 500MG', 6, 100),
(83, 'ANALMIN 500MG', 1, 100),
(84, 'DOLFENAL 500MG', 28, 100),
(85, 'DOLFENAL 250MG', 15, 100),
(86, 'PONSTAN SF 500MG', 37, 100),
(87, 'PONSTAN SF 250MG', 17, 99),
(88, 'RM CELECOXIB 200MG', 20, 100),
(89, 'SAPHLECOX 200MG', 5, 100),
(90, 'FLACOXTO 200MG', 2, 100),
(91, 'RM NAPROXEN SODIUM', 6, 100),
(92, 'SKELAN 220MG', 9, 99),
(93, 'FLANAX FORTE 550MG', 23, 100),
(94, 'FLANAX 275MG', 11, 100),
(95, 'ARCOXIA 60MG', 55, 100),
(96, 'ARCOXIA 90MG', 61, 100),
(97, 'ARCOXIA 120MG', 75, 100),
(98, 'E-DOL 50MG', 1, 100),
(99, 'ALGESIA 37.5MG/325MG', 45, 100),
(100, 'DOLCET 325MG', 45, 99),
(101, 'RM PARACETAMOL 60MG 250MG/5ML SYRUP STRAWBERRY', 75, 100),
(102, 'RM PARACETAMOL 60ML 120MG/5ML SYRUP LEMON', 50, 100),
(103, 'CALPOL INFANT DROPS 10ML', 58, 100),
(104, 'CALPOL 120MG/5ML STRAWBERRY 2-6 YRS. OLD', 86, 100);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(10) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `password` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `user_name`, `password`) VALUES
(1, 'anthony', 'planos'),
(2, 'shanon', 'ticse'),
(3, 'froilan', 'baguio'),
(4, 'lynchee', 'circulo');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dprod_sold`
--
ALTER TABLE `dprod_sold`
  ADD UNIQUE KEY `product_name` (`product_name`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `product_list`
--
ALTER TABLE `product_list`
  ADD PRIMARY KEY (`product_id`),
  ADD UNIQUE KEY `product_name` (`product_name`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `user_name` (`user_name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `product_list`
--
ALTER TABLE `product_list`
  MODIFY `product_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
