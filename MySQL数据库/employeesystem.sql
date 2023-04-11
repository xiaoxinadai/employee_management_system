/*
 Navicat Premium Data Transfer

 Source Server         : 本地连接
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : employeesystem

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 11/04/2023 11:05:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_staff
-- ----------------------------
DROP TABLE IF EXISTS `t_staff`;
CREATE TABLE `t_staff`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '作为主键自增长id，记录员工数变化',
  `staff_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '员工的工号',
  `staff_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '***' COMMENT '员工的姓名',
  `staff_job` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '***' COMMENT '员工的工作岗位',
  `staff_sex` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工的性别',
  `staff_age` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工的年龄',
  `staff_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工的邮箱',
  `staff_entrytime` date NULL DEFAULT NULL COMMENT '员工入职日期',
  `staff_workingyears` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工工龄',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 80 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_staff
-- ----------------------------
INSERT INTO `t_staff` VALUES (1, '2204', '溥溪澈', '***', '男', '19', '1234@', '2022-01-01', '1');
INSERT INTO `t_staff` VALUES (2, '2201', '张三', '***', '男', '20', '4563', '2022-08-15', '7');
INSERT INTO `t_staff` VALUES (3, '2202', '王五', '***', '男', '21', '3848', '2022-08-07', '4');
INSERT INTO `t_staff` VALUES (4, '2203', '李四', '***', '男', '20', '6458', '2022-08-10', '5');
INSERT INTO `t_staff` VALUES (5, '2205', '续兰', '***', '女', '20', '3268', '2022-08-14', '5');
INSERT INTO `t_staff` VALUES (6, '2206', '耿华灿', '***', '女', '22', '1452', '2022-08-15', '6');
INSERT INTO `t_staff` VALUES (7, '2207', '邓乐珍', '***', '女', '23', '5786', '2022-08-15', '8');
INSERT INTO `t_staff` VALUES (8, '2208', '皇甫从云', '***', '男', '27', '4567', '2022-08-13', '3');
INSERT INTO `t_staff` VALUES (9, '2209', '扶清', '***', '男', '29', '9857', '2022-08-15', '9');
INSERT INTO `t_staff` VALUES (10, '2210', '明寻梅', '***', '女', '25', '2134', '2019-01-02', '4');
INSERT INTO `t_staff` VALUES (11, '2211', '老王', '2222', '男', '22', 'laowang123321@123.com', '2022-06-09', '2');
INSERT INTO `t_staff` VALUES (12, '2276', '侯致远', '服务支持部', '男', '41', 'hou415@gmail.com', '2015-03-01', '6');
INSERT INTO `t_staff` VALUES (13, '2285', '宋晓明', '人力资源部', '男', '49', 'xiaoming6@yahoo.com', '1999-01-07', '7');
INSERT INTO `t_staff` VALUES (14, '2267', '傅岚', '服务支持部', '女', '35', 'fulan@outlook.com', '1996-06-01', '2');
INSERT INTO `t_staff` VALUES (15, '2241', '潘子韬', '服务支持部', '男', '35', 'panzit71@outlook.com', '2015-03-15', '2');
INSERT INTO `t_staff` VALUES (16, '2268', '魏子韬', '研究及开发部', '男', '40', 'weiz@mail.com', '1990-11-30', '7');
INSERT INTO `t_staff` VALUES (17, '2233', '孟安琪', '人力资源部', '女', '26', 'menganq328@yahoo.com', '1993-09-18', '6');
INSERT INTO `t_staff` VALUES (18, '2296', '薛安琪', '生产部', '女', '30', 'xue2@icloud.com', '1997-12-22', '6');
INSERT INTO `t_staff` VALUES (19, '2237', '邓安琪', '采购部', '女', '19', 'deng724@outlook.com', '2017-10-03', '0');
INSERT INTO `t_staff` VALUES (20, '2273', '吕安琪', '物流部', '女', '23', 'lanqi@outlook.com', '2021-01-24', '2');
INSERT INTO `t_staff` VALUES (21, '2277', '陶宇宁', '会计及金融部', '男', '47', 'taoyunin74@gmail.com', '1998-09-01', '3');
INSERT INTO `t_staff` VALUES (22, '2251', '陶晓明', '外销部', '男', '25', 'xiat5@gmail.com', '1990-11-30', '6');
INSERT INTO `t_staff` VALUES (23, '2250', '陆致远', '物流部', '男', '29', 'zhiyuanlu@mail.com', '1998-11-25', '2');
INSERT INTO `t_staff` VALUES (24, '2289', '袁杰宏', '服务支持部', '男', '47', 'jieyua10@yahoo.com', '2004-01-05', '5');
INSERT INTO `t_staff` VALUES (25, '2254', '汤秀英', '公关部', '女', '48', 'tangxiuyi@gmail.com', '2009-01-03', '1');
INSERT INTO `t_staff` VALUES (26, '2274', '陶杰宏', '公关部', '男', '37', 'jtao@mail.com', '1999-06-01', '4');
INSERT INTO `t_staff` VALUES (27, '2227', '毛子异', '物流部', '男', '40', 'ziymao@outlook.com', '2020-11-08', '2');
INSERT INTO `t_staff` VALUES (28, '2299', '吴云熙', '市场部', '男', '33', 'wuyunxi@outlook.com', '2010-01-25', '4');
INSERT INTO `t_staff` VALUES (29, '2255', '徐睿', '信息技术支持部', '男', '40', 'ruixu@icloud.com', '2016-01-30', '9');
INSERT INTO `t_staff` VALUES (30, '2238', '徐致远', '产品质量部', '男', '25', 'xz903@gmail.com', '2019-01-29', '7');
INSERT INTO `t_staff` VALUES (31, '2225', '曹睿', '公关部', '男', '22', 'cao50@gmail.com', '2013-11-15', '2');
INSERT INTO `t_staff` VALUES (32, '2232', '侯云熙', '销售部', '男', '20', 'yunxihou@outlook.com', '2000-07-30', '2');
INSERT INTO `t_staff` VALUES (33, '2257', '王晓明', '人力资源部', '男', '31', 'wanxiaom@hotmail.com', '1992-05-07', '5');
INSERT INTO `t_staff` VALUES (34, '2288', '金杰宏', '人力资源部', '男', '24', 'jjiehong2@gmail.com', '2014-01-27', '4');
INSERT INTO `t_staff` VALUES (35, '2240', '段詩涵', '物流部', '女', '26', 'dshiha@outlook.com', '2012-12-20', '3');
INSERT INTO `t_staff` VALUES (36, '2226', '傅宇宁', '行政管理部', '男', '30', 'yufu@outlook.com', '1997-07-04', '8');
INSERT INTO `t_staff` VALUES (37, '2279', '侯宇宁', '生产部', '男', '37', 'yh926@yahoo.com', '1996-03-28', '8');
INSERT INTO `t_staff` VALUES (38, '2262', '姜璐', '会计及金融部', '女', '44', 'luji1115@outlook.com', '2000-06-26', '5');
INSERT INTO `t_staff` VALUES (39, '2229', '蒋睿', '外销部', '男', '47', 'jianrui1005@outlook.com', '1998-10-19', '6');
INSERT INTO `t_staff` VALUES (40, '2242', '沈晓明', '信息技术支持部', '男', '33', 'shenxi606@hotmail.com', '2018-11-07', '1');
INSERT INTO `t_staff` VALUES (41, '2256', '汤致远', '市场部', '男', '42', 'zhiyuantang@icloud.com', '1992-05-01', '0');
INSERT INTO `t_staff` VALUES (42, '2295', '谢晓明', '工程部', '男', '47', 'xiexiaoming810@mail.com', '1996-06-30', '5');
INSERT INTO `t_staff` VALUES (43, '2292', '程子韬', '会计及金融部', '男', '28', 'czitao@icloud.com', '2020-03-20', '7');
INSERT INTO `t_staff` VALUES (44, '2230', '薛杰宏', '销售部', '男', '44', 'jiehongxue@hotmail.com', '1999-10-21', '3');
INSERT INTO `t_staff` VALUES (45, '2271', '石致远', '工程部', '男', '44', 'shizhiyuan12@hotmail.com', '1990-09-12', '8');
INSERT INTO `t_staff` VALUES (46, '2258', '邵震南', '销售部', '男', '48', 'shao1231@mail.com', '2007-09-27', '8');
INSERT INTO `t_staff` VALUES (47, '2291', '张震南', '研究及开发部', '男', '25', 'zhangz@outlook.com', '1991-10-27', '1');
INSERT INTO `t_staff` VALUES (48, '2247', '谭宇宁', '信息技术支持部', '男', '47', 'tayuning@icloud.com', '2017-07-03', '1');
INSERT INTO `t_staff` VALUES (49, '2275', '金震南', '外销部', '男', '27', 'jinzhennan@icloud.com', '2020-05-02', '1');
INSERT INTO `t_staff` VALUES (50, '2260', '王晓明', '法律部', '男', '42', 'xiaw828@icloud.com', '2018-02-18', '5');
INSERT INTO `t_staff` VALUES (51, '2269', '秦璐', '产品质量部', '女', '32', 'lqin1208@outlook.com', '2004-05-29', '7');
INSERT INTO `t_staff` VALUES (52, '2218', '罗岚', '服务支持部', '女', '38', 'llu@outlook.com', '2002-09-02', '1');
INSERT INTO `t_staff` VALUES (53, '2214', '周杰宏', '销售部', '男', '32', 'jzhou@gmail.com', '1999-10-08', '8');
INSERT INTO `t_staff` VALUES (54, '2228', '萧杰宏', '物流部', '男', '44', 'jiehxiao@gmail.com', '2015-06-03', '5');
INSERT INTO `t_staff` VALUES (55, '2224', '陆秀英', '公关部', '女', '39', 'luxiuying@mail.com', '2021-03-05', '7');
INSERT INTO `t_staff` VALUES (56, '2219', '熊璐', '行政管理部', '女', '34', 'xiol@gmail.com', '2021-11-08', '9');
INSERT INTO `t_staff` VALUES (57, '2244', '熊岚', '物流部', '女', '46', 'xl421@mail.com', '2004-02-28', '0');
INSERT INTO `t_staff` VALUES (58, '2265', '钱璐', '会计及金融部', '女', '45', 'luqia1943@icloud.com', '2015-02-09', '4');
INSERT INTO `t_staff` VALUES (59, '2249', '段睿', '外销部', '男', '25', 'duan8@mail.com', '2005-03-29', '5');
INSERT INTO `t_staff` VALUES (60, '2259', '石岚', '市场部', '女', '43', 'shilan@yahoo.com', '2005-09-11', '6');
INSERT INTO `t_staff` VALUES (61, '2270', '梁璐', '公关部', '女', '26', 'lulia@icloud.com', '1993-01-26', '7');
INSERT INTO `t_staff` VALUES (77, '22111', '李华', '市场部', '女', '23', 'laowang123321@123.com', '2022-08-03', '8');
INSERT INTO `t_staff` VALUES (79, '2222222', '222222', '22222', '男', '22', '222222@222.com', '2022-08-01', '7');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '作为主键，记录用户使用情况',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '123456' COMMENT '密码，对密码进行加密,默认密码为123456',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin0', '3d9a11072fefe3f2edbbe55ef4ccf2ff');
INSERT INTO `t_user` VALUES (2, 'admin1', '2ed8c319e47e4173b247e9e79b0fc74b');

SET FOREIGN_KEY_CHECKS = 1;
