package pub.avalonframework.jdbc.core.spring;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import pub.avalonframework.jdbc.core.spring.api.entity.SysUser;
import pub.avalonframework.jdbc.core.spring.controller.SysUserController;
import pub.avalonframework.jdbc.core.spring.dao.IDataSource;
import pub.avalonframework.jdbc.core.spring.dao.SysUserJdbcDao;
import pub.avalonframework.jdbc.core.spring.service.impl.SysUserServiceImpl;
import pub.avalonframework.sqlhelper.jdbc.core.PageResult;
import pub.avalonframework.sqlhelper.jdbc.core.spring.context.annotation.JdbcScan;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author baichao
 */
@SpringBootTest(
        classes = {
                SpringTest.Configuration.class,
                SysUserServiceImpl.class,
                SysUserController.class
        },
        // 启动原生容器, 并使用自定义端口
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@EnableAutoConfiguration
@SpringBootConfiguration
@JdbcScan(basePackages = {"pub.avalonframework.jdbc.core.spring.dao"})
public class SpringTest {

    @TestConfiguration
    public static class Configuration {
//        @Bean
//        @Primary
//        @ConfigurationProperties(prefix = "spring.datasource.sqlhelper")
//        public DataSource ds0MasterDataSource() {
//            return new IDataSource(DataSourceBuilder.create().build());
//            return DataSourceBuilder.create().build();
//        }

        @Bean
        @Primary
        public IDataSource dataSource() {
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setUsername("root");
            dataSource.setPassword("19910405");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/sqlhelper?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai");
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            return new IDataSource(dataSource);
        }
    }

    @Autowired
    private SysUserController testSysUserController;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SysUserJdbcDao sysUserJdbcDao;

    @Test
    void test() throws Exception {
        PageResult<SysUser> pageResult = this.sysUserJdbcDao.pageQuery(1, 10);
        List<SysUser> list = pageResult.getEntity();
//        SysUser sysUser = testSysUserController.getSysUserById("1");
    }
}
