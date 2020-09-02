package io.starskyoio.dbstore.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 分页数据实体
 */
@Data
public class Page implements Serializable {
    /**
     * 页数
     */
    private int pageNum;

    /**
     * 页大小
     */
    private int pageSize;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 总页数
     */
    private int totalPages;

    /**
     * 数据
     */
    private List<Map<String, Object>> data = new ArrayList<>();

    public static Page build() {
        return new Page();
    }
}
