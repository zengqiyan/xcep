package com.xiao.xbcp.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiao.xbcp.bo.JdbcDataSourceParam;
import com.xiao.xbcp.dto.DataSourceDetailDto;
import com.xiao.xbcp.dto.DataSourceSearchDto;
import com.xiao.xbcp.entity.DataSource;
import com.xiao.xbcp.repository.mapper.DataSourceMapper;
import com.xiao.xbcp.util.BeanUtil;
import com.xiao.xbcp.vo.DataSourceListVo;
import com.xiao.xbcp.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * DataSourceRepository
 * @author KyleZeng
 * @date 2020-10-29
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Repository
public class DataSourceRepository {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

    @Autowired
    private DataSourceMapper dataSourceMapper;

    public JdbcDataSourceParam getJdbcDataSourceParam(String dataSourceId){
        try {
            DataSource dataSource = dataSourceMapper.getDataSource(dataSourceId);
            JdbcDataSourceParam  jdbcDataSourceParam = objectMapper.readValue(dataSource.getParams(),
                    new TypeReference<JdbcDataSourceParam>() {});
            return jdbcDataSourceParam;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("get DataSource error");
        }
    }
    public Page<DataSourceListVo> pageDataSources(DataSourceSearchDto searchDto){
        PageInfo<DataSourceListVo> studentInfoBoPageInfo =
                PageHelper.startPage(searchDto.getPageNum(), searchDto.getPageSize()).doSelectPageInfo(
                        () -> dataSourceMapper.listDataSources(searchDto));
        Page<DataSourceListVo> dataSourceListVos = BeanUtil.copyPage(studentInfoBoPageInfo, DataSourceListVo.class);
        return dataSourceListVos;
    }
    public void saveDataSource(DataSourceDetailDto dataSourceDetailDto){
        DataSource dataSource = BeanUtil.copy(dataSourceDetailDto,DataSource.class);
        if(dataSourceDetailDto.getId()!=null){
            dataSourceMapper.update(dataSource);
        }else {
            dataSource.setIsDeleted(0);
            dataSource.setCreateTime(new Date());
            dataSourceMapper.insert(dataSource);
        }

    }
    public void deleteDataSource(long dataSourceId){
        dataSourceMapper.softDelete(dataSourceId);
    }
}
