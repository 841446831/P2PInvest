/*
Navicat MySQL Data Transfer

Source Server         : xiaoy
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : p2pinvest

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2016-08-12 14:29:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for borrower
-- ----------------------------
DROP TABLE IF EXISTS `borrower`;
CREATE TABLE `borrower` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `annualrate` double(255,2) NOT NULL,
  `total` int(11) NOT NULL,
  `timelimit` varchar(20) NOT NULL,
  `receivedway` varchar(255) DEFAULT NULL,
  `peoplenum` int(11) NOT NULL,
  `bpid` int(11) DEFAULT NULL,
  `investmoney` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bpid` (`bpid`),
  CONSTRAINT `borrower_ibfk_1` FOREIGN KEY (`bpid`) REFERENCES `borrowplatform` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrower
-- ----------------------------
INSERT INTO `borrower` VALUES ('3', '20.00', '46000', '24天', '到期付款还息', '212', '1', '40000');
INSERT INTO `borrower` VALUES ('4', '15.00', '50000', '12天', '到期付款', '331', '3', '40000');
INSERT INTO `borrower` VALUES ('5', '65.00', '40000', '6个月', '到期付款', '44', '5', '35000');
INSERT INTO `borrower` VALUES ('6', '10.00', '50000', '24天', '到期付款', '234', '3', '45000');
INSERT INTO `borrower` VALUES ('7', '7.00', '60000', '12天', '到期付款带息', '123', '4', '40000');
INSERT INTO `borrower` VALUES ('8', '14.00', '40000', '18天', '到期付款', '231', '6', '30000');

-- ----------------------------
-- Table structure for borrowerinfo
-- ----------------------------
DROP TABLE IF EXISTS `borrowerinfo`;
CREATE TABLE `borrowerinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `birthday` bigint(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `success` int(11) DEFAULT NULL,
  `norepayment` int(11) DEFAULT NULL,
  `isverify` int(11) DEFAULT NULL,
  `bid` int(11) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `borrowmoney` int(11) DEFAULT NULL,
  `limit` varchar(255) DEFAULT NULL,
  `income` int(11) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  KEY `bid` (`bid`),
  CONSTRAINT `borrowerinfo_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `borrowerinfo_ibfk_2` FOREIGN KEY (`bid`) REFERENCES `borrower` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrowerinfo
-- ----------------------------
INSERT INTO `borrowerinfo` VALUES ('1', '1', '男', '1993', '广东肇庆', '1', '1', '1', '3', '15779787974', '30000', '6', '5000', '蔡永宁');
INSERT INTO `borrowerinfo` VALUES ('2', '2', '女', '199312', '珠海', '2', '2', '2', '5', '15779787978', '40000', '12', '7000', '岑烘燕');

-- ----------------------------
-- Table structure for borrowerinvest
-- ----------------------------
DROP TABLE IF EXISTS `borrowerinvest`;
CREATE TABLE `borrowerinvest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `starttime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bid` (`bid`),
  KEY `uid` (`uid`),
  CONSTRAINT `borrowerinvest_ibfk_1` FOREIGN KEY (`bid`) REFERENCES `borrower` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `borrowerinvest_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrowerinvest
-- ----------------------------

-- ----------------------------
-- Table structure for borrowplatform
-- ----------------------------
DROP TABLE IF EXISTS `borrowplatform`;
CREATE TABLE `borrowplatform` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `peoplenum` int(11) DEFAULT NULL,
  `annualrate` double(255,2) DEFAULT NULL,
  `imageurl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrowplatform
-- ----------------------------
INSERT INTO `borrowplatform` VALUES ('1', '嘉宝贷', '无需抵押，无需抵押材料齐全最快一天到款的，最高可借50万元', '432', '10.00', 'xin.png');
INSERT INTO `borrowplatform` VALUES ('2', '嘉房贷', '无需押房，无需担保，幸福生活随\"福\"来，最高可借50万元', '321', '6.87', 'room.png');
INSERT INTO `borrowplatform` VALUES ('3', '嘉车贷', '不押车，不押证，有车辆交强险保单即可借', '543', '8.90', 'car.png');
INSERT INTO `borrowplatform` VALUES ('4', '嘉英贷', '提供社保公积金信息即可申请，最高可以借50万，社保公积金信息', '654', '13.20', 'ying.png');
INSERT INTO `borrowplatform` VALUES ('5', '房易贷', '有一套或多套住宅的业主即可申请，额度高达500万元', '456', '12.43', 'yi.png');
INSERT INTO `borrowplatform` VALUES ('6', '优质持卡人', '可借款适用可借款适用本金保障计划本金保障计划', '235', '10.78', 'ka.png');

-- ----------------------------
-- Table structure for dayincome
-- ----------------------------
DROP TABLE IF EXISTS `dayincome`;
CREATE TABLE `dayincome` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `income` double NOT NULL,
  `time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dayincome
-- ----------------------------
INSERT INTO `dayincome` VALUES ('1', '0.25', null);

-- ----------------------------
-- Table structure for loan
-- ----------------------------
DROP TABLE IF EXISTS `loan`;
CREATE TABLE `loan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bpid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bpid` (`bpid`),
  CONSTRAINT `loan_ibfk_1` FOREIGN KEY (`bpid`) REFERENCES `borrowplatform` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of loan
-- ----------------------------

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `managename` varchar(255) DEFAULT NULL,
  `managepwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('1', 'cenhongyan', 'cen199312');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imageurl` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pid` (`pid`),
  CONSTRAINT `notice_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', 'index1.jpg', '1');
INSERT INTO `notice` VALUES ('2', 'index2.jpg', '2');
INSERT INTO `notice` VALUES ('3', 'index3.jpg', null);
INSERT INTO `notice` VALUES ('4', 'index4.jpg', null);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `annualrate` double(20,2) NOT NULL,
  `timelimit` varchar(20) NOT NULL,
  `investmoney` int(11) NOT NULL,
  `starttime` varchar(20) NOT NULL,
  `total` int(11) DEFAULT NULL,
  `startmoney` int(11) DEFAULT NULL,
  `peoplenum` int(11) NOT NULL,
  `introduce` varchar(2000) DEFAULT NULL,
  `receivedway` varchar(255) DEFAULT NULL,
  `tid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tid` (`tid`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`tid`) REFERENCES `type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '财神道', '新手必选', '15.50', '45天', '270000', '购买后当日起息', '300000', '100', '123', '财神大', '到期还本付息', '1');
INSERT INTO `product` VALUES ('2', '嘉利宝', '本息复投', '5.50', '2个月', '60000', '购买后当日起息', '60000', '100', '11', '收益最大化', '到期还本付息', '3');
INSERT INTO `product` VALUES ('3', '月月盈', '本金复投', '7.90', '2个月', '40000', '购买后当日起息', '50000', '100', '321', '你我贷互联网', '到期还本付息', '2');
INSERT INTO `product` VALUES ('4', '季季丰', '体验投资', '8.90', '1个月', '40000', '购买后当日起息', '40000', '100', '333', '快速汇报', '到期还本付息', '3');
INSERT INTO `product` VALUES ('5', '体验宝', '本金复投', '7.90', '12天', '37000', '购买后当日起息', '40000', '100', '666', '你我贷', '到期还本付息', '1');
INSERT INTO `product` VALUES ('6', '双季赢', '新手必选', '9.00', '3个月', '20000', '购买后当日起息', '50000', '100', '22', '财神大', '到期还本付息', '2');
INSERT INTO `product` VALUES ('7', '日月升', '灵活方便', '10.60', '24天', '30000', '购买后当日起息', '40000', '100', '231', '快速汇报', '到期还本付息', '3');
INSERT INTO `product` VALUES ('8', '你我贷', '体验投资', '7.00', '3个月', '30000', '购买后当日起息', '40000', '100', '23', '财神大', '到期还本付息', '3');
INSERT INTO `product` VALUES ('9', '宜有财', '本金复投', '3.00', '1个月', '35000', '购买后当日起息', '40000', '100', '32', '收益最大化', '到期还本付息', '3');

-- ----------------------------
-- Table structure for productinvest
-- ----------------------------
DROP TABLE IF EXISTS `productinvest`;
CREATE TABLE `productinvest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `starttime` bigint(20) DEFAULT NULL,
  `investmoney` double(255,10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pid` (`pid`),
  KEY `uid` (`uid`),
  CONSTRAINT `invest_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `productinvest_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of productinvest
-- ----------------------------
INSERT INTO `productinvest` VALUES ('5', '2', '1', '1', '1470791183589', '500.0000000000');
INSERT INTO `productinvest` VALUES ('6', '2', '1', '1', '1470791183542', '1000.0000000000');
INSERT INTO `productinvest` VALUES ('7', '2', '1', '1', '1470791183523', '4000.0000000000');
INSERT INTO `productinvest` VALUES ('11', '1', '1', '1', '1470639483000', '50000.0000000000');
INSERT INTO `productinvest` VALUES ('12', '1', '1', '1', '1470639483000', '40000.0000000000');
INSERT INTO `productinvest` VALUES ('13', '1', '1', '0', '1470639483000', '20000.0000000000');
INSERT INTO `productinvest` VALUES ('14', '5', '1', '0', '1470808594069', '100.0000000000');
INSERT INTO `productinvest` VALUES ('15', '1', '1', '0', '1470880028364', '100.0000000000');
INSERT INTO `productinvest` VALUES ('18', '3', '1', '0', '1470980495973', '100.0000000000');
INSERT INTO `productinvest` VALUES ('19', '5', '1', '0', '1470980974679', '3000.0000000000');
INSERT INTO `productinvest` VALUES ('20', '5', '1', '0', '1470981003888', '3000.0000000000');
INSERT INTO `productinvest` VALUES ('21', '1', '1', '0', '1470981379716', '100.0000000000');

-- ----------------------------
-- Table structure for transfer
-- ----------------------------
DROP TABLE IF EXISTS `transfer`;
CREATE TABLE `transfer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bid` (`bid`),
  KEY `uid` (`uid`),
  CONSTRAINT `transfer_ibfk_1` FOREIGN KEY (`bid`) REFERENCES `borrower` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `transfer_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of transfer
-- ----------------------------
INSERT INTO `transfer` VALUES ('1', '3', '1');
INSERT INTO `transfer` VALUES ('2', '4', '2');
INSERT INTO `transfer` VALUES ('3', '6', '2');
INSERT INTO `transfer` VALUES ('4', '7', '1');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('1', '新手专区');
INSERT INTO `type` VALUES ('2', '嘉道有财');
INSERT INTO `type` VALUES ('3', '体验专区');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `totalmoney` double(255,10) NOT NULL,
  `income` double(255,10) NOT NULL,
  `rechage` double(255,10) NOT NULL,
  `username` varchar(255) NOT NULL,
  `userinfo` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `gesture` varchar(255) DEFAULT NULL,
  `bid` int(11) DEFAULT NULL,
  `integral` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '50000.0000000000', '101.8900000000', '3000.0000000000', 'caiyongning', '中软', '157787686', '84446831@qq.com', '199312', '1', null, '1500');
INSERT INTO `user` VALUES ('2', '600000.0000000000', '60.0900000000', '4000.0000000000', '岑烘燕', '江西理工大学', '157783432', '1125190449@qq.com', '931126', '2', null, '2000');
