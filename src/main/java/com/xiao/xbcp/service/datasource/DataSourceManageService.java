package com.xiao.xbcp.service.datasource;

import com.xiao.xbcp.dto.DataSourceDetailDto;
import com.xiao.xbcp.dto.DataSourceSearchDto;
import com.xiao.xbcp.repository.DataSourceRepository;
import com.xiao.xbcp.vo.DataSourceListVo;
import com.xiao.xbcp.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description: 请描述你的文件
 *
 * @author 请在这里署名
 * @date 2020-10-29
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Service
public class DataSourceManageService {

    @Autowired
    private DataSourceRepository dataSourceRepository;

    public Page<DataSourceListVo> pageDataSources(DataSourceSearchDto searchDto){
       return dataSourceRepository.pageDataSources(searchDto);
    }
    public void saveDataSource(DataSourceDetailDto dataSourceDetailDto){
         dataSourceRepository.saveDataSource(dataSourceDetailDto);
    }
    public void deleteDataSource(long dataSourceId){
        dataSourceRepository.deleteDataSource(dataSourceId);
    }

}
