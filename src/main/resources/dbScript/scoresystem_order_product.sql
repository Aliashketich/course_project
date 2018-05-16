-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: scoresystem
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `order_product`
--

DROP TABLE IF EXISTS `order_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_product` (
  `order_idorder` int(10) unsigned NOT NULL ,
  `product_idproduct` int(10) unsigned NOT NULL ,
  `quantity` int(10) unsigned DEFAULT '1',
  PRIMARY KEY (`order_idorder`,`product_idproduct`),
  KEY `fk_order_has_product_product1_idx` (`product_idproduct`),
  KEY `fk_order_has_product_order1_idx` (`order_idorder`),
  CONSTRAINT `fk_order_has_product_order1` FOREIGN KEY (`order_idorder`) REFERENCES `order` (`idorder`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_has_product_product1` FOREIGN KEY (`product_idproduct`) REFERENCES `menu` (`idproduct`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Таблица предназначена для хранения информации о перечне продуктов из меню, которые были добавлены в конкретный заказ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_product`
--

LOCK TABLES `order_product` WRITE;
/*!40000 ALTER TABLE `order_product` DISABLE KEYS */;
INSERT INTO `order_product` VALUES (1,2,1),(1,72,1),(1,77,1),(1,82,1),(1,111,1),(1,116,1),(1,128,1),(2,3,1),(2,71,1),(2,78,1),(2,85,1),(2,113,1),(2,133,1),(3,7,2),(3,75,2),(3,83,2),(3,90,1),(3,115,2),(3,128,1),(4,74,2),(4,80,1),(4,92,1),(4,101,1),(4,108,1),(4,116,2),(4,129,2),(5,6,3),(5,78,2),(5,89,1),(5,90,1),(5,102,2),(5,121,2),(5,134,3),(6,7,2),(6,86,1),(6,102,1),(6,125,1),(8,74,3),(8,77,2),(8,81,1),(8,85,2),(8,92,1),(8,100,1),(8,125,1),(9,74,2),(9,80,1),(9,84,1),(9,107,1),(9,115,3),(9,120,1),(9,134,1),(10,71,1),(10,80,1),(10,105,1),(10,127,1),(11,2,3),(11,72,1),(11,85,2),(11,109,2),(11,125,1),(11,126,3),(12,7,1),(12,78,2),(12,85,1),(12,92,1),(12,111,1),(12,118,1),(12,135,1),(13,5,4),(13,80,1),(13,133,1),(14,115,1),(14,120,1),(14,128,2),(15,99,1),(15,113,1),(15,116,1),(15,124,1),(16,119,3),(16,122,1),(16,129,1),(16,130,1),(17,5,1),(17,76,1),(17,85,1),(17,123,1),(17,126,1);
/*!40000 ALTER TABLE `order_product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-17  1:57:57
