package com.xiao.xbcp.controller;

import com.xiao.xbcp.dto.ClassifyAnalyzerSearchDto;
import com.xiao.xbcp.service.setting.SettingService;
import com.xiao.xbcp.vo.ClassifyAnalyzerVo;
import com.xiao.xbcp.vo.Page;
import com.xiao.xbcp.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 请描述你的文件
 *
 * @author 请在这里署名
 * @date 2020-10-28
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@RestController
@RequestMapping("/setting")
public class SettingController {

    @Autowired
    private SettingService settingService;

    @RequestMapping(value = "/init")
    public Response<Page<ClassifyAnalyzerVo>> pageClassifyAnalyzers(ClassifyAnalyzerSearchDto searchDto) {
        settingService.init();
        return  Response.success();
    }


}
