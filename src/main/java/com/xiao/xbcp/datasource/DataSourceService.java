package com.xiao.xbcp.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

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

    public DataSource getMySqlDataSource(String dataSourceId){
        JdbcDataSourceParam jdbcDataSourceParam = new JdbcDataSourceParam();
        jdbcDataSourceParam.setJdbcUrl("jdbc:mysql://xhboss-test-public.mysql.polardb.rds.aliyuncs.com:3306/eduboss_xinghuo_test?useOldAliasMetadataBehavior=true&useUnicode=true&characterEncoding=utf-8");
        jdbcDataSourceParam.setUserName("xhboss_test");
        jdbcDataSourceParam.setPassword("bbAdb#6dfd#c4d");
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcDataSourceParam.getJdbcUrl());
        config.setUsername(jdbcDataSourceParam.getUserName());
        config.setPassword(jdbcDataSourceParam.getPassword());
        HikariDataSource ds = new HikariDataSource(config);
        return ds;
    }
}
