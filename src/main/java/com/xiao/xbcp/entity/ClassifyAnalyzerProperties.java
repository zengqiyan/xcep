package com.xiao.xbcp.entity;

import java.io.Serializable;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/*
 * classify_analyzer_properties
 */
@Getter
@Setter
@Accessors(chain = true)
public class ClassifyAnalyzerProperties implements Serializable {
  private static final long serialVersionUID = 1L;

  private long id;
 /**
  * 分析器id
  */
  private long classifyAnalyzerId;
 /**
  * 数据脚本
  */
  private String dataScript;
 /**
  * 总数量
  */
  private int totalCount;

 /**
  * 行数
  */
 private int rows;
 /**
  * 分类属性
  */
  private String classifysJson;
 /**
  * 数据类型
  */
  private String dataSourceType;
 /**
  * 数据源id
  */
  private long dataSourceId;
 /**
  * 创建时间
  */
  private Date createTime;
 /**
  * 创建用户
  */
  private long createUser;
 /**
  * 更新时间
  */
  private Date updateTime;
 /**
  * 更新用户
  */
  private long updateUser;
 /**
  * 环境id
  */
  private int envId;
 /**
  * 是否删除：0.否，1.是
  */
  private int isDeleted;
}