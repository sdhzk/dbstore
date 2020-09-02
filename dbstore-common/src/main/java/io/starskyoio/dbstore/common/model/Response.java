package io.starskyoio.dbstore.common.model;

import io.starskyoio.dbstore.common.constants.ResponseCode;
import io.starskyoio.dbstore.common.constants.ResponseMessage;
import lombok.Data;

import java.io.Serializable;

/**
 * 响应结果
 */
@Data
public class Response implements Serializable {
    /**
     * 响应代码
     */
    private int respnum;
    /**
     * 响应消息
     */
    private String respmsg;

    /**
     * 响应数据
     */
    private Object data;

    public Response() {
    }

    public Response(int respnum, String respmsg) {
        this.respnum = respnum;
        this.respmsg = respmsg;
    }

    public Response(int respnum, String respmsg, Object data) {
        this(respnum, respmsg);
        this.data = data;
    }

    public static Response build(int respnum, String respmsg) {
        return new Response(respnum, respmsg);
    }

    public static Response build(int respnum, String respmsg, Object data) {
        return new Response(respnum, respmsg, data);
    }

    public static Response ok() {
        return Response.build(ResponseCode.REQUEST_OK, ResponseMessage.OK);
    }

    public static Response ok(Object data) {
        return Response.build(ResponseCode.REQUEST_OK, ResponseMessage.OK, data);
    }
}
