package com.xiao.xbcp.service.analysis;

import com.alibaba.fastjson.JSON;
import com.xiao.xbcp.bo.ClassifyAnalyzerPropertiesBo;
import com.xiao.xbcp.constant.AnalyzerTaskStatus;
import com.xiao.xbcp.constant.DataSourceType;
import com.xiao.xbcp.dto.ClassifyAnalyzerTaskDto;
import com.xiao.xbcp.repository.ClassifyAnalyzerRepository;
import com.xiao.xbcp.service.analysis.handler.AnalyzerDataHandler;
import com.xiao.xbcp.service.analysis.handler.MySqlAnalyzerHandler;
import com.xiao.xbcp.service.datasource.DataSourceService;
import com.xiao.xbcp.bo.ClassifyRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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
    private ClassifyAnalyzerRepository classifyAnalyzerRepository;

    @Autowired
    private DataSourceService dataSourceService;

    private Map<Long, ClassifyAnalyzerInstance> analyzers = new HashMap<>();

    public List<Map<String, Object>> getInstanceData(long analyzerId,int rows){
        ClassifyAnalyzerPropertiesBo properties = classifyAnalyzerRepository.getClassifyAnalyzerPropertiesByClassifyAnalyzer(analyzerId);
        AnalyzerDataHandler handler = getAnalyzerDataHandler(properties);
        return handler.getDataList(rows);
    }
   @Async
   public void runInstance(long analyzerId){
       ClassifyAnalyzerInstance analyzer = getInstance(analyzerId);
       ClassifyAnalyzerTaskDto classifyAnalyzerTaskDto = new ClassifyAnalyzerTaskDto();
       classifyAnalyzerTaskDto.setClassifyAnalyzerId(analyzerId);
       classifyAnalyzerTaskDto.setName("Task_"+System.currentTimeMillis());
       classifyAnalyzerTaskDto.setStatus(AnalyzerTaskStatus.PROGRESS.getValue());
       classifyAnalyzerRepository.saveClassifyAnalyzerTask(classifyAnalyzerTaskDto);
       try {
           List<ClassifyRule> classifyRule = analyzer.analysisClassifyRule();
           classifyAnalyzerTaskDto.setResult(JSON.toJSONString(classifyRule)) ;
           classifyAnalyzerTaskDto.setStatus(AnalyzerTaskStatus.SUCCESS.getValue());
       }catch (Exception e){
           e.printStackTrace();
           classifyAnalyzerTaskDto.setStatus(AnalyzerTaskStatus.FAIL.getValue());
       }finally {
           classifyAnalyzerRepository.saveClassifyAnalyzerTask(classifyAnalyzerTaskDto);
       }
   }

    private ClassifyAnalyzerInstance getInstance(long analyzerId){
        if(analyzers.get(analyzerId)!=null){
            return analyzers.get(analyzerId);
        }
        ClassifyAnalyzerInstance analyzer = new ClassifyAnalyzerInstance();
        ClassifyAnalyzerPropertiesBo properties = classifyAnalyzerRepository.getClassifyAnalyzerPropertiesByClassifyAnalyzer(analyzerId);
        analyzer.setRows(properties.getRows());
        analyzer.setClassifys(properties.getClassifys());
        analyzer.setAnalyzerDataHandler(getAnalyzerDataHandler(properties));
        analyzers.put(analyzerId,analyzer);
        return analyzer;
    }
    private AnalyzerDataHandler getAnalyzerDataHandler(ClassifyAnalyzerPropertiesBo properties){
        if(DataSourceType.MYSQL.getValue().equals(properties.getDataSourceType())){
            MySqlAnalyzerHandler handler = new MySqlAnalyzerHandler(properties);
            handler.setDataSource(dataSourceService.getMySqlDataSource(properties.getDataSourceId()));
            return handler;
        }
        return null;
    }
}
