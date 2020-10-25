/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : share-rbac

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 25/10/2020 14:25:17
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
  `deleted_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `created_person` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) DEFAULT NULL COMMENT '更新人',
  `deleted_person` varchar(255) DEFAULT NULL COMMENT '删除人',
  PRIMARY KEY (`action_id`) USING BTREE,
  UNIQUE KEY `menuid` (`menu_id`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='请求地址表';

-- ----------------------------
-- Table structure for action_operate_power
-- ----------------------------
DROP TABLE IF EXISTS `action_operate_power`;
CREATE TABLE `action_operate_power` (
  `action_power_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action_id` bigint(20) NOT NULL,
  `operate_power_id` bigint(20) NOT NULL,
  PRIMARY KEY (`action_power_id`),
  KEY `operate_power_id` (`operate_power_id`),
  KEY `action_id` (`action_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='请求权限关系表\r\n';

-- ----------------------------
-- Table structure for an_menu
-- ----------------------------
DROP TABLE IF EXISTS `an_menu`;
CREATE TABLE `an_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(128) DEFAULT NULL COMMENT '菜单名称',
  `menu_code` varchar(64) DEFAULT NULL COMMENT '菜单编码',
  `menu_url` varchar(512) DEFAULT NULL COMMENT '菜单地址',
  `component` varchar(255) DEFAULT NULL,
  `redirect` varchar(255) DEFAULT NULL,
  `menu_type` tinyint(2) DEFAULT NULL COMMENT '菜单类型',
  `menu_parent_code` varchar(64) DEFAULT NULL COMMENT '父菜单id',
  `menu_icon` varchar(512) DEFAULT NULL COMMENT '菜单图标',
  `page_url` varchar(512) DEFAULT NULL,
  `app_system_id` bigint(20) DEFAULT NULL COMMENT '系统id',
  `order_code` int(2) DEFAULT NULL,
  `disabled` tinyint(1) DEFAULT '0' COMMENT '禁用 0 正常 1 禁用',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `deleted_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `created_person` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) DEFAULT NULL COMMENT '更新人',
  `deleted_person` varchar(255) DEFAULT NULL COMMENT '删除人',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表\r\n';

-- ----------------------------
-- Records of an_menu
-- ----------------------------
BEGIN;
INSERT INTO `an_menu` VALUES (2, '系统管理', 'm_sys_mange', '/system', '#', '/system/menu', 1, '0', 'el-icon-s-tools', NULL, 1, 2, 1, '2020-09-06 20:05:30', '2020-09-20 04:30:48', NULL, 'god', '10000', NULL);
INSERT INTO `an_menu` VALUES (3, '公司管理', 'm_company_m', '/company1', '/nested/menu1/menu1-3', '', 1, 'm_sys_mange', NULL, NULL, 1, 1, 1, '2020-09-06 23:55:03', NULL, NULL, 'god', NULL, NULL);
INSERT INTO `an_menu` VALUES (4, '表单', 'form', '/form343', '/system/form/index', '', 1, 'm_sys_mange', NULL, NULL, 1, 2, 1, '2020-09-10 00:03:25', NULL, NULL, 'god', NULL, NULL);
INSERT INTO `an_menu` VALUES (5, '组织管理', 'm_organ', '/organ/manage', '/organ/organ/index', '', 1, 'm_organ_mange', NULL, NULL, 1, 1, 1, '2020-09-17 22:50:09', NULL, NULL, 'god', NULL, NULL);
INSERT INTO `an_menu` VALUES (6, '用户管理', 'm_user', '/user', '/organ/user/index', NULL, 1, 'm_organ_mange', NULL, NULL, 1, 1, 1, '2020-09-17 22:50:12', NULL, NULL, 'god', NULL, NULL);
INSERT INTO `an_menu` VALUES (7, '组织管理', 'm_organ_mange', '/organ', '#', '/organ/manage', 1, 'm_organ_mange', 'el-icon-user-solid', NULL, 1, 2, 1, '2020-09-06 20:05:30', '2020-09-20 04:30:51', NULL, 'god', '10000', NULL);
INSERT INTO `an_menu` VALUES (8, '菜单管理', 'm_menu', '/menu', '/system/menu/index', NULL, 1, 'm_sys_mange', NULL, NULL, 1, 1, 1, '2020-09-17 22:50:06', '2020-09-20 04:32:02', NULL, 'god', '10000', NULL);
INSERT INTO `an_menu` VALUES (18, '字典管理', 'm_dict_manage', '/dict/manage', '/system/dict/index', '', 1, 'm_sys_mange', '', NULL, 1, NULL, 1, '2020-09-18 15:40:25', '2020-09-19 05:48:25', NULL, '10000', '10000', NULL);
INSERT INTO `an_menu` VALUES (20, '角色管理', 'm_role', '/role', '/system/role/index', '', 1, 'm_organ_mange', '', NULL, 1, NULL, 1, '2020-09-19 06:20:01', NULL, NULL, '10000', NULL, NULL);
INSERT INTO `an_menu` VALUES (21, '2323', '34', NULL, NULL, NULL, 1, '0', NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL);
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
  `deleted_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `created_person` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) DEFAULT NULL COMMENT '更新人',
  `deleted_person` varchar(255) DEFAULT NULL COMMENT '删除人',
  PRIMARY KEY (`app_system_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='应用系统表';

