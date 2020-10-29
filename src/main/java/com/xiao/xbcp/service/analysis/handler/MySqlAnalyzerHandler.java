package com.xiao.xbcp.service.analysis.handler;

import com.xiao.xbcp.service.analysis.AnalyzerProperties;
import lombok.Setter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * mysql 分析器数据处理器
 * @author KyleZeng
 * @date 2020-10-27
 */
public class MySqlAnalyzerHandler extends AnalyzerDataHandler{

    @Setter
    private DataSource dataSource;

    private String queryDataSql;

    public MySqlAnalyzerHandler(AnalyzerProperties analyzerProperties) {
        super(analyzerProperties);
        queryDataSql = analyzerProperties.getDataScript();
    }

    @Override
    public List<Map<String, Object>> getDataList(int offset) {
        try {
            QueryRunner queryRunner = new QueryRunner();
            Connection connection = dataSource.getConnection();
            String sql = queryDataSql+" limit "+offset+","+getRows();
            List<Map<String, Object>> dataLists = queryRunner.query(connection,sql,new MapListHandler());
            connection.close();
            return dataLists;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
