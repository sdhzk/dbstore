package io.starskyoio.dbstore.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcTemplatesTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testInsertMysql() {
        jdbcTemplate.update("insert person(name) values (?)", "hello world");
    }

    @Test
    public void testInsertOralce() {
        jdbcTemplate.update("INSERT INTO PERSONS(NAME) VALUES (?)", "hello world");
    }

    @Test
    public void test() {
        System.out.println(jdbcTemplate);
    }
}
