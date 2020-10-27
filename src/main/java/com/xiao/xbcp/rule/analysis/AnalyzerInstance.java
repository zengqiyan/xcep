package com.xiao.xbcp.rule.analysis;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 分析器实例
 *
 * @author Kyle
 * @date 2020-10-27
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Data
public class AnalyzerInstance {

    private int totalCount;
    private int rows;
    private int offset;
    private List<Map<String, Object>> statusList;
}
