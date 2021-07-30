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
-- Table structure for table `system_users`
--
USE `acheteraccountservicedb`;
DROP TABLE IF EXISTS `system_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_users` (
  `system_user_id` int NOT NULL AUTO_INCREMENT,
  `email_address` varchar(100) DEFAULT NULL,
  `mobile_no` varchar(13) DEFAULT NULL,
  `first_nm` varchar(50) DEFAULT NULL,
  `last_nm` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `display_nm` varchar(50) DEFAULT NULL,
  `email_verification_code` varchar(50) DEFAULT NULL,
  `email_verification_code_generated_dt` date DEFAULT NULL,
  `otp_code` varchar(50) DEFAULT NULL,
  `otp_code_generated_dt` date DEFAULT NULL,
  `user_role_id` int NOT NULL,
  `status` char(1) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`system_user_id`),
  UNIQUE KEY `email_address_UNIQUE` (`email_address`),
  UNIQUE KEY `mobile_no_UNIQUE` (`mobile_no`),
  UNIQUE KEY `display_name_UNIQUE` (`display_nm`),
  KEY `user_role_id_idx` (`user_role_id`),
  CONSTRAINT `user_role_id` FOREIGN KEY (`user_role_id`) REFERENCES `user_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_users`
--

LOCK TABLES `system_users` WRITE;
/*!40000 ALTER TABLE `system_users` DISABLE KEYS */;
INSERT INTO `system_users` VALUES (1,'acheter@s-force.org',NULL,'acheteradmin',NULL,'welcome1','acheteradmin',NULL,NULL,NULL,NULL,1,NULL,'2021-05-31 00:00:00','system','2021-05-31 00:00:00','system'),(2,'vinayp@gmail.com','799452155','vinay','Pathak','welcome2','vinay','fe944','2021-05-01','46667','2021-05-01',2,'a','2021-05-01 00:00:00','system','2021-05-01 00:00:00','system'),(3,'kishore@gmail.com','9899899879','Kishore','Kumar','welcome2','Kishore','9090','2021-06-01',NULL,NULL,2,'a','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system'),(4,'ravigcp7@gmail.com','9502383661','Raveendra','kutla','welcome2','Ravi','','2021-06-01',NULL,NULL,2,'a','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system'),(5,'naveen@gamil.com','890987654','naveen','kumar','welcome2','naveen k','abc987','2021-06-01','87654','2021-06-01',2,'a','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system'),(6,'sagar.reddy@gmail.com','487658789','sagar','reddy','welcome2','sagar_r','x19848','2021-06-01','489988','2021-06-01',2,'a','2021-06-01 00:00:00','system','2021-06-01 00:00:00','system'),(7,'nilesh.sisode@yahoo.com','987564125','nilesh','sisode','welcome2','nilesh','dgfn23','2021-06-01','778',NULL,3,'a','2021-06-01 00:00:00','csr_manager','2021-06-01 00:00:00','csr_manager'),(8,'umang@gmail.com','999994323','Umang','Goyal','welcome2','Umang','dfr45','2021-06-01',NULL,NULL,3,'a','2021-06-01 00:00:00','csr_manager','2021-06-01 00:00:00','csr_manager'),(9,'sumalathad@gmail.com','8106882558','sumalatha','dubbuka','welcome2','Sumalatha',NULL,'2021-06-01',NULL,NULL,3,'a','2021-06-01 00:00:00','csr_manager','2021-06-01 00:00:00','csr_manager'),(10,'maheshr@gmail.com','6567890987','mahesh','babu','welcome2','mahesh r','czxv32','2021-06-01','987890','2021-06-01',3,'a','2021-06-01 00:00:00','csr_manager','2021-06-01 00:00:00','csr_manager'),(11,'mitesh.basatani@gmail.com','896789376','mitesh','basantani','welcome2','mitesh_basantani','d93844','2021-06-01','789933','2021-06-01',3,'a','2021-06-01 00:00:00','csr_manager','2021-06-01 00:00:00','csr_manager'),(12,'dinesh.sarang@reddifmail.com','7845962547','dinesh','sarang','welcome2','dinesh','wdw35','2021-06-01','784519','2021-06-01',6,'a','2021-06-01 00:00:00','store_owner','2021-06-01 00:00:00','store_owner'),(13,'praful.patil@hotmail.com','8874594744','praful','patil','welcome2','patil praful','dwrw45','2021-06-01','476922','2021-06-01',7,'a','2021-06-01 00:00:00','store_owner','2021-06-01 00:00:00','store_owner'),(14,'krishna.kumar@gmail.com','9876546789','krishna','kumar','welcome2','krishna kumar','d5thdfg','2021-06-01','876543','2021-06-01',6,'a','2021-06-01 00:00:00','store_owner','2021-06-01 00:00:00','store_owner'),(15,'deekshasharma@gmail.com','9876541231','deeksha','sharma','welcome2','deeksha','dx58e','2021-06-01','8756','2021-06-01',7,'a','2021-06-01 00:00:00','store_owner','2021-06-01 00:00:00','store_owner'),(20,'abhishek.jadhav@gmail.com','785936478','abhishek','jadhav','welcome2','abhishek_jadhav','n8869','2021-06-01','789834','2021-06-01',6,'a','2021-06-01 00:00:00','store_owner','2021-06-01 00:00:00','store_owner'),(21,'ajay.malya@gmail.com','783567846','ajay','malya','welcome2','ajay_malya','u8364','2021-06-01','562354','2021-06-01',7,'a','2021-06-01 00:00:00','store_owner','2021-06-01 00:00:00','store_owner'),(22,'atulagre@gmail.com','9595236540','atul','agre','welcome2','atul','shw345','2021-06-01','3947','2021-06-01',4,'a','2021-06-01 00:00:00','store_owner','2021-06-01 00:00:00','store_owner'),(23,'kambleamit@gmail.com','86953478926','amit','kamble','welcome2','amit','dju354','2021-06-01','5264','2021-06-01',4,'a','2021-06-01 00:00:00','store_owner','2021-06-01 00:00:00','store_owner'),(24,'chinna15088@gmail.com','9704763218','swapna','g','welcome2','swapna','6uh78','2021-06-01','879777','2021-06-01',5,'a','2021-06-01 00:00:00','csr_manager','2021-06-01 00:00:00','csr_manager'),(25,'sharad.mca@gmail.com','9865324578','sudanshu','sharad','welcome2','sudanshu','d475a','2021-06-01','786245','2021-06-01',5,'a','2021-06-01 00:00:00','csr_manager','2021-06-01 00:00:00','csr_manager');
/*!40000 ALTER TABLE `system_users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-02 17:13:48
