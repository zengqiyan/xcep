package com.xiao.xbcp.service.analysis;

import com.xiao.xbcp.service.analysis.handler.MySqlAnalyzerHandler;
import com.xiao.xbcp.service.datasource.DataSourceService;
import com.xiao.xbcp.bo.ClassifyRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
           handler.setDataSource(dataSourceService.getMySqlDataSource(analyzerProperties.getDataSourceId()));
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
