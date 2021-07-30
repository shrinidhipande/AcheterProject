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
-- Table structure for table `user_details`
--
USE `acheteraccountservicedb`;
DROP TABLE IF EXISTS `user_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_details` (
  `system_user_id` int NOT NULL,
  `dob` date DEFAULT NULL,
  `gender` varchar(15) DEFAULT NULL,
  `contact_details_id` int DEFAULT NULL,
  `csr_primary_address_id` varchar(100) DEFAULT NULL,
  `csr_secondary_address_id` varchar(100) DEFAULT NULL,
  `identity_proof_type_id` int DEFAULT NULL,
  `identity_proof_value` varchar(100) DEFAULT NULL,
  `issued_authority` varchar(100) DEFAULT NULL,
  `joining_date` date DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`system_user_id`),
  KEY `contact_details_id_idx` (`contact_details_id`),
  KEY `identity_proof_type_id_idx` (`identity_proof_type_id`),
  CONSTRAINT `contact_details_id` FOREIGN KEY (`contact_details_id`) REFERENCES `contact_details` (`contact_details_id`),
  CONSTRAINT `identity_proof_type_id` FOREIGN KEY (`identity_proof_type_id`) REFERENCES `identity_proof_type` (`identity_proof_type_id`),
  CONSTRAINT `system_user_id` FOREIGN KEY (`system_user_id`) REFERENCES `system_users` (`system_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_details`
--

LOCK TABLES `user_details` WRITE;
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
INSERT INTO `user_details` VALUES (7,'1990-04-14','M',1,NULL,NULL,2,'158422544855','GOV. INDIA','2021-06-01','2021-06-01 00:00:00','csr_manager','2021-06-01 00:00:00','csr_manager'),(8,'1986-02-01','M',2,NULL,NULL,1,'YU89938','Govt of India','2021-06-01','2021-06-01 00:00:00','csr_manager','2021-06-01 00:00:00','csr_manager'),(9,'1993-06-16','f',3,'','',2,'443489494','Govt.of india','2021-06-01','2021-06-01 00:00:00','csr_manager','2021-06-01 00:00:00','csr_manager'),(11,'1982-08-12','m',5,'',NULL,4,'676534546787','GOV. Of INDIA','2021-06-01','2021-06-01 00:00:00','csr_manager','2021-06-01 00:00:00','csr_manager'),(12,'1999-12-23','m',19,NULL,NULL,1,'895A636C','Govt of Maharashtra','2021-06-01','2021-06-01 00:00:00','store_owner','2021-06-01 00:00:00','store_owner'),(13,'2000-11-12','M',20,NULL,NULL,3,'5362AER','Govt of Maharashtra','2021-06-01','2021-06-01 00:00:00','csr_manager','2021-06-01 00:00:00','csr_manager'),(14,'1995-08-27','M',8,NULL,NULL,2,'456787652345','Gov Of India','2021-06-01','2021-06-01 00:00:00','store_owner','2021-06-01 00:00:00','store_owner'),(15,'1994-01-01','F',6,NULL,NULL,3,'SUM987','Central Govt','2021-06-01','2021-06-01 00:00:00','store_owner','2021-06-01 00:00:00','store_owner'),(20,'1990-06-16','M',17,NULL,NULL,4,'898765489812','Gov. of India','2021-06-01','2021-06-01 00:00:00','store_owner','2021-06-01 00:00:00','store_owner'),(21,'1982-08-02','M',18,NULL,NULL,4,'879379781245','Gov. of India','2021-06-01','2021-06-01 00:00:00','store_owner','2021-06-01 00:00:00','store_owner'),(22,'1992-02-22','M',14,NULL,NULL,4,'253614789365','Govt of India','2021-06-01','2021-06-01 00:00:00','store_owner','2021-06-01 00:00:00','store_owner'),(23,'1993-10-25','M',13,NULL,NULL,2,'ASV1216','Govt of India','2021-06-01','2021-06-01 00:00:00','store_owner','2021-06-01 00:00:00','store_owner'),(24,'1970-03-20','M',16,NULL,NULL,4,'895636981247','Govt of India','2021-06-01','2021-06-01 00:00:00','csr_manager','2021-06-01 00:00:00','store_owner'),(25,'1980-01-21','M',7,NULL,NULL,1,'589461266','Govt of India','2021-06-01','2021-06-01 00:00:00','csr_manager','2021-06-01 00:00:00','store_owner');
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-02 17:16:32
