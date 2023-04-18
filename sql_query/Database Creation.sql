DROP DATABASE IF EXISTS LIBRARY_MANAGEMENT;
CREATE DATABASE LIBRARY_MANAGEMENT;
USE LIBRARY_MANAGEMENT;

-- MySQL dump 10.13  Distrib 8.0.32, for Linux (x86_64)
--
-- Host: localhost    Database: LIBRARY_MANAGEMENT
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `AUTHOR`
--

DROP TABLE IF EXISTS `AUTHOR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `AUTHOR` (
                          `MA_TG` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                          `TEN_TG` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                          `EMAIL` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                          `GIOI_THIEU` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                          `GIOI_TINH` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                          `IS_DELETED` tinyint(1) NOT NULL,
                          PRIMARY KEY (`MA_TG`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AUTHOR`
--

LOCK TABLES `AUTHOR` WRITE;
/*!40000 ALTER TABLE `AUTHOR` DISABLE KEYS */;
INSERT INTO `AUTHOR` VALUES ('TG001','Võ Minh Trí','ex@gmail.com','','NAM',0),('TG002','Tiến hải','aibiet@gmail.com','','NAM',0);
/*!40000 ALTER TABLE `AUTHOR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BOOK`
--

DROP TABLE IF EXISTS `BOOK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BOOK` (
                        `MA_SERIES` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                        `MA_SACH` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                        `TEN_SACH` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                        `MO_TA` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                        `VI_TRI` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                        `GIA` mediumtext NOT NULL,
                        `NAM_XUAT_BAN` year NOT NULL,
                        `NGON_NGU` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                        `TONG_SO_TRANG` int NOT NULL,
                        `TRANG_THAI` enum('AVAILABLE','BORROWED','MISSING','IN_USE','SOLD') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                        `MA_NHA_NHAP` varchar(20) DEFAULT NULL,
                        `IS_DELETED` tinyint(1) NOT NULL,
                        PRIMARY KEY (`MA_SERIES`),
                        KEY `BOOK_IMPORTED_FROM_ID_fk` (`MA_NHA_NHAP`),
                        CONSTRAINT `BOOK_IMPORTED_FROM_ID_fk` FOREIGN KEY (`MA_NHA_NHAP`) REFERENCES `IMPORTED_FROM` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOOK`
--

LOCK TABLES `BOOK` WRITE;
/*!40000 ALTER TABLE `BOOK` DISABLE KEYS */;
INSERT INTO `BOOK` VALUES ('1_1','1','Hello world','','Nep','1000',2019,'English',100,'AVAILABLE',NULL,0);
/*!40000 ALTER TABLE `BOOK` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `book_update_trigger` AFTER UPDATE ON `BOOK` FOR EACH ROW BEGIN
    IF NEW.IS_DELETED <> OLD.IS_DELETED THEN
        UPDATE BOOK_GENRE
        SET IS_DELETED = NEW.IS_DELETED
        WHERE NEW.MA_SERIES = BOOK_GENRE.MA_SERIES;

        UPDATE BOOK_PUBLISHER
        SET IS_DELETED = NEW.IS_DELETED
        WHERE NEW.MA_SERIES = BOOK_PUBLISHER.MA_SERIES;

        UPDATE BOOK_AUTHOR
        SET IS_DELETED = NEW.IS_DELETED
        WHERE NEW.MA_SERIES = BOOK_AUTHOR.MA_SERIES;

        UPDATE BOOK_BOOK_FAULT
        SET IS_DELETED = NEW.IS_DELETED
        WHERE NEW.MA_SERIES = BOOK_BOOK_FAULT.MA_SERIES;

    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `BOOK_AUTHOR`
--

DROP TABLE IF EXISTS `BOOK_AUTHOR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BOOK_AUTHOR` (
                               `MA_SERIES` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                               `MA_TG` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                               `IS_DELETED` tinyint(1) NOT NULL,
                               PRIMARY KEY (`MA_SERIES`,`MA_TG`),
                               KEY `FK_BA_AUTHOR_MATG` (`MA_TG`),
                               CONSTRAINT `FK_BA_AUTHOR_MATG` FOREIGN KEY (`MA_TG`) REFERENCES `AUTHOR` (`MA_TG`),
                               CONSTRAINT `FK_BAU_BOOK_MASACH` FOREIGN KEY (`MA_SERIES`) REFERENCES `BOOK` (`MA_SERIES`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOOK_AUTHOR`
--

LOCK TABLES `BOOK_AUTHOR` WRITE;
/*!40000 ALTER TABLE `BOOK_AUTHOR` DISABLE KEYS */;
INSERT INTO `BOOK_AUTHOR` VALUES ('1_1','TG001',0);
/*!40000 ALTER TABLE `BOOK_AUTHOR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BOOK_BOOK_FAULT`
--

DROP TABLE IF EXISTS `BOOK_BOOK_FAULT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BOOK_BOOK_FAULT` (
                                   `MA_SERIES` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                   `MA_LOI` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                   `IS_DELETED` tinyint(1) DEFAULT NULL,
                                   PRIMARY KEY (`MA_SERIES`,`MA_LOI`),
                                   KEY `FK_BF_BBF_MALOI` (`MA_LOI`),
                                   CONSTRAINT `FK_BF_BBF_MALOI` FOREIGN KEY (`MA_LOI`) REFERENCES `BOOK_FAULT` (`MA_LOI`),
                                   CONSTRAINT `FK_BOOK_BBF_MASERIES` FOREIGN KEY (`MA_SERIES`) REFERENCES `BOOK` (`MA_SERIES`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOOK_BOOK_FAULT`
--

LOCK TABLES `BOOK_BOOK_FAULT` WRITE;
/*!40000 ALTER TABLE `BOOK_BOOK_FAULT` DISABLE KEYS */;
/*!40000 ALTER TABLE `BOOK_BOOK_FAULT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BOOK_FAULT`
--

DROP TABLE IF EXISTS `BOOK_FAULT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BOOK_FAULT` (
                              `MA_LOI` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                              `TEN_LOI` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                              `HE_SO` double DEFAULT NULL,
                              `IS_DELETED` int DEFAULT NULL,
                              PRIMARY KEY (`MA_LOI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOOK_FAULT`
--

LOCK TABLES `BOOK_FAULT` WRITE;
/*!40000 ALTER TABLE `BOOK_FAULT` DISABLE KEYS */;
/*!40000 ALTER TABLE `BOOK_FAULT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BOOK_GENRE`
--

DROP TABLE IF EXISTS `BOOK_GENRE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BOOK_GENRE` (
                              `MA_SERIES` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                              `MA_TL` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                              `IS_DELETED` tinyint(1) DEFAULT NULL,
                              PRIMARY KEY (`MA_SERIES`,`MA_TL`),
                              KEY `FK_BG_GENRE_MATL` (`MA_TL`),
                              CONSTRAINT `FK_BG_BOOK_MASACH` FOREIGN KEY (`MA_SERIES`) REFERENCES `BOOK` (`MA_SERIES`),
                              CONSTRAINT `FK_BG_GENRE_MATL` FOREIGN KEY (`MA_TL`) REFERENCES `GENRE` (`MA_TL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOOK_GENRE`
--

LOCK TABLES `BOOK_GENRE` WRITE;
/*!40000 ALTER TABLE `BOOK_GENRE` DISABLE KEYS */;
INSERT INTO `BOOK_GENRE` VALUES ('1_1','TL001',0);
/*!40000 ALTER TABLE `BOOK_GENRE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BOOK_PUBLISHER`
--

DROP TABLE IF EXISTS `BOOK_PUBLISHER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BOOK_PUBLISHER` (
                                  `MA_SERIES` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                  `MA_NXB` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                  `IS_DELETED` tinyint(1) DEFAULT NULL,
                                  PRIMARY KEY (`MA_SERIES`,`MA_NXB`),
                                  KEY `FK_BP_PUBLISHER_MA_NXB` (`MA_NXB`),
                                  CONSTRAINT `FK_BP_BOOK_MASACH` FOREIGN KEY (`MA_SERIES`) REFERENCES `BOOK` (`MA_SERIES`),
                                  CONSTRAINT `FK_BP_PUBLISHER_MA_NXB` FOREIGN KEY (`MA_NXB`) REFERENCES `PUBLISHER` (`MA_NXB`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BOOK_PUBLISHER`
--

LOCK TABLES `BOOK_PUBLISHER` WRITE;
/*!40000 ALTER TABLE `BOOK_PUBLISHER` DISABLE KEYS */;
INSERT INTO `BOOK_PUBLISHER` VALUES ('1_1','NXB001',0);
/*!40000 ALTER TABLE `BOOK_PUBLISHER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BORROW_BOOK_TICKET_FAULT`
--

DROP TABLE IF EXISTS `BORROW_BOOK_TICKET_FAULT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BORROW_BOOK_TICKET_FAULT` (
                                            `MA_LOI` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                            `MA_SERIES` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                            `MA_PHIEU` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                            `MA_CHITIET` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                            `SO_LUONG` int NOT NULL,
                                            `IS_DELETED` tinyint(1) DEFAULT NULL,
                                            PRIMARY KEY (`MA_SERIES`,`MA_PHIEU`,`MA_LOI`),
                                            KEY `FK_BTD_BBTF_MA_CHITIET` (`MA_CHITIET`),
                                            KEY `FK_BF_BBTF_MA_CHITIET` (`MA_LOI`),
                                            KEY `FK__C` (`MA_PHIEU`,`MA_SERIES`),
                                            CONSTRAINT `FK__C` FOREIGN KEY (`MA_PHIEU`, `MA_SERIES`) REFERENCES `BORROW_TICKET_DETAILS` (`MA_PHIEU`, `MA_SERIES`),
                                            CONSTRAINT `FK_BF_BBTF_MA_CHITIET` FOREIGN KEY (`MA_LOI`) REFERENCES `BOOK_FAULT` (`MA_LOI`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BORROW_BOOK_TICKET_FAULT`
--

LOCK TABLES `BORROW_BOOK_TICKET_FAULT` WRITE;
/*!40000 ALTER TABLE `BORROW_BOOK_TICKET_FAULT` DISABLE KEYS */;
/*!40000 ALTER TABLE `BORROW_BOOK_TICKET_FAULT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BORROW_TICKET`
--

DROP TABLE IF EXISTS `BORROW_TICKET`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BORROW_TICKET` (
                                 `MA_PHIEU` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                 `MA_NV` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                 `MA_THE` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                 `IS_DELETED` tinyint(1) DEFAULT NULL,
                                 `DATE_BORROW` date DEFAULT NULL,
                                 `DATE_TO_GIVE_BACK` date DEFAULT NULL,
                                 `DATE_GIVE_BACK` date DEFAULT NULL,
                                 PRIMARY KEY (`MA_PHIEU`),
                                 KEY `FK_BT_MEM_MA_THE` (`MA_THE`),
                                 CONSTRAINT `FK_BT_MEM_MA_THE` FOREIGN KEY (`MA_THE`) REFERENCES `MEMBERSHIP` (`MA_THE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BORROW_TICKET`
--

LOCK TABLES `BORROW_TICKET` WRITE;
/*!40000 ALTER TABLE `BORROW_TICKET` DISABLE KEYS */;
/*!40000 ALTER TABLE `BORROW_TICKET` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `BORROW_TICKET_TRIGGER` AFTER UPDATE ON `BORROW_TICKET` FOR EACH ROW BEGIN
    IF NEW.IS_DELETED <> OLD.IS_DELETED THEN
        UPDATE BORROW_TICKET_DETAILS
        SET IS_DELETED = NEW.IS_DELETED
        WHERE BORROW_TICKET_DETAILS.MA_PHIEU = NEW.MA_PHIEU;

    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `BORROW_TICKET_DETAILS`
--

DROP TABLE IF EXISTS `BORROW_TICKET_DETAILS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BORROW_TICKET_DETAILS` (
                                         `MA_PHIEU` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                         `MA_SERIES` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                         `NGAY_HENTRA` date DEFAULT NULL,
                                         `NGAY_CHO_MUON` date DEFAULT NULL,
                                         `TIEN_TAM_TINH` mediumtext,
                                         `TIEN_TONG` mediumtext,
                                         `MA_NVXN` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                                         `IS_DELETED` tinyint(1) DEFAULT NULL,
                                         PRIMARY KEY (`MA_SERIES`,`MA_PHIEU`),
                                         KEY `FK_BTD_BT_MA_PHIEU` (`MA_PHIEU`),
                                         CONSTRAINT `FK_BTD_BOOK_MASERIES` FOREIGN KEY (`MA_SERIES`) REFERENCES `BOOK` (`MA_SERIES`),
                                         CONSTRAINT `FK_BTD_BT_MA_PHIEU` FOREIGN KEY (`MA_PHIEU`) REFERENCES `BORROW_TICKET` (`MA_PHIEU`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BORROW_TICKET_DETAILS`
--

LOCK TABLES `BORROW_TICKET_DETAILS` WRITE;
/*!40000 ALTER TABLE `BORROW_TICKET_DETAILS` DISABLE KEYS */;
/*!40000 ALTER TABLE `BORROW_TICKET_DETAILS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CUSTOMER`
--

DROP TABLE IF EXISTS `CUSTOMER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CUSTOMER` (
                            `MA_KH` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                            `TEN` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                            `NGAY_SINH` date DEFAULT NULL,
                            `DIA_CHI` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                            `CCCD` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                            `EMAIL` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                            `PHONE` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                            `GIOI_TINH` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                            `IS_DELETED` tinyint(1) NOT NULL,
                            PRIMARY KEY (`MA_KH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CUSTOMER`
--

LOCK TABLES `CUSTOMER` WRITE;
/*!40000 ALTER TABLE `CUSTOMER` DISABLE KEYS */;
/*!40000 ALTER TABLE `CUSTOMER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EMPLOYEE`
--

DROP TABLE IF EXISTS `EMPLOYEE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `EMPLOYEE` (
                            `MA_NV` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci not NULL,
                            `TEN` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                            `CA` int DEFAULT NULL,
                            `CHUC_VU` int   DEFAULT 0,
                            `SO_NGAY_LAM_VIEC` int DEFAULT NULL,
                            `NOI_LAM_VIEC` int DEFAULT 0,
                            `LUONG` int DEFAULT 0,
                            `HE_SO` double DEFAULT NULL,
                            `PASSWORD` varchar(100) DEFAULT NULL,
                            `NGAY_NHAN_CHUC` date DEFAULT NULL,
                            `NGAY_SINH` date DEFAULT NULL,
                            `DIA_CHI` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                            `CCCD` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci default NULL,
                            `EMAIL` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                            `PHONE` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                            `GIOI_TINH` int DEFAULT 0,
                            `IS_DELETED` tinyint(1) DEFAULT 1,
                            PRIMARY KEY (`MA_NV`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EMPLOYEE`
--

LOCK TABLES `EMPLOYEE` WRITE;
/*!40000 ALTER TABLE `EMPLOYEE` DISABLE KEYS */;
/*!40000 ALTER TABLE `EMPLOYEE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EMPLOYEE_PERMISSION`
--

DROP TABLE IF EXISTS `EMPLOYEE_PERMISSION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `EMPLOYEE_PERMISSION` (
                                       `CHUC_VU` int DEFAULT 0,
                                       `DESCRIPTION` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                                       `IS_DELETED` tinyint(1) DEFAULT NULL,
                                       PRIMARY KEY (`CHUC_VU`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EMPLOYEE_PERMISSION`
--

LOCK TABLES `EMPLOYEE_PERMISSION` WRITE;
/*!40000 ALTER TABLE `EMPLOYEE_PERMISSION` DISABLE KEYS */;
/*!40000 ALTER TABLE `EMPLOYEE_PERMISSION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GENRE`
--

DROP TABLE IF EXISTS `GENRE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `GENRE` (
                         `MA_TL` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                         `TEN_TL` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                         `MO_TA` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                         `IS_DELETED` tinyint(1) DEFAULT NULL,
                         PRIMARY KEY (`MA_TL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GENRE`
--

LOCK TABLES `GENRE` WRITE;
/*!40000 ALTER TABLE `GENRE` DISABLE KEYS */;
INSERT INTO `GENRE` VALUES ('TL001','Phiêu lưu','',NULL);
/*!40000 ALTER TABLE `GENRE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IMPORTED_FROM`
--

DROP TABLE IF EXISTS `IMPORTED_FROM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `IMPORTED_FROM` (
                                 `ID` varchar(20) NOT NULL,
                                 `NAME` varchar(100) DEFAULT NULL,
                                 `DESCRIPTION` varchar(1000) DEFAULT NULL,
                                 `IS_DELETED` tinyint(1) DEFAULT NULL,
                                 PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IMPORTED_FROM`
--

LOCK TABLES `IMPORTED_FROM` WRITE;
/*!40000 ALTER TABLE `IMPORTED_FROM` DISABLE KEYS */;
/*!40000 ALTER TABLE `IMPORTED_FROM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MEMBERSHIP`
--

DROP TABLE IF EXISTS `MEMBERSHIP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `MEMBERSHIP` (
                              `MA_THE` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                              `MA_KH` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                              `DANG_THE` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                              `NGAY_DK` date NOT NULL,
                              `NGAY_HH` date NOT NULL,
                              `IS_DELETED` tinyint(1) DEFAULT NULL,
                              PRIMARY KEY (`MA_THE`),
                              KEY `FK_MEM_MEMTYPE_DANG_THE` (`DANG_THE`),
                              KEY `FK_MEM_CUS_MAKH` (`MA_KH`),
                              CONSTRAINT `FK_MEM_CUS_MAKH` FOREIGN KEY (`MA_KH`) REFERENCES `CUSTOMER` (`MA_KH`),
                              CONSTRAINT `FK_MEM_MEMTYPE_DANG_THE` FOREIGN KEY (`DANG_THE`) REFERENCES `MEMBERSHIP_TYPE` (`DANG_THE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MEMBERSHIP`
--

LOCK TABLES `MEMBERSHIP` WRITE;
/*!40000 ALTER TABLE `MEMBERSHIP` DISABLE KEYS */;
/*!40000 ALTER TABLE `MEMBERSHIP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MEMBERSHIP_TYPE`
--

DROP TABLE IF EXISTS `MEMBERSHIP_TYPE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `MEMBERSHIP_TYPE` (
                                   `DANG_THE` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                   `GIA_GIAM` int DEFAULT NULL,
                                   `IS_DELETED` tinyint(1) DEFAULT NULL,
                                   PRIMARY KEY (`DANG_THE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MEMBERSHIP_TYPE`
--

LOCK TABLES `MEMBERSHIP_TYPE` WRITE;
/*!40000 ALTER TABLE `MEMBERSHIP_TYPE` DISABLE KEYS */;
/*!40000 ALTER TABLE `MEMBERSHIP_TYPE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PUBLISHER`
--

DROP TABLE IF EXISTS `PUBLISHER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PUBLISHER` (
                             `MA_NXB` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                             `TEN_NXB` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                             `EMAIL` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                             `DIA_CHI` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                             `PHONE` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                             `DESCRIPTION` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                             `IS_DELETED` tinyint(1) DEFAULT NULL,
                             PRIMARY KEY (`MA_NXB`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PUBLISHER`
--

LOCK TABLES `PUBLISHER` WRITE;
/*!40000 ALTER TABLE `PUBLISHER` DISABLE KEYS */;
INSERT INTO `PUBLISHER` VALUES ('NXB001','Neptune','Woa@gmail.com','Temp','123456789','',NULL);
/*!40000 ALTER TABLE `PUBLISHER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SELL_TICKET`
--

DROP TABLE IF EXISTS `SELL_TICKET`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SELL_TICKET` (
                               `MA_PHIEU` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                               `MA_NV`  varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                               `MA_KH` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                               `IS_DELETED` tinyint(1) DEFAULT NULL,
                               PRIMARY KEY (`MA_PHIEU`),
                               KEY `FK_ST_CUS_MA_KH` (`MA_KH`),
                               KEY `SELL_TICKET_EMPLOYEE_MA_NV_fk` (`MA_NV`),
                               CONSTRAINT `FK_ST_CUS_MA_KH` FOREIGN KEY (`MA_KH`) REFERENCES `CUSTOMER` (`MA_KH`),
                               CONSTRAINT `SELL_TICKET_EMPLOYEE_MA_NV_fk` FOREIGN KEY (`MA_NV`) REFERENCES `EMPLOYEE` (`MA_NV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SELL_TICKET`
--

LOCK TABLES `SELL_TICKET` WRITE;
/*!40000 ALTER TABLE `SELL_TICKET` DISABLE KEYS */;
/*!40000 ALTER TABLE `SELL_TICKET` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `SELL_TICKET_TRIGGER` AFTER UPDATE ON `SELL_TICKET` FOR EACH ROW BEGIN
    IF NEW.IS_DELETED <> OLD.IS_DELETED THEN
        UPDATE SELL_TICKET_DETAILS
        SET IS_DELETED = NEW.IS_DELETED
        WHERE SELL_TICKET_DETAILS.MA_PHIEU = NEW.MA_PHIEU;

    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `SELL_TICKET_DETAILS`
--

DROP TABLE IF EXISTS `SELL_TICKET_DETAILS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SELL_TICKET_DETAILS` (
                                       `MA_PHIEU` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                       `HE_SO` double NOT NULL,
                                       `MA_SERIES` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                       `IS_DELETED` tinyint(1) DEFAULT NULL,
                                       PRIMARY KEY (`MA_SERIES`,`MA_PHIEU`),
                                       KEY `FK_STD_ST_MAPH` (`MA_PHIEU`),
                                       CONSTRAINT `FK_STD_BOOK_MASERIES` FOREIGN KEY (`MA_SERIES`) REFERENCES `BOOK` (`MA_SERIES`),
                                       CONSTRAINT `FK_STD_ST_MAPH` FOREIGN KEY (`MA_PHIEU`) REFERENCES `SELL_TICKET` (`MA_PHIEU`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SELL_TICKET_DETAILS`
--

LOCK TABLES `SELL_TICKET_DETAILS` WRITE;
/*!40000 ALTER TABLE `SELL_TICKET_DETAILS` DISABLE KEYS */;
/*!40000 ALTER TABLE `SELL_TICKET_DETAILS` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-11 15:01:23
