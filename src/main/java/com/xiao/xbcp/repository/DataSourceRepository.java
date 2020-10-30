package com.xiao.xbcp.repository;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.xiao.xbcp.bo.JdbcDataSourceParam;
import com.xiao.xbcp.dto.DataSourceDetailDto;
import com.xiao.xbcp.dto.DataSourceSearchDto;
import com.xiao.xbcp.entity.DataSource;
import com.xiao.xbcp.repository.mapper.DataSourceMapper;
import com.xiao.xbcp.util.BeanUtil;
import com.xiao.xbcp.vo.DataSourceDetailVo;
import com.xiao.xbcp.vo.DataSourceListVo;
import com.xiao.xbcp.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Map;

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
        vo.setParamsMap((Map<String, Object>) JSON.parse(ds.getParams()));
        return vo;
    }

    public Page<DataSourceListVo> pageDataSources(DataSourceSearchDto searchDto){
        Page<DataSourceListVo> dataSourceListVos = BeanUtil.toPage(PageHelper.startPage(searchDto.getPageNum(), searchDto.getPageSize()).doSelectPageInfo(
                () -> dataSourceMapper.listDataSources(searchDto)));
        return dataSourceListVos;
    }
    public void saveDataSource(DataSourceDetailDto dataSourceDetailDto){
        DataSource dataSource = BeanUtil.copy(dataSourceDetailDto,DataSource.class);
        dataSource.setParams(JSON.toJSONString(dataSourceDetailDto.getParamsMap()));
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
            JdbcDataSourceParam  jdbcDataSourceParam = (JdbcDataSourceParam) JSON.parse(dataSource.getParams());
            return jdbcDataSourceParam;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("get DataSource error");
        }
    }
}
