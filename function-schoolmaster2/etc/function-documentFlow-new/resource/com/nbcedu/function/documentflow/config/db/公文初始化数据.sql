-- ----------------------------
-- 初始化配置文件
-- ----------------------------
INSERT INTO `t_documentflow_notifyprofile` VALUES ('3d3c8ce4e28911e0bc1c0025645a252a', 1, '普通', 1, '您有新的公文，请及时回复。', '1');
INSERT INTO `t_documentflow_notifyprofile` VALUES ('4c200abbe28911e0b6fd0025645a252a', 0, '紧急', 0, '您有紧急公文，请马上回复。', '1');
-- ----------------------------
-- 初始化提醒时间
-- ----------------------------
INSERT INTO `t_documentflow_notifytime` VALUES ('17517ff5e4dc11e0962e0025645a252a', 'IMMEDIATE', '发布时', '1');
INSERT INTO `t_documentflow_notifytime` VALUES ('178931c6e4dc11e0962e0025645a252a', 'AFTER1', '发布后1小时', '2');
INSERT INTO `t_documentflow_notifytime` VALUES ('17936af7e4dc11e0962e0025645a252a', 'AFTER24', '发布后24小时', '3');
INSERT INTO `t_documentflow_notifytime` VALUES ('179789a8e4dc11e0962e0025645a252a', 'AFTER72', '发布后72小时', '4');
INSERT INTO `t_documentflow_notifytime` VALUES ('179d5609e4dc11e0962e0025645a252a', 'LAST48', '过期前48小时', '5');
INSERT INTO `t_documentflow_notifytime` VALUES ('17a19bcae4dc11e0962e0025645a252a', 'LAST24', '过期前24小时', '6');
-- ----------------------------
-- 初始化提醒时间与配置文件关联关系
-- ----------------------------
INSERT INTO `t_documentflow_notifyprofile_notifytime` VALUES ('3d3c8ce4e28911e0bc1c0025645a252a', '17517ff5e4dc11e0962e0025645a252a');
INSERT INTO `t_documentflow_notifyprofile_notifytime` VALUES ('4c200abbe28911e0b6fd0025645a252a', '17517ff5e4dc11e0962e0025645a252a');
