package ua.yarynych.apipostgre.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring")
public class DataSourceProperties {
    private List<DataSourceConfig> dataSources;
}
