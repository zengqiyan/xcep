package com.xiao.xbcp.repository.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.xbcp.bo.JdbcDataSourceParam;
import com.xiao.xbcp.dto.*;
import com.xiao.xbcp.entity.ClassifyAnalyzer;
import com.xiao.xbcp.entity.ClassifyAnalyzerProperties;
import com.xiao.xbcp.entity.ClassifyAnalyzerTask;
import com.xiao.xbcp.entity.DataSource;
import com.xiao.xbcp.util.BeanUtil;
import com.xiao.xbcp.vo.*;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * DataSourceRepository
 * @author KyleZeng
 * @date 2020-10-29
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Mapper
public interface ClassifyAnalyzerMapper {

    @Select("SELECT * FROM classify_analyzer WHERE id=#{0}")
    ClassifyAnalyzer getById(long id);


    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO classify_analyzer (name, status, create_time, create_user, update_time, update_user, env_id, is_deleted) VALUES (#{name}, #{status}, #{createTime}, #{createUser}, #{updateTime}, #{updateUser}, #{envId}, #{isDeleted})")
    int insert(ClassifyAnalyzer classifyAnalyzer);

    @Insert("<script>INSERT INTO classify_analyzer (name, status, create_time, create_user, update_time, update_user, env_id, is_deleted) VALUES <foreach collection='classifyAnalyzers' item='item' separator=','>(#{name}, #{status}, #{createTime}, #{createUser}, #{updateTime}, #{updateUser}, #{envId}, #{isDeleted})</foreach></script>")
    int bulkInsert(@Param("classifyAnalyzers") List<ClassifyAnalyzer> classifyAnalyzers);

    @Select("SELECT * FROM classify_analyzer")
    List<ClassifyAnalyzerVo> listClassifyAnalyzers(ClassifyAnalyzerSearchDto searchDto);

    @Select("SELECT * FROM classify_analyzer_task")
    List<ClassifyAnalyzerTaskVo> listClassifyAnalyzerTasks(ClassifyAnalyzerTaskSearchDto searchDto);

    @Select("SELECT * FROM classify_analyzer_properties where classify_analyzer_id = #{classifyAnalyzerId}")
    ClassifyAnalyzerProperties getClassifyAnalyzerPropertiesByClassifyAnalyzer(long classifyAnalyzerId);







    void update(ClassifyAnalyzer classifyAnalyzer);

    @Update("update classify_analyzer set is_deleted = 1 where id = #{id} ")
    void softDelete(long id);

    @Update("<script> update  classify_analyzer_properties set " +
            "        <if test=' classifyAnalyzerId != null and  classifyAnalyzerId != \"\" '>" +
            "            classify_analyzer_id=#{classifyAnalyzerId}," +
            "        </if>" +
            "        <if test=' dataScript != null and  dataScript != \"\" '>" +
            "            data_script=#{dataScript}," +
            "        </if>" +
            "        <if test=' totalCount != 0 '>" +
            "            total_count=#{totalCount}," +
            "        </if>" +
            "        <if test=' classifys != null and  classifys != \"\" '>" +
            "            classifys_json=#{classifysJson}," +
            "        </if>" +
            "        <if test=' dataSourceType != null and  dataSourceType != \"\" '>" +
            "            data_source_type=#{dataSourceType}," +
            "        </if>" +
            "        <if test=' dataSourceId != 0'>" +
            "            data_source_id=#{dataSourceId}," +
            "        </if>" +
            "        update_time = #{updateTime} where id = #{id}" +
            "</script>")
    void updateClassifyAnalyzerProperties(ClassifyAnalyzerProperties classifyAnalyzerProperties);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO classify_analyzer_properties (classify_analyzer_id, data_script, total_count, classifys_json, data_source_type, data_source_id, create_time, create_user, update_time, update_user, is_deleted) VALUES (#{classifyAnalyzerId}, #{dataScript}, #{totalCount}, #{classifysJson}, #{dataSourceType}, #{dataSourceId}, #{createTime}, #{createUser}, #{updateTime}, #{updateUser}, #{isDeleted})")
    long insertClassifyAnalyzerProperties(ClassifyAnalyzerProperties classifyAnalyzerProperties);

    @Update("update classify_analyzer_properties set is_deleted = 1 where id = #{id} ")
    void softDeleteClassifyAnalyzerProperties(long id);

    @Update("<script> update  classify_analyzer_task set " +
            "        <if test=' status != null and  status != \"\" '>" +
            "            status=#{status}," +
            "        </if>" +
            "        <if test=' result != null and  result != \"\" '>" +
            "            result=#{result}," +
            "        </if>" +
            "        update_time = #{updateTime} where id = #{id}" +
            "</script>")
    void updateClassifyAnalyzerTask(ClassifyAnalyzerTask classifyAnalyzerTask);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO classify_analyzer_task (classify_analyzer_id, name, status, result, create_time, create_user, update_time, update_user, is_deleted) VALUES (#{classifyAnalyzerId}, #{name}, #{status}, #{result}, #{createTime}, #{createUser}, #{updateTime}, #{updateUser}, #{isDeleted})")
    long insertClassifyAnalyzerTask(ClassifyAnalyzerTask classifyAnalyzerTask);

}
