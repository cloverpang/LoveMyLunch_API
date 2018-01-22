/*
Navicat MySQL Data Transfer

Source Server         : ourwaysvn.cloudapp.net
Source Server Version : 50537
Source Host           : ourwaysvn.cloudapp.net:3306
Source Database       : clover_crm

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2018-01-22 11:26:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `awf_dish`
-- ----------------------------
DROP TABLE IF EXISTS `awf_dish`;
CREATE TABLE `awf_dish` (
  `dishId` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `dishName` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dishType` varchar(200) COLLATE utf8_unicode_ci DEFAULT '' COMMENT 'dish,soup,staplefood',
  `dishPrice` int(11) DEFAULT '1',
  `status` int(11) DEFAULT '0',
  `dishImageSmall` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dishImageMiddle` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dishImageLarge` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dishStyle` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `component` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pepper` int(11) DEFAULT '0',
  `operationCenterCode` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`dishId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of awf_dish
-- ----------------------------
INSERT INTO `awf_dish` VALUES ('1c2ae45e86da45f3941d09fd64322c0d', '蒜蓉青菜', 'dish', '3', '0', 'http://localhost:8000/static/img/team15.jpg', '', '', 'afdafd', 'fasdfasdf', '0', 'kexing', '2018-01-02 17:00:34');
INSERT INTO `awf_dish` VALUES ('1f4bc10549464464b53f09ad80c2281f', '红烧武昌鱼', 'dish', '7', '1', '/static/img/team16.jpg', '', '', 'afdafd', 'fasdfasdf', '0', 'kexing', '2018-01-02 17:00:43');
INSERT INTO `awf_dish` VALUES ('6e14392d848c49549c2026d0d7a5cb79', '青菜豆腐例汤', 'soup', '1', '0', 'http://localhost:8000/static/img/team15.jpg', '', '', 'afdafddasdsda', 'fasdfasdf', '1', 'kexing', '2018-01-02 17:00:54');
INSERT INTO `awf_dish` VALUES ('a32b39cd61924641a435ef5e5b8e5458', '红烧猪肉', 'dish', '10', '0', 'http://localhost:8000/static/img/team15.jpg', '', '', 'afdafddasdsda', 'fasdfasdf', '1', 'kexing', '2018-01-02 17:02:45');
INSERT INTO `awf_dish` VALUES ('e6329b824f504a0a8cf9ed3093d45be5', '白米饭', 'staplefood', '1', '0', 'http://localhost:8000/static/img/team15.jpg', '', '', 'afdafddasdsda', 'fasdfasdf', '0', 'kexing', '2018-01-10 10:00:38');
