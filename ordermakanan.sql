-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 22, 2024 at 01:45 PM
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
-- Database: `ordermakanan`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id_admin` varchar(5) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id_admin`, `username`, `password`) VALUES
('A01', 'rolis', '123'),
('A02', 'rafa', '123'),
('A03', 'seno', '123'),
('A04', 'syafiq', '123');

-- --------------------------------------------------------

--
-- Table structure for table `keranjang`
--

CREATE TABLE `keranjang` (
  `keranjang_id` int(11) NOT NULL,
  `menu_id` varchar(10) DEFAULT NULL,
  `nama_menu` varchar(50) DEFAULT NULL,
  `harga` decimal(10,2) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `keranjang`
--

INSERT INTO `keranjang` (`keranjang_id`, `menu_id`, `nama_menu`, `harga`, `jumlah`, `total`) VALUES
(1, 'P001', 'Beef Burger', 20000.00, 2, 40000.00),
(2, 'P001', 'Beef Burger', 20000.00, 3, 60000.00),
(3, 'P001', 'Beef Burger', 20000.00, 3, 60000.00),
(4, 'P001', 'Beef Burger', 20000.00, 3, 60000.00),
(5, 'P001', 'Beef Burger', 20000.00, 3, 60000.00),
(6, 'P001', 'Beef Burger', 20000.00, 3, 60000.00),
(7, 'P001', 'Beef Burger', 20000.00, 1, 20000.00),
(8, 'P001', 'Beef Burger', 20000.00, 1, 20000.00),
(9, 'P001', 'Beef Burger', 20000.00, 4, 80000.00),
(10, 'P001', 'Beef Burger', 20000.00, 4, 80000.00),
(11, 'P001', 'Beef Burger', 20000.00, 4, 80000.00),
(12, 'P001', 'Beef Burger', 20000.00, 4, 80000.00),
(13, 'P001', 'Beef Burger', 20000.00, 2, 40000.00),
(14, 'P001', 'Beef Burger', 20000.00, 3, 60000.00),
(15, 'P001', 'Beef Burger', 20000.00, 2, 40000.00),
(16, 'P005', 'HotDog', 17000.00, 3, 51000.00),
(17, 'P001', 'Beef Burger', 20000.00, 3, 60000.00),
(18, 'P001', 'Beef Burger', 20000.00, 2, 40000.00);

-- --------------------------------------------------------

--
-- Table structure for table `meja`
--

CREATE TABLE `meja` (
  `meja_id` varchar(2) NOT NULL,
  `no_meja` int(2) NOT NULL,
  `status` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `meja`
--

INSERT INTO `meja` (`meja_id`, `no_meja`, `status`) VALUES
('M1', 1, 0),
('M2', 2, 1),
('M3', 3, 0),
('M4', 4, 0),
('M5', 5, 1),
('M6', 6, 1),
('M7', 7, 1),
('M8', 8, 1),
('M9', 9, 1);

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `menu_id` varchar(50) NOT NULL,
  `nama_menu` varchar(50) NOT NULL,
  `kategori` enum('promo','makanan','minuman','lainnya') DEFAULT NULL,
  `harga` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`menu_id`, `nama_menu`, `kategori`, `harga`) VALUES
('L001', 'Ice Cream Corn', 'lainnya', 12000.00),
('L002', 'Gedagedi Nuggets', 'lainnya', 20000.00),
('L003', 'Onion Rings', 'lainnya', 15000.00),
('L004', 'Ice Cream Cup', 'lainnya', 10000.00),
('L005', 'Cookies', 'lainnya', 10000.00),
('MA001', 'Deluxe Burger', 'makanan', 32000.00),
('MA002', 'Chicken Burger', 'makanan', 30000.00),
('MA003', 'Ayam + Nasi', 'makanan', 15000.00),
('MA004', 'Chicken Bucket', 'makanan', 95000.00),
('MA005', 'Rice', 'makanan', 6000.00),
('MI001', 'Ice Cappucino', 'minuman', 10000.00),
('MI002', 'Ice Chocolate', 'minuman', 10000.00),
('MI003', 'Kelp Special Edition', 'minuman', 75000.00),
('MI004', 'Coca Cola', 'minuman', 8000.00),
('MI005', 'Le Mineral', 'minuman', 5000.00),
('P001', 'Beef Burger', 'promo', 20000.00),
('P002', 'Chezzzz Burger', 'promo', 25000.00),
('P003', 'French Fries', 'promo', 12000.00),
('P004', 'ChocoNut Ais', 'promo', 7000.00),
('P005', 'HotDog', 'promo', 17000.00);

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `pelanggan_id` varchar(10) NOT NULL,
  `nama_pelanggan` varchar(255) NOT NULL,
  `no_meja` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`pelanggan_id`, `nama_pelanggan`, `no_meja`) VALUES
('PE001', 'rafa', 1),
('PE002', 'rafa#', 2),
('PE003', 'rafa', 1),
('PE004', 'rafa', 1),
('PE005', 'rafa', 1),
('PE006', 'rafa#', 1),
('PE007', 'rafa', 1),
('PE008', 'rafa', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indexes for table `keranjang`
--
ALTER TABLE `keranjang`
  ADD PRIMARY KEY (`keranjang_id`);

--
-- Indexes for table `meja`
--
ALTER TABLE `meja`
  ADD PRIMARY KEY (`meja_id`),
  ADD UNIQUE KEY `no_meja` (`no_meja`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`menu_id`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`pelanggan_id`),
  ADD KEY `no_meja` (`no_meja`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `keranjang`
--
ALTER TABLE `keranjang`
  MODIFY `keranjang_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD CONSTRAINT `pelanggan_ibfk_1` FOREIGN KEY (`no_meja`) REFERENCES `meja` (`no_meja`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
