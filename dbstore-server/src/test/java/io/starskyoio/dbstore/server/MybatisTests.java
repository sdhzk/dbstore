package io.starskyoio.dbstore.server;

import io.starskyoio.dbstore.common.model.SqlParam;
import io.starskyoio.dbstore.server.ds.DynamicDataSourceContextHolder;
import io.starskyoio.dbstore.server.mapper.IDbStoreMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MybatisTests {
    @Autowired
    IDbStoreMapper mapper;

    @Test
    public void testInsert() {
        DynamicDataSourceContextHolder.setDataSourceKey("ds01");
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "zhangsan");

        SqlParam sqlParam = SqlParam.build();
        sqlParam.setTableName("person");
        sqlParam.setColumnMap(columnMap);

        mapper.insert(sqlParam);
    }

    @Test
    public void testUpdate() {
        DynamicDataSourceContextHolder.setDataSourceKey("ds01");
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "zhangsan11");

        SqlParam sqlParam = SqlParam.build();
        sqlParam.setTableName("person");
        sqlParam.setColumnMap(columnMap);
        sqlParam.setWhere("id=2");

        mapper.update(sqlParam);
    }

    @Test
    public void testDelete() {
        DynamicDataSourceContextHolder.setDataSourceKey("ds01");

        SqlParam sqlParam = SqlParam.build();
        sqlParam.setTableName("person");
        sqlParam.setWhere("id=10");

        mapper.delete(sqlParam);
    }

    @Test
    public void testSelectOne() {
        DynamicDataSourceContextHolder.setDataSourceKey("ds01");

        SqlParam sqlParam = SqlParam.build();
        sqlParam.setTableName("person");
        sqlParam.setColumnList(Arrays.asList("id", "name"));
        sqlParam.setWhere("id=2");

        Map<String, Object> result = mapper.selectOne(sqlParam);
        System.out.println(result);
    }


    @Test
    public void testSelectList() {
        DynamicDataSourceContextHolder.setDataSourceKey("ds01");

        SqlParam sqlParam = SqlParam.build();
        sqlParam.setTableName("person");
        sqlParam.setColumnList(Arrays.asList("id", "name"));
        sqlParam.setWhere("name='test'");
        sqlParam.setOrderBy("id desc");

        List<Map<String, Object>> list = mapper.selectList(sqlParam);
        System.out.println(list);
    }

    @Test
    public void testSelectPage() {
        DynamicDataSourceContextHolder.setDataSourceKey("ds01");
        PageHelper.startPage(2, 2);

        SqlParam sqlParam = SqlParam.build();
        sqlParam.setTableName("person");
        sqlParam.setColumnList(Arrays.asList("id", "name"));
        sqlParam.setOrderBy("id desc");

        List<Map<String, Object>> list = mapper.selectList(sqlParam);
        PageInfo<List> pageInfo = new PageInfo(list);
        System.out.println(pageInfo);
    }
}
