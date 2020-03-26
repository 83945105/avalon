package pub.avalonframework.security.data.antlr.v4.mysql;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalonframework.security.data.SqlRewrite;
import pub.avalonframework.security.data.TableRuleOperations;
import pub.avalonframework.security.data.antlr.SqlRewriteBuilder;

import java.util.Map;

/**
 * Mysql重写分析测试
 *
 * @author baichao
 */
public class MySqlRewriteAnalysisTest {

    SqlRewriteBuilder sqlRewriteBuilder = new SqlRewriteBuilder(new HikariDataSource() {{
        setDriverClassName("com.mysql.cj.jdbc.Driver");
        setJdbcUrl("jdbc:mysql://localhost:3306/sql_rewrite?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false");
        setUsername("root");
        setPassword("19910405");
    }});

    private Map<String, TableRuleOperations> getRuntimeTableRule(String sql) {
        SqlRewrite sqlRewrite = sqlRewriteBuilder.build(sql);
        sqlRewrite.run();
        return sqlRewrite.getRuleStore().getRuntimeTableRuleMap();
    }

    @Test
    void test() {
        Map<String, TableRuleOperations> ruleMap = getRuntimeTableRule("SELECT * FROM USER");
        Assertions.assertNotNull(ruleMap);
        Assertions.assertEquals(1, ruleMap.size());
        TableRuleOperations rule = ruleMap.get("USER");
        Assertions.assertNotNull(rule);
    }
}