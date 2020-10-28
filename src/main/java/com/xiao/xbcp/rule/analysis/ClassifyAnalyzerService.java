package com.xiao.xbcp.rule.analysis;

import com.xiao.xbcp.datasource.DataSourceService;
import com.xiao.xbcp.rule.analysis.handler.AnalyzerDataHandler;
import com.xiao.xbcp.rule.analysis.handler.MySqlAnalyzerHandler;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 * 分类分析器
 * @author KyleZeng
 * @date 2020-10-28
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Service
public class ClassifyAnalyzerService {
   @Autowired
   private AnalyzerPropertiesService analyzerPropertiesService;

   @Autowired
   private DataSourceService dataSourceService;

   private Map<String,ClassifyAnalyzer> analyzers = new HashMap<>();

   public ClassifyAnalyzer getClassifyAnalyzer(String analyzerId){
       if(analyzers.get(analyzerId)!=null){
           return analyzers.get(analyzerId);
       }
       ClassifyAnalyzer analyzer = new ClassifyAnalyzer();
       AnalyzerProperties analyzerProperties = analyzerPropertiesService.getAnalyzerProperties(analyzerId);
       analyzer.setRows(analyzerProperties.getRows());
       analyzer.setClassifys(analyzerProperties.getClassifys());
       if("mysql".equals(analyzerProperties.getType())){
           MySqlAnalyzerHandler handler = new MySqlAnalyzerHandler(analyzerProperties);
           handler.setDataSource( dataSourceService.getMySqlDataSource(analyzerProperties.getDataSourceId()));
           analyzer.setAnalyzerDataHandler(handler);
       }
       analyzers.put(analyzerId,analyzer);
       return analyzer;
   }
   public List<ClassifyRule> analysisClassifyRule(String analyzerId){
       ClassifyAnalyzer analyzer = getClassifyAnalyzer(analyzerId);
       return  analyzer.analysisClassifyRule();
   }
}
