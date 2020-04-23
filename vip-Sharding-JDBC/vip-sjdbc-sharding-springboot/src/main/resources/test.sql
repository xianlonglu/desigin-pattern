-- CREATE DATABASE `ds0` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
--  垂直拆分：user表绑定ds0节点（读写分离 + 垂直分库） ds0,ds0slave1,ds0slave2主从 
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `name` varchar(64) NOT NULL,
  `status` varchar(64) NOT NULL DEFAULT '1' COMMENT '状态：0非正常、1-正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `user` VALUES ('1', '1', '1');
INSERT INTO `user` VALUES ('3', '3', '1');

--  垂直拆分：student表绑定ds1节点（读写分离 + 垂直分库）ds1,ds1slave1,ds1slave2主从  
CREATE TABLE `student` (
  `id` bigint(20) NOT NULL,
  `name` varchar(64) NOT NULL,
  `status` varchar(64) NOT NULL DEFAULT '1' COMMENT '状态：0非正常、1-正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `student` VALUES ('1', '1', '1');
INSERT INTO `student` VALUES ('3', '3', '1');


-- role单库分表（不分库，只分表 + 读写分离案例）ds0,ds0slave1,ds0slave2主从 
CREATE TABLE `role_0` (
  `role_id` bigint(20) NOT NULL ,
  `name` varchar(64) NOT NULL,
  `status` varchar(64) NOT NULL DEFAULT '1' COMMENT '状态：0非正常、1-正常',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `role_1` (
  `role_id` bigint(20) NOT NULL ,
  `name` varchar(64) NOT NULL,
  `status` varchar(64) NOT NULL DEFAULT '1' COMMENT '状态：0非正常、1-正常',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `role_2` (
  `role_id` bigint(20) NOT NULL ,
  `name` varchar(64) NOT NULL,
  `status` varchar(64) NOT NULL DEFAULT '1' COMMENT '状态：0非正常、1-正常',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `role_3` (
  `role_id` bigint(20) NOT NULL ,
  `name` varchar(64) NOT NULL,
  `status` varchar(64) NOT NULL DEFAULT '1' COMMENT '状态：0非正常、1-正常',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `role_0` VALUES ('4', 'name4', 'status4');
INSERT INTO `role_0` VALUES ('8', 'name8', 'status8');

INSERT INTO `role_1` VALUES ('1', 'name1', 'status1');
INSERT INTO `role_1` VALUES ('5', 'name5', 'status5');

INSERT INTO `role_2` VALUES ('2', 'name2', 'status2');
INSERT INTO `role_2` VALUES ('6', 'name6', 'status6');

INSERT INTO `role_3` VALUES ('3', 'name3', 'status3');
INSERT INTO `role_3` VALUES ('7', 'name7', 'status7');


-- 多库分表 ds0,ds0slave1,ds0slave2主从、ds1,ds1slave1,ds1slave2
CREATE TABLE `user_info` (
  `user_id` bigint(19) NOT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `account` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `user_info` VALUES ('2', 'name2', 'account2', 'password2');
INSERT INTO `user_info` VALUES ('4', 'name4', 'account4', 'password4');
INSERT INTO `user_info` VALUES ('6', 'name6', 'account6', 'password6');
INSERT INTO `user_info` VALUES ('8', 'name8', 'account8', 'password8');

INSERT INTO `user_info` VALUES ('1', 'name1', 'account1', 'password1');
INSERT INTO `user_info` VALUES ('3', 'name3', 'account3', 'password3');
INSERT INTO `user_info` VALUES ('5', 'name5', 'account5', 'password5');
INSERT INTO `user_info` VALUES ('7', 'name7', 'account7', 'password7');


-- 广播表 ds0,ds0slave1,ds0slave2主从、ds1,ds1slave1,ds1slave2
CREATE TABLE `t_config` (
  `config_id` int(16) NOT NULL AUTO_INCREMENT,
  `para_name` varchar(255) DEFAULT NULL,
  `para_value` varchar(255) DEFAULT NULL,
  `para_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `t_config` VALUES ('1', 'name1', 'value1', 'test0');
INSERT INTO `t_config` VALUES ('2', 'name2', 'value2', 'desc2');
INSERT INTO `t_config` VALUES ('3', 'name3', 'value3', 'desc3');
INSERT INTO `t_config` VALUES ('4', 'name4', 'value4', 'desc4');
INSERT INTO `t_config` VALUES ('5', 'name5', 'value5', 'desc5');
INSERT INTO `t_config` VALUES ('6', 'name6', 'value6', 'desc6');
INSERT INTO `t_config` VALUES ('7', 'name7', 'value7', 'desc7');
INSERT INTO `t_config` VALUES ('8', 'name8', 'value8', 'desc8');
INSERT INTO `t_config` VALUES ('9', 'name9', 'value9', 'desc9');
INSERT INTO `t_config` VALUES ('10', 'name10', 'value10', 'desc10');


-- 分库分表 ds0,ds0slave1,ds0slave2主从、ds1,ds1slave1,ds1slave2
CREATE TABLE `t_order_0` (
  `order_id` bigint(19) NOT NULL,
  `user_id` bigint(19) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `t_order_1` (
  `order_id` bigint(19) NOT NULL,
  `user_id` bigint(19) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `t_order_item_0` (
  `item_id` bigint(19) NOT NULL,
  `order_id` bigint(19) NOT NULL,
  `user_id` bigint(19) NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `t_order_item_1` (
  `item_id` bigint(19) NOT NULL,
  `order_id` bigint(19) NOT NULL,
  `user_id` bigint(19) NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `t_order_0` VALUES ('102', '22');
INSERT INTO `t_order_0` VALUES ('104', '22');
INSERT INTO `t_order_0` VALUES ('106', '22');
INSERT INTO `t_order_0` VALUES ('108', '22');
INSERT INTO `t_order_item_0` VALUES ('102', '102', '22');
INSERT INTO `t_order_item_0` VALUES ('104', '104', '22');
INSERT INTO `t_order_item_0` VALUES ('106', '106', '22');
INSERT INTO `t_order_item_0` VALUES ('108', '108', '22');
INSERT INTO `t_order_1` VALUES ('101', '22');
INSERT INTO `t_order_1` VALUES ('103', '22');
INSERT INTO `t_order_1` VALUES ('105', '22');
INSERT INTO `t_order_1` VALUES ('107', '22');
INSERT INTO `t_order_item_1` VALUES ('101', '101', '22');
INSERT INTO `t_order_item_1` VALUES ('103', '103', '22');
INSERT INTO `t_order_item_1` VALUES ('105', '105', '22');
INSERT INTO `t_order_item_1` VALUES ('107', '107', '22');

INSERT INTO `t_order_0` VALUES ('112', '11');
INSERT INTO `t_order_0` VALUES ('114', '11');
INSERT INTO `t_order_0` VALUES ('116', '11');
INSERT INTO `t_order_0` VALUES ('118', '11');
INSERT INTO `t_order_item_0` VALUES ('112', '112', '11');
INSERT INTO `t_order_item_0` VALUES ('114', '114', '11');
INSERT INTO `t_order_item_0` VALUES ('116', '116', '11');
INSERT INTO `t_order_item_0` VALUES ('118', '118', '11');
INSERT INTO `t_order_1` VALUES ('111', '11');
INSERT INTO `t_order_1` VALUES ('113', '11');
INSERT INTO `t_order_1` VALUES ('115', '11');
INSERT INTO `t_order_1` VALUES ('117', '11');
INSERT INTO `t_order_item_1` VALUES ('111', '111', '11');
INSERT INTO `t_order_item_1` VALUES ('113', '113', '11');
INSERT INTO `t_order_item_1` VALUES ('115', '115', '11');
INSERT INTO `t_order_item_1` VALUES ('117', '117', '11');

;