package com.hive;

import com.hive.dao.TestHiveDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootHiveApplicationTests {
    @Autowired
    private TestHiveDao testHiveDao;

    @Test
    void contextLoads() {
        String test = testHiveDao.test();
        System.out.println(test);
    }

}