-- ----------------------------
-- Table structure for app_user_relation
-- ----------------------------
DROP TABLE IF EXISTS `app_user_relation`;
CREATE TABLE `app_user_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_id` bigint(20) NOT NULL COMMENT '应用id',
  `x_number` bigint(20) NOT NULL COMMENT 'x号',
  `effective` int(1) DEFAULT NULL COMMENT '有效性',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

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
  `deleted_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `created_person` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) DEFAULT NULL COMMENT '更新人',
  `deleted_person` varchar(255) DEFAULT NULL COMMENT '删除人',
  PRIMARY KEY (`element_id`) USING BTREE,
  KEY `page_element_id` (`element_name`),
  KEY `operate_power_id` (`element_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='元素表';

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
  `deleted_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `created_person` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) DEFAULT NULL COMMENT '更新人',
  `deleted_person` varchar(255) DEFAULT NULL COMMENT '删除人',
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件表\r\n';

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
  `deleted_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `created_person` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) DEFAULT NULL COMMENT '更新人',
  `deleted_person` varchar(255) DEFAULT NULL COMMENT '删除人',
  PRIMARY KEY (`group_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分组表\r\n';

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=283 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

-- ----------------------------
-- Records of icons
-- ----------------------------
BEGIN;
INSERT INTO `icons` VALUES (3, 'Element', 1, 'el-icon-platform-eleme', NULL, NULL);
INSERT INTO `icons` VALUES (4, 'Element', 1, 'el-icon-eleme', NULL, NULL);
INSERT INTO `icons` VALUES (5, 'Element', 1, 'el-icon-delete-solid', NULL, NULL);
INSERT INTO `icons` VALUES (6, 'Element', 1, 'el-icon-delete', NULL, NULL);
INSERT INTO `icons` VALUES (7, 'Element', 1, 'el-icon-s-tools', NULL, NULL);
INSERT INTO `icons` VALUES (8, 'Element', 1, 'el-icon-setting', NULL, NULL);
INSERT INTO `icons` VALUES (9, 'Element', 1, 'el-icon-user-solid', NULL, NULL);
INSERT INTO `icons` VALUES (10, 'Element', 1, 'el-icon-user', NULL, NULL);
INSERT INTO `icons` VALUES (11, 'Element', 1, 'el-icon-phone', NULL, NULL);
INSERT INTO `icons` VALUES (12, 'Element', 1, 'el-icon-phone-outline', NULL, NULL);
INSERT INTO `icons` VALUES (13, 'Element', 1, 'el-icon-more', NULL, NULL);
INSERT INTO `icons` VALUES (14, 'Element', 1, 'el-icon-more-outline', NULL, NULL);
INSERT INTO `icons` VALUES (15, 'Element', 1, 'el-icon-star-on', NULL, NULL);
INSERT INTO `icons` VALUES (16, 'Element', 1, 'el-icon-star-off', NULL, NULL);
INSERT INTO `icons` VALUES (17, 'Element', 1, 'el-icon-s-goods', NULL, NULL);
INSERT INTO `icons` VALUES (18, 'Element', 1, 'el-icon-goods', NULL, NULL);
INSERT INTO `icons` VALUES (19, 'Element', 1, 'el-icon-warning', NULL, NULL);
INSERT INTO `icons` VALUES (20, 'Element', 1, 'el-icon-warning-outline', NULL, NULL);
INSERT INTO `icons` VALUES (21, 'Element', 1, 'el-icon-question', NULL, NULL);
INSERT INTO `icons` VALUES (22, 'Element', 1, 'el-icon-info', NULL, NULL);
INSERT INTO `icons` VALUES (23, 'Element', 1, 'el-icon-remove', NULL, NULL);
INSERT INTO `icons` VALUES (24, 'Element', 1, 'el-icon-circle-plus', NULL, NULL);
INSERT INTO `icons` VALUES (25, 'Element', 1, 'el-icon-success', NULL, NULL);
INSERT INTO `icons` VALUES (26, 'Element', 1, 'el-icon-error', NULL, NULL);
INSERT INTO `icons` VALUES (27, 'Element', 1, 'el-icon-zoom-in', NULL, NULL);
INSERT INTO `icons` VALUES (28, 'Element', 1, 'el-icon-zoom-out', NULL, NULL);
INSERT INTO `icons` VALUES (29, 'Element', 1, 'el-icon-remove-outline', NULL, NULL);
INSERT INTO `icons` VALUES (30, 'Element', 1, 'el-icon-circle-plus-outline', NULL, NULL);
INSERT INTO `icons` VALUES (31, 'Element', 1, 'el-icon-circle-check', NULL, NULL);
INSERT INTO `icons` VALUES (32, 'Element', 1, 'el-icon-circle-close', NULL, NULL);
INSERT INTO `icons` VALUES (33, 'Element', 1, 'el-icon-s-help', NULL, NULL);
INSERT INTO `icons` VALUES (34, 'Element', 1, 'el-icon-help', NULL, NULL);
INSERT INTO `icons` VALUES (35, 'Element', 1, 'el-icon-minus', NULL, NULL);
INSERT INTO `icons` VALUES (36, 'Element', 1, 'el-icon-plus', NULL, NULL);
INSERT INTO `icons` VALUES (37, 'Element', 1, 'el-icon-check', NULL, NULL);
INSERT INTO `icons` VALUES (38, 'Element', 1, 'el-icon-close', NULL, NULL);
INSERT INTO `icons` VALUES (39, 'Element', 1, 'el-icon-picture', NULL, NULL);
INSERT INTO `icons` VALUES (40, 'Element', 1, 'el-icon-picture-outline', NULL, NULL);
INSERT INTO `icons` VALUES (41, 'Element', 1, 'el-icon-picture-outline-round', NULL, NULL);
INSERT INTO `icons` VALUES (42, 'Element', 1, 'el-icon-upload', NULL, NULL);
INSERT INTO `icons` VALUES (43, 'Element', 1, 'el-icon-upload2', NULL, NULL);
INSERT INTO `icons` VALUES (44, 'Element', 1, 'el-icon-download', NULL, NULL);
INSERT INTO `icons` VALUES (45, 'Element', 1, 'el-icon-camera-solid', NULL, NULL);
INSERT INTO `icons` VALUES (46, 'Element', 1, 'el-icon-camera', NULL, NULL);
INSERT INTO `icons` VALUES (47, 'Element', 1, 'el-icon-video-camera-solid', NULL, NULL);
INSERT INTO `icons` VALUES (48, 'Element', 1, 'el-icon-video-camera', NULL, NULL);
INSERT INTO `icons` VALUES (49, 'Element', 1, 'el-icon-message-solid', NULL, NULL);
INSERT INTO `icons` VALUES (50, 'Element', 1, 'el-icon-bell', NULL, NULL);
INSERT INTO `icons` VALUES (51, 'Element', 1, 'el-icon-s-cooperation', NULL, NULL);
INSERT INTO `icons` VALUES (52, 'Element', 1, 'el-icon-s-order', NULL, NULL);
INSERT INTO `icons` VALUES (53, 'Element', 1, 'el-icon-s-platform', NULL, NULL);
INSERT INTO `icons` VALUES (54, 'Element', 1, 'el-icon-s-fold', NULL, NULL);
INSERT INTO `icons` VALUES (55, 'Element', 1, 'el-icon-s-unfold', NULL, NULL);
INSERT INTO `icons` VALUES (56, 'Element', 1, 'el-icon-s-operation', NULL, NULL);
INSERT INTO `icons` VALUES (57, 'Element', 1, 'el-icon-s-promotion', NULL, NULL);
INSERT INTO `icons` VALUES (58, 'Element', 1, 'el-icon-s-home', NULL, NULL);
INSERT INTO `icons` VALUES (59, 'Element', 1, 'el-icon-s-release', NULL, NULL);
INSERT INTO `icons` VALUES (60, 'Element', 1, 'el-icon-s-ticket', NULL, NULL);
INSERT INTO `icons` VALUES (61, 'Element', 1, 'el-icon-s-management', NULL, NULL);
INSERT INTO `icons` VALUES (62, 'Element', 1, 'el-icon-s-open', NULL, NULL);
INSERT INTO `icons` VALUES (63, 'Element', 1, 'el-icon-s-shop', NULL, NULL);
INSERT INTO `icons` VALUES (64, 'Element', 1, 'el-icon-s-marketing', NULL, NULL);
INSERT INTO `icons` VALUES (65, 'Element', 1, 'el-icon-s-flag', NULL, NULL);
INSERT INTO `icons` VALUES (66, 'Element', 1, 'el-icon-s-comment', NULL, NULL);
INSERT INTO `icons` VALUES (67, 'Element', 1, 'el-icon-s-finance', NULL, NULL);
INSERT INTO `icons` VALUES (68, 'Element', 1, 'el-icon-s-claim', NULL, NULL);
INSERT INTO `icons` VALUES (69, 'Element', 1, 'el-icon-s-custom', NULL, NULL);
INSERT INTO `icons` VALUES (70, 'Element', 1, 'el-icon-s-opportunity', NULL, NULL);
INSERT INTO `icons` VALUES (71, 'Element', 1, 'el-icon-s-data', NULL, NULL);
INSERT INTO `icons` VALUES (72, 'Element', 1, 'el-icon-s-check', NULL, NULL);
INSERT INTO `icons` VALUES (73, 'Element', 1, 'el-icon-s-grid', NULL, NULL);
INSERT INTO `icons` VALUES (74, 'Element', 1, 'el-icon-menu', NULL, NULL);
INSERT INTO `icons` VALUES (75, 'Element', 1, 'el-icon-share', NULL, NULL);
INSERT INTO `icons` VALUES (76, 'Element', 1, 'el-icon-d-caret', NULL, NULL);
INSERT INTO `icons` VALUES (77, 'Element', 1, 'el-icon-caret-left', NULL, NULL);
INSERT INTO `icons` VALUES (78, 'Element', 1, 'el-icon-caret-right', NULL, NULL);
INSERT INTO `icons` VALUES (79, 'Element', 1, 'el-icon-caret-bottom', NULL, NULL);
INSERT INTO `icons` VALUES (80, 'Element', 1, 'el-icon-caret-top', NULL, NULL);
INSERT INTO `icons` VALUES (81, 'Element', 1, 'el-icon-bottom-left', NULL, NULL);
INSERT INTO `icons` VALUES (82, 'Element', 1, 'el-icon-bottom-right', NULL, NULL);
INSERT INTO `icons` VALUES (83, 'Element', 1, 'el-icon-back', NULL, NULL);
INSERT INTO `icons` VALUES (84, 'Element', 1, 'el-icon-right', NULL, NULL);
INSERT INTO `icons` VALUES (85, 'Element', 1, 'el-icon-bottom', NULL, NULL);
INSERT INTO `icons` VALUES (86, 'Element', 1, 'el-icon-top', NULL, NULL);
INSERT INTO `icons` VALUES (87, 'Element', 1, 'el-icon-top-left', NULL, NULL);
INSERT INTO `icons` VALUES (88, 'Element', 1, 'el-icon-top-right', NULL, NULL);
INSERT INTO `icons` VALUES (89, 'Element', 1, 'el-icon-arrow-left', NULL, NULL);
INSERT INTO `icons` VALUES (90, 'Element', 1, 'el-icon-arrow-right', NULL, NULL);
INSERT INTO `icons` VALUES (91, 'Element', 1, 'el-icon-arrow-down', NULL, NULL);
INSERT INTO `icons` VALUES (92, 'Element', 1, 'el-icon-arrow-up', NULL, NULL);
INSERT INTO `icons` VALUES (93, 'Element', 1, 'el-icon-d-arrow-left', NULL, NULL);
INSERT INTO `icons` VALUES (94, 'Element', 1, 'el-icon-d-arrow-right', NULL, NULL);
INSERT INTO `icons` VALUES (95, 'Element', 1, 'el-icon-video-pause', NULL, NULL);
INSERT INTO `icons` VALUES (96, 'Element', 1, 'el-icon-video-play', NULL, NULL);
INSERT INTO `icons` VALUES (97, 'Element', 1, 'el-icon-refresh', NULL, NULL);
INSERT INTO `icons` VALUES (98, 'Element', 1, 'el-icon-refresh-right', NULL, NULL);
INSERT INTO `icons` VALUES (99, 'Element', 1, 'el-icon-refresh-left', NULL, NULL);
INSERT INTO `icons` VALUES (100, 'Element', 1, 'el-icon-finished', NULL, NULL);
INSERT INTO `icons` VALUES (101, 'Element', 1, 'el-icon-sort', NULL, NULL);
INSERT INTO `icons` VALUES (102, 'Element', 1, 'el-icon-sort-up', NULL, NULL);
INSERT INTO `icons` VALUES (103, 'Element', 1, 'el-icon-sort-down', NULL, NULL);
INSERT INTO `icons` VALUES (104, 'Element', 1, 'el-icon-rank', NULL, NULL);
INSERT INTO `icons` VALUES (105, 'Element', 1, 'el-icon-loading', NULL, NULL);
INSERT INTO `icons` VALUES (106, 'Element', 1, 'el-icon-view', NULL, NULL);
INSERT INTO `icons` VALUES (107, 'Element', 1, 'el-icon-c-scale-to-original', NULL, NULL);
INSERT INTO `icons` VALUES (108, 'Element', 1, 'el-icon-date', NULL, NULL);
INSERT INTO `icons` VALUES (109, 'Element', 1, 'el-icon-edit', NULL, NULL);
INSERT INTO `icons` VALUES (110, 'Element', 1, 'el-icon-edit-outline', NULL, NULL);
INSERT INTO `icons` VALUES (111, 'Element', 1, 'el-icon-folder', NULL, NULL);
INSERT INTO `icons` VALUES (112, 'Element', 1, 'el-icon-folder-opened', NULL, NULL);
INSERT INTO `icons` VALUES (113, 'Element', 1, 'el-icon-folder-add', NULL, NULL);
INSERT INTO `icons` VALUES (114, 'Element', 1, 'el-icon-folder-remove', NULL, NULL);
INSERT INTO `icons` VALUES (115, 'Element', 1, 'el-icon-folder-delete', NULL, NULL);
INSERT INTO `icons` VALUES (116, 'Element', 1, 'el-icon-folder-checked', NULL, NULL);
INSERT INTO `icons` VALUES (117, 'Element', 1, 'el-icon-tickets', NULL, NULL);
INSERT INTO `icons` VALUES (118, 'Element', 1, 'el-icon-document-remove', NULL, NULL);
INSERT INTO `icons` VALUES (119, 'Element', 1, 'el-icon-document-delete', NULL, NULL);
INSERT INTO `icons` VALUES (120, 'Element', 1, 'el-icon-document-copy', NULL, NULL);
INSERT INTO `icons` VALUES (121, 'Element', 1, 'el-icon-document-checked', NULL, NULL);
INSERT INTO `icons` VALUES (122, 'Element', 1, 'el-icon-document', NULL, NULL);
INSERT INTO `icons` VALUES (123, 'Element', 1, 'el-icon-document-add', NULL, NULL);
INSERT INTO `icons` VALUES (124, 'Element', 1, 'el-icon-printer', NULL, NULL);
INSERT INTO `icons` VALUES (125, 'Element', 1, 'el-icon-paperclip', NULL, NULL);
INSERT INTO `icons` VALUES (126, 'Element', 1, 'el-icon-takeaway-box', NULL, NULL);
INSERT INTO `icons` VALUES (127, 'Element', 1, 'el-icon-search', NULL, NULL);
INSERT INTO `icons` VALUES (128, 'Element', 1, 'el-icon-monitor', NULL, NULL);
INSERT INTO `icons` VALUES (129, 'Element', 1, 'el-icon-attract', NULL, NULL);
INSERT INTO `icons` VALUES (130, 'Element', 1, 'el-icon-mobile', NULL, NULL);
INSERT INTO `icons` VALUES (131, 'Element', 1, 'el-icon-scissors', NULL, NULL);
INSERT INTO `icons` VALUES (132, 'Element', 1, 'el-icon-umbrella', NULL, NULL);
INSERT INTO `icons` VALUES (133, 'Element', 1, 'el-icon-headset', NULL, NULL);
INSERT INTO `icons` VALUES (134, 'Element', 1, 'el-icon-brush', NULL, NULL);
INSERT INTO `icons` VALUES (135, 'Element', 1, 'el-icon-mouse', NULL, NULL);
INSERT INTO `icons` VALUES (136, 'Element', 1, 'el-icon-coordinate', NULL, NULL);
INSERT INTO `icons` VALUES (137, 'Element', 1, 'el-icon-magic-stick', NULL, NULL);
INSERT INTO `icons` VALUES (138, 'Element', 1, 'el-icon-reading', NULL, NULL);
INSERT INTO `icons` VALUES (139, 'Element', 1, 'el-icon-data-line', NULL, NULL);
INSERT INTO `icons` VALUES (140, 'Element', 1, 'el-icon-data-board', NULL, NULL);
INSERT INTO `icons` VALUES (141, 'Element', 1, 'el-icon-pie-chart', NULL, NULL);
INSERT INTO `icons` VALUES (142, 'Element', 1, 'el-icon-data-analysis', NULL, NULL);
INSERT INTO `icons` VALUES (143, 'Element', 1, 'el-icon-collection-tag', NULL, NULL);
INSERT INTO `icons` VALUES (144, 'Element', 1, 'el-icon-film', NULL, NULL);
INSERT INTO `icons` VALUES (145, 'Element', 1, 'el-icon-suitcase', NULL, NULL);
INSERT INTO `icons` VALUES (146, 'Element', 1, 'el-icon-suitcase-1', NULL, NULL);
INSERT INTO `icons` VALUES (147, 'Element', 1, 'el-icon-receiving', NULL, NULL);
INSERT INTO `icons` VALUES (148, 'Element', 1, 'el-icon-collection', NULL, NULL);
INSERT INTO `icons` VALUES (149, 'Element', 1, 'el-icon-files', NULL, NULL);
INSERT INTO `icons` VALUES (150, 'Element', 1, 'el-icon-notebook-1', NULL, NULL);
INSERT INTO `icons` VALUES (151, 'Element', 1, 'el-icon-notebook-2', NULL, NULL);
INSERT INTO `icons` VALUES (152, 'Element', 1, 'el-icon-toilet-paper', NULL, NULL);
INSERT INTO `icons` VALUES (153, 'Element', 1, 'el-icon-office-building', NULL, NULL);
INSERT INTO `icons` VALUES (154, 'Element', 1, 'el-icon-school', NULL, NULL);
INSERT INTO `icons` VALUES (155, 'Element', 1, 'el-icon-table-lamp', NULL, NULL);
INSERT INTO `icons` VALUES (156, 'Element', 1, 'el-icon-house', NULL, NULL);
INSERT INTO `icons` VALUES (157, 'Element', 1, 'el-icon-no-smoking', NULL, NULL);
INSERT INTO `icons` VALUES (158, 'Element', 1, 'el-icon-smoking', NULL, NULL);
INSERT INTO `icons` VALUES (159, 'Element', 1, 'el-icon-shopping-cart-full', NULL, NULL);
INSERT INTO `icons` VALUES (160, 'Element', 1, 'el-icon-shopping-cart-1', NULL, NULL);
INSERT INTO `icons` VALUES (161, 'Element', 1, 'el-icon-shopping-cart-2', NULL, NULL);
INSERT INTO `icons` VALUES (162, 'Element', 1, 'el-icon-shopping-bag-1', NULL, NULL);
INSERT INTO `icons` VALUES (163, 'Element', 1, 'el-icon-shopping-bag-2', NULL, NULL);
INSERT INTO `icons` VALUES (164, 'Element', 1, 'el-icon-sold-out', NULL, NULL);
INSERT INTO `icons` VALUES (165, 'Element', 1, 'el-icon-sell', NULL, NULL);
INSERT INTO `icons` VALUES (166, 'Element', 1, 'el-icon-present', NULL, NULL);
INSERT INTO `icons` VALUES (167, 'Element', 1, 'el-icon-box', NULL, NULL);
INSERT INTO `icons` VALUES (168, 'Element', 1, 'el-icon-bank-card', NULL, NULL);
INSERT INTO `icons` VALUES (169, 'Element', 1, 'el-icon-money', NULL, NULL);
INSERT INTO `icons` VALUES (170, 'Element', 1, 'el-icon-coin', NULL, NULL);
INSERT INTO `icons` VALUES (171, 'Element', 1, 'el-icon-wallet', NULL, NULL);
INSERT INTO `icons` VALUES (172, 'Element', 1, 'el-icon-discount', NULL, NULL);
INSERT INTO `icons` VALUES (173, 'Element', 1, 'el-icon-price-tag', NULL, NULL);
INSERT INTO `icons` VALUES (174, 'Element', 1, 'el-icon-news', NULL, NULL);
INSERT INTO `icons` VALUES (175, 'Element', 1, 'el-icon-guide', NULL, NULL);
INSERT INTO `icons` VALUES (176, 'Element', 1, 'el-icon-male', NULL, NULL);
INSERT INTO `icons` VALUES (177, 'Element', 1, 'el-icon-female', NULL, NULL);
INSERT INTO `icons` VALUES (178, 'Element', 1, 'el-icon-thumb', NULL, NULL);
INSERT INTO `icons` VALUES (179, 'Element', 1, 'el-icon-cpu', NULL, NULL);
INSERT INTO `icons` VALUES (180, 'Element', 1, 'el-icon-link', NULL, NULL);
INSERT INTO `icons` VALUES (181, 'Element', 1, 'el-icon-connection', NULL, NULL);
INSERT INTO `icons` VALUES (182, 'Element', 1, 'el-icon-open', NULL, NULL);
INSERT INTO `icons` VALUES (183, 'Element', 1, 'el-icon-turn-off', NULL, NULL);
INSERT INTO `icons` VALUES (184, 'Element', 1, 'el-icon-set-up', NULL, NULL);
INSERT INTO `icons` VALUES (185, 'Element', 1, 'el-icon-chat-round', NULL, NULL);
INSERT INTO `icons` VALUES (186, 'Element', 1, 'el-icon-chat-line-round', NULL, NULL);
INSERT INTO `icons` VALUES (187, 'Element', 1, 'el-icon-chat-square', NULL, NULL);
INSERT INTO `icons` VALUES (188, 'Element', 1, 'el-icon-chat-dot-round', NULL, NULL);
INSERT INTO `icons` VALUES (189, 'Element', 1, 'el-icon-chat-dot-square', NULL, NULL);
INSERT INTO `icons` VALUES (190, 'Element', 1, 'el-icon-chat-line-square', NULL, NULL);
INSERT INTO `icons` VALUES (191, 'Element', 1, 'el-icon-message', NULL, NULL);
INSERT INTO `icons` VALUES (192, 'Element', 1, 'el-icon-postcard', NULL, NULL);
INSERT INTO `icons` VALUES (193, 'Element', 1, 'el-icon-position', NULL, NULL);
INSERT INTO `icons` VALUES (194, 'Element', 1, 'el-icon-turn-off-microphone', NULL, NULL);
INSERT INTO `icons` VALUES (195, 'Element', 1, 'el-icon-microphone', NULL, NULL);
INSERT INTO `icons` VALUES (196, 'Element', 1, 'el-icon-close-notification', NULL, NULL);
INSERT INTO `icons` VALUES (197, 'Element', 1, 'el-icon-bangzhu', NULL, NULL);
INSERT INTO `icons` VALUES (198, 'Element', 1, 'el-icon-time', NULL, NULL);
INSERT INTO `icons` VALUES (199, 'Element', 1, 'el-icon-odometer', NULL, NULL);
INSERT INTO `icons` VALUES (200, 'Element', 1, 'el-icon-crop', NULL, NULL);
INSERT INTO `icons` VALUES (201, 'Element', 1, 'el-icon-aim', NULL, NULL);
INSERT INTO `icons` VALUES (202, 'Element', 1, 'el-icon-switch-button', NULL, NULL);
INSERT INTO `icons` VALUES (203, 'Element', 1, 'el-icon-full-screen', NULL, NULL);
INSERT INTO `icons` VALUES (204, 'Element', 1, 'el-icon-copy-document', NULL, NULL);
INSERT INTO `icons` VALUES (205, 'Element', 1, 'el-icon-mic', NULL, NULL);
INSERT INTO `icons` VALUES (206, 'Element', 1, 'el-icon-stopwatch', NULL, NULL);
INSERT INTO `icons` VALUES (207, 'Element', 1, 'el-icon-medal-1', NULL, NULL);
INSERT INTO `icons` VALUES (208, 'Element', 1, 'el-icon-medal', NULL, NULL);
INSERT INTO `icons` VALUES (209, 'Element', 1, 'el-icon-trophy', NULL, NULL);
INSERT INTO `icons` VALUES (210, 'Element', 1, 'el-icon-trophy-1', NULL, NULL);
INSERT INTO `icons` VALUES (211, 'Element', 1, 'el-icon-first-aid-kit', NULL, NULL);
INSERT INTO `icons` VALUES (212, 'Element', 1, 'el-icon-discover', NULL, NULL);
INSERT INTO `icons` VALUES (213, 'Element', 1, 'el-icon-place', NULL, NULL);
INSERT INTO `icons` VALUES (214, 'Element', 1, 'el-icon-location', NULL, NULL);
INSERT INTO `icons` VALUES (215, 'Element', 1, 'el-icon-location-outline', NULL, NULL);
INSERT INTO `icons` VALUES (216, 'Element', 1, 'el-icon-location-information', NULL, NULL);
INSERT INTO `icons` VALUES (217, 'Element', 1, 'el-icon-add-location', NULL, NULL);
INSERT INTO `icons` VALUES (218, 'Element', 1, 'el-icon-delete-location', NULL, NULL);
INSERT INTO `icons` VALUES (219, 'Element', 1, 'el-icon-map-location', NULL, NULL);
INSERT INTO `icons` VALUES (220, 'Element', 1, 'el-icon-alarm-clock', NULL, NULL);
INSERT INTO `icons` VALUES (221, 'Element', 1, 'el-icon-timer', NULL, NULL);
INSERT INTO `icons` VALUES (222, 'Element', 1, 'el-icon-watch-1', NULL, NULL);
INSERT INTO `icons` VALUES (223, 'Element', 1, 'el-icon-watch', NULL, NULL);
INSERT INTO `icons` VALUES (224, 'Element', 1, 'el-icon-lock', NULL, NULL);
INSERT INTO `icons` VALUES (225, 'Element', 1, 'el-icon-unlock', NULL, NULL);
INSERT INTO `icons` VALUES (226, 'Element', 1, 'el-icon-key', NULL, NULL);
INSERT INTO `icons` VALUES (227, 'Element', 1, 'el-icon-service', NULL, NULL);
INSERT INTO `icons` VALUES (228, 'Element', 1, 'el-icon-mobile-phone', NULL, NULL);
INSERT INTO `icons` VALUES (229, 'Element', 1, 'el-icon-bicycle', NULL, NULL);
INSERT INTO `icons` VALUES (230, 'Element', 1, 'el-icon-truck', NULL, NULL);
INSERT INTO `icons` VALUES (231, 'Element', 1, 'el-icon-ship', NULL, NULL);
INSERT INTO `icons` VALUES (232, 'Element', 1, 'el-icon-basketball', NULL, NULL);
INSERT INTO `icons` VALUES (233, 'Element', 1, 'el-icon-football', NULL, NULL);
INSERT INTO `icons` VALUES (234, 'Element', 1, 'el-icon-soccer', NULL, NULL);
INSERT INTO `icons` VALUES (235, 'Element', 1, 'el-icon-baseball', NULL, NULL);
INSERT INTO `icons` VALUES (236, 'Element', 1, 'el-icon-wind-power', NULL, NULL);
INSERT INTO `icons` VALUES (237, 'Element', 1, 'el-icon-light-rain', NULL, NULL);
INSERT INTO `icons` VALUES (238, 'Element', 1, 'el-icon-lightning', NULL, NULL);
INSERT INTO `icons` VALUES (239, 'Element', 1, 'el-icon-heavy-rain', NULL, NULL);
INSERT INTO `icons` VALUES (240, 'Element', 1, 'el-icon-sunrise', NULL, NULL);
INSERT INTO `icons` VALUES (241, 'Element', 1, 'el-icon-sunrise-1', NULL, NULL);
INSERT INTO `icons` VALUES (242, 'Element', 1, 'el-icon-sunset', NULL, NULL);
INSERT INTO `icons` VALUES (243, 'Element', 1, 'el-icon-sunny', NULL, NULL);
INSERT INTO `icons` VALUES (244, 'Element', 1, 'el-icon-cloudy', NULL, NULL);
INSERT INTO `icons` VALUES (245, 'Element', 1, 'el-icon-partly-cloudy', NULL, NULL);
INSERT INTO `icons` VALUES (246, 'Element', 1, 'el-icon-cloudy-and-sunny', NULL, NULL);
INSERT INTO `icons` VALUES (247, 'Element', 1, 'el-icon-moon', NULL, NULL);
INSERT INTO `icons` VALUES (248, 'Element', 1, 'el-icon-moon-night', NULL, NULL);
INSERT INTO `icons` VALUES (249, 'Element', 1, 'el-icon-dish', NULL, NULL);
INSERT INTO `icons` VALUES (250, 'Element', 1, 'el-icon-dish-1', NULL, NULL);
INSERT INTO `icons` VALUES (251, 'Element', 1, 'el-icon-food', NULL, NULL);
INSERT INTO `icons` VALUES (252, 'Element', 1, 'el-icon-chicken', NULL, NULL);
INSERT INTO `icons` VALUES (253, 'Element', 1, 'el-icon-fork-spoon', NULL, NULL);
INSERT INTO `icons` VALUES (254, 'Element', 1, 'el-icon-knife-fork', NULL, NULL);
INSERT INTO `icons` VALUES (255, 'Element', 1, 'el-icon-burger', NULL, NULL);
INSERT INTO `icons` VALUES (256, 'Element', 1, 'el-icon-tableware', NULL, NULL);
INSERT INTO `icons` VALUES (257, 'Element', 1, 'el-icon-sugar', NULL, NULL);
INSERT INTO `icons` VALUES (258, 'Element', 1, 'el-icon-dessert', NULL, NULL);
INSERT INTO `icons` VALUES (259, 'Element', 1, 'el-icon-ice-cream', NULL, NULL);
INSERT INTO `icons` VALUES (260, 'Element', 1, 'el-icon-hot-water', NULL, NULL);
INSERT INTO `icons` VALUES (261, 'Element', 1, 'el-icon-water-cup', NULL, NULL);
INSERT INTO `icons` VALUES (262, 'Element', 1, 'el-icon-coffee-cup', NULL, NULL);
INSERT INTO `icons` VALUES (263, 'Element', 1, 'el-icon-cold-drink', NULL, NULL);
INSERT INTO `icons` VALUES (264, 'Element', 1, 'el-icon-goblet', NULL, NULL);
INSERT INTO `icons` VALUES (265, 'Element', 1, 'el-icon-goblet-full', NULL, NULL);
INSERT INTO `icons` VALUES (266, 'Element', 1, 'el-icon-goblet-square', NULL, NULL);
INSERT INTO `icons` VALUES (267, 'Element', 1, 'el-icon-goblet-square-full', NULL, NULL);
INSERT INTO `icons` VALUES (268, 'Element', 1, 'el-icon-refrigerator', NULL, NULL);
INSERT INTO `icons` VALUES (269, 'Element', 1, 'el-icon-grape', NULL, NULL);
INSERT INTO `icons` VALUES (270, 'Element', 1, 'el-icon-watermelon', NULL, NULL);
INSERT INTO `icons` VALUES (271, 'Element', 1, 'el-icon-cherry', NULL, NULL);
INSERT INTO `icons` VALUES (272, 'Element', 1, 'el-icon-apple', NULL, NULL);
INSERT INTO `icons` VALUES (273, 'Element', 1, 'el-icon-pear', NULL, NULL);
INSERT INTO `icons` VALUES (274, 'Element', 1, 'el-icon-orange', NULL, NULL);
INSERT INTO `icons` VALUES (275, 'Element', 1, 'el-icon-coffee', NULL, NULL);
INSERT INTO `icons` VALUES (276, 'Element', 1, 'el-icon-ice-tea', NULL, NULL);
INSERT INTO `icons` VALUES (277, 'Element', 1, 'el-icon-ice-drink', NULL, NULL);
INSERT INTO `icons` VALUES (278, 'Element', 1, 'el-icon-milk-tea', NULL, NULL);
INSERT INTO `icons` VALUES (279, 'Element', 1, 'el-icon-potato-strips', NULL, NULL);
INSERT INTO `icons` VALUES (280, 'Element', 1, 'el-icon-lollipop', NULL, NULL);
INSERT INTO `icons` VALUES (281, 'Element', 1, 'el-icon-ice-cream-square', NULL, NULL);
INSERT INTO `icons` VALUES (282, 'Element', 1, 'el-icon-ice-cream-round', NULL, NULL);
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
  PRIMARY KEY (`menu_element_id`),
  KEY `menu_id` (`menu_id`),
  KEY `element_id` (`element_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单元素关联表';

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
  `deleted_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `created_person` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) DEFAULT NULL COMMENT '更新人',
  `deleted_person` varchar(255) DEFAULT NULL COMMENT '删除人',
  PRIMARY KEY (`operate_power_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作权限表';

-- ----------------------------
-- Table structure for organ
-- ----------------------------
DROP TABLE IF EXISTS `organ`;
CREATE TABLE `organ` (
  `organ_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `organ_name` varchar(128) DEFAULT NULL,
  `organ_type` tinyint(2) DEFAULT NULL,
  `order_code` tinyint(3) DEFAULT NULL,
  `organ_parent_id` int(11) DEFAULT NULL,
  `caption` varchar(128) DEFAULT NULL,
  `disabled` tinyint(2) DEFAULT '0' COMMENT '禁用 0 正常 1 禁用',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `deleted_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `created_person` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) DEFAULT NULL COMMENT '更新人',
  `deleted_person` varchar(255) DEFAULT NULL COMMENT '删除人',
  PRIMARY KEY (`organ_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组织表';

-- ----------------------------
-- Table structure for organ_groups
-- ----------------------------
DROP TABLE IF EXISTS `organ_groups`;
CREATE TABLE `organ_groups` (
  `organ_group_id` bigint(20) NOT NULL,
  `organ_id` bigint(20) DEFAULT NULL COMMENT '组织id',
  `group_id` bigint(20) DEFAULT NULL COMMENT '分组id',
  PRIMARY KEY (`organ_group_id`),
  KEY `organ_id` (`organ_id`),
  KEY `group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组织分组关系表\r\n';

-- ----------------------------
-- Table structure for organ_user
-- ----------------------------
DROP TABLE IF EXISTS `organ_user`;
CREATE TABLE `organ_user` (
  `organ_user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `x_number` bigint(20) NOT NULL COMMENT '用户唯一标示',
  `organ_id` bigint(20) NOT NULL COMMENT '组织id',
  PRIMARY KEY (`organ_user_id`),
  KEY `organ_id` (`organ_id`),
  KEY `user_id` (`x_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户组织管理表\r\n';

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `role_type` tinyint(4) DEFAULT NULL COMMENT '角色类型',
  `role_code` varchar(16) DEFAULT NULL,
  `disabled` tinyint(4) DEFAULT '0' COMMENT '禁用 0 正常 1 禁用',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `deleted_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `created_person` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) DEFAULT NULL COMMENT '更新人',
  `deleted_person` varchar(255) DEFAULT NULL COMMENT '删除人',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='角色表\r\n';

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, '上帝管理员', 1, 'ADMIN', 0, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `role` VALUES (2, '测试用户1', 3, 'test_user_1', 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `role` VALUES (3, '测试用户2', 3, 'test_user_2', 1, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for role_groups
-- ----------------------------
DROP TABLE IF EXISTS `role_groups`;
CREATE TABLE `role_groups` (
  `role_groups_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `group_id` bigint(20) NOT NULL COMMENT '分组id',
  PRIMARY KEY (`role_groups_id`),
  KEY `role_id` (`role_id`),
  KEY `group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色分组关系表';

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `role_menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `menu_code` varchar(64) DEFAULT NULL COMMENT '菜单标识',
  PRIMARY KEY (`role_menu_id`) USING BTREE,
  KEY `user_id` (`menu_code`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='角色用户关系表\r\n';

-- ----------------------------
-- Records of role_menu
-- ----------------------------
BEGIN;
INSERT INTO `role_menu` VALUES (2, 1, 'm_user');
INSERT INTO `role_menu` VALUES (3, 1, 'm_sys_mange');
INSERT INTO `role_menu` VALUES (4, 1, 'm_company_m');
INSERT INTO `role_menu` VALUES (5, 1, 'form');
INSERT INTO `role_menu` VALUES (6, 1, 'm_organ');
INSERT INTO `role_menu` VALUES (7, 1, 'm_organ_mange');
INSERT INTO `role_menu` VALUES (8, 1, 'm_menu');
INSERT INTO `role_menu` VALUES (9, 1, 'm_role');
COMMIT;

-- ----------------------------
-- Table structure for role_operate_power
-- ----------------------------
DROP TABLE IF EXISTS `role_operate_power`;
CREATE TABLE `role_operate_power` (
  `role_power_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `operate_power_id` bigint(20) NOT NULL COMMENT '操作权限id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`role_power_id`),
  KEY `operate_power_id` (`operate_power_id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色操作权限表';

-- ----------------------------
-- Table structure for role_user
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
  `role_user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `x_number` bigint(20) NOT NULL COMMENT '用户标识',
  PRIMARY KEY (`role_user_id`),
  KEY `user_id` (`x_number`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='角色用户关系表\r\n';

-- ----------------------------
-- Records of role_user
-- ----------------------------
BEGIN;
INSERT INTO `role_user` VALUES (1, 1, 10000);
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

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
  `deleted_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `created_person` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) DEFAULT NULL COMMENT '更新人',
  `deleted_person` varchar(255) DEFAULT NULL COMMENT '删除人',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户基本表';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 10000, '18668927276', '$2a$10$aa9u3R3zjqNx09BqfMM1QeCfLqWG543zMRoum.ZIm2ngugrQsUEJi', 1, 1, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL);
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
  `deleted_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `created_person` varchar(255) DEFAULT NULL COMMENT '创建人',
  `updated_person` varchar(255) DEFAULT NULL COMMENT '更新人',
  `deleted_person` varchar(255) DEFAULT NULL COMMENT '删除人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_detail
-- ----------------------------
BEGIN;
INSERT INTO `user_detail` VALUES (1, 10000, '谢炎', '工程师', '1', '2020-09-03 23:09:08', 1, NULL, '1', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for user_file
-- ----------------------------
DROP TABLE IF EXISTS `user_file`;
CREATE TABLE `user_file` (
  `user_file_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `file_id` bigint(11) DEFAULT NULL COMMENT '文件id',
  `x_number` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`user_file_id`),
  KEY `file_id` (`file_id`),
  KEY `user_id` (`x_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户文件表\r\n';

-- ----------------------------
-- Table structure for user_groups
-- ----------------------------
DROP TABLE IF EXISTS `user_groups`;
CREATE TABLE `user_groups` (
  `user_group_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `x_number` bigint(20) NOT NULL COMMENT '用户唯一编号',
  `group_id` bigint(20) NOT NULL COMMENT '分组id',
  PRIMARY KEY (`user_group_id`),
  KEY `group_id` (`group_id`),
  KEY `user_id` (`x_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户分组表\r\n';

SET FOREIGN_KEY_CHECKS = 1;
