package com.xiao.xbcp.rule.analysis;

import lombok.Data;

@Data
public  class Classify{
    public Classify(String name, String value) {
        this.name = name;
        this.value = value;
    }

    private String name;
        private String value;
    }