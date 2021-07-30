CREATE DATABASE  IF NOT EXISTS `acheteraccountservicedb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `acheteraccountservicedb`;
-- MySQL dump 10.13  Distrib 8.0.25, for Linux (x86_64)
--
-- Host: localhost    Database: accountservicedb
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
-- Table structure for table `api_users`
--

DROP TABLE IF EXISTS `api_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `api_users` (
  `api_user_id` int NOT NULL,
  `api_key` varchar(50) NOT NULL,
  `secret` varchar(299) NOT NULL,
  `status` varchar(1) NOT NULL,
  `user_role` int DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`api_user_id`),
  UNIQUE KEY `api_user_id_UNIQUE` (`api_user_id`),
  UNIQUE KEY `api_key_UNIQUE` (`api_key`),
  KEY `user_key_role_idx` (`user_role`),
  CONSTRAINT `user_key_role` FOREIGN KEY (`user_role`) REFERENCES `user_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cities` (
  `city_id` int NOT NULL AUTO_INCREMENT,
  `city_name` varchar(100) NOT NULL,
  PRIMARY KEY (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contact_details`
--

DROP TABLE IF EXISTS `contact_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact_details` (
  `contact_details_id` int NOT NULL AUTO_INCREMENT,
  `primary_contact_no` varchar(13) DEFAULT NULL,
  `secondary_contact_no` varchar(13) DEFAULT NULL,
  `primary_email_address` varchar(100) DEFAULT NULL,
  `secondary_email_address` varchar(100) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`contact_details_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customer_address`
--

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
-- Table structure for table `identity_proof_type`
--

DROP TABLE IF EXISTS `identity_proof_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `identity_proof_type` (
  `identity_proof_type_id` int NOT NULL AUTO_INCREMENT,
  `identity_proof_type_nm` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`identity_proof_type_id`),
  UNIQUE KEY `identity_proof_type_nm_UNIQUE` (`identity_proof_type_nm`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `service_area`
--

DROP TABLE IF EXISTS `service_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_area` (
  `service_area_id` int NOT NULL AUTO_INCREMENT,
  `area_nm` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `pincode` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`service_area_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store` (
  `store_id` int NOT NULL AUTO_INCREMENT,
  `business_nm` varchar(50) NOT NULL,
  `license_no` varchar(50) NOT NULL,
  `registered_dt` datetime NOT NULL,
  `owner_user_details_id` int DEFAULT NULL,
  `store_contact_details_id` int DEFAULT NULL,
  `store_address_id` int DEFAULT NULL,
  `opening_time` varchar(50) DEFAULT NULL,
  `closing_time` varchar(50) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`store_id`),
  UNIQUE KEY `business_nm_UNIQUE` (`business_nm`),
  UNIQUE KEY `license_no_UNIQUE` (`license_no`),
  KEY `fk_store_contact_details_id_idx` (`store_contact_details_id`),
  KEY `fk_store_store_address_id_idx` (`store_address_id`),
  KEY `fk_store_owner_details_id_idx` (`owner_user_details_id`),
  CONSTRAINT `fk_store_contact_details_id` FOREIGN KEY (`store_contact_details_id`) REFERENCES `contact_details` (`contact_details_id`),
  CONSTRAINT `fk_store_store_address_id` FOREIGN KEY (`store_address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `store_enquiry`
--

DROP TABLE IF EXISTS `store_enquiry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store_enquiry` (
  `store_enquiry_id` int NOT NULL AUTO_INCREMENT,
  `contact_person_nm` varchar(50) DEFAULT NULL,
  `email_address` varchar(100) DEFAULT NULL,
  `mobile_no` varchar(13) DEFAULT NULL,
  `alt_mobile_no` varchar(13) DEFAULT NULL,
  `preferrable_timings` varchar(50) DEFAULT NULL,
  `enquiry_dt` datetime DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`store_enquiry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `store_service_areas`
--

DROP TABLE IF EXISTS `store_service_areas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store_service_areas` (
  `store_service_area_id` int NOT NULL AUTO_INCREMENT,
  `store_id` int NOT NULL,
  `service_area_id` int NOT NULL,
  `status` varchar(1) NOT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`store_service_area_id`),
  KEY `store_id_idx` (`store_id`),
  KEY `service_area_id_idx` (`service_area_id`),
  CONSTRAINT `service_area_id` FOREIGN KEY (`service_area_id`) REFERENCES `service_area` (`service_area_id`),
  CONSTRAINT `store_id` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `store_staff`
--

DROP TABLE IF EXISTS `store_staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store_staff` (
  `store_staff_id` int NOT NULL AUTO_INCREMENT,
  `store_id` int NOT NULL,
  `staff_id` int NOT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`store_staff_id`),
  KEY `fk_store_staff_stored_id_idx` (`store_id`),
  KEY `fk_store_staff_id_idx` (`staff_id`),
  CONSTRAINT `fk_store_staff_id` FOREIGN KEY (`staff_id`) REFERENCES `system_users` (`system_user_id`),
  CONSTRAINT `fk_store_staff_store_id` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `store_terms_and_conditions`
--

DROP TABLE IF EXISTS `store_terms_and_conditions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store_terms_and_conditions` (
  `store_terms_and_conditions_id` int NOT NULL AUTO_INCREMENT,
  `store_id` int NOT NULL,
  `annual_maintainance_charges` double DEFAULT NULL,
  `direct_procure_percentage_commission` double DEFAULT NULL,
  `direct_sell_percentage_commission` double DEFAULT NULL,
  `exchange_percentage_commission` double DEFAULT NULL,
  `billing_cycle_period` varchar(50) DEFAULT NULL,
  `effective_start_dt` datetime DEFAULT NULL,
  `effective_end_dt` datetime DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`store_terms_and_conditions_id`),
  KEY `store_id_idx` (`store_id`),
  CONSTRAINT `store_id_fk` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `system_users`
--

DROP TABLE IF EXISTS `system_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_users` (
  `system_user_id` int NOT NULL AUTO_INCREMENT,
  `email_address` varchar(100) DEFAULT NULL,
  `mobile_no` varchar(13) DEFAULT NULL,
  `first_nm` varchar(50) DEFAULT NULL,
  `last_nm` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `display_nm` varchar(50) DEFAULT NULL,
  `email_verification_code` varchar(50) DEFAULT NULL,
  `email_verification_code_generated_dt` date DEFAULT NULL,
  `otp_code` varchar(50) DEFAULT NULL,
  `otp_code_generated_dt` date DEFAULT NULL,
  `user_role_id` int NOT NULL,
  `status` char(1) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(30) DEFAULT NULL,
  `email_verification_code_status` tinyint(1) DEFAULT NULL,
  `otp_code_status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`system_user_id`),
  UNIQUE KEY `email_address_UNIQUE` (`email_address`),
  UNIQUE KEY `mobile_no_UNIQUE` (`mobile_no`),
  UNIQUE KEY `display_name_UNIQUE` (`display_nm`),
  KEY `user_role_id_idx` (`user_role_id`),
  CONSTRAINT `user_role_id` FOREIGN KEY (`user_role_id`) REFERENCES `user_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_details`
--

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
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_nm` varchar(100) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_nm_UNIQUE` (`role_nm`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-11 22:07:41
