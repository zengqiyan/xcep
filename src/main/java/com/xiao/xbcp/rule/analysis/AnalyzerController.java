package com.xiao.xbcp.rule.analysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description: 请描述你的文件
 *
 * @author 请在这里署名
 * @date 2020-10-28
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@RestController
@RequestMapping("/analyzer")
public class AnalyzerController {
    @Autowired
    private ClassifyAnalyzerService classifyAnalyzerService;

    @RequestMapping("/analysisClassifyRule")
    public List<ClassifyRule>  analysisClassifyRule(String analyzerId){
        return classifyAnalyzerService.analysisClassifyRule(analyzerId);
    }
}
