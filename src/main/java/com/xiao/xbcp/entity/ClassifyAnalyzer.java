package com.xiao.xbcp.entity;

import java.io.Serializable;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/*
 * classify_analyzer
 */
@Getter
@Setter
@Accessors(chain = true)
public class ClassifyAnalyzer implements Serializable {
  private static final long serialVersionUID = 1L;

 /**
  * id
  */
  private long id;
 /**
  * 名称
  */
  private String name;
 /**
  * 状态
  */
  private String status;
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