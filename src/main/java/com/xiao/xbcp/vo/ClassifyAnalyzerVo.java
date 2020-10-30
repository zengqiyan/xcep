package com.xiao.xbcp.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 请描述你的文件
 *
 * @author 请在这里署名
 * @date 2020-10-30
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Getter
@Setter
@Accessors(chain = true)
public class ClassifyAnalyzerVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 状态
     */
    private String status;
    /**
     * 创建时间
     */
    private Date createTime;

}