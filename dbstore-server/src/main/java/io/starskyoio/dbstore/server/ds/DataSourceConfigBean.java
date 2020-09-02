package io.starskyoio.dbstore.server.ds;

import lombok.Data;

@Data
public class DataSourceConfigBean {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}
