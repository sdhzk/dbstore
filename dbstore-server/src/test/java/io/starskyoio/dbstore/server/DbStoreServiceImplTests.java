package io.starskyoio.dbstore.server;

import io.starskyoio.dbstore.server.service.IDbStoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbStoreServiceImplTests {
    @Autowired
    private IDbStoreService dbStoreService;

    @Test
    public void testInsert() {

    }
}
