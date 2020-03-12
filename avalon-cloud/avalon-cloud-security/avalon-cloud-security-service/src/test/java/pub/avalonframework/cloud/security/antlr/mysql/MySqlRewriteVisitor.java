package pub.avalonframework.cloud.security.antlr.mysql;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.IOException;
import java.util.List;

/**
 * @author baichao
 */
public class MySqlRewriteVisitor extends MySqlParserBaseVisitor<String> {

    private SqlBuilder sqlBuilder = new SqlBuilder();

    public String getSql() {
        return sqlBuilder.toString();
    }

    @Override
    public String visitSimpleSelect(MySqlParser.SimpleSelectContext ctx) {
        MySqlParser.QuerySpecificationContext querySpecification = ctx.querySpecification();
        if (querySpecification != null) {
            visit(querySpecification);
            return sqlBuilder.toString();
        }
        return unsupportedSqlNode();
    }

    @Override
    public String visitQuerySpecification(MySqlParser.QuerySpecificationContext ctx) {
        TerminalNode select = ctx.SELECT();
        MySqlParser.SelectElementsContext selectElements = ctx.selectElements();
        MySqlParser.FromClauseContext fromClause = ctx.fromClause();
        if (select != null && selectElements != null && fromClause != null) {
            sqlBuilder.appendWithSpace(select);
            visit(selectElements);
            visit(fromClause);
            return null;
        }
        return unsupportedSqlNode();
    }

    @Override
    public String visitSelectElements(MySqlParser.SelectElementsContext ctx) {
        TerminalNode star = ctx.STAR();
        if (star != null) {
            sqlBuilder.appendWithSpace(star);
            return null;
        }
        List<MySqlParser.SelectElementContext> selectElement = ctx.selectElement();
        if (selectElement.size() <= 0) {
            return sqlSyntaxError();
        }
        List<TerminalNode> comma = ctx.COMMA();
        int len = comma.size();
        if (len != selectElement.size() - 1) {
            return sqlSyntaxError();
        }
        for (int i = 0; i <= len; i++) {
            visit(selectElement.get(i));
            if (i < len) {
                sqlBuilder.append(comma.get(i));
            }
        }
        return null;
    }

    @Override
    public String visitSelectStarElement(MySqlParser.SelectStarElementContext ctx) {
        sqlBuilder.appendWithSpace(ctx.getText());
        return null;
    }

    @Override
    public String visitSelectColumnElement(MySqlParser.SelectColumnElementContext ctx) {
        MySqlParser.FullColumnNameContext fullColumnName = ctx.fullColumnName();
        if (fullColumnName != null) {
            sqlBuilder.appendWithSpace(fullColumnName);
            TerminalNode as = ctx.AS();
            if (as != null) {
                sqlBuilder.appendWithSpace(as);
            }
            MySqlParser.UidContext uid = ctx.uid();
            if (uid != null) {
                sqlBuilder.appendWithSpace(uid);
            }
            return null;
        }
        return unsupportedSqlNode();
    }

    @Override
    public String visitSelectExpressionElement(MySqlParser.SelectExpressionElementContext ctx) {
        MySqlParser.ExpressionContext expression = ctx.expression();
        if (expression != null) {
            visit(expression);
            TerminalNode as = ctx.AS();
            if (as != null) {
                sqlBuilder.appendWithSpace(as);
            }
            MySqlParser.UidContext uid = ctx.uid();
            if (uid != null) {
                sqlBuilder.appendWithSpace(uid);
            }
            return null;
        }
        return unsupportedSqlNode();
    }

    @Override
    public String visitSubqueryExpessionAtom(MySqlParser.SubqueryExpessionAtomContext ctx) {
        TerminalNode lrBracket = ctx.LR_BRACKET();
        MySqlParser.SelectStatementContext selectStatement = ctx.selectStatement();
        TerminalNode rrBracket = ctx.RR_BRACKET();
        if(lrBracket != null && selectStatement != null && rrBracket != null) {
            sqlBuilder.appendWithSpace(lrBracket);
            visit(selectStatement);
            sqlBuilder.appendWithSpace(rrBracket);
            return null;
        }
        return unsupportedSqlNode();
    }

