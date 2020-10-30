package com.xiao.xbcp.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/*
 * classify_analyzer_task
 */
@Getter
@Setter
@Accessors(chain = true)
public class ClassifyAnalyzerTaskDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private long id;
 /**
  * 分析器id
  */
  private long classifyAnalyzerId;
 /**
  * 名称
  */
  private String name;
 /**
  * 状态
  */
  private String status;
 /**
  * 结果
  */
  private String result;


}