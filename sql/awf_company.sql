/*
Navicat MySQL Data Transfer

Source Server         : ourwaysvn.cloudapp.net
Source Server Version : 50537
Source Host           : ourwaysvn.cloudapp.net:3306
Source Database       : clover_crm

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2018-01-22 11:26:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `awf_company`
-- ----------------------------
DROP TABLE IF EXISTS `awf_company`;
CREATE TABLE `awf_company` (
  `companyId` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `companyName` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `companyCode` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `operationCenterCode` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `companyAddress` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `companyLogoPath` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(4) DEFAULT '0' COMMENT '0 正常 1 已注销不活动的',
  `joinTime` datetime DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`companyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of awf_company
-- ----------------------------
INSERT INTO `awf_company` VALUES ('086e2730e92d42f9b35547a9779f1864', '东方股份公司的风格', 'A1', 'kexing', '啊撒大声地', '', '0', '2017-12-20 14:30:23', '2017-12-22 15:14:02');
INSERT INTO `awf_company` VALUES ('101', '实打实地方', 'A3', 'kexing', '科兴科学园A3', '', '0', '2017-12-20 14:30:23', '2017-12-20 14:30:23');
INSERT INTO `awf_company` VALUES ('102', '艾智能哈哈sdsdfsdf成长型df ', 'A4', 'kexing', '科兴科学园A3', '', '1', '2017-12-20 14:30:23', '2017-12-20 14:30:23');
INSERT INTO `awf_company` VALUES ('18239e14b1e843e38318528e649ef6f6', '1212121223', 'A6', 'kexing', '科兴科学园A3', '', '0', '2017-12-20 14:30:23', '2017-12-20 14:30:23');
INSERT INTO `awf_company` VALUES ('1f797cb276244670a04685e8fd761d59', '艾智能45345xxxx', 'A7', 'kexing', '科兴科学园A3', '', '0', '2017-12-20 14:30:23', '2017-12-20 14:30:23');
INSERT INTO `awf_company` VALUES ('220d593d4b8a43d8b67a2e6637d5d3d6', 'dfsdf sdfsdf', 'A8', 'kexing', '科兴科学园A3', '', '0', '2017-12-20 14:30:23', '2017-12-20 14:30:23');
INSERT INTO `awf_company` VALUES ('25583f9a48bb481e8597fa982717a20c', 'asdfasdfasdf', 'dfasdf', 'kexing', 'sdgsdf', '', '0', '2018-01-20 11:44:39', '2018-01-20 11:44:39');
INSERT INTO `awf_company` VALUES ('29a51ddabb7644d9a5c40c8419090f1a', '艾智能6666啊撒大声地', 'IZneng', 'kexing', '科兴科学园A3', '', '0', '2017-12-20 14:30:23', '2017-12-20 14:30:23');
INSERT INTO `awf_company` VALUES ('39f3400fe9164283b2c590298384f037', 'cesdsdf', 'dfdf', 'luohu', 'sdfsdf', '', '0', '2018-01-20 10:24:22', '2018-01-20 10:24:22');
INSERT INTO `awf_company` VALUES ('3ba21acda4e74b2b982bd83e7304f390', '说到底沙发斯蒂芬撒的', 'A9', 'kexing', '科兴科学园A3', null, '1', '2017-12-20 14:30:23', '2017-12-20 14:30:23');
INSERT INTO `awf_company` VALUES ('447bea5e70ae40e7bf1298f9cc17ac33', '1212121223', 'A10', 'kexing', '科兴科学园A3', '', '0', '2017-12-20 14:30:23', '2017-12-20 14:30:23');
INSERT INTO `awf_company` VALUES ('498fee9d000e47bd8d9455413c6e96ed', '1212121223', 'A11', 'kexing', '科兴科学园A3', '', '0', '2017-12-20 14:30:23', '2017-12-20 14:30:23');
INSERT INTO `awf_company` VALUES ('78fd017166ef4b18bea73aa8b7c2624b', '亚洲检测2', 'AsiaInspection2', 'kexing', '的沙发斯蒂芬', '', '0', '2018-01-10 10:48:03', '2018-01-10 10:48:03');
INSERT INTO `awf_company` VALUES ('7ff43a05d8c44a968d343d34ff29b4d2', '阿萨斯大多sasdsddsf', 'dsadfASDAS', 'kexing', 'adfadfadfdfsss', '', '0', '2018-01-20 11:35:28', '2018-01-20 11:35:28');
INSERT INTO `awf_company` VALUES ('8e82cf72fd844f0881a29636cf67d5e0', '实打实地方水电费', 'A13', 'kexing', null, '', '0', '2017-12-20 14:30:23', '2017-12-22 15:06:39');
INSERT INTO `awf_company` VALUES ('a48335b443a84d44b6cfadccea27020e', '亚洲检测', 'AsiaInspection', 'kexing', '的沙发斯蒂芬', '', '0', '2018-01-10 10:26:01', '2018-01-10 10:26:01');
INSERT INTO `awf_company` VALUES ('a86f081b144f4f5587be3a90bcedd6c9', '实打实地方啊是撒打发斯蒂芬', 'A14', 'kexing', null, '', '0', '2017-12-20 14:30:23', '2017-12-22 15:05:54');
INSERT INTO `awf_company` VALUES ('b4fcdfbde00a4cbcb15c67534b85275a', '东方股份公司的风格dff ', 'A16', 'kexing', '啊撒大声地', '', '0', '2017-12-20 14:30:23', '2017-12-22 15:17:08');
INSERT INTO `awf_company` VALUES ('cdcf5fdd65ba4143bae92b268d9efc6c', '的风格的风格是的发个', 'A18', 'kexing', 'dsfgsdgdsfg', '', '0', '2017-12-28 17:30:56', '2017-12-28 17:30:56');
INSERT INTO `awf_company` VALUES ('d4d645753a5f4adeb664a6bf94a3f234', 'asdfasdf ', 'asdfasdf', 'kexing', 'adsfa', '', '0', '2018-01-20 11:35:08', '2018-01-20 11:35:08');
INSERT INTO `awf_company` VALUES ('eb28b3d87ab44f9da30d57021876b72d', '发生的撒打发斯蒂芬但是', 'A20', 'kexing', null, '', '0', '2017-12-20 14:30:23', '2017-12-22 15:06:47');
INSERT INTO `awf_company` VALUES ('f3f29e617dee48bf983e50e703aada77', '阿萨斯大多sasdsddsf', 'dsadf', 'kexing', 'adfadfadfdfsss', '', '0', '2018-01-20 09:56:39', '2018-01-20 09:56:39');
INSERT INTO `awf_company` VALUES ('fa3026dd5daf4216a75a9d4ac5e22f90', '阿萨斯大多sasdsddsfSDFASDASD', 'dsadfASDASASD', 'kexing', 'adfadfadfdfsss', '', '0', '2018-01-20 11:35:39', '2018-01-20 11:35:39');
