/*
Navicat MySQL Data Transfer

Source Server         : RCS_mysql
Source Server Version : 50638
Source Host           : 139.159.225.115:13306
Source Database       : hedge_fund

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2018-01-12 19:05:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin_user`
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `admin_id` varchar(32) NOT NULL,
  `admin_login` varchar(200) NOT NULL,
  `admin_password` varchar(200) NOT NULL,
  `admin_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES ('101', 'clover', 'e10adc3949ba59abbe56e057f20f883e', '庞静波');
INSERT INTO `admin_user` VALUES ('102', 'dennis', '96e79218965eb72c92a549dd5a330112', '王京华');
INSERT INTO `admin_user` VALUES ('103', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员');