    @Override
    public String visitFromClause(MySqlParser.FromClauseContext ctx) {
        TerminalNode from = ctx.FROM();
        MySqlParser.TableSourcesContext tableSources = ctx.tableSources();
        if (from != null && tableSources != null) {
            sqlBuilder.appendWithSpace(from);
            visit(tableSources);
            return null;
        }
        return unsupportedSqlNode();
    }

    @Override
    public String visitTableSourceBase(MySqlParser.TableSourceBaseContext ctx) {
        MySqlParser.TableSourceItemContext tableSourceItem = ctx.tableSourceItem();
        List<MySqlParser.JoinPartContext> joinPartContexts = ctx.joinPart();
        if (tableSourceItem != null && joinPartContexts != null) {
            visit(tableSourceItem);
            for (MySqlParser.JoinPartContext joinPartContext : joinPartContexts) {
                visit(joinPartContext);
            }
            return null;
        }
        return unsupportedSqlNode();
    }

    @Override
    public String visitSubqueryTableItem(MySqlParser.SubqueryTableItemContext ctx) {
        MySqlParser.SelectStatementContext selectStatement = ctx.selectStatement();
        MySqlParser.UidContext uid = ctx.uid();
        if (selectStatement != null && uid != null) {
            visit(selectStatement);
            TerminalNode as = ctx.AS();
            if (as != null) {
                sqlBuilder.appendWithSpace(as);
            }
            sqlBuilder.appendWithSpace(uid);
            return null;
        }
        return unsupportedSqlNode();
    }

    @Override
    public String visitQueryExpression(MySqlParser.QueryExpressionContext ctx) {
        TerminalNode lrBracket = ctx.LR_BRACKET();
        MySqlParser.QuerySpecificationContext querySpecification = ctx.querySpecification();
        TerminalNode rrBracket = ctx.RR_BRACKET();
        if (lrBracket != null && querySpecification != null && rrBracket != null) {
            sqlBuilder.appendWithSpace(lrBracket);
            visit(querySpecification);
            sqlBuilder.appendWithSpace(rrBracket);
            return null;
        }
        return unsupportedSqlNode();
    }

    @Override
    public String visitAtomTableItem(MySqlParser.AtomTableItemContext ctx) {
        MySqlParser.TableNameContext tableName = ctx.tableName();
        if (tableName != null) {
            sqlBuilder.appendWithSpace(tableName);
            TerminalNode as = ctx.AS();
            if (as != null) {
                sqlBuilder.appendWithSpace(as);
            }
            MySqlParser.UidContext uidContext = ctx.uid();
            if (uidContext != null) {
                sqlBuilder.appendWithSpace(uidContext);
            }
            return null;
        }
        return unsupportedSqlNode();
    }

    @Override
    public String visitInnerJoin(MySqlParser.InnerJoinContext ctx) {
        TerminalNode join = ctx.JOIN();
        if (join != null) {
            TerminalNode inner = ctx.INNER();
            if (inner != null) {
                sqlBuilder.appendWithSpace(inner);
            }
            sqlBuilder.appendWithSpace(join);
            MySqlParser.TableSourceItemContext tableSourceItem = ctx.tableSourceItem();
            if (tableSourceItem == null) {
                return sqlSyntaxError();
            }
            visit(tableSourceItem);
            TerminalNode on = ctx.ON();
            if (on == null) {
                return null;
            }
            sqlBuilder.appendWithSpace(on);
            MySqlParser.ExpressionContext expression = ctx.expression();
            if (expression == null) {
                return sqlSyntaxError();
            }
            visit(ctx.expression());
            return null;
        }
        return unsupportedSqlNode();
    }

