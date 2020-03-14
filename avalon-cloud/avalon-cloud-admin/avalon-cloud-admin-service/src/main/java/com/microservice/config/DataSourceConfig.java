package com.microservice.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 数据源配置
 *
 * @author 白超
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "ds0MasterDataSource")
    @Qualifier("ds0MasterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.ds0-master")
    public DataSource ds0MasterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "ds0MasterJdbcTemplate")
    @Qualifier("ds0MasterJdbcTemplate")
    @Primary
    public JdbcTemplate ds0MasterJdbcTemplate(@Qualifier("ds0MasterDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "ds0MasterPlatformTransactionManager")
    @Qualifier("ds0MasterPlatformTransactionManager")
    public PlatformTransactionManager ds0MasterPlatformTransactionManager(@Qualifier("ds0MasterDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
