package ua.yarynych.apipostgre.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataSourceConfig {
    private String name;
    private String strategy;
    private String url;
    private String table;
    private String user;
    private String password;
    private String driverClassName;
    private MappingConfig mapping;
}

