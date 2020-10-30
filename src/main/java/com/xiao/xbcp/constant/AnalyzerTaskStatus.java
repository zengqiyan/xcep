package com.xiao.xbcp.constant;

/**
 * analyzer task status
 * @author KyleZeng
 * @date 2020-10-30
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
public enum AnalyzerTaskStatus {
    /**
     * 进行中
     */
    PROGRESS("PROGRESS", "进行中"),
    /**
     * 意向产品
     */
    SUCCESS("SUCCESS", "成功"),

    FAIL("FAIL", "失败");

    private String value;
    private String name;

    AnalyzerTaskStatus(String value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
