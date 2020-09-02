package io.starskyoio.dbstore.server.service;

import io.starskyoio.dbstore.common.model.Page;
import io.starskyoio.dbstore.common.model.SqlParam;

import java.util.List;
import java.util.Map;

/**
 * 数据存储服务接口
 */
public interface IDbStoreService {
    /**
     * 保存记录
     *
     * @param param sql参数
     */
    void save(SqlParam param);

    /**
     * 更新记录
     *
     * @param param sql参数
     */
    void update(SqlParam param);

    /**
     * 删除记录
     *
     * @param param sql参数
     */
    void delete(SqlParam param);

    /**
     * 查询单个记录
     *
     * @param param sql参数
     * @return 单个记录
     */
    Map<String, Object> findOne(SqlParam param);

    /**
     * 查询记录列表
     *
     * @param param sql参数
     * @return 记录列表
     */
    List<Map<String, Object>> findAll(SqlParam param);

    /**
     * 分页查询
     *
     * @param param    sql参数
     * @param pageNum  页数
     * @param pageSize 页大小
     * @return page分页对象
     */
    Page findPage(SqlParam param, int pageNum, int pageSize);

    /**
     * 执行更新类的sql语句
     *
     * @param param sql参数
     */
    void executeUpdate(SqlParam param);

    /**
     * 执行查询单个记录的sql语句
     *
     * @param param sql参数
     * @return 单个记录
     */
    Map<String, Object> executeForMap(SqlParam param);

    /**
     * 执行查询多条记录的sql语句
     *
     * @param param sql参数
     * @return 记录列表
     */
    List<Map<String, Object>> executeForList(SqlParam param);

    /**
     * 执行查询分页记录的sql语句
     *
     * @param param    sql参数
     * @param pageNum  页数
     * @param pageSize 页大小
     * @return page分页对象
     */
    Page executeForPage(SqlParam param, int pageNum, int pageSize);
}
