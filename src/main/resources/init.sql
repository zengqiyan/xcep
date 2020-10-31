CREATE TABLE `data_source` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `params_json` varchar(1000) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `create_time` datetime,
  `create_user` bigint(11) DEFAULT NULL,
	`update_time` datetime,
  `update_user` bigint(11) DEFAULT NULL,
	`env_id` bigint(11) comment '环境id',
	`is_deleted` smallint(1) comment '是否删除：0.否，1.是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `classify_analyzer` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT comment 'id',
  `name` varchar(200) DEFAULT NULL comment '名称',
	`status` varchar(20) DEFAULT NULL comment '状态',
  `create_time` datetime comment '创建时间',
  `create_user` bigint(11) DEFAULT NULL comment '创建用户',
	`update_time` datetime comment '更新时间',
  `update_user` bigint(11) DEFAULT NULL comment '更新用户',
	`env_id` bigint(11) comment '环境id',
	`is_deleted` smallint(1) comment '是否删除：0.否，1.是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `classify_analyzer_properties` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
	`classify_analyzer_id` bigint(11) comment '分析器id',
  `data_script` varchar(2000) DEFAULT NULL comment '数据脚本',
	`rows` bigint DEFAULT 0 comment '分析量',
	`total_count` bigint DEFAULT 0 comment '总数量',
	`classifys_json` varchar(2000) comment '分类属性',
  `data_source_type` varchar(20) DEFAULT NULL comment '数据类型',
	`data_source_id` bigint comment '数据源id',
  `create_time` datetime comment '创建时间',
  `create_user` bigint(11) DEFAULT NULL comment '创建用户',
	`update_time` datetime comment '更新时间',
  `update_user` bigint(11) DEFAULT NULL comment '更新用户',
	`is_deleted` smallint(1) comment '是否删除：0.否，1.是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `classify_analyzer_task` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
	`classify_analyzer_id` bigint(11) comment '分析器id',
  `name` varchar(200) DEFAULT NULL comment '名称',
	`status` varchar(20) DEFAULT NULL comment '状态',
	`result` varchar(2000) DEFAULT NULL comment '结果',
  `create_time` datetime comment '创建时间',
  `create_user` bigint(11) DEFAULT NULL comment '创建用户',
	`update_time` datetime comment '更新时间',
  `update_user` bigint(11) DEFAULT NULL comment '更新用户',
	`is_deleted` smallint(1) comment '是否删除：0.否，1.是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
