package pub.avalonframework.jdbc.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import pub.avalonframework.sqlhelper.jdbc.core.SpringJdbcTemplate;

import javax.sql.DataSource;

/**
 * @author baichao
 */
public class SpringJdbcTemplateTest {

    private static SpringJdbcTemplate springJdbcTemplate;

    @BeforeAll
    static void beforeAll() {
        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/gar-2.1.x?useUnicode=true&characterEncoding=utf8&useSSL=false")
                .username("root")
                .password("root")
                .build();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        springJdbcTemplate = SpringJdbcTemplate.getInstance(jdbcTemplate);
    }

    @Test
    void Test() {
        System.out.println(springJdbcTemplate.getDatabaseType());
    }
}