package pub.avalonframework.sqlhelper.jdbc.core.factory;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author baichao
 */
@SpringBootTest(
        // 启动原生容器, 并使用自定义端口
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {JdbcFactoryTest.Configuration.class})
@EnableAutoConfiguration
@SpringBootConfiguration
public class JdbcFactoryTest {

    @TestConfiguration
    public static class Configuration {
        @Bean
        @Primary
        @ConfigurationProperties(prefix = "spring.datasource.sqlhelper")
        public DataSource ds0MasterDataSource() {
            return DataSourceBuilder.create().build();
        }
    }

    @Test
    void TestFetch() {

    }
}