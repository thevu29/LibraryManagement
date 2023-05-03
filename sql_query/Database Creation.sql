DROP DATABASE IF EXISTS LIBRARY_MANAGEMENT;
CREATE DATABASE LIBRARY_MANAGEMENT;
USE LIBRARY_MANAGEMENT;

-- MySQL dump 10.13  Distrib 8.0.32, for Linux (x86_64)
--
-- Host: localhost    Database: LIBRARY_MANAGEMENT
-- ------------------------------------------------------
-- Server version       8.0.32

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
INSERT INTO `AUTHOR` VALUES ('TG001','Võ Minh Trí','ex@gmail.co','','NAM',0),('TG002','Tiến hải','aibiet@gmail.com','','NAM',0),('TG003','cas c','cas@g.c','','NAM',0),('TG10','vasv','asv@gmail.com','','NAM',0),('TG4','avsa','asv@gmail.com','av','NU',0),('TG5','vasv','cas@g.c','','NAM',0),('TG6','Tiến hải','aibiet@gmail.com','','NAM',0),('TG7','avsa','asv@gmail.com','','NAM',0),('TG8','cas c','cas@g.c','','NAM',0),('TG9','Tiến hải','ex@gmail.co','','NAM',0);
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
INSERT INTO `BOOK` VALUES ('1_1','1','Hello world','','Nep','1000',2019,'English',299,'SOLD','IM1',0),('2_1','2','D','','NXB001, Neptune','20',2019,'English',100,'AVAILABLE','IM1',1),('3_2','3','a','','c','23',2003,'English',3,'SOLD','IM1',0),('3_3','3','a','','c','23',2003,'English',3,'AVAILABLE','IM1',0),('3_4','3','a','','c','23',2003,'English',3,'AVAILABLE','IM1',0),('3_5','3','a','','c','23',2003,'English',3,'AVAILABLE','IM1',1),('3_6','3','a','','c','23',2003,'English',3,'AVAILABLE','IM1',0),('3_7','3','a','','c','23',2003,'English',3,'AVAILABLE','IM1',0),('3_8','3','a','','c','23',2003,'English',3,'AVAILABLE','IM1',0),('3_9','3','a','','c','23',2003,'English',3,'AVAILABLE','IM1',0);
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
INSERT INTO `BOOK_AUTHOR` VALUES ('1_1','TG001',0),('2_1','TG4',1),('3_2','TG002',0),('3_3','TG002',0),('3_4','TG002',0),('3_5','TG002',1),('3_6','TG002',0),('3_7','TG002',0),('3_8','TG002',0),('3_9','TG002',0);
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
INSERT INTO `BOOK_GENRE` VALUES ('1_1','TL3',0),('2_1','TL2',1),('3_2','TL2',0),('3_3','TL2',0),('3_4','TL2',0),('3_5','TL2',1),('3_6','TL2',0),('3_7','TL2',0),('3_8','TL2',0),('3_9','TL2',0);
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
INSERT INTO `BOOK_PUBLISHER` VALUES ('1_1','NXB001',0),('3_2','NXB001',0),('3_3','NXB001',0),('3_4','NXB001',0),('3_5','NXB001',1),('3_6','NXB001',0),('3_7','NXB001',0),('3_8','NXB001',0),('3_9','NXB001',0);
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
                                            `SO_LUONG` int NOT NULL,
                                            PRIMARY KEY (`MA_SERIES`,`MA_PHIEU`,`MA_LOI`),
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
                                 `MA_NV_MUON` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                 `MA_NV_TRA` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                                 `MA_THE` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                 `IS_DELETED` tinyint(1) DEFAULT NULL,
                                 `DATE_BORROW` date DEFAULT NULL,
                                 `DATE_TO_GIVE_BACK` date DEFAULT NULL,
                                 `DATE_GIVE_BACK` date DEFAULT NULL,
                                 PRIMARY KEY (`MA_PHIEU`),
                                 UNIQUE KEY `MA_PHIEU` (`MA_PHIEU`),
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
                                         `TIEN_TAM_TINH` mediumtext,
                                         `TIEN_TONG` mediumtext,
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
INSERT INTO `CUSTOMER` VALUES ('CUS001','Thế Vũ','2003-08-29','HCM','01234','vu@gmail.com','0123456789','Nam',0),('CUS002','Minh Trí','2003-08-29','HCM','01234','tri@gmail.com','0123456789','Nam',0),('CUS003','Ngọc Huy','2003-08-29','HCM','01234','huy@gmail.com','0123456789','Nam',0),('CUS004','Bảo Quỳnh','2003-08-29','HCM','01234','quynh@gmail.com','0123456789','Nam',0),('CUS005','Tiến Hải','2003-08-29','HCM','01234','hai@gmail.com','0123456789','Nam',0),('CUS006','Tiến Đạt','2003-08-29','HCM','01234','dat@gmail.com','0123456789','Nam',0),('CUS007','Minh Nam','2003-08-29','HCM','01234','nam@gmail.com','0123456789','Nam',0),('CUS008','An Tuấn','2003-08-29','HCM','01234','tuan@gmail.com','0123456789','Nam',0),('CUS009','Tuấn Khải','2003-08-29','HCM','01234','khai@gmail.com','0123456789','Nam',0),('CUS010','Huy Hoàng','2003-08-29','HCM','01234','hoang@gmail.com','0123456789','Nam',0),('CUS011','Hải Nam','2003-08-29','HCM','01234','nam@gmail.com','0123456789','Nam',0),('CUS012','Minh Khôi','2003-08-29','HCM','01234','khoi@gmail.com','0123456789','Nam',0),('CUS013','Như Quỳnh','2003-08-29','HCM','01234','quynh@gmail.com','0123456789','Nữ',0),('CUS014','Yến Nhi','2003-08-29','HCM','01234','nhi@gmail.com','0123456789','Nữ',0),('CUS015','Quốc Tiến','2003-08-29','HCM','01234','tien@gmail.com','0123456789','Nam',0),('CUS016','Đức Thắng','2003-08-29','HCM','01234','thang@gmail.com','0123456789','Nam',0),('CUS017','Kim Phú','2003-08-29','HCM','01234','phu@gmail.com','0123456789','Nam',0),('CUS018','Quốc Cường','2003-08-29','HCM','01234','cuong@gmail.com','0123456789','Nam',0),('CUS019','Quốc Đại','2003-08-29','HCM','01234','dai@gmail.com','0123456789','Nam',0),('CUS020','Bình Hải','2003-08-29','HCM','01234','hai2@gmail.com','0123456789','Nam',0),('CUS021','Mỹ Ngọc','2003-08-29','HCM','01234','ngoc@gmail.com','0123456789','Nữ',0),('CUS022','Việt Hùng','2003-08-29','HCM','01234','hung@gmail.com','0123456789','Nam',0),('CUS023','Minh Quang','2003-08-29','HCM','01234','quang@gmail.com','0123456789','Nam',0),('CUS024','Hiếu Ngân','2003-08-29','HCM','01234','ngan@gmail.com','0123456789','Nữ',0),('CUS025','Diễm Quỳnh','2003-08-29','HCM','01234','quynh2@gmail.com','0123456789','Nữ',0),('CUS026','Minh Nghị','2003-08-29','HCM','01234','nghi@gmail.com','0123456789','Nam',0),('CUS027','Duy Thành','2003-08-29','HCM','01234','thanh@gmail.com','0123456789','Nam',0),('CUS028','Minh Nguyên','2003-08-29','HCM','01234','nguyen@gmail.com','0123456789','Nam',0),('CUS029','Thiên Quốc','2003-08-29','HCM','01234','quoc@gmail.com','0123456789','Nam',0),('CUS030','Trung Kha','2003-08-29','HCM','01234','kha@gmail.com','0123456789','Nam',0),('KH001','Nguyen Van A','2023-04-15',' ',' ',' ','01111111','NAM',0);
/*!40000 ALTER TABLE `CUSTOMER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EMPLOYEE`
--

DROP TABLE IF EXISTS `EMPLOYEE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `EMPLOYEE` (
                            `MA_NV` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                            `TEN` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                            `CA` int DEFAULT NULL,
                            `CHUC_VU` int DEFAULT NULL,
                            `SO_NGAY_LAM_VIEC` int DEFAULT NULL,
                            `NOI_LAM_VIEC` int DEFAULT NULL,
                            `HE_SO` double DEFAULT NULL,
                            `PASSWORD` varchar(100) DEFAULT NULL,
                            `NGAY_NHAN_CHUC` date DEFAULT NULL,
                            `NGAY_SINH` date DEFAULT NULL,
                            `DIA_CHI` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                            `CCCD` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                            `EMAIL` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                            `PHONE` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                            `GIOI_TINH` int DEFAULT NULL,
                            `IS_DELETED` tinyint(1) NOT NULL,
                            `LUONG` int DEFAULT NULL,
                            PRIMARY KEY (`MA_NV`),
                            KEY `FK_EMPL_EP_CHUCVU` (`CHUC_VU`)
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
INSERT INTO `GENRE` VALUES ('TL001','casv','asvas',1),('TL2','csa','',0),('TL3','CS','',0);
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
                                 `PHONE` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                                 `ADDRESS` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                                 `EMAIL` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
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
INSERT INTO `IMPORTED_FROM` VALUES ('IM1','Neptune','0393406364','cas','v@g.c','',0);
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
                              `MA_KH` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                              `DANG_THE` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                              `NGAY_DK` date NOT NULL,
                              `NGAY_HH` date NOT NULL,
                              `IS_DELETED` tinyint(1) DEFAULT NULL,
                              PRIMARY KEY (`MA_KH`,`DANG_THE`),
                              KEY `MA_THE` (`MA_THE`),
                              KEY `FK_MEM_MEMTYPE_DANG_THE` (`DANG_THE`),
                              CONSTRAINT `FK_MEM_CUS_MAKH` FOREIGN KEY (`MA_KH`) REFERENCES `CUSTOMER` (`MA_KH`),
                              CONSTRAINT `FK_MEM_MEMTYPE_DANG_THE` FOREIGN KEY (`DANG_THE`) REFERENCES `MEMBERSHIP_TYPE` (`DANG_THE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MEMBERSHIP`
--

LOCK TABLES `MEMBERSHIP` WRITE;
/*!40000 ALTER TABLE `MEMBERSHIP` DISABLE KEYS */;
INSERT INTO `MEMBERSHIP` VALUES ('MEM001','CUS001','Bạc','2023-09-28','2024-09-28',0),('MEM009','CUS002','Vàng','2023-01-28','2024-07-28',0),('MEM010','CUS003','Bạc','2023-01-28','2024-07-28',0),('MEM002','CUS004','Vàng','2023-07-28','2024-07-28',0),('MEM003','CUS005','Bạch Kim','2023-08-28','2024-07-28',0),('MEM016','CUS006','Vàng','2023-11-28','2024-07-28',0),('MEM004','CUS007','Bạch Kim','2023-04-28','2024-07-28',0),('MEM011','CUS008','Vàng','2023-03-28','2024-07-28',0),('MEM015','CUS011','Vàng','2023-10-28','2024-07-28',0),('MEM005','CUS012','Bạch Kim','2023-05-28','2024-07-28',0),('MEM012','CUS013','Bạch Kim','2023-03-28','2024-07-28',0),('MEM013','CUS014','Bạc','2023-10-28','2024-07-28',0),('MEM008','CUS015','Bạc','2023-06-28','2024-07-28',0),('MEM014','CUS018','Bạc','2023-12-28','2024-07-28',0),('MEM017','CUS022','Bạc','2023-09-28','2024-07-28',0),('MEM018','CUS023','Bạc','2023-04-28','2024-07-28',0),('MEM019','CUS025','Vàng','2023-04-28','2024-07-28',0),('MEM020','CUS027','Bạc','2023-04-28','2024-07-28',0),('MEM007','CUS029','Bạc','2023-06-28','2024-07-28',0),('MEM006','CUS030','Bạch Kim','2023-06-28','2024-07-28',0);
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
INSERT INTO `MEMBERSHIP_TYPE` VALUES ('Bạc',10,0),('Bạch Kim',30,0),('Vàng',20,0);
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
INSERT INTO `PUBLISHER` VALUES ('NXB001','Neptune','Woa@gmail.com','Temp','0393406364','',0);
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
                               `MA_NV` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                               `MA_KH` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                               `IS_DELETED` tinyint(1) DEFAULT NULL,
                               `CREATED_AT` datetime DEFAULT NULL,
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

-- Dump completed on 2023-05-01  6:10:24