-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: online-shop
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `price` decimal(7,2) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (13,'Samsung Galaxy S8',18000.00,'Samsung Galaxy S8 Android smartphone. Announced Mar 2017. Features 5.8&#8243; Super AMOLED display, Exynos 8895 Octa chipset, 12 MP primary camera, 8 MP front camera, 3000 mAh battery, 64 GB storage, 4 GB RAM, IP68 certified, Corning Gorilla Glass 5.'),(14,'Samsung Galaxy S7',17000.00,'Samsung Galaxy S7 Android smartphone. Announced Feb 2016. Features 5.1&#8243; Super AMOLED display, Exynos 8890 Octa chipset, 12 MP primary camera, 5 MP front camera, 3000 mAh battery, 64 GB storage, 4 GB RAM, IP68 certified, Corning Gorilla Glass 4.'),(15,'Samsung Galaxy S6',15000.00,'Samsung Galaxy S6 Android smartphone. Announced Mar 2015. Features 5.1&#8243; Super AMOLED display, Exynos 7420 Octa chipset, 16 MP primary camera, 5 MP front camera, 2550 mAh battery, 128 GB storage, 3 GB RAM, Corning Gorilla Glass 4.'),(16,'Samsung Galaxy S6 Silver',15000.00,'Samsung Galaxy S6 Android smartphone. Announced Mar 2015. Features 5.1&#8243; Super AMOLED display, Exynos 7420 Octa chipset, 16 MP primary camera, 5 MP front camera, 2550 mAh battery, 128 GB storage, 3 GB RAM, Corning Gorilla Glass 4.'),(17,'iPhone 8',25000.00,'Apple iPhone 8 smartphone. Announced Sep 2017. Features 4.7&#8243; LED-backlit IPS LCD display, Apple A11 Bionic chipset, 12 MP primary camera, 7 MP front camera, 1821 mAh battery, 256 GB storage, 2 GB RAM, IP67 certified, Ion-strengthened glass.'),(18,'1',1.00,'1');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-18 21:04:24
