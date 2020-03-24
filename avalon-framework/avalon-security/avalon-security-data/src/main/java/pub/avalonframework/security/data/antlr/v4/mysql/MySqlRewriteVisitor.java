package pub.avalonframework.security.data.antlr.v4.mysql;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import pub.avalonframework.security.data.ComparisonType;
import pub.avalonframework.security.data.LogicExpressionOperations;
import pub.avalonframework.security.data.RuleContext;
import pub.avalonframework.security.data.SqlRewrite;
import pub.avalonframework.security.data.sql.MysqlCacheJdbcOperations;
import pub.avalonframework.security.data.sql.SqlSyntaxErrorException;

import java.io.IOException;
import java.util.List;

/**
 * @author baichao
 */
public class MySqlRewriteVisitor extends MySqlParserBaseVisitor<String> implements SqlRewrite {

    private SqlBuilder sqlBuilder = new SqlBuilder();

    private RuleContext ruleContext;

    private ParseTree parseTree;

    public MySqlRewriteVisitor(String sql, MysqlCacheJdbcOperations jdbcOperations) {
        ANTLRInputStream input = new ANTLRInputStream(sql);
        MySqlLexer lexer = new MySqlLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MySqlParser parser = new MySqlParser(tokens);
        this.ruleContext = new RuleContext(jdbcOperations);
        this.parseTree = parser.root();
    }

    @Override
    public String visitRoot(MySqlParser.RootContext ctx) {
        MySqlParser.SqlStatementsContext sqlStatements = ctx.sqlStatements();
        if (sqlStatements == null) {
            return sqlSyntaxError();
        }
        visit(sqlStatements);
        return null;
    }

    @Override
    public String visitSqlStatements(MySqlParser.SqlStatementsContext ctx) {
        List<MySqlParser.SqlStatementContext> sqlStatement = ctx.sqlStatement();
        if (sqlStatement.size() == 0) {
            return sqlSyntaxError();
        }
        for (MySqlParser.SqlStatementContext sqlStatementContext : sqlStatement) {
            visit(sqlStatementContext);
        }
        List<MySqlParser.EmptyStatementContext> emptyStatement = ctx.emptyStatement();
        if (emptyStatement.size() > 0) {
            for (MySqlParser.EmptyStatementContext emptyStatementContext : emptyStatement) {
                sqlBuilder.append(emptyStatementContext);
            }
        }
        return null;
    }

    @Override
    public String visitSimpleSelect(MySqlParser.SimpleSelectContext ctx) {
        MySqlParser.QuerySpecificationContext querySpecification = ctx.querySpecification();
        if (querySpecification != null) {
            visit(querySpecification);
            return null;
        }
        return unsupportedSqlNode();
    }

    @Override
    public String visitQuerySpecification(MySqlParser.QuerySpecificationContext ctx) {
        TerminalNode select = ctx.SELECT();
        if (select == null) {
            return sqlSyntaxError();
        }
        sqlBuilder.appendWithSpace(select);
        MySqlParser.SelectElementsContext selectElements = ctx.selectElements();
        if (selectElements == null) {
            return sqlSyntaxError();
        }
        visit(selectElements);
        MySqlParser.FromClauseContext fromClause = ctx.fromClause();
        if (fromClause != null) {
            visit(fromClause);
        }
        MySqlParser.OrderByClauseContext orderByClause = ctx.orderByClause();
        if (orderByClause != null) {
            visit(orderByClause);
        }
        MySqlParser.LimitClauseContext limitClause = ctx.limitClause();
        if (limitClause != null) {
            visit(limitClause);
        }
        return null;
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
        if (fullColumnName == null) {
            return sqlSyntaxError();
        }
        MySqlParser.UidContext uid = fullColumnName.uid();
        if (uid == null) {
            return sqlSyntaxError();
        }
        String tableAlias = null;
        String columnName;
        String columnAlias;
        MySqlParser.DottedIdContext dottedId = fullColumnName.dottedId(0);
        if (dottedId == null) {
            // 没有表别名
            columnName = uid.getText();
        } else {
            tableAlias = uid.getText();
            columnName = dottedId.getText().substring(1);
        }
        sqlBuilder.appendWithSpace(fullColumnName);
        TerminalNode as = ctx.AS();
        if (as != null) {
            sqlBuilder.appendWithSpace(as);
        }
        uid = ctx.uid();
        if (uid != null) {
            columnAlias = uid.getText();
            sqlBuilder.appendWithSpace(columnAlias);
        } else {
            columnAlias = columnName;
        }
//        this.ruleContext.addRuntimeColumnAliasColumnName(tableAlias, columnName, columnAlias);
        return null;
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
        if (lrBracket != null && selectStatement != null && rrBracket != null) {
            sqlBuilder.appendWithSpace(lrBracket);
            this.ruleContext.addRuntimeSubRuleContext(ctx.hashCode() + "");//TODO 临时处理
            visit(selectStatement);
            this.ruleContext.getParentRuleContext();
            sqlBuilder.appendWithSpace(rrBracket);
            return null;
        }
        return unsupportedSqlNode();
    }

