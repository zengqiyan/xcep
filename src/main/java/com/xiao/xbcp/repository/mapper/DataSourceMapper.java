package com.xiao.xbcp.repository.mapper;

import com.xiao.xbcp.dto.DataSourceSearchDto;
import com.xiao.xbcp.entity.DataSource;
import com.xiao.xbcp.vo.DataSourceListVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Datasource Mapper
 *
 * @author KyelZeng
 * @date 2020-10-29
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Mapper
public interface DataSourceMapper {

    @Select("select * from data_source where id = #{dataSourceId} and is_deleted = 0")
    public DataSource getDataSource(@Param("dataSourceId") String dataSourceId);

    @Select("select * from data_source where is_deleted = 0")
    public DataSourceListVo listDataSources(DataSourceSearchDto searchDto);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO data_source (name, params, type, create_time, create_user, update_time, update_user, env_id, is_deleted) VALUES (#{name}, #{params}, #{type}, #{createTime}, #{createUser}, #{updateTime}, #{updateUser}, #{envId}, #{isDeleted})")
    int insert(DataSource dataSource);

    @Insert("<script>INSERT INTO data_source (name, params, type, create_time, create_user, update_time, update_user, env_id, is_deleted) VALUES <foreach collection='dataSources' item='item' separator=','>(#{name}, #{params}, #{type}, #{createTime}, #{createUser}, #{updateTime}, #{updateUser}, #{envId}, #{isDeleted})</foreach></script>")
    int bulkInsert(@Param("dataSources") List<DataSource> dataSources);

    @Update("update data_source set is_deleted = 1 where id = #{dataSourceId} ")
    void softDelete(@Param("dataSourceId") long dataSourceId);

    @Update("update data_source set name = #{} where id = #{dataSourceId} ")
    void update(DataSource dataSource);
}
