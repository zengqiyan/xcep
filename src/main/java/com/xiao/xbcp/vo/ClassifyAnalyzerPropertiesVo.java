package com.xiao.xbcp.vo;

import com.xiao.xbcp.bo.Classify;
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
public class ClassifyAnalyzerPropertiesVo {
    private long id;
    /**
     * 分析量
     */
    private int rows;
    /**
     * 数据脚本
     */
    private String dataScript;

    /**
     * 数据源id
     */
    private long dataSourceId;
    /**
     * 总数量
     */
    private int totalCount;

    /**
     * 数据类型
     */
    private String dataSourceType;

    private List<ClassifyVo> classifyslist;
}
