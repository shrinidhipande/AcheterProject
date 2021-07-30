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
-- Table structure for table `address`
--
USE `acheteraccountservicedb`;
DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `address_line1` varchar(100) DEFAULT NULL,
  `address_line2` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `zip` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'F-53','BHAGAT SINGH MARKET','bangalore','karnataka','560041','India','2021-06-01 00:00:00','csr_manager','2021-06-01 00:00:00','csr_manager'),(2,'H-23','Masti Bazar','pune','maharastra','587422','India','2021-06-01 00:00:00','csr_manager','2021-06-01 00:00:00','csr_manager'),(3,'K-26','Near Ameerpt','hyderabad','Telenagana','500038','India','2021-06-01 00:00:00','csr_manager','2021-06-01 00:00:00','csr_manager'),(4,'N-14','OMR','chennai','chennai','600096','India','2021-06-01 00:00:00','csr_manager','2021-06-01 00:00:00','csr_manager'),(5,'S-18','Sasti Bazar','bangalore','karnataka','560041','India','2021-06-01 00:00:00','csr_manager','2021-06-01 00:00:00','csr_manager'),(6,'J-25','Near Maitrivanam','Hyderabad','Telengana','578899','India','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system'),(7,'P-09','Ambe Nagar','Mumbai','Maharshtra','440023','India','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system'),(8,'R-99','Near Daler Mehndi House, Singers Colony','Amritsar','Punjab','466898','India','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system'),(9,'W-99','Main Market Square','Amritsar','Punjab','466398','India','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system'),(10,'P-N52','HB Town','Pune','Maharshtra','365920','India','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system'),(11,'P-203','Modern Colony','Delhi','Delhi','101023','India','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system'),(12,'R-NH6','Civil Line','Indore','Madhya Pradesh','463582','India','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system'),(13,'P-F23','Small Factory Area','Vadodara','Gujarat','896532','India','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system'),(14,'7-2-108','maitrivanam','hyderabad','telangana','500073','India','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system'),(15,'ec-71-1','Sobha Dream Acres','bangalore','karnataka','560100','India','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system'),(16,'T-16','CSIR Road','chennai','tamil_nadu','600020','India','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system'),(17,'SN-17','Adarsh Nagar','pune','maharashtra','411005','India','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system'),(18,'4-1-21','jagadamba','visakhapatnam','andhra_pradesh','530020','india','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system'),(19,'p-42','thiruwalleswarar nagar anna nagar west','chennai','tamil_nadu','600050','india','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system'),(20,'15-2-42','brigade road ','bangalore','karnataka','560100','india','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-02 17:14:40
