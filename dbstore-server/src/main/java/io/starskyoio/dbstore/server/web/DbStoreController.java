package io.starskyoio.dbstore.server.web;

import io.starskyoio.dbstore.common.model.Page;
import io.starskyoio.dbstore.common.model.Response;
import io.starskyoio.dbstore.common.model.SqlParam;
import io.starskyoio.dbstore.server.service.IDbStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 数据存储服务v1.0
 */
@RestController
@RequestMapping("/api/dbstore")
public class DbStoreController {
    @Autowired
    private IDbStoreService dbStoreService;

    /**
     * 新增
     *
     * @param sqlParam 请求参数
     * @return 响应实体
     */
    @PostMapping("/add/v1")
    public Response add(@RequestBody SqlParam sqlParam) {
        dbStoreService.save(sqlParam);
        return Response.ok();
    }

    /**
     * 删除
     *
     * @param sqlParam 请求参数
     * @return 响应实体
     */
    @PostMapping("/del/v1")
    public Response del(@RequestBody SqlParam sqlParam) {
        dbStoreService.delete(sqlParam);
        return Response.ok();
    }

    /**
     * 修改
     *
     * @param sqlParam 请求参数
     * @return 响应实体
     */
    @PostMapping("/mod/v1")
    public Response mod(@RequestBody SqlParam sqlParam) {
        dbStoreService.update(sqlParam);
        return Response.ok();
    }

    /**
     * 查询单个
     *
     * @param sqlParam 请求参数
     * @return 响应实体
     */
    @PostMapping("/detail/v1")
    public Response detail(@RequestBody SqlParam sqlParam) {
        Map<String, Object> data = dbStoreService.findOne(sqlParam);
        return Response.ok(data);
    }

    /**
     * 查询全部
     *
     * @param sqlParam 请求参数
     * @return 响应实体
     */
    @PostMapping("/list/v1")
    public Response list(@RequestBody SqlParam sqlParam) {
        List<Map<String, Object>> list = dbStoreService.findAll(sqlParam);
        return Response.ok(list);
    }

    /**
     * 分页查询
     *
     * @param sqlParam 请求参数
     * @return 响应实体
     */
    @PostMapping("/page/v1")
    public Response page(@RequestBody SqlParam sqlParam) {
        Page page = dbStoreService.findPage(sqlParam, sqlParam.getPageNum(),
                sqlParam.getPageSize());
        return Response.ok(page);
    }

    /**
     * 执行更新sql语句
     *
     * @param sqlParam 请求参数
     * @return 响应实体
     */
    @PostMapping("/executeUpdate/v1")
    public Response executeUpdate(@RequestBody SqlParam sqlParam) {
        dbStoreService.executeUpdate(sqlParam);
        return Response.ok();
    }

    /**
     * 执行查询单条记录的sql语句
     *
     * @param sqlParam 请求参数
     * @return 响应实体
     */
    @PostMapping("/executeForMap/v1")
    public Response executeForMap(@RequestBody SqlParam sqlParam) {
        Map<String, Object> data = dbStoreService.executeForMap(sqlParam);
        return Response.ok(data);
    }

    /**
     * 执行查询多条记录的sql语句
     *
     * @param sqlParam 请求参数
     * @return 响应实体
     */
    @PostMapping("/executeForList/v1")
    public Response executeForList(@RequestBody SqlParam sqlParam) {
        List<Map<String, Object>> list = dbStoreService.executeForList(sqlParam);
        return Response.ok(list);
    }


    /**
     * 执行查询分页记录的sql语句
     *
     * @param sqlParam 请求参数
     * @return 响应实体
     */
    @PostMapping("/executeForPage/v1")
    public Response executeForPage(@RequestBody SqlParam sqlParam) {
        Page page = dbStoreService.executeForPage(sqlParam, sqlParam.getPageNum(),
                sqlParam.getPageSize());
        return Response.ok(page);
    }
}
