package io.starskyoio.dbstore.common.constants;

public interface ResponseMessage {
    /**
     * 请求成功
     */
    String REQUEST_OK = "请求成功";

    /**
     * 操作成功
     */
    String OK = "操作成功";

    /**
     * 服务器出现异常
     */
    String SERVER_ERROR = "服务器出现异常";

    /**
     * 数据源不存在
     */
    String DS_NOT_FOUND = "数据源不存在";

    /**
     * 请求头错误
     */
    String REQUEST_HEADER_ERROR = "请求头错误";

    /**
     * 请求参数错误
     */
    String REQUEST_PARAM_ERROR = "请求参数错误";
}
