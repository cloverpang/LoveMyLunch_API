/*
Navicat MySQL Data Transfer

Source Server         : ourwaysvn.cloudapp.net
Source Server Version : 50537
Source Host           : ourwaysvn.cloudapp.net:3306
Source Database       : clover_crm

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2018-01-22 11:26:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `awf_admin`
-- ----------------------------
DROP TABLE IF EXISTS `awf_admin`;
CREATE TABLE `awf_admin` (
  `admin_id` varchar(32) NOT NULL,
  `admin_login` varchar(200) NOT NULL,
  `admin_password` varchar(200) NOT NULL,
  `admin_name` varchar(200) DEFAULT NULL,
  `frontend_permissions` text,
  `backend_permissions` text,
  `operationCenterCode` varchar(200) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of awf_admin
-- ----------------------------
INSERT INTO `awf_admin` VALUES ('101', 'clover', '12345678', '庞静波', '/default,/company,component_company_add,component_company_batch_delete,component_company_delete,component_company_update,/customer,component_customer_delete,component_customer_update,/product,component_dish_delete,component_dish_update,component_dish_add,/product/dish,/product/soup,/product/staplefood,/order,component_order_delete,component_order_update,/summary,/distributionForm,component_distributionForm_delete,component_distributionForm_generate,component_distributionForm_mark_all_arrived,component_distributionForm_mark_arrived,component_distributionForm_select_dirstributer,/distributer,component_distributer_delete,component_distributer_update,component_distributer_add,/dashboard,/setting,/adminUser,component_adminUser_delete,component_adminUser_updatePassword,component_adminUser_update_front_permission,component_adminUser_update_backend_permission,/log,/distribution', 'company_get_list,company_get_one,company_add,company_batch_delete,company_delete,company_update,customer_get_list,customer_get_one,customer_add,customer_delete,customer_update,dish_get_list,dish_get_one,dish_marknotuse,dish_delete,dish_update,dish_add,order_get_list,order_get_one,order_cancel,order_add,order_delete,order_update,summary,distributionForm_get_list,distributionForm_get_one,distributionForm_update,distributionForm_add,distributionForm_delete,distributionForm_generate,distributionForm_mark_all_arrived,distributionForm_mark_arrived,distributionForm_select_dirstributer,distributer_get_list,distributer_get_one,distributer_delete,distributer_update,distributer_add,dashboard_summary,dashboard_order_chart,dashboard_customer_chart,adminUser_getAll,adminUser_delete,adminUser_updatePassword,adminUser_update_front_permission,adminUser_update_backend_permission,operationLog_get_list,operationLog_delete,operationLog_batch_delete,operationLog_get_one', 'kexing', '2018-01-12 19:06:32');
INSERT INTO `awf_admin` VALUES ('102', 'admin', '12345678', '超级管理员', '/default,/company,/customer,component_company_add,component_company_batch_delete,component_company_delete,component_company_update,component_customer_update,component_customer_delete,/product,component_dish_delete,component_dish_update,component_dish_add,/product/dish,/product/soup,/product/staplefood,/order,component_order_delete,component_order_update,/summary,/distribution,/distributionForm,/distributer,component_distributionForm_delete,component_distributionForm_generate', 'company_get_list,company_get_one,customer_get_list,customer_get_one,customer_add,company_add,company_batch_delete,company_delete,company_update,customer_update,customer_delete,dish_get_list,dish_get_one,dish_marknotuse,dish_delete,dish_update,dish_add,order_get_list,order_get_one,order_cancel,order_add,order_delete,order_update,summary,distributionForm_get_list,distributionForm_get_one,distributionForm_update,distributionForm_add,distributer_get_list,distributer_get_one,distributionForm_delete,distributionForm_generate', 'kexing', '2018-01-12 19:33:38');
INSERT INTO `awf_admin` VALUES ('103', 'pjb', '12345678', 'jingbo', '/default,/company,component_company_add,component_company_batch_delete,component_company_delete,component_company_update,/customer,component_customer_delete,component_customer_update,/product,component_dish_delete,component_dish_update,component_dish_add,/product/dish,/product/soup,/product/staplefood,/order,component_order_delete,component_order_update,/summary,/distributionForm,component_distributionForm_delete,component_distributionForm_generate,component_distributionForm_mark_all_arrived,component_distributionForm_mark_arrived,component_distributionForm_select_dirstributer,/distributer,component_distributer_delete,component_distributer_update,component_distributer_add,/dashboard,/setting,/adminUser,component_adminUser_delete,component_adminUser_updatePassword,component_adminUser_update_front_permission,component_adminUser_update_backend_permission,/log,/distribution', 'company_get_list,company_get_one,company_add,company_batch_delete,company_delete,company_update,customer_get_list,customer_get_one,customer_add,customer_delete,customer_update,dish_get_list,dish_get_one,dish_marknotuse,dish_delete,dish_update,dish_add,order_get_list,order_get_one,order_cancel,order_add,order_delete,order_update,summary,distributionForm_get_list,distributionForm_get_one,distributionForm_update,distributionForm_add,distributionForm_delete,distributionForm_generate,distributionForm_mark_all_arrived,distributionForm_mark_arrived,distributionForm_select_dirstributer,distributer_get_list,distributer_get_one,distributer_delete,distributer_update,distributer_add,dashboard_summary,dashboard_order_chart,dashboard_customer_chart,adminUser_getAll,adminUser_delete,adminUser_updatePassword,adminUser_update_front_permission,adminUser_update_backend_permission,operationLog_get_list,operationLog_delete,operationLog_batch_delete,operationLog_get_one', 'luohu', '2018-01-20 10:23:25');
