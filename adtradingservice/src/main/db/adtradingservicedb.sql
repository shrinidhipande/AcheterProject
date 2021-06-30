CREATE DATABASE  IF NOT EXISTS `acheteradstradingservicedb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `acheteradstradingservicedb`;
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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_no` int NOT NULL AUTO_INCREMENT,
  `category_nm` varchar(50) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `display_sequence_no` int DEFAULT NULL,
  `parent_category_no` int DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`category_no`),
  UNIQUE KEY `category_nm_UNIQUE` (`category_nm`),
  UNIQUE KEY `display_sequence_no_UNIQUE` (`display_sequence_no`),
  KEY `parent_category_fk_idx` (`parent_category_no`),
  CONSTRAINT `parent_category_fk` FOREIGN KEY (`parent_category_no`) REFERENCES `category` (`category_no`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `classified_images`
--

DROP TABLE IF EXISTS `classified_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classified_images` (
  `classified_image_id` int NOT NULL AUTO_INCREMENT,
  `post_id` int DEFAULT NULL,
  `image_id` int DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`classified_image_id`),
  UNIQUE KEY `image_id_UNIQUE` (`image_id`),
  KEY `post_id_idx` (`post_id`),
  CONSTRAINT `image_id` FOREIGN KEY (`image_id`) REFERENCES `images` (`image_id`),
  CONSTRAINT `post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `classified_post`
--

DROP TABLE IF EXISTS `classified_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classified_post` (
  `post_id` int NOT NULL,
  `classified_type_id` int DEFAULT NULL,
  `expired_dt` datetime DEFAULT NULL,
  `classified_fee` double DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  KEY `classified_post_classified_type_id_fk_idx` (`classified_type_id`),
  CONSTRAINT `classified_post_classified_type_id_fk` FOREIGN KEY (`classified_type_id`) REFERENCES `classified_types` (`classified_type_id`),
  CONSTRAINT `classified_post_post_id_fk` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `classified_types`
--

DROP TABLE IF EXISTS `classified_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classified_types` (
  `classified_type_id` int NOT NULL AUTO_INCREMENT,
  `classified_type_nm` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`classified_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `direct_sell_post`
--

DROP TABLE IF EXISTS `direct_sell_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direct_sell_post` (
  `post_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  KEY `product_id_fk_idx` (`product_id`),
  CONSTRAINT `product_id_fk` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `global_post_keys`
--

DROP TABLE IF EXISTS `global_post_keys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `global_post_keys` (
  `key_id` int NOT NULL AUTO_INCREMENT,
  `key_nm` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`key_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `images` (
  `image_id` int NOT NULL AUTO_INCREMENT,
  `image_nm` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `image_file` blob,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `post_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `posted_dt` datetime DEFAULT NULL,
  `post_owner_id` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `post_type_id` int DEFAULT NULL,
  `contact_details_id` int DEFAULT NULL,
  `service_area_id` int DEFAULT NULL,
  `post_address_id` int DEFAULT NULL,
  `views` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  KEY `category_id_fk_idx` (`category_id`),
  KEY `post_type_id_fk_idx` (`post_type_id`),
  CONSTRAINT `category_id_fk` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_no`),
  CONSTRAINT `post_type_id_fk` FOREIGN KEY (`post_type_id`) REFERENCES `post_type` (`post_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `post_keys`
--

DROP TABLE IF EXISTS `post_keys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_keys` (
  `post_key_id` int NOT NULL AUTO_INCREMENT,
  `post_id` int DEFAULT NULL,
  `key_id` int DEFAULT NULL,
  `key_value` varchar(45) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`post_key_id`),
  KEY `post_id_idx` (`post_id`),
  KEY `key_id_idx` (`key_id`),
  CONSTRAINT `key_id_fk` FOREIGN KEY (`key_id`) REFERENCES `global_post_keys` (`key_id`),
  CONSTRAINT `post_id_fk` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `post_type`
--

DROP TABLE IF EXISTS `post_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_type` (
  `post_type_id` int NOT NULL AUTO_INCREMENT,
  `post_type_nm` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`post_type_id`),
  UNIQUE KEY `post_type_nm_UNIQUE` (`post_type_nm`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `post_user_enquiries`
--

DROP TABLE IF EXISTS `post_user_enquiries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_user_enquiries` (
  `classified_user_enquiries_id` int NOT NULL AUTO_INCREMENT,
  `post_id` int DEFAULT NULL,
  `email_address` varchar(50) DEFAULT NULL,
  `mobile_no` varchar(45) DEFAULT NULL,
  `contact_nm` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`classified_user_enquiries_id`),
  KEY `post_id_fk_idx` (`post_id`),
  CONSTRAINT `post_user_enquires_post_id_fk` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `product_nm` varchar(50) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `manufacturer` varchar(50) DEFAULT NULL,
  `year` year DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `product_category_fk_idx` (`category_id`),
  CONSTRAINT `product_category_fk` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_no`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product_exchange_request`
--

DROP TABLE IF EXISTS `product_exchange_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_exchange_request` (
  `exchange_request_no` int NOT NULL AUTO_INCREMENT,
  `customer_product_id` int DEFAULT NULL,
  `store_product_id` int DEFAULT NULL,
  `exchange_request_placed_dt` datetime DEFAULT NULL,
  `customer_product_service_area_id` int DEFAULT NULL,
  `customer_product_address_id` int DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`exchange_request_no`),
  KEY `customer_product_fk_idx` (`customer_product_id`),
  KEY `store_product_fk_idx` (`store_product_id`),
  CONSTRAINT `customer_product_fk` FOREIGN KEY (`customer_product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `store_product_fk` FOREIGN KEY (`store_product_id`) REFERENCES `store_products` (`store_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product_images`
--

DROP TABLE IF EXISTS `product_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_images` (
  `product_image_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int DEFAULT NULL,
  `image_id` int DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`product_image_id`),
  UNIQUE KEY `image_no_UNIQUE` (`image_id`),
  KEY `fk_product_no_idx` (`product_id`),
  CONSTRAINT `product_id_image_fk` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `product_image_fk` FOREIGN KEY (`image_id`) REFERENCES `images` (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `store_products`
--

DROP TABLE IF EXISTS `store_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store_products` (
  `store_product_id` int NOT NULL AUTO_INCREMENT,
  `category_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `store_id` int DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `exchange_offer_price` double DEFAULT NULL,
  `direct_selling_price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`store_product_id`),
  KEY `category_id_idx` (`category_id`),
  KEY `product_id_idx` (`product_id`),
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_no`),
  CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `store_quotation`
--

DROP TABLE IF EXISTS `store_quotation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store_quotation` (
  `quotation_id` int NOT NULL AUTO_INCREMENT,
  `post_id` int DEFAULT NULL,
  `exchange_request_id` int DEFAULT NULL,
  `store_id` int DEFAULT NULL,
  `quotation_dt` date DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `offer_price` double DEFAULT NULL,
  `expiry_dt` datetime DEFAULT NULL,
  `post_owner_comments` varchar(200) DEFAULT NULL,
  `post_owner_replied_dt` date DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(55) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`quotation_id`),
  KEY `quotation__fk_post_id_idx` (`post_id`),
  KEY `quotation__fk_exchange_request_id_idx` (`exchange_request_id`),
  CONSTRAINT `quotation__fk_exchange_request_id` FOREIGN KEY (`exchange_request_id`) REFERENCES `product_exchange_request` (`exchange_request_no`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `quotation__fk_post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_favourite_posts`
--

DROP TABLE IF EXISTS `user_favourite_posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_favourite_posts` (
  `user_favourite_post_id` int NOT NULL AUTO_INCREMENT,
  `post_id` int DEFAULT NULL,
  `system_user_id` int DEFAULT NULL,
  `created_dt` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `last_modified_dt` datetime DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_favourite_post_id`),
  KEY `favourite__fk_post_id_idx` (`post_id`),
  CONSTRAINT `favourite__fk_post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON UPDATE CASCADE
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

-- Dump completed on 2021-06-12  0:52:24
