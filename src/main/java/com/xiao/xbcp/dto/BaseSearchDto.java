
package com.xiao.xbcp.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseSearchDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer pageSize = 20;
    private Integer pageNum = 1;

}
