package com.xiao.xbcp.vo;


import com.xiao.xbcp.constant.ResponseCode;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 统一返回封装
 */
@Data
@Accessors(chain = true)
public class Response<T> {

    private static Response SUCCESS = new Response<>();

    /**
     * 状态码
     */
    private int resultCode = 0;

    /**
     * 异常信息
     */
    private String resultMessage = "";

    /**
     * 实际返回的数据
     */
    private T data;

    /**
     * 服务器时间
     */
    private Long timeStamp = System.currentTimeMillis();

    public Response() {
        super();
    }

    public Response(int resultCode, String resultMessage) {
        super();
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public Response(T data) {
        this.resultCode = ResponseCode.SUCCESS.getCode();
        this.data = data;
    }

    public Response(int resultCode, String resultMessage, T data) {
        super();
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.data = data;
    }

    public static <K> Response<K> success() {
        return SUCCESS;
    }

    public static <K> Response<K> of(K data) {
        return new Response<>(data);
    }

}
