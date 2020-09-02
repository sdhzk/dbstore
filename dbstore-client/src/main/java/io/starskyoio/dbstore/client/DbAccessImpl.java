package io.starskyoio.dbstore.client;

import com.google.common.collect.Maps;
import io.starskyoio.dbstore.common.api.IDbAccess;
import io.starskyoio.dbstore.common.constants.Fields;
import io.starskyoio.dbstore.common.constants.Paths;
import io.starskyoio.dbstore.common.model.Response;
import io.starskyoio.dbstore.common.model.SqlParam;
import io.starskyoio.dbstore.common.util.HttpUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;

/**
 * 数据服务访问接口实现
 */
@Data
public class DbAccessImpl implements IDbAccess {
    private String host;
    private int port;
    private String dataSourceName;

    public DbAccessImpl(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private String getPath(String path) {
        return "http://" + this.host + ":" + this.port + "/" + path;
    }

    private void checkDataSourceName() {
        if (StringUtils.isBlank(this.dataSourceName)) {
            throw new RuntimeException("dataSourceName不能为空");
        }
    }

    @Override
    public Response insert(SqlParam sqlParam) {
        return insert(sqlParam.getTableName(), sqlParam.getColumnMap());
    }

    @Override
    public Response insert(String tableName, Map<String, Object> columnMap) {
        checkDataSourceName();
        try {
            return HttpUtil.postJSON(getPath(Paths.INSERT_PATH),
                    of(Fields.HEADER_DS_FIELD, this.dataSourceName),
                    of(Fields.PARAM_TABLE_NAME_FIELD, tableName,
                            Fields.PARAM_COLUMN_MAP_FIELD, columnMap)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response update(SqlParam sqlParam) {
        return update(sqlParam.getTableName(), sqlParam.getWhere(), sqlParam.getColumnMap());
    }

    @Override
    public Response update(String tableName, String where, Map<String, Object> columnMap) {
        checkDataSourceName();
        try {
            return HttpUtil.postJSON(getPath(Paths.UPDATE_PATH),
                    of(Fields.HEADER_DS_FIELD, this.dataSourceName),
                    of(Fields.PARAM_TABLE_NAME_FIELD, tableName,
                            Fields.PARAM_WHERE_FIELD, where,
                            Fields.PARAM_COLUMN_MAP_FIELD, columnMap)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response delete(SqlParam sqlParam) {
        return delete(sqlParam.getTableName(), sqlParam.getWhere());
    }

    @Override
    public Response delete(String tableName, String where) {
        checkDataSourceName();
        try {
            return HttpUtil.postJSON(getPath(Paths.DELETE_PATH),
                    of(Fields.HEADER_DS_FIELD, this.dataSourceName),
                    of(Fields.PARAM_TABLE_NAME_FIELD, tableName,
                            Fields.PARAM_WHERE_FIELD, where)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response findOne(SqlParam sqlParam) {
        return findOne(sqlParam.getTableName(), sqlParam.getColumnList(), sqlParam.getWhere());
    }

    @Override
    public Response findOne(String tableName, List<String> columnList, String where) {
        checkDataSourceName();
        try {
            return HttpUtil.postJSON(getPath(Paths.DETAIL_PATH),
                    of(Fields.HEADER_DS_FIELD, this.dataSourceName),
                    of(Fields.PARAM_TABLE_NAME_FIELD, tableName,
                            Fields.PARAM_WHERE_FIELD, where,
                            Fields.PARAM_COLUMN_LIST_FIELD, columnList)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response findList(SqlParam sqlParam) {
        return findList(sqlParam.getTableName(), sqlParam.getColumnList(), sqlParam.getWhere(), sqlParam.getOrderBy());
    }

    @Override
    public Response findList(String tableName, List<String> columnList, String where) {
        return findList(tableName, columnList, where, "");
    }

    @Override
    public Response findList(String tableName, List<String> columnList, String where, String orderBy) {
        checkDataSourceName();
        try {
            return HttpUtil.postJSON(getPath(Paths.LIST_PATH),
                    of(Fields.HEADER_DS_FIELD, this.dataSourceName),
                    of(Fields.PARAM_TABLE_NAME_FIELD, tableName,
                            Fields.PARAM_COLUMN_LIST_FIELD, columnList,
                            Fields.PARAM_WHERE_FIELD, where,
                            Fields.PARAM_ORDER_BY_FIELD, orderBy)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response findPage(SqlParam sqlParam) {
        return findPage(sqlParam.getTableName(),
                sqlParam.getColumnList(),
                sqlParam.getWhere(),
                sqlParam.getOrderBy(),
                sqlParam.getPageNum(),
                sqlParam.getPageSize());
    }

    @Override
    public Response findPage(String tableName, List<String> columnList, String where, int pageNum, int pageSize) {
        return findPage(tableName, columnList, where, "", pageNum, pageSize);
    }

    @Override
    public Response findPage(String tableName, List<String> columnList, String where, String orderBy, int pageNum, int pageSize) {
        checkDataSourceName();
        try {
            Map<String, Object> params = Maps.newHashMap();
            params.put(Fields.PARAM_TABLE_NAME_FIELD, tableName);
            params.put(Fields.PARAM_COLUMN_LIST_FIELD, columnList);
            params.put(Fields.PARAM_WHERE_FIELD, where);
            params.put(Fields.PARAM_ORDER_BY_FIELD, orderBy);
            params.put(Fields.PARAM_PAGE_NUM_FIELD, pageNum);
            params.put(Fields.PARAM_PAGE_SIZE_FIELD, pageSize);
            return HttpUtil.postJSON(getPath(Paths.PAGE_PATH),
                    of(Fields.HEADER_DS_FIELD, this.dataSourceName), params
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response executeUpdate(String sql) {
        try {
            return HttpUtil.postJSON(getPath(Paths.EXECUTE_UPDATE_PATH),
                    of(Fields.HEADER_DS_FIELD, this.dataSourceName),
                    of("sql", sql)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response executeForMap(String sql) {
        try {
            return HttpUtil.postJSON(getPath(Paths.EXECUTE_FOR_MAP_PATH),
                    of(Fields.HEADER_DS_FIELD, this.dataSourceName),
                    of("sql", sql)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response executeForList(String sql) {
        try {
            return HttpUtil.postJSON(getPath(Paths.EXECUTE_FOR_LIST_PATH),
                    of(Fields.HEADER_DS_FIELD, this.dataSourceName),
                    of("sql", sql)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response executeForPage(String sql, int pageNum, int pageSize) {
        try {
            return HttpUtil.postJSON(getPath(Paths.EXECUTE_FOR_PAGE_PATH),
                    of(Fields.HEADER_DS_FIELD, this.dataSourceName),
                    of("sql", sql,
                            Fields.PARAM_PAGE_NUM_FIELD, pageNum,
                            Fields.PARAM_PAGE_SIZE_FIELD, pageSize)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setDataSource(String dataSource) {
        this.dataSourceName = dataSource;
    }
}
