--创建数据库
CREATE DATABASE `huoxuan` /*!40100 DEFAULT CHARACTER SET utf8 */;

--生成每日清单表
CREATE TABLE `daily_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_id` int(11) NOT NULL,
  `information` text NOT NULL COMMENT '清单内容',
  `create_time` date NOT NULL,
  `delete_flag` int(11) NOT NULL DEFAULT '0' COMMENT '非0即删除',
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `daily_list_FK` (`create_id`),
  CONSTRAINT `daily_list_FK` FOREIGN KEY (`create_id`) REFERENCES `user_information` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000003 DEFAULT CHARSET=utf8 COMMENT='每日清单';

--插入数据
INSERT INTO huoxuan.daily_list
(id, create_id, information, create_time, delete_flag, end_time)
VALUES(10000001, 10001, 'TEST', '2021-02-08', 0, '2021-02-08 18:43:22.0');
INSERT INTO huoxuan.daily_list
(id, create_id, information, create_time, delete_flag, end_time)
VALUES(10000002, 10001, 'VAMPIRE', '2021-02-08', 0, '2021-02-08 16:43:22.0');

--生成用户信息表
-- huoxuan.user_information definition

CREATE TABLE `user_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(51) NOT NULL,
  `pwd` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `delete_flag` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_information_un` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10002 DEFAULT CHARSET=utf8;

--插入数据
INSERT INTO huoxuan.user_information
(id, name, pwd, email, delete_flag)
VALUES(10001, 'admin', 'admin', 'admin@qq.com', 0);


-- huoxuan.stock_info definition

CREATE TABLE `stock_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_id` int(11) NOT NULL,
  `stock_id` varchar(100) NOT NULL,
  `delete_flag` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `stock_info_FK` (`create_id`),
  CONSTRAINT `stock_info_FK` FOREIGN KEY (`create_id`) REFERENCES `user_information` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10003 DEFAULT CHARSET=utf8;

INSERT INTO huoxuan.stock_info
(id, create_id, stock_id, delete_flag)
VALUES(10000, 10001, '300377', 0);
INSERT INTO huoxuan.stock_info
(id, create_id, stock_id, delete_flag)
VALUES(10001, 10001, '002714', 0);
INSERT INTO huoxuan.stock_info
(id, create_id, stock_id, delete_flag)
VALUES(10002, 10001, '300892', 0);
