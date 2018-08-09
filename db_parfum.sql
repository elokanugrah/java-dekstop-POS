-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 26, 2016 at 05:42 AM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_parfum`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `username` varchar(19) NOT NULL,
  `password` varchar(35) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('root', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `tb_botol`
--

DROP TABLE IF EXISTS `tb_botol`;
CREATE TABLE IF NOT EXISTS `tb_botol` (
  `kode_botol` varchar(7) NOT NULL,
  `nama_botol` varchar(55) NOT NULL,
  `harga_botol` int(7) NOT NULL,
  PRIMARY KEY (`kode_botol`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_botol`
--

INSERT INTO `tb_botol` (`kode_botol`, `nama_botol`, `harga_botol`) VALUES
('BTL000', 'Tanpa Botol', 0),
('BTL001', 'Botol 15 ml', 7000),
('BTL002', 'Botol 30 ml', 75000);

-- --------------------------------------------------------

--
-- Table structure for table `tb_parfum`
--

DROP TABLE IF EXISTS `tb_parfum`;
CREATE TABLE IF NOT EXISTS `tb_parfum` (
  `kode_parfum` varchar(7) NOT NULL,
  `nama_parfum` varchar(55) NOT NULL,
  `harga_ml` int(7) NOT NULL,
  PRIMARY KEY (`kode_parfum`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_parfum`
--

INSERT INTO `tb_parfum` (`kode_parfum`, `nama_parfum`, `harga_ml`) VALUES
('PRF0004', 'Parfum Murahan', 9000),
('PRF001', 'Bulgari', 175000),
('PRF002', 'Hugo Boss', 150000);

-- --------------------------------------------------------

--
-- Table structure for table `tb_transaksi`
--

DROP TABLE IF EXISTS `tb_transaksi`;
CREATE TABLE IF NOT EXISTS `tb_transaksi` (
  `kode_transaksi` varchar(9) NOT NULL DEFAULT '0',
  `tanggal` date NOT NULL,
  `kode_parfum` varchar(7) NOT NULL,
  `kode_botol` varchar(7) DEFAULT NULL,
  `bibit` int(3) DEFAULT '1',
  `campuran` int(3) DEFAULT '0',
  `jumlah` int(9) NOT NULL,
  `total` int(9) NOT NULL,
  PRIMARY KEY (`kode_transaksi`),
  KEY `fg_kode_parfum` (`kode_parfum`),
  KEY `fg_kode_botol` (`kode_botol`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Triggers `tb_transaksi`
--
DROP TRIGGER IF EXISTS `tg_transaksi_insert`;
DELIMITER //
CREATE TRIGGER `tg_transaksi_insert` BEFORE INSERT ON `tb_transaksi`
 FOR EACH ROW BEGIN
  INSERT INTO transaksi_seq VALUES (NULL);
  SET NEW.kode_transaksi = CONCAT('TRK', LPAD(LAST_INSERT_ID(), 4, '0'));
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_seq`
--

DROP TABLE IF EXISTS `transaksi_seq`;
CREATE TABLE IF NOT EXISTS `transaksi_seq` (
  `kode_transaksi` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`kode_transaksi`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_transaksi`
--
ALTER TABLE `tb_transaksi`
  ADD CONSTRAINT `tb_transaksi_ibfk_1` FOREIGN KEY (`kode_parfum`) REFERENCES `tb_parfum` (`kode_parfum`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_transaksi_ibfk_2` FOREIGN KEY (`kode_botol`) REFERENCES `tb_botol` (`kode_botol`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
