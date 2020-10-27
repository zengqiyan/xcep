package com.xiao.xbcp.rule.analysis;

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
public class AnalyzerConfig {
    private String analyzerConfigId;
    private String countScript;
    private String dataScript;
    private int rows;
    private String type;
    private List<Classify> classifys;

}
