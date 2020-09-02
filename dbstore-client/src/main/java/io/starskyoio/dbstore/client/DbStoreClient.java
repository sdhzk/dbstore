package io.starskyoio.dbstore.client;

import io.starskyoio.dbstore.common.api.IDbAccess;
import lombok.Data;

/**
 * 数据服务客户端
 */
@Data
public class DbStoreClient {
    private String host;
    private int port;


    private DbStoreClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static DbStoreClient build(String host, int port) {
        return new DbStoreClient(host, port);
    }

    public IDbAccess getDb(String dataSource) {
        IDbAccess db = new DbAccessImpl(host, port);
        db.setDataSource(dataSource);
        return db;
    }
}
