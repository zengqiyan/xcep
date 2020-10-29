package com.xiao.xbcp.bo;

import lombok.Data;

/**
 * @author KyleZeng
 */
@Data
public  class Classify {
    public Classify(String name, String value) {
        this.name = name;
        this.value = value;
    }

    private String name;
    private String value;
    }