    @Override
    public String visitFromClause(MySqlParser.FromClauseContext ctx) {
        TerminalNode from = ctx.FROM();
        MySqlParser.TableSourcesContext tableSources = ctx.tableSources();
        if (from == null || tableSources == null) {
            return sqlSyntaxError();
        }
        sqlBuilder.appendWithSpace(from);
        visit(tableSources);
        TerminalNode where = ctx.WHERE();
        if (where != null) {
            this.ruleContext.addRuntimeWhereColumnRule();
            sqlBuilder.appendWithSpace(where);
            MySqlParser.ExpressionContext whereExpr = ctx.whereExpr;
            if (whereExpr == null) {
                return sqlSyntaxError();
            }
            this.ruleContext.addRuntimeLogicExpression(LogicExpressionOperations.AndOr.AND);
            visit(whereExpr);
        }
        TerminalNode group = ctx.GROUP();
        TerminalNode by = ctx.BY();
        List<MySqlParser.GroupByItemContext> groupByItem = ctx.groupByItem();
        List<TerminalNode> comma = ctx.COMMA();
        if (group != null) {
            sqlBuilder.appendWithSpace(group);
            if (by == null) {
                return sqlSyntaxError();
            }
            sqlBuilder.appendWithSpace(by);
            if (groupByItem.size() == 0) {
                return sqlSyntaxError();
            }
            int len = comma.size();
            if (len != groupByItem.size() - 1) {
                return sqlSyntaxError();
            }
            for (int i = 0; i <= len; i++) {
                visit(groupByItem.get(i));
                if (i != len) {
                    sqlBuilder.append(comma.get(i));
                }
            }
        }
        TerminalNode having = ctx.HAVING();
        if (having != null) {
            sqlBuilder.appendWithSpace(having);
            MySqlParser.ExpressionContext havingExpr = ctx.havingExpr;
            if (havingExpr == null) {
                return sqlSyntaxError();
            }
            visit(havingExpr);
        }
        return null;
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
        if (selectStatement == null || uid == null) {
            return sqlSyntaxError();
        }
        String tableAlias = uid.getText();
        if (!this.ruleContext.hasMasterTable()) {
            // 设置主表信息
            this.ruleContext.setRuntimeMasterTableNameAlias(null, tableAlias);
        }
        // 添加运行时虚拟表信息
        RuleContext.TableColumnNamesInjector tableColumnNamesInjector = this.ruleContext.addRuntimeVirtualTable(tableAlias);
        // 添加运行时虚拟表规则
        this.ruleContext.addRuntimeVirtualTableRule(tableAlias);
        // 切换到运行时虚拟规则上下文
        this.ruleContext.addRuntimeSubVirtualRuleContext(tableAlias, tableColumnNamesInjector);
        visit(selectStatement);
        this.ruleContext.getParentRuleContext();
        TerminalNode as = ctx.AS();
        if (as != null) {
            sqlBuilder.appendWithSpace(as);
        }
        sqlBuilder.appendWithSpace(tableAlias);
        return null;
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
        if (tableName == null) {
            return sqlSyntaxError();
        }
        String tableNameStr = tableName.getText();
        String tableAliasStr = tableNameStr;
        sqlBuilder.appendWithSpace(tableNameStr);
        TerminalNode as = ctx.AS();
        if (as != null) {
            sqlBuilder.appendWithSpace(as);
        }
        MySqlParser.UidContext uidContext = ctx.uid();
        if (uidContext != null) {
            tableAliasStr = uidContext.getText();
            sqlBuilder.appendWithSpace(tableAliasStr);
        }
        if (!this.ruleContext.hasMasterTable()) {
            // 设置主表信息
            this.ruleContext.setRuntimeMasterTableNameAlias(tableNameStr, tableAliasStr);
        }
        // 添加运行时真实表信息
        this.ruleContext.addRuntimeRealTable(tableNameStr, tableAliasStr);
        // 添加运行时真实表规则
        this.ruleContext.addRuntimeRealTableRule(tableNameStr, tableAliasStr);
        return null;
    }

