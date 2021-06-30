CREATE DATABASE  IF NOT EXISTS `deliverymanagementservicedb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `deliverymanagementservicedb`;
-- MySQL dump 10.13  Distrib 8.0.25, for Linux (x86_64)
--
-- Host: localhost    Database: canjeardb
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
-- Table structure for table `direct_post_pickup`
--

DROP TABLE IF EXISTS `direct_post_pickup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direct_post_pickup` (
  `pickup_id` int NOT NULL AUTO_INCREMENT,
  `post_id` int DEFAULT NULL,
  `quotation_id` int DEFAULT NULL,
  `staff_id` int DEFAULT NULL,
  `pickup_instructions` varchar(200) DEFAULT NULL,
  `pickup_scheduled_date` date DEFAULT NULL,
  `pickup_scheduled_begin_time` time DEFAULT NULL,
  `pickup_scheduled_end_time` time DEFAULT NULL,
  `pickup_comments` varchar(200) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pickup_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payment_order`
--

DROP TABLE IF EXISTS `payment_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_order` (
  `payment_order_id` int NOT NULL AUTO_INCREMENT,
  `pickup_id` int DEFAULT NULL,
  `mode_of_payment` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `final_settlement_amount` double DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`payment_order_id`),
  KEY `fk_payment_order_1_idx` (`pickup_id`),
  CONSTRAINT `payment_direct_pickup_fk` FOREIGN KEY (`pickup_id`) REFERENCES `direct_post_pickup` (`pickup_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payment_order_account`
--

DROP TABLE IF EXISTS `payment_order_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_order_account` (
  `payment_order_id` int NOT NULL AUTO_INCREMENT,
  `account_no` int DEFAULT NULL,
  `bank_nm` varchar(50) DEFAULT NULL,
  `ifsc_code` varchar(50) DEFAULT NULL,
  `account_holder_nm` varchar(50) DEFAULT NULL,
  `account_type` varchar(50) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`payment_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'canjeardb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-12  0:53:25
