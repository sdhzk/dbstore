package io.starskyoio.dbstore.client;

import com.google.common.collect.ImmutableMap;
import io.starskyoio.dbstore.common.model.Response;
import io.starskyoio.dbstore.common.util.HttpUtil;
import org.junit.Test;

import java.util.Map;

public class HttpClientTests {
    @Test
    public void testPost() throws Exception {
        Map<String, Object> params = ImmutableMap.of("tableName", "person",
                "columnMap", ImmutableMap.of("name", "测试"));
        Response resp = HttpUtil.postJSON("http://localhost:8081/api/dbstore/add/v1",
                ImmutableMap.of("x-ds", "ds01"), params);
        System.out.println(resp);
    }
}
