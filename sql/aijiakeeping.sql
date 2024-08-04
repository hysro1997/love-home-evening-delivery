/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50741
 Source Host           : localhost:3306
 Source Schema         : aijiakeeping

 Target Server Type    : MySQL
 Target Server Version : 50741
 File Encoding         : 65001

 Date: 04/08/2024 17:17:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for aj_base_check_in
-- ----------------------------
DROP TABLE IF EXISTS `aj_base_check_in`;
CREATE TABLE `aj_base_check_in`  (
  `base_check_in_id` int(11) NOT NULL AUTO_INCREMENT,
  `base_check_in_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考勤名称',
  `base_check_in_students` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考勤学生的id数组',
  `base_check_in_teachers` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考勤教师的id数组',
  `base_check_in_begin_date` date NULL DEFAULT NULL COMMENT '考勤开始日期',
  `base_check_in_end_date` date NULL DEFAULT NULL COMMENT '考勤结束日期',
  `base_check_in_days_one_week` int(2) NULL DEFAULT NULL COMMENT '每周考勤天数',
  `base_check_in_sum_days` int(3) NULL DEFAULT NULL COMMENT '考勤总天数',
  `base_check_in_weeks` int(2) NULL DEFAULT NULL COMMENT '考勤周数',
  `base_check_in_status` tinyint(1) NULL DEFAULT NULL COMMENT '考勤状态（0默认，1考勤中，2考勤结束）',
  PRIMARY KEY (`base_check_in_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '考勤总表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aj_base_check_in
-- ----------------------------

-- ----------------------------
-- Table structure for aj_cost_template
-- ----------------------------
DROP TABLE IF EXISTS `aj_cost_template`;
CREATE TABLE `aj_cost_template`  (
  `cost_template_id` int(11) NOT NULL AUTO_INCREMENT,
  `cost_template_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模板名次',
  `cost_fee_per_day` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '每天费用',
  `cost_use_fee_per_day` tinyint(1) NULL DEFAULT NULL COMMENT '使用日收费（0不用，1用）',
  `cost_fee_per_lesson` decimal(10, 2) NULL DEFAULT NULL COMMENT '课时费',
  `cost_use_fee_per_lesson` tinyint(1) NULL DEFAULT NULL COMMENT '使用课时费（0不用，1用）',
  `cost_food_fee` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '伙食费',
  `cost_textbooks_fee` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '材料费',
  `cost_fee_per_month` decimal(10, 2) NULL DEFAULT NULL COMMENT '每月收费',
  `cost_use_fee_per_month` tinyint(1) NULL DEFAULT NULL COMMENT '使用每月收费（0不用，1用）',
  `cost_use_grade` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '适合哪个年级用',
  `cost_default` tinyint(1) NULL DEFAULT 0 COMMENT '默认（0不默认，1默认）',
  PRIMARY KEY (`cost_template_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '费用模板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aj_cost_template
-- ----------------------------

-- ----------------------------
-- Table structure for aj_grade
-- ----------------------------
DROP TABLE IF EXISTS `aj_grade`;
CREATE TABLE `aj_grade`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '年级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '年级' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aj_grade
-- ----------------------------

-- ----------------------------
-- Table structure for aj_salary_bill
-- ----------------------------
DROP TABLE IF EXISTS `aj_salary_bill`;
CREATE TABLE `aj_salary_bill`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `teacher_id` int(11) NULL DEFAULT NULL COMMENT '教师id',
  `teacher_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '教师姓名',
  `teacher_check_in_statistci_id` int(11) NULL DEFAULT NULL COMMENT '教师考勤统计表id',
  `check_in_begin_date` date NULL DEFAULT NULL COMMENT '考勤开始日期',
  `check_in_end_date` date NULL DEFAULT NULL COMMENT '考勤结束日期',
  `check_in_sum_days` int(3) NULL DEFAULT NULL COMMENT '考勤总天数',
  `check_in_times` int(3) NULL DEFAULT NULL COMMENT '考勤次数',
  `leave_days` int(2) NULL DEFAULT NULL COMMENT '请假天数',
  `on_duty_times` int(2) NULL DEFAULT NULL COMMENT '值班次数',
  `on_duty_pension` decimal(10, 2) NULL DEFAULT NULL COMMENT '值班补助',
  `full_check_in` tinyint(1) NULL DEFAULT NULL COMMENT '全勤（0全勤，1未全勤）',
  `full_check_in_pension` decimal(10, 2) NULL DEFAULT NULL COMMENT '全勤奖',
  `other_pension` decimal(10, 2) NULL DEFAULT NULL COMMENT '其他补助',
  `deduck_money` decimal(10, 2) NULL DEFAULT NULL COMMENT '工资扣除',
  `acutal_salary` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际工资',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '工资明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aj_salary_bill
-- ----------------------------

-- ----------------------------
-- Table structure for aj_salary_template
-- ----------------------------
DROP TABLE IF EXISTS `aj_salary_template`;
CREATE TABLE `aj_salary_template`  (
  `salary_id` int(11) NOT NULL AUTO_INCREMENT,
  `salary_template_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工资模板名次',
  `salary_per_month` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '月工资',
  `salary_use_per_month` tinyint(1) NULL DEFAULT NULL COMMENT '使用月工资（0不用，1用）',
  `salary_per_day` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '日工资',
  `salary_user_per_day` tinyint(1) NULL DEFAULT NULL COMMENT '使用日工资（0不用，1用）',
  `salary_on_duty` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '值班费',
  `salary_off_duty_fee` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '请假工资',
  `salary_default` tinyint(1) NULL DEFAULT 0 COMMENT '默认（0不默认，1默认）',
  `salary_all_duty` decimal(10, 2) NULL DEFAULT NULL COMMENT '全勤奖',
  PRIMARY KEY (`salary_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '老师工资模板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aj_salary_template
-- ----------------------------

-- ----------------------------
-- Table structure for aj_school
-- ----------------------------
DROP TABLE IF EXISTS `aj_school`;
CREATE TABLE `aj_school`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `school_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学校',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '学校' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aj_school
-- ----------------------------

-- ----------------------------
-- Table structure for aj_student_bill
-- ----------------------------
DROP TABLE IF EXISTS `aj_student_bill`;
CREATE TABLE `aj_student_bill`  (
  `id` int(11) NOT NULL COMMENT 'id',
  `student_id` int(11) NULL DEFAULT NULL COMMENT '学生id',
  `student_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `check_in_statistic_id` int(11) NULL DEFAULT NULL COMMENT '考勤统计表id',
  `check_in_begin_date` date NULL DEFAULT NULL COMMENT '考勤开始日期',
  `check_in_end_date` date NULL DEFAULT NULL COMMENT '考勤结束日期',
  `check_in_times` int(3) NULL DEFAULT NULL COMMENT '考勤次数',
  `leave_times` int(2) NULL DEFAULT NULL COMMENT '请假次数',
  `count_as_check_in_times` int(2) NULL DEFAULT NULL COMMENT '视同考勤次数',
  `per_month_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '每月收费',
  `per_day_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '每天收费',
  `textbook_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '资料费',
  `food_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '伙食费',
  `bill_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '账单费用',
  `coupon` decimal(10, 2) NULL DEFAULT NULL COMMENT '优惠金额',
  `actual_per_month_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际每月收费',
  `actual_per_day_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际每日收费',
  `actual_textbook_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际资料费',
  `actual_food_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际伙食费',
  `acutal_bill_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际账单费用',
  `bill_status` tinyint(1) NULL DEFAULT NULL COMMENT '缴费状态（0未缴费，1已缴费）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '学生账单明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aj_student_bill
-- ----------------------------

-- ----------------------------
-- Table structure for aj_student_check_in
-- ----------------------------
DROP TABLE IF EXISTS `aj_student_check_in`;
CREATE TABLE `aj_student_check_in`  (
  `student_check_in_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NULL DEFAULT NULL COMMENT '学生id',
  `base_check_in_id` int(11) NULL DEFAULT NULL COMMENT '对应考勤总表id',
  `check_in_student_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `check_in_student_grade` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学生年级',
  `check_in_date` date NULL DEFAULT NULL COMMENT '签到日期',
  `check_in_status` tinyint(1) NULL DEFAULT NULL COMMENT '签到状态（0未到，1签到，2请假）',
  `check_in_datetime` datetime(0) NULL DEFAULT NULL COMMENT '签到时间',
  PRIMARY KEY (`student_check_in_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '学生考勤表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aj_student_check_in
-- ----------------------------

-- ----------------------------
-- Table structure for aj_student_check_in_statistic
-- ----------------------------
DROP TABLE IF EXISTS `aj_student_check_in_statistic`;
CREATE TABLE `aj_student_check_in_statistic`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `base_check_in_id` int(11) NULL DEFAULT NULL COMMENT '对应考勤总表id',
  `student_id` int(11) NULL DEFAULT NULL COMMENT '学生id',
  `student_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `check_in_sum_days` int(3) NULL DEFAULT NULL COMMENT '考勤总天数',
  `check_in_times` int(3) NULL DEFAULT NULL COMMENT '考勤次数',
  `check_in_begin_date` date NULL DEFAULT NULL COMMENT '考勤开始日期',
  `check_in_end_date` date NULL DEFAULT NULL COMMENT '考勤结束日期',
  `leave_days` int(2) NULL DEFAULT NULL COMMENT '请假天数',
  `bill_status` tinyint(1) NULL DEFAULT NULL COMMENT '账单状态（0未生成，1已生成）',
  `payment_status` tinyint(1) NULL DEFAULT NULL COMMENT '缴费状态（0未缴费，1已缴费）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '考勤统计表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aj_student_check_in_statistic
-- ----------------------------

-- ----------------------------
-- Table structure for aj_student_info
-- ----------------------------
DROP TABLE IF EXISTS `aj_student_info`;
CREATE TABLE `aj_student_info`  (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学生姓名（可重复，建议添加后缀）',
  `student_gender` tinyint(1) NULL DEFAULT 0 COMMENT '学生性别（0默认，1男，2女）',
  `student_school` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学生学校',
  `student_grade` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学生年级',
  `student_class` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学生班级',
  `student_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `student_face` blob NULL COMMENT '人脸识别base64',
  `student_source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学生来源（上门，招生，推荐）',
  `student_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `student_status` tinyint(1) NULL DEFAULT 0 COMMENT '学生状态（0在学，1不学）',
  PRIMARY KEY (`student_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '学生信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aj_student_info
-- ----------------------------

-- ----------------------------
-- Table structure for aj_teacher_check_in
-- ----------------------------
DROP TABLE IF EXISTS `aj_teacher_check_in`;
CREATE TABLE `aj_teacher_check_in`  (
  `teacher_check_in_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `teacher_id` int(11) NULL DEFAULT NULL COMMENT '教师id',
  `teacher_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '教师姓名',
  `check_in_date` date NULL DEFAULT NULL COMMENT '签到日期',
  `check_in_status` tinyint(1) NULL DEFAULT NULL COMMENT '考勤状态（0未到，1签到，2迟到，3请假）',
  `check_in_datetime` datetime(0) NULL DEFAULT NULL COMMENT '签到时间',
  `check_in_day_on_duty_times` int(1) NULL DEFAULT NULL COMMENT '当日值班次数',
  PRIMARY KEY (`teacher_check_in_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '员工考勤表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aj_teacher_check_in
-- ----------------------------

-- ----------------------------
-- Table structure for aj_teacher_check_in_statistic
-- ----------------------------
DROP TABLE IF EXISTS `aj_teacher_check_in_statistic`;
CREATE TABLE `aj_teacher_check_in_statistic`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `base_check_in_id` int(11) NULL DEFAULT NULL COMMENT '对应考勤总表id',
  `teacher_id` int(11) NULL DEFAULT NULL COMMENT '教师id',
  `teacher_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '教师姓名',
  `check_in_sum_days` int(3) NULL DEFAULT NULL COMMENT '考勤总天数',
  `check_in_times` int(3) NULL DEFAULT NULL COMMENT '考勤次数',
  `check_in_begin_date` date NULL DEFAULT NULL COMMENT '考勤开始日期',
  `check_in_end_date` date NULL DEFAULT NULL COMMENT '考勤结束日期',
  `leave_days` int(2) NULL DEFAULT NULL COMMENT '请假天数',
  `on_duty_times` int(3) NULL DEFAULT NULL COMMENT '值班次数',
  `bill_status` tinyint(1) NULL DEFAULT NULL COMMENT '账单状态（0未生成，1已生成）',
  `payment_status` tinyint(1) NULL DEFAULT NULL COMMENT '工资付款状态（0未付，1已付）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '教师考勤汇总表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aj_teacher_check_in_statistic
-- ----------------------------

-- ----------------------------
-- Table structure for aj_teacher_info
-- ----------------------------
DROP TABLE IF EXISTS `aj_teacher_info`;
CREATE TABLE `aj_teacher_info`  (
  `teacher_id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '教师姓名（可重复，建议添加后缀）',
  `teacher_gender` tinyint(1) NULL DEFAULT 0 COMMENT '教师性别',
  `teacher_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '教师电话',
  `teacher_employ_type` tinyint(1) NULL DEFAULT NULL COMMENT '用工类别（0兼职，1全职，2暑假工）',
  `teacher_face` blob NULL COMMENT '教师人脸识别base64',
  `teacher_status` tinyint(1) NULL DEFAULT 0 COMMENT '教师状态（0在职，1离职）',
  PRIMARY KEY (`teacher_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '教师信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of aj_teacher_info
-- ----------------------------

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `tpl_web_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '前端模板类型（element-ui模版 element-plus模版）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES (1, 'aj_student_info', '学生信息表', NULL, NULL, 'AjStudentInfo', 'crud', 'element-ui', 'com.hysro.ajkeeping', 'info', 'studentInfo', '学生信息', 'aj', '0', '/', '{\"parentMenuId\":\"1061\"}', 'admin', '2024-08-04 13:39:51', '', '2024-08-04 17:01:13', '学生信息');
INSERT INTO `gen_table` VALUES (2, 'aj_base_check_in', '考勤总表', NULL, NULL, 'AjBaseCheckIn', 'crud', 'element-ui', 'com.hysro.ajkeeping', 'checkin', 'baseCheckin', '考勤总表', 'hysro', '0', '/', '{\"parentMenuId\":1063}', 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:20:52', NULL);
INSERT INTO `gen_table` VALUES (4, 'aj_salary_bill', '工资明细表', NULL, NULL, 'AjSalaryBill', 'crud', 'element-ui', 'com.hysro.ajkeeping', 'payment', 'teacherSalary', '工资明细', 'hysro', '0', '/', '{\"parentMenuId\":1064}', 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:58', NULL);
INSERT INTO `gen_table` VALUES (6, 'aj_student_bill', '学生账单明细', NULL, NULL, 'AjStudentBill', 'crud', 'element-ui', 'com.hysro.ajkeeping', 'payment', 'studentBill', '学生账单明细', 'hysro', '0', '/', '{\"parentMenuId\":1064}', 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:27', NULL);
INSERT INTO `gen_table` VALUES (7, 'aj_student_check_in', '学生考勤表', NULL, NULL, 'AjStudentCheckIn', 'crud', 'element-ui', 'com.hysro.ajkeeping', 'checkin', 'studentCheckin', '学生考勤', 'hysro', '0', '/', '{\"parentMenuId\":1063}', 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:21:50', NULL);
INSERT INTO `gen_table` VALUES (8, 'aj_student_check_in_statistic', '考勤统计表', NULL, NULL, 'AjStudentCheckInStatistic', 'crud', 'element-ui', 'com.hysro.ajkeeping', 'checkin', 'studentCheckinStatistic', '考勤统计', 'hysro', '0', '/', '{\"parentMenuId\":1063}', 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:23:53', NULL);
INSERT INTO `gen_table` VALUES (9, 'aj_teacher_check_in', '员工考勤表', NULL, NULL, 'AjTeacherCheckIn', 'crud', 'element-ui', 'com.hysro.ajkeeping', 'checkin', 'teacherCheckin', '员工考勤', 'hysro', '0', '/', '{\"parentMenuId\":1063}', 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:22:51', NULL);
INSERT INTO `gen_table` VALUES (10, 'aj_teacher_check_in_statistic', '教师考勤汇总表', NULL, NULL, 'AjTeacherCheckInStatistic', 'crud', 'element-ui', 'com.hysro.ajkeeping', 'checkin', 'teacherCheckinStatistic', '教师考勤汇总', 'hysro', '0', '/', '{\"parentMenuId\":1063}', 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:24:37', NULL);
INSERT INTO `gen_table` VALUES (11, 'aj_teacher_info', '教师信息表', NULL, NULL, 'AjTeacherInfo', 'crud', 'element-ui', 'com.hysro.ajkeeping', 'info', 'teacherInfo', '教师信息', 'ruoyi', '0', '/', '{\"parentMenuId\":\"1061\"}', 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:27:53', NULL);
INSERT INTO `gen_table` VALUES (12, 'aj_cost_template', '费用模板', NULL, NULL, 'AjCostTemplate', 'crud', 'element-ui', 'com.hysro.ajkeeping', 'bill', 'studentBillTemplate', '学生费用模板', 'hysro', '0', '/', '{\"parentMenuId\":1062}', 'admin', '2024-08-04 14:12:58', '', '2024-08-04 14:14:51', NULL);
INSERT INTO `gen_table` VALUES (13, 'aj_salary_template', '老师工资模板', NULL, NULL, 'AjSalaryTemplate', 'crud', 'element-ui', 'com.hysro.ajkeeping', 'bill', 'teacherBillTemplate', '老师工资模板', 'hysro', '0', '/', '{\"parentMenuId\":1062}', 'admin', '2024-08-04 14:15:32', '', '2024-08-04 14:16:37', NULL);
INSERT INTO `gen_table` VALUES (14, 'aj_grade', '年级', NULL, NULL, 'AjGrade', 'crud', 'element-ui', 'com.hysro.ajkeeping', 'schools', 'grade', '年级', 'hysro', '0', '/', '{\"parentMenuId\":1143}', 'admin', '2024-08-04 17:12:44', '', '2024-08-04 17:13:23', NULL);
INSERT INTO `gen_table` VALUES (15, 'aj_school', '学校', NULL, NULL, 'AjSchool', 'crud', 'element-ui', 'com.hysro.ajkeeping', 'schools', 'school', '学校', 'hysro', '0', '/', '{\"parentMenuId\":1143}', 'admin', '2024-08-04 17:12:44', '', '2024-08-04 17:13:39', NULL);

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` bigint(20) NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 152 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES (1, 1, 'student_id', 'id', 'int(11)', 'Long', 'studentId', '1', '1', '0', '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-08-04 13:39:51', '', '2024-08-04 17:01:13');
INSERT INTO `gen_table_column` VALUES (2, 1, 'student_name', '学生姓名（可重复，建议添加后缀）', 'varchar(255)', 'String', 'studentName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2024-08-04 13:39:51', '', '2024-08-04 17:01:13');
INSERT INTO `gen_table_column` VALUES (3, 1, 'student_gender', '学生性别（0默认，1男，2女）', 'tinyint(1)', 'Integer', 'studentGender', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'radio', '', 3, 'admin', '2024-08-04 13:39:51', '', '2024-08-04 17:01:13');
INSERT INTO `gen_table_column` VALUES (4, 1, 'student_school', '学生学校', 'varchar(255)', 'String', 'studentSchool', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'select', '', 4, 'admin', '2024-08-04 13:39:51', '', '2024-08-04 17:01:13');
INSERT INTO `gen_table_column` VALUES (5, 1, 'student_grade', '学生年级', 'varchar(255)', 'String', 'studentGrade', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 5, 'admin', '2024-08-04 13:39:51', '', '2024-08-04 17:01:13');
INSERT INTO `gen_table_column` VALUES (6, 1, 'student_class', '学生班级', 'varchar(255)', 'String', 'studentClass', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'select', '', 6, 'admin', '2024-08-04 13:39:51', '', '2024-08-04 17:01:13');
INSERT INTO `gen_table_column` VALUES (7, 1, 'student_phone', '联系电话', 'varchar(255)', 'String', 'studentPhone', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2024-08-04 13:39:51', '', '2024-08-04 17:01:13');
INSERT INTO `gen_table_column` VALUES (8, 1, 'student_face', '人脸识别base64', 'blob', 'String', 'studentFace', '0', '0', '0', '1', '1', '1', '1', 'EQ', NULL, '', 8, 'admin', '2024-08-04 13:39:51', '', '2024-08-04 17:01:13');
INSERT INTO `gen_table_column` VALUES (9, 1, 'student_source', '学生来源（上门，招生，推荐）', 'varchar(50)', 'String', 'studentSource', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'select', '', 9, 'admin', '2024-08-04 13:39:51', '', '2024-08-04 17:01:13');
INSERT INTO `gen_table_column` VALUES (10, 1, 'student_remark', '备注信息', 'varchar(255)', 'String', 'studentRemark', '0', '0', '0', '1', '1', '1', '0', 'EQ', 'textarea', '', 10, 'admin', '2024-08-04 13:39:51', '', '2024-08-04 17:01:13');
INSERT INTO `gen_table_column` VALUES (11, 1, 'student_status', '学生状态（0在学，1不学）', 'tinyint(1)', 'Integer', 'studentStatus', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'radio', '', 11, 'admin', '2024-08-04 13:39:51', '', '2024-08-04 17:01:13');
INSERT INTO `gen_table_column` VALUES (12, 2, 'base_check_in_id', 'id', 'int(11)', 'Long', 'baseCheckInId', '1', '1', '0', '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:20:52');
INSERT INTO `gen_table_column` VALUES (13, 2, 'base_check_in_name', '考勤名称', 'varchar(255)', 'String', 'baseCheckInName', '0', '0', '0', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:20:52');
INSERT INTO `gen_table_column` VALUES (14, 2, 'base_check_in_students', '考勤学生的id数组', 'varchar(1000)', 'String', 'baseCheckInStudents', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'textarea', '', 3, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:20:52');
INSERT INTO `gen_table_column` VALUES (15, 2, 'base_check_in_teachers', '考勤教师的id数组', 'varchar(255)', 'String', 'baseCheckInTeachers', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:20:52');
INSERT INTO `gen_table_column` VALUES (16, 2, 'base_check_in_begin_date', '考勤开始日期', 'date', 'Date', 'baseCheckInBeginDate', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'datetime', '', 5, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:20:52');
INSERT INTO `gen_table_column` VALUES (17, 2, 'base_check_in_end_date', '考勤结束日期', 'date', 'Date', 'baseCheckInEndDate', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'datetime', '', 6, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:20:52');
INSERT INTO `gen_table_column` VALUES (18, 2, 'base_check_in_days_one_week', '每周考勤天数', 'int(2)', 'Integer', 'baseCheckInDaysOneWeek', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:20:52');
INSERT INTO `gen_table_column` VALUES (19, 2, 'base_check_in_sum_days', '考勤总天数', 'int(3)', 'Integer', 'baseCheckInSumDays', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:20:52');
INSERT INTO `gen_table_column` VALUES (20, 2, 'base_check_in_weeks', '考勤周数', 'int(2)', 'Integer', 'baseCheckInWeeks', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:20:52');
INSERT INTO `gen_table_column` VALUES (21, 2, 'base_check_in_status', '考勤状态（0默认，1考勤中，2考勤结束）', 'tinyint(1)', 'Integer', 'baseCheckInStatus', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'radio', '', 10, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:20:52');
INSERT INTO `gen_table_column` VALUES (34, 4, 'id', 'id', 'int(11)', 'Long', 'id', '1', '1', '0', '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:58');
INSERT INTO `gen_table_column` VALUES (35, 4, 'teacher_id', '教师id', 'int(11)', 'Long', 'teacherId', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:58');
INSERT INTO `gen_table_column` VALUES (36, 4, 'teacher_name', '教师姓名', 'varchar(255)', 'String', 'teacherName', '0', '0', '0', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:58');
INSERT INTO `gen_table_column` VALUES (37, 4, 'teacher_check_in_statistci_id', '教师考勤统计表id', 'int(11)', 'Long', 'teacherCheckInStatistciId', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:58');
INSERT INTO `gen_table_column` VALUES (38, 4, 'check_in_begin_date', '考勤开始日期', 'date', 'Date', 'checkInBeginDate', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'datetime', '', 5, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:58');
INSERT INTO `gen_table_column` VALUES (39, 4, 'check_in_end_date', '考勤结束日期', 'date', 'Date', 'checkInEndDate', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'datetime', '', 6, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:58');
INSERT INTO `gen_table_column` VALUES (40, 4, 'check_in_sum_days', '考勤总天数', 'int(3)', 'Integer', 'checkInSumDays', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:58');
INSERT INTO `gen_table_column` VALUES (41, 4, 'check_in_times', '考勤次数', 'int(3)', 'Integer', 'checkInTimes', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:58');
INSERT INTO `gen_table_column` VALUES (42, 4, 'leave_days', '请假天数', 'int(2)', 'Integer', 'leaveDays', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:58');
INSERT INTO `gen_table_column` VALUES (43, 4, 'on_duty_times', '值班次数', 'int(2)', 'Integer', 'onDutyTimes', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:58');
INSERT INTO `gen_table_column` VALUES (44, 4, 'on_duty_pension', '值班补助', 'decimal(10,2)', 'BigDecimal', 'onDutyPension', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:58');
INSERT INTO `gen_table_column` VALUES (45, 4, 'full_check_in', '全勤（0全勤，1未全勤）', 'tinyint(1)', 'Integer', 'fullCheckIn', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:58');
INSERT INTO `gen_table_column` VALUES (46, 4, 'full_check_in_pension', '全勤奖', 'decimal(10,2)', 'BigDecimal', 'fullCheckInPension', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:58');
INSERT INTO `gen_table_column` VALUES (47, 4, 'other_pension', '其他补助', 'decimal(10,2)', 'BigDecimal', 'otherPension', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:58');
INSERT INTO `gen_table_column` VALUES (48, 4, 'deduck_money', '工资扣除', 'decimal(10,2)', 'BigDecimal', 'deduckMoney', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:58');
INSERT INTO `gen_table_column` VALUES (49, 4, 'acutal_salary', '实际工资', 'decimal(10,2)', 'BigDecimal', 'acutalSalary', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 16, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:58');
INSERT INTO `gen_table_column` VALUES (60, 6, 'id', 'id', 'int(11)', 'Long', 'id', '1', '0', '0', '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:27');
INSERT INTO `gen_table_column` VALUES (61, 6, 'student_id', '学生id', 'int(11)', 'Long', 'studentId', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:27');
INSERT INTO `gen_table_column` VALUES (62, 6, 'student_name', '学生姓名', 'varchar(255)', 'String', 'studentName', '0', '0', '0', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:27');
INSERT INTO `gen_table_column` VALUES (63, 6, 'check_in_statistic_id', '考勤统计表id', 'int(11)', 'Long', 'checkInStatisticId', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:27');
INSERT INTO `gen_table_column` VALUES (64, 6, 'check_in_begin_date', '考勤开始日期', 'date', 'Date', 'checkInBeginDate', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'datetime', '', 5, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:27');
INSERT INTO `gen_table_column` VALUES (65, 6, 'check_in_end_date', '考勤结束日期', 'date', 'Date', 'checkInEndDate', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'datetime', '', 6, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:27');
INSERT INTO `gen_table_column` VALUES (66, 6, 'check_in_times', '考勤次数', 'int(3)', 'Integer', 'checkInTimes', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:27');
INSERT INTO `gen_table_column` VALUES (67, 6, 'leave_times', '请假次数', 'int(2)', 'Integer', 'leaveTimes', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:27');
INSERT INTO `gen_table_column` VALUES (68, 6, 'count_as_check_in_times', '视同考勤次数', 'int(2)', 'Integer', 'countAsCheckInTimes', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:27');
INSERT INTO `gen_table_column` VALUES (69, 6, 'per_month_fee', '每月收费', 'decimal(10,2)', 'BigDecimal', 'perMonthFee', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:27');
INSERT INTO `gen_table_column` VALUES (70, 6, 'per_day_fee', '每天收费', 'decimal(10,2)', 'BigDecimal', 'perDayFee', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:27');
INSERT INTO `gen_table_column` VALUES (71, 6, 'textbook_fee', '资料费', 'decimal(10,2)', 'BigDecimal', 'textbookFee', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:27');
INSERT INTO `gen_table_column` VALUES (72, 6, 'food_fee', '伙食费', 'decimal(10,2)', 'BigDecimal', 'foodFee', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:27');
INSERT INTO `gen_table_column` VALUES (73, 6, 'bill_fee', '账单费用', 'decimal(10,2)', 'BigDecimal', 'billFee', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:27');
INSERT INTO `gen_table_column` VALUES (74, 6, 'coupon', '优惠金额', 'decimal(10,2)', 'BigDecimal', 'coupon', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:27');
INSERT INTO `gen_table_column` VALUES (75, 6, 'actual_per_month_fee', '实际每月收费', 'decimal(10,2)', 'BigDecimal', 'actualPerMonthFee', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 16, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:27');
INSERT INTO `gen_table_column` VALUES (76, 6, 'actual_per_day_fee', '实际每日收费', 'decimal(10,2)', 'BigDecimal', 'actualPerDayFee', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 17, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:27');
INSERT INTO `gen_table_column` VALUES (77, 6, 'actual_textbook_fee', '实际资料费', 'decimal(10,2)', 'BigDecimal', 'actualTextbookFee', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 18, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:27');
INSERT INTO `gen_table_column` VALUES (78, 6, 'actual_food_fee', '实际伙食费', 'decimal(10,2)', 'BigDecimal', 'actualFoodFee', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 19, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:27');
INSERT INTO `gen_table_column` VALUES (79, 6, 'acutal_bill_fee', '实际账单费用', 'decimal(10,2)', 'BigDecimal', 'acutalBillFee', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 20, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:27');
INSERT INTO `gen_table_column` VALUES (80, 6, 'bill_status', '缴费状态（0未缴费，1已缴费）', 'tinyint(1)', 'Integer', 'billStatus', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'radio', '', 21, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:26:27');
INSERT INTO `gen_table_column` VALUES (81, 7, 'student_check_in_id', 'id', 'int(11)', 'Long', 'studentCheckInId', '1', '1', '0', '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:21:50');
INSERT INTO `gen_table_column` VALUES (82, 7, 'student_id', '学生id', 'int(11)', 'Long', 'studentId', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:21:50');
INSERT INTO `gen_table_column` VALUES (83, 7, 'base_check_in_id', '对应考勤总表id', 'int(11)', 'Long', 'baseCheckInId', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:21:50');
INSERT INTO `gen_table_column` VALUES (84, 7, 'check_in_student_name', '学生姓名', 'varchar(255)', 'String', 'checkInStudentName', '0', '0', '0', '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:21:50');
INSERT INTO `gen_table_column` VALUES (85, 7, 'check_in_student_grade', '学生年级', 'varchar(255)', 'String', 'checkInStudentGrade', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:21:50');
INSERT INTO `gen_table_column` VALUES (86, 7, 'check_in_date', '签到日期', 'date', 'Date', 'checkInDate', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'datetime', '', 6, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:21:50');
INSERT INTO `gen_table_column` VALUES (87, 7, 'check_in_status', '签到状态（0未到，1签到，2请假）', 'tinyint(1)', 'Integer', 'checkInStatus', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'radio', '', 7, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:21:50');
INSERT INTO `gen_table_column` VALUES (88, 7, 'check_in_datetime', '签到时间', 'datetime', 'Date', 'checkInDatetime', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'datetime', '', 8, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:21:50');
INSERT INTO `gen_table_column` VALUES (89, 8, 'id', 'id', 'int(11)', 'Long', 'id', '1', '1', '0', '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:23:53');
INSERT INTO `gen_table_column` VALUES (90, 8, 'base_check_in_id', '对应考勤总表id', 'int(11)', 'Long', 'baseCheckInId', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:23:53');
INSERT INTO `gen_table_column` VALUES (91, 8, 'student_id', '学生id', 'int(11)', 'Long', 'studentId', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:23:53');
INSERT INTO `gen_table_column` VALUES (92, 8, 'student_name', '学生姓名', 'varchar(255)', 'String', 'studentName', '0', '0', '0', '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:23:53');
INSERT INTO `gen_table_column` VALUES (93, 8, 'check_in_sum_days', '考勤总天数', 'int(3)', 'Integer', 'checkInSumDays', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:23:53');
INSERT INTO `gen_table_column` VALUES (94, 8, 'check_in_times', '考勤次数', 'int(3)', 'Integer', 'checkInTimes', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:23:53');
INSERT INTO `gen_table_column` VALUES (95, 8, 'check_in_begin_date', '考勤开始日期', 'date', 'Date', 'checkInBeginDate', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'datetime', '', 7, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:23:53');
INSERT INTO `gen_table_column` VALUES (96, 8, 'check_in_end_date', '考勤结束日期', 'date', 'Date', 'checkInEndDate', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'datetime', '', 8, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:23:53');
INSERT INTO `gen_table_column` VALUES (97, 8, 'leave_days', '请假天数', 'int(2)', 'Integer', 'leaveDays', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:23:53');
INSERT INTO `gen_table_column` VALUES (98, 8, 'bill_status', '账单状态（0未生成，1已生成）', 'tinyint(1)', 'Integer', 'billStatus', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'radio', '', 10, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:23:53');
INSERT INTO `gen_table_column` VALUES (99, 8, 'payment_status', '缴费状态（0未缴费，1已缴费）', 'tinyint(1)', 'Integer', 'paymentStatus', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'radio', '', 11, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:23:53');
INSERT INTO `gen_table_column` VALUES (100, 9, 'teacher_check_in_id', 'id', 'int(11)', 'Long', 'teacherCheckInId', '1', '1', '0', '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:22:51');
INSERT INTO `gen_table_column` VALUES (101, 9, 'teacher_id', '教师id', 'int(11)', 'Long', 'teacherId', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:22:51');
INSERT INTO `gen_table_column` VALUES (102, 9, 'teacher_name', '教师姓名', 'varchar(255)', 'String', 'teacherName', '0', '0', '0', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:22:51');
INSERT INTO `gen_table_column` VALUES (103, 9, 'check_in_date', '签到日期', 'date', 'Date', 'checkInDate', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'datetime', '', 4, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:22:51');
INSERT INTO `gen_table_column` VALUES (104, 9, 'check_in_status', '考勤状态（0未到，1签到，2迟到，3请假）', 'tinyint(1)', 'Integer', 'checkInStatus', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'radio', '', 5, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:22:51');
INSERT INTO `gen_table_column` VALUES (105, 9, 'check_in_datetime', '签到时间', 'datetime', 'Date', 'checkInDatetime', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'datetime', '', 6, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:22:51');
INSERT INTO `gen_table_column` VALUES (106, 9, 'check_in_day_on_duty_times', '当日值班次数', 'int(1)', 'Integer', 'checkInDayOnDutyTimes', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:22:51');
INSERT INTO `gen_table_column` VALUES (107, 10, 'id', 'id', 'int(11)', 'Long', 'id', '1', '1', '0', '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:24:37');
INSERT INTO `gen_table_column` VALUES (108, 10, 'base_check_in_id', '对应考勤总表id', 'int(11)', 'Long', 'baseCheckInId', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:24:37');
INSERT INTO `gen_table_column` VALUES (109, 10, 'teacher_id', '教师id', 'int(11)', 'Long', 'teacherId', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:24:37');
INSERT INTO `gen_table_column` VALUES (110, 10, 'teacher_name', '教师姓名', 'varchar(255)', 'String', 'teacherName', '0', '0', '0', '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:24:37');
INSERT INTO `gen_table_column` VALUES (111, 10, 'check_in_sum_days', '考勤总天数', 'int(3)', 'Integer', 'checkInSumDays', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:24:37');
INSERT INTO `gen_table_column` VALUES (112, 10, 'check_in_times', '考勤次数', 'int(3)', 'Integer', 'checkInTimes', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:24:37');
INSERT INTO `gen_table_column` VALUES (113, 10, 'check_in_begin_date', '考勤开始日期', 'date', 'Date', 'checkInBeginDate', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'datetime', '', 7, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:24:37');
INSERT INTO `gen_table_column` VALUES (114, 10, 'check_in_end_date', '考勤结束日期', 'date', 'Date', 'checkInEndDate', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'datetime', '', 8, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:24:37');
INSERT INTO `gen_table_column` VALUES (115, 10, 'leave_days', '请假天数', 'int(2)', 'Integer', 'leaveDays', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:24:37');
INSERT INTO `gen_table_column` VALUES (116, 10, 'on_duty_times', '值班次数', 'int(3)', 'Integer', 'onDutyTimes', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:24:37');
INSERT INTO `gen_table_column` VALUES (117, 10, 'bill_status', '账单状态（0未生成，1已生成）', 'tinyint(1)', 'Integer', 'billStatus', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'radio', '', 11, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:24:37');
INSERT INTO `gen_table_column` VALUES (118, 10, 'payment_status', '工资付款状态（0未付，1已付）', 'tinyint(1)', 'Integer', 'paymentStatus', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'radio', '', 12, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:24:37');
INSERT INTO `gen_table_column` VALUES (119, 11, 'teacher_id', NULL, 'int(11)', 'Long', 'teacherId', '1', '1', '0', '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:27:53');
INSERT INTO `gen_table_column` VALUES (120, 11, 'teacher_name', '教师姓名（可重复，建议添加后缀）', 'varchar(255)', 'String', 'teacherName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:27:53');
INSERT INTO `gen_table_column` VALUES (121, 11, 'teacher_gender', '教师性别', 'tinyint(1)', 'Integer', 'teacherGender', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:27:53');
INSERT INTO `gen_table_column` VALUES (122, 11, 'teacher_phone', '教师电话', 'varchar(255)', 'String', 'teacherPhone', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:27:53');
INSERT INTO `gen_table_column` VALUES (123, 11, 'teacher_employ_type', '用工类别（0兼职，1全职，2暑假工）', 'tinyint(1)', 'Integer', 'teacherEmployType', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'select', '', 5, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:27:53');
INSERT INTO `gen_table_column` VALUES (124, 11, 'teacher_face', '教师人脸识别base64', 'blob', 'String', 'teacherFace', '0', '0', '0', '1', '1', '1', '1', 'EQ', NULL, '', 6, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:27:53');
INSERT INTO `gen_table_column` VALUES (125, 11, 'teacher_status', '教师状态（0在职，1离职）', 'tinyint(1)', 'Integer', 'teacherStatus', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'radio', '', 7, 'admin', '2024-08-04 13:40:03', '', '2024-08-04 14:27:53');
INSERT INTO `gen_table_column` VALUES (126, 12, 'cost_template_id', 'id', 'int(11)', 'Long', 'costTemplateId', '1', '1', '0', '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-08-04 14:12:58', '', '2024-08-04 14:14:51');
INSERT INTO `gen_table_column` VALUES (127, 12, 'cost_template_name', '模板名次', 'varchar(255)', 'String', 'costTemplateName', '0', '0', '0', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2024-08-04 14:12:58', '', '2024-08-04 14:14:51');
INSERT INTO `gen_table_column` VALUES (128, 12, 'cost_fee_per_day', '每天费用', 'decimal(10,2)', 'BigDecimal', 'costFeePerDay', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2024-08-04 14:12:58', '', '2024-08-04 14:14:51');
INSERT INTO `gen_table_column` VALUES (129, 12, 'cost_use_fee_per_day', '使用日收费（0不用，1用）', 'tinyint(1)', 'Integer', 'costUseFeePerDay', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2024-08-04 14:12:58', '', '2024-08-04 14:14:51');
INSERT INTO `gen_table_column` VALUES (130, 12, 'cost_fee_per_lesson', '课时费', 'decimal(10,2)', 'BigDecimal', 'costFeePerLesson', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2024-08-04 14:12:58', '', '2024-08-04 14:14:51');
INSERT INTO `gen_table_column` VALUES (131, 12, 'cost_use_fee_per_lesson', '使用课时费（0不用，1用）', 'tinyint(1)', 'Integer', 'costUseFeePerLesson', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2024-08-04 14:12:58', '', '2024-08-04 14:14:51');
INSERT INTO `gen_table_column` VALUES (132, 12, 'cost_food_fee', '伙食费', 'decimal(10,2)', 'BigDecimal', 'costFoodFee', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2024-08-04 14:12:58', '', '2024-08-04 14:14:51');
INSERT INTO `gen_table_column` VALUES (133, 12, 'cost_textbooks_fee', '材料费', 'decimal(10,2)', 'BigDecimal', 'costTextbooksFee', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2024-08-04 14:12:58', '', '2024-08-04 14:14:51');
INSERT INTO `gen_table_column` VALUES (134, 12, 'cost_fee_per_month', '每月收费', 'decimal(10,2)', 'BigDecimal', 'costFeePerMonth', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2024-08-04 14:12:58', '', '2024-08-04 14:14:51');
INSERT INTO `gen_table_column` VALUES (135, 12, 'cost_use_fee_per_month', '使用每月收费（0不用，1用）', 'tinyint(1)', 'Integer', 'costUseFeePerMonth', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2024-08-04 14:12:58', '', '2024-08-04 14:14:51');
INSERT INTO `gen_table_column` VALUES (136, 12, 'cost_use_grade', '适合哪个年级用', 'varchar(255)', 'String', 'costUseGrade', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2024-08-04 14:12:58', '', '2024-08-04 14:14:51');
INSERT INTO `gen_table_column` VALUES (137, 12, 'cost_default', '默认（0不默认，1默认）', 'tinyint(1)', 'Integer', 'costDefault', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2024-08-04 14:12:58', '', '2024-08-04 14:14:51');
INSERT INTO `gen_table_column` VALUES (138, 13, 'salary_id', 'id', 'int(11)', 'Long', 'salaryId', '1', '1', '0', '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-08-04 14:15:32', '', '2024-08-04 14:16:37');
INSERT INTO `gen_table_column` VALUES (139, 13, 'salary_template_name', '工资模板名次', 'varchar(255)', 'String', 'salaryTemplateName', '0', '0', '0', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2024-08-04 14:15:32', '', '2024-08-04 14:16:37');
INSERT INTO `gen_table_column` VALUES (140, 13, 'salary_per_month', '月工资', 'decimal(10,2)', 'BigDecimal', 'salaryPerMonth', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2024-08-04 14:15:32', '', '2024-08-04 14:16:37');
INSERT INTO `gen_table_column` VALUES (141, 13, 'salary_use_per_month', '使用月工资（0不用，1用）', 'tinyint(1)', 'Integer', 'salaryUsePerMonth', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2024-08-04 14:15:32', '', '2024-08-04 14:16:37');
INSERT INTO `gen_table_column` VALUES (142, 13, 'salary_per_day', '日工资', 'decimal(10,2)', 'BigDecimal', 'salaryPerDay', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2024-08-04 14:15:32', '', '2024-08-04 14:16:37');
INSERT INTO `gen_table_column` VALUES (143, 13, 'salary_user_per_day', '使用日工资（0不用，1用）', 'tinyint(1)', 'Integer', 'salaryUserPerDay', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2024-08-04 14:15:32', '', '2024-08-04 14:16:37');
INSERT INTO `gen_table_column` VALUES (144, 13, 'salary_on_duty', '值班费', 'decimal(10,2)', 'BigDecimal', 'salaryOnDuty', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2024-08-04 14:15:32', '', '2024-08-04 14:16:37');
INSERT INTO `gen_table_column` VALUES (145, 13, 'salary_off_duty_fee', '请假工资', 'decimal(10,2)', 'BigDecimal', 'salaryOffDutyFee', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2024-08-04 14:15:32', '', '2024-08-04 14:16:37');
INSERT INTO `gen_table_column` VALUES (146, 13, 'salary_default', '默认（0不默认，1默认）', 'tinyint(1)', 'Integer', 'salaryDefault', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2024-08-04 14:15:32', '', '2024-08-04 14:16:37');
INSERT INTO `gen_table_column` VALUES (147, 13, 'salary_all_duty', '全勤奖', 'decimal(10,2)', 'BigDecimal', 'salaryAllDuty', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2024-08-04 14:15:32', '', '2024-08-04 14:16:37');
INSERT INTO `gen_table_column` VALUES (148, 14, 'id', NULL, 'int(11)', 'Long', 'id', '1', '1', '0', '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-08-04 17:12:44', '', '2024-08-04 17:13:23');
INSERT INTO `gen_table_column` VALUES (149, 14, 'grade', '年级', 'varchar(255)', 'String', 'grade', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2024-08-04 17:12:44', '', '2024-08-04 17:13:23');
INSERT INTO `gen_table_column` VALUES (150, 15, 'id', NULL, 'int(11)', 'Long', 'id', '1', '1', '0', '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2024-08-04 17:12:44', '', '2024-08-04 17:13:39');
INSERT INTO `gen_table_column` VALUES (151, 15, 'school_name', '学校', 'varchar(255)', 'String', 'schoolName', '0', '0', '0', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2024-08-04 17:12:44', '', '2024-08-04 17:13:39');

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `blob_data` blob NULL COMMENT '存放持久化Trigger对象',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Blob类型的触发器表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `calendar_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日历名称',
  `calendar` blob NOT NULL COMMENT '存放持久化calendar对象',
  PRIMARY KEY (`sched_name`, `calendar_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日历信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `cron_expression` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'cron表达式',
  `time_zone_id` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '时区',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Cron类型的触发器表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `entry_id` varchar(95) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度器实例id',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `instance_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度器实例名',
  `fired_time` bigint(13) NOT NULL COMMENT '触发的时间',
  `sched_time` bigint(13) NOT NULL COMMENT '定时器制定的时间',
  `priority` int(11) NOT NULL COMMENT '优先级',
  `state` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态',
  `job_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务组名',
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否并发',
  `requests_recovery` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否接受恢复执行',
  PRIMARY KEY (`sched_name`, `entry_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '已触发的触发器表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `job_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务组名',
  `description` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '相关介绍',
  `job_class_name` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '执行任务类名称',
  `is_durable` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否持久化',
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否并发',
  `is_update_data` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否更新数据',
  `requests_recovery` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否接受恢复执行',
  `job_data` blob NULL COMMENT '存放持久化job对象',
  PRIMARY KEY (`sched_name`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务详细信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `lock_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '悲观锁名称',
  PRIMARY KEY (`sched_name`, `lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '存储的悲观锁信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  PRIMARY KEY (`sched_name`, `trigger_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '暂停的触发器表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `instance_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实例名称',
  `last_checkin_time` bigint(13) NOT NULL COMMENT '上次检查时间',
  `checkin_interval` bigint(13) NOT NULL COMMENT '检查间隔时间',
  PRIMARY KEY (`sched_name`, `instance_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '调度器状态表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `repeat_count` bigint(7) NOT NULL COMMENT '重复的次数统计',
  `repeat_interval` bigint(12) NOT NULL COMMENT '重复的间隔时间',
  `times_triggered` bigint(10) NOT NULL COMMENT '已经触发的次数',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '简单触发器的信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `str_prop_1` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'String类型的trigger的第一个参数',
  `str_prop_2` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'String类型的trigger的第二个参数',
  `str_prop_3` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'String类型的trigger的第三个参数',
  `int_prop_1` int(11) NULL DEFAULT NULL COMMENT 'int类型的trigger的第一个参数',
  `int_prop_2` int(11) NULL DEFAULT NULL COMMENT 'int类型的trigger的第二个参数',
  `long_prop_1` bigint(20) NULL DEFAULT NULL COMMENT 'long类型的trigger的第一个参数',
  `long_prop_2` bigint(20) NULL DEFAULT NULL COMMENT 'long类型的trigger的第二个参数',
  `dec_prop_1` decimal(13, 4) NULL DEFAULT NULL COMMENT 'decimal类型的trigger的第一个参数',
  `dec_prop_2` decimal(13, 4) NULL DEFAULT NULL COMMENT 'decimal类型的trigger的第二个参数',
  `bool_prop_1` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Boolean类型的trigger的第一个参数',
  `bool_prop_2` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Boolean类型的trigger的第二个参数',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '同步机制的行锁表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '触发器的名字',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '触发器所属组的名字',
  `job_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_job_details表job_name的外键',
  `job_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_job_details表job_group的外键',
  `description` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '相关介绍',
  `next_fire_time` bigint(13) NULL DEFAULT NULL COMMENT '上一次触发时间（毫秒）',
  `prev_fire_time` bigint(13) NULL DEFAULT NULL COMMENT '下一次触发时间（默认为-1表示不触发）',
  `priority` int(11) NULL DEFAULT NULL COMMENT '优先级',
  `trigger_state` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '触发器状态',
  `trigger_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '触发器的类型',
  `start_time` bigint(13) NOT NULL COMMENT '开始时间',
  `end_time` bigint(13) NULL DEFAULT NULL COMMENT '结束时间',
  `calendar_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日程表名称',
  `misfire_instr` smallint(2) NULL DEFAULT NULL COMMENT '补偿执行的策略',
  `job_data` blob NULL COMMENT '存放持久化job对象',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  INDEX `sched_name`(`sched_name`, `job_name`, `job_group`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '触发器详细信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2024-07-14 20:02:51', '', NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2024-07-14 20:02:51', '', NULL, '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2024-07-14 20:02:51', '', NULL, '深色主题theme-dark，浅色主题theme-light');
INSERT INTO `sys_config` VALUES (4, '账号自助-验证码开关', 'sys.account.captchaEnabled', 'true', 'Y', 'admin', '2024-07-14 20:02:51', '', NULL, '是否开启验证码功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', '2024-07-14 20:02:51', '', NULL, '是否开启注册用户功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (6, '用户登录-黑名单列表', 'sys.login.blackIPList', '', 'Y', 'admin', '2024-07-14 20:02:51', '', NULL, '设置登录IP黑名单限制，多个匹配项以;分隔，支持匹配（*通配、网段）');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 110 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', '爱家科技', 0, '爱家', '15888888888', 'aj@qq.com', '0', '0', 'admin', '2024-07-14 20:02:50', 'admin', '2024-08-04 13:35:27');
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '常州总公司', 1, '爱家', '15888888888', 'aj@qq.com', '0', '0', 'admin', '2024-07-14 20:02:50', 'admin', '2024-08-04 13:35:56');
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2024-07-14 20:02:50', '', NULL);
INSERT INTO `sys_dept` VALUES (103, 101, '0,100,101', '行政部门', 1, '爱家', '15888888888', 'aj@qq.com', '0', '0', 'admin', '2024-07-14 20:02:50', 'admin', '2024-08-04 13:36:32');
INSERT INTO `sys_dept` VALUES (104, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2024-07-14 20:02:50', '', NULL);
INSERT INTO `sys_dept` VALUES (105, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2024-07-14 20:02:50', '', NULL);
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2024-07-14 20:02:50', '', NULL);
INSERT INTO `sys_dept` VALUES (107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2024-07-14 20:02:50', '', NULL);
INSERT INTO `sys_dept` VALUES (108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2024-07-14 20:02:50', '', NULL);
INSERT INTO `sys_dept` VALUES (109, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '2', 'admin', '2024-07-14 20:02:50', '', NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 99, '其他', '0', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '其他操作');
INSERT INTO `sys_dict_data` VALUES (19, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (20, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (21, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (22, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (23, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (24, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (25, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (26, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (27, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (28, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (29, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '停用状态');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2024-07-14 20:02:51', '', NULL, '登录状态列表');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', '2024-07-14 20:02:52', '', NULL, '');
INSERT INTO `sys_job` VALUES (2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin', '2024-07-14 20:02:52', '', NULL, '');
INSERT INTO `sys_job` VALUES (3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', '2024-07-14 20:02:52', '', NULL, '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '异常信息',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE,
  INDEX `idx_sys_logininfor_s`(`status`) USING BTREE,
  INDEX `idx_sys_logininfor_lt`(`login_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统访问记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES (1, 'admin', '127.0.0.1', '内网IP', 'Chrome 12', 'Windows 10', '0', '登录成功', '2024-08-04 13:33:59');
INSERT INTO `sys_logininfor` VALUES (2, 'admin', '127.0.0.1', '内网IP', 'Chrome 12', 'Windows 10', '0', '登录成功', '2024-08-04 16:12:20');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由参数',
  `route_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由名称',
  `is_frame` int(1) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` int(1) NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1156 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, 'system', NULL, '', '', 1, 0, 'M', '0', '0', '', 'system', 'admin', '2024-07-14 20:02:50', '', NULL, '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 2, 'monitor', NULL, '', '', 1, 0, 'M', '0', '0', '', 'monitor', 'admin', '2024-07-14 20:02:50', '', NULL, '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 3, 'tool', NULL, '', '', 1, 0, 'M', '0', '0', '', 'tool', 'admin', '2024-07-14 20:02:50', '', NULL, '系统工具目录');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', '', '', 1, 0, 'C', '0', '0', 'system:user:list', 'user', 'admin', '2024-07-14 20:02:50', '', NULL, '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', '', '', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', 'admin', '2024-07-14 20:02:50', '', NULL, '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', '', '', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2024-07-14 20:02:50', '', NULL, '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', '', '', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2024-07-14 20:02:50', '', NULL, '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, 'post', 'system/post/index', '', '', 1, 0, 'C', '0', '0', 'system:post:list', 'post', 'admin', '2024-07-14 20:02:50', '', NULL, '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', '', '', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', 'admin', '2024-07-14 20:02:50', '', NULL, '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', '', '', 1, 0, 'C', '0', '0', 'system:config:list', 'edit', 'admin', '2024-07-14 20:02:50', '', NULL, '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', '', '', 1, 0, 'C', '0', '0', 'system:notice:list', 'message', 'admin', '2024-07-14 20:02:50', '', NULL, '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, 'log', '', '', '', 1, 0, 'M', '0', '0', '', 'log', 'admin', '2024-07-14 20:02:50', '', NULL, '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', '', '', 1, 0, 'C', '0', '0', 'monitor:online:list', 'online', 'admin', '2024-07-14 20:02:50', '', NULL, '在线用户菜单');
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, 'job', 'monitor/job/index', '', '', 1, 0, 'C', '0', '0', 'monitor:job:list', 'job', 'admin', '2024-07-14 20:02:50', '', NULL, '定时任务菜单');
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, 'druid', 'monitor/druid/index', '', '', 1, 0, 'C', '0', '0', 'monitor:druid:list', 'druid', 'admin', '2024-07-14 20:02:50', '', NULL, '数据监控菜单');
INSERT INTO `sys_menu` VALUES (112, '服务监控', 2, 4, 'server', 'monitor/server/index', '', '', 1, 0, 'C', '0', '0', 'monitor:server:list', 'server', 'admin', '2024-07-14 20:02:50', '', NULL, '服务监控菜单');
INSERT INTO `sys_menu` VALUES (113, '缓存监控', 2, 5, 'cache', 'monitor/cache/index', '', '', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis', 'admin', '2024-07-14 20:02:50', '', NULL, '缓存监控菜单');
INSERT INTO `sys_menu` VALUES (114, '缓存列表', 2, 6, 'cacheList', 'monitor/cache/list', '', '', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis-list', 'admin', '2024-07-14 20:02:50', '', NULL, '缓存列表菜单');
INSERT INTO `sys_menu` VALUES (115, '表单构建', 3, 1, 'build', 'tool/build/index', '', '', 1, 0, 'C', '0', '0', 'tool:build:list', 'build', 'admin', '2024-07-14 20:02:50', '', NULL, '表单构建菜单');
INSERT INTO `sys_menu` VALUES (116, '代码生成', 3, 2, 'gen', 'tool/gen/index', '', '', 1, 0, 'C', '0', '0', 'tool:gen:list', 'code', 'admin', '2024-07-14 20:02:50', '', NULL, '代码生成菜单');
INSERT INTO `sys_menu` VALUES (117, '系统接口', 3, 3, 'swagger', 'tool/swagger/index', '', '', 1, 0, 'C', '0', '0', 'tool:swagger:list', 'swagger', 'admin', '2024-07-14 20:02:50', '', NULL, '系统接口菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', '', '', 1, 0, 'C', '0', '0', 'monitor:operlog:list', 'form', 'admin', '2024-07-14 20:02:50', '', NULL, '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', '', '', 1, 0, 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', 'admin', '2024-07-14 20:02:50', '', NULL, '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1000, '用户查询', 100, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1001, '用户新增', 100, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1002, '用户修改', 100, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1003, '用户删除', 100, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1004, '用户导出', 100, 5, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1005, '用户导入', 100, 6, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1006, '重置密码', 100, 7, '', '', '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1007, '角色查询', 101, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1008, '角色新增', 101, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1009, '角色修改', 101, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1010, '角色删除', 101, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1011, '角色导出', 101, 5, '', '', '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1012, '菜单查询', 102, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1013, '菜单新增', 102, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1014, '菜单修改', 102, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1015, '菜单删除', 102, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1016, '部门查询', 103, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:query', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1017, '部门新增', 103, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:add', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1018, '部门修改', 103, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1019, '部门删除', 103, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1020, '岗位查询', 104, 1, '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:query', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1021, '岗位新增', 104, 2, '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:add', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1022, '岗位修改', 104, 3, '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:edit', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1023, '岗位删除', 104, 4, '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:remove', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1024, '岗位导出', 104, 5, '', '', '', '', 1, 0, 'F', '0', '0', 'system:post:export', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1025, '字典查询', 105, 1, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1026, '字典新增', 105, 2, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1027, '字典修改', 105, 3, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1028, '字典删除', 105, 4, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1029, '字典导出', 105, 5, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1030, '参数查询', 106, 1, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1031, '参数新增', 106, 2, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1032, '参数修改', 106, 3, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1033, '参数删除', 106, 4, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1034, '参数导出', 106, 5, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1035, '公告查询', 107, 1, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1036, '公告新增', 107, 2, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1037, '公告修改', 107, 3, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1038, '公告删除', 107, 4, '#', '', '', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1039, '操作查询', 500, 1, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:query', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1040, '操作删除', 500, 2, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:remove', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1041, '日志导出', 500, 3, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:export', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1042, '登录查询', 501, 1, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:query', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1043, '登录删除', 501, 2, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:remove', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1044, '日志导出', 501, 3, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:export', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1045, '账户解锁', 501, 4, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:unlock', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1046, '在线查询', 109, 1, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1047, '批量强退', 109, 2, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1048, '单条强退', 109, 3, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1049, '任务查询', 110, 1, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:query', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1050, '任务新增', 110, 2, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:add', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1051, '任务修改', 110, 3, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:edit', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1052, '任务删除', 110, 4, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:remove', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1053, '状态修改', 110, 5, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:changeStatus', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1054, '任务导出', 110, 6, '#', '', '', '', 1, 0, 'F', '0', '0', 'monitor:job:export', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1055, '生成查询', 116, 1, '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:query', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1056, '生成修改', 116, 2, '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:edit', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1057, '生成删除', 116, 3, '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:remove', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1058, '导入代码', 116, 4, '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:import', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1059, '预览代码', 116, 5, '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:preview', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1060, '生成代码', 116, 6, '#', '', '', '', 1, 0, 'F', '0', '0', 'tool:gen:code', '#', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1061, '人员信息', 0, 4, 'userInfo', NULL, NULL, '', 1, 0, 'M', '0', '0', '', 'peoples', 'admin', '2024-08-04 13:54:29', 'admin', '2024-08-04 13:55:32', '');
INSERT INTO `sys_menu` VALUES (1062, '费用模板', 0, 5, 'bill', NULL, NULL, '', 1, 0, 'M', '0', '0', NULL, 'money', 'admin', '2024-08-04 13:55:22', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1063, '考勤', 0, 6, 'checkin', NULL, NULL, '', 1, 0, 'M', '0', '0', NULL, 'date', 'admin', '2024-08-04 13:56:33', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1064, '经济', 0, 7, 'profits', NULL, NULL, '', 1, 0, 'M', '0', '0', NULL, 'number', 'admin', '2024-08-04 13:57:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1065, '考勤总表', 1063, 1, 'baseCheckin', 'checkin/baseCheckin/index', NULL, '', 1, 0, 'C', '0', '0', 'checkin:baseCheckin:list', '#', 'admin', '2024-08-04 14:50:17', '', NULL, '考勤总表菜单');
INSERT INTO `sys_menu` VALUES (1066, '考勤总表查询', 1065, 1, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:baseCheckin:query', '#', 'admin', '2024-08-04 14:50:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1067, '考勤总表新增', 1065, 2, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:baseCheckin:add', '#', 'admin', '2024-08-04 14:50:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1068, '考勤总表修改', 1065, 3, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:baseCheckin:edit', '#', 'admin', '2024-08-04 14:50:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1069, '考勤总表删除', 1065, 4, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:baseCheckin:remove', '#', 'admin', '2024-08-04 14:50:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1070, '考勤总表导出', 1065, 5, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:baseCheckin:export', '#', 'admin', '2024-08-04 14:50:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1071, '学生账单明细', 1064, 1, 'studentBill', 'payment/studentBill/index', NULL, '', 1, 0, 'C', '0', '0', 'payment:studentBill:list', '#', 'admin', '2024-08-04 14:50:23', '', NULL, '学生账单明细菜单');
INSERT INTO `sys_menu` VALUES (1072, '学生账单明细查询', 1071, 1, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'payment:studentBill:query', '#', 'admin', '2024-08-04 14:50:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1073, '学生账单明细新增', 1071, 2, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'payment:studentBill:add', '#', 'admin', '2024-08-04 14:50:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1074, '学生账单明细修改', 1071, 3, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'payment:studentBill:edit', '#', 'admin', '2024-08-04 14:50:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1075, '学生账单明细删除', 1071, 4, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'payment:studentBill:remove', '#', 'admin', '2024-08-04 14:50:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1076, '学生账单明细导出', 1071, 5, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'payment:studentBill:export', '#', 'admin', '2024-08-04 14:50:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1077, '学生费用模板', 1062, 1, 'studentBillTemplate', 'bill/studentBillTemplate/index', NULL, '', 1, 0, 'C', '0', '0', 'bill:studentBillTemplate:list', '#', 'admin', '2024-08-04 14:50:29', '', NULL, '学生费用模板菜单');
INSERT INTO `sys_menu` VALUES (1078, '学生费用模板查询', 1077, 1, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'bill:studentBillTemplate:query', '#', 'admin', '2024-08-04 14:50:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1079, '学生费用模板新增', 1077, 2, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'bill:studentBillTemplate:add', '#', 'admin', '2024-08-04 14:50:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1080, '学生费用模板修改', 1077, 3, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'bill:studentBillTemplate:edit', '#', 'admin', '2024-08-04 14:50:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1081, '学生费用模板删除', 1077, 4, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'bill:studentBillTemplate:remove', '#', 'admin', '2024-08-04 14:50:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1082, '学生费用模板导出', 1077, 5, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'bill:studentBillTemplate:export', '#', 'admin', '2024-08-04 14:50:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1083, '学生考勤', 1063, 1, 'studentCheckin', 'checkin/studentCheckin/index', NULL, '', 1, 0, 'C', '0', '0', 'checkin:studentCheckin:list', '#', 'admin', '2024-08-04 14:50:36', '', NULL, '学生考勤菜单');
INSERT INTO `sys_menu` VALUES (1084, '学生考勤查询', 1083, 1, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:studentCheckin:query', '#', 'admin', '2024-08-04 14:50:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1085, '学生考勤新增', 1083, 2, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:studentCheckin:add', '#', 'admin', '2024-08-04 14:50:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1086, '学生考勤修改', 1083, 3, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:studentCheckin:edit', '#', 'admin', '2024-08-04 14:50:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1087, '学生考勤删除', 1083, 4, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:studentCheckin:remove', '#', 'admin', '2024-08-04 14:50:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1088, '学生考勤导出', 1083, 5, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:studentCheckin:export', '#', 'admin', '2024-08-04 14:50:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1089, '考勤统计', 1063, 1, 'studentCheckinStatistic', 'checkin/studentCheckinStatistic/index', NULL, '', 1, 0, 'C', '0', '0', 'checkin:studentCheckinStatistic:list', '#', 'admin', '2024-08-04 14:50:44', '', NULL, '考勤统计菜单');
INSERT INTO `sys_menu` VALUES (1090, '考勤统计查询', 1089, 1, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:studentCheckinStatistic:query', '#', 'admin', '2024-08-04 14:50:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1091, '考勤统计新增', 1089, 2, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:studentCheckinStatistic:add', '#', 'admin', '2024-08-04 14:50:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1092, '考勤统计修改', 1089, 3, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:studentCheckinStatistic:edit', '#', 'admin', '2024-08-04 14:50:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1093, '考勤统计删除', 1089, 4, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:studentCheckinStatistic:remove', '#', 'admin', '2024-08-04 14:50:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1094, '考勤统计导出', 1089, 5, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:studentCheckinStatistic:export', '#', 'admin', '2024-08-04 14:50:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1095, '学生信息', 1061, 1, 'studentInfo', 'info/studentInfo/index', NULL, '', 1, 0, 'C', '0', '0', 'info:studentInfo:list', '#', 'admin', '2024-08-04 14:50:51', '', NULL, '学生信息菜单');
INSERT INTO `sys_menu` VALUES (1096, '学生信息查询', 1095, 1, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'info:studentInfo:query', '#', 'admin', '2024-08-04 14:50:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1097, '学生信息新增', 1095, 2, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'info:studentInfo:add', '#', 'admin', '2024-08-04 14:50:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1098, '学生信息修改', 1095, 3, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'info:studentInfo:edit', '#', 'admin', '2024-08-04 14:50:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1099, '学生信息删除', 1095, 4, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'info:studentInfo:remove', '#', 'admin', '2024-08-04 14:50:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1100, '学生信息导出', 1095, 5, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'info:studentInfo:export', '#', 'admin', '2024-08-04 14:50:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1101, '老师工资模板', 1062, 1, 'teacherBillTemplate', 'bill/teacherBillTemplate/index', NULL, '', 1, 0, 'C', '0', '0', 'bill:teacherBillTemplate:list', '#', 'admin', '2024-08-04 14:50:58', '', NULL, '老师工资模板菜单');
INSERT INTO `sys_menu` VALUES (1102, '老师工资模板查询', 1101, 1, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'bill:teacherBillTemplate:query', '#', 'admin', '2024-08-04 14:50:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1103, '老师工资模板新增', 1101, 2, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'bill:teacherBillTemplate:add', '#', 'admin', '2024-08-04 14:50:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1104, '老师工资模板修改', 1101, 3, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'bill:teacherBillTemplate:edit', '#', 'admin', '2024-08-04 14:50:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1105, '老师工资模板删除', 1101, 4, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'bill:teacherBillTemplate:remove', '#', 'admin', '2024-08-04 14:50:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1106, '老师工资模板导出', 1101, 5, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'bill:teacherBillTemplate:export', '#', 'admin', '2024-08-04 14:50:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1107, '工资明细', 1064, 1, 'teacherSalary', 'payment/teacherSalary/index', NULL, '', 1, 0, 'C', '0', '0', 'payment:teacherSalary:list', '#', 'admin', '2024-08-04 14:51:14', '', NULL, '工资明细菜单');
INSERT INTO `sys_menu` VALUES (1108, '工资明细查询', 1107, 1, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'payment:teacherSalary:query', '#', 'admin', '2024-08-04 14:51:14', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1109, '工资明细新增', 1107, 2, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'payment:teacherSalary:add', '#', 'admin', '2024-08-04 14:51:14', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1110, '工资明细修改', 1107, 3, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'payment:teacherSalary:edit', '#', 'admin', '2024-08-04 14:51:14', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1111, '工资明细删除', 1107, 4, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'payment:teacherSalary:remove', '#', 'admin', '2024-08-04 14:51:14', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1112, '工资明细导出', 1107, 5, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'payment:teacherSalary:export', '#', 'admin', '2024-08-04 14:51:14', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1113, '教师信息', 1061, 1, 'teacherInfo', 'info/teacherInfo/index', NULL, '', 1, 0, 'C', '0', '0', 'info:teacherInfo:list', '#', 'admin', '2024-08-04 14:51:55', '', NULL, '教师信息菜单');
INSERT INTO `sys_menu` VALUES (1114, '教师信息查询', 1113, 1, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'info:teacherInfo:query', '#', 'admin', '2024-08-04 14:51:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1115, '教师信息新增', 1113, 2, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'info:teacherInfo:add', '#', 'admin', '2024-08-04 14:51:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1116, '教师信息修改', 1113, 3, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'info:teacherInfo:edit', '#', 'admin', '2024-08-04 14:51:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1117, '教师信息删除', 1113, 4, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'info:teacherInfo:remove', '#', 'admin', '2024-08-04 14:51:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1118, '教师信息导出', 1113, 5, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'info:teacherInfo:export', '#', 'admin', '2024-08-04 14:51:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1119, '教师考勤汇总', 1063, 1, 'teacherCheckinStatistic', 'checkin/teacherCheckinStatistic/index', NULL, '', 1, 0, 'C', '0', '0', 'checkin:teacherCheckinStatistic:list', '#', 'admin', '2024-08-04 14:52:05', '', NULL, '教师考勤汇总菜单');
INSERT INTO `sys_menu` VALUES (1120, '教师考勤汇总查询', 1119, 1, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:teacherCheckinStatistic:query', '#', 'admin', '2024-08-04 14:52:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1121, '教师考勤汇总新增', 1119, 2, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:teacherCheckinStatistic:add', '#', 'admin', '2024-08-04 14:52:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1122, '教师考勤汇总修改', 1119, 3, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:teacherCheckinStatistic:edit', '#', 'admin', '2024-08-04 14:52:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1123, '教师考勤汇总删除', 1119, 4, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:teacherCheckinStatistic:remove', '#', 'admin', '2024-08-04 14:52:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1124, '教师考勤汇总导出', 1119, 5, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:teacherCheckinStatistic:export', '#', 'admin', '2024-08-04 14:52:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1125, '员工考勤', 1063, 1, 'teacherCheckin', 'checkin/teacherCheckin/index', NULL, '', 1, 0, 'C', '0', '0', 'checkin:teacherCheckin:list', '#', 'admin', '2024-08-04 14:52:13', '', NULL, '员工考勤菜单');
INSERT INTO `sys_menu` VALUES (1126, '员工考勤查询', 1125, 1, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:teacherCheckin:query', '#', 'admin', '2024-08-04 14:52:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1127, '员工考勤新增', 1125, 2, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:teacherCheckin:add', '#', 'admin', '2024-08-04 14:52:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1128, '员工考勤修改', 1125, 3, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:teacherCheckin:edit', '#', 'admin', '2024-08-04 14:52:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1129, '员工考勤删除', 1125, 4, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:teacherCheckin:remove', '#', 'admin', '2024-08-04 14:52:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1130, '员工考勤导出', 1125, 5, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'checkin:teacherCheckin:export', '#', 'admin', '2024-08-04 14:52:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1143, '周边学校', 0, 8, 'schools', NULL, NULL, '', 1, 0, 'M', '0', '0', '', 'nested', 'admin', '2024-08-04 17:12:13', 'admin', '2024-08-04 17:12:18', '');
INSERT INTO `sys_menu` VALUES (1144, '年级', 1143, 1, 'grade', 'schools/grade/index', NULL, '', 1, 0, 'C', '0', '0', 'schools:grade:list', '#', 'admin', '2024-08-04 17:14:31', '', NULL, '年级菜单');
INSERT INTO `sys_menu` VALUES (1145, '年级查询', 1144, 1, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'schools:grade:query', '#', 'admin', '2024-08-04 17:14:31', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1146, '年级新增', 1144, 2, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'schools:grade:add', '#', 'admin', '2024-08-04 17:14:31', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1147, '年级修改', 1144, 3, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'schools:grade:edit', '#', 'admin', '2024-08-04 17:14:31', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1148, '年级删除', 1144, 4, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'schools:grade:remove', '#', 'admin', '2024-08-04 17:14:31', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1149, '年级导出', 1144, 5, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'schools:grade:export', '#', 'admin', '2024-08-04 17:14:31', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1150, '学校', 1143, 1, 'school', 'schools/school/index', NULL, '', 1, 0, 'C', '0', '0', 'schools:school:list', '#', 'admin', '2024-08-04 17:14:39', '', NULL, '学校菜单');
INSERT INTO `sys_menu` VALUES (1151, '学校查询', 1150, 1, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'schools:school:query', '#', 'admin', '2024-08-04 17:14:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1152, '学校新增', 1150, 2, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'schools:school:add', '#', 'admin', '2024-08-04 17:14:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1153, '学校修改', 1150, 3, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'schools:school:edit', '#', 'admin', '2024-08-04 17:14:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1154, '学校删除', 1150, 4, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'schools:school:remove', '#', 'admin', '2024-08-04 17:14:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1155, '学校导出', 1150, 5, '#', '', NULL, '', 1, 0, 'F', '0', '0', 'schools:school:export', '#', 'admin', '2024-08-04 17:14:39', '', NULL, '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 若依新版本发布啦', '2', 0xE696B0E78988E69CACE58685E5AEB9, '0', 'admin', '2024-07-14 20:02:52', '', NULL, '管理员');
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 若依系统凌晨维护', '1', 0xE7BBB4E68AA4E58685E5AEB9, '0', 'admin', '2024-07-14 20:02:52', '', NULL, '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int(1) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `cost_time` bigint(20) NULL DEFAULT 0 COMMENT '消耗时间',
  PRIMARY KEY (`oper_id`) USING BTREE,
  INDEX `idx_sys_oper_log_bt`(`business_type`) USING BTREE,
  INDEX `idx_sys_oper_log_s`(`status`) USING BTREE,
  INDEX `idx_sys_oper_log_ot`(`oper_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES (1, '操作日志', 9, 'com.ruoyi.web.controller.monitor.SysOperlogController.clean()', 'DELETE', 1, 'admin', '研发部门', '/monitor/operlog/clean', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 13:38:34', 69);
INSERT INTO `sys_oper_log` VALUES (2, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":\"aj_student_info\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 13:39:51', 127);
INSERT INTO `sys_oper_log` VALUES (3, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":\"aj_base_check_in,aj_teacher_check_in_statistic,aj_student_bill,aj_salary_bill,aj_student_check_in_statistic,aj_student_check_in,aj_teacher_check_in,aj_salary_template,aj_cost_template,aj_teacher_info\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 13:40:03', 272);
INSERT INTO `sys_oper_log` VALUES (4, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"info\",\"className\":\"AjStudentInfo\",\"columns\":[{\"capJavaField\":\"StudentId\",\"columnComment\":\"id\",\"columnId\":1,\"columnName\":\"student_id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:39:51\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":true,\"insert\":true,\"isIncrement\":\"1\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"studentId\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"StudentName\",\"columnComment\":\"学生姓名（可重复，建议添加后缀）\",\"columnId\":2,\"columnName\":\"student_name\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:39:51\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"studentName\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"LIKE\",\"required\":false,\"sort\":2,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"StudentGender\",\"columnComment\":\"学生性别（0默认，1男，2女）\",\"columnId\":3,\"columnName\":\"student_gender\",\"columnType\":\"tinyint(1)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:39:51\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"studentGender\",\"javaType\":\"Integer\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":3,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"StudentSchool\",\"columnComment\":\"学生学校\",\"columnId\":4,\"columnName\":\"student_school\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:39:51\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 13:44:17', 304);
INSERT INTO `sys_oper_log` VALUES (5, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'DELETE', 1, 'admin', '研发部门', '/system/menu/4', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"菜单已分配,不允许删除\",\"code\":601}', 0, NULL, '2024-08-04 13:44:31', 25);
INSERT INTO `sys_oper_log` VALUES (6, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'DELETE', 1, 'admin', '研发部门', '/system/menu/4', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"菜单已分配,不允许删除\",\"code\":601}', 0, NULL, '2024-08-04 13:44:36', 4);
INSERT INTO `sys_oper_log` VALUES (7, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.edit()', 'PUT', 1, 'admin', '研发部门', '/system/role', '127.0.0.1', '内网IP', '{\"admin\":false,\"createTime\":\"2024-07-14 20:02:50\",\"dataScope\":\"2\",\"delFlag\":\"0\",\"deptCheckStrictly\":true,\"flag\":false,\"menuCheckStrictly\":true,\"menuIds\":[],\"params\":{},\"remark\":\"普通角色\",\"roleId\":2,\"roleKey\":\"common\",\"roleName\":\"普通角色\",\"roleSort\":2,\"status\":\"0\",\"updateBy\":\"admin\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 13:45:04', 57);
INSERT INTO `sys_oper_log` VALUES (8, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'DELETE', 1, 'admin', '研发部门', '/system/menu/4', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 13:45:09', 13);
INSERT INTO `sys_oper_log` VALUES (9, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', '研发部门', '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createBy\":\"admin\",\"icon\":\"user\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuName\":\"人员信息\",\"menuType\":\"M\",\"orderNum\":4,\"params\":{},\"parentId\":0,\"path\":\"userInfo\",\"status\":\"0\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 13:54:29', 39);
INSERT INTO `sys_oper_log` VALUES (10, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', '研发部门', '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createBy\":\"admin\",\"icon\":\"money\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuName\":\"费用模板\",\"menuType\":\"M\",\"orderNum\":5,\"params\":{},\"parentId\":0,\"path\":\"bill\",\"status\":\"0\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 13:55:22', 278);
INSERT INTO `sys_oper_log` VALUES (11, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', '研发部门', '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createTime\":\"2024-08-04 13:54:29\",\"icon\":\"peoples\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":1061,\"menuName\":\"人员信息\",\"menuType\":\"M\",\"orderNum\":4,\"params\":{},\"parentId\":0,\"path\":\"userInfo\",\"perms\":\"\",\"routeName\":\"\",\"status\":\"0\",\"updateBy\":\"admin\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 13:55:32', 18);
INSERT INTO `sys_oper_log` VALUES (12, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', '研发部门', '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createBy\":\"admin\",\"icon\":\"date\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuName\":\"考勤\",\"menuType\":\"M\",\"orderNum\":6,\"params\":{},\"parentId\":0,\"path\":\"checkin\",\"status\":\"0\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 13:56:33', 27);
INSERT INTO `sys_oper_log` VALUES (13, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', '研发部门', '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createBy\":\"admin\",\"icon\":\"number\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuName\":\"经济\",\"menuType\":\"M\",\"orderNum\":7,\"params\":{},\"parentId\":0,\"path\":\"profits\",\"status\":\"0\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 13:57:31', 273);
INSERT INTO `sys_oper_log` VALUES (14, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"studentInfo\",\"className\":\"AjStudentInfo\",\"columns\":[{\"capJavaField\":\"StudentId\",\"columnComment\":\"id\",\"columnId\":1,\"columnName\":\"student_id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:39:51\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":true,\"insert\":true,\"isIncrement\":\"1\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"studentId\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"updateTime\":\"2024-08-04 13:44:17\",\"usableColumn\":false},{\"capJavaField\":\"StudentName\",\"columnComment\":\"学生姓名（可重复，建议添加后缀）\",\"columnId\":2,\"columnName\":\"student_name\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:39:51\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"studentName\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"LIKE\",\"required\":false,\"sort\":2,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"updateTime\":\"2024-08-04 13:44:17\",\"usableColumn\":false},{\"capJavaField\":\"StudentGender\",\"columnComment\":\"学生性别（0默认，1男，2女）\",\"columnId\":3,\"columnName\":\"student_gender\",\"columnType\":\"tinyint(1)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:39:51\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"studentGender\",\"javaType\":\"Integer\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":3,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"updateTime\":\"2024-08-04 13:44:17\",\"usableColumn\":false},{\"capJavaField\":\"StudentSchool\",\"columnComment\":\"学生学校\",\"columnId\":4,\"columnName\":\"student_school\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 14:06:15', 303);
INSERT INTO `sys_oper_log` VALUES (15, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"studentInfo\",\"className\":\"AjStudentInfo\",\"columns\":[{\"capJavaField\":\"StudentId\",\"columnComment\":\"id\",\"columnId\":1,\"columnName\":\"student_id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:39:51\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":true,\"insert\":true,\"isIncrement\":\"1\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"studentId\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"updateTime\":\"2024-08-04 14:06:15\",\"usableColumn\":false},{\"capJavaField\":\"StudentName\",\"columnComment\":\"学生姓名（可重复，建议添加后缀）\",\"columnId\":2,\"columnName\":\"student_name\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:39:51\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"studentName\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"LIKE\",\"required\":false,\"sort\":2,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"updateTime\":\"2024-08-04 14:06:15\",\"usableColumn\":false},{\"capJavaField\":\"StudentGender\",\"columnComment\":\"学生性别（0默认，1男，2女）\",\"columnId\":3,\"columnName\":\"student_gender\",\"columnType\":\"tinyint(1)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:39:51\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"studentGender\",\"javaType\":\"Integer\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":3,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"updateTime\":\"2024-08-04 14:06:15\",\"usableColumn\":false},{\"capJavaField\":\"StudentSchool\",\"columnComment\":\"学生学校\",\"columnId\":4,\"columnName\":\"student_school\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 14:07:40', 282);
INSERT INTO `sys_oper_log` VALUES (16, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"teacherInfo\",\"className\":\"AjTeacherInfo\",\"columns\":[{\"capJavaField\":\"TeacherId\",\"columnId\":119,\"columnName\":\"teacher_id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":true,\"insert\":true,\"isIncrement\":\"1\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"teacherId\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":11,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"TeacherName\",\"columnComment\":\"教师姓名（可重复，建议添加后缀）\",\"columnId\":120,\"columnName\":\"teacher_name\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"teacherName\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"LIKE\",\"required\":false,\"sort\":2,\"superColumn\":false,\"tableId\":11,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"TeacherGender\",\"columnComment\":\"教师性别\",\"columnId\":121,\"columnName\":\"teacher_gender\",\"columnType\":\"tinyint(1)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"teacherGender\",\"javaType\":\"Integer\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":3,\"superColumn\":false,\"tableId\":11,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"TeacherPhone\",\"columnComment\":\"教师电话\",\"columnId\":122,\"columnName\":\"teacher_phone\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 14:09:32', 281);
INSERT INTO `sys_oper_log` VALUES (17, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"teacherInfo\",\"className\":\"AjTeacherInfo\",\"columns\":[{\"capJavaField\":\"TeacherId\",\"columnId\":119,\"columnName\":\"teacher_id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":true,\"insert\":true,\"isIncrement\":\"1\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"teacherId\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":11,\"updateBy\":\"\",\"updateTime\":\"2024-08-04 14:09:31\",\"usableColumn\":false},{\"capJavaField\":\"TeacherName\",\"columnComment\":\"教师姓名（可重复，建议添加后缀）\",\"columnId\":120,\"columnName\":\"teacher_name\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"teacherName\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"LIKE\",\"required\":false,\"sort\":2,\"superColumn\":false,\"tableId\":11,\"updateBy\":\"\",\"updateTime\":\"2024-08-04 14:09:31\",\"usableColumn\":false},{\"capJavaField\":\"TeacherGender\",\"columnComment\":\"教师性别\",\"columnId\":121,\"columnName\":\"teacher_gender\",\"columnType\":\"tinyint(1)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"teacherGender\",\"javaType\":\"Integer\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":3,\"superColumn\":false,\"tableId\":11,\"updateBy\":\"\",\"updateTime\":\"2024-08-04 14:09:31\",\"usableColumn\":false},{\"capJavaField\":\"TeacherPhone\",\"columnComment\":\"教师电话\",\"columnId\":122,\"columnName\":\"teacher_phone\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"e', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 14:10:04', 41);
INSERT INTO `sys_oper_log` VALUES (18, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.synchDb()', 'GET', 1, 'admin', '研发部门', '/tool/gen/synchDb/aj_cost_template', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 14:12:18', 50);
INSERT INTO `sys_oper_log` VALUES (19, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.synchDb()', 'GET', 1, 'admin', '研发部门', '/tool/gen/synchDb/aj_cost_template', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 14:12:40', 79);
INSERT INTO `sys_oper_log` VALUES (20, '代码生成', 3, 'com.ruoyi.generator.controller.GenController.remove()', 'DELETE', 1, 'admin', '研发部门', '/tool/gen/3', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 14:12:53', 153);
INSERT INTO `sys_oper_log` VALUES (21, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":\"aj_cost_template\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 14:12:58', 218);
INSERT INTO `sys_oper_log` VALUES (22, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"studentBillTemplate\",\"className\":\"AjCostTemplate\",\"columns\":[{\"capJavaField\":\"CostTemplateId\",\"columnComment\":\"id\",\"columnId\":126,\"columnName\":\"cost_template_id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 14:12:58\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":true,\"insert\":true,\"isIncrement\":\"1\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"costTemplateId\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":12,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"CostTemplateName\",\"columnComment\":\"模板名次\",\"columnId\":127,\"columnName\":\"cost_template_name\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 14:12:58\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"costTemplateName\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"LIKE\",\"required\":false,\"sort\":2,\"superColumn\":false,\"tableId\":12,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"CostFeePerDay\",\"columnComment\":\"每天费用\",\"columnId\":128,\"columnName\":\"cost_fee_per_day\",\"columnType\":\"decimal(10,2)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 14:12:58\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"costFeePerDay\",\"javaType\":\"BigDecimal\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":3,\"superColumn\":false,\"tableId\":12,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"CostUseFeePerDay\",\"columnComment\":\"使用日收费（0不用，1用）\",\"columnId\":129,\"columnName\":\"cost_use_fee_per_day\",\"columnType\":\"tinyint(1)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 14:12:58\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 14:14:52', 287);
INSERT INTO `sys_oper_log` VALUES (23, '代码生成', 3, 'com.ruoyi.generator.controller.GenController.remove()', 'DELETE', 1, 'admin', '研发部门', '/tool/gen/5', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 14:15:28', 117);
INSERT INTO `sys_oper_log` VALUES (24, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":\"aj_salary_template\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 14:15:32', 35);
INSERT INTO `sys_oper_log` VALUES (25, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"teacherBillTemplate\",\"className\":\"AjSalaryTemplate\",\"columns\":[{\"capJavaField\":\"SalaryId\",\"columnComment\":\"id\",\"columnId\":138,\"columnName\":\"salary_id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 14:15:32\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":true,\"insert\":true,\"isIncrement\":\"1\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"salaryId\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":13,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"SalaryTemplateName\",\"columnComment\":\"工资模板名次\",\"columnId\":139,\"columnName\":\"salary_template_name\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 14:15:32\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"salaryTemplateName\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"LIKE\",\"required\":false,\"sort\":2,\"superColumn\":false,\"tableId\":13,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"SalaryPerMonth\",\"columnComment\":\"月工资\",\"columnId\":140,\"columnName\":\"salary_per_month\",\"columnType\":\"decimal(10,2)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 14:15:32\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"salaryPerMonth\",\"javaType\":\"BigDecimal\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":3,\"superColumn\":false,\"tableId\":13,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"SalaryUsePerMonth\",\"columnComment\":\"使用月工资（0不用，1用）\",\"columnId\":141,\"columnName\":\"salary_use_per_month\",\"columnType\":\"tinyint(1)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 14:15:32\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increm', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 14:16:37', 44);
INSERT INTO `sys_oper_log` VALUES (26, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.synchDb()', 'GET', 1, 'admin', '研发部门', '/tool/gen/synchDb/aj_base_check_in', '127.0.0.1', '内网IP', '{}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 14:18:58', 83);
INSERT INTO `sys_oper_log` VALUES (27, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"baseCheckin\",\"className\":\"AjBaseCheckIn\",\"columns\":[{\"capJavaField\":\"BaseCheckInId\",\"columnComment\":\"id\",\"columnId\":12,\"columnName\":\"base_check_in_id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":true,\"insert\":true,\"isIncrement\":\"1\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"baseCheckInId\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":2,\"updateBy\":\"\",\"updateTime\":\"2024-08-04 14:18:58\",\"usableColumn\":false},{\"capJavaField\":\"BaseCheckInName\",\"columnComment\":\"考勤名称\",\"columnId\":13,\"columnName\":\"base_check_in_name\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"baseCheckInName\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"LIKE\",\"required\":false,\"sort\":2,\"superColumn\":false,\"tableId\":2,\"updateBy\":\"\",\"updateTime\":\"2024-08-04 14:18:58\",\"usableColumn\":false},{\"capJavaField\":\"BaseCheckInStudents\",\"columnComment\":\"考勤学生的id数组\",\"columnId\":14,\"columnName\":\"base_check_in_students\",\"columnType\":\"varchar(1000)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"textarea\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"baseCheckInStudents\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":3,\"superColumn\":false,\"tableId\":2,\"updateBy\":\"\",\"updateTime\":\"2024-08-04 14:18:58\",\"usableColumn\":false},{\"capJavaField\":\"BaseCheckInTeachers\",\"columnComment\":\"考勤教师的id数组\",\"columnId\":15,\"columnName\":\"base_check_in_teachers\",\"columnType\":\"var', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 14:20:53', 300);
INSERT INTO `sys_oper_log` VALUES (28, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"studentCheckin\",\"className\":\"AjStudentCheckIn\",\"columns\":[{\"capJavaField\":\"StudentCheckInId\",\"columnComment\":\"id\",\"columnId\":81,\"columnName\":\"student_check_in_id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":true,\"insert\":true,\"isIncrement\":\"1\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"studentCheckInId\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":7,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"StudentId\",\"columnComment\":\"学生id\",\"columnId\":82,\"columnName\":\"student_id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"studentId\",\"javaType\":\"Long\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":2,\"superColumn\":false,\"tableId\":7,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"BaseCheckInId\",\"columnComment\":\"对应考勤总表id\",\"columnId\":83,\"columnName\":\"base_check_in_id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"baseCheckInId\",\"javaType\":\"Long\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":3,\"superColumn\":false,\"tableId\":7,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"CheckInStudentName\",\"columnComment\":\"学生姓名\",\"columnId\":84,\"columnName\":\"check_in_student_name\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 14:21:50', 65);
INSERT INTO `sys_oper_log` VALUES (29, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"teacherCheckin\",\"className\":\"AjTeacherCheckIn\",\"columns\":[{\"capJavaField\":\"TeacherCheckInId\",\"columnComment\":\"id\",\"columnId\":100,\"columnName\":\"teacher_check_in_id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":true,\"insert\":true,\"isIncrement\":\"1\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"teacherCheckInId\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":9,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"TeacherId\",\"columnComment\":\"教师id\",\"columnId\":101,\"columnName\":\"teacher_id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"teacherId\",\"javaType\":\"Long\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":2,\"superColumn\":false,\"tableId\":9,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"TeacherName\",\"columnComment\":\"教师姓名\",\"columnId\":102,\"columnName\":\"teacher_name\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"teacherName\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"LIKE\",\"required\":false,\"sort\":3,\"superColumn\":false,\"tableId\":9,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"CheckInDate\",\"columnComment\":\"签到日期\",\"columnId\":103,\"columnName\":\"check_in_date\",\"columnType\":\"date\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"datetime\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"i', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 14:22:51', 273);
INSERT INTO `sys_oper_log` VALUES (30, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"studentCheckinStatistic\",\"className\":\"AjStudentCheckInStatistic\",\"columns\":[{\"capJavaField\":\"Id\",\"columnComment\":\"id\",\"columnId\":89,\"columnName\":\"id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":true,\"insert\":true,\"isIncrement\":\"1\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"id\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":8,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"BaseCheckInId\",\"columnComment\":\"对应考勤总表id\",\"columnId\":90,\"columnName\":\"base_check_in_id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"baseCheckInId\",\"javaType\":\"Long\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":2,\"superColumn\":false,\"tableId\":8,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"StudentId\",\"columnComment\":\"学生id\",\"columnId\":91,\"columnName\":\"student_id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"studentId\",\"javaType\":\"Long\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":3,\"superColumn\":false,\"tableId\":8,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"StudentName\",\"columnComment\":\"学生姓名\",\"columnId\":92,\"columnName\":\"student_name\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 14:23:53', 33);
INSERT INTO `sys_oper_log` VALUES (31, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"teacherCheckinStatistic\",\"className\":\"AjTeacherCheckInStatistic\",\"columns\":[{\"capJavaField\":\"Id\",\"columnComment\":\"id\",\"columnId\":107,\"columnName\":\"id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":true,\"insert\":true,\"isIncrement\":\"1\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"id\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":10,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"BaseCheckInId\",\"columnComment\":\"对应考勤总表id\",\"columnId\":108,\"columnName\":\"base_check_in_id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"baseCheckInId\",\"javaType\":\"Long\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":2,\"superColumn\":false,\"tableId\":10,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"TeacherId\",\"columnComment\":\"教师id\",\"columnId\":109,\"columnName\":\"teacher_id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"teacherId\",\"javaType\":\"Long\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":3,\"superColumn\":false,\"tableId\":10,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"TeacherName\",\"columnComment\":\"教师姓名\",\"columnId\":110,\"columnName\":\"teacher_name\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isL', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 14:24:37', 56);
INSERT INTO `sys_oper_log` VALUES (32, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"studentBill\",\"className\":\"AjStudentBill\",\"columns\":[{\"capJavaField\":\"Id\",\"columnComment\":\"id\",\"columnId\":60,\"columnName\":\"id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"id\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":6,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"StudentId\",\"columnComment\":\"学生id\",\"columnId\":61,\"columnName\":\"student_id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"studentId\",\"javaType\":\"Long\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":2,\"superColumn\":false,\"tableId\":6,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"StudentName\",\"columnComment\":\"学生姓名\",\"columnId\":62,\"columnName\":\"student_name\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"studentName\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"LIKE\",\"required\":false,\"sort\":3,\"superColumn\":false,\"tableId\":6,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"CheckInStatisticId\",\"columnComment\":\"考勤统计表id\",\"columnId\":63,\"columnName\":\"check_in_statistic_id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\"', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 14:26:27', 64);
INSERT INTO `sys_oper_log` VALUES (33, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"teacherSalary\",\"className\":\"AjSalaryBill\",\"columns\":[{\"capJavaField\":\"Id\",\"columnComment\":\"id\",\"columnId\":34,\"columnName\":\"id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":true,\"insert\":true,\"isIncrement\":\"1\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"id\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":4,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"TeacherId\",\"columnComment\":\"教师id\",\"columnId\":35,\"columnName\":\"teacher_id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"teacherId\",\"javaType\":\"Long\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":2,\"superColumn\":false,\"tableId\":4,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"TeacherName\",\"columnComment\":\"教师姓名\",\"columnId\":36,\"columnName\":\"teacher_name\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"teacherName\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"LIKE\",\"required\":false,\"sort\":3,\"superColumn\":false,\"tableId\":4,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"TeacherCheckInStatistciId\",\"columnComment\":\"教师考勤统计表id\",\"columnId\":37,\"columnName\":\"teacher_check_in_statistci_id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isLis', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 14:26:58', 54);
INSERT INTO `sys_oper_log` VALUES (34, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"studentInfo\",\"className\":\"AjStudentInfo\",\"columns\":[{\"capJavaField\":\"StudentId\",\"columnComment\":\"id\",\"columnId\":1,\"columnName\":\"student_id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:39:51\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":true,\"insert\":true,\"isIncrement\":\"1\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"studentId\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"updateTime\":\"2024-08-04 14:07:40\",\"usableColumn\":false},{\"capJavaField\":\"StudentName\",\"columnComment\":\"学生姓名（可重复，建议添加后缀）\",\"columnId\":2,\"columnName\":\"student_name\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:39:51\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"1\",\"javaField\":\"studentName\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"LIKE\",\"required\":true,\"sort\":2,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"updateTime\":\"2024-08-04 14:07:40\",\"usableColumn\":false},{\"capJavaField\":\"StudentGender\",\"columnComment\":\"学生性别（0默认，1男，2女）\",\"columnId\":3,\"columnName\":\"student_gender\",\"columnType\":\"tinyint(1)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:39:51\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"studentGender\",\"javaType\":\"Integer\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":3,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"updateTime\":\"2024-08-04 14:07:40\",\"usableColumn\":false},{\"capJavaField\":\"StudentSchool\",\"columnComment\":\"学生学校\",\"columnId\":4,\"columnName\":\"student_school\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:3', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 14:27:40', 42);
INSERT INTO `sys_oper_log` VALUES (35, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', '研发部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"teacherInfo\",\"className\":\"AjTeacherInfo\",\"columns\":[{\"capJavaField\":\"TeacherId\",\"columnId\":119,\"columnName\":\"teacher_id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":true,\"insert\":true,\"isIncrement\":\"1\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"teacherId\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":11,\"updateBy\":\"\",\"updateTime\":\"2024-08-04 14:10:04\",\"usableColumn\":false},{\"capJavaField\":\"TeacherName\",\"columnComment\":\"教师姓名（可重复，建议添加后缀）\",\"columnId\":120,\"columnName\":\"teacher_name\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"1\",\"javaField\":\"teacherName\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"LIKE\",\"required\":true,\"sort\":2,\"superColumn\":false,\"tableId\":11,\"updateBy\":\"\",\"updateTime\":\"2024-08-04 14:10:04\",\"usableColumn\":false},{\"capJavaField\":\"TeacherGender\",\"columnComment\":\"教师性别\",\"columnId\":121,\"columnName\":\"teacher_gender\",\"columnType\":\"tinyint(1)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"teacherGender\",\"javaType\":\"Integer\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":3,\"superColumn\":false,\"tableId\":11,\"updateBy\":\"\",\"updateTime\":\"2024-08-04 14:10:04\",\"usableColumn\":false},{\"capJavaField\":\"TeacherPhone\",\"columnComment\":\"教师电话\",\"columnId\":122,\"columnName\":\"teacher_phone\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:40:03\",\"dictType\":\"\",\"ed', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 14:27:53', 151);
INSERT INTO `sys_oper_log` VALUES (36, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', '研发部门', '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\"tables\":\"aj_student_info,aj_base_check_in,aj_salary_bill,aj_student_bill,aj_student_check_in,aj_student_check_in_statistic,aj_teacher_check_in,aj_teacher_check_in_statistic,aj_teacher_info,aj_cost_template,aj_salary_template\"}', NULL, 0, NULL, '2024-08-04 14:28:12', 443);
INSERT INTO `sys_oper_log` VALUES (37, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', '行政部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"studentInfo\",\"className\":\"AjStudentInfo\",\"columns\":[{\"capJavaField\":\"StudentId\",\"columnComment\":\"id\",\"columnId\":1,\"columnName\":\"student_id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:39:51\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":true,\"insert\":true,\"isIncrement\":\"1\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"studentId\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"updateTime\":\"2024-08-04 14:27:40\",\"usableColumn\":false},{\"capJavaField\":\"StudentName\",\"columnComment\":\"学生姓名（可重复，建议添加后缀）\",\"columnId\":2,\"columnName\":\"student_name\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:39:51\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"1\",\"javaField\":\"studentName\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"LIKE\",\"required\":true,\"sort\":2,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"updateTime\":\"2024-08-04 14:27:40\",\"usableColumn\":false},{\"capJavaField\":\"StudentGender\",\"columnComment\":\"学生性别（0默认，1男，2女）\",\"columnId\":3,\"columnName\":\"student_gender\",\"columnType\":\"tinyint(1)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:39:51\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"radio\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"studentGender\",\"javaType\":\"Integer\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":3,\"superColumn\":false,\"tableId\":1,\"updateBy\":\"\",\"updateTime\":\"2024-08-04 14:27:40\",\"usableColumn\":false},{\"capJavaField\":\"StudentSchool\",\"columnComment\":\"学生学校\",\"columnId\":4,\"columnName\":\"student_school\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 13:3', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 17:01:13', 329);
INSERT INTO `sys_oper_log` VALUES (38, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.add()', 'POST', 1, 'admin', '行政部门', '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createBy\":\"admin\",\"icon\":\"nested\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuName\":\"周边学校\",\"menuType\":\"M\",\"orderNum\":7,\"params\":{},\"parentId\":0,\"path\":\"schools\",\"status\":\"0\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 17:12:13', 53);
INSERT INTO `sys_oper_log` VALUES (39, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.edit()', 'PUT', 1, 'admin', '行政部门', '/system/menu', '127.0.0.1', '内网IP', '{\"children\":[],\"createTime\":\"2024-08-04 17:12:13\",\"icon\":\"nested\",\"isCache\":\"0\",\"isFrame\":\"1\",\"menuId\":1143,\"menuName\":\"周边学校\",\"menuType\":\"M\",\"orderNum\":8,\"params\":{},\"parentId\":0,\"path\":\"schools\",\"perms\":\"\",\"routeName\":\"\",\"status\":\"0\",\"updateBy\":\"admin\",\"visible\":\"0\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 17:12:18', 14);
INSERT INTO `sys_oper_log` VALUES (40, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', '行政部门', '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":\"aj_grade,aj_school\"}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 17:12:45', 93);
INSERT INTO `sys_oper_log` VALUES (41, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', '行政部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"grade\",\"className\":\"AjGrade\",\"columns\":[{\"capJavaField\":\"Id\",\"columnId\":148,\"columnName\":\"id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 17:12:44\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":true,\"insert\":true,\"isIncrement\":\"1\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"id\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":14,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"Grade\",\"columnComment\":\"年级\",\"columnId\":149,\"columnName\":\"grade\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 17:12:44\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"grade\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"EQ\",\"required\":false,\"sort\":2,\"superColumn\":false,\"tableId\":14,\"updateBy\":\"\",\"usableColumn\":false}],\"crud\":true,\"functionAuthor\":\"hysro\",\"functionName\":\"年级\",\"genPath\":\"/\",\"genType\":\"0\",\"moduleName\":\"schools\",\"options\":\"{\\\"parentMenuId\\\":1143}\",\"packageName\":\"com.hysro.ajkeeping\",\"params\":{\"parentMenuId\":1143},\"parentMenuId\":\"1143\",\"sub\":false,\"tableComment\":\"年级\",\"tableId\":14,\"tableName\":\"aj_grade\",\"tplCategory\":\"crud\",\"tplWebType\":\"element-ui\",\"tree\":false}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 17:13:23', 43);
INSERT INTO `sys_oper_log` VALUES (42, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', '行政部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"school\",\"className\":\"AjSchool\",\"columns\":[{\"capJavaField\":\"Id\",\"columnId\":150,\"columnName\":\"id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 17:12:44\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":true,\"insert\":true,\"isIncrement\":\"1\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"id\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":15,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"SchoolName\",\"columnComment\":\"学校\",\"columnId\":151,\"columnName\":\"school_name\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 17:12:44\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"schoolName\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"LIKE\",\"required\":false,\"sort\":2,\"superColumn\":false,\"tableId\":15,\"updateBy\":\"\",\"usableColumn\":false}],\"crud\":true,\"functionAuthor\":\"hysro\",\"functionName\":\"学校\",\"genPath\":\"/\",\"genType\":\"0\",\"moduleName\":\"schools\",\"options\":\"{\\\"parentMenuId\\\":1143}\",\"packageName\":\"com.hysro.ajkeeping\",\"params\":{\"parentMenuId\":1143},\"parentMenuId\":\"1143\",\"sub\":false,\"tableComment\":\"学校\",\"tableId\":15,\"tableName\":\"aj_school\",\"tplCategory\":\"crud\",\"tplWebType\":\"element-ui\",\"tree\":false}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 17:13:41', 3535);
INSERT INTO `sys_oper_log` VALUES (43, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'PUT', 1, 'admin', '行政部门', '/tool/gen', '127.0.0.1', '内网IP', '{\"businessName\":\"school\",\"className\":\"AjSchool\",\"columns\":[{\"capJavaField\":\"Id\",\"columnId\":150,\"columnName\":\"id\",\"columnType\":\"int(11)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 17:12:44\",\"dictType\":\"\",\"edit\":false,\"htmlType\":\"input\",\"increment\":true,\"insert\":true,\"isIncrement\":\"1\",\"isInsert\":\"1\",\"isPk\":\"1\",\"isRequired\":\"0\",\"javaField\":\"id\",\"javaType\":\"Long\",\"list\":false,\"params\":{},\"pk\":true,\"query\":false,\"queryType\":\"EQ\",\"required\":false,\"sort\":1,\"superColumn\":false,\"tableId\":15,\"updateBy\":\"\",\"usableColumn\":false},{\"capJavaField\":\"SchoolName\",\"columnComment\":\"学校\",\"columnId\":151,\"columnName\":\"school_name\",\"columnType\":\"varchar(255)\",\"createBy\":\"admin\",\"createTime\":\"2024-08-04 17:12:44\",\"dictType\":\"\",\"edit\":true,\"htmlType\":\"input\",\"increment\":false,\"insert\":true,\"isEdit\":\"1\",\"isIncrement\":\"0\",\"isInsert\":\"1\",\"isList\":\"1\",\"isPk\":\"0\",\"isQuery\":\"1\",\"isRequired\":\"0\",\"javaField\":\"schoolName\",\"javaType\":\"String\",\"list\":true,\"params\":{},\"pk\":false,\"query\":true,\"queryType\":\"LIKE\",\"required\":false,\"sort\":2,\"superColumn\":false,\"tableId\":15,\"updateBy\":\"\",\"usableColumn\":false}],\"crud\":true,\"functionAuthor\":\"hysro\",\"functionName\":\"学校\",\"genPath\":\"/\",\"genType\":\"0\",\"moduleName\":\"schools\",\"options\":\"{\\\"parentMenuId\\\":1143}\",\"packageName\":\"com.hysro.ajkeeping\",\"params\":{\"parentMenuId\":1143},\"parentMenuId\":\"1143\",\"sub\":false,\"tableComment\":\"学校\",\"tableId\":15,\"tableName\":\"aj_school\",\"tplCategory\":\"crud\",\"tplWebType\":\"element-ui\",\"tree\":false}', '{\"msg\":\"操作成功\",\"code\":200}', 0, NULL, '2024-08-04 17:13:41', 1583);
INSERT INTO `sys_oper_log` VALUES (44, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', '行政部门', '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\"tables\":\"aj_grade,aj_school\"}', NULL, 0, NULL, '2024-08-04 17:13:50', 56);

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '岗位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, '0', 'admin', '2024-07-14 20:02:50', '', NULL, '');
INSERT INTO `sys_post` VALUES (4, 'user', '普通员工', 2, '0', 'admin', '2024-07-14 20:02:50', 'admin', '2024-08-04 13:37:34', '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '部门树选择项是否关联显示',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin', '2024-07-14 20:02:50', '', NULL, '超级管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '2', 1, 1, '0', '0', 'admin', '2024-07-14 20:02:50', 'admin', '2024-08-04 13:45:04', '普通角色');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (2, 100);
INSERT INTO `sys_role_dept` VALUES (2, 101);
INSERT INTO `sys_role_dept` VALUES (2, 105);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 'admin', '爱家', '00', 'aj@qq.com', '15888888888', '0', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2024-08-04 16:12:21', 'admin', '2024-07-14 20:02:50', '', '2024-08-04 16:12:20', '管理员');
INSERT INTO `sys_user` VALUES (2, 105, 'ry', '若依', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '2', '127.0.0.1', '2024-07-14 20:02:50', 'admin', '2024-07-14 20:02:50', '', NULL, '测试员');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);

SET FOREIGN_KEY_CHECKS = 1;
