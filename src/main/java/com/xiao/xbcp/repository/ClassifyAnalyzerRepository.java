package com.xiao.xbcp.repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.xiao.xbcp.bo.Classify;
import com.xiao.xbcp.bo.ClassifyAnalyzerBo;
import com.xiao.xbcp.dto.ClassifyAnalyzerDto;
import com.xiao.xbcp.dto.ClassifyAnalyzerSearchDto;
import com.xiao.xbcp.dto.ClassifyAnalyzerTaskDto;
import com.xiao.xbcp.dto.ClassifyAnalyzerTaskSearchDto;
import com.xiao.xbcp.entity.ClassifyAnalyzer;
import com.xiao.xbcp.entity.ClassifyAnalyzerTask;
import com.xiao.xbcp.repository.mapper.ClassifyAnalyzerMapper;
import com.xiao.xbcp.util.BeanUtil;
import com.xiao.xbcp.util.JsonUtil;
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
        ClassifyAnalyzer classifyAnalyzer = classifyAnalyzerMapper.getById(id);
        List<Map<String,Object>> classifyParamList = JsonUtil.toListMaps(classifyAnalyzer.getClassifysJson());
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
        ClassifyAnalyzerBo bo =  BeanUtil.copy(classifyAnalyzer,ClassifyAnalyzerBo.class);
        bo.setClassifys(classifies);
        return bo;
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
        if(classifyAnalyzerDto.getClassifyList()!=null && classifyAnalyzerDto.getClassifyList().size()>0){
            classifyAnalyzer.setClassifysJson(JsonUtil.toJson(classifyAnalyzerDto.getClassifyList()));
        }
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

    public void saveClassifyAnalyzerTask(ClassifyAnalyzerTaskDto dto) {
        ClassifyAnalyzerTask task = BeanUtil.copy(dto, ClassifyAnalyzerTask.class);
        if(task.getId()!=0){
            task.setUpdateTime(new Date());
            classifyAnalyzerMapper.updateClassifyAnalyzerTask(task);
        }else {
            task.setIsDeleted(0);
            task.setCreateTime(new Date());
            classifyAnalyzerMapper.insertClassifyAnalyzerTask(task);
        }
    }
    public void updateClassifyAnalyzerTaskByTag(ClassifyAnalyzerTaskDto dto) {
        ClassifyAnalyzerTask task = BeanUtil.copy(dto, ClassifyAnalyzerTask.class);
        task.setUpdateTime(new Date());
        classifyAnalyzerMapper.updateClassifyAnalyzerTaskByTag(task);
    }


}
