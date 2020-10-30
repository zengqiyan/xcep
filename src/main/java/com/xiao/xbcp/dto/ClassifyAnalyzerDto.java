package com.xiao.xbcp.dto;

import lombok.Data;

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
    /**
     * 名称
     */
    private String name;
    /**
     * 状态
     */
    private String status;
    /**
     * 环境id
     */
    private long envId;
}
