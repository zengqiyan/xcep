package com.xiao.xbcp.datasource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Description: 请描述你的文件
 *
 * @author 请在这里署名
 * @date 2020-10-28
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
public class MySqlDataSourceComposit {
    Map<String,DataSource> dataSources;
    public DataSource getDataSource(String dataSourceId){
        return dataSources.get(dataSourceId);
    }
}
