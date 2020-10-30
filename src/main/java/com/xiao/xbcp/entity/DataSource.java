package com.xiao.xbcp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 请描述你的文件
 *
 * @author 请在这里署名
 * @date 2020-10-29
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
@Getter
@Setter
@Accessors(chain = true)
public class DataSource implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private String paramsJson;
    private String type;
    private Date createTime;
    private long createUser;
    private Date updateTime;
    private long updateUser;
    /**
     * 环境id
     */
    private int envId;
    /**
     * 是否删除：0.否，1.是
     */
    private int isDeleted;
}
