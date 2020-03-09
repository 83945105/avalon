package pub.avalonframework.cloud.security.antlr;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.UnbufferedCharStream;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

/**
 * @author baichao
 */
public class AntlrTest {

    @Test
    void test() {
        MySqlLexer lexer = new MySqlLexer(
                new UnbufferedCharStream(
                        new StringReader("SELECT * FROM USER")));
        CommonTokenStream token = new CommonTokenStream(lexer);
        MySqlParser parser = new MySqlParser(token);
        MySqlParser.RootContext root = parser.root();
    }
}