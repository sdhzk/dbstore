package io.starskyoio.dbstore.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.starskyoio.dbstore.common.model.Page;
import io.starskyoio.dbstore.common.model.SqlParam;
import io.starskyoio.dbstore.server.service.IDbStoreService;
import io.starskyoio.dbstore.server.mapper.IDbStoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 数据存储服务实现
 */
@Service
public class DbStoreServiceImpl implements IDbStoreService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IDbStoreMapper dbStoreMapper;

    @Override
    public void save(SqlParam param) {
        dbStoreMapper.insert(param);
    }

    @Override
    public void update(SqlParam param) {
        dbStoreMapper.update(param);
    }

    @Override
    public void delete(SqlParam param) {
        dbStoreMapper.delete(param);
    }

    @Override
    public Map<String, Object> findOne(SqlParam param) {
        return dbStoreMapper.selectOne(param);
    }

    @Override
    public List<Map<String, Object>> findAll(SqlParam param) {
        return dbStoreMapper.selectList(param);
    }

    @Override
    public Page findPage(SqlParam param, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = dbStoreMapper.selectList(param);
        return getPage(list);
    }

    @Override
    public void executeUpdate(SqlParam param) {
        jdbcTemplate.execute(param.getSql());
    }

    @Override
    public Map<String, Object> executeForMap(SqlParam param) {
        return jdbcTemplate.queryForMap(param.getSql());
    }

    @Override
    public List<Map<String, Object>> executeForList(SqlParam param) {
        return dbStoreMapper.selectListBySql(param);
    }

    @Override
    public Page executeForPage(SqlParam param, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = dbStoreMapper.selectListBySql(param);
        return getPage(list);
    }

    private Page getPage(List<Map<String, Object>> list) {
        PageInfo<List> pageInfo = new PageInfo(list);
        Page page = Page.build();
        page.setData(list);
        page.setPageNum(pageInfo.getPageNum());
        page.setPageSize(pageInfo.getPageSize());
        page.setTotal(pageInfo.getTotal());
        page.setTotalPages(pageInfo.getPages());
        return page;
    }
}
