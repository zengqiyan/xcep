package com.xiao.xbcp.rule.analysis;

import com.xiao.xbcp.rule.analysis.handler.AnalyzerInstanceHandler;
import com.xiao.xbcp.rule.analysis.handler.MySqlAnalyzerInstanceHandler;
import org.springframework.stereotype.Service;

/**
 * Description: 请描述你的文件
 *
 * @author 请在这里署名
 * @date 2020-10-27
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Service
public class AnalyzerInstanceService {
    public AnalyzerInstanceHandler getAnalyzerInstanceHandler(String analyzerConfigId){
        AnalyzerConfig analyzerConfig = new  AnalyzerConfig();
        if("mysql".equals(analyzerConfig.getType())){
            MySqlAnalyzerInstanceHandler handler = new MySqlAnalyzerInstanceHandler();
            handler.init(analyzerConfig);
            return handler;
        }else {
            return null;
        }
    }
}
