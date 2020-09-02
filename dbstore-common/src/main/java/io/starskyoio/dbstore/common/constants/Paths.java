package io.starskyoio.dbstore.common.constants;

/**
 * 定义数据存储服务URL地址
 */
public interface Paths {
    /**
     * 插入URL
     */
    String INSERT_PATH = "/api/dbstore/add/v1";

    /**
     * 修改URL
     */
    String UPDATE_PATH = "/api/dbstore/mod/v1";

    /**
     * 删除URL
     */
    String DELETE_PATH = "/api/dbstore/del/v1";

    /**
     * 查询单条记录URL
     */
    String DETAIL_PATH = "/api/dbstore/detail/v1";

    /**
     * 查询列表记录URL
     */
    String LIST_PATH = "/api/dbstore/list/v1";

    /**
     * 查询分页记录URL
     */
    String PAGE_PATH = "/api/dbstore/page/v1";

    /**
     * 执行更新SQL URL
     */
    String EXECUTE_UPDATE_PATH = "/api/dbstore/executeUpdate/v1";

    /**
     * 执行查询单条记录SQL URL
     */
    String EXECUTE_FOR_MAP_PATH = "/api/dbstore/executeForMap/v1";

    /**
     * 执行查询多条记录SQL URL
     */
    String EXECUTE_FOR_LIST_PATH = "/api/dbstore/executeForList/v1";

    /**
     * 执行查询分页记录SQL URL
     */
    String EXECUTE_FOR_PAGE_PATH = "/api/dbstore/executeForPage/v1";
}
