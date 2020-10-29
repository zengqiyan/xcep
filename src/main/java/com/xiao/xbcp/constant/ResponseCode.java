package com.xiao.xbcp.constant;

import lombok.Getter;

/**
 * 返回码
 *
 * @author jiawei
 */
@Getter
public enum ResponseCode {
    /**
     * 成功
     */
    SUCCESS(200, "成功"),

    /**
     * 未授权
     */
    UNAUTHORIZED(401, "登录失败或已超时，请重新登录"),

    /**
     * 未授权
     */
    FORBIDDEN(403, "你没有权限，请联系系统管理员"),

    // ---------------------系统错误保留编码500~599，此类编码将进行告警----------------
    /**
     * 系统发生了未知错误，请稍候重试
     */
    SYSTEM_EXCEPTION(503, "系统发生了未知错误，请稍候重试"),

    /**
     * 外部系统异常
     */
    EXTERNAL_API_ERROR(530, "外部系统异常"),

    /**
     * 数据操作失败
     */
    DATABASE_ERROR(531, "数据操作失败"),

    /**
     * 业务异常
     */
    BUSINESS_EXCEPTION(10000, "业务异常"),

    /**
     * 您的请求过于频繁，请稍候重试
     */
    RATE_LIMIT(10001, "您的请求过于频繁，请稍候重试"),

    /**
     * 上传文件过大！
     */
    UPLOAD_SIZE_EXCEEDED(10002, "上传文件过大！"),

    /**
     * 请勿重复操作
     */
    DUPLICATED(10003, "请勿重复操作");

    /**
     * 编码
     */
    private int code;

    /**
     * 描述
     */
    private String desc;

    private ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ResponseCode of(int code) {
        for (ResponseCode responseCode : values()) {
            if (responseCode.getCode() == code) {
                return responseCode;
            }
        }
        return null;
    }
}
