package com.xiao.xbcp.bo;

import lombok.Data;

import java.util.List;

/**
 * @author KyleZeng
 */
@Data
public  class Classify {
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
    private Property property;

    @Data
    static public class  Property{
        private String name;
        private String value;
    }

    }