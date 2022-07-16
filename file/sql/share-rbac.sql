/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : share-rbac

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 16/07/2022 23:44:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for action
-- ----------------------------
DROP TABLE IF EXISTS `action`;
CREATE TABLE `action` (
  `action_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action_name` varchar(128) NOT NULL,
  `action_type` tinyint(2) NOT NULL COMMENT '可以是匹配或者是单个，单个是全路径，匹配是书写url前缀或者url后缀',
  `action_url` varchar(512) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  `caption` varchar(128) DEFAULT NULL,
  `disabled` tinyint(2) DEFAULT '0' COMMENT '禁用 0 正常 1 禁用',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `created_person` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) DEFAULT NULL COMMENT '更新人',
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`action_id`) USING BTREE,
  UNIQUE KEY `menuid` (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='请求地址表';

-- ----------------------------
-- Records of action
-- ----------------------------
BEGIN;
INSERT INTO `action` (`action_id`, `action_name`, `action_type`, `action_url`, `menu_id`, `caption`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (1, '测试', 2, '5656', 0, '666666', 1, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for action_operate_power
-- ----------------------------
DROP TABLE IF EXISTS `action_operate_power`;
CREATE TABLE `action_operate_power` (
  `action_power_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action_id` bigint(20) NOT NULL,
  `operate_power_id` bigint(20) NOT NULL,
  PRIMARY KEY (`action_power_id`) USING BTREE,
  KEY `operate_power_id` (`operate_power_id`) USING BTREE,
  KEY `action_id` (`action_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='请求权限关系表\r\n';

-- ----------------------------
-- Records of action_operate_power
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for an_menu
-- ----------------------------
DROP TABLE IF EXISTS `an_menu`;
CREATE TABLE `an_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(128) NOT NULL COMMENT '菜单名称',
  `menu_code` varchar(64) NOT NULL COMMENT '菜单编码',
  `menu_url` varchar(512) DEFAULT NULL COMMENT '菜单地址',
  `component` varchar(255) NOT NULL,
  `redirect` varchar(255) DEFAULT NULL,
  `menu_type` tinyint(2) NOT NULL COMMENT '菜单类型',
  `menu_parent_code` varchar(64) NOT NULL COMMENT '父菜单id',
  `menu_icon` varchar(512) DEFAULT NULL COMMENT '菜单图标',
  `page_url` varchar(512) DEFAULT NULL,
  `app_system_id` bigint(20) DEFAULT NULL COMMENT '系统id',
  `order_code` int(2) DEFAULT NULL,
  `hidden` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0显示1隐藏',
  `disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除1删除0未删除',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `created_person` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) DEFAULT NULL COMMENT '更新人',
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表\r\n';

-- ----------------------------
-- Records of an_menu
-- ----------------------------
BEGIN;
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (2, '系统管理', 'm_sys', '/system', '#', '/system/menu', 1, 'root', 'el-icon-s-tools', NULL, 1, 2, 0, 0, '2020-09-06 20:05:30', '2020-09-20 04:30:48', 'god', '10000', NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (4, '表单', 'form', '/form343', '/system/form/index', '', 2, 'm_sys', 'el-icon-s-grid', NULL, 1, 2, 0, 0, '2020-09-10 00:03:25', '2021-08-17 12:40:24', 'god', '10000', NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (5, '人事管理', 'm_hr', '/hr', '#', '', 1, 'root', 'el-icon-user', NULL, 1, 1, 0, 0, '2020-09-17 22:50:09', '2021-08-19 15:09:32', 'god', '10000', NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (8, '菜单管理', 'm_menu', '/menu', '/system/menu/index', NULL, 2, 'm_sys', 'el-icon-s-unfold', NULL, 1, 1, 0, 0, '2020-09-17 22:50:06', '2022-07-15 13:38:16', 'god', '10000', NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (18, '字典管理', 'm_dict_manage', '/dict/type/manage', '/system/dict/index', '', 2, 'm_sys', 'el-icon-notebook-2', NULL, 1, 1, 0, 0, '2020-09-18 15:40:25', '2021-08-21 15:39:53', '10000', '10000', NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (20, '角色管理', 'm_hr_role', '/organ/role', '/hr/role/index', '', 2, 'm_hr', 'el-icon-medal-1', NULL, 1, 3, 0, 0, '2020-09-19 06:20:01', '2021-08-17 12:49:24', '10000', '10000', NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (22, '同道', 'm_line', '/line', '#', '', 1, 'root', 'el-icon-chat-dot-round', NULL, 1, 3, 0, 0, NULL, NULL, '10000', NULL, NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (23, '聊天', 'm_line_chat', '/line/chat', '/line/chat/index', '', 2, 'm_line', 'el-icon-chat-line-round', NULL, 1, 1, 0, 0, NULL, NULL, '10000', NULL, NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (25, '组织管理', 'm_hr_organ', '/hr/organ', '/hr/organ/index', NULL, 2, 'm_hr', 'el-icon-office-building', NULL, 1, 1, 0, 0, '2021-08-16 16:08:27', '2022-07-15 22:41:20', '10000', '10000', NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (26, '部门管理', 'm_hr_dept', '/hr/dept', '/hr/dept/index', NULL, 2, 'm_hr', 'el-icon-mobile', NULL, 1, 2, 0, 0, '2021-08-16 16:12:03', '2022-07-16 15:16:39', '10000', '99999', NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (27, '用户管理', 'm_hr_user', '/hr/user', '/hr/user/index', NULL, 2, 'm_hr', 'el-icon-user', NULL, 1, 5, 0, 0, '2021-08-16 16:12:37', '2021-08-17 12:46:01', '10000', '10000', NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (28, '标记管理', 'm_mark', '/mark', '#', NULL, 1, 'root', 'el-icon-help', NULL, 1, 3, 0, 0, '2021-08-16 16:15:10', NULL, '10000', NULL, NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (29, '标签管理', 'm_mark_label', '/mark/label', '#', '/mark/label/index', 2, 'm_mark', 'el-icon-collection-tag', NULL, 1, 1, 0, 0, '2021-08-17 12:33:36', NULL, '10000', NULL, NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (30, '外部链接', 'external', '', '#', 'https://panjiachen.github.io/vue-element-admin-site/#/', 1, 'root', 'el-icon-connection', NULL, 1, 9, 0, 0, '2021-08-17 12:42:08', NULL, '10000', NULL, NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (31, '岗位管理', 'm_hr_post', '/hr/post', '/hr/post/index', 'http://mirror.aarnet.edu.au/pub/TED-talks/911Mothers_2010W-480p.mp4', 2, 'm_hr', 'el-icon-table-lamp', NULL, 1, 4, 0, 0, '2021-08-17 12:47:40', '2022-06-14 17:01:23', '10000', '10000', NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (32, '新增', 'hr:role:add', NULL, '#', NULL, 3, 'm_role', NULL, NULL, 1, 1, 0, 0, '2021-08-19 14:29:49', NULL, '10000', NULL, NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (33, '查看', 'hr:role:see', NULL, '#', NULL, 3, 'm_role', NULL, NULL, 1, 2, 0, 0, '2021-08-19 14:30:15', NULL, '10000', NULL, NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (34, '编辑', 'hr:role:update', NULL, '#', NULL, 3, 'm_role', NULL, NULL, 1, 3, 0, 0, '2021-08-19 14:30:49', NULL, '10000', NULL, NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (35, '删除', 'hr:role:delete', NULL, '#', NULL, 3, 'm_role', NULL, NULL, 1, 4, 0, 0, '2021-08-19 14:31:08', NULL, '10000', NULL, NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (36, '新增', 'dict:type:add', '', '#', NULL, 3, 'm_dict_manage', NULL, NULL, 1, 1, 0, 0, '2021-08-22 08:45:59', '2021-08-23 15:12:27', '10000', '10000', NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (37, '更新', 'dict:type:update', NULL, '#', NULL, 3, 'm_dict_manage', NULL, NULL, 1, NULL, 0, 0, '2021-08-22 08:46:32', NULL, '10000', NULL, NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (38, '删除', 'dict:data:delete', NULL, '#', NULL, 3, 'm_dict_manage', NULL, NULL, 1, NULL, 0, 0, '2021-08-22 08:46:47', '2021-08-22 08:47:00', '10000', '10000', NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (39, '查看', 'dict:data:see', NULL, '#', NULL, 3, 'm_dict_manage', NULL, NULL, 1, NULL, 0, 0, '2021-08-22 08:47:14', NULL, '10000', NULL, NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (40, '数据表格数据', 'dict:data:table', NULL, '#', NULL, 3, 'm_dict_manage', NULL, NULL, 1, NULL, 0, 0, '2021-08-22 08:47:56', NULL, '10000', NULL, NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (41, '新增', 'system:menu:add', NULL, '#', NULL, 3, 'm_menu', NULL, NULL, 1, NULL, 0, 0, '2021-08-22 16:14:13', NULL, '10000', NULL, NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (42, '修改', 'system:menu:update', NULL, '#', NULL, 3, 'm_menu', NULL, NULL, 1, NULL, 0, 0, '2021-08-22 16:14:32', NULL, '10000', NULL, NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (43, '查看', 'organ:role:see', NULL, '#', NULL, 3, 'm_hr_role', NULL, NULL, 1, NULL, 0, 0, '2021-08-22 16:15:32', NULL, '10000', NULL, NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (44, '修改', 'organ:role:update', NULL, '#', NULL, 3, 'm_hr_role', NULL, NULL, 1, NULL, 0, 0, '2021-08-22 16:15:41', NULL, '10000', NULL, NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (45, '类型表格数据', 'dict:type:table', NULL, '#', NULL, 3, 'm_dict_manage', NULL, NULL, 1, NULL, 0, 0, '2021-08-22 17:05:26', NULL, '10000', NULL, NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (46, '根据类型查询', 'dict:data:list', NULL, '#', NULL, 3, 'm_dict_manage', NULL, NULL, 1, NULL, 0, 0, '2021-08-23 14:58:26', NULL, '10000', NULL, NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (47, '查询类型数据', 'dict:type:query', NULL, '#', NULL, 3, 'm_dict_manage', NULL, NULL, 1, NULL, 0, 0, '2021-08-23 15:13:24', NULL, '10000', NULL, NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (48, '删除类型', 'dict:type:remove', NULL, '#', NULL, 3, 'm_dict_manage', NULL, NULL, 1, NULL, 0, 0, '2021-08-29 07:35:51', NULL, '10000', NULL, NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (49, '查看类型', 'dict:type:see', NULL, '#', NULL, 3, 'm_dict_manage', NULL, NULL, 1, NULL, 0, 0, '2021-08-29 07:36:07', NULL, '10000', NULL, NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (50, '数据管理', 'm_dict_data', '/dict/data/manage', '/system/dict/data', NULL, 2, 'm_dict_manage', NULL, NULL, 1, 5, 1, 0, '2021-08-29 09:13:01', '2022-07-15 13:55:01', '10000', '10000', NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (51, '插件市场', 'plugin-market', '/plugin/market', '/system/plugin/index', NULL, 2, 'm_sys', 'el-icon-s-grid', NULL, 1, 3, 0, 0, '2022-05-10 15:17:29', '2022-05-10 15:19:31', '10000', '10000', NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (52, '合格', 'app_manage', '/system/app', '/system/app/index', NULL, 2, 'm_sys', 'el-icon-menu', NULL, 1, NULL, 0, 0, '2022-05-15 07:39:00', '2022-05-15 15:30:00', '10000', '10000', NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (53, '测试', 'test', '/aaa', '#', NULL, 1, 'm_hr', NULL, NULL, 1, NULL, 0, 0, '2022-07-15 14:05:26', NULL, '10000', NULL, NULL);
INSERT INTO `an_menu` (`menu_id`, `menu_name`, `menu_code`, `menu_url`, `component`, `redirect`, `menu_type`, `menu_parent_code`, `menu_icon`, `page_url`, `app_system_id`, `order_code`, `hidden`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (54, '查看', 'm_hr_company_see', NULL, '#', NULL, 3, 'm_hr_company', NULL, NULL, 1, NULL, 0, 0, '2022-07-15 14:13:05', NULL, '10000', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for app_system
-- ----------------------------
DROP TABLE IF EXISTS `app_system`;
CREATE TABLE `app_system` (
  `app_system_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_system_name` varchar(128) DEFAULT NULL COMMENT '系统名称',
  `app_system_icon` varchar(512) DEFAULT NULL COMMENT '系统logo',
  `app_system_url` varchar(512) DEFAULT NULL COMMENT '系统首页URL',
  `order_code` tinyint(3) DEFAULT NULL COMMENT '排序',
  `disabled` tinyint(2) DEFAULT '0' COMMENT '禁用 0 正常 1 禁用',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `created_person` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) DEFAULT NULL COMMENT '更新人',
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`app_system_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='应用系统表';

-- ----------------------------
-- Records of app_system
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for app_user_relation
-- ----------------------------
DROP TABLE IF EXISTS `app_user_relation`;
CREATE TABLE `app_user_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_id` bigint(20) NOT NULL COMMENT '应用id',
  `x_number` bigint(20) NOT NULL COMMENT 'x号',
  `effective` int(1) DEFAULT NULL COMMENT '有效性',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

-- ----------------------------
-- Records of app_user_relation
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for dict_data
-- ----------------------------
DROP TABLE IF EXISTS `dict_data`;
CREATE TABLE `dict_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `data_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `data_label` varchar(100) COLLATE utf8mb4_unicode_520_ci DEFAULT '' COMMENT '字典标签',
  `data_value` varchar(100) COLLATE utf8mb4_unicode_520_ci DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) COLLATE utf8mb4_unicode_520_ci DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) COLLATE utf8mb4_unicode_520_ci DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) COLLATE utf8mb4_unicode_520_ci DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `disabled` tinyint(2) DEFAULT '0' COMMENT '禁用 0 正常 1 禁用',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `created_person` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '更新人',
  `memo` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci COMMENT='字典数据表';

-- ----------------------------
-- Records of dict_data
-- ----------------------------
BEGIN;
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dict_data` (`id`, `data_sort`, `data_label`, `data_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 0, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for dict_type
-- ----------------------------
DROP TABLE IF EXISTS `dict_type`;
CREATE TABLE `dict_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) COLLATE utf8mb4_unicode_520_ci DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) COLLATE utf8mb4_unicode_520_ci DEFAULT '' COMMENT '字典类型',
  `status` char(1) COLLATE utf8mb4_unicode_520_ci DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `disabled` tinyint(1) DEFAULT '0' COMMENT '0 正常 1 禁用',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `created_person` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '更新人',
  `memo` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `dict_type` (`dict_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci COMMENT='字典类型表';

-- ----------------------------
-- Records of dict_type
-- ----------------------------
BEGIN;
INSERT INTO `dict_type` (`id`, `dict_name`, `dict_type`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (1, '用户性别', 'sys_user_sex', '0', 0, NULL, '2021-08-23 15:35:55', NULL, '10000', 'jkbjk ');
INSERT INTO `dict_type` (`id`, `dict_name`, `dict_type`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (2, '菜单状态', 'sys_show_hide', '0', 0, NULL, '2021-08-23 15:28:43', NULL, '10000', 'b ');
INSERT INTO `dict_type` (`id`, `dict_name`, `dict_type`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (3, '系统开关', 'sys_normal_disable', '0', 0, NULL, '2021-08-23 15:35:35', NULL, '10000', 'jkbjk');
INSERT INTO `dict_type` (`id`, `dict_name`, `dict_type`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (4, '任务状态', 'sys_job_status', '0', 0, NULL, '2021-08-23 15:19:42', NULL, '10000', '谁跟谁');
INSERT INTO `dict_type` (`id`, `dict_name`, `dict_type`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (5, '任务分组', 'sys_job_group', '0', 0, NULL, '2021-08-29 07:23:55', NULL, '10000', '是非得失');
INSERT INTO `dict_type` (`id`, `dict_name`, `dict_type`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (6, '系统是否', 'sys_yes_no', '0', 0, NULL, '2021-08-23 15:31:24', NULL, '10000', 'jkbjk');
INSERT INTO `dict_type` (`id`, `dict_name`, `dict_type`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (7, '通知类型', 'sys_notice_type', '0', 0, NULL, NULL, NULL, NULL, 'xds');
INSERT INTO `dict_type` (`id`, `dict_name`, `dict_type`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (8, '通知状态', 'sys_notice_status', '0', 0, NULL, NULL, NULL, NULL, 'xds');
INSERT INTO `dict_type` (`id`, `dict_name`, `dict_type`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (9, '操作类型', 'sys_oper_type', '0', 0, NULL, NULL, NULL, NULL, 'xds');
INSERT INTO `dict_type` (`id`, `dict_name`, `dict_type`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (10, '系统状态', 'sys_common_status', '0', 0, NULL, NULL, NULL, NULL, 'xds');
INSERT INTO `dict_type` (`id`, `dict_name`, `dict_type`, `status`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (101, '性别1', 'sex', '0', 0, '2021-08-22 09:37:27', '2021-08-29 07:24:07', '10000', '10000', '你那会颂德歌功');
COMMIT;

-- ----------------------------
-- Table structure for element
-- ----------------------------
DROP TABLE IF EXISTS `element`;
CREATE TABLE `element` (
  `element_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `element_type` varchar(16) DEFAULT NULL COMMENT '元素类型 ',
  `element_name` varchar(11) DEFAULT NULL COMMENT '元素名称',
  `element_flag` varchar(255) DEFAULT NULL COMMENT '元素标识',
  `disabled` tinyint(2) DEFAULT '0' COMMENT '禁用 0 正常 1 禁用',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `created_person` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) DEFAULT NULL COMMENT '更新人',
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`element_id`) USING BTREE,
  KEY `page_element_id` (`element_name`) USING BTREE,
  KEY `operate_power_id` (`element_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='元素表';

-- ----------------------------
-- Records of element
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for file_base
-- ----------------------------
DROP TABLE IF EXISTS `file_base`;
CREATE TABLE `file_base` (
  `file_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(128) DEFAULT NULL,
  `file_url` varchar(512) DEFAULT NULL,
  `file_size` double(16,2) DEFAULT NULL,
  `file_type` varchar(16) DEFAULT NULL COMMENT '文件类型',
  `file_memo` varchar(512) DEFAULT NULL,
  `disabled` tinyint(2) DEFAULT '0' COMMENT '禁用 0 正常 1 禁用',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `created_person` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) DEFAULT NULL COMMENT '更新人',
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件表\r\n';

-- ----------------------------
-- Records of file_base
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for groups
-- ----------------------------
DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
  `group_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(128) NOT NULL DEFAULT 'NULL' COMMENT '分组名称',
  `group_parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父分组id',
  `group_memo` varchar(512) DEFAULT 'NULL' COMMENT '分组说明',
  `disabled` tinyint(2) DEFAULT '0' COMMENT '禁用 0 正常 1 禁用',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `created_person` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) DEFAULT NULL COMMENT '更新人',
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`group_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分组表\r\n';

-- ----------------------------
-- Records of groups
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for icons
-- ----------------------------
DROP TABLE IF EXISTS `icons`;
CREATE TABLE `icons` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand` varchar(64) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL COMMENT '制造图标的品牌',
  `icon_type` int(2) DEFAULT NULL COMMENT '图标类型 1 代码 2 链接',
  `icon_code` varchar(255) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL COMMENT '图标代码',
  `icon_url` varchar(512) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL COMMENT '图标地址',
  `icon_commit` varchar(255) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL COMMENT '图标说明',
  `memo` varchar(255) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=283 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

-- ----------------------------
-- Records of icons
-- ----------------------------
BEGIN;
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (3, 'Element', 1, 'el-icon-platform-eleme', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (4, 'Element', 1, 'el-icon-eleme', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (5, 'Element', 1, 'el-icon-delete-solid', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (6, 'Element', 1, 'el-icon-delete', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (7, 'Element', 1, 'el-icon-s-tools', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (8, 'Element', 1, 'el-icon-setting', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (9, 'Element', 1, 'el-icon-user-solid', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (10, 'Element', 1, 'el-icon-user', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (11, 'Element', 1, 'el-icon-phone', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (12, 'Element', 1, 'el-icon-phone-outline', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (13, 'Element', 1, 'el-icon-more', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (14, 'Element', 1, 'el-icon-more-outline', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (15, 'Element', 1, 'el-icon-star-on', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (16, 'Element', 1, 'el-icon-star-off', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (17, 'Element', 1, 'el-icon-s-goods', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (18, 'Element', 1, 'el-icon-goods', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (19, 'Element', 1, 'el-icon-warning', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (20, 'Element', 1, 'el-icon-warning-outline', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (21, 'Element', 1, 'el-icon-question', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (22, 'Element', 1, 'el-icon-info', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (23, 'Element', 1, 'el-icon-remove', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (24, 'Element', 1, 'el-icon-circle-plus', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (25, 'Element', 1, 'el-icon-success', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (26, 'Element', 1, 'el-icon-error', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (27, 'Element', 1, 'el-icon-zoom-in', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (28, 'Element', 1, 'el-icon-zoom-out', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (29, 'Element', 1, 'el-icon-remove-outline', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (30, 'Element', 1, 'el-icon-circle-plus-outline', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (31, 'Element', 1, 'el-icon-circle-check', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (32, 'Element', 1, 'el-icon-circle-close', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (33, 'Element', 1, 'el-icon-s-help', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (34, 'Element', 1, 'el-icon-help', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (35, 'Element', 1, 'el-icon-minus', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (36, 'Element', 1, 'el-icon-plus', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (37, 'Element', 1, 'el-icon-check', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (38, 'Element', 1, 'el-icon-close', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (39, 'Element', 1, 'el-icon-picture', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (40, 'Element', 1, 'el-icon-picture-outline', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (41, 'Element', 1, 'el-icon-picture-outline-round', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (42, 'Element', 1, 'el-icon-upload', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (43, 'Element', 1, 'el-icon-upload2', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (44, 'Element', 1, 'el-icon-download', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (45, 'Element', 1, 'el-icon-camera-solid', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (46, 'Element', 1, 'el-icon-camera', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (47, 'Element', 1, 'el-icon-video-camera-solid', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (48, 'Element', 1, 'el-icon-video-camera', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (49, 'Element', 1, 'el-icon-message-solid', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (50, 'Element', 1, 'el-icon-bell', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (51, 'Element', 1, 'el-icon-s-cooperation', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (52, 'Element', 1, 'el-icon-s-order', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (53, 'Element', 1, 'el-icon-s-platform', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (54, 'Element', 1, 'el-icon-s-fold', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (55, 'Element', 1, 'el-icon-s-unfold', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (56, 'Element', 1, 'el-icon-s-operation', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (57, 'Element', 1, 'el-icon-s-promotion', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (58, 'Element', 1, 'el-icon-s-home', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (59, 'Element', 1, 'el-icon-s-release', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (60, 'Element', 1, 'el-icon-s-ticket', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (61, 'Element', 1, 'el-icon-s-management', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (62, 'Element', 1, 'el-icon-s-open', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (63, 'Element', 1, 'el-icon-s-shop', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (64, 'Element', 1, 'el-icon-s-marketing', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (65, 'Element', 1, 'el-icon-s-flag', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (66, 'Element', 1, 'el-icon-s-comment', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (67, 'Element', 1, 'el-icon-s-finance', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (68, 'Element', 1, 'el-icon-s-claim', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (69, 'Element', 1, 'el-icon-s-custom', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (70, 'Element', 1, 'el-icon-s-opportunity', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (71, 'Element', 1, 'el-icon-s-data', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (72, 'Element', 1, 'el-icon-s-check', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (73, 'Element', 1, 'el-icon-s-grid', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (74, 'Element', 1, 'el-icon-menu', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (75, 'Element', 1, 'el-icon-share', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (76, 'Element', 1, 'el-icon-d-caret', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (77, 'Element', 1, 'el-icon-caret-left', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (78, 'Element', 1, 'el-icon-caret-right', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (79, 'Element', 1, 'el-icon-caret-bottom', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (80, 'Element', 1, 'el-icon-caret-top', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (81, 'Element', 1, 'el-icon-bottom-left', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (82, 'Element', 1, 'el-icon-bottom-right', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (83, 'Element', 1, 'el-icon-back', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (84, 'Element', 1, 'el-icon-right', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (85, 'Element', 1, 'el-icon-bottom', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (86, 'Element', 1, 'el-icon-top', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (87, 'Element', 1, 'el-icon-top-left', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (88, 'Element', 1, 'el-icon-top-right', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (89, 'Element', 1, 'el-icon-arrow-left', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (90, 'Element', 1, 'el-icon-arrow-right', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (91, 'Element', 1, 'el-icon-arrow-down', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (92, 'Element', 1, 'el-icon-arrow-up', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (93, 'Element', 1, 'el-icon-d-arrow-left', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (94, 'Element', 1, 'el-icon-d-arrow-right', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (95, 'Element', 1, 'el-icon-video-pause', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (96, 'Element', 1, 'el-icon-video-play', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (97, 'Element', 1, 'el-icon-refresh', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (98, 'Element', 1, 'el-icon-refresh-right', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (99, 'Element', 1, 'el-icon-refresh-left', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (100, 'Element', 1, 'el-icon-finished', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (101, 'Element', 1, 'el-icon-sort', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (102, 'Element', 1, 'el-icon-sort-up', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (103, 'Element', 1, 'el-icon-sort-down', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (104, 'Element', 1, 'el-icon-rank', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (105, 'Element', 1, 'el-icon-loading', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (106, 'Element', 1, 'el-icon-view', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (107, 'Element', 1, 'el-icon-c-scale-to-original', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (108, 'Element', 1, 'el-icon-date', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (109, 'Element', 1, 'el-icon-edit', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (110, 'Element', 1, 'el-icon-edit-outline', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (111, 'Element', 1, 'el-icon-folder', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (112, 'Element', 1, 'el-icon-folder-opened', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (113, 'Element', 1, 'el-icon-folder-add', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (114, 'Element', 1, 'el-icon-folder-remove', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (115, 'Element', 1, 'el-icon-folder-delete', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (116, 'Element', 1, 'el-icon-folder-checked', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (117, 'Element', 1, 'el-icon-tickets', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (118, 'Element', 1, 'el-icon-document-remove', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (119, 'Element', 1, 'el-icon-document-delete', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (120, 'Element', 1, 'el-icon-document-copy', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (121, 'Element', 1, 'el-icon-document-checked', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (122, 'Element', 1, 'el-icon-document', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (123, 'Element', 1, 'el-icon-document-add', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (124, 'Element', 1, 'el-icon-printer', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (125, 'Element', 1, 'el-icon-paperclip', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (126, 'Element', 1, 'el-icon-takeaway-box', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (127, 'Element', 1, 'el-icon-search', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (128, 'Element', 1, 'el-icon-monitor', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (129, 'Element', 1, 'el-icon-attract', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (130, 'Element', 1, 'el-icon-mobile', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (131, 'Element', 1, 'el-icon-scissors', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (132, 'Element', 1, 'el-icon-umbrella', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (133, 'Element', 1, 'el-icon-headset', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (134, 'Element', 1, 'el-icon-brush', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (135, 'Element', 1, 'el-icon-mouse', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (136, 'Element', 1, 'el-icon-coordinate', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (137, 'Element', 1, 'el-icon-magic-stick', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (138, 'Element', 1, 'el-icon-reading', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (139, 'Element', 1, 'el-icon-data-line', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (140, 'Element', 1, 'el-icon-data-board', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (141, 'Element', 1, 'el-icon-pie-chart', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (142, 'Element', 1, 'el-icon-data-analysis', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (143, 'Element', 1, 'el-icon-collection-tag', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (144, 'Element', 1, 'el-icon-film', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (145, 'Element', 1, 'el-icon-suitcase', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (146, 'Element', 1, 'el-icon-suitcase-1', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (147, 'Element', 1, 'el-icon-receiving', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (148, 'Element', 1, 'el-icon-collection', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (149, 'Element', 1, 'el-icon-files', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (150, 'Element', 1, 'el-icon-notebook-1', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (151, 'Element', 1, 'el-icon-notebook-2', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (152, 'Element', 1, 'el-icon-toilet-paper', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (153, 'Element', 1, 'el-icon-office-building', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (154, 'Element', 1, 'el-icon-school', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (155, 'Element', 1, 'el-icon-table-lamp', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (156, 'Element', 1, 'el-icon-house', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (157, 'Element', 1, 'el-icon-no-smoking', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (158, 'Element', 1, 'el-icon-smoking', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (159, 'Element', 1, 'el-icon-shopping-cart-full', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (160, 'Element', 1, 'el-icon-shopping-cart-1', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (161, 'Element', 1, 'el-icon-shopping-cart-2', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (162, 'Element', 1, 'el-icon-shopping-bag-1', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (163, 'Element', 1, 'el-icon-shopping-bag-2', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (164, 'Element', 1, 'el-icon-sold-out', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (165, 'Element', 1, 'el-icon-sell', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (166, 'Element', 1, 'el-icon-present', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (167, 'Element', 1, 'el-icon-box', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (168, 'Element', 1, 'el-icon-bank-card', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (169, 'Element', 1, 'el-icon-money', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (170, 'Element', 1, 'el-icon-coin', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (171, 'Element', 1, 'el-icon-wallet', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (172, 'Element', 1, 'el-icon-discount', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (173, 'Element', 1, 'el-icon-price-tag', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (174, 'Element', 1, 'el-icon-news', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (175, 'Element', 1, 'el-icon-guide', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (176, 'Element', 1, 'el-icon-male', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (177, 'Element', 1, 'el-icon-female', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (178, 'Element', 1, 'el-icon-thumb', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (179, 'Element', 1, 'el-icon-cpu', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (180, 'Element', 1, 'el-icon-link', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (181, 'Element', 1, 'el-icon-connection', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (182, 'Element', 1, 'el-icon-open', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (183, 'Element', 1, 'el-icon-turn-off', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (184, 'Element', 1, 'el-icon-set-up', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (185, 'Element', 1, 'el-icon-chat-round', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (186, 'Element', 1, 'el-icon-chat-line-round', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (187, 'Element', 1, 'el-icon-chat-square', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (188, 'Element', 1, 'el-icon-chat-dot-round', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (189, 'Element', 1, 'el-icon-chat-dot-square', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (190, 'Element', 1, 'el-icon-chat-line-square', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (191, 'Element', 1, 'el-icon-message', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (192, 'Element', 1, 'el-icon-postcard', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (193, 'Element', 1, 'el-icon-position', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (194, 'Element', 1, 'el-icon-turn-off-microphone', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (195, 'Element', 1, 'el-icon-microphone', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (196, 'Element', 1, 'el-icon-close-notification', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (197, 'Element', 1, 'el-icon-bangzhu', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (198, 'Element', 1, 'el-icon-time', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (199, 'Element', 1, 'el-icon-odometer', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (200, 'Element', 1, 'el-icon-crop', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (201, 'Element', 1, 'el-icon-aim', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (202, 'Element', 1, 'el-icon-switch-button', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (203, 'Element', 1, 'el-icon-full-screen', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (204, 'Element', 1, 'el-icon-copy-document', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (205, 'Element', 1, 'el-icon-mic', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (206, 'Element', 1, 'el-icon-stopwatch', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (207, 'Element', 1, 'el-icon-medal-1', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (208, 'Element', 1, 'el-icon-medal', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (209, 'Element', 1, 'el-icon-trophy', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (210, 'Element', 1, 'el-icon-trophy-1', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (211, 'Element', 1, 'el-icon-first-aid-kit', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (212, 'Element', 1, 'el-icon-discover', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (213, 'Element', 1, 'el-icon-place', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (214, 'Element', 1, 'el-icon-location', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (215, 'Element', 1, 'el-icon-location-outline', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (216, 'Element', 1, 'el-icon-location-information', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (217, 'Element', 1, 'el-icon-add-location', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (218, 'Element', 1, 'el-icon-delete-location', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (219, 'Element', 1, 'el-icon-map-location', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (220, 'Element', 1, 'el-icon-alarm-clock', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (221, 'Element', 1, 'el-icon-timer', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (222, 'Element', 1, 'el-icon-watch-1', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (223, 'Element', 1, 'el-icon-watch', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (224, 'Element', 1, 'el-icon-lock', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (225, 'Element', 1, 'el-icon-unlock', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (226, 'Element', 1, 'el-icon-key', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (227, 'Element', 1, 'el-icon-service', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (228, 'Element', 1, 'el-icon-mobile-phone', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (229, 'Element', 1, 'el-icon-bicycle', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (230, 'Element', 1, 'el-icon-truck', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (231, 'Element', 1, 'el-icon-ship', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (232, 'Element', 1, 'el-icon-basketball', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (233, 'Element', 1, 'el-icon-football', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (234, 'Element', 1, 'el-icon-soccer', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (235, 'Element', 1, 'el-icon-baseball', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (236, 'Element', 1, 'el-icon-wind-power', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (237, 'Element', 1, 'el-icon-light-rain', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (238, 'Element', 1, 'el-icon-lightning', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (239, 'Element', 1, 'el-icon-heavy-rain', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (240, 'Element', 1, 'el-icon-sunrise', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (241, 'Element', 1, 'el-icon-sunrise-1', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (242, 'Element', 1, 'el-icon-sunset', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (243, 'Element', 1, 'el-icon-sunny', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (244, 'Element', 1, 'el-icon-cloudy', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (245, 'Element', 1, 'el-icon-partly-cloudy', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (246, 'Element', 1, 'el-icon-cloudy-and-sunny', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (247, 'Element', 1, 'el-icon-moon', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (248, 'Element', 1, 'el-icon-moon-night', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (249, 'Element', 1, 'el-icon-dish', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (250, 'Element', 1, 'el-icon-dish-1', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (251, 'Element', 1, 'el-icon-food', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (252, 'Element', 1, 'el-icon-chicken', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (253, 'Element', 1, 'el-icon-fork-spoon', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (254, 'Element', 1, 'el-icon-knife-fork', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (255, 'Element', 1, 'el-icon-burger', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (256, 'Element', 1, 'el-icon-tableware', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (257, 'Element', 1, 'el-icon-sugar', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (258, 'Element', 1, 'el-icon-dessert', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (259, 'Element', 1, 'el-icon-ice-cream', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (260, 'Element', 1, 'el-icon-hot-water', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (261, 'Element', 1, 'el-icon-water-cup', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (262, 'Element', 1, 'el-icon-coffee-cup', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (263, 'Element', 1, 'el-icon-cold-drink', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (264, 'Element', 1, 'el-icon-goblet', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (265, 'Element', 1, 'el-icon-goblet-full', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (266, 'Element', 1, 'el-icon-goblet-square', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (267, 'Element', 1, 'el-icon-goblet-square-full', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (268, 'Element', 1, 'el-icon-refrigerator', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (269, 'Element', 1, 'el-icon-grape', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (270, 'Element', 1, 'el-icon-watermelon', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (271, 'Element', 1, 'el-icon-cherry', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (272, 'Element', 1, 'el-icon-apple', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (273, 'Element', 1, 'el-icon-pear', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (274, 'Element', 1, 'el-icon-orange', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (275, 'Element', 1, 'el-icon-coffee', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (276, 'Element', 1, 'el-icon-ice-tea', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (277, 'Element', 1, 'el-icon-ice-drink', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (278, 'Element', 1, 'el-icon-milk-tea', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (279, 'Element', 1, 'el-icon-potato-strips', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (280, 'Element', 1, 'el-icon-lollipop', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (281, 'Element', 1, 'el-icon-ice-cream-square', NULL, NULL, NULL);
INSERT INTO `icons` (`id`, `brand`, `icon_type`, `icon_code`, `icon_url`, `icon_commit`, `memo`) VALUES (282, 'Element', 1, 'el-icon-ice-cream-round', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for menu_element
-- ----------------------------
DROP TABLE IF EXISTS `menu_element`;
CREATE TABLE `menu_element` (
  `menu_element_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_id` bigint(20) NOT NULL,
  `element_id` bigint(20) NOT NULL,
  `display` tinyint(2) DEFAULT '0' COMMENT '0 显示 1 隐藏',
  PRIMARY KEY (`menu_element_id`) USING BTREE,
  KEY `menu_id` (`menu_id`) USING BTREE,
  KEY `element_id` (`element_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单元素关联表';

-- ----------------------------
-- Records of menu_element
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for operate_power
-- ----------------------------
DROP TABLE IF EXISTS `operate_power`;
CREATE TABLE `operate_power` (
  `operate_power_id` int(11) NOT NULL AUTO_INCREMENT,
  `operate_power_name` varchar(128) DEFAULT NULL COMMENT '操作名称',
  `operate_power_type` tinyint(4) DEFAULT NULL COMMENT '操作权限类型 1 读写 2 读 3 写 4 无权限',
  `disabled` tinyint(2) DEFAULT '0' COMMENT '禁用 0 正常 1 禁用',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `created_person` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) DEFAULT NULL COMMENT '更新人',
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`operate_power_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作权限表';

-- ----------------------------
-- Records of operate_power
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for organ
-- ----------------------------
DROP TABLE IF EXISTS `organ`;
CREATE TABLE `organ` (
  `organ_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `organ_name` varchar(128) DEFAULT NULL,
  `organ_type` tinyint(2) NOT NULL COMMENT '1 集团 2 公司 3 单位 4 组织',
  `organ_code` varchar(16) NOT NULL COMMENT '编码',
  `organ_parent_code` varchar(16) NOT NULL COMMENT '父编码',
  `order_code` tinyint(3) NOT NULL COMMENT '序号',
  `organ_url` varchar(128) DEFAULT NULL COMMENT '官方地址',
  `caption` varchar(128) DEFAULT NULL COMMENT '说明',
  `disabled` tinyint(2) NOT NULL DEFAULT '0' COMMENT '禁用 0 正常 1 禁用',
  `is_del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '逻辑删除 0正常 1删除',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `created_person` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) DEFAULT NULL COMMENT '更新人',
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`organ_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='组织表';

-- ----------------------------
-- Records of organ
-- ----------------------------
BEGIN;
INSERT INTO `organ` (`organ_id`, `organ_name`, `organ_type`, `organ_code`, `organ_parent_code`, `order_code`, `organ_url`, `caption`, `disabled`, `is_del`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (1, 'dd', 1, 'dd', 'ROOT', 0, NULL, NULL, 1, 1, '2022-07-16 12:35:51', '2022-07-16 14:45:48', '10000', '99999', NULL);
INSERT INTO `organ` (`organ_id`, `organ_name`, `organ_type`, `organ_code`, `organ_parent_code`, `order_code`, `organ_url`, `caption`, `disabled`, `is_del`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (2, 'rr', 1, 'rr', 'ROOT', 0, NULL, NULL, 1, 1, '2022-07-16 12:40:05', '2022-07-16 15:03:11', '10000', '99999', NULL);
INSERT INTO `organ` (`organ_id`, `organ_name`, `organ_type`, `organ_code`, `organ_parent_code`, `order_code`, `organ_url`, `caption`, `disabled`, `is_del`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (3, 'ff', 2, 'ff', 'rr', 0, NULL, NULL, 1, 1, '2022-07-16 13:18:33', '2022-07-16 15:02:02', '99999', '99999', NULL);
INSERT INTO `organ` (`organ_id`, `organ_name`, `organ_type`, `organ_code`, `organ_parent_code`, `order_code`, `organ_url`, `caption`, `disabled`, `is_del`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (4, '蚁点集团', 2, 'andot_group', 'ROOT', 0, NULL, NULL, 1, 0, '2022-07-16 14:22:33', NULL, '99999', NULL, NULL);
INSERT INTO `organ` (`organ_id`, `organ_name`, `organ_type`, `organ_code`, `organ_parent_code`, `order_code`, `organ_url`, `caption`, `disabled`, `is_del`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (6, '蚁点科技', 2, 'andot_tele', 'andot_group', 1, NULL, NULL, 1, 0, '2022-07-16 14:23:48', NULL, '99999', NULL, NULL);
INSERT INTO `organ` (`organ_id`, `organ_name`, `organ_type`, `organ_code`, `organ_parent_code`, `order_code`, `organ_url`, `caption`, `disabled`, `is_del`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (7, '牧云子公司', 2, 'muyun', 'andot_tele', 1, NULL, NULL, 1, 0, '2022-07-16 14:24:37', NULL, '99999', NULL, NULL);
INSERT INTO `organ` (`organ_id`, `organ_name`, `organ_type`, `organ_code`, `organ_parent_code`, `order_code`, `organ_url`, `caption`, `disabled`, `is_del`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (8, '蚁点材料研究员', 3, 'andot_y', 'andot_group', 0, NULL, NULL, 1, 0, '2022-07-16 14:25:31', NULL, '99999', NULL, NULL);
INSERT INTO `organ` (`organ_id`, `organ_name`, `organ_type`, `organ_code`, `organ_parent_code`, `order_code`, `organ_url`, `caption`, `disabled`, `is_del`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (9, '蚁点工作室', 1, 'andot_studio', 'andot_group', 1, NULL, NULL, 1, 0, '2022-07-16 14:26:28', NULL, '99999', NULL, NULL);
INSERT INTO `organ` (`organ_id`, `organ_name`, `organ_type`, `organ_code`, `organ_parent_code`, `order_code`, `organ_url`, `caption`, `disabled`, `is_del`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (10, '蚁点生物科技', 2, 'andot_shengwu', 'andot_group', 5, NULL, NULL, 1, 0, '2022-07-16 14:27:29', NULL, '99999', NULL, NULL);
INSERT INTO `organ` (`organ_id`, `organ_name`, `organ_type`, `organ_code`, `organ_parent_code`, `order_code`, `organ_url`, `caption`, `disabled`, `is_del`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (11, '牧云能源', 2, 'muyun_engry', 'andot_tele', 0, NULL, NULL, 1, 0, '2022-07-16 14:29:05', NULL, '99999', NULL, NULL);
INSERT INTO `organ` (`organ_id`, `organ_name`, `organ_type`, `organ_code`, `organ_parent_code`, `order_code`, `organ_url`, `caption`, `disabled`, `is_del`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (12, '牧云电力', 1, 'power', 'andot_tele', 0, NULL, NULL, 1, 0, '2022-07-16 14:29:37', NULL, '99999', NULL, NULL);
INSERT INTO `organ` (`organ_id`, `organ_name`, `organ_type`, `organ_code`, `organ_parent_code`, `order_code`, `organ_url`, `caption`, `disabled`, `is_del`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (13, '牧云畜牧业', 2, 'xumuye', 'andot_tele', 0, NULL, NULL, 1, 0, '2022-07-16 14:34:45', NULL, '99999', NULL, NULL);
INSERT INTO `organ` (`organ_id`, `organ_name`, `organ_type`, `organ_code`, `organ_parent_code`, `order_code`, `organ_url`, `caption`, `disabled`, `is_del`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (14, '浪潮集团', 1, 'insper', 'ROOT', 0, '', NULL, 1, 0, '2022-07-16 15:02:54', NULL, '99999', NULL, NULL);
INSERT INTO `organ` (`organ_id`, `organ_name`, `organ_type`, `organ_code`, `organ_parent_code`, `order_code`, `organ_url`, `caption`, `disabled`, `is_del`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (15, '好未来集团', 1, 'tal', 'ROOT', 3, '', NULL, 1, 0, '2022-07-16 15:03:07', NULL, '99999', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for organ_dept
-- ----------------------------
DROP TABLE IF EXISTS `organ_dept`;
CREATE TABLE `organ_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(128) DEFAULT NULL,
  `dept_type` tinyint(2) NOT NULL COMMENT '1 集团 2 公司 3 单位 4 组织',
  `dept_code` varchar(16) NOT NULL COMMENT '编码',
  `dept_parent_code` varchar(16) NOT NULL COMMENT '父编码',
  `sort` tinyint(3) NOT NULL COMMENT '序号',
  `caption` varchar(128) DEFAULT NULL COMMENT '说明',
  `disabled` tinyint(2) NOT NULL DEFAULT '0' COMMENT '禁用 0 正常 1 禁用',
  `is_del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '逻辑删除 0正常 1删除',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `created_person` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) DEFAULT NULL COMMENT '更新人',
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COMMENT='组织表';

-- ----------------------------
-- Records of organ_dept
-- ----------------------------
BEGIN;
INSERT INTO `organ_dept` (`dept_id`, `dept_name`, `dept_type`, `dept_code`, `dept_parent_code`, `sort`, `caption`, `disabled`, `is_del`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (16, '人事部', 1, 'person', 'ROOT', 1, NULL, 1, 0, '2022-07-16 15:18:31', NULL, '99999', NULL, NULL);
INSERT INTO `organ_dept` (`dept_id`, `dept_name`, `dept_type`, `dept_code`, `dept_parent_code`, `sort`, `caption`, `disabled`, `is_del`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (17, '产研部', 1, 'tel', 'ROOT', 1, NULL, 1, 0, '2022-07-16 15:18:43', '2022-07-16 15:23:25', '99999', '99999', NULL);
INSERT INTO `organ_dept` (`dept_id`, `dept_name`, `dept_type`, `dept_code`, `dept_parent_code`, `sort`, `caption`, `disabled`, `is_del`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (18, '产品部', 1, 'telss', 'ROOT', 1, NULL, 1, 1, '2022-07-16 15:18:51', '2022-07-16 15:23:27', '99999', '99999', NULL);
INSERT INTO `organ_dept` (`dept_id`, `dept_name`, `dept_type`, `dept_code`, `dept_parent_code`, `sort`, `caption`, `disabled`, `is_del`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (19, '产品组', 1, 'product', 'tel', 1, NULL, 1, 0, '2022-07-16 15:23:39', NULL, '99999', NULL, NULL);
INSERT INTO `organ_dept` (`dept_id`, `dept_name`, `dept_type`, `dept_code`, `dept_parent_code`, `sort`, `caption`, `disabled`, `is_del`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (20, '前端组', 1, 'fe', 'tel', 1, NULL, 1, 0, '2022-07-16 15:23:59', NULL, '99999', NULL, NULL);
INSERT INTO `organ_dept` (`dept_id`, `dept_name`, `dept_type`, `dept_code`, `dept_parent_code`, `sort`, `caption`, `disabled`, `is_del`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (21, '后端组', 1, 'be', 'tel', 3, NULL, 1, 0, '2022-07-16 15:24:11', NULL, '99999', NULL, NULL);
INSERT INTO `organ_dept` (`dept_id`, `dept_name`, `dept_type`, `dept_code`, `dept_parent_code`, `sort`, `caption`, `disabled`, `is_del`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (22, '测试组', 1, 'test', 'tel', 5, NULL, 1, 0, '2022-07-16 15:24:21', NULL, '99999', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for organ_groups
-- ----------------------------
DROP TABLE IF EXISTS `organ_groups`;
CREATE TABLE `organ_groups` (
  `organ_group_id` bigint(20) NOT NULL,
  `organ_id` bigint(20) DEFAULT NULL COMMENT '组织id',
  `group_id` bigint(20) DEFAULT NULL COMMENT '分组id',
  PRIMARY KEY (`organ_group_id`) USING BTREE,
  KEY `organ_id` (`organ_id`) USING BTREE,
  KEY `group_id` (`group_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组织分组关系表\r\n';

-- ----------------------------
-- Records of organ_groups
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for organ_user
-- ----------------------------
DROP TABLE IF EXISTS `organ_user`;
CREATE TABLE `organ_user` (
  `organ_user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `x_number` bigint(20) NOT NULL COMMENT '用户唯一标示',
  `organ_id` bigint(20) NOT NULL COMMENT '组织id',
  PRIMARY KEY (`organ_user_id`) USING BTREE,
  KEY `organ_id` (`organ_id`) USING BTREE,
  KEY `user_id` (`x_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户组织管理表\r\n';

-- ----------------------------
-- Records of organ_user
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `role_type` tinyint(4) DEFAULT NULL COMMENT '角色类型',
  `role_code` varchar(16) DEFAULT NULL,
  `role_order` int(2) DEFAULT NULL COMMENT '角色序号',
  `disabled` tinyint(4) DEFAULT '0' COMMENT '禁用 0 正常 1 禁用',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `created_person` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) DEFAULT NULL COMMENT '更新人',
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='角色表\r\n';

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` (`role_id`, `role_name`, `role_type`, `role_code`, `role_order`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (1, '上帝管理员', 1, 'ADMIN', 1, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `role` (`role_id`, `role_name`, `role_type`, `role_code`, `role_order`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (2, '测试用户', 3, 'TEST_USER', 2, 1, NULL, '2021-08-19 15:34:34', NULL, '10000', 'lihlhklk');
INSERT INTO `role` (`role_id`, `role_name`, `role_type`, `role_code`, `role_order`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (3, '蚁点公司管理员', 2, 'COM_ADMIN', 3, 0, NULL, '2022-07-15 14:33:06', NULL, '10000', NULL);
COMMIT;

-- ----------------------------
-- Table structure for role_groups
-- ----------------------------
DROP TABLE IF EXISTS `role_groups`;
CREATE TABLE `role_groups` (
  `role_groups_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `group_id` bigint(20) NOT NULL COMMENT '分组id',
  PRIMARY KEY (`role_groups_id`) USING BTREE,
  KEY `role_id` (`role_id`) USING BTREE,
  KEY `group_id` (`group_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色分组关系表';

-- ----------------------------
-- Records of role_groups
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `role_menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(16) DEFAULT NULL COMMENT '角色id',
  `menu_code` varchar(64) DEFAULT NULL COMMENT '菜单标识',
  PRIMARY KEY (`role_menu_id`) USING BTREE,
  UNIQUE KEY `menuroleid` (`menu_code`,`role_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=372 DEFAULT CHARSET=utf8mb4 COMMENT='角色用户关系表\r\n';

-- ----------------------------
-- Records of role_menu
-- ----------------------------
BEGIN;
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (80, 'COM_ADMIN', 'btn_hr_delete');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (77, 'COM_ADMIN', 'btn_hr_role_add');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (78, 'COM_ADMIN', 'btn_hr_role_see');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (79, 'COM_ADMIN', 'btn_hr_role_update');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (350, 'COM_ADMIN', 'dict:data:delete');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (354, 'COM_ADMIN', 'dict:data:list');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (351, 'COM_ADMIN', 'dict:data:see');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (352, 'COM_ADMIN', 'dict:data:table');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (348, 'COM_ADMIN', 'dict:type:add');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (355, 'COM_ADMIN', 'dict:type:query');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (356, 'COM_ADMIN', 'dict:type:remove');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (357, 'COM_ADMIN', 'dict:type:see');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (353, 'COM_ADMIN', 'dict:type:table');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (349, 'COM_ADMIN', 'dict:type:update');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (126, 'COM_ADMIN', 'external');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (358, 'COM_ADMIN', 'm_dict_data');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (347, 'COM_ADMIN', 'm_dict_manage');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (343, 'COM_ADMIN', 'm_hr');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (363, 'COM_ADMIN', 'm_hr_company');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (364, 'COM_ADMIN', 'm_hr_company_see');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (365, 'COM_ADMIN', 'm_hr_depart');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (6, 'COM_ADMIN', 'm_hr_organ');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (367, 'COM_ADMIN', 'm_hr_post');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (360, 'COM_ADMIN', 'm_hr_role');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (366, 'COM_ADMIN', 'm_hr_user');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (368, 'COM_ADMIN', 'm_line');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (369, 'COM_ADMIN', 'm_line_chat');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (370, 'COM_ADMIN', 'm_mark');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (371, 'COM_ADMIN', 'm_mark_label');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (344, 'COM_ADMIN', 'm_menu');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (2, 'COM_ADMIN', 'm_organ_user');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (342, 'COM_ADMIN', 'm_sys');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (167, 'COM_ADMIN', 'organ:role:add');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (168, 'COM_ADMIN', 'organ:role:remove');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (361, 'COM_ADMIN', 'organ:role:see');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (362, 'COM_ADMIN', 'organ:role:update');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (359, 'COM_ADMIN', 'plugin-market');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (345, 'COM_ADMIN', 'system:menu:add');
INSERT INTO `role_menu` (`role_menu_id`, `role_code`, `menu_code`) VALUES (346, 'COM_ADMIN', 'system:menu:update');
COMMIT;

-- ----------------------------
-- Table structure for role_operate_power
-- ----------------------------
DROP TABLE IF EXISTS `role_operate_power`;
CREATE TABLE `role_operate_power` (
  `role_power_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `operate_power_id` bigint(20) NOT NULL COMMENT '操作权限id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`role_power_id`) USING BTREE,
  KEY `operate_power_id` (`operate_power_id`) USING BTREE,
  KEY `role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色操作权限表';

-- ----------------------------
-- Records of role_operate_power
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for role_user
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
  `role_user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(16) NOT NULL COMMENT '角色id',
  `x_number` bigint(20) NOT NULL COMMENT '用户标识',
  PRIMARY KEY (`role_user_id`) USING BTREE,
  KEY `user_id` (`x_number`) USING BTREE,
  KEY `role_id` (`role_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='角色用户关系表\r\n';

-- ----------------------------
-- Records of role_user
-- ----------------------------
BEGIN;
INSERT INTO `role_user` (`role_user_id`, `role_code`, `x_number`) VALUES (1, 'COM_ADMIN', 10000);
INSERT INTO `role_user` (`role_user_id`, `role_code`, `x_number`) VALUES (2, 'COM_ADMIN', 66666);
COMMIT;

-- ----------------------------
-- Table structure for share_token
-- ----------------------------
DROP TABLE IF EXISTS `share_token`;
CREATE TABLE `share_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `x_number` bigint(20) NOT NULL,
  `token` varchar(256) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL COMMENT 'token',
  `last_token_refresh_time` timestamp NULL DEFAULT NULL COMMENT '最后token刷新时间',
  `token_create_time` timestamp NULL DEFAULT NULL COMMENT 'token创建时间',
  `reversion` int(2) DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

-- ----------------------------
-- Records of share_token
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `x_number` bigint(20) DEFAULT NULL COMMENT '唯一标识码',
  `phone` varchar(11) DEFAULT NULL COMMENT '用户名称',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `user_type` tinyint(2) DEFAULT NULL COMMENT '用户类型',
  `online_status` tinyint(2) DEFAULT NULL COMMENT '在线状态',
  `salt` varchar(255) DEFAULT NULL COMMENT '加密盐值',
  `disabled` tinyint(2) DEFAULT '0' COMMENT '禁用 0 正常 1 禁用',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `created_person` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) DEFAULT NULL COMMENT '更新人',
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  KEY `xnumber` (`x_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='用户基本表';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`user_id`, `x_number`, `phone`, `password`, `user_type`, `online_status`, `salt`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (1, 10000, '18668927276', '$2a$10$aa9u3R3zjqNx09BqfMM1QeCfLqWG543zMRoum.ZIm2ngugrQsUEJi', 1, 1, NULL, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`user_id`, `x_number`, `phone`, `password`, `user_type`, `online_status`, `salt`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (2, 66666, '18888261554', '$2a$10$aa9u3R3zjqNx09BqfMM1QeCfLqWG543zMRoum.ZIm2ngugrQsUEJi', 1, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`user_id`, `x_number`, `phone`, `password`, `user_type`, `online_status`, `salt`, `disabled`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (3, 99999, '18888261554', '$2a$10$aa9u3R3zjqNx09BqfMM1QeCfLqWG543zMRoum.ZIm2ngugrQsUEJi', 1, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for user_detail
-- ----------------------------
DROP TABLE IF EXISTS `user_detail`;
CREATE TABLE `user_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `x_number` bigint(20) NOT NULL,
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `introduction` varchar(255) DEFAULT NULL COMMENT '简介',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别 M 女 F 男',
  `birthday` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '生日',
  `auth` tinyint(2) DEFAULT '0' COMMENT '认证状态 0 未认证 1 认证',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `domain` varchar(255) DEFAULT '',
  `disabled` tinyint(2) DEFAULT '0' COMMENT '禁用 0 正常 1 禁用',
  `driver` varchar(255) DEFAULT NULL COMMENT '设备型号',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `created_person` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) DEFAULT NULL COMMENT '更新人',
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_detail
-- ----------------------------
BEGIN;
INSERT INTO `user_detail` (`id`, `x_number`, `real_name`, `introduction`, `sex`, `birthday`, `auth`, `avatar`, `domain`, `disabled`, `driver`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (1, 10000, '创始人', '工程师', '1', '2021-08-29 21:10:16', 1, NULL, '1', 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user_detail` (`id`, `x_number`, `real_name`, `introduction`, `sex`, `birthday`, `auth`, `avatar`, `domain`, `disabled`, `driver`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (2, 66666, '小鑫', '流量控制', '1', '2021-08-29 21:13:29', 1, NULL, '1', 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user_detail` (`id`, `x_number`, `real_name`, `introduction`, `sex`, `birthday`, `auth`, `avatar`, `domain`, `disabled`, `driver`, `created_time`, `updated_time`, `created_person`, `updated_person`, `memo`) VALUES (3, 99999, '管理员', '平台管理', '1', '2021-08-29 21:13:42', 1, NULL, '1', 1, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for user_file
-- ----------------------------
DROP TABLE IF EXISTS `user_file`;
CREATE TABLE `user_file` (
  `user_file_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `file_id` bigint(11) DEFAULT NULL COMMENT '文件id',
  `x_number` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`user_file_id`) USING BTREE,
  KEY `file_id` (`file_id`) USING BTREE,
  KEY `user_id` (`x_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户文件表\r\n';

-- ----------------------------
-- Records of user_file
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user_groups
-- ----------------------------
DROP TABLE IF EXISTS `user_groups`;
CREATE TABLE `user_groups` (
  `user_group_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `x_number` bigint(20) NOT NULL COMMENT '用户唯一编号',
  `group_id` bigint(20) NOT NULL COMMENT '分组id',
  PRIMARY KEY (`user_group_id`) USING BTREE,
  KEY `group_id` (`group_id`) USING BTREE,
  KEY `user_id` (`x_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户分组表\r\n';

-- ----------------------------
-- Records of user_groups
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
