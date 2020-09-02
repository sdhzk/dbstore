package io.starskyoio.dbstore.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class SqlParam implements Serializable {
    private String tableName = "";
    private String where = "";
    private String orderBy = "";
    private String sql = "";
    private int pageNum = 1;
    private int pageSize = 10;
    private List<String> columnList = new ArrayList<>();
    private Map<String, Object> columnMap = new HashMap<>();


    public static SqlParam build() {
        return new SqlParam();
    }
}
