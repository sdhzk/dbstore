package io.starskyoio.dbstore.server.mapper;

import io.starskyoio.dbstore.common.model.SqlParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 数据存储Mapper
 */
@Mapper
public interface IDbStoreMapper {
    /**
     * 插入记录
     *
     * @param param
     */
    void insert(@Param("param") SqlParam param);

    /**
     * 更新记录
     *
     * @param param
     */
    void update(@Param("param") SqlParam param);

    /**
     * 删除记录
     *
     * @param param
     */
    void delete(@Param("param") SqlParam param);

    /**
     * 查询单个记录
     *
     * @param param sql参数
     * @return 单个记录
     */
    Map<String, Object> selectOne(@Param("param") SqlParam param);

    /**
     * 查询记录列表
     *
     * @param param sql参数
     * @return 记录列表
     */
    List<Map<String, Object>> selectList(@Param("param") SqlParam param);

    /**
     * 通过sql语句查询所有
     *
     * @param param sql参数
     * @return 记录列表
     */
    List<Map<String, Object>> selectListBySql(@Param("param") SqlParam param);
}
