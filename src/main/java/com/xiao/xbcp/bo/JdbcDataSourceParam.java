package com.xiao.xbcp.bo;

import lombok.Data;

/**
 * Description: 请描述你的文件
 *
 * @author 请在这里署名
 * @date 2020-10-28
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Data
public class JdbcDataSourceParam {
    private String jdbcUrl;
    private String userName;
    private String password;
}
