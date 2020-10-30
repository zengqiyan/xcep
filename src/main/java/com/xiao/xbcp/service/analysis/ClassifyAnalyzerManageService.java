package com.xiao.xbcp.service.analysis;

import com.xiao.xbcp.dto.ClassifyAnalyzerDto;
import com.xiao.xbcp.dto.ClassifyAnalyzerPropertiesDto;
import com.xiao.xbcp.dto.ClassifyAnalyzerSearchDto;
import com.xiao.xbcp.dto.ClassifyAnalyzerTaskSearchDto;
import com.xiao.xbcp.repository.ClassifyAnalyzerRepository;
import com.xiao.xbcp.vo.ClassifyAnalyzerPropertiesVo;
import com.xiao.xbcp.vo.ClassifyAnalyzerTaskVo;
import com.xiao.xbcp.vo.ClassifyAnalyzerVo;
import com.xiao.xbcp.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classify Analyzer Manage Service
 * @date 2020-10-30
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Service
public class ClassifyAnalyzerManageService {
    @Autowired
    private ClassifyAnalyzerRepository classifyAnalyzerRepository;

    public Page<ClassifyAnalyzerVo> pageClassifyAnalyzers(ClassifyAnalyzerSearchDto searchDto){
        return classifyAnalyzerRepository.pageClassifyAnalyzers(searchDto);
    }
    public Page<ClassifyAnalyzerTaskVo> pageClassifyAnalyzerTasks(ClassifyAnalyzerTaskSearchDto searchDto) {
        return classifyAnalyzerRepository.pageClassifyAnalyzerTasks(searchDto);
    }

    public ClassifyAnalyzerPropertiesVo getClassifyAnalyzerPropertiesByClassifyAnalyzer(long classifyAnalyzerId) {
        return classifyAnalyzerRepository.getClassifyAnalyzerPropertiesVoByClassifyAnalyzer(classifyAnalyzerId);
    }

    public void saveClassifyAnalyzer(ClassifyAnalyzerDto classifyAnalyzer){
        classifyAnalyzerRepository.saveClassifyAnalyzer(classifyAnalyzer);
    }
    public void deleteClassifyAnalyzer(long id){
        classifyAnalyzerRepository.deleteClassifyAnalyzer(id);
    }
    public void saveClassifyAnalyzerProperties(ClassifyAnalyzerPropertiesDto dto){
        classifyAnalyzerRepository.saveClassifyAnalyzerProperties(dto);
    }
    public void deleteClassAnalyzerProperties(long id){
        classifyAnalyzerRepository.deleteClassifyAnalyzerProperties(id);
    }


}
