package com.xiao.xbcp.rule.analysis;


import com.xiao.xbcp.rule.Rule;
import com.xiao.xbcp.rule.analysis.handler.AnalyzerInstanceHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * 规则分析器
 * @author Kyle
 * @date 2020-10-27
 * <p>
 */
public class RuleAnalyzerService {


    @Autowired
    private AnalyzerInstanceService analyzerInstanceService;
    /**
     * 状态分析
     * @param analyzerConfigId
     * @return
     */
    public List<AnalyzerInstance> getAnalyzerInstance(String analyzerConfigId){
        
        return null;
    }
    public List<Rule> classifyAnalysis(String analyzerConfigId){
        AnalyzerInstanceHandler handler = analyzerInstanceService.getAnalyzerInstanceHandler(analyzerConfigId);
        Apriori apriori = new Apriori();
        List<Map<String,List<String>>> oneDataList = new ArrayList<>();
        handler.getClassifys().forEach(c->{
            Map<String,List<String>> map = new HashMap<>();
            map.put(c.getName(),c.getValues());
            oneDataList.add(map);
        });
        Map<String, Integer> map = apriori.apriori(oneDataList, handler.getRows(), offset -> handler.getDataList(offset));
        List<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
        //然后通过比较器来实现排序
        //升序排序
        Collections.sort(list, Comparator.comparing(Map.Entry::getValue));

        for(Map.Entry<String,Integer> mapping:list){
            System.out.println(mapping.getKey()+":"+mapping.getValue());
        }
        return null;
    }
}

