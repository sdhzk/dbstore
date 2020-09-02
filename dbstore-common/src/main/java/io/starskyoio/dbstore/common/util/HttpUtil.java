package io.starskyoio.dbstore.common.util;


import com.alibaba.fastjson.JSON;
import io.starskyoio.dbstore.common.model.Response;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.util.Map;

/**
 * http工具类
 */
public class HttpUtil {

    /**
     * 发送json参数POST请求
     *
     * @param url     url连接
     * @param headers 请求头
     * @param params  请求参数
     * @return Response
     * @throws IOException
     */
    public static Response postJSON(String url, Map<String, String> headers, Map<String, Object> params) throws IOException {
        Request request = Request.Post(url);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            request.setHeader(entry.getKey(), entry.getValue());
        }
        String result = request.bodyString(JSON.toJSONString(params), ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();
        return JSON.parseObject(result, Response.class);
    }
}
