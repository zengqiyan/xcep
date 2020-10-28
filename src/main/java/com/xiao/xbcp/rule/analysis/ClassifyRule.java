package com.xiao.xbcp.rule.analysis;

import lombok.Data;

import java.util.List;

/**
 * 规则
 * @author 请在这里署名
 * @date 2020-10-27
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Data
public class ClassifyRule {
    private List<Classify> ifItem;
    private List<Classify> thenItem;
    private double confidence;
}
