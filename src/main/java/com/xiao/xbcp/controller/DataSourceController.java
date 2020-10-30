package com.xiao.xbcp.controller;

import com.xiao.xbcp.dto.*;
import com.xiao.xbcp.service.datasource.DataSourceManageService;
import com.xiao.xbcp.service.datasource.DataSourceService;
import com.xiao.xbcp.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Description: 请描述你的文件
 *
 * @author 请在这里署名
 * @date 2020-10-28
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@RestController
@RequestMapping("/dataSource")
public class DataSourceController {

    @Autowired
    private DataSourceService dataSourceService;

    @Autowired
    private DataSourceManageService dataSourceManageService;

    @RequestMapping(value = "/pageDataSources")
    public Response<Page<DataSourceListVo>> pageDataSources(DataSourceSearchDto searchDto) {
        return new Response<>(dataSourceManageService.pageDataSources(searchDto));
    }

    @RequestMapping(value = "/getDataSource")
    public Response<DataSourceDetailVo> getDataSource(long dataSourceId) {
        return new Response<>(dataSourceManageService.getDataSource(dataSourceId));
    }

    @PostMapping("/saveDataSource")
    public Response saveDataSource(@RequestBody DataSourceDetailDto dto){
        dataSourceManageService.saveDataSource(dto);
        return  Response.success();
    }

    @PostMapping("/deleteDataSource")
    public Response deleteDataSource(long dataSourceId){
        dataSourceManageService.deleteDataSource(dataSourceId);
        return  Response.success();
    }

}
