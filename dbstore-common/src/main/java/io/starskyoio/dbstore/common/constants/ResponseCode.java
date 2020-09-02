package io.starskyoio.dbstore.common.constants;

/**
 * 返回响应码
 */
public interface ResponseCode {
    /**
     * 请求成功
     */
    int REQUEST_OK = 1;

    /**
     * 服务器出错
     */
    int SERVER_ERROR = -1;

    /**
     * 数据源不存在
     */
    int DS_NOT_FOUND = -2;

    /**
     * 请求头错误
     */
    int REQUEST_HEADER_ERROR = -3;

    /**
     * 请求参数错误
     */
    int REQUEST_PARAM_ERROR = -4;
}
