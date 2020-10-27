package com.xiao.xbcp.rule.analysis;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Description: 请描述你的文件
 *
 * @author 请在这里署名
 * @date 2020-10-27
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Data
public class DataManagerBox {
    private int nextOffSet;
    private List<Map<String,Object>> dataList;
}