    @Override
    public String visitOuterJoin(MySqlParser.OuterJoinContext ctx) {
        TerminalNode left = ctx.LEFT();
        TerminalNode right = ctx.RIGHT();
        if (left != null) {
            sqlBuilder.appendWithSpace(left);
        } else if (right != null) {
            sqlBuilder.appendWithSpace(right);
        } else {
            return unsupportedSqlNode();
        }
        TerminalNode join = ctx.JOIN();
        if (join != null) {
            sqlBuilder.appendWithSpace(join);
            MySqlParser.TableSourceItemContext tableSourceItem = ctx.tableSourceItem();
            if (tableSourceItem == null) {
                return sqlSyntaxError();
            }
            visit(tableSourceItem);
            TerminalNode on = ctx.ON();
            if (on == null) {
                return null;
            }
            sqlBuilder.appendWithSpace(on);
            MySqlParser.ExpressionContext expression = ctx.expression();
            if (expression == null) {
                return sqlSyntaxError();
            }
            visit(ctx.expression());
            return null;
        }
        return unsupportedSqlNode();
    }

    // 处理条件与条件之间 AND | OR
    @Override
    public String visitLogicalExpression(MySqlParser.LogicalExpressionContext ctx) {
        List<MySqlParser.ExpressionContext> expression = ctx.expression();
        MySqlParser.LogicalOperatorContext logicalOperator = ctx.logicalOperator();
        if (expression.size() == 2 && logicalOperator != null) {
            visit(expression.get(0));
            visit(logicalOperator);
            visit(expression.get(1));
            return null;
        }
        return unsupportedSqlNode();
    }

    @Override
    public String visitLogicalOperator(MySqlParser.LogicalOperatorContext ctx) {
        TerminalNode and = ctx.AND();
        if (and != null) {
            sqlBuilder.appendWithSpace(and);
            return null;
        }
        TerminalNode or = ctx.OR();
        if (or != null) {
            sqlBuilder.appendWithSpace(or);
            return null;
        }
        return unsupportedSqlNode();
    }

    @Override
    public String visitPredicateExpression(MySqlParser.PredicateExpressionContext ctx) {
        MySqlParser.PredicateContext predicate = ctx.predicate();
        if (predicate != null) {
            visit(predicate);
            return null;
        }
        return unsupportedSqlNode();
    }

    @Override
    public String visitBinaryComparasionPredicate(MySqlParser.BinaryComparasionPredicateContext ctx) {
        MySqlParser.PredicateContext left = ctx.left;
        MySqlParser.ComparisonOperatorContext comparisonOperator = ctx.comparisonOperator();
        MySqlParser.PredicateContext right = ctx.right;
        if (left != null && comparisonOperator != null && right != null) {
            //TODO sql去重
            sqlBuilder.appendWithSpace(left).appendWithSpace(comparisonOperator).appendWithSpace(right);
            return null;
        }
        return unsupportedSqlNode();
    }

    @Override
    public String visitLikePredicate(MySqlParser.LikePredicateContext ctx) {
        List<MySqlParser.PredicateContext> predicate = ctx.predicate();
        TerminalNode like = ctx.LIKE();
        if (predicate.size() == 2 && like != null) {
            //TODO sql去重
            sqlBuilder.appendWithSpace(predicate.get(0))
                    .appendWithSpace(like)
                    .appendWithSpace(predicate.get(1));
            return null;
        }
        return unsupportedSqlNode();
    }

    @Override
    public String visitBetweenPredicate(MySqlParser.BetweenPredicateContext ctx) {
        TerminalNode between = ctx.BETWEEN();
        List<MySqlParser.PredicateContext> predicate = ctx.predicate();
        TerminalNode and = ctx.AND();
        if (between != null && predicate.size() == 3 && and != null) {
            sqlBuilder.appendWithSpace(predicate.get(0))
                    .appendWithSpace(between)
                    .appendWithSpace(predicate.get(1))
                    .appendWithSpace(and)
                    .appendWithSpace(predicate.get(2));
            return null;
        }
        return unsupportedSqlNode();
    }