    @Override
    public String visitInnerJoin(MySqlParser.InnerJoinContext ctx) {
        TerminalNode join = ctx.JOIN();
        if (join == null) {
            return sqlSyntaxError();
        }
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
        this.ruleContext.addRuntimeOnColumnRule();
        MySqlParser.ExpressionContext expression = ctx.expression();
        if (expression == null) {
            return sqlSyntaxError();
        }
        this.ruleContext.addRuntimeLogicExpression(LogicExpressionOperations.AndOr.AND);
        visit(ctx.expression());
        return null;
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
        if (join == null) {
            return sqlSyntaxError();
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
        this.ruleContext.addRuntimeOnColumnRule();
        MySqlParser.ExpressionContext expression = ctx.expression();
        if (expression == null) {
            return sqlSyntaxError();
        }
        this.ruleContext.addRuntimeLogicExpression(LogicExpressionOperations.AndOr.AND);
        visit(ctx.expression());
        return null;
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
        TerminalNode or = ctx.OR();
        if (and == null && or == null) {
            return sqlSyntaxError();
        }
        if (and != null && or != null) {
            return sqlSyntaxError();
        }
        if (and != null) {
            String andStr = and.getText();
            sqlBuilder.appendWithSpace(andStr);//TODO 后面要删除  条件由归并后的 规则  重新生成
        } else {
            String orStr = or.getText();
            this.ruleContext.addRuntimeSubLogicExpression(LogicExpressionOperations.AndOr.OR);
            sqlBuilder.appendWithSpace(orStr);//TODO 后面要删除  条件由归并后的 规则  重新生成
        }
        return null;
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
    public String visitIsNullPredicate(MySqlParser.IsNullPredicateContext ctx) {
        MySqlParser.PredicateContext predicate = ctx.predicate();
        TerminalNode is = ctx.IS();
        MySqlParser.NullNotnullContext nullNotnullContext = ctx.nullNotnull();
        if (predicate == null || is == null || nullNotnullContext == null) {
            return sqlSyntaxError();
        }
        visit(predicate);
        sqlBuilder.appendWithSpace(is);
        visit(nullNotnullContext);
        return null;
    }

    @Override
    public String visitNullNotnull(MySqlParser.NullNotnullContext ctx) {
        TerminalNode not = ctx.NOT();
        if (not != null) {
            sqlBuilder.appendWithSpace(not);
        }
        TerminalNode nullLiteral = ctx.NULL_LITERAL();
        if (nullLiteral == null) {
            return sqlSyntaxError();
        }
        sqlBuilder.appendWithSpace(nullLiteral);
        return null;
    }

    @Override
    public String visitBinaryComparasionPredicate(MySqlParser.BinaryComparasionPredicateContext ctx) {
        List<MySqlParser.PredicateContext> predicate = ctx.predicate();
        MySqlParser.ComparisonOperatorContext comparisonOperator = ctx.comparisonOperator();
        if (predicate.size() != 2 || comparisonOperator == null) {
            return sqlSyntaxError();
        }
        this.ruleContext.addRuntimePredicateExpression();
        visit(predicate.get(0));//TODO 后面要删除  条件由归并后的 规则  重新生成
        String symbol = comparisonOperator.getText();
        this.ruleContext.setRuntimeComparisonType(ComparisonType.parseComparison(symbol));
        sqlBuilder.appendWithSpace(symbol);//TODO 后面要删除  条件由归并后的 规则  重新生成
        visit(predicate.get(1));//TODO 后面要删除  条件由归并后的 规则  重新生成
        return null;
    }

    /**
     * 只需要判断原生字段是否需要重写
     * 对像子查询等虚拟字段,合并逻辑后保证条件还在即可
     */
    @Override
    public String visitFullColumnNameExpressionAtom(MySqlParser.FullColumnNameExpressionAtomContext ctx) {
        MySqlParser.FullColumnNameContext fullColumnName = ctx.fullColumnName();
        if (fullColumnName == null) {
            return sqlSyntaxError();
        }
        MySqlParser.UidContext uid = fullColumnName.uid();
        if (uid == null) {
            return sqlSyntaxError();
        }
        MySqlParser.DottedIdContext dottedId = fullColumnName.dottedId(0);
        String tableName;
        String tableAlias;
        String columnName;
        String columnAlias;
        if (dottedId == null) {// 只有字段名没有表别名
            // 对于原生字段来说, columnAlias = columnName
            // 对于虚拟字段来说, 有可能 columnAlias == columnName 也有可能 columnAlias != columnName
            columnAlias = uid.getText();
            if (this.ruleContext.runtimeOnlyMasterTable()) {
                // 有且只有主表
                tableName = this.ruleContext.getMasterTableName();
                tableAlias = this.ruleContext.getMasterTableAlias();
                //
            } else {
                //TODO 未考虑子查询的列 此时不应该去数据库查询
//                tableName = jdbcOperations.selectTableNameOfUniqueColumnName(this.ruleContext.getRuntimeTableNames(), columnName);
//                tableAlias = tableName;
            }
        } else {
            tableAlias = uid.getText();
//            tableName = this.ruleContext.getRuntimeTableNameByTableAlias(tableAlias);

        }
        sqlBuilder.appendWithSpace(fullColumnName);//TODO 后面要删除  条件由归并后的 规则  重新生成
        return null;
    }

    @Override
    public String visitLikePredicate(MySqlParser.LikePredicateContext ctx) {
        List<MySqlParser.PredicateContext> predicate = ctx.predicate();
        TerminalNode like = ctx.LIKE();
        if (predicate.size() != 2 || like == null) {
            return sqlSyntaxError();
        }
        //TODO sql去重
        visit(predicate.get(0));
        sqlBuilder.appendWithSpace(like);
        visit(predicate.get(1));
        return null;
    }

    @Override
    public String visitBetweenPredicate(MySqlParser.BetweenPredicateContext ctx) {
        TerminalNode between = ctx.BETWEEN();
        List<MySqlParser.PredicateContext> predicate = ctx.predicate();
        TerminalNode and = ctx.AND();
        if (between == null || predicate.size() != 3 || and == null) {
            return sqlSyntaxError();
        }
        visit(predicate.get(0));
        sqlBuilder.appendWithSpace(between);
        visit(predicate.get(1));
        sqlBuilder.appendWithSpace(and);
        visit(predicate.get(2));
        return null;
    }

    @Override
    public String visitInPredicate(MySqlParser.InPredicateContext ctx) {
        MySqlParser.PredicateContext predicate = ctx.predicate();
        TerminalNode in = ctx.IN();
        TerminalNode lrBracket = ctx.LR_BRACKET();
        MySqlParser.ExpressionsContext expressions = ctx.expressions();
        MySqlParser.SelectStatementContext selectStatement = ctx.selectStatement();
        TerminalNode rrBracket = ctx.RR_BRACKET();
        if (predicate == null || in == null || lrBracket == null || rrBracket == null) {
            return sqlSyntaxError();
        }
        if (expressions == null && selectStatement == null) {
            return sqlSyntaxError();
        }
        sqlBuilder.appendWithSpace(predicate);
        TerminalNode not = ctx.NOT();
        if (not != null) {
            sqlBuilder.appendWithSpace(not);
        }
        sqlBuilder.appendWithSpace(in)
                .appendWithSpace(lrBracket);
        if (expressions != null) {
            visit(expressions);
        }
        if (selectStatement != null) {
            this.ruleContext.addRuntimeSubRuleContext(ctx.hashCode() + "");//TODO 临时处理
            visit(selectStatement);
            this.ruleContext.getParentRuleContext();
        }
        sqlBuilder.appendWithSpace(rrBracket);
        return null;
    }

    @Override
    public String visitNestedExpressionAtom(MySqlParser.NestedExpressionAtomContext ctx) {
        TerminalNode lrBracket = ctx.LR_BRACKET();
        List<MySqlParser.ExpressionContext> expression = ctx.expression();
        TerminalNode rrBracket = ctx.RR_BRACKET();
        if (lrBracket == null || expression.size() < 1 || rrBracket == null) {
            return sqlSyntaxError();
        }
        if (this.ruleContext.getRuntimeAndOr() == LogicExpressionOperations.AndOr.AND) {
            this.ruleContext.addRuntimeSubLogicExpression(LogicExpressionOperations.AndOr.AND);
        }
        sqlBuilder.appendWithSpace(lrBracket);//TODO 后面要删除  条件由归并后的 规则  重新生成
        for (MySqlParser.ExpressionContext expressionContext : expression) {
            visit(expressionContext);
        }
        sqlBuilder.appendWithSpace(rrBracket);//TODO 后面要删除  条件由归并后的 规则  重新生成
        return null;
    }

    @Override
    public String visitConstantExpressionAtom(MySqlParser.ConstantExpressionAtomContext ctx) {
        String constant = ctx.constant().getText();
        sqlBuilder.appendWithSpace(constant);
        return constant;
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
                sqlBuilder.append(comma.get(i));
            }
        }
        return null;
    }

