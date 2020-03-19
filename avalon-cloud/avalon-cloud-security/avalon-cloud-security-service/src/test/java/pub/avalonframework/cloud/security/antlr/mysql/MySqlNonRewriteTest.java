package pub.avalonframework.cloud.security.antlr.mysql;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author baichao
 */
public class MySqlNonRewriteTest {

    private String rewriteSql(String sql) {
        ANTLRInputStream input = new ANTLRInputStream(sql);
        MySqlLexer lexer = new MySqlLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MySqlParser parser = new MySqlParser(tokens);
        MySqlParser.RootContext tree = parser.root();
        MySqlRewriteVisitor visitor = new MySqlRewriteVisitor();
        visitor.visit(tree);
        return visitor.getSql();
    }

    private void assertNonRewrite(String sql) {
        Assertions.assertEquals(sql, rewriteSql(sql));
    }

    @Test
    void test() {
        assertNonRewrite("SELECT * FROM USER");
        assertNonRewrite("SELECT A.* FROM USER A");
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
        assertNonRewrite("SELECT USER.*, NAME, AGE FROM USER");
        assertNonRewrite("SELECT USER.*, NAME AS name, AGE AS age FROM USER");
        assertNonRewrite("SELECT A.*, A.NAME, A.AGE FROM USER A");
        assertNonRewrite("SELECT A.*, A.NAME AS name, A.AGE AS age FROM USER A");
        assertNonRewrite("SELECT * FROM USER INNER JOIN ROLE");
        assertNonRewrite("SELECT * FROM USER LEFT JOIN ROLE");
        assertNonRewrite("SELECT * FROM USER RIGHT JOIN ROLE");
        assertNonRewrite("SELECT * FROM USER INNER JOIN ROLE ON ROLE.USER_ID = USER.ID");
        assertNonRewrite("SELECT * FROM USER INNER JOIN ROLE ON ROLE.USER_ID = USER.ID AND ROLE.ID = ''");
        assertNonRewrite("SELECT * FROM USER INNER JOIN ROLE ON ROLE.USER_ID = USER.ID OR ROLE.ID = ''");
        assertNonRewrite("SELECT * FROM USER INNER JOIN ROLE ON ROLE.USER_ID > ''");
        assertNonRewrite("SELECT * FROM USER INNER JOIN ROLE ON ROLE.USER_ID >= ''");
        assertNonRewrite("SELECT * FROM USER INNER JOIN ROLE ON ROLE.USER_ID < ''");
        assertNonRewrite("SELECT * FROM USER INNER JOIN ROLE ON ROLE.USER_ID <= ''");
        assertNonRewrite("SELECT * FROM USER INNER JOIN ROLE ON ROLE.USER_ID <> ''");
        assertNonRewrite("SELECT * FROM USER INNER JOIN ROLE ON ROLE.USER_ID != ''");
        assertNonRewrite("SELECT * FROM USER INNER JOIN ROLE ON ROLE.USER_ID BETWEEN '' AND ''");
        assertNonRewrite("SELECT * FROM USER INNER JOIN ROLE ON ROLE.USER_ID IN ( '' )");
        assertNonRewrite("SELECT * FROM USER INNER JOIN ROLE ON ROLE.USER_ID IN ( '', '' )");
        assertNonRewrite("SELECT * FROM USER INNER JOIN ROLE ON ROLE.USER_ID NOT IN ( '', '' )");
        assertNonRewrite("SELECT * FROM USER A INNER JOIN ROLE B ON B.USER_ID = A.ID");
        assertNonRewrite("SELECT * FROM USER AS A INNER JOIN ROLE AS B ON B.USER_ID = A.ID");
        assertNonRewrite("SELECT * FROM USER INNER JOIN ROLE ON ( ROLE.ID = '' AND ROLE.ID = '' )");
        assertNonRewrite("SELECT * FROM USER INNER JOIN ROLE ON ( ROLE.ID BETWEEN '' AND '' AND ROLE.ID LIKE '' AND ROLE.ID IN ( '', '' ) )");
        assertNonRewrite("SELECT * FROM USER A INNER JOIN ROLE B ON ( B.ROLE.ID = '' AND B.ROLE.ID = '' )");
        assertNonRewrite("SELECT * FROM USER INNER JOIN ROLE ON ROLE.ID = '' OR ( ROLE.ID = '' OR ROLE.ID = '' )");
        assertNonRewrite("SELECT * FROM USER A INNER JOIN ROLE B ON B.ID = '' OR ( B.ID = '' OR B.ID = '' )");
        assertNonRewrite("SELECT * FROM USER INNER JOIN ROLE ON ( ROLE.USER_ID = USER.ID AND ROLE.NAME = USER.ROLE_NAME )");
        assertNonRewrite("SELECT * FROM USER INNER JOIN ROLE ON ( ROLE.USER_ID = USER.ID ) LEFT JOIN RESOURCE ON ( RESOURCE.USER_ID = USER.ID AND RESOURCE.ROLE_ID = ROLE.ID )");
        assertNonRewrite("SELECT * FROM USER INNER JOIN ROLE ON ( ROLE.USER_ID IS NULL ) LEFT JOIN RESOURCE ON ( RESOURCE.USER_ID = USER.ID AND RESOURCE.ROLE_ID IS NOT NULL )");
        assertNonRewrite("SELECT * FROM ( SELECT * FROM USER ) T");
        assertNonRewrite("SELECT T.* FROM ( SELECT * FROM USER ) T");
        assertNonRewrite("SELECT T.*, T.ID id, T.ID AS id FROM ( SELECT * FROM USER ) AS T");
        assertNonRewrite("SELECT * FROM USER INNER JOIN ( SELECT * FROM USER ) T");
        assertNonRewrite("SELECT * FROM USER INNER JOIN ( SELECT * FROM USER ) T ON T.USER_ID = USER.ID");
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
    }

    public static void main(String[] args) throws IOException {
        ANTLRInputStream input = new ANTLRInputStream("SELECT * FROM USER WHERE ID = '' HAVING NAME = ''");
        MySqlLexer lexer = new MySqlLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MySqlParser parser = new MySqlParser(tokens);
        MySqlParser.RootContext tree = parser.root();

        System.out.println(tree.toStringTree(parser));

        MySqlRewriteVisitor visitor = new MySqlRewriteVisitor();
        visitor.visit(tree);
        System.out.println(visitor.getSql());
    }
}