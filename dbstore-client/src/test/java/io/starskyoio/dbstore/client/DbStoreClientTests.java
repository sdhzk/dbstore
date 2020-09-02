package io.starskyoio.dbstore.client;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.starskyoio.dbstore.common.api.IDbAccess;
import io.starskyoio.dbstore.common.constants.ResponseCode;
import io.starskyoio.dbstore.common.model.Response;
import io.starskyoio.dbstore.common.model.SqlParam;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 数据服务客户端单元测试
 */
public class DbStoreClientTests {
    private DbStoreClient client;
    @Before
    public void setUp() {
        client = DbStoreClient.build("localhost", 8081);
    }

    @Test
    public void testInsert1() {
        IDbAccess db = client.getDb("ds01");
        SqlParam sqlParam = SqlParam.build();
        sqlParam.setTableName("person");
        sqlParam.setColumnMap(ImmutableMap.of("name", "测试1"));
        Response response = db.insert(sqlParam);
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());

        db = client.getDb("ds02");
        sqlParam = SqlParam.build();
        sqlParam.setTableName("persons");
        sqlParam.setColumnMap(ImmutableMap.of("name", "测试1"));
        response = db.insert(sqlParam);
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());
    }

    @Test
    public void testInsert2() {
        IDbAccess db = client.getDb("ds01");
        Response response = db.insert("person", ImmutableMap.of("name", "测试2"));
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());

        db = client.getDb("ds02");
        response = db.insert("persons", ImmutableMap.of("name", "测试2"));
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());
    }

    @Test
    public void testUpdate1() {
        IDbAccess db = client.getDb("ds01");
        SqlParam sqlParam = SqlParam.build();
        sqlParam.setTableName("person");
        sqlParam.setColumnMap(ImmutableMap.of("name", "测试1"));
        sqlParam.setWhere("id=1");
        Response response = db.update(sqlParam);
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());

        db = client.getDb("ds02");
        sqlParam = SqlParam.build();
        sqlParam.setTableName("persons");
        sqlParam.setColumnMap(ImmutableMap.of("name", "测试1"));
        sqlParam.setWhere("id=1");
        response = db.update(sqlParam);
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());
    }

    @Test
    public void testUpdate2() {
        IDbAccess db = client.getDb("ds01");
        Response response = db.update("person", "id=1", ImmutableMap.of("name", "测试2"));
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());

        db = client.getDb("ds02");
        response = db.update("persons", "id=1", ImmutableMap.of("name", "测试2"));
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());
    }

    @Test
    public void testDelete1() {
        IDbAccess db = client.getDb("ds01");
        SqlParam sqlParam = SqlParam.build();
        sqlParam.setTableName("person");
        sqlParam.setWhere("id=26");
        Response response = db.delete(sqlParam);
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());

        db = client.getDb("ds02");
        sqlParam = SqlParam.build();
        sqlParam.setTableName("persons");
        sqlParam.setWhere("id=61");
        response = db.delete(sqlParam);
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());
    }

    @Test
    public void testDelete2() {
        IDbAccess db = client.getDb("ds01");
        Response response = db.delete("person", "id=25");
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());

        db = client.getDb("ds02");
        response = db.delete("persons", "id=62");
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());
    }

    @Test
    public void testDetail1() {
        IDbAccess db = client.getDb("ds01");
        SqlParam sqlParam = SqlParam.build();
        sqlParam.setTableName("person");
        sqlParam.setColumnList(ImmutableList.of("id", "name"));
        sqlParam.setWhere("id=1");
        Response response = db.findOne(sqlParam);
        System.out.println(response);
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());

        db = client.getDb("ds02");
        sqlParam = SqlParam.build();
        sqlParam.setTableName("persons");
        sqlParam.setColumnList(ImmutableList.of("id", "name"));
        sqlParam.setWhere("id=1");
        response = db.findOne(sqlParam);
        System.out.println(response);
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());
    }

    @Test
    public void testDetail2() {
        IDbAccess db = client.getDb("ds01");
        Response response = db.findOne("person", ImmutableList.of("id", "name"), "id=1");
        System.out.println(response);
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());

        db = client.getDb("ds02");
        response = db.findOne("persons", ImmutableList.of("id", "name"), "id=1");
        System.out.println(response);
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());
    }

    @Test
    public void testList() {
        IDbAccess db = client.getDb("ds01");
        SqlParam sqlParam = SqlParam.build();
        sqlParam.setTableName("person");
        sqlParam.setColumnList(ImmutableList.of("id", "name"));
        sqlParam.setWhere("name like '测试%'");
        Response response = db.findList(sqlParam);
        System.out.println(response);
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());

        db = client.getDb("ds02");
        sqlParam = SqlParam.build();
        sqlParam.setTableName("persons");
        sqlParam.setColumnList(ImmutableList.of("id", "name"));
        sqlParam.setWhere("name like '测试%'");
        response = db.findList(sqlParam);
        System.out.println(response);
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());
    }

    @Test
    public void testList2() {
        IDbAccess db = client.getDb("ds01");
        Response response = db.findList("person",
                ImmutableList.of("id", "name"),
                "name like '测试%'",
                "id desc");
        System.out.println(response);
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());

        db = client.getDb("ds02");
        response = db.findList("persons",
                ImmutableList.of("id", "name"),
                "name like '测试%'",
                "id desc");
        System.out.println(response);
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());
    }

    @Test
    public void testPage1() {
        IDbAccess db = client.getDb("ds01");
        SqlParam sqlParam = SqlParam.build();
        sqlParam.setTableName("person");
        sqlParam.setColumnList(ImmutableList.of("id", "name"));
        sqlParam.setPageNum(1);
        sqlParam.setPageSize(10);
        Response response = db.findPage(sqlParam);
        System.out.println(response);
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());

        db = client.getDb("ds02");
        sqlParam = SqlParam.build();
        sqlParam.setTableName("persons");
        sqlParam.setColumnList(ImmutableList.of("id", "name"));
        sqlParam.setPageNum(1);
        sqlParam.setPageSize(10);
        response = db.findPage(sqlParam);
        System.out.println(response);
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());
    }

    @Test
    public void testPage2() {
        IDbAccess db = client.getDb("ds01");
        Response response = db.findPage("person",
                ImmutableList.of("id", "name"),
                null,
                null,
                1,
                10);
        System.out.println(response);
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());

        db = client.getDb("ds02");
        response = db.findPage("persons",
                ImmutableList.of("id", "name"),
                null,
                null,
                1,
                10);
        System.out.println(response);
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());
    }

    @Test
    public void testExecuteUpdate() {
        IDbAccess db = client.getDb("ds01");
        Response response = db.executeUpdate("insert into person(name) values ('测试a')");
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());

        db = client.getDb("ds02");
        response = db.executeUpdate("insert into persons(name) values ('测试a')");
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());
    }

    @Test
    public void testExecuteForMap() {
        IDbAccess db = client.getDb("ds01");
        Response response = db.executeForMap("select id, name from person");
        System.out.println(response);
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());

        db = client.getDb("ds02");
        response = db.executeUpdate("select id, name from person");
        System.out.println(response);
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());
    }

    @Test
    public void testExecuteForList() {
        IDbAccess db = client.getDb("ds01");
        Response response = db.executeForList("select id,name from person");
        System.out.println(response);
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());

        db = client.getDb("ds02");
        response = db.executeForList("select id,name from persons");
        System.out.println(response);
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());
    }

    @Test
    public void testExecuteForPage() {
        IDbAccess db = client.getDb("ds01");
        Response response = db.executeForPage("select id,name from person", 1, 10);
        System.out.println(response);
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());

        db = client.getDb("ds02");
        response = db.executeForPage("select id,name from persons", 1, 10);
        System.out.println(response);
        assertEquals(ResponseCode.REQUEST_OK, response.getRespnum());
    }
}
