CREATE TABLE `data_source` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `params` varchar(1000) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `create_time` datetime,
  `create_user` bigint(11) DEFAULT NULL,
	`update_time` datetime,
  `update_user` bigint(11) DEFAULT NULL,
	`env_id` int(11) comment '环境id',
	`is_deleted` smallint(1) comment '是否删除：0.否，1.是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `classify_analyzer` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
	`status` varchar(20) DEFAULT NULL,
  `create_time` datetime,
  `create_user` bigint(11) DEFAULT NULL,
	`update_time` datetime,
  `update_user` bigint(11) DEFAULT NULL,
	`env_id` int(11) comment '环境id',
	`is_deleted` smallint(1) comment '是否删除：0.否，1.是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `classify_analyzer_properties` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
	`classify_analyzer_id` int(11),
  `data_script` varchar(2000) DEFAULT NULL,
	`total_count` int DEFAULT 0,
	`classifys` varchar(2000),
  `data_type` varchar(20) DEFAULT NULL,
	`data_source_id` bigint,
  `create_time` datetime,
  `create_user` bigint(11) DEFAULT NULL,
	`update_time` datetime,
  `update_user` bigint(11) DEFAULT NULL,
	`env_id` int(11) comment '环境id',
	`is_deleted` smallint(1) comment '是否删除：0.否，1.是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `classify_analyzer_result` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
	`status` varchar(20) DEFAULT NULL,
	`result` varchar(2000) DEFAULT NULL,
  `create_time` datetime,
  `create_user` bigint(11) DEFAULT NULL,
	`update_time` datetime,
  `update_user` bigint(11) DEFAULT NULL,
	`env_id` int(11) comment '环境id',
	`is_deleted` smallint(1) comment '是否删除：0.否，1.是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

