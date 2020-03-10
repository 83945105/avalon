package pub.avalonframework.cloud.security.antlr.expr;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * @author baichao
 */
public class Calc {

    public static void main(String[] args) {
        ANTLRInputStream input = new ANTLRInputStream("1 + 2 + 3 + 4 + 5");
        LabeledExprLexer lexer = new LabeledExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LabeledExprParser parser = new LabeledExprParser(tokens);
        ParseTree tree = parser.prog();

        EvalVisitor eval = new EvalVisitor();
        eval.visit(tree);

    }
}