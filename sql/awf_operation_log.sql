/*
Navicat MySQL Data Transfer

Source Server         : ourwaysvn.cloudapp.net
Source Server Version : 50537
Source Host           : ourwaysvn.cloudapp.net:3306
Source Database       : clover_crm

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2018-01-22 11:27:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `awf_operation_log`
-- ----------------------------
DROP TABLE IF EXISTS `awf_operation_log`;
CREATE TABLE `awf_operation_log` (
  `operationId` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `operationUrl` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `operationName` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `requestParameters` text CHARACTER SET utf8,
  `operationReturn` text CHARACTER SET utf8,
  `operationException` text CHARACTER SET utf8,
  `operationUser` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `operationToken` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `runTime` int(11) DEFAULT NULL,
  `operationCenterCode` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`operationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of awf_operation_log
-- ----------------------------
