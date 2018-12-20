-- MySQL dump 10.13  Distrib 5.7.18, for Win64 (x86_64)
--
-- Host: localhost    Database: pony
-- ------------------------------------------------------
-- Server version	5.5.5-10.2.7-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `imsi01`
--

DROP TABLE IF EXISTS `imsi01`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imsi01` (
  `C1` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imsi01`
--

LOCK TABLES `imsi01` WRITE;
/*!40000 ALTER TABLE `imsi01` DISABLE KEYS */;
/*!40000 ALTER TABLE `imsi01` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tdacm00010`
--

DROP TABLE IF EXISTS `tdacm00010`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdacm00010` (
  `PKG_FUL_NM` varchar(20) NOT NULL COMMENT '패키지풀명',
  `PKG2_NM` char(2) DEFAULT NULL COMMENT '패키지2자리명',
  `PKG3_NM` char(3) DEFAULT NULL COMMENT '패키지3자리명',
  `PKG4_NM` char(4) DEFAULT NULL COMMENT '패키지4자리명',
  `PKG_HNM` varchar(10) DEFAULT NULL COMMENT '패키지한글명',
  `PKG_DESC` varchar(200) DEFAULT NULL COMMENT '패키지설명',
  `USE_YN` char(1) DEFAULT NULL COMMENT '사용유무',
  `REG_DTM` varchar(14) DEFAULT NULL COMMENT '등록일시',
  `REG_USR_ID` varchar(20) DEFAULT NULL COMMENT '등록자',
  `UPD_DTM` varchar(14) DEFAULT NULL COMMENT '변경일시',
  `UPD_USR_ID` varchar(20) DEFAULT NULL COMMENT '변경자',
  PRIMARY KEY (`PKG_FUL_NM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='패키지정보';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tdacm00010`
--

LOCK TABLES `tdacm00010` WRITE;
/*!40000 ALTER TABLE `tdacm00010` DISABLE KEYS */;
INSERT INTO `tdacm00010` VALUES ('data architecter','da','das','dams','데이터 아키텍쳐','테이블스키마관리 시스템','Y','201708040113','admin','201708040113','admin'),('system','sy','sys','syst','시스템','시스템','Y','201708040113','admin','201708040113','admin'),('user','ur','usr','user','유저','유저 관리 시스템','Y','201708040113','admin','201708040113','admin');
/*!40000 ALTER TABLE `tdacm00010` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tdacm00020`
--

DROP TABLE IF EXISTS `tdacm00020`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdacm00020` (
  `ABBR_NM` varchar(10) NOT NULL COMMENT '약어명',
  `ABBR_FUL_NM` varchar(20) DEFAULT NULL COMMENT '약어풀명',
  `ABBR_HNM` varchar(10) DEFAULT NULL COMMENT '약어한글명',
  `ABBR_DESC` varchar(200) DEFAULT NULL COMMENT '약어설명',
  `ABBR_APR_CD` char(2) DEFAULT NULL COMMENT '약어승인코드',
  `REG_DTM` varchar(14) DEFAULT NULL COMMENT '등록일시',
  `REG_USR_ID` varchar(20) DEFAULT NULL COMMENT '등록자',
  `UPD_DTM` varchar(14) DEFAULT NULL COMMENT '변경일시',
  `UPD_USR_ID` varchar(20) DEFAULT NULL COMMENT '변경자',
  PRIMARY KEY (`ABBR_NM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='약어정보';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tdacm00020`
--

LOCK TABLES `tdacm00020` WRITE;
/*!40000 ALTER TABLE `tdacm00020` DISABLE KEYS */;
/*!40000 ALTER TABLE `tdacm00020` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tdacm00021`
--

DROP TABLE IF EXISTS `tdacm00021`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdacm00021` (
  `ABBR_NM` varchar(10) NOT NULL COMMENT '약어명',
  `ABBR_FUL_NM` varchar(20) DEFAULT NULL COMMENT '약어풀명',
  `ABBR_HNM` varchar(10) DEFAULT NULL COMMENT '약어한글명',
  `ABBR_DESC` varchar(200) DEFAULT NULL COMMENT '약어설명',
  `REG_DTM` varchar(14) DEFAULT NULL COMMENT '등록일시',
  `REG_USR_ID` varchar(20) DEFAULT NULL COMMENT '등록자',
  `UPD_DTM` varchar(14) DEFAULT NULL COMMENT '변경일시',
  `UPD_USR_ID` varchar(20) DEFAULT NULL COMMENT '변경자'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='약어임시정보';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tdacm00021`
--

LOCK TABLES `tdacm00021` WRITE;
/*!40000 ALTER TABLE `tdacm00021` DISABLE KEYS */;
/*!40000 ALTER TABLE `tdacm00021` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tdacm00030`
--

DROP TABLE IF EXISTS `tdacm00030`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdacm00030` (
  `INFO_TYPE_NM` varchar(30) NOT NULL COMMENT '인포타입명',
  `INFO_TYPE_HNM` varchar(30) DEFAULT NULL COMMENT '인포타입한글명',
  `DOMAIN_NM` varchar(20) DEFAULT NULL COMMENT '도메인명',
  `DOMAIN_HNM` varchar(20) DEFAULT NULL COMMENT '도메인한글명',
  `DATA_TYPE_NM` varchar(20) DEFAULT NULL COMMENT '데이터타입명',
  `LEN` int(11) DEFAULT NULL COMMENT '길이',
  `DECIMAL_CNT` int(11) DEFAULT NULL COMMENT '소수점수',
  `DATA_TYPE_DESC` varchar(50) DEFAULT NULL COMMENT '데이터타입설명',
  `REG_DTM` varchar(14) DEFAULT NULL COMMENT '등록일시',
  `REG_USR_ID` varchar(20) DEFAULT NULL COMMENT '등록자',
  `UPD_DTM` varchar(14) DEFAULT NULL COMMENT '변경일시',
  `UPD_USR_ID` varchar(20) DEFAULT NULL COMMENT '변경자',
  PRIMARY KEY (`INFO_TYPE_NM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='인포타입';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tdacm00030`
--

LOCK TABLES `tdacm00030` WRITE;
/*!40000 ALTER TABLE `tdacm00030` DISABLE KEYS */;
/*!40000 ALTER TABLE `tdacm00030` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tdacm00040`
--

DROP TABLE IF EXISTS `tdacm00040`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdacm00040` (
  `COL_NM` varchar(20) NOT NULL COMMENT '컬럼명',
  `COL_HNM` varchar(20) DEFAULT NULL COMMENT '컬럼한글명',
  `COL_DESC` varchar(200) DEFAULT NULL COMMENT '컬럼설명',
  `COL_ABBR_HNM` varchar(30) DEFAULT NULL COMMENT '컬럼약어한글명',
  `INFO_TYPE_NM` varchar(30) DEFAULT NULL COMMENT '인포타입명',
  `USE_YN` char(1) DEFAULT NULL COMMENT '사용여부',
  `REG_DTM` varchar(14) DEFAULT NULL COMMENT '등록일시',
  `REG_USR_ID` varchar(20) DEFAULT NULL COMMENT '등록자',
  `UPD_DTM` varchar(14) DEFAULT NULL COMMENT '변경일시',
  `UPD_USR_ID` varchar(20) DEFAULT NULL COMMENT '변경자',
  PRIMARY KEY (`COL_NM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='용어사전';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tdacm00040`
--

LOCK TABLES `tdacm00040` WRITE;
/*!40000 ALTER TABLE `tdacm00040` DISABLE KEYS */;
/*!40000 ALTER TABLE `tdacm00040` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tdacm00041`
--

DROP TABLE IF EXISTS `tdacm00041`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdacm00041` (
  `COL_NM` varchar(20) NOT NULL COMMENT '컬럼명',
  `COL_HNM` varchar(20) DEFAULT NULL COMMENT '컬럼한글명',
  `COL_DESC` varchar(200) DEFAULT NULL COMMENT '컬럼설명',
  `COL_ABBR_HNM` varchar(30) DEFAULT NULL COMMENT '컬럼약어한글명',
  `INFO_TYPE_NM` varchar(30) DEFAULT NULL COMMENT '인포타입명',
  `USE_YN` char(1) DEFAULT NULL COMMENT '사용여부',
  `REG_DTM` varchar(14) DEFAULT NULL COMMENT '등록일시',
  `REG_USR_ID` varchar(20) DEFAULT NULL COMMENT '등록자',
  `UPD_DTM` varchar(14) DEFAULT NULL COMMENT '변경일시',
  `UPD_USR_ID` varchar(20) DEFAULT NULL COMMENT '변경자',
  PRIMARY KEY (`COL_NM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='용어사전임시1';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tdacm00041`
--

LOCK TABLES `tdacm00041` WRITE;
/*!40000 ALTER TABLE `tdacm00041` DISABLE KEYS */;
/*!40000 ALTER TABLE `tdacm00041` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tdacm00042`
--

DROP TABLE IF EXISTS `tdacm00042`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdacm00042` (
  `COL_NM` varchar(20) NOT NULL COMMENT '컬럼명',
  `COL_HNM` varchar(20) DEFAULT NULL COMMENT '컬럼한글명',
  `COL_DESC` varchar(200) DEFAULT NULL COMMENT '컬럼설명',
  `COL_ABBR_HNM` varchar(30) DEFAULT NULL COMMENT '컬럼약어한글명',
  `INFO_TYPE_NM` varchar(30) DEFAULT NULL COMMENT '인포타입명',
  `REG_DTM` varchar(14) DEFAULT NULL COMMENT '등록일시',
  `REG_USR_ID` varchar(20) DEFAULT NULL COMMENT '등록자',
  `UPD_DTM` varchar(14) DEFAULT NULL COMMENT '변경일시',
  `UPD_USR_ID` varchar(20) DEFAULT NULL COMMENT '변경자',
  PRIMARY KEY (`COL_NM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='용어사전임시2';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tdacm00042`
--

LOCK TABLES `tdacm00042` WRITE;
/*!40000 ALTER TABLE `tdacm00042` DISABLE KEYS */;
/*!40000 ALTER TABLE `tdacm00042` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tdacm00050`
--

DROP TABLE IF EXISTS `tdacm00050`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdacm00050` (
  `CD_ID_GRP_NM` varchar(10) NOT NULL COMMENT '코드아이디그룹명',
  `CD_ID_GRP_HNM` varchar(10) DEFAULT NULL COMMENT '코드아이디그룹한글명',
  `CD_ID_GRP_DESC` varchar(50) DEFAULT NULL COMMENT '코드아이디그룹설명',
  `REG_DTM` varchar(14) DEFAULT NULL COMMENT '등록일시',
  `REG_USR_ID` varchar(20) DEFAULT NULL COMMENT '등록자',
  `UPD_DTM` varchar(14) DEFAULT NULL COMMENT '변경일시',
  `UPD_USR_ID` varchar(20) DEFAULT NULL COMMENT '변경자',
  PRIMARY KEY (`CD_ID_GRP_NM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='코드그룹';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tdacm00050`
--

LOCK TABLES `tdacm00050` WRITE;
/*!40000 ALTER TABLE `tdacm00050` DISABLE KEYS */;
/*!40000 ALTER TABLE `tdacm00050` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tdacm00070`
--

DROP TABLE IF EXISTS `tdacm00070`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdacm00070` (
  `DB_NM` varchar(10) NOT NULL COMMENT 'DB명',
  `OWNER` varchar(15) NOT NULL COMMENT '소유자',
  `TAB_NM` varchar(20) NOT NULL COMMENT '테이블명',
  `TAB_HNM` varchar(20) DEFAULT NULL COMMENT '테이블한글명',
  `TAB_DESC` varchar(200) DEFAULT NULL COMMENT '테이블설명',
  `REG_DTM` varchar(14) DEFAULT NULL COMMENT '등록일시',
  `REG_USR_ID` varchar(20) DEFAULT NULL COMMENT '등록자',
  `UPD_DTM` varchar(14) DEFAULT NULL COMMENT '변경일시',
  `UPD_USR_ID` varchar(20) DEFAULT NULL COMMENT '변경자',
  PRIMARY KEY (`DB_NM`,`OWNER`,`TAB_NM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='테이블정보';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tdacm00070`
--

LOCK TABLES `tdacm00070` WRITE;
/*!40000 ALTER TABLE `tdacm00070` DISABLE KEYS */;
/*!40000 ALTER TABLE `tdacm00070` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tdacm00080`
--

DROP TABLE IF EXISTS `tdacm00080`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tdacm00080` (
  `DB_NM` varchar(10) NOT NULL COMMENT 'DB명',
  `OWNER` varchar(15) NOT NULL COMMENT '소유자',
  `TAB_NM` varchar(20) NOT NULL COMMENT '테이블명',
  `COL_NM` varchar(20) NOT NULL COMMENT '컬럼명',
  `COL_HNM` varchar(20) DEFAULT NULL COMMENT '컬럼한글명',
  `COL_DESC` varchar(200) DEFAULT NULL COMMENT '컬럼설명',
  `DATA_TYPE_NM` varchar(20) DEFAULT NULL COMMENT '데이터타입명',
  `LEN` int(11) DEFAULT NULL COMMENT '길이',
  `DECIMAL_CNT` int(11) DEFAULT NULL COMMENT '소수점수',
  `DATA_TYPE_DESC` varchar(50) DEFAULT NULL COMMENT '데이터타입설명',
  `REG_DTM` varchar(14) DEFAULT NULL COMMENT '등록일시',
  `REG_USR_ID` varchar(20) DEFAULT NULL COMMENT '등록자',
  `UPD_DTM` varchar(14) DEFAULT NULL COMMENT '변경일시',
  `UPD_USR_ID` varchar(20) DEFAULT NULL COMMENT '변경자',
  PRIMARY KEY (`DB_NM`,`OWNER`,`TAB_NM`,`COL_NM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='컬럼정보';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tdacm00080`
--

LOCK TABLES `tdacm00080` WRITE;
/*!40000 ALTER TABLE `tdacm00080` DISABLE KEYS */;
/*!40000 ALTER TABLE `tdacm00080` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tsyur0001`
--

DROP TABLE IF EXISTS `tsyur0001`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tsyur0001` (
  `USER_ID` varchar(20) NOT NULL COMMENT '사용자아이디',
  `USER_EMAIL` varchar(40) NOT NULL COMMENT '사용자이메일',
  `USER_PW` varchar(20) NOT NULL COMMENT '사용자암호',
  `USER_NM` varchar(40) NOT NULL COMMENT '사용자명',
  `USER_AGE` int(11) DEFAULT NULL COMMENT '사용자나이',
  `USE_YN` char(1) NOT NULL COMMENT '사용여부',
  `REG_DT` date DEFAULT NULL COMMENT '등록일시',
  `REG_USER_ID` varchar(20) DEFAULT NULL COMMENT '등록사용자아이디',
  `UPD_DT` date DEFAULT NULL COMMENT '변경일시',
  `UPD_USER_ID` varchar(20) DEFAULT NULL COMMENT '변경사용자아이디',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사용자';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tsyur0001`
--

LOCK TABLES `tsyur0001` WRITE;
/*!40000 ALTER TABLE `tsyur0001` DISABLE KEYS */;
INSERT INTO `tsyur0001` VALUES ('AAAAAAA','AAAAAAA','AAAAAA','AAAAAA',40,'Y','2017-08-01',NULL,'2017-08-01',NULL),('ADMIN','ADMIN@PMOSOFT.NET','1','ADMIN',50,'Y','2017-07-30','ADMIN','2017-07-30','ADMIN'),('ADMIN2','ADMIN2@PMOSOFT.NET','1','ADMIN',50,'Y','2017-07-30','ADMIN2','2017-07-30','ADMIN2'),('ADMIN3','ADMIN3@PMOSOFT.NET','1','ADMIN',50,'Y','2017-07-30','ADMIN3','2017-07-30','ADMIN3'),('ADMIN4','ADMIN4@PMOSOFT.NET','1','ADMIN',50,'Y','2017-07-30','ADMIN4','2017-07-30','ADMIN4');
/*!40000 ALTER TABLE `tsyur0001` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tsyur00010`
--

DROP TABLE IF EXISTS `tsyur00010`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tsyur00010` (
  `USR_ID` varchar(40) NOT NULL COMMENT '사용자아이디',
  `USR_EMAIL` varchar(40) NOT NULL COMMENT '사용자이메일',
  `USR_PW` varchar(20) NOT NULL COMMENT '사용자암호',
  `USR_NM` varchar(40) NOT NULL COMMENT '사용자명',
  `USR_AGE` int(11) DEFAULT NULL COMMENT '사용자나이',
  `USE_YN` char(1) NOT NULL COMMENT '사용여부',
  `REG_DT` date DEFAULT NULL COMMENT '등록일시',
  `REG_USR_ID` varchar(20) DEFAULT NULL COMMENT '등록사용자아이디',
  `UPD_DT` date DEFAULT NULL COMMENT '변경일시',
  `UPD_USR_ID` varchar(20) DEFAULT NULL COMMENT '변경사용자아이디',
  PRIMARY KEY (`USR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사용자';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tsyur00010`
--

LOCK TABLES `tsyur00010` WRITE;
/*!40000 ALTER TABLE `tsyur00010` DISABLE KEYS */;
INSERT INTO `tsyur00010` VALUES ('ADMIN','ADMIN@PMOSOFT.NET','1','ADMIN',50,'Y','2017-08-04','ADMIN','2017-08-04','ADMIN'),('ADMIN2','ADMIN2@PMOSOFT.NET','1','ADMIN',50,'Y','2017-08-04','ADMIN2','2017-08-04','ADMIN2'),('ADMIN3','ADMIN3@PMOSOFT.NET','1','ADMIN',50,'Y','2017-08-04','ADMIN3','2017-08-04','ADMIN3'),('ADMIN4','ADMIN4@PMOSOFT.NET','1','ADMIN',50,'Y','2017-08-04','ADMIN4','2017-08-04','ADMIN4');
/*!40000 ALTER TABLE `tsyur00010` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tsyur00030`
--

DROP TABLE IF EXISTS `tsyur00030`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tsyur00030` (
  `USR_ID` varchar(40) NOT NULL COMMENT '사용자아이디',
  `USR_PRJ_ID` varchar(40) NOT NULL COMMENT '사용자프로젝트아이디',
  `REG_DT` date DEFAULT NULL COMMENT '등록일시',
  `REG_USR_ID` varchar(20) DEFAULT NULL COMMENT '등록사용자아이디',
  `UPD_DT` date DEFAULT NULL COMMENT '변경일시',
  `UPD_USR_ID` varchar(20) DEFAULT NULL COMMENT '변경사용자아이디',
  PRIMARY KEY (`USR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사용자프로젝트';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tsyur00030`
--

LOCK TABLES `tsyur00030` WRITE;
/*!40000 ALTER TABLE `tsyur00030` DISABLE KEYS */;
/*!40000 ALTER TABLE `tsyur00030` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-11 20:02:15
