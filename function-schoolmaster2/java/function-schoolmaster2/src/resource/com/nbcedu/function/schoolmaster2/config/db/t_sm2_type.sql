/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : nbc_lib_shijia

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2013-12-11 19:17:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_sm2_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_sm2_type`;
CREATE TABLE `t_sm2_type` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT '',
  `moduleId` varchar(32) DEFAULT '',
  `mold` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sm2_type
-- ----------------------------
INSERT INTO `t_sm2_type` VALUES ('caiwu', '财务', '', '1');
INSERT INTO `t_sm2_type` VALUES ('daduifudaoyuan', '大队辅导员', '', '1');
INSERT INTO `t_sm2_type` VALUES ('deyu', '德育', 'linshishixiang', '1');
INSERT INTO `t_sm2_type` VALUES ('dibudaduifudaoyuan', '低部大队辅导员', '', '1');
INSERT INTO `t_sm2_type` VALUES ('dinianjibu', '低年级部', 'nianduzhongxin', '0');
INSERT INTO `t_sm2_type` VALUES ('dinianjibushuxue', '低年级部数学', 'nianduzhongxin', '0');
INSERT INTO `t_sm2_type` VALUES ('dinianjibuyuwen', '低年级部语文', 'nianduzhongxin', '0');
INSERT INTO `t_sm2_type` VALUES ('houqin', '后勤', '', '1');
INSERT INTO `t_sm2_type` VALUES ('jiaokeyan', '教科研', 'nianduzhongxin', '0');
INSERT INTO `t_sm2_type` VALUES ('jidi', '基地', 'nianduzhongxin', '0');
INSERT INTO `t_sm2_type` VALUES ('keji', '科技', 'zongjiehuibao', '0');
INSERT INTO `t_sm2_type` VALUES ('keren', '科任', 'zongjiehuibao', '0');
INSERT INTO `t_sm2_type` VALUES ('lantiangongcheng', '蓝天工程', 'linshishixiang', '1');
INSERT INTO `t_sm2_type` VALUES ('qitiaojiaoxue', '七条教学', '', '1');
INSERT INTO `t_sm2_type` VALUES ('shuba', '书吧', '', '1');
INSERT INTO `t_sm2_type` VALUES ('shuxue', '数学', 'zongjiehuibao', '0');
INSERT INTO `t_sm2_type` VALUES ('shuyuan', '书院', '', '1');
INSERT INTO `t_sm2_type` VALUES ('tiyujulebu', '体育俱乐部', '', '1');
INSERT INTO `t_sm2_type` VALUES ('xinxi', '信息', '', '1');
INSERT INTO `t_sm2_type` VALUES ('yangguanggongyishe', '阳光公益社', '', '1');
INSERT INTO `t_sm2_type` VALUES ('yuwen', '语文', 'nianduzhongxin', '0');

-- ----------------------------
-- Table structure for `t_sm2_type_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_sm2_type_user`;
CREATE TABLE `t_sm2_type_user` (
  `id` varchar(32) NOT NULL,
  `typeId` varchar(32) DEFAULT '',
  `userId` varchar(32) DEFAULT NULL,
  `USERNAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCF1ABABE3B6456CE` (`typeId`),
  CONSTRAINT `FKCF1ABABE3B6456CE` FOREIGN KEY (`typeId`) REFERENCES `t_sm2_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sm2_type_user
-- ----------------------------
INSERT INTO `t_sm2_type_user` VALUES ('1', 'deyu', 'J201108251340030003665', '李娟');
INSERT INTO `t_sm2_type_user` VALUES ('10', 'lantiangongcheng', 'J201108251316440044780', '郭志滨');
INSERT INTO `t_sm2_type_user` VALUES ('11', 'jiaokeyan', 'J201108251339290029173', '吕闽松');
INSERT INTO `t_sm2_type_user` VALUES ('12', 'yuwen', 'J201108251339290029173', '吕闽松');
INSERT INTO `t_sm2_type_user` VALUES ('13', 'dinianjibu', 'J20110825131606000671', '乔红');
INSERT INTO `t_sm2_type_user` VALUES ('14', 'dinianjibushuxue', 'J201108251316030003113', '刘颖');
INSERT INTO `t_sm2_type_user` VALUES ('15', 'dinianjibuyuwen', 'J201108251340330033726', '闫欣');
INSERT INTO `t_sm2_type_user` VALUES ('16', 'jidi', 'J201108251315440044322', '陈纲');
INSERT INTO `t_sm2_type_user` VALUES ('17', 'xinxi', 'J201108251334440044953', '汪忱');
INSERT INTO `t_sm2_type_user` VALUES ('18', 'keren', 'J201108251338350035319', '张欣欣');
INSERT INTO `t_sm2_type_user` VALUES ('19', 'tiyujulebu', 'J201108251338350035319', '张欣欣');
INSERT INTO `t_sm2_type_user` VALUES ('2', 'deyu', 'J201108251334360036264', '张婉');
INSERT INTO `t_sm2_type_user` VALUES ('20', 'caiwu', 'J201108251452280028210', '王继红');
INSERT INTO `t_sm2_type_user` VALUES ('21', 'houqin', 'J20110825133652005231', '李大明');
INSERT INTO `t_sm2_type_user` VALUES ('22', 'deyu', 'J201108241517430043889', '张均帅');
INSERT INTO `t_sm2_type_user` VALUES ('23', 'daduifudaoyuan', 'J201108241517430043889', '张均帅');
INSERT INTO `t_sm2_type_user` VALUES ('24', 'yangguanggongyishe', 'J201108241517430043889', '张均帅');
INSERT INTO `t_sm2_type_user` VALUES ('25', 'dibudaduifudaoyuan', 'J201108251315220022618', '赵慧霞');
INSERT INTO `t_sm2_type_user` VALUES ('3', 'shuyuan', 'J201108251334360036264', '张婉');
INSERT INTO `t_sm2_type_user` VALUES ('4', 'shuba', 'J201108251334360036264', '张婉');
INSERT INTO `t_sm2_type_user` VALUES ('5', 'deyu', 'J2011082513402900297', '张怡');
INSERT INTO `t_sm2_type_user` VALUES ('6', 'qitiaojiaoxue', 'J201108251334240024314', '王秀鲜');
INSERT INTO `t_sm2_type_user` VALUES ('7', 'shuxue', 'J201108251316100010228', '韩巧玲');
INSERT INTO `t_sm2_type_user` VALUES ('8', 'keren', 'J201108251316440044780', '郭志滨');
INSERT INTO `t_sm2_type_user` VALUES ('9', 'keji', 'J201108251316440044780', '郭志滨');
