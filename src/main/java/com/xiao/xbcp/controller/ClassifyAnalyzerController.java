package com.xiao.xbcp.controller;

import com.xiao.xbcp.bo.ClassifyRule;
import com.xiao.xbcp.dto.ClassifyAnalyzerDto;
import com.xiao.xbcp.dto.ClassifyAnalyzerPropertiesDto;
import com.xiao.xbcp.dto.ClassifyAnalyzerSearchDto;
import com.xiao.xbcp.dto.ClassifyAnalyzerTaskSearchDto;
import com.xiao.xbcp.entity.ClassifyAnalyzerTask;
import com.xiao.xbcp.service.analysis.ClassifyAnalyzerManageService;
import com.xiao.xbcp.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import com.xiao.xbcp.service.analysis.ClassifyAnalyzerService;
/**
 * Description: 请描述你的文件
 *
 * @author 请在这里署名
 * @date 2020-10-28
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@RestController
@RequestMapping("/classifyAnalyzer")
public class ClassifyAnalyzerController {

    @Autowired
    private ClassifyAnalyzerService classifyAnalyzerService;

    @Autowired
    private ClassifyAnalyzerManageService classifyAnalyzerManageService;


    @RequestMapping(value = "/pageClassifyAnalyzers")
    public Response<Page<ClassifyAnalyzerVo>> pageClassifyAnalyzers(ClassifyAnalyzerSearchDto searchDto) {
        return new Response<>(classifyAnalyzerManageService.pageClassifyAnalyzers(searchDto));
    }

    @RequestMapping(value = "/pageClassifyAnalyzerTasks")
    public Response<Page<ClassifyAnalyzerTaskVo>> pageClassifyAnalyzerTasks(ClassifyAnalyzerTaskSearchDto searchDto) {
        return new Response<>(classifyAnalyzerManageService.pageClassifyAnalyzerTasks(searchDto));
    }

    @RequestMapping("/getClassAnalyzerPropertiesByClassifyAnalyzer")
    public Response<ClassifyAnalyzerPropertiesVo> getClassifyAnalyzerPropertiesByClassifyAnalyzer(long classifyAnalyzerId){
        ClassifyAnalyzerPropertiesVo vo = classifyAnalyzerManageService.getClassifyAnalyzerPropertiesByClassifyAnalyzer(classifyAnalyzerId);
        return  Response.of(vo);
    }

    @RequestMapping("/getInstanceData")
    public Response<List<Map<String, Object>>> getInstanceData(long analyzerId,int rows){
        List<Map<String, Object>>  datas = classifyAnalyzerService.getInstanceData(analyzerId,rows);
        return  Response.of(datas);
    }



    @PostMapping("/saveClassifyAnalyzer")
    public Response saveClassifyAnalyzer(@RequestBody ClassifyAnalyzerDto classifyAnalyzerDto){
        classifyAnalyzerManageService.saveClassifyAnalyzer(classifyAnalyzerDto);
        return  Response.success();
    }

    @PostMapping("/deleteClassifyAnalyzer")
    public Response deleteClassifyAnalyzer(long classifyAnalyzerId){
        classifyAnalyzerManageService.deleteClassifyAnalyzer(classifyAnalyzerId);
        return  Response.success();
    }

    @PostMapping("/saveClassifyAnalyzerProperties")
    public Response saveClassifyAnalyzerProperties(@RequestBody ClassifyAnalyzerPropertiesDto dto){
        classifyAnalyzerManageService.saveClassifyAnalyzerProperties(dto);
        return  Response.success();
    }

    @PostMapping("/runInstance")
    public Response runInstance(long analyzerId){
        classifyAnalyzerService.runInstance(analyzerId);
        return  Response.success();
    }
}
