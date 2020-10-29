package com.xiao.xbcp.service.datasource;

import com.xiao.xbcp.bo.JdbcDataSourceParam;
import com.xiao.xbcp.repository.DataSourceRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 请描述你的文件
 *
 * @author 请在这里署名
 * @date 2020-10-28
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Service
public class DataSourceService {

    @Autowired
    private DataSourceRepository dataSourceRepository;

    private Map<String,DataSource> jdbcDataSources = new HashMap<>();

    public DataSource getMySqlDataSource(String dataSourceId){
        if(jdbcDataSources.get(dataSourceId)!=null){
            return jdbcDataSources.get(dataSourceId);
        }
        JdbcDataSourceParam jdbcDataSourceParam = dataSourceRepository.getJdbcDataSourceParam(dataSourceId);
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(jdbcDataSourceParam.getJdbcUrl());
        ds.setUsername(jdbcDataSourceParam.getUserName());
        ds.setPassword(jdbcDataSourceParam.getPassword());
        ds.setIdleTimeout(60000);
        ds.setConnectionTimeout(60000);
        ds.setValidationTimeout(3000);
        ds.setMaxLifetime(60000);
        ds.setMaximumPoolSize(10);
        jdbcDataSources.put(dataSourceId,ds);
        return ds;
    }
}
