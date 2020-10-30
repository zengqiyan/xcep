package com.xiao.xbcp.constant;

/**
 * 数据源类型
 *
 * @author 请在这里署名
 * @date 2020-10-30
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
public enum DataSourceType {
    /**
     * mysql
     */
    MYSQL("MYSQL", "mysql"),

    /**
     * http
     */
    HTTP("HTTP", "http");


    private String value;

    private String name;

    DataSourceType(String value, String name) {
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
