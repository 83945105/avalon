package pub.avalonframework.cloud.gar.config.jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;

/**
 * @author baichao
 */
@Configuration
public class JdbcConfig {

    @Bean
    public SpringJdbcEngine springJdbcEngine(JdbcTemplate jdbcTemplate) {
        SpringJdbcEngine engine = new SpringJdbcEngine();
        engine.setJdbcTemplate(jdbcTemplate);
        return engine;
    }
}