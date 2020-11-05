CREATE TABLE `data_source` (
  `id` INTEGER NOT NULL PRIMARY KEY autoincrement,
  `name` varchar(100) DEFAULT NULL,
  `params_json varchar(1000) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `create_time` datetime,
  `create_user` INTEGER DEFAULT NULL,
	`update_time` datetime,
  `update_user` INTEGER DEFAULT NULL,
	`env_id` INTEGER ,
	`is_deleted` smallint(1)
);



CREATE TABLE `classify_analyzer` (
  `id` INTEGER NOT NULL PRIMARY KEY autoincrement ,
  `name` varchar(200) DEFAULT NULL ,
	`status` varchar(20) DEFAULT NULL ,
	`data_script` varchar(2000) DEFAULT NULL ,
    	`rows` int DEFAULT 0 ,
    	`total_count` int DEFAULT 0 ,
    	`classifys_json` varchar(2000) ,
      `data_source_type` varchar(20) DEFAULT NULL ,
    	`data_source_id` bigint,
  `create_time` datetime comment ,
  `create_user` INTEGER DEFAULT NULL ,
	`update_time` datetime comment ,
  `update_user` INTEGER DEFAULT NULL ,
	`env_id` INTEGER comment ,
	`is_deleted` smallint(1)
);




CREATE TABLE `classify_analyzer_task` (
  `id` INTEGER NOT NULL PRIMARY KEY autoincrement,
	`classify_analyzer_id` INTEGER ,
  `tag` varchar(200) DEFAULT NULL ,
	`status` varchar(20) DEFAULT NULL,
	`result` varchar(2000) DEFAULT NULL ,
  `create_time` datetime ,
  `create_user` INTEGER DEFAULT NULL ,
	`update_time` datetime,
  `update_user` INTEGER DEFAULT NULL ,
	`is_deleted` smallint(1)

);
