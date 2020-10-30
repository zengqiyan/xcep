package com.xiao.xbcp.vo;

import lombok.Data;

import java.util.Map;

/**
 * Description: 请描述你的文件
 *
 * @author 请在这里署名
 * @date 2020-10-29
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Data
public class DataSourceDetailVo {
    private String id;
    private String name;
    private String type;
    private Map<String,Object> paramsMap;
}
