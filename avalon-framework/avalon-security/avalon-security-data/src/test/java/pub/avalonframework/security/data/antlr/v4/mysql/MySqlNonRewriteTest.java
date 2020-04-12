package pub.avalonframework.security.data.antlr.v4.mysql;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalonframework.security.data.antlr.SqlRewriteBuilder;

import java.io.IOException;

/**
 * Mysql 不重写测试
 *
 * @author baichao
 */
public class MySqlNonRewriteTest {

    SqlRewriteBuilder sqlRewriteBuilder = new SqlRewriteBuilder(new HikariDataSource() {{
        setDriverClassName("com.mysql.cj.jdbc.Driver");
        setJdbcUrl("jdbc:mysql://localhost:3306/sql_rewrite?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false");
        setUsername("root");
        setPassword("19910405");
    }});

    private String rewriteSql(String sql) {
        return sqlRewriteBuilder.build(sql).run();
    }

    private void assertNonRewrite(String sql) {
        System.out.println(sql);
        Assertions.assertEquals(sql, rewriteSql(sql));
    }

    @Test
    void test() {
        assertNonRewrite("( SELECT * FROM USER )");
        assertNonRewrite("SELECT * FROM USER");
        assertNonRewrite("SELECT * FROM USER;");
        assertNonRewrite("SELECT A.* FROM USER A");
        assertNonRewrite("SELECT A.* FROM USER A;");
        assertNonRewrite("SELECT USER.* FROM USER");
        assertNonRewrite("SELECT ID ID FROM USER");
        assertNonRewrite("SELECT `ID` `ID` FROM USER");
        assertNonRewrite("SELECT ID AS ID FROM USER");
        assertNonRewrite("SELECT `ID` AS `ID` FROM USER");
        assertNonRewrite("SELECT ID id FROM USER");
        assertNonRewrite("SELECT `ID` `id` FROM USER");
        assertNonRewrite("SELECT ID AS id FROM USER");
        assertNonRewrite("SELECT `ID` AS `id` FROM USER");
        assertNonRewrite("SELECT ID, NAME, AGE FROM USER");
        assertNonRewrite("SELECT USER.*, NAME AS name2, AGE AS age2 FROM USER");
        assertNonRewrite("SELECT USER.*, NAME AS name, AGE AS age FROM USER");
        assertNonRewrite("SELECT A.*, A.NAME name2, A.AGE age2 FROM USER A");
        assertNonRewrite("SELECT A.*, A.NAME AS name, A.AGE AS age FROM USER A");
        assertNonRewrite("SELECT USER.* FROM USER INNER JOIN ROLE");
        assertNonRewrite("SELECT USER.* FROM USER AS USER LEFT JOIN ROLE ON ROLE.ID = ''");
        assertNonRewrite("SELECT USER.* FROM USER AS USER RIGHT JOIN ROLE ON ROLE.ID = ''");
        assertNonRewrite("SELECT USER.* FROM USER INNER JOIN ROLE ON ROLE.USER_ID = USER.ID");
        assertNonRewrite("SELECT USER.* FROM USER INNER JOIN ROLE ON ROLE.USER_ID = USER.ID AND ROLE.ID = ''");
        assertNonRewrite("SELECT USER.* FROM USER INNER JOIN ROLE ON ROLE.USER_ID = USER.ID OR ROLE.ID = ''");
        assertNonRewrite("SELECT USER.* FROM USER INNER JOIN ROLE ON ROLE.USER_ID > ''");
        assertNonRewrite("SELECT USER.* FROM USER INNER JOIN ROLE ON ROLE.USER_ID >= ''");
        assertNonRewrite("SELECT USER.* FROM USER INNER JOIN ROLE ON ROLE.USER_ID < ''");
        assertNonRewrite("SELECT USER.* FROM USER INNER JOIN ROLE ON ROLE.USER_ID <= ''");
        assertNonRewrite("SELECT USER.* FROM USER INNER JOIN ROLE ON ROLE.USER_ID <> ''");
        assertNonRewrite("SELECT USER.* FROM USER INNER JOIN ROLE ON ROLE.USER_ID != ''");
        assertNonRewrite("SELECT USER.* FROM USER INNER JOIN ROLE ON ROLE.USER_ID BETWEEN '' AND ''");
        assertNonRewrite("SELECT USER.* FROM USER INNER JOIN ROLE ON ROLE.USER_ID IN ( '' )");
        assertNonRewrite("SELECT USER.* FROM USER INNER JOIN ROLE ON ROLE.USER_ID IN ( '', '' )");
        assertNonRewrite("SELECT USER.* FROM USER INNER JOIN ROLE ON ROLE.USER_ID NOT IN ( '', '' )");
        assertNonRewrite("SELECT A.* FROM USER A INNER JOIN ROLE B ON B.USER_ID = A.ID");
        assertNonRewrite("SELECT A.* FROM USER AS A INNER JOIN ROLE AS B ON B.USER_ID = A.ID");
        assertNonRewrite("SELECT USER.* FROM USER INNER JOIN ROLE ON ( ROLE.ID = '' AND ROLE.ID = '' )");
        assertNonRewrite("SELECT USER.* FROM USER INNER JOIN ROLE ON ( ROLE.ID BETWEEN '' AND '' AND ROLE.ID LIKE '' AND ROLE.ID IN ( '', '' ) )");
        assertNonRewrite("SELECT A.* FROM USER A INNER JOIN ROLE B ON ( B.ROLE.ID = '' AND B.ROLE.ID = '' )");
        assertNonRewrite("SELECT USER.* FROM USER INNER JOIN ROLE ON ROLE.ID = '' OR ( ROLE.ID = '' OR ROLE.ID = '' )");
        assertNonRewrite("SELECT A.* FROM USER A INNER JOIN ROLE B ON B.ID = '' OR ( B.ID = '' OR B.ID = '' )");
        assertNonRewrite("SELECT USER.* FROM USER INNER JOIN ROLE ON ( ROLE.USER_ID = USER.ID AND ROLE.NAME = USER.ROLE_NAME )");
        assertNonRewrite("SELECT USER.* FROM USER INNER JOIN ROLE ON ( ROLE.USER_ID = USER.ID ) LEFT JOIN RESOURCE ON ( RESOURCE.USER_ID = USER.ID AND RESOURCE.ROLE_ID = ROLE.ID )");
        assertNonRewrite("SELECT USER.* FROM USER INNER JOIN ROLE ON ( ROLE.USER_ID IS NULL ) LEFT JOIN RESOURCE ON ( RESOURCE.USER_ID = USER.ID AND RESOURCE.ROLE_ID IS NOT NULL )");
        assertNonRewrite("SELECT * FROM ( SELECT * FROM USER ) T");
        assertNonRewrite("SELECT T.* FROM ( SELECT * FROM USER ) T");
        assertNonRewrite("SELECT T.*, T.ID id, T.ID AS id2 FROM ( SELECT * FROM USER ) AS T");
        assertNonRewrite("SELECT USER.* FROM USER INNER JOIN ( SELECT * FROM USER ) T");
        assertNonRewrite("SELECT T.* FROM USER INNER JOIN ( SELECT * FROM USER ) T ON T.USER_ID = USER.ID");
        assertNonRewrite("SELECT ( SELECT * FROM USER ) FROM USER INNER JOIN ( SELECT * FROM USER ) T");
        assertNonRewrite("SELECT ( SELECT * FROM USER ) id FROM USER INNER JOIN ( SELECT * FROM USER ) T");
        assertNonRewrite("SELECT ( SELECT * FROM USER ) AS id FROM USER INNER JOIN ( SELECT * FROM USER ) T");
        assertNonRewrite("SELECT ( SELECT * FROM USER ), ( SELECT * FROM USER ) FROM USER INNER JOIN ( SELECT * FROM USER ) T");
        assertNonRewrite("SELECT * FROM USER WHERE ID = ''");
        assertNonRewrite("SELECT * FROM USER WHERE ID IS NULL");
        assertNonRewrite("SELECT * FROM USER WHERE ID IS NOT NULL");
        assertNonRewrite("SELECT * FROM USER WHERE ( ID = '' OR NAME LIKE '' ) OR AGE BETWEEN '' AND ''");
        assertNonRewrite("SELECT * FROM USER WHERE ID IN ( SELECT ID FROM USER )");
        assertNonRewrite("SELECT * FROM USER WHERE ID = ( SELECT ID FROM USER )");
        assertNonRewrite("SELECT * FROM USER WHERE ID LIKE ( SELECT ID FROM USER )");
        assertNonRewrite("SELECT * FROM USER WHERE ID BETWEEN ( SELECT ID FROM USER ) AND ''");
        assertNonRewrite("SELECT * FROM USER WHERE ID BETWEEN ( SELECT ID FROM USER ) AND ( SELECT ID FROM USER )");
        assertNonRewrite("SELECT * FROM USER WHERE ID IS NULL AND NAME IS NOT NULL");
        assertNonRewrite("SELECT * FROM USER WHERE ID NOT IN ( SELECT ID FROM USER )");
        assertNonRewrite("SELECT * FROM USER GROUP BY ID");
        assertNonRewrite("SELECT * FROM USER GROUP BY ID, NAME, AGE");
        assertNonRewrite("SELECT * FROM USER WHERE ID = '' GROUP BY NAME");
        assertNonRewrite("SELECT * FROM USER WHERE ID = '' HAVING NAME = ''");
        assertNonRewrite("SELECT * FROM USER WHERE ID = '' GROUP BY NAME HAVING NAME LIKE ''");
        assertNonRewrite("SELECT * FROM USER ORDER BY ID");
        assertNonRewrite("SELECT * FROM USER ORDER BY ID ASC, NAME DESC");
        assertNonRewrite("SELECT * FROM USER ORDER BY USER.ID ASC, USER.NAME DESC");
        assertNonRewrite("SELECT * FROM USER LIMIT 1");
        assertNonRewrite("SELECT * FROM USER LIMIT 1, 2");
        assertNonRewrite("SELECT * FROM USER LIMIT 1 OFFSET 2");
        assertNonRewrite("SELECT * FROM ( SELECT ID AS idAlias FROM USER ) T WHERE idAlias = ''");
        assertNonRewrite("SELECT ( SELECT ID FROM USER ) id FROM ( SELECT ID AS idAlias FROM USER ) T INNER JOIN ROLE INNER JOIN ( SELECT ID FROM USER ) T2 WHERE idAlias = ''");
        assertNonRewrite("SELECT *, ID id2 FROM USER");
        assertNonRewrite("SELECT *, ID AS id2 FROM USER");
        assertNonRewrite("SELECT *, ID AS id2, NAME name2 FROM USER");
        assertNonRewrite("SELECT *, ID AS id2, NAME name2, AGE age FROM USER");
        assertNonRewrite("SELECT USER.* FROM ( SELECT * FROM USER ) USER INNER JOIN ROLE ON ROLE.ID = ''");
        assertNonRewrite("SELECT * FROM USER WHERE ( ID = '1' OR ID = '2' ) OR ID = '3'");
    }

    public static void main(String[] args) throws IOException {
        SqlRewriteBuilder sqlRewriteBuilder = new SqlRewriteBuilder(new HikariDataSource() {{
            setDriverClassName("com.mysql.cj.jdbc.Driver");
            setJdbcUrl("jdbc:mysql://localhost:3306/sql_rewrite?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false");
            setUsername("root");
            setPassword("19910405");
        }});
        long timeStart = System.nanoTime();
//        String sql = sqlRewriteBuilder.build("SELECT USER.ID, USER.NAME AS name FROM USER INNER JOIN ROLE ON ROLE.USER_ID = USER.ID INNER JOIN RESOURCE ON RESOURCE.ROLE_ID = ROLE.ID").run();
//        String sql = sqlRewriteBuilder.build("SELECT * FROM ( SELECT ID AS idAlias FROM USER ) T WHERE idAlias = ''").run();
        String sql = sqlRewriteBuilder.build("SELECT * FROM USER WHERE ( ID = '1' OR ID = '2' ) OR ID = '3'").run();
        long time = System.nanoTime() - timeStart;
        System.out.println("用时: " + (time / 1000000) + "毫秒");

        System.out.println("\n");
        System.out.println(sql);
    }
}