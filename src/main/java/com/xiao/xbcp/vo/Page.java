package com.xiao.xbcp.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author jiawei
 */
@Data
@Accessors(chain = true)
public class Page<T> {
    /**
     * 页码
     */
    private int pageNum;
    /**
     * 页大小
     */
    private int pageSize;
    /**
     * 总数
     */
    private long total;
    /**
     * 数据
     */
    private List<T> list;

    public static <T> Page<T> emptyPage() {
        return new Page<>();
    }
}