    @Override
    public String visitOrderByClause(MySqlParser.OrderByClauseContext ctx) {
        TerminalNode order = ctx.ORDER();
        TerminalNode by = ctx.BY();
        List<MySqlParser.OrderByExpressionContext> orderByExpression = ctx.orderByExpression();
        if (order == null || by == null || orderByExpression.size() < 1) {
            return sqlSyntaxError();
        }
        List<TerminalNode> comma = ctx.COMMA();
        int len = comma.size();
        if (len >= orderByExpression.size()) {
            return sqlSyntaxError();
        }
        sqlBuilder.appendWithSpace(order)
                .appendWithSpace(by);
        for (int i = 0; i <= len; i++) {
            visit(orderByExpression.get(i));
            if (i != len) {
                sqlBuilder.append(comma.get(i));
            }
        }
        return null;
    }

    @Override
    public String visitOrderByExpression(MySqlParser.OrderByExpressionContext ctx) {
        MySqlParser.ExpressionContext expression = ctx.expression();
        if (expression == null) {
            return sqlSyntaxError();
        }
        visit(expression);
        TerminalNode asc = ctx.ASC();
        if (asc != null) {
            sqlBuilder.appendWithSpace(asc);
        }
        TerminalNode desc = ctx.DESC();
        if (desc != null) {
            sqlBuilder.appendWithSpace(desc);
        }
        return null;
    }

