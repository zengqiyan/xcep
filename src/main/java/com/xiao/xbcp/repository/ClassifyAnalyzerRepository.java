package com.xiao.xbcp.repository;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.xiao.xbcp.bo.Classify;
import com.xiao.xbcp.bo.ClassifyAnalyzerPropertiesBo;
import com.xiao.xbcp.bo.ClassifyAnalyzerBo;
import com.xiao.xbcp.dto.*;
import com.xiao.xbcp.entity.ClassifyAnalyzer;
import com.xiao.xbcp.entity.ClassifyAnalyzerProperties;
import com.xiao.xbcp.entity.ClassifyAnalyzerTask;
import com.xiao.xbcp.repository.mapper.ClassifyAnalyzerMapper;
import com.xiao.xbcp.util.BeanUtil;
import com.xiao.xbcp.vo.ClassifyAnalyzerPropertiesVo;
import com.xiao.xbcp.vo.ClassifyAnalyzerTaskVo;
import com.xiao.xbcp.vo.ClassifyAnalyzerVo;
import com.xiao.xbcp.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * DataSourceRepository
 * @author KyleZeng
 * @date 2020-10-29
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Repository
public class ClassifyAnalyzerRepository {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

    @Autowired
    private ClassifyAnalyzerMapper classifyAnalyzerMapper;

    public ClassifyAnalyzerBo getClassifyAnalyzerBo(long id) {
        return BeanUtil.copy(classifyAnalyzerMapper.getById(id),ClassifyAnalyzerBo.class);
    }

    public Page<ClassifyAnalyzerVo> pageClassifyAnalyzers(ClassifyAnalyzerSearchDto searchDto){
        Page<ClassifyAnalyzerVo> vos = BeanUtil.toPage( PageHelper.startPage(searchDto.getPageNum(), searchDto.getPageSize()).doSelectPageInfo(
                () -> classifyAnalyzerMapper.listClassifyAnalyzers(searchDto)));
        return vos;
    }

    public Page<ClassifyAnalyzerTaskVo> pageClassifyAnalyzerTasks(ClassifyAnalyzerTaskSearchDto searchDto) {
        Page<ClassifyAnalyzerTaskVo> vos = BeanUtil.toPage( PageHelper.startPage(searchDto.getPageNum(), searchDto.getPageSize()).doSelectPageInfo(
                () -> classifyAnalyzerMapper.listClassifyAnalyzerTasks(searchDto)));
        return vos;
    }

    public void saveClassifyAnalyzer(ClassifyAnalyzerDto classifyAnalyzerDto){
        ClassifyAnalyzer classifyAnalyzer = BeanUtil.copy(classifyAnalyzerDto, ClassifyAnalyzer.class);
        if(classifyAnalyzer.getId()!=0){
            classifyAnalyzerMapper.update(classifyAnalyzer);
        }else {
            classifyAnalyzer.setIsDeleted(0);
            classifyAnalyzer.setCreateTime(new Date());
            classifyAnalyzerMapper.insert(classifyAnalyzer);
        }

    }
    public void deleteClassifyAnalyzer(long id){
        classifyAnalyzerMapper.softDelete(id);
    }

    public ClassifyAnalyzerPropertiesVo getClassifyAnalyzerPropertiesVoByClassifyAnalyzer(long classifyAnalyzerId) {
        ClassifyAnalyzerProperties properties = classifyAnalyzerMapper.getClassifyAnalyzerPropertiesByClassifyAnalyzer(classifyAnalyzerId);
        return BeanUtil.copy(properties,ClassifyAnalyzerPropertiesVo.class);
    }

    public ClassifyAnalyzerPropertiesBo getClassifyAnalyzerPropertiesByClassifyAnalyzer(long classifyAnalyzerId) {
        ClassifyAnalyzerProperties properties = classifyAnalyzerMapper.getClassifyAnalyzerPropertiesByClassifyAnalyzer(classifyAnalyzerId);
        List<Map<String,Object>> classifyParamList = (List<Map<String, Object>>) JSON.parse(properties.getClassifysJson());
        List<Classify> classifies = new ArrayList<>();
        classifyParamList.forEach(c->{
            String classifyName = c.get("name").toString();
            String classifyValue = c.get("value").toString();
            List<Map<String,Object>> classProperties = (List<Map<String,Object>>)c.get("properties");
            classProperties.forEach(cp->{
                Classify classify = new Classify();
                classify.setName(classifyName);
                classify.setValue(classifyValue);
                Classify.Property property = new Classify.Property();
                property.setName(cp.get("name").toString());
                property.setValue(cp.get("value").toString());
                classify.setProperty(property);
                classifies.add(classify);
            });
        });
        ClassifyAnalyzerPropertiesBo vo = BeanUtil.copy(properties, ClassifyAnalyzerPropertiesBo.class);
        vo.setClassifys(classifies);
        return vo;
    }

    public void saveClassifyAnalyzerTask(ClassifyAnalyzerTaskDto dto) {
        ClassifyAnalyzerTask task = BeanUtil.copy(dto, ClassifyAnalyzerTask.class);
        if(task.getId()!=0){
            task.setUpdateTime(new Date());
            classifyAnalyzerMapper.updateClassifyAnalyzerTask(task);
        }else {
            task.setIsDeleted(0);
            task.setCreateTime(new Date());
            long id = classifyAnalyzerMapper.insertClassifyAnalyzerTask(task);
            dto.setId(id);
        }
    }

    public void saveClassifyAnalyzerProperties(ClassifyAnalyzerPropertiesDto dto) {
        ClassifyAnalyzerProperties properties = BeanUtil.copy(dto,ClassifyAnalyzerProperties.class);
        if(dto.getClassifyList()!=null && dto.getClassifyList().size()>0){
            properties.setClassifysJson(JSON.toJSONString(dto.getClassifyList()));
        }
        if(dto.getId()!=0){
            properties.setUpdateTime(new Date());
            classifyAnalyzerMapper.updateClassifyAnalyzerProperties(properties);
        }else {
            properties.setIsDeleted(0);
            properties.setCreateTime(new Date());
            long id = classifyAnalyzerMapper.insertClassifyAnalyzerProperties(properties);
            dto.setId(id);
        }
    }

    public void deleteClassifyAnalyzerProperties(long id) {
        classifyAnalyzerMapper.softDeleteClassifyAnalyzerProperties(id);
    }


}
