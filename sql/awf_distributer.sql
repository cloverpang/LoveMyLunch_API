/*
Navicat MySQL Data Transfer

Source Server         : ourwaysvn.cloudapp.net
Source Server Version : 50537
Source Host           : ourwaysvn.cloudapp.net:3306
Source Database       : clover_crm

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2018-01-22 11:27:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `awf_distributer`
-- ----------------------------
DROP TABLE IF EXISTS `awf_distributer`;
CREATE TABLE `awf_distributer` (
  `distributerId` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `distributerName` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mobile` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `photoPath` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `operationCenterCode` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`distributerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of awf_distributer
-- ----------------------------
INSERT INTO `awf_distributer` VALUES ('1a337596fe994e0e873b30cf6ecd4c24', '庞静波', '13418854380', '/static/img/team16.jpg', '1', 'kexing', '2018-01-09 18:54:50');
INSERT INTO `awf_distributer` VALUES ('5e54d21529b94a7fa26eea45d88db1e1', 'clover', '12345678', '/static/img/team16.jpg', '0', 'kexing', '2018-01-09 18:07:09');
INSERT INTO `awf_distributer` VALUES ('ae91f8007692450d951840c133863c49', '哈哈哈', '13418854380', '/static/img/team16.jpg', '1', 'kexing', '2018-01-20 10:19:37');
