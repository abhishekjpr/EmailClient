-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.22-rc-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema emailclient
--

CREATE DATABASE IF NOT EXISTS emailclient;
USE emailclient;

--
-- Definition of table `drafts`
--

DROP TABLE IF EXISTS `drafts`;
CREATE TABLE `drafts` (
  `To` varchar(45) DEFAULT NULL,
  `Subject` varchar(45) DEFAULT NULL,
  `Content` varchar(1000) DEFAULT NULL,
  `Date` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `drafts`
--

/*!40000 ALTER TABLE `drafts` DISABLE KEYS */;
/*!40000 ALTER TABLE `drafts` ENABLE KEYS */;


--
-- Definition of table `inbox`
--

DROP TABLE IF EXISTS `inbox`;
CREATE TABLE `inbox` (
  `FromId` varchar(45) DEFAULT NULL,
  `Subject` varchar(45) DEFAULT NULL,
  `Content` varchar(1000) DEFAULT NULL,
  `Date` varchar(45) NOT NULL DEFAULT 's',
  PRIMARY KEY (`Date`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `inbox`
--

/*!40000 ALTER TABLE `inbox` DISABLE KEYS */;
INSERT INTO `inbox` (`FromId`,`Subject`,`Content`,`Date`) VALUES 
 ('agoyal718@gmail.com','profe','kite.!!','Fri Jun 07 20:00:53 IST 2013'),
 ('xyz@hotmail.com','Hello','Hello','Fri Jun 07 20:02:41 IST 2013'),
 ('agoyal718@gmail.com','prog','programmmer','Fri Jun 07 20:04:33 IST 2013');
/*!40000 ALTER TABLE `inbox` ENABLE KEYS */;


--
-- Definition of table `outbox`
--

DROP TABLE IF EXISTS `outbox`;
CREATE TABLE `outbox` (
  `To` varchar(45) NOT NULL DEFAULT '',
  `Subject` varchar(45) DEFAULT NULL,
  `Content` varchar(1000) DEFAULT NULL,
  `Date` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `outbox`
--

/*!40000 ALTER TABLE `outbox` DISABLE KEYS */;
/*!40000 ALTER TABLE `outbox` ENABLE KEYS */;


--
-- Definition of table `sent`
--

DROP TABLE IF EXISTS `sent`;
CREATE TABLE `sent` (
  `To` varchar(45) DEFAULT NULL,
  `Subject` varchar(45) DEFAULT NULL,
  `Content` varchar(45) DEFAULT NULL,
  `Date` varchar(445) NOT NULL DEFAULT 'd',
  PRIMARY KEY (`Date`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sent`
--

/*!40000 ALTER TABLE `sent` DISABLE KEYS */;
/*!40000 ALTER TABLE `sent` ENABLE KEYS */;


--
-- Definition of table `trash`
--

DROP TABLE IF EXISTS `trash`;
CREATE TABLE `trash` (
  `From` varchar(45) DEFAULT NULL,
  `Subject` varchar(45) DEFAULT NULL,
  `Content` varchar(1145) DEFAULT NULL,
  `Date` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`Date`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `trash`
--

/*!40000 ALTER TABLE `trash` DISABLE KEYS */;
INSERT INTO `trash` (`From`,`Subject`,`Content`,`Date`) VALUES 
 ('info@monsterindia.com','Important - Employers cannot contact you','javax.mail.internet.MimeMultipart@117a5b','Fri Jun 07 07:40:48 IST 2013'),
 ('Goodreads <no-reply@mail.goodreads.com>','June Newsletter','javax.mail.internet.MimeMultipart@1f2a8f5','Fri Jun 07 07:43:58 IST 2013'),
 ('Reader Digest <info@monster.co.in>','READER\'s DIGEST Approval for Abhishek','javax.mail.internet.MimeMultipart@48d0ea','Fri Jun 07 08:00:02 IST 2013'),
 ('agoyal718@gmail.com','Demo Message 3','This is the third demo message..!!\r\n','Thu Jun 06 19:28:06 IST 2013'),
 ('agoyal718@gmail.com','Demo Message 4','This is the fourth demo message..!!\r\n','Thu Jun 06 19:28:32 IST 2013');
/*!40000 ALTER TABLE `trash` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
