/*
Navicat MySQL Data Transfer

Source Server         : ourwaysvn.cloudapp.net
Source Server Version : 50537
Source Host           : ourwaysvn.cloudapp.net:3306
Source Database       : clover_crm

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2018-01-22 11:27:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `awf_lunchorder`
-- ----------------------------
DROP TABLE IF EXISTS `awf_lunchorder`;
CREATE TABLE `awf_lunchorder` (
  `orderId` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `orderNumber` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `distributNumber` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bookTime` datetime DEFAULT NULL,
  `lunchTime` datetime DEFAULT NULL,
  `customerName` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `customerMobile` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `customerId` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `companyId` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `content` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dishIds` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remark` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `originPrice` decimal(20,2) DEFAULT NULL,
  `discoutPrice` decimal(20,2) DEFAULT NULL,
  `realPrice` decimal(20,2) DEFAULT NULL,
  `star` int(11) DEFAULT '0',
  `appraise` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `orderStatus` int(11) DEFAULT '0',
  `paymentStatus` int(11) DEFAULT '0',
  `operationCenterCode` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of awf_lunchorder
-- ----------------------------
INSERT INTO `awf_lunchorder` VALUES ('0fdd737aee3247a3be9e45a48c92d75b', 'R-00001', 'xxx', '2018-01-03 15:12:06', '2018-01-03 15:12:06', 'Clover', '123', '101', '102', '红烧猪肉,红烧武昌鱼,蒜蓉青菜,白米饭,青菜豆腐例汤', '1f4bc10549464464b53f09ad80c2281f,6e14392d848c49549c2026d0d7a5cb79,a32b39cd61924641a435ef5e5b8e5458,e6329b824f504a0a8cf9ed3093d45be5', 'ADSFASDFASDF', '20.00', '0.00', '20.00', '0', 'string', '0', '0', 'kexing', '2018-01-04 15:12:06');
INSERT INTO `awf_lunchorder` VALUES ('130e89eadd4a400cbc7b1bcb1676dc13', 'R-00002', 'xxx', '2018-01-09 16:12:11', '2018-01-03 15:12:11', 'Clover', '123', '101', '102', '红烧猪肉,红烧武昌鱼,蒜蓉青菜,白米饭,青菜豆腐例汤', '1c2ae45e86da45f3941d09fd64322c0d,1f4bc10549464464b53f09ad80c2281f,6e14392d848c49549c2026d0d7a5cb79,a32b39cd61924641a435ef5e5b8e5458,e6329b824f504a0a8cf9ed3093d45be5', 'ADSFASDFASDF', '20.00', '0.00', '20.00', '0', 'string', '0', '1', 'kexing', '2018-01-05 15:12:11');
INSERT INTO `awf_lunchorder` VALUES ('2121eb47625e4e09ad752e3cf90bcb10', 'R-00004', 'xxx', '2018-01-03 15:12:06', '2018-01-03 15:12:06', 'Clover', '123', '201', '102', '红烧猪肉,红烧武昌鱼,蒜蓉青菜,白米饭,青菜豆腐例汤', '1c2ae45e86da45f3941d09fd64322c0d,1f4bc10549464464b53f09ad80c2281f,6e14392d848c49549c2026d0d7a5cb79,a32b39cd61924641a435ef5e5b8e5458,e6329b824f504a0a8cf9ed3093d45be5', 'ADSFASDFASDF', '20.00', '0.00', '20.00', '0', 'string', '1', '0', 'kexing', '2018-01-09 15:12:06');
INSERT INTO `awf_lunchorder` VALUES ('388943453fa240b0a9dfc4859aa9e8dd', 'R-00005', 'xxx', '2018-01-03 15:12:08', '2018-01-03 15:12:08', 'Clover', '123', '805b167cdf6249bc8c6309b2c2d3562b', '101', '红烧猪肉,红烧武昌鱼,蒜蓉青菜,白米饭,青菜豆腐例汤', '1c2ae45e86da45f3941d09fd64322c0d,1f4bc10549464464b53f09ad80c2281f,6e14392d848c49549c2026d0d7a5cb79,a32b39cd61924641a435ef5e5b8e5458,e6329b824f504a0a8cf9ed3093d45be5', 'ADSFASDFASDF', '20.00', '0.00', '20.00', '0', 'string', '0', '0', 'kexing', '2018-01-03 15:12:08');
INSERT INTO `awf_lunchorder` VALUES ('396ac1f8713241a4a82a98a463d6523c', 'R-00006', 'xxx', '2018-01-03 15:12:11', '2018-01-03 15:12:11', 'Clover', '123', '805b167cdf6249bc8c6309b2c2d3562b', '101', '红烧猪肉,红烧武昌鱼,蒜蓉青菜,白米饭,青菜豆腐例汤', '1c2ae45e86da45f3941d09fd64322c0d,1f4bc10549464464b53f09ad80c2281f,6e14392d848c49549c2026d0d7a5cb79,a32b39cd61924641a435ef5e5b8e5458,e6329b824f504a0a8cf9ed3093d45be5', 'ADSFASDFASDF', '20.00', '0.00', '20.00', '0', 'string', '0', '0', 'kexing', '2018-01-03 15:12:11');
INSERT INTO `awf_lunchorder` VALUES ('4248b74b01e64a1e8ae8a1880dfb09b6', 'R-00007', 'xxx', '2018-01-03 15:11:25', '2018-01-03 15:11:25', 'Clover', '123', '805b167cdf6249bc8c6309b2c2d3562b', '101', '红烧猪肉,红烧武昌鱼,蒜蓉青菜,白米饭,青菜豆腐例汤', '1c2ae45e86da45f3941d09fd64322c0d,1f4bc10549464464b53f09ad80c2281f,6e14392d848c49549c2026d0d7a5cb79,a32b39cd61924641a435ef5e5b8e5458,e6329b824f504a0a8cf9ed3093d45be5', 'ADSFASDFASDF', '20.00', '0.00', '20.00', '0', 'string', '0', '1', 'kexing', '2018-01-03 15:11:25');
INSERT INTO `awf_lunchorder` VALUES ('4d5043226355493881dfd4e93e42a125', 'R-00008', 'xxx', '2018-01-03 15:12:13', '2018-01-03 15:12:13', 'Clover', '123', '805b167cdf6249bc8c6309b2c2d3562b', '101', '红烧猪肉,红烧武昌鱼,蒜蓉青菜,白米饭,青菜豆腐例汤', '1c2ae45e86da45f3941d09fd64322c0d,1f4bc10549464464b53f09ad80c2281f,6e14392d848c49549c2026d0d7a5cb79,a32b39cd61924641a435ef5e5b8e5458,e6329b824f504a0a8cf9ed3093d45be5', 'ADSFASDFASDF', '20.00', '0.00', '20.00', '0', 'string', '0', '0', 'kexing', '2018-01-03 15:12:13');
INSERT INTO `awf_lunchorder` VALUES ('68c1ef758bb8409f860350244c9ed0cf', 'R-00009', 'xxx', '2018-01-03 15:12:04', '2018-01-03 15:12:04', 'Clover', '123', '805b167cdf6249bc8c6309b2c2d3562b', '101', '红烧猪肉,红烧武昌鱼,蒜蓉青菜,白米饭,青菜豆腐例汤', '1c2ae45e86da45f3941d09fd64322c0d,1f4bc10549464464b53f09ad80c2281f,6e14392d848c49549c2026d0d7a5cb79,a32b39cd61924641a435ef5e5b8e5458,e6329b824f504a0a8cf9ed3093d45be5', 'ADSFASDFASDF', '20.00', '0.00', '20.00', '0', 'string', '0', '0', 'kexing', '2018-01-03 15:12:04');
INSERT INTO `awf_lunchorder` VALUES ('69795246408d40ea843654b10ab4315d', 'R-00010', 'xxx', '2018-01-03 15:12:09', '2018-01-03 15:12:09', 'Clover', '123', '805b167cdf6249bc8c6309b2c2d3562b', '101', '红烧猪肉,红烧武昌鱼,蒜蓉青菜,白米饭,青菜豆腐例汤', '1c2ae45e86da45f3941d09fd64322c0d,1f4bc10549464464b53f09ad80c2281f,6e14392d848c49549c2026d0d7a5cb79,a32b39cd61924641a435ef5e5b8e5458,e6329b824f504a0a8cf9ed3093d45be5', 'ADSFASDFASDF', '20.00', '0.00', '20.00', '0', 'string', '0', '0', 'kexing', '2018-01-03 15:12:09');
INSERT INTO `awf_lunchorder` VALUES ('6fe2ea68e474480296cb8aa7c519567c', 'R-000011', 'xxx', '2018-01-03 15:12:10', '2018-01-03 15:12:10', 'Clover', '123', '805b167cdf6249bc8c6309b2c2d3562b', '101', '红烧猪肉,红烧武昌鱼,蒜蓉青菜,白米饭,青菜豆腐例汤', '1c2ae45e86da45f3941d09fd64322c0d,1f4bc10549464464b53f09ad80c2281f,6e14392d848c49549c2026d0d7a5cb79,a32b39cd61924641a435ef5e5b8e5458,e6329b824f504a0a8cf9ed3093d45be5', 'ADSFASDFASDF', '20.00', '0.00', '20.00', '0', 'string', '0', '2', 'kexing', '2018-01-03 15:12:10');
INSERT INTO `awf_lunchorder` VALUES ('7c8a6fd07c0746ed860c9811f8c2cf74', 'R-000012', 'xxx', '2018-01-03 15:12:05', '2018-01-03 15:12:05', 'Clover', '123', '805b167cdf6249bc8c6309b2c2d3562b', '101', '红烧猪肉,红烧武昌鱼,蒜蓉青菜,白米饭,青菜豆腐例汤', '1c2ae45e86da45f3941d09fd64322c0d,1f4bc10549464464b53f09ad80c2281f,6e14392d848c49549c2026d0d7a5cb79,a32b39cd61924641a435ef5e5b8e5458,e6329b824f504a0a8cf9ed3093d45be5', 'ADSFASDFASDF', '20.00', '0.00', '20.00', '0', 'string', '0', '0', 'kexing', '2018-01-03 15:12:05');
INSERT INTO `awf_lunchorder` VALUES ('a8955cf1bb6342a39003e4c2d88dfd18', 'R-000013', 'xxx', '2018-01-03 15:12:07', '2018-01-03 15:12:07', 'Clover', '123', '805b167cdf6249bc8c6309b2c2d3562b', '101', '红烧猪肉,红烧武昌鱼,蒜蓉青菜,白米饭,青菜豆腐例汤', '1c2ae45e86da45f3941d09fd64322c0d,1f4bc10549464464b53f09ad80c2281f,6e14392d848c49549c2026d0d7a5cb79,a32b39cd61924641a435ef5e5b8e5458,e6329b824f504a0a8cf9ed3093d45be5', 'ADSFASDFASDF', '20.00', '0.00', '20.00', '0', 'string', '0', '0', 'kexing', '2018-01-03 15:12:07');
INSERT INTO `awf_lunchorder` VALUES ('b3f02be71c6f48a4a858fdbdc1bfe44c', 'R-000014', 'xxx', '2018-01-03 15:12:09', '2018-01-03 15:12:09', 'Clover', '123', '805b167cdf6249bc8c6309b2c2d3562b', '101', '红烧猪肉,红烧武昌鱼,蒜蓉青菜,白米饭,青菜豆腐例汤', '1c2ae45e86da45f3941d09fd64322c0d,1f4bc10549464464b53f09ad80c2281f,6e14392d848c49549c2026d0d7a5cb79,a32b39cd61924641a435ef5e5b8e5458,e6329b824f504a0a8cf9ed3093d45be5', 'ADSFASDFASDF', '20.00', '0.00', '20.00', '0', 'string', '0', '0', 'kexing', '2018-01-03 15:12:09');
INSERT INTO `awf_lunchorder` VALUES ('b4b46b5d62ce4d2db6e7d3662e1fe20b', 'R-000015', 'xxx', '2018-01-03 15:12:12', '2018-01-03 15:12:12', 'Clover', '123', '805b167cdf6249bc8c6309b2c2d3562b', '101', '红烧猪肉,红烧武昌鱼,蒜蓉青菜,白米饭,青菜豆腐例汤', '1c2ae45e86da45f3941d09fd64322c0d,1f4bc10549464464b53f09ad80c2281f,6e14392d848c49549c2026d0d7a5cb79,a32b39cd61924641a435ef5e5b8e5458,e6329b824f504a0a8cf9ed3093d45be5', 'ADSFASDFASDF', '20.00', '0.00', '20.00', '0', 'string', '0', '0', 'kexing', '2018-01-03 15:12:12');
INSERT INTO `awf_lunchorder` VALUES ('c6fd2cbb53024429892963a382dbec21', 'R-000017', 'xxx', '2018-01-03 15:12:07', '2018-01-03 15:12:07', 'Clover', '123', '805b167cdf6249bc8c6309b2c2d3562b', '101', '红烧猪肉,红烧武昌鱼,蒜蓉青菜,白米饭,青菜豆腐例汤', '1c2ae45e86da45f3941d09fd64322c0d,1f4bc10549464464b53f09ad80c2281f,6e14392d848c49549c2026d0d7a5cb79,a32b39cd61924641a435ef5e5b8e5458,e6329b824f504a0a8cf9ed3093d45be5', 'ADSFASDFASDF', '20.00', '0.00', '20.00', '0', 'string', '0', '0', 'kexing', '2018-01-03 15:12:07');
INSERT INTO `awf_lunchorder` VALUES ('e24334a6467d4239ac48fd4af9517105', 'R-000018', 'xxx', '2018-01-03 15:12:08', '2018-01-03 15:12:08', 'Clover', '123', '805b167cdf6249bc8c6309b2c2d3562b', '101', '红烧猪肉,红烧武昌鱼,蒜蓉青菜,白米饭,青菜豆腐例汤', '1c2ae45e86da45f3941d09fd64322c0d,1f4bc10549464464b53f09ad80c2281f,6e14392d848c49549c2026d0d7a5cb79,a32b39cd61924641a435ef5e5b8e5458,e6329b824f504a0a8cf9ed3093d45be5', 'ADSFASDFASDF', '20.00', '0.00', '20.00', '0', 'string', '0', '0', 'kexing', '2018-01-03 15:12:08');
INSERT INTO `awf_lunchorder` VALUES ('e2ba73cca8f3429d91157cfb3c7c5a58', 'R-000019', 'xxx', '2018-01-03 15:12:08', '2018-01-03 15:12:08', 'Clover', '123', '805b167cdf6249bc8c6309b2c2d3562b', '101', '红烧猪肉,红烧武昌鱼,蒜蓉青菜,白米饭,青菜豆腐例汤', '1c2ae45e86da45f3941d09fd64322c0d,1f4bc10549464464b53f09ad80c2281f,6e14392d848c49549c2026d0d7a5cb79,a32b39cd61924641a435ef5e5b8e5458,e6329b824f504a0a8cf9ed3093d45be5', 'ADSFASDFASDF', '20.00', '0.00', '20.00', '0', 'string', '0', '0', 'kexing', '2018-01-03 15:12:08');
