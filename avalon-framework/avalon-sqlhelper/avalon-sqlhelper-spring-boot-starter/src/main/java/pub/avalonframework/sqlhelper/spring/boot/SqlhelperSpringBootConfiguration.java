package pub.avalonframework.sqlhelper.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import pub.avalonframework.sqlhelper.core.api.config.*;
import pub.avalonframework.sqlhelper.core.yaml.swapper.impl.*;
import pub.avalonframework.sqlhelper.jdbc.core.JdbcHelper;
import pub.avalonframework.sqlhelper.jdbc.core.SpringJdbcHelper;
import pub.avalonframework.sqlhelper.jdbc.core.factory.JdbcFactory;

/**
 * Security spring boot configuration.
 *
 * @author baichao
 */
@Configuration
@EnableConfigurationProperties({
        SpringBootPrintConfigurationProperties.class,
        SpringBootDataBlockBuilderConfigurationProperties.class,
        SpringBootSqlBuilderConfigurationProperties.class,
        SpringBootJdbcConfigurationProperties.class,
        SpringBootSqlhelperConfigurationProperties.class
})
public class SqlhelperSpringBootConfiguration implements EnvironmentAware {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final SpringBootPrintConfigurationProperties printConfigurationProperties;

    private final SpringBootDataBlockBuilderConfigurationProperties dataBlockBuilderConfigurationProperties;

    private final SpringBootSqlBuilderConfigurationProperties sqlBuilderConfigurationProperties;

    private final SpringBootJdbcConfigurationProperties jdbcConfigurationProperties;

    private final SpringBootSqlhelperConfigurationProperties sqlhelperConfigurationProperties;

    public SqlhelperSpringBootConfiguration(SpringBootPrintConfigurationProperties printConfigurationProperties, SpringBootDataBlockBuilderConfigurationProperties dataBlockBuilderConfigurationProperties, SpringBootSqlBuilderConfigurationProperties sqlBuilderConfigurationProperties, SpringBootJdbcConfigurationProperties jdbcConfigurationProperties, SpringBootSqlhelperConfigurationProperties sqlhelperConfigurationProperties) {
        this.printConfigurationProperties = printConfigurationProperties;
        this.dataBlockBuilderConfigurationProperties = dataBlockBuilderConfigurationProperties;
        this.sqlBuilderConfigurationProperties = sqlBuilderConfigurationProperties;
        this.jdbcConfigurationProperties = jdbcConfigurationProperties;
        this.sqlhelperConfigurationProperties = sqlhelperConfigurationProperties;
    }

    @Bean
    @ConditionalOnMissingBean(PrintConfiguration.class)
    public PrintConfiguration printConfiguration() {
        return new PrintConfigurationYamlSwapper().swap(printConfigurationProperties);
    }

    @Bean
    @ConditionalOnMissingBean(DataBlockBuilderConfiguration.class)
    public DataBlockBuilderConfiguration dataBlockBuilderConfiguration() {
        return new DataBlockBuilderConfigurationYamlSwapper().swap(dataBlockBuilderConfigurationProperties);
    }

    @Bean
    @ConditionalOnMissingBean(SqlBuilderConfiguration.class)
    public SqlBuilderConfiguration sqlBuilderConfiguration(DataBlockBuilderConfiguration dataBlockBuilderConfiguration) {
        SqlBuilderConfiguration sqlBuilderConfiguration = new SqlBuilderConfigurationYamlSwapper().swap(sqlBuilderConfigurationProperties);
        sqlBuilderConfiguration.setDataBlockBuilder(dataBlockBuilderConfiguration);
        return sqlBuilderConfiguration;
    }

    @Bean
    @ConditionalOnMissingBean(JdbcConfiguration.class)
    public JdbcConfiguration jdbcConfiguration() {
        return new JdbcConfigurationYamlSwapper().swap(jdbcConfigurationProperties);
    }

    @Bean
    @ConditionalOnMissingBean(SqlhelperConfiguration.class)
    public SqlhelperConfiguration sqlhelperConfiguration(PrintConfiguration printConfiguration,
                                                         SqlBuilderConfiguration sqlBuilderConfiguration,
                                                         JdbcConfiguration jdbcConfiguration) {
        SqlhelperConfiguration sqlhelperConfiguration = new SqlhelperConfigurationYamlSwapper().swap(sqlhelperConfigurationProperties);
        sqlhelperConfiguration.setPrint(printConfiguration);
        sqlhelperConfiguration.setSqlBuilder(sqlBuilderConfiguration);
        sqlhelperConfiguration.setJdbc(jdbcConfiguration);
        return sqlhelperConfiguration;
    }

    @Bean
    @ConditionalOnMissingBean(JdbcHelper.class)
    public JdbcHelper jdbcHelper(SqlhelperConfiguration sqlhelperConfiguration) {
        return SpringJdbcHelper.getInstance(jdbcTemplate, sqlhelperConfiguration.getJdbc());
    }

    @Bean
    @ConditionalOnMissingBean(JdbcFactory.class)
    public JdbcFactory jdbcFactory(JdbcHelper jdbcHelper) {
        return new JdbcFactory(jdbcHelper);
    }

    @Override
    public void setEnvironment(Environment environment) {

    }
}