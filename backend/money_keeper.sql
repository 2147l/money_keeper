-- MySQL dump 10.13  Distrib 8.2.0, for Win64 (x86_64)
--
-- Host: localhost    Database: money_keeper
-- ------------------------------------------------------
-- Server version	8.2.0

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
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int NOT NULL COMMENT '用户id',
  `category_id` int NOT NULL COMMENT '分类id',
  `type` tinyint(1) NOT NULL COMMENT '收支类型，false为支出，true为收入',
  `amount` decimal(10,2) NOT NULL COMMENT '金额',
  `date` varchar(20) NOT NULL COMMENT '创建日期',
  `note` text COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (1,1,1,0,-36.00,'2023-11-17',''),(2,1,1,0,-36.20,'2023-11-17',''),(5,1,1,0,-57.00,'2023-11-18',''),(6,1,1,0,-57.00,'2023-11-24',''),(7,2,6,0,-6.00,'2023-11-24',''),(8,1,8,0,-3.65,'2023-12-24',''),(9,2,11,0,-50.20,'2023-11-24','测试备注测试备注测试备注'),(10,2,3,1,8.60,'2023-12-24',''),(12,1,3,0,-56.29,'2023-11-24',''),(13,1,1,0,-5.51,'2023-12-24','test'),(14,1,1,0,-66.60,'2023-12-06','测试'),(33,1,16,1,5.00,'2023-12-06',''),(34,1,5,0,-5.20,'2023-12-06',''),(54,1,13,1,100.00,'2023-12-06',''),(55,1,7,0,-100.10,'2023-12-07','');
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL COMMENT '分类名称',
  `icon` text COMMENT '分类图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'餐饮','src/assets/icon/餐饮.png'),(2,'购物','src/assets/icon/购物.png'),(3,'日用','src/assets/icon/日用.png'),(4,'交通','src/assets/icon/交通.png'),(5,'社交','src/assets/icon/社交.png'),(6,'娱乐','src/assets/icon/娱乐.png'),(7,'学习','src/assets/icon/学习.png'),(8,'运动','src/assets/icon/运动.png'),(9,'医疗','src/assets/icon/医疗.png'),(10,'旅行','src/assets/icon/旅行.png'),(11,'其他','src/assets/icon/其他.png'),(12,'工资','src/assets/icon/工资.png'),(13,'兼职','src/assets/icon/兼职.png'),(14,'理财','src/assets/icon/理财.png'),(15,'礼金','src/assets/icon/礼金.png'),(16,'其他','src/assets/icon/其他.png');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `phone` varchar(11) NOT NULL COMMENT '手机号码',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `username` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `avatar` text COMMENT '头像',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `wechat` varchar(20) DEFAULT NULL COMMENT '微信号',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'123','123','测试者','src/assets/avatar/1.png','女','wxid_ov56s29z75sfg','ceshizhe@email.com'),(2,'456','456',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-07 22:54:45
