package pub.avalonframework.jdbc.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import pub.avalonframework.sqlhelper.jdbc.core.SpringJdbcHelper;

import javax.sql.DataSource;

/**
 * @author baichao
 */
public class SpringJdbcTemplateTest {

    private static SpringJdbcHelper springJdbcHelper;

    @BeforeAll
    static void beforeAll() {
        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/gar-2.1.x?useUnicode=true&characterEncoding=utf8&useSSL=false")
                .username("root")
                .password("root")
                .build();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        springJdbcHelper = SpringJdbcHelper.getInstance(jdbcTemplate);
    }

    @Test
    void Test() {
        System.out.println(springJdbcHelper.getDatabaseType());
    }
}