package com.xiao.xbcp.service.analysis;

import com.xiao.xbcp.bo.Classify;
import lombok.Data;

import java.util.List;

/**
 * Description: 请描述你的文件
 *
 * @author 请在这里署名
 * @date 2020-10-27
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Data
public class ClassAnalyzerProperties {
    private long id;
    private String dataScript;
    private long dataSourceId;
    private int rows;
    private String type;
    private List<Classify> classifys;
}
