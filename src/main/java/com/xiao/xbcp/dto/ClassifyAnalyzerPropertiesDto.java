package com.xiao.xbcp.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
 * classify_analyzer_properties
 */
@Getter
@Setter
@Accessors(chain = true)
public class ClassifyAnalyzerPropertiesDto implements Serializable {
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
  * 分类属性
  */
  private List<ClassifySaveDto> classifyList;
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