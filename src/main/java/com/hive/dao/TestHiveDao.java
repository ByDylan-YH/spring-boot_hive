package com.hive.dao;

import com.hive.dao.impl.HiveJdbcBaseDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Author:BYDylan
 * Date:2020/9/21
 * Description: 测试获取hive数据库数据信息
 */
@Repository
public class TestHiveDao extends HiveJdbcBaseDaoImpl {
    public String test() {
        String sql = "select id,`name` from hive.test_hive limit 1";
        this.getJdbcTemplate().execute(sql);
        return this.getJdbcTemplate().queryForObject(sql, String.class);
    }
}
