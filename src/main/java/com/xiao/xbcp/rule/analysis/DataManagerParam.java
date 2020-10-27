package com.xiao.xbcp.rule.analysis;

import lombok.Data;

/**
 * Description: 请描述你的文件
 *
 * @author 请在这里署名
 * @date 2020-10-27
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Data
public class DataManagerParam {
    private int totalCount;
    private int offset;

    public DataManagerParam(int totalCount, int offset) {
        this.totalCount = totalCount;
        this.offset = offset;
    }
}
