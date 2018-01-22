/*
Navicat MySQL Data Transfer

Source Server         : ourwaysvn.cloudapp.net
Source Server Version : 50537
Source Host           : ourwaysvn.cloudapp.net:3306
Source Database       : clover_crm

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2018-01-22 11:26:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `awf_customer`
-- ----------------------------
DROP TABLE IF EXISTS `awf_customer`;
CREATE TABLE `awf_customer` (
  `customerId` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `customerLogin` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `customerPassword` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `customerName` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `companyId` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `companyName` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `weChatAccount` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mobileNumber` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `customerScore` int(11) DEFAULT '0',
  `customerType` int(11) DEFAULT '0',
  `status` int(11) DEFAULT '0',
  `operationCenterCode` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`customerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of awf_customer
-- ----------------------------
INSERT INTO `awf_customer` VALUES ('201', 'pangjingbo', '12345', '庞静波', '101', 'AI', 'asasd', 'dsdfsdf', '0', '1', '1', 'kexing', '2018-01-10 14:17:33');
INSERT INTO `awf_customer` VALUES ('805b167cdf6249bc8c6309b2c2d3562b', 'Clover', null, 'Clover', '102', 'AI', null, null, null, null, '0', 'kexing', '2017-12-25 11:56:30');
