package com.xiao.xbcp.vo;

import lombok.Data;

import java.util.List;

/**
 * @author KyleZeng
 */
@Data
public  class ClassifyVo {
    /**
     * name
     */
    private String name;
    /**
     * value
     */
    private String value;
    /**
     * property
     */
    private List<Property> properties;

    @Data
    static class  Property{
        private String name;
        private String value;
    }

    }