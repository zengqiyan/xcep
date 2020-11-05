package com.xiao.xbcp.service.analysis;

import com.xiao.xbcp.bo.ClassifyAnalyzerBo;
import com.xiao.xbcp.bo.ClassifyRule;
import com.xiao.xbcp.constant.AnalyzerTaskStatus;
import com.xiao.xbcp.constant.DataSourceType;
import com.xiao.xbcp.dto.ClassifyAnalyzerTaskDto;
import com.xiao.xbcp.repository.ClassifyAnalyzerRepository;
import com.xiao.xbcp.service.analysis.handler.AnalyzerDataHandler;
import com.xiao.xbcp.service.analysis.handler.MySqlAnalyzerHandler;
import com.xiao.xbcp.service.datasource.DataSourceService;
import com.xiao.xbcp.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    public List<Map<String, Object>> getInstanceData(long analyzerId,int rows){
        ClassifyAnalyzerBo classifyAnalyzerBo = classifyAnalyzerRepository.getClassifyAnalyzerBo(analyzerId);
        AnalyzerDataHandler handler = getAnalyzerDataHandler(classifyAnalyzerBo);
        return handler.getDataList(rows);
    }
   @Async
   public void runInstance(long analyzerId){
       ClassifyAnalyzerInstance analyzer = getInstance(analyzerId);
       ClassifyAnalyzerTaskDto classifyAnalyzerTaskDto = new ClassifyAnalyzerTaskDto();
       classifyAnalyzerTaskDto.setClassifyAnalyzerId(analyzerId);
       classifyAnalyzerTaskDto.setTag("Task_"+analyzerId+"_"+ UUID.randomUUID().toString().replace("-", ""));
       classifyAnalyzerTaskDto.setStatus(AnalyzerTaskStatus.PROGRESS.getValue());
       classifyAnalyzerRepository.saveClassifyAnalyzerTask(classifyAnalyzerTaskDto);
       try {
           List<ClassifyRule> classifyRule = analyzer.analysisClassifyRule();
           classifyAnalyzerTaskDto.setResult(JsonUtil.toJson(classifyRule)) ;
           classifyAnalyzerTaskDto.setStatus(AnalyzerTaskStatus.SUCCESS.getValue());
           classifyAnalyzerRepository.updateClassifyAnalyzerTaskByTag(classifyAnalyzerTaskDto);
       }catch (Exception e){
           e.printStackTrace();
           classifyAnalyzerTaskDto.setStatus(AnalyzerTaskStatus.FAIL.getValue());
           classifyAnalyzerRepository.saveClassifyAnalyzerTask(classifyAnalyzerTaskDto);
       }
   }

    private ClassifyAnalyzerInstance getInstance(long analyzerId){
        ClassifyAnalyzerBo classifyAnalyzerBo = classifyAnalyzerRepository.getClassifyAnalyzerBo(analyzerId);
        ClassifyAnalyzerInstance analyzer = getInstance(classifyAnalyzerBo);
        return analyzer;
    }
    private ClassifyAnalyzerInstance getInstance(ClassifyAnalyzerBo classifyAnalyzerBo){
        ClassifyAnalyzerInstance analyzer = new ClassifyAnalyzerInstance();
        analyzer.setRows(classifyAnalyzerBo.getRows());
        analyzer.setClassifys(classifyAnalyzerBo.getClassifys());
        analyzer.setAnalyzerDataHandler(getAnalyzerDataHandler(classifyAnalyzerBo));
        return analyzer;
    }
    private AnalyzerDataHandler getAnalyzerDataHandler(ClassifyAnalyzerBo classifyAnalyzerBo){
        if(DataSourceType.MYSQL.getValue().equals(classifyAnalyzerBo.getDataSourceType())){
            MySqlAnalyzerHandler handler = new MySqlAnalyzerHandler(classifyAnalyzerBo);
            handler.setDataSource(dataSourceService.getMySqlDataSource(classifyAnalyzerBo.getDataSourceId()));
            return handler;
        }
        return null;
    }
}
