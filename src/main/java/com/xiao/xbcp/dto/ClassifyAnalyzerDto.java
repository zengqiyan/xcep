package com.xiao.xbcp.dto;

import lombok.Data;

import java.util.List;

/**
 * Description: 请描述你的文件
 *
 * @author 请在这里署名
 * @date 2020-10-30
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Data
public class ClassifyAnalyzerDto {

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
     * 数据脚本
     */
    private String dataScript;
    /**
     * 行数
     */
    private int rows;
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
     * 环境id
     */
    private long envId;
}
