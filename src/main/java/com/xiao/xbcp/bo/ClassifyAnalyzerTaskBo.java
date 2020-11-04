package com.xiao.xbcp.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/*
 * classify_analyzer_task
 */
@Getter
@Setter
@Accessors(chain = true)
public class ClassifyAnalyzerTaskBo implements Serializable {

  private static final long serialVersionUID = 1L;

  private long id;
 /**
  * 分析器id
  */
  private long classifyAnalyzerId;
 /**
  * 标签
  */
  private String tag;
 /**
  * 状态
  */
  private String status;
 /**
  * 结果
  */
  private String result;


}