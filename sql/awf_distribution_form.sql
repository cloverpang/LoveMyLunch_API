/*
Navicat MySQL Data Transfer

Source Server         : ourwaysvn.cloudapp.net
Source Server Version : 50537
Source Host           : ourwaysvn.cloudapp.net:3306
Source Database       : clover_crm

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2018-01-22 11:27:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `awf_distribution_form`
-- ----------------------------
DROP TABLE IF EXISTS `awf_distribution_form`;
CREATE TABLE `awf_distribution_form` (
  `distributionFormId` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `formNumber` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `companyId` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `companyName` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `companyAddress` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lastArriveTime` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `distributerId` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `distributerName` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orderIds` text COLLATE utf8_unicode_ci,
  `status` int(11) DEFAULT '0',
  `operationCenterCode` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`distributionFormId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of awf_distribution_form
-- ----------------------------
INSERT INTO `awf_distribution_form` VALUES ('72ecb36e71254478847f69010331df0e', 'A4_2018-01-03', '102', '艾智能哈哈sdsdfsdf成长型df ', '科兴科学园A3', '2018-01-20 10:20:23', 'ae91f8007692450d951840c133863c49', '哈哈哈', '0fdd737aee3247a3be9e45a48c92d75b,2121eb47625e4e09ad752e3cf90bcb10,19ff6b218bac4cecb4c7f6fd39c0009d', '1', 'kexing', '2018-01-19 15:27:38');
INSERT INTO `awf_distribution_form` VALUES ('73b971576bce4e099e839d0b16b829c0', 'A3_2018-01-03', '101', '实打实地方', '科兴科学园A3', null, null, null, '4248b74b01e64a1e8ae8a1880dfb09b6,68c1ef758bb8409f860350244c9ed0cf,7c8a6fd07c0746ed860c9811f8c2cf74,a8955cf1bb6342a39003e4c2d88dfd18,c6fd2cbb53024429892963a382dbec21,388943453fa240b0a9dfc4859aa9e8dd,e24334a6467d4239ac48fd4af9517105,e2ba73cca8f3429d91157cfb3c7c5a58,69795246408d40ea843654b10ab4315d,b3f02be71c6f48a4a858fdbdc1bfe44c,6fe2ea68e474480296cb8aa7c519567c,396ac1f8713241a4a82a98a463d6523c,b4b46b5d62ce4d2db6e7d3662e1fe20b,4d5043226355493881dfd4e93e42a125', '0', 'kexing', '2018-01-19 15:27:38');
