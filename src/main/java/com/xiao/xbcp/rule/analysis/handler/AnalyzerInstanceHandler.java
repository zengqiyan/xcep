package com.xiao.xbcp.rule.analysis.handler;

import com.xiao.xbcp.rule.analysis.AnalyzerConfig;
import com.xiao.xbcp.rule.analysis.Classify;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;

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
public abstract class AnalyzerInstanceHandler {


    public abstract  List<Map<String,Object>>  getDataList(int offset);

    public void init(AnalyzerConfig analyzerConfig){
        setRows(analyzerConfig.getRows());
        setClassifys(getClassifys());
    }
    private int totalCount;
    private int rows;
    private List<Classify> classifys;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public List<Classify> getClassifys() {
        return classifys;
    }

    public void setClassifys(List<Classify> classifys) {
        this.classifys = classifys;
    }
}