    @Override
    public String visitInPredicate(MySqlParser.InPredicateContext ctx) {
        MySqlParser.PredicateContext predicate = ctx.predicate();
        TerminalNode in = ctx.IN();
        TerminalNode lrBracket = ctx.LR_BRACKET();
        MySqlParser.ExpressionsContext expressions = ctx.expressions();
        TerminalNode rrBracket = ctx.RR_BRACKET();
        if (predicate == null || in == null || lrBracket == null || expressions == null || rrBracket == null) {
            return sqlSyntaxError();
        }
        sqlBuilder.appendWithSpace(predicate);
        TerminalNode not = ctx.NOT();
        if (not != null) {
            sqlBuilder.appendWithSpace(not);
        }
        sqlBuilder.appendWithSpace(in)
                .appendWithSpace(lrBracket);
        visit(expressions);
        sqlBuilder.appendWithSpace(rrBracket);
        return null;
    }

    @Override
    public String visitNestedExpressionAtom(MySqlParser.NestedExpressionAtomContext ctx) {
        TerminalNode lrBracket = ctx.LR_BRACKET();
        List<MySqlParser.ExpressionContext> expression = ctx.expression();
        TerminalNode rrBracket = ctx.RR_BRACKET();
        if (lrBracket == null || expression.size() <= 0 || rrBracket == null) {
            return sqlSyntaxError();
        }
        sqlBuilder.appendWithSpace(lrBracket);
        for (MySqlParser.ExpressionContext expressionContext : expression) {
            visit(expressionContext);
        }
        sqlBuilder.appendWithSpace(rrBracket);
        return null;
    }

    @Override
    public String visitConstantExpressionAtom(MySqlParser.ConstantExpressionAtomContext ctx) {
        sqlBuilder.appendWithSpace(ctx.constant());
        return null;
    }

    @Override
    public String visitExpressions(MySqlParser.ExpressionsContext ctx) {
        List<MySqlParser.ExpressionContext> expression = ctx.expression();
        List<TerminalNode> comma = ctx.COMMA();
        int len = comma.size();
        if (len != expression.size() - 1) {
            return sqlSyntaxError();
        }
        for (int i = 0; i <= len; i++) {
            visit(expression.get(i));
            if (i < len) {
                sqlBuilder.appendWithSpace(comma.get(i));
            }
        }
        return null;
    }

    private String unsupportedSqlNode() {
        throw new RuntimeException("暂不支持的节点");
    }

    private String sqlSyntaxError() {
        throw new RuntimeException("Sql语法错误");
    }

    private final static class SqlBuilder implements Appendable {

        private final StringBuilder sql;

        public SqlBuilder() {
            this.sql = new StringBuilder();
        }

        public SqlBuilder(int capacity) {
            this.sql = new StringBuilder(capacity);
        }

        public SqlBuilder append(ParseTree tree) {
            sql.append(tree.getText());
            return this;
        }

        public SqlBuilder appendWithSpace(ParseTree tree) {
            sql.append(" ").append(tree.getText());
            return this;
        }

        public SqlBuilder appendWithSpace(CharSequence csq) {
            sql.append(" ").append(csq);
            return this;
        }

        @Override
        public SqlBuilder append(CharSequence csq) {
            sql.append(csq);
            return this;
        }

        @Override
        public SqlBuilder append(CharSequence csq, int start, int end) throws IOException {
            sql.append(csq, start, end);
            return this;
        }

        @Override
        public SqlBuilder append(char c) throws IOException {
            sql.append(c);
            return this;
        }

        @Override
        public String toString() {
            return sql.toString().trim();
        }
    }
}