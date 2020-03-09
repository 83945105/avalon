package pub.avalonframework.cloud.security.antlr.arrayinit;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author baichao
 */
public class ArrayInitTest {

    public static void main(String[] args) throws IOException {
        ANTLRInputStream input = new ANTLRInputStream("{1,2,3}");
        ArrayInitLexer lexer = new ArrayInitLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ArrayInitParser parser = new ArrayInitParser(tokens);
        ParseTree tree = parser.init();

        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new ShortToUnicodeString(), tree);
        System.out.println();
    }

    @Test
    void test() throws IOException {
    }
}