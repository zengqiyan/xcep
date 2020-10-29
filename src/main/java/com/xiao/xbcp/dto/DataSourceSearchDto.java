
package com.xiao.xbcp.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DataSourceSearchDto extends BaseSearchDto {
   private String name;
   private String type;
}
