package com.xiao.xbcp.rule.analysis.handler;

import com.xiao.xbcp.rule.analysis.AnalyzerConfig;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import javax.sql.DataSource;
import java.sql.Connection;
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
public class MySqlAnalyzerInstanceHandler extends AnalyzerInstanceHandler{

    private DataSource dataSource;

    private String queryDataSql;
    @Override
    public List<Map<String, Object>> getDataList(int offset) {
        try {
            QueryRunner queryRunner = new QueryRunner();
            Connection connection = dataSource.getConnection();
            String sql = queryDataSql+" limit "+offset+","+getRows();
            List<Map<String, Object>> dataLists = queryRunner.query(connection,sql,new MapListHandler());
            return dataLists;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void init(AnalyzerConfig analyzerConfig){
        try {
            super.init(analyzerConfig);
            QueryRunner queryRunner = new QueryRunner();
            Connection connection = dataSource.getConnection();
            String sql = analyzerConfig.getCountScript();
            Map<String, Object> countMap = queryRunner.query(connection,sql,new MapHandler());
            setTotalCount((Integer) countMap.get("count"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
