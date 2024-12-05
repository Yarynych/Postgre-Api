package ua.yarynych.apipostgre.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Конфігурація для роботи з кількома джерелами даних.
 */
@Configuration
public class MultiDataSourceConfig {

    private final DataSourceProperties dataSourceProperties;

    public MultiDataSourceConfig(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Bean(name = "dataSource1")
    public DataSource dataSource1() {
        return createDataSource(getDataSourceConfig(0));
    }

    @Bean(name = "dataSource2")
    public DataSource dataSource2() {
        return createDataSource(getDataSourceConfig(1));
    }

    /**
     * Створює DataSource на основі конфігурації.
     *
     * @param config конфігурація джерела даних
     * @return DataSource
     */
    private DataSource createDataSource(DataSourceConfig config) {
        return DataSourceBuilder.create()
                .url(config.getUrl())
                .username(config.getUser())
                .password(config.getPassword())
                .driverClassName(config.getDriverClassName())
                .build();
    }

    /**
     * Отримує конфігурацію джерела даних за індексом.
     *
     * @param index індекс джерела даних у списку
     * @return DataSourceConfig
     * @throws IllegalArgumentException якщо індекс виходить за межі списку
     */
    private DataSourceConfig getDataSourceConfig(int index) {
        if (index < 0 || index >= dataSourceProperties.getDataSources().size()) {
            throw new IllegalArgumentException("Invalid datasource index: " + index);
        }
        return dataSourceProperties.getDataSources().get(index);
    }
}
