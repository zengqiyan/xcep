package com.xiao.xbcp.service.analysis;

import com.xiao.xbcp.bo.Classify;
import com.xiao.xbcp.bo.ClassifyRule;
import com.xiao.xbcp.service.analysis.handler.AnalyzerDataHandler;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * 分类分析器
 * @author KyleZeng
 * @date 2020-10-28
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */

public class ClassifyAnalyzer {
    @Getter
    @Setter
    private AnalyzerDataHandler analyzerDataHandler;
    @Getter
    @Setter
    private int rows;
    @Getter
    @Setter
    List<Classify> classifys;

    public List<ClassifyRule> analysisClassifyRule(){
        Apriori apriori = new Apriori();
        List<Map<String,Object>> oneDataList = new ArrayList<>();
        classifys.forEach(c->{
            Map<String,Object> map = new HashMap<>();
            map.put(c.getName(),c.getValue());
            oneDataList.add(map);
        });
        Map<String, Integer> map = apriori.apriori(oneDataList,rows,offset -> analyzerDataHandler.getDataList(offset));
        Map<String, Double> relationRulesMap = apriori.getRelationRules(map);
        List<Map.Entry<String,Double>> list = new ArrayList<>(relationRulesMap.entrySet());
        //然后通过比较器来实现排序
        //升序排序
        Collections.sort(list, Comparator.comparing(Map.Entry::getValue));
        List<ClassifyRule> rules = new ArrayList<>();
        for(Map.Entry<String,Double> mapping:list){
            ClassifyRule classifyRule = new ClassifyRule();
            String[] relationStrArr = mapping.getKey().split(Apriori.CON);
            List<String> ifItemStrs= Arrays.asList(relationStrArr[0].split(Apriori.ITEM_SPLIT));
            List<Classify> ifItem =  ifItemStrs.stream().map(item-> new Classify(item.split(Apriori.CLASSIFY_SPLIT)[0],item.split(Apriori.CLASSIFY_SPLIT)[1])).collect(Collectors.toList());
            List<String> thenItemStrs = Arrays.asList(relationStrArr[1].split(Apriori.ITEM_SPLIT));
            List<Classify> thenItem =  thenItemStrs.stream().map(item-> new Classify(item.split(Apriori.CLASSIFY_SPLIT)[0],item.split(Apriori.CLASSIFY_SPLIT)[1])).collect(Collectors.toList());
            classifyRule.setIfItem(ifItem);
            classifyRule.setThenItem(thenItem);
            classifyRule.setConfidence(mapping.getValue());
            rules.add(classifyRule);
        }
        return rules;
    }
}
