package io.starskyoio.dbstore.common.api;

import io.starskyoio.dbstore.common.model.Response;
import io.starskyoio.dbstore.common.model.SqlParam;

import java.util.List;
import java.util.Map;

/**
 * 数据服务访问接口
 */
public interface IDbAccess {
    /**
     * 插入记录
     *
     * @param sqlParam sql参数
     * @return Response
     */
    Response insert(SqlParam sqlParam);

    /**
     * 插入记录
     *
     * @param tableName 表名
     * @param columnMap 字段键值Map对象
     * @return Response
     */
    Response insert(String tableName, Map<String, Object> columnMap);

    /**
     * 更新记录
     *
     * @param sqlParam sql参数
     * @return Response
     */
    Response update(SqlParam sqlParam);

    /**
     * 更新记录
     *
     * @param tableName 表名
     * @param where     sql条件
     * @param columnMap 字段键值Map对象
     * @return Response
     */
    Response update(String tableName, String where, Map<String, Object> columnMap);

    /**
     * 删除记录
     *
     * @param sqlParam sql参数
     * @return Response
     */
    Response delete(SqlParam sqlParam);

    /**
     * 删除记录
     *
     * @param tableName 表名
     * @param where     sql条件
     * @return Response
     */
    Response delete(String tableName, String where);

    /**
     * 查询单个记录
     *
     * @param sqlParam sql参数
     * @return Response
     */
    Response findOne(SqlParam sqlParam);

    /**
     * 查询单个记录
     *
     * @param tableName  表名
     * @param columnList 字段列表
     * @param where      sql条件
     * @return Response
     */
    Response findOne(String tableName, List<String> columnList, String where);

    /**
     * 查询列表记录
     *
     * @param sqlParam sql参数
     * @return Response
     */
    Response findList(SqlParam sqlParam);

    /**
     * 查询列表记录
     *
     * @param tableName  表名
     * @param columnList 字段列表
     * @param where      sql条件
     * @return Response
     */
    Response findList(String tableName, List<String> columnList, String where);

    /**
     * 查询列表记录
     *
     * @param tableName  表名
     * @param columnList 字段列表
     * @param where      sql条件
     * @param orderBy    排序字段
     * @return Response
     */
    Response findList(String tableName, List<String> columnList, String where, String orderBy);

    /**
     * 查询分页记录
     *
     * @param sqlParam sql参数
     * @return Response
     */
    Response findPage(SqlParam sqlParam);

    /**
     * 查询分页记录
     *
     * @param tableName  表名
     * @param columnList 字段列表
     * @param where      sql条件
     * @param pageNum    页数
     * @param pageSize   页大小
     * @return Response
     */
    Response findPage(String tableName, List<String> columnList, String where, int pageNum, int pageSize);

    /**
     * 查询分页记录
     *
     * @param tableName  表名
     * @param columnList 字段列表
     * @param where      sql条件
     * @param orderBy    排序字段
     * @param pageNum    页数
     * @param pageSize   页大小
     * @return Response
     */
    Response findPage(String tableName, List<String> columnList, String where, String orderBy, int pageNum, int pageSize);

    /**
     * 执行更新SQL语句
     *
     * @param sql sql语句
     * @return Response
     */
    Response executeUpdate(String sql);

    /**
     * 执行查询单个记录SQL语句
     *
     * @param sql sql语句
     * @return Response
     */
    Response executeForMap(String sql);

    /**
     * 执行查询列表记录SQL语句
     *
     * @param sql sql语句
     * @return Response
     */
    Response executeForList(String sql);

    /**
     * 执行查询分页记录SQL语句
     *
     * @param sql      sql语句
     * @param pageNum  页数
     * @param pageSize 页大小
     * @return Response
     */
    Response executeForPage(String sql, int pageNum, int pageSize);

    /**
     * 设置数据源名称
     * @param dataSource 数据源名称
     */
    void setDataSource(String dataSource);
}
