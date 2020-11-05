package com.xiao.xbcp.repository.mapper;

import com.xiao.xbcp.dto.ClassifyAnalyzerSearchDto;
import com.xiao.xbcp.dto.ClassifyAnalyzerTaskSearchDto;
import com.xiao.xbcp.entity.ClassifyAnalyzer;
import com.xiao.xbcp.entity.ClassifyAnalyzerTask;
import com.xiao.xbcp.vo.ClassifyAnalyzerTaskVo;
import com.xiao.xbcp.vo.ClassifyAnalyzerVo;
import org.apache.ibatis.annotations.*;

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
    @Insert("INSERT INTO classify_analyzer (name, status,data_script, total_count,rows, classifys_json, data_source_type, data_source_id, create_time, create_user, update_time, update_user, env_id, is_deleted) VALUES (#{name}, #{status}, #{dataScript}, #{totalCount}, #{rows},#{classifysJson}, #{dataSourceType}, #{dataSourceId},#{createTime}, #{createUser}, #{updateTime}, #{updateUser}, #{envId}, #{isDeleted})")
    int insert(ClassifyAnalyzer classifyAnalyzer);


    @Select("SELECT * FROM classify_analyzer")
    List<ClassifyAnalyzerVo> listClassifyAnalyzers(ClassifyAnalyzerSearchDto searchDto);

    @Select("SELECT * FROM classify_analyzer_task where classify_analyzer_id=#{classifyAnalyzerId}")
    List<ClassifyAnalyzerTaskVo> listClassifyAnalyzerTasks(ClassifyAnalyzerTaskSearchDto searchDto);

    @Update("<script> update  classify_analyzer set" +
            "        <if test=' name != null and  name != \"\" '>" +
            "            name=#{name}," +
            "        </if>" +
            "        <if test=' status != null and  status != \"\" '>" +
            "            status=#{status}," +
            "        </if>" +
            "        <if test=' dataScript != null and  dataScript != \"\" '>" +
            "             data_script=#{dataScript}," +
            "        </if>" +
            "        <if test=' totalCount != 0 '>" +
            "             total_count=#{totalCount}," +
            "        </if>" +
            "        <if test=' classifysJson != null and  classifysJson != \"\" '>" +
            "             classifys_json=#{classifysJson}," +
            "        </if>" +
            "        <if test=' dataSourceType != null and  dataSourceType != \"\" '>" +
            "             data_source_type=#{dataSourceType}," +
            "        </if>" +
            "        <if test=' dataSourceId != 0'>" +
            "             data_source_id=#{dataSourceId}," +
            "        </if>" +
            "         update_time = #{updateTime} where id = #{id}" +
            "</script>")
    void update(ClassifyAnalyzer classifyAnalyzer);

    @Update("update classify_analyzer set is_deleted = 1 where id = #{id} ")
    void softDelete(long id);


    @Update("update classify_analyzer_properties set is_deleted = 1 where id = #{id} ")
    void softDeleteClassifyAnalyzerProperties(long id);

    @Update("<script> update  classify_analyzer_task set" +
            "        <if test=' status != null and  status != \"\" '>" +
            "             status=#{status}," +
            "        </if>" +
            "        <if test=' result != null and  result != \"\" '>" +
            "             result=#{result}," +
            "        </if>" +
            "         update_time = #{updateTime} where id = #{id}" +
            "</script>")
    void updateClassifyAnalyzerTask(ClassifyAnalyzerTask classifyAnalyzerTask);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO classify_analyzer_task (classify_analyzer_id, tag, status, result, create_time, create_user, update_time, update_user, is_deleted) VALUES (#{classifyAnalyzerId}, #{tag}, #{status}, #{result}, #{createTime}, #{createUser}, #{updateTime}, #{updateUser}, #{isDeleted})")
    long insertClassifyAnalyzerTask(ClassifyAnalyzerTask classifyAnalyzerTask);

    @Update("<script> update  classify_analyzer_task set" +
            "        <if test=' status != null and  status != \"\" '>" +
            "             status=#{status}," +
            "        </if>" +
            "        <if test=' result != null and  result != \"\" '>" +
            "             result=#{result}," +
            "        </if>" +
            "         update_time = #{updateTime} where tag = #{tag}" +
            "</script>")
    void updateClassifyAnalyzerTaskByTag(ClassifyAnalyzerTask task);
}
