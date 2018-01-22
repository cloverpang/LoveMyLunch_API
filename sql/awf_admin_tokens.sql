/*
Navicat MySQL Data Transfer

Source Server         : ourwaysvn.cloudapp.net
Source Server Version : 50537
Source Host           : ourwaysvn.cloudapp.net:3306
Source Database       : clover_crm

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2018-01-22 11:26:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `awf_admin_tokens`
-- ----------------------------
DROP TABLE IF EXISTS `awf_admin_tokens`;
CREATE TABLE `awf_admin_tokens` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `token` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `expiry` datetime DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of awf_admin_tokens
-- ----------------------------