    @Override
    public String visitLimitClause(MySqlParser.LimitClauseContext ctx) {
        TerminalNode limit = ctx.LIMIT();
        List<MySqlParser.LimitClauseAtomContext> limitClauseAtom = ctx.limitClauseAtom();
        int len = limitClauseAtom.size();
        if (limit == null || len < 1) {
            return sqlSyntaxError();
        }
        sqlBuilder.appendWithSpace(limit);
        TerminalNode comma = ctx.COMMA();
        TerminalNode offset = ctx.OFFSET();
        if (comma != null && offset != null) {
            return sqlSyntaxError();
        }
        if (len == 1) {
            sqlBuilder.appendWithSpace(limitClauseAtom.get(0));
            if (comma != null || offset != null) {
                return sqlSyntaxError();
            }
        } else if (len == 2) {
            sqlBuilder.appendWithSpace(limitClauseAtom.get(0));
            if (comma == null && offset == null) {
                return sqlSyntaxError();
            }
            if (comma != null) {
                sqlBuilder.append(comma);
            }
            if (offset != null) {
                sqlBuilder.appendWithSpace(offset);
            }
            sqlBuilder.appendWithSpace(limitClauseAtom.get(1));
        } else {
            return sqlSyntaxError();
        }
        return null;
    }

    private String unsupportedSqlNode() {
        throw new RuntimeException("暂不支持的节点");
    }

    private String sqlSyntaxError() {
        throw new SqlSyntaxErrorException("Sql语法错误");
    }

    public String getSql() {
        return sqlBuilder.toString();
    }

    @Override
    public String run() {
        visit(this.parseTree);
        return getSql();
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