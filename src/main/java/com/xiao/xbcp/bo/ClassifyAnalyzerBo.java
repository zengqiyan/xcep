package com.xiao.xbcp.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * Description: 请描述你的文件
 *
 * @author 请在这里署名
 * @date 2020-10-30
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Getter
@Setter
@Accessors(chain = true)
public class ClassifyAnalyzerBo implements Serializable {
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

    private List<Classify> classifys;
}
