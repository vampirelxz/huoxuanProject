--创建数据库
CREATE DATABASE IF NOT EXISTS `huoxuan` /*!40100 DEFAULT CHARACTER SET utf8 */;

-- huoxuan.account_info definition

CREATE TABLE IF NOT EXISTS `account_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_id` int(11) NOT NULL,
  `type` varchar(20) NOT NULL,
  `time` date NOT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `delete_flag` int(5) NOT NULL DEFAULT '0',
  `money` decimal(16,6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `account_info_FK` (`create_id`),
  CONSTRAINT `account_info_FK` FOREIGN KEY (`create_id`) REFERENCES `user_information` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100031 DEFAULT CHARSET=utf8;

-- huoxuan.algorithm_info definition

CREATE TABLE IF NOT EXISTS  `algorithm_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) NOT NULL,
  `content1` text NOT NULL,
  `content2` text,
  `content3` text,
  `type1` varchar(20) NOT NULL,
  `delete_flag` int(4) NOT NULL DEFAULT '0',
  `thinking1` text,
  `question` text NOT NULL,
  `type2` varchar(20) DEFAULT NULL,
  `type3` varchar(20) DEFAULT NULL,
  `thinking2` text,
  `thinking3` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1013 DEFAULT CHARSET=utf8;

-- huoxuan.algorithm_user definition

CREATE TABLE IF NOT EXISTS `algorithm_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `algorithm_id` int(11) NOT NULL,
  `content` text,
  `delete_flag` int(4) NOT NULL DEFAULT '0',
  `update_time` date NOT NULL,
  `time_expend` varchar(20) DEFAULT NULL,
  `space_expend` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `algorithm_user_FK` (`user_id`),
  KEY `algorithm_user_FK_1` (`algorithm_id`),
  CONSTRAINT `algorithm_user_FK` FOREIGN KEY (`user_id`) REFERENCES `user_information` (`id`),
  CONSTRAINT `algorithm_user_FK_1` FOREIGN KEY (`algorithm_id`) REFERENCES `algorithm_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100006 DEFAULT CHARSET=utf8 COMMENT='用户解题记录';

-- huoxuan.daily_list definition

CREATE TABLE IF NOT EXISTS `daily_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_id` int(11) NOT NULL,
  `information` text NOT NULL COMMENT '清单内容',
  `create_time` date NOT NULL,
  `delete_flag` int(11) NOT NULL DEFAULT '0' COMMENT '非0即删除',
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `daily_list_FK` (`create_id`),
  CONSTRAINT `daily_list_FK` FOREIGN KEY (`create_id`) REFERENCES `user_information` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000099 DEFAULT CHARSET=utf8 COMMENT='每日清单';

-- huoxuan.date_type definition

CREATE TABLE IF NOT EXISTS `date_type` (
  `type` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO huoxuan.date_type
(`type`)
VALUES('餐饮美食');
INSERT INTO huoxuan.date_type
(`type`)
VALUES('服饰装扮');
INSERT INTO huoxuan.date_type
(`type`)
VALUES('日用百货');
INSERT INTO huoxuan.date_type
(`type`)
VALUES('生活服务');
INSERT INTO huoxuan.date_type
(`type`)
VALUES('运动户外');
INSERT INTO huoxuan.date_type
(`type`)
VALUES('交通出行');
INSERT INTO huoxuan.date_type
(`type`)
VALUES('数码电器');
INSERT INTO huoxuan.date_type
(`type`)
VALUES('投资理财');

-- huoxuan.date_week definition

CREATE TABLE IF NOT EXISTS `date_week` (
  `num` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO huoxuan.date_week
(num)
VALUES(0);
INSERT INTO huoxuan.date_week
(num)
VALUES(1);
INSERT INTO huoxuan.date_week
(num)
VALUES(2);
INSERT INTO huoxuan.date_week
(num)
VALUES(3);
INSERT INTO huoxuan.date_week
(num)
VALUES(4);
INSERT INTO huoxuan.date_week
(num)
VALUES(5);
INSERT INTO huoxuan.date_week
(num)
VALUES(6);

-- huoxuan.fund_info definition

CREATE TABLE IF NOT EXISTS `fund_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fund_id` varchar(50) NOT NULL,
  `create_id` int(11) NOT NULL,
  `delete_flag` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fund_info_FK` (`create_id`),
  CONSTRAINT `fund_info_FK` FOREIGN KEY (`create_id`) REFERENCES `user_information` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10007 DEFAULT CHARSET=utf8;

-- huoxuan.note_info definition

CREATE TABLE IF NOT EXISTS `note_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_id` int(11) NOT NULL,
  `title` varchar(20) NOT NULL,
  `content` text,
  `update_time` date DEFAULT NULL,
  `delete_flag` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `note_info_FK` (`create_id`),
  CONSTRAINT `note_info_FK` FOREIGN KEY (`create_id`) REFERENCES `user_information` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100035 DEFAULT CHARSET=utf8 COMMENT='随手记';

-- huoxuan.stock_info definition

CREATE TABLE IF NOT EXISTS `stock_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_id` int(11) NOT NULL,
  `stock_id` varchar(100) NOT NULL,
  `delete_flag` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `stock_info_FK` (`create_id`),
  CONSTRAINT `stock_info_FK` FOREIGN KEY (`create_id`) REFERENCES `user_information` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10038 DEFAULT CHARSET=utf8;


-- huoxuan.user_information definition

CREATE TABLE IF NOT EXISTS `user_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(51) NOT NULL,
  `pwd` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `delete_flag` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_information_un` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10006 DEFAULT CHARSET=utf8;