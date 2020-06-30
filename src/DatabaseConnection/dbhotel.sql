-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: dbhotel
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `guests`
--

DROP TABLE IF EXISTS `guests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `personal_number` bigint(20) DEFAULT NULL,
  `birthdate` date NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `registred_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gender` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guests`
--

LOCK TABLES `guests` WRITE;
/*!40000 ALTER TABLE `guests` DISABLE KEYS */;
INSERT INTO `guests` VALUES (1,'Margarita','Nasufi',1234,'2000-03-19','blla','2020-05-04 21:21:04',NULL),(2,'Erleta','Nasufi',1235,'2002-03-19','bllablla','2020-05-04 21:21:04',NULL),(3,'Makfire','Bajramaj',1221,'1988-03-19','some number','2020-05-04 21:21:04',NULL),(4,'Sinan','Dobreva',2345,'1980-05-19','smth','2020-05-04 21:21:04',NULL),(5,'Makfir','Sahiti',3254,'1977-03-01','bllabllabllaaa','2020-05-04 21:21:04',NULL),(6,'Sinane','Halili',11235,'1998-01-01','044435671','2020-06-11 17:42:21',NULL),(7,'helena','Troja',348756,'2020-06-17','3476345','2020-06-30 19:36:10','Female'),(8,'Destan','Balaj',473578,'1998-06-17','0456735758','2020-06-30 19:37:36','Male');
/*!40000 ALTER TABLE `guests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guest_id` int(11) NOT NULL,
  `price` double NOT NULL,
  `payment_method` varchar(30) NOT NULL,
  `is_payed` tinyint(1) NOT NULL DEFAULT '0',
  `pay_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `guest_id` (`guest_id`),
  CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`guest_id`) REFERENCES `guests` (`id`),
  CONSTRAINT `payments_chk_1` CHECK ((`price` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` VALUES (1,1,10,'Cash',1,'2020-06-30 10:29:01'),(2,2,10,'Cash',1,'2020-06-30 20:01:39'),(3,3,10,'cash',0,NULL),(4,4,10,'card',0,NULL),(5,5,10,'cash',0,NULL),(10,2,300,'cash',0,NULL),(11,2,300,'cash',0,NULL),(12,1,250,'Cash',1,'2020-06-30 10:29:01'),(13,1,1225,'Cash',1,'2020-06-30 10:29:01'),(14,1,200,'Cash',1,'2020-06-30 10:29:01'),(15,1,875,'Cash',1,'2020-06-30 10:29:01'),(16,1,700,'Cash',1,'2020-06-30 10:29:01'),(17,2,4500,'cash',0,NULL),(18,1,5400,'Cash',1,'2020-06-30 10:29:01'),(19,1,700,'Cash',1,'2020-06-30 10:29:01'),(20,1,700,'Cash',1,'2020-06-30 10:29:01'),(21,2,1750,'cash',0,NULL),(22,1,1050,'cash',0,NULL),(23,2,700,'cash',0,NULL),(24,2,500,'cash',0,NULL);
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perdoruesit`
--

DROP TABLE IF EXISTS `perdoruesit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perdoruesit` (
  `username` int(11) NOT NULL,
  `hashedPassword` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perdoruesit`
--

LOCK TABLES `perdoruesit` WRITE;
/*!40000 ALTER TABLE `perdoruesit` DISABLE KEYS */;
INSERT INTO `perdoruesit` VALUES (123456,'YjEwOWYzYmJiYzI0NGViODI0NDE5MTdlZDA2ZDYxOGI5MDA4ZGQwOWIzYmVmZDFiNWUwNzM5NGM3MDZhOGJiOTgwYjFkNzc4NWU1OTc2ZWMwNDliNDZkZjVmMTMyNmFmNWEyZWE2ZDEwM2ZkMDdjOTUzODVmZmFiMGNhY2JjODY=');
/*!40000 ALTER TABLE `perdoruesit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guest_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `reservation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `checkin_date` date DEFAULT NULL,
  `checkout_date` date DEFAULT NULL,
  `adults` int(11) DEFAULT '1',
  `children` int(11) DEFAULT '1',
  `payment_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `guest_id` (`guest_id`),
  KEY `room_id` (`room_id`),
  KEY `payment_id` (`payment_id`),
  CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`guest_id`) REFERENCES `guests` (`id`),
  CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_number`),
  CONSTRAINT `reservations_ibfk_3` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (3,1,1,'2020-01-13 23:00:00','2020-04-05','2020-04-09',1,0,1),(4,1,2,'2020-01-13 23:00:00','2020-04-05','2020-04-09',2,0,1),(5,2,4,'2020-01-31 23:00:00','2020-04-05','2020-04-08',2,1,2),(6,3,4,'2020-02-02 23:00:00','2020-04-10','2020-04-15',2,1,3),(7,4,1,'2020-02-12 23:00:00','2020-04-20','2020-04-23',1,0,4),(8,4,9,'2020-02-12 23:00:00','2020-04-20','2020-04-23',1,0,4),(9,5,2,'2020-02-29 23:00:00','2020-05-01','2020-05-11',1,1,5),(10,5,11,'2020-06-12 13:39:46','2020-06-12','2020-06-18',1,1,5),(11,1,1,'2020-06-12 13:59:46','2020-06-12','2020-06-16',1,1,14),(12,1,1,'2020-06-18 20:35:22','2020-06-18','2020-06-25',1,1,15),(13,1,2,'2020-06-18 20:35:22','2020-06-18','2020-06-25',1,1,15),(14,1,5,'2020-06-18 20:38:22','2020-06-18','2020-06-25',1,1,16),(15,1,6,'2020-06-18 20:38:22','2020-06-18','2020-06-25',1,1,16),(16,2,9,'2020-06-18 20:39:44','2020-06-18','2020-06-23',1,1,17),(17,2,10,'2020-06-18 20:39:44','2020-06-18','2020-06-23',1,1,17),(18,1,9,'2020-06-25 22:38:29','2020-06-26','2020-07-02',1,1,18),(19,1,10,'2020-06-25 22:38:29','2020-06-26','2020-07-02',1,1,18),(20,1,1,'2020-06-26 15:10:33','2020-06-26','2020-07-03',1,1,19),(21,1,5,'2020-06-26 15:10:33','2020-06-26','2020-07-03',1,1,19),(22,1,3,'2020-06-27 14:13:42','2020-06-27','2020-07-04',1,1,20),(23,2,5,'2020-06-27 14:19:51','2020-06-27','2020-07-04',1,1,21),(24,2,7,'2020-06-27 14:19:51','2020-06-27','2020-07-04',1,1,21),(25,1,1,'2020-06-30 10:45:18','2020-06-30','2020-07-07',1,1,22),(26,1,4,'2020-06-30 10:45:18','2020-06-30','2020-07-07',1,1,22),(27,2,3,'2020-06-30 11:47:16','2020-06-30','2020-07-07',1,1,23),(28,2,5,'2020-06-30 13:44:23','2020-06-03','2020-06-05',1,1,24),(29,2,7,'2020-06-30 13:44:23','2020-06-03','2020-06-05',1,1,24);
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms` (
  `room_number` int(11) NOT NULL AUTO_INCREMENT,
  `floor_number` int(11) NOT NULL,
  `capacity` int(11) NOT NULL,
  `bed_number` int(11) NOT NULL,
  `room_type` varchar(20) NOT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`room_number`),
  CONSTRAINT `rooms_chk_1` CHECK ((`floor_number` > 0)),
  CONSTRAINT `rooms_chk_2` CHECK ((`capacity` > 0)),
  CONSTRAINT `rooms_chk_3` CHECK ((`bed_number` > 0)),
  CONSTRAINT `rooms_chk_4` CHECK ((`price` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,1,1,1,'single',60),(2,1,2,1,'double',75),(3,2,3,3,'triple',100),(4,2,3,2,'triple',90),(5,3,1,1,'single',50),(6,3,1,1,'single',50),(7,4,4,4,'quad',200),(8,4,4,2,'quad',175),(9,5,2,1,'suite',300),(10,5,4,2,'suite',600),(11,5,4,2,'suite',650.5),(12,3,3,2,'Double',100.5);
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guest_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `payment_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `guest_id` (`guest_id`),
  KEY `service_id` (`service_id`),
  KEY `payment_id` (`payment_id`),
  CONSTRAINT `services_ibfk_1` FOREIGN KEY (`guest_id`) REFERENCES `guests` (`id`),
  CONSTRAINT `services_ibfk_2` FOREIGN KEY (`service_id`) REFERENCES `services_type` (`id`),
  CONSTRAINT `services_ibfk_3` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`id`),
  CONSTRAINT `services_chk_1` CHECK ((`quantity` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services_type`
--

DROP TABLE IF EXISTS `services_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `service_name` varchar(40) NOT NULL,
  `price` double DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `services_type_chk_1` CHECK ((`price` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services_type`
--

LOCK TABLES `services_type` WRITE;
/*!40000 ALTER TABLE `services_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `services_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `personal_number` int(11) DEFAULT NULL,
  `position` varchar(20) NOT NULL,
  `birthdate` date NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `salary` double DEFAULT NULL,
  `passwordd` varchar(200) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `staff_chk_1` CHECK ((`salary` > 169))
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'Gita','Nasufi',123456,'Admin','2000-03-19','+123456',1000,'qgjCBoi5UtgRnhq6xS48e8CpGLEPUYjAwi/Us2y1u75ibiWnGmsC0HHLdJ81GTe82wcchc73Gz14WKuys7F+tA==','Female'),(2,'Margarita','Dibrani',123001,'Manager','2020-08-18','+38344611833',1550.2,'magimaemira','Female'),(3,'Desantino','Ismajli',12367,'Recepsionist','1998-01-03','+38345678901',500.99,'nULTVTEXAp5dYJc29S/D7jyE+5tKWYF1gw2Z3oM9SY4KKbIXtOYEUHbd+abnXStpm7AD8V9AsavZLlYaKtVBDw==','Mashkull'),(4,'Helena','Troja',4756347,'Manager','1997-06-12','044585856',1550.2,'pABpSEoi3VAG3Zl7eH9jEHnAOR9E0+cBb7efLCD1sF/4tfnHBbc92sO0yK78fbUU7xT3g5qXzndp64oI5YK99Q==','Female');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-30 23:01:36
