package com.xiao.xbcp.repository;


import com.github.pagehelper.PageHelper;
import com.xiao.xbcp.bo.JdbcDataSourceParam;
import com.xiao.xbcp.dto.DataSourceDetailDto;
import com.xiao.xbcp.dto.DataSourceSearchDto;
import com.xiao.xbcp.entity.DataSource;
import com.xiao.xbcp.repository.mapper.DataSourceMapper;
import com.xiao.xbcp.util.BeanUtil;
import com.xiao.xbcp.util.JsonUtil;
import com.xiao.xbcp.vo.DataSourceDetailVo;
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


    @Autowired
    private DataSourceMapper dataSourceMapper;

    public DataSourceDetailVo getDataSource(long dataSourceId) {
        DataSource ds = dataSourceMapper.getDataSource(dataSourceId);
        DataSourceDetailVo vo = BeanUtil.copy(ds,DataSourceDetailVo.class);
        vo.setParamsMap(JsonUtil.toMap(ds.getParamsJson()));
        return vo;
    }

    public Page<DataSourceListVo> pageDataSources(DataSourceSearchDto searchDto){
        Page<DataSourceListVo> dataSourceListVos = BeanUtil.toPage(PageHelper.startPage(searchDto.getPageNum(), searchDto.getPageSize()).doSelectPageInfo(
                () -> dataSourceMapper.listDataSources(searchDto)));
        return dataSourceListVos;
    }
    public void saveDataSource(DataSourceDetailDto dataSourceDetailDto){
        DataSource dataSource = BeanUtil.copy(dataSourceDetailDto,DataSource.class);
        dataSource.setParamsJson(JsonUtil.toJson(dataSourceDetailDto.getParamsMap()));
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



    public JdbcDataSourceParam getJdbcDataSourceParam(long dataSourceId){
        try {
            DataSource dataSource = dataSourceMapper.getDataSource(dataSourceId);
            JdbcDataSourceParam  jdbcDataSourceParam =  JsonUtil.toObject(dataSource.getParamsJson(),JdbcDataSourceParam.class);
            return jdbcDataSourceParam;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("get DataSource error");
        }
    }
}
