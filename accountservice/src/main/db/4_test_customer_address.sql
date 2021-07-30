-- MySQL dump 10.13  Distrib 8.0.25, for Linux (x86_64)
--
-- Host: 35.224.215.231    Database: acheterdb
-- ------------------------------------------------------
-- Server version	8.0.25-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer_address`
--
USE `acheteraccountservicedb`;
DROP TABLE IF EXISTS `customer_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_address` (
  `customer_address_id` int NOT NULL AUTO_INCREMENT,
  `system_user_id` int DEFAULT NULL,
  `address_id` int DEFAULT NULL,
  `address_type` varchar(100) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`customer_address_id`),
  UNIQUE KEY `address_id_UNIQUE` (`address_id`),
  KEY `system_user_id_idx` (`system_user_id`),
  CONSTRAINT `address_id` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `system_user_id_fk` FOREIGN KEY (`system_user_id`) REFERENCES `system_users` (`system_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_address`
--

LOCK TABLES `customer_address` WRITE;
/*!40000 ALTER TABLE `customer_address` DISABLE KEYS */;
INSERT INTO `customer_address` VALUES (1,2,7,'Home','A','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system'),(2,6,8,'Home','A','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system'),(3,6,9,'Work','A','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system'),(4,3,10,'Work','A','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system'),(5,4,11,'Work','S','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system'),(6,5,12,'Home','S','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system'),(7,6,13,'Home','A','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system');
/*!40000 ALTER TABLE `customer_address` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-02 17:15:32
