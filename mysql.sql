-- --------------------------------------------------------
-- 主机:                           192.168.10.129
-- 服务器版本:                        5.7.32 - MySQL Community Server (GPL)
-- 服务器操作系统:                      linux-glibc2.12
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 pets 的数据库结构
CREATE DATABASE IF NOT EXISTS `pets` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `pets`;


-- 导出  表 pets.account 结构
CREATE TABLE IF NOT EXISTS `account` (
  `userid` int(111) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4;

-- 正在导出表  pets.account 的数据：~36 rows (大约)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
REPLACE INTO `account` (`userid`, `username`, `password`, `email`, `name`, `address`) VALUES
	(1, 'weikun', '119', 'wk2003119@163.com', '尚品', '尚品'),
	(2, 'song', '111', 'wk2003119@163.com', '尚品', '尚品'),
	(3, 'tong', '123456', '1434@qq.com', '杨林', 'sfefeff'),
	(4, 'KKK', '123', '123@123.com', '123', '121212'),
	(5, 'KKKK', '123', '123@123.com', '123', '121212'),
	(6, 'PPP', '111', '123@123.com', '2', '34'),
	(7, 'UUU', '111', '123@123.com', '12312', '123123'),
	(9, 'tt', '119', '123@123.com', '11212', '23434'),
	(10, 'PP', '111', '123@123.com', '222', '333'),
	(11, '3333', '111', '123@123.com', 'weikun', '1222'),
	(12, 'weikun888', '111', '123@123.com', '123', 'jjjj'),
	(13, 'ooo', '111', '123@123.com', '222', '222'),
	(14, '555', '119', '123@123.com', '123123', '123123'),
	(18, '6767', '8998', '9898', '8998', '8989'),
	(19, 'MIKE', '111', '11', '123', '12312'),
	(21, '883891', '111', '123@123.com', '123213', '123213'),
	(22, '883891', '111', '123@123.com', '123213', '123213'),
	(23, '883891', '111', '123@123.com', '123213', '123213'),
	(24, 'sdf12123', '111', '123@123.com', '123213', '12321321321'),
	(25, '222', '119', '123@123.com', '', ''),
	(26, '444', '111', '123@123.com', '', ''),
	(27, '55555', '111', '123@123.com', '211221', '121212'),
	(28, '54555', '111', '123@123.com', '1212', '122112'),
	(29, '6666222', '111', '123@123.com', '123213', '123213'),
	(30, '2323333', '1111', '123@123.com', '123213', '121212'),
	(31, '4444', '111', '123@123.com', '12321312', '12312321'),
	(32, '4444', '111', '123@123.com', '12321312', '12312321'),
	(33, '6666', '111', '123@123.com', '12321312', '12312312'),
	(34, 'weikun33434', '54555', '123@123.com', '1221', ''),
	(35, '3434434', '119', '123@123.com', '123123', '12312312'),
	(49, 'sadsad', 'aaa', '2112@112.aaa', 'sda', 'sada'),
	(50, 'sadsad', 'aaa', '2112@112.aaa', 'sda', 'sada'),
	(51, 'ssss', 'aaaa', 'sss@ss.ss', 'aaa', 'aaa'),
	(52, 'yunlong', '123', '123@123.com', 'CYL', '1256'),
	(53, '3', '3', '123@123.ccc', 'cc', 'cc'),
	(54, 'root', 'root', 'root@root.root', 'root', 'root');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;


-- 导出  表 pets.cart 结构
CREATE TABLE IF NOT EXISTS `cart` (
  `userid` int(11) NOT NULL,
  `orderid` int(11) NOT NULL,
  `itemid` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`userid`,`orderid`,`itemid`),
  KEY `itemid` (`itemid`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`userid`, `orderid`) REFERENCES `orders` (`userid`, `orderid`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`itemid`) REFERENCES `item` (`itemid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 正在导出表  pets.cart 的数据：~104 rows (大约)
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
REPLACE INTO `cart` (`userid`, `orderid`, `itemid`, `quantity`) VALUES
	(1, 1, 5, 2),
	(1, 1, 22, 3),
	(1, 2, 6, 1),
	(1, 2, 13, 1),
	(1, 3, 8, 4),
	(1, 4, 5, 2),
	(1, 4, 13, 1),
	(1, 4, 25, 3),
	(1, 5, 13, 8),
	(1, 5, 19, 5),
	(1, 6, 7, 1),
	(1, 7, 13, 1),
	(1, 8, 2, 1),
	(1, 8, 5, 2),
	(1, 9, 11, 2),
	(1, 9, 25, 3),
	(1, 10, 4, 3),
	(1, 10, 6, 3),
	(1, 10, 28, 5),
	(1, 11, 7, 1),
	(1, 12, 8, 3),
	(1, 12, 26, 3),
	(1, 13, 5, 1),
	(1, 13, 20, 1),
	(1, 14, 27, 1),
	(1, 15, 14, 4),
	(1, 15, 23, 1),
	(1, 16, 6, 0),
	(1, 16, 7, 2),
	(1, 16, 22, 5),
	(1, 17, 4, 1),
	(1, 17, 13, 2),
	(1, 18, 8, 1),
	(1, 19, 1, 11),
	(1, 19, 23, 10),
	(1, 20, 13, 2),
	(1, 21, 5, 1),
	(1, 22, 11, 1),
	(1, 22, 21, 4),
	(1, 23, 5, 2),
	(1, 23, 12, 1),
	(1, 24, 6, 4),
	(1, 25, 5, 1),
	(1, 25, 24, 3),
	(1, 26, 20, 1),
	(1, 27, 20, 2),
	(1, 28, 9, 2),
	(1, 29, 19, 7),
	(1, 29, 27, 4),
	(1, 31, 3, 3),
	(1, 31, 24, 8),
	(1, 32, 13, 2),
	(1, 33, 14, 2),
	(1, 34, 2, 1),
	(1, 35, 4, 1),
	(1, 35, 13, 1),
	(1, 36, 13, 4),
	(1, 36, 27, 1),
	(1, 37, 5, 4),
	(1, 37, 21, 4),
	(1, 37, 27, 3),
	(1, 38, 27, 1),
	(1, 39, 2, 1),
	(1, 39, 6, 1),
	(1, 39, 9, 2),
	(1, 40, 2, 1),
	(1, 40, 9, 1),
	(1, 40, 10, 1),
	(1, 40, 11, 1),
	(1, 41, 2, 1),
	(1, 42, 7, 1),
	(1, 42, 9, 1),
	(1, 43, 2, 2),
	(1, 44, 14, 1),
	(2, 1, 23, 3),
	(2, 2, 23, 2),
	(2, 3, 7, 1),
	(2, 3, 27, 1),
	(2, 4, 24, 1),
	(2, 5, 1, 3),
	(2, 5, 5, 2),
	(2, 5, 22, 1),
	(2, 5, 26, 1),
	(2, 6, 24, 1),
	(3, 1, 8, 3),
	(3, 2, 5, 1),
	(6, 1, 5, 3),
	(6, 1, 23, 2),
	(6, 2, 5, 1),
	(6, 2, 22, 3),
	(6, 3, 3, 1),
	(6, 3, 5, 1),
	(7, 1, 21, 1),
	(7, 1, 24, 2),
	(9, 1, 20, 1),
	(9, 2, 9, 2),
	(9, 3, 19, 1),
	(9, 4, 21, 1),
	(9, 5, 12, 22),
	(10, 1, 9, 1),
	(10, 2, 5, 2),
	(54, 1, 20, 22),
	(54, 1, 24, 43),
	(54, 2, 25, 22);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;


-- 导出  表 pets.category 结构
CREATE TABLE IF NOT EXISTS `category` (
  `catid` int(11) NOT NULL AUTO_INCREMENT,
  `catno` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `descn` varchar(100) NOT NULL,
  PRIMARY KEY (`catid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- 正在导出表  pets.category 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
REPLACE INTO `category` (`catid`, `catno`, `name`, `descn`) VALUES
	(1, 'BIRDS', '鸟类', ''),
	(2, 'CATS', '猫', '<image src="${ppath}/static/images/cats_icon.gif"><font size="5" color="blue"> Cats</font>'),
	(3, 'DOGS', '狗', '<image src="${ppath}/static/images/dogs_icon.gif"><font size="5" color="blue"> Dogs</font>'),
	(4, 'FISH', '鱼', '<image src="${ppath}/static/images/fish_icon.gif"><font size="5" color="blue"> Fish</font>'),
	(5, 'REPTILES', '爬虫类', '<image src="${ppath}/static/images/reptiles_icon.gif"><font size="5" color="blue"> Reptiles</font>');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;


-- 导出  表 pets.item 结构
CREATE TABLE IF NOT EXISTS `item` (
  `itemid` int(11) NOT NULL AUTO_INCREMENT,
  `itemno` varchar(100) NOT NULL,
  `productid` int(11) NOT NULL,
  `listprice` double NOT NULL,
  `attr1` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`itemid`),
  KEY `productid` (`productid`),
  CONSTRAINT `item_ibfk_1` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

-- 正在导出表  pets.item 的数据：~28 rows (大约)
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
REPLACE INTO `item` (`itemid`, `itemno`, `productid`, `listprice`, `attr1`) VALUES
	(1, 'EST_1', 5, 16.5, 'Large'),
	(2, 'EST_10', 11, 18.5, 'Spotted Adult Female'),
	(3, 'EST_11', 16, 18.5, 'Venomless'),
	(4, 'EST_12', 16, 18.5, 'Rattleless'),
	(5, 'EST_13', 15, 18.5, 'Green Adult'),
	(6, 'EST_14', 8, 58.5, 'Tailless'),
	(7, 'EST_15', 8, 23.5, 'With tail'),
	(8, 'EST_16', 7, 93.5, 'Adult Female'),
	(9, 'EST_17', 7, 93.5, 'Adult Male'),
	(10, 'EST_18', 1, 193.5, 'Adult Male'),
	(11, 'EST_19', 2, 15.5, 'Adult Male'),
	(12, 'EST_2', 5, 16.5, 'Small'),
	(13, 'EST_20', 4, 5.5, 'Adult Male'),
	(14, 'EST_21', 4, 5.29, 'Adult Female'),
	(15, 'EST_22', 14, 135.5, 'Adult Male'),
	(16, 'EST_23', 14, 145.49, 'Adult Female'),
	(17, 'EST_24', 14, 255.5, 'Adult Male'),
	(18, 'EST_25', 14, 325.29, 'Adult Female'),
	(19, 'EST_26', 10, 125.5, 'Adult Male'),
	(20, 'EST_27', 10, 155.29, 'Adult Female'),
	(21, 'EST_28', 13, 155.29, 'Adult Female'),
	(22, 'EST_3', 6, 18.5, 'Toothless'),
	(23, 'EST_4', 3, 18.5, 'Spotted'),
	(24, 'EST_5', 3, 18.5, 'Spotless'),
	(25, 'EST_6', 9, 18.5, 'Male Adult'),
	(26, 'EST_7', 9, 18.5, 'Female Puppy'),
	(27, 'EST_8', 12, 18.5, 'Male Puppy'),
	(28, 'EST_9', 11, 18.5, 'Spotless Male Puppy');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;


-- 导出  表 pets.orders 结构
CREATE TABLE IF NOT EXISTS `orders` (
  `userid` int(11) NOT NULL,
  `orderid` int(11) NOT NULL,
  `orderdate` date DEFAULT NULL,
  `totalprice` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`userid`,`orderid`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `account` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 正在导出表  pets.orders 的数据：~67 rows (大约)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
REPLACE INTO `orders` (`userid`, `orderid`, `orderdate`, `totalprice`) VALUES
	(1, 1, '2018-02-12', 931.20),
	(1, 2, '2018-02-12', 64.00),
	(1, 3, '2018-02-12', 374.00),
	(1, 4, '2018-02-12', 93.00),
	(1, 5, '2018-02-12', 672.00),
	(1, 6, '2018-02-12', 24.00),
	(1, 7, '2018-02-12', 6.00),
	(1, 8, '2018-02-12', 56.00),
	(1, 9, '2018-02-12', 87.00),
	(1, 10, '2018-02-13', 324.00),
	(1, 11, '2018-02-13', 24.00),
	(1, 12, '2018-02-13', 336.00),
	(1, 13, '2018-06-30', 190.00),
	(1, 14, '2018-06-30', 19.00),
	(1, 15, '2018-09-19', 37.00),
	(1, 16, '2018-07-28', 140.00),
	(1, 17, '2018-07-28', 30.00),
	(1, 18, '2018-07-28', 94.00),
	(1, 19, '2018-11-03', 367.00),
	(1, 20, '2018-11-03', 11.00),
	(1, 21, '2018-11-03', 19.00),
	(1, 22, '2018-11-03', 637.00),
	(1, 23, '2018-11-03', 54.00),
	(1, 24, '2018-11-03', 234.00),
	(1, 25, '2018-12-13', 74.00),
	(1, 26, '2018-12-13', 155.00),
	(1, 27, '2018-12-13', 311.00),
	(1, 28, '2018-12-13', 187.00),
	(1, 29, '2019-04-24', 953.00),
	(1, 30, '2019-04-24', 74.00),
	(1, 31, '2019-05-21', 204.00),
	(1, 32, '2019-05-21', 11.00),
	(1, 33, '2019-05-21', 11.00),
	(1, 34, '2019-05-21', 19.00),
	(1, 35, '2019-05-21', 24.00),
	(1, 36, '2019-05-21', 41.00),
	(1, 37, '2019-05-21', 751.00),
	(1, 38, '2019-05-21', 19.00),
	(1, 39, '2019-05-21', 264.00),
	(1, 40, '2019-05-21', 321.00),
	(1, 41, '2019-05-25', 621.00),
	(1, 42, '2019-05-25', 117.00),
	(1, 43, '2019-05-25', 37.00),
	(1, 44, NULL, NULL),
	(2, 1, '2018-02-13', 56.00),
	(2, 2, '2018-06-30', 17.00),
	(2, 3, '2018-06-30', 19.00),
	(2, 4, '2018-06-30', 19.00),
	(2, 5, '2018-07-01', 87.00),
	(2, 6, '2018-09-21', 19.00),
	(3, 1, '2018-07-02', 281.00),
	(3, 2, '2018-07-02', 19.00),
	(6, 1, '2018-02-13', 93.00),
	(6, 2, '2018-02-14', 74.00),
	(6, 3, NULL, 0.00),
	(7, 1, NULL, 0.00),
	(9, 1, '2018-05-03', 155.00),
	(9, 2, '2018-05-03', 187.00),
	(9, 3, '2018-05-03', 126.00),
	(9, 4, '2018-05-03', 155.00),
	(9, 5, '2020-11-08', 363.00),
	(9, 6, NULL, NULL),
	(10, 1, '2018-05-03', 94.00),
	(10, 2, '2018-05-03', 37.00),
	(54, 1, '2020-11-08', 4211.88),
	(54, 2, '2020-11-08', 407.00),
	(54, 3, NULL, NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;


-- 导出  表 pets.product 结构
CREATE TABLE IF NOT EXISTS `product` (
  `productid` int(11) NOT NULL AUTO_INCREMENT,
  `productno` varchar(100) NOT NULL,
  `catid` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `descn` varchar(100) NOT NULL,
  `pic` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`productid`),
  KEY `catid` (`catid`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`catid`) REFERENCES `category` (`catid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- 正在导出表  pets.product 的数据：~16 rows (大约)
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
REPLACE INTO `product` (`productid`, `productno`, `catid`, `name`, `descn`, `pic`) VALUES
	(1, 'AV-CB-01', 1, 'Amazon Parrot', 'Great companion for up to 75 years', 'bird4.gif'),
	(2, 'AV-SB-02', 1, 'Finch', 'Great stress reliever', 'bird1.gif'),
	(3, 'FI-FW-01', 4, 'Koi', 'Fresh Water fish from Japan', 'fish3.gif'),
	(4, 'FI-FW-02', 4, 'Goldfish', 'Fresh Water fish from China', 'fish2.gif'),
	(5, 'FI-SW-01', 4, 'Angelfish', 'Salt Water fish from Australia', 'fish1.jpg'),
	(6, 'FI-SW-02', 4, 'Tiger Shark', 'Salt Water fish from Australia', 'fish4.gif'),
	(7, 'FL-DLH-02', 2, 'Persian', 'Friendly house cat, doubles as a princess', 'cat1.gif'),
	(8, 'FL-DSH-01', 2, 'Manx', 'Great for reducing mouse populations', 'cat3.gif'),
	(9, 'K9-BD-01', 3, 'Bulldog', 'Friendly dog from England', 'dog2.gif'),
	(10, 'K9-CW-01', 3, 'Chihuahua', 'Great companion dog', 'dog4.gif'),
	(11, 'K9-DL-01', 3, 'Dalmation', 'Great dog for a Fire Station', 'dog5.gif'),
	(12, 'K9-PO-02', 3, 'Poodle', 'Cute dog from France', 'dog6.gif'),
	(13, 'K9-RT-01', 3, 'Golden Retriever', 'Great family dog', 'dog1.gif'),
	(14, 'K9-RT-02', 3, 'Labrador Retriever', 'Great hunting dog', 'dog3.gif'),
	(15, 'RP-LI-02', 5, 'Iguana', 'Friendly green friend', 'lizard2.gif'),
	(16, 'RP-SN-01', 5, 'Rattlesnake', 'Doubles as a watch dog', 'lizard3.gif');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;


-- 导出  表 pets.profile 结构
CREATE TABLE IF NOT EXISTS `profile` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `lang` varchar(20) NOT NULL,
  `catid` int(11) NOT NULL,
  PRIMARY KEY (`userid`),
  KEY `catid` (`catid`),
  CONSTRAINT `profile_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `account` (`userid`),
  CONSTRAINT `profile_ibfk_2` FOREIGN KEY (`catid`) REFERENCES `category` (`catid`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4;

-- 正在导出表  pets.profile 的数据：~33 rows (大约)
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
REPLACE INTO `profile` (`userid`, `lang`, `catid`) VALUES
	(1, 'eng', 1),
	(5, 'eng', 2),
	(6, 'eng', 2),
	(7, 'eng', 4),
	(9, 'eng', 4),
	(10, 'eng', 4),
	(11, 'eng', 1),
	(12, 'eng', 4),
	(13, 'eng', 4),
	(14, 'eng', 4),
	(18, 'eng', 2),
	(19, 'eng', 2),
	(21, 'eng', 2),
	(22, 'eng', 2),
	(23, 'eng', 2),
	(24, 'eng', 4),
	(25, 'chi', 4),
	(26, 'eng', 3),
	(27, 'eng', 4),
	(28, 'eng', 2),
	(29, 'eng', 3),
	(30, 'eng', 3),
	(31, 'eng', 4),
	(32, 'eng', 4),
	(33, 'eng', 4),
	(34, 'chi', 1),
	(35, 'chi', 5),
	(49, 'eng', 4),
	(50, 'eng', 4),
	(51, 'eng', 4),
	(52, 'eng', 2),
	(53, 'eng', 2),
	(54, 'eng', 2);
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
