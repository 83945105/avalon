package pub.avalonframework.security.data.antlr.v4.mysql;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import pub.avalonframework.security.data.*;
import pub.avalonframework.security.data.expression.ComparisonOperator;
import pub.avalonframework.security.data.expression.LogicOperator;
import pub.avalonframework.security.data.sql.MysqlCacheJdbcOperations;
import pub.avalonframework.security.data.sql.SqlSyntaxErrorException;

import java.io.IOException;
import java.util.List;

/**
 * @author baichao
 */
public class MySqlRewriteVisitor extends MySqlParserBaseVisitor<String> implements SqlRewrite {

    private RuleContextWrapper ruleContextWrapper;

    private ParseTree parseTree;

    public MySqlRewriteVisitor(String sql, MysqlCacheJdbcOperations jdbcOperations) {
        ANTLRInputStream input = new ANTLRInputStream(sql);
        MySqlLexer lexer = new MySqlLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MySqlParser parser = new MySqlParser(tokens);
        this.ruleContextWrapper = new RuleContextWrapper(new RuleContextWrapper.RuntimeRuleContext(jdbcOperations));
        this.parseTree = parser.root();
    }

    @Override
    public String visitRoot(MySqlParser.RootContext ctx) {
        MySqlParser.SqlStatementsContext sqlStatements = ctx.sqlStatements();
        if (sqlStatements == null) {
            return sqlSyntaxError();
        }
        return visit(sqlStatements);
    }

    @Override
    public String visitSqlStatements(MySqlParser.SqlStatementsContext ctx) {
        List<MySqlParser.SqlStatementContext> sqlStatement = ctx.sqlStatement();
        if (sqlStatement.size() == 0) {
            return sqlSyntaxError();
        }
        SqlBuilder sqlBuilder = new SqlBuilder();
        for (MySqlParser.SqlStatementContext sqlStatementContext : sqlStatement) {
            sqlBuilder.append(visit(sqlStatementContext));
        }
        List<MySqlParser.EmptyStatementContext> emptyStatement = ctx.emptyStatement();
        if (emptyStatement.size() > 0) {
            for (MySqlParser.EmptyStatementContext emptyStatementContext : emptyStatement) {
                sqlBuilder.append(emptyStatementContext);
            }
        }
        return sqlBuilder.toString();
    }

    @Override
    public String visitSimpleSelect(MySqlParser.SimpleSelectContext ctx) {
        MySqlParser.QuerySpecificationContext querySpecification = ctx.querySpecification();
        if (querySpecification == null) {
            return sqlSyntaxError();
        }
        return visit(querySpecification);
    }

    @Override
    public String visitQuerySpecification(MySqlParser.QuerySpecificationContext ctx) {
        TerminalNode select = ctx.SELECT();
        if (select == null) {
            return sqlSyntaxError();
        }
        SqlBuilder sqlBuilder = new SqlBuilder();
        sqlBuilder.appendWithSpace(select);
        MySqlParser.SelectElementsContext selectElements = ctx.selectElements();
        MySqlParser.FromClauseContext fromClause = ctx.fromClause();
        if (selectElements == null || fromClause == null) {
            return sqlSyntaxError();
        }
        // 先解析fromClause再解析selectElements, 要先把表关联上, 再计算列信息
        this.ruleContextWrapper.switchToRuntimeFromStage();// 切换至From阶段
        String fromClauseStr = visit(fromClause);
        this.ruleContextWrapper.switchToRuntimeSelectStage();// 切换至Select阶段
        sqlBuilder.append(visit(selectElements));
        // 解析完selectElements, 执行规则上下文相关方法
        this.ruleContextWrapper.addRuntimeTableColumnFinish();
        sqlBuilder.append(fromClauseStr);
        MySqlParser.OrderByClauseContext orderByClause = ctx.orderByClause();
        if (orderByClause != null) {
            this.ruleContextWrapper.switchToRuntimeOrderStage();// 切换至Order阶段
            sqlBuilder.append(visit(orderByClause));
        }
        MySqlParser.LimitClauseContext limitClause = ctx.limitClause();
        if (limitClause != null) {
            this.ruleContextWrapper.switchToRuntimeLimitStage();// 切换至Limit阶段
            sqlBuilder.append(visit(limitClause));
        }
        return sqlBuilder.toString();
    }

    @Override
    public String visitSelectElements(MySqlParser.SelectElementsContext ctx) {
        SqlBuilder sqlBuilder = new SqlBuilder();
        TerminalNode star = ctx.STAR();
        List<MySqlParser.SelectElementContext> selectElement = ctx.selectElement();
        int len = selectElement.size();
        if (star == null && len < 1) {
            return sqlSyntaxError();
        }
        if (star != null && len < 1) {// 只有 *
            this.ruleContextWrapper.addRuntimeStarColumn();
            sqlBuilder.appendWithSpace(star);
            return sqlBuilder.toString();
        }
        List<TerminalNode> comma = ctx.COMMA();
        if (star == null) {// 只有 列
            if (comma.size() != len - 1) {
                return sqlSyntaxError();
            }
            len = comma.size();
            for (int i = 0; i <= len; i++) {
                sqlBuilder.append(visit(selectElement.get(i)));
                if (i < len) {
                    sqlBuilder.append(comma.get(i));
                }
            }
            return sqlBuilder.toString();
        }
        if (comma.size() != len) {
            return sqlSyntaxError();
        }
        // 既有 * 也有 列
        this.ruleContextWrapper.addRuntimeStarColumn();
        sqlBuilder.appendWithSpace(star);
        sqlBuilder.append(comma.get(0));
        for (int i = 0; i < len; ) {
            sqlBuilder.append(visit(selectElement.get(i++)));
            if (i < len) {
                sqlBuilder.append(comma.get(i));
            }
        }
        return sqlBuilder.toString();
    }

    @Override
    public String visitSelectStarElement(MySqlParser.SelectStarElementContext ctx) {
        MySqlParser.FullIdContext fullId = ctx.fullId();
        TerminalNode dot = ctx.DOT();
        TerminalNode star = ctx.STAR();
        if (fullId == null || dot == null || star == null) {
            return sqlSyntaxError();
        }
        String tableAlias = fullId.getText();
        this.ruleContextWrapper.addRuntimeAllColumn(tableAlias);
        return new SqlBuilder().appendWithSpace(tableAlias).append(dot).append(star).toString();
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
        SqlBuilder sqlBuilder = new SqlBuilder();
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
        this.ruleContextWrapper.addRuntimeTableColumn(tableAlias, columnName, columnAlias);
        return sqlBuilder.toString();
    }

    @Override
    public String visitSelectExpressionElement(MySqlParser.SelectExpressionElementContext ctx) {
        MySqlParser.ExpressionContext expression = ctx.expression();
        if (expression == null) {
            return sqlSyntaxError();
        }
        SqlBuilder sqlBuilder = new SqlBuilder();
        sqlBuilder.append(visit(expression));
        TerminalNode as = ctx.AS();
        if (as != null) {
            sqlBuilder.appendWithSpace(as);
        }
        MySqlParser.UidContext uid = ctx.uid();
        if (uid != null) {
            sqlBuilder.appendWithSpace(uid);
        }
        return sqlBuilder.toString();
    }

    @Override
    public String visitSubqueryExpessionAtom(MySqlParser.SubqueryExpessionAtomContext ctx) {
        TerminalNode lrBracket = ctx.LR_BRACKET();
        MySqlParser.SelectStatementContext selectStatement = ctx.selectStatement();
        TerminalNode rrBracket = ctx.RR_BRACKET();
        if (lrBracket == null || selectStatement == null || rrBracket == null) {
            return sqlSyntaxError();
        }
        SqlBuilder sqlBuilder = new SqlBuilder();
        sqlBuilder.appendWithSpace(lrBracket);
        this.ruleContextWrapper.switchToSubRuntimeRuleContext(ctx.hashCode() + "");//TODO 临时处理
        sqlBuilder.append(visit(selectStatement));
        this.ruleContextWrapper.switchToParentRuntimeRuleContext();
        sqlBuilder.appendWithSpace(rrBracket);
        if (ruleContextWrapper.runtimePredicateExpressionStage()) {
            if (!ruleContextWrapper.hasRuntimePredicateExpressionColumn()) {
//                this.ruleContextWrapper.setRuntimePredicateExpressionColumn(tableAlias, columnName);
            } else if (!ruleContextWrapper.hasRuntimePredicateExpressionValue()) {
//                this.ruleContextWrapper.setRuntimePredicateExpressionColumnTypeValue(tableAlias, columnName);
            } else {
                return sqlSyntaxError();
            }
        }
        return sqlBuilder.toString();
    }

    @Override
    public String visitFromClause(MySqlParser.FromClauseContext ctx) {
        TerminalNode from = ctx.FROM();
        MySqlParser.TableSourcesContext tableSources = ctx.tableSources();
        if (from == null || tableSources == null) {
            return sqlSyntaxError();
        }
        SqlBuilder sqlBuilder = new SqlBuilder();
        sqlBuilder.appendWithSpace(from);
        sqlBuilder.append(visit(tableSources));
        TerminalNode where = ctx.WHERE();
        if (where != null) {
            this.ruleContextWrapper.addRuntimeWhereColumnRule();
            sqlBuilder.appendWithSpace(where);
            MySqlParser.ExpressionContext whereExpr = ctx.whereExpr;
            if (whereExpr == null) {
                return sqlSyntaxError();
            }
            this.ruleContextWrapper.switchToRuntimeWhereStage();// 切换至Where阶段
            this.ruleContextWrapper.addRuntimeLogicExpression(LogicOperator.AND);
            sqlBuilder.append(visit(whereExpr));
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
            this.ruleContextWrapper.switchToRuntimeGroupStage();// 切换至Group阶段
            for (int i = 0; i <= len; i++) {
                sqlBuilder.append(visit(groupByItem.get(i)));
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
            this.ruleContextWrapper.switchToRuntimeHavingStage();// 切换至Having阶段
            sqlBuilder.append(visit(havingExpr));
        }
        return sqlBuilder.toString();
    }

    @Override
    public String visitTableSourceBase(MySqlParser.TableSourceBaseContext ctx) {
        MySqlParser.TableSourceItemContext tableSourceItem = ctx.tableSourceItem();
        List<MySqlParser.JoinPartContext> joinPartContexts = ctx.joinPart();
        if (tableSourceItem == null || joinPartContexts == null) {
            return sqlSyntaxError();
        }
        SqlBuilder sqlBuilder = new SqlBuilder();
        sqlBuilder.append(visit(tableSourceItem));
        for (MySqlParser.JoinPartContext joinPartContext : joinPartContexts) {
            sqlBuilder.append(visit(joinPartContext));
        }
        return sqlBuilder.toString();
    }

    @Override
    public String visitSubqueryTableItem(MySqlParser.SubqueryTableItemContext ctx) {
        MySqlParser.SelectStatementContext selectStatement = ctx.selectStatement();
        MySqlParser.UidContext uid = ctx.uid();
        if (selectStatement == null || uid == null) {
            return sqlSyntaxError();
        }
        SqlBuilder sqlBuilder = new SqlBuilder();
        String tableAlias = uid.getText();
        if (!this.ruleContextWrapper.hasRuntimeMasterTable()) {
            // 设置虚拟主表信息
            this.ruleContextWrapper.setRuntimeVirtualMasterTable(tableAlias);
        }
        // 添加运行时虚拟表信息
        RuleContextOperations.TableColumnNamesInjector tableColumnNamesInjector = this.ruleContextWrapper.addRuntimeVirtualTable(tableAlias);
        // 添加运行时虚拟表规则
        this.ruleContextWrapper.addRuntimeVirtualTableRule(tableAlias);
        // 切换到运行时虚拟规则上下文
        this.ruleContextWrapper.switchToSubRuntimeVirtualRuleContext(tableAlias, tableColumnNamesInjector);
        sqlBuilder.append(visit(selectStatement));
        this.ruleContextWrapper.switchToParentRuntimeRuleContext();
        TerminalNode as = ctx.AS();
        if (as != null) {
            sqlBuilder.appendWithSpace(as);
        }
        sqlBuilder.appendWithSpace(tableAlias);
        return sqlBuilder.toString();
    }

    @Override
    public String visitQueryExpression(MySqlParser.QueryExpressionContext ctx) {
        TerminalNode lrBracket = ctx.LR_BRACKET();
        MySqlParser.QuerySpecificationContext querySpecification = ctx.querySpecification();
        TerminalNode rrBracket = ctx.RR_BRACKET();
        if (lrBracket == null || querySpecification == null || rrBracket == null) {
            return sqlSyntaxError();
        }
        SqlBuilder sqlBuilder = new SqlBuilder();
        sqlBuilder.appendWithSpace(lrBracket);
        sqlBuilder.append(visit(querySpecification));
        sqlBuilder.appendWithSpace(rrBracket);
        return sqlBuilder.toString();
    }

    @Override
    public String visitAtomTableItem(MySqlParser.AtomTableItemContext ctx) {
        MySqlParser.TableNameContext tableName = ctx.tableName();
        if (tableName == null) {
            return sqlSyntaxError();
        }
        SqlBuilder sqlBuilder = new SqlBuilder();
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
        if (!this.ruleContextWrapper.hasRuntimeMasterTable()) {
            // 设置真实主表信息
            this.ruleContextWrapper.setRuntimeRealMasterTable(tableNameStr, tableAliasStr);
        }
        // 添加运行时真实表信息
        this.ruleContextWrapper.addRuntimeRealTable(tableNameStr, tableAliasStr);
        // 添加运行时真实表规则
        this.ruleContextWrapper.addRuntimeRealTableRule(tableNameStr, tableAliasStr);
        return sqlBuilder.toString();
    }

    @Override
    public String visitInnerJoin(MySqlParser.InnerJoinContext ctx) {
        TerminalNode inner = ctx.INNER();
        if (inner == null) {
            return sqlSyntaxError("由于sql分析的局限性, 请不要省略 'INNER' 关键字,如果你是想使用 'LEFT/RIGHT JOIN', 请使用 'AS' 给主表取个别名");
        }
        TerminalNode join = ctx.JOIN();
        MySqlParser.TableSourceItemContext tableSourceItem = ctx.tableSourceItem();
        if (join == null || tableSourceItem == null) {
            return sqlSyntaxError();
        }
        SqlBuilder sqlBuilder = new SqlBuilder();
        sqlBuilder.appendWithSpace(inner)
                .appendWithSpace(join)
                .append(visit(tableSourceItem));
        TerminalNode on = ctx.ON();
        if (on == null) {
            return sqlBuilder.toString();
        }
        this.ruleContextWrapper.switchToRuntimeOnStage();// 切换至On阶段
        sqlBuilder.appendWithSpace(on);
        this.ruleContextWrapper.addRuntimeOnColumnRule();
        MySqlParser.ExpressionContext expression = ctx.expression();
        if (expression == null) {
            return sqlSyntaxError();
        }
        this.ruleContextWrapper.addRuntimeLogicExpression(LogicOperator.AND);
        sqlBuilder.append(visit(ctx.expression()));
        return sqlBuilder.toString();
    }

    @Override
    public String visitOuterJoin(MySqlParser.OuterJoinContext ctx) {
        TerminalNode left = ctx.LEFT();
        TerminalNode right = ctx.RIGHT();
        if (left == null && right == null) {
            return unsupportedSqlNode();
        }
        if (left != null && right != null) {
            return unsupportedSqlNode();
        }
        TerminalNode join = ctx.JOIN();
        if (join == null) {
            return sqlSyntaxError();
        }
        TerminalNode on = ctx.ON();
        MySqlParser.ExpressionContext expression = ctx.expression();
        if (on == null || expression == null) {
            return sqlSyntaxError("'LEFT/RIGHT JOIN' 至少要有一个 'ON' 条件");
        }
        this.ruleContextWrapper.switchToRuntimeOnStage();// 切换至On阶段
        SqlBuilder sqlBuilder = new SqlBuilder();
        if (left != null) {
            sqlBuilder.appendWithSpace(left);
        } else {
            sqlBuilder.appendWithSpace(right);
        }
        sqlBuilder.appendWithSpace(join);
        MySqlParser.TableSourceItemContext tableSourceItem = ctx.tableSourceItem();
        if (tableSourceItem == null) {
            return sqlSyntaxError();
        }
        sqlBuilder.append(visit(tableSourceItem));
        sqlBuilder.appendWithSpace(on);
        this.ruleContextWrapper.addRuntimeOnColumnRule();
        this.ruleContextWrapper.addRuntimeLogicExpression(LogicOperator.AND);
        sqlBuilder.append(visit(ctx.expression()));
        return sqlBuilder.toString();
    }

    // 处理条件与条件之间 AND | OR
    @Override
    public String visitLogicalExpression(MySqlParser.LogicalExpressionContext ctx) {
        List<MySqlParser.ExpressionContext> expression = ctx.expression();
        MySqlParser.LogicalOperatorContext logicalOperator = ctx.logicalOperator();
        if (expression.size() != 2 || logicalOperator == null) {
            return sqlSyntaxError();
        }
        SqlBuilder sqlBuilder = new SqlBuilder();
        sqlBuilder.append(visit(expression.get(0)));
        sqlBuilder.append(visit(logicalOperator));
        sqlBuilder.append(visit(expression.get(1)));
        return sqlBuilder.toString();
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
        SqlBuilder sqlBuilder = new SqlBuilder();
        if (and != null) {
            String andStr = and.getText();
            sqlBuilder.appendWithSpace(andStr);//TODO 后面要删除  条件由归并后的 规则  重新生成
        } else {
            String orStr = or.getText();
            this.ruleContextWrapper.addRuntimeSubLogicExpression(LogicOperator.OR);
            sqlBuilder.appendWithSpace(orStr);//TODO 后面要删除  条件由归并后的 规则  重新生成
        }
        return sqlBuilder.toString();
    }

    @Override
    public String visitPredicateExpression(MySqlParser.PredicateExpressionContext ctx) {
        MySqlParser.PredicateContext predicate = ctx.predicate();
        if (predicate == null) {
            return sqlSyntaxError();
        }
        SqlBuilder sqlBuilder = new SqlBuilder();
        sqlBuilder.append(visit(predicate));
        return sqlBuilder.toString();
    }

    @Override
    public String visitIsNullPredicate(MySqlParser.IsNullPredicateContext ctx) {
        MySqlParser.PredicateContext predicate = ctx.predicate();
        TerminalNode is = ctx.IS();
        MySqlParser.NullNotnullContext nullNotnull = ctx.nullNotnull();
        if (predicate == null || is == null || nullNotnull == null) {
            return sqlSyntaxError();
        }
        SqlBuilder sqlBuilder = new SqlBuilder();
        this.ruleContextWrapper.addRuntimeIsNullPredicate();
        sqlBuilder.append(visit(predicate));
        sqlBuilder.appendWithSpace(is);
        sqlBuilder.append(visit(nullNotnull));
        return sqlBuilder.toString();
    }

    @Override
    public String visitNullNotnull(MySqlParser.NullNotnullContext ctx) {
        SqlBuilder sqlBuilder = new SqlBuilder();
        TerminalNode not = ctx.NOT();
        if (not != null) {
            sqlBuilder.appendWithSpace(not);
        }
        TerminalNode nullLiteral = ctx.NULL_LITERAL();
        if (nullLiteral == null) {
            return sqlSyntaxError();
        }
        sqlBuilder.appendWithSpace(nullLiteral);
        return sqlBuilder.toString();
    }

    @Override
    public String visitBinaryComparasionPredicate(MySqlParser.BinaryComparasionPredicateContext ctx) {
        List<MySqlParser.PredicateContext> predicate = ctx.predicate();
        MySqlParser.ComparisonOperatorContext comparisonOperator = ctx.comparisonOperator();
        if (predicate.size() != 2 || comparisonOperator == null) {
            return sqlSyntaxError();
        }
        SqlBuilder sqlBuilder = new SqlBuilder();
        this.ruleContextWrapper.addRuntimeBinaryComparisonPredicate();
        sqlBuilder.append(visit(predicate.get(0)));//TODO 后面要删除  条件由归并后的 规则  重新生成
        String symbol = comparisonOperator.getText();
        this.ruleContextWrapper.setRuntimePredicateExpressionComparisonType(ComparisonOperator.parseComparison(symbol));
        sqlBuilder.appendWithSpace(symbol);//TODO 后面要删除  条件由归并后的 规则  重新生成
        sqlBuilder.append(visit(predicate.get(1)));//TODO 后面要删除  条件由归并后的 规则  重新生成
        return sqlBuilder.toString();
    }

    @Override
    public String visitFullColumnNameExpressionAtom(MySqlParser.FullColumnNameExpressionAtomContext ctx) {
        MySqlParser.FullColumnNameContext fullColumnName = ctx.fullColumnName();
        if (fullColumnName == null) {
            return sqlSyntaxError();
        }
        SqlBuilder sqlBuilder = new SqlBuilder();
        if (ruleContextWrapper.runtimePredicateExpressionStage()) {
            MySqlParser.UidContext uid = fullColumnName.uid();
            if (uid == null) {
                return sqlSyntaxError();
            }
            MySqlParser.DottedIdContext dottedId = fullColumnName.dottedId(0);
            String tableAlias = null;
            String columnName;
            if (dottedId == null) {// 只有字段名没有表别名
                columnName = uid.getText();
            } else {
                tableAlias = uid.getText();
                columnName = dottedId.getText().substring(1);
            }
            if (!ruleContextWrapper.runtimeBinaryComparisonPredicateHasMasterPredicate()) {
                this.ruleContextWrapper.setRuntimePredicateExpressionColumn(tableAlias, columnName);
            } else if (!ruleContextWrapper.runtimeBinaryComparisonPredicateHasSlavePredicate()) {
                this.ruleContextWrapper.setRuntimePredicateExpressionColumnTypeValue(tableAlias, columnName);
            } else {
                return sqlSyntaxError();
            }
        }
        sqlBuilder.appendWithSpace(fullColumnName);//TODO 后面要删除  条件由归并后的 规则  重新生成
        return sqlBuilder.toString();
    }

    @Override
    public String visitLikePredicate(MySqlParser.LikePredicateContext ctx) {
        List<MySqlParser.PredicateContext> predicate = ctx.predicate();
        TerminalNode like = ctx.LIKE();
        if (predicate.size() != 2 || like == null) {
            return sqlSyntaxError();
        }
        SqlBuilder sqlBuilder = new SqlBuilder();
        this.ruleContextWrapper.addRuntimePredicateExpression();
        sqlBuilder.append(visit(predicate.get(0)));
        sqlBuilder.appendWithSpace(like);
        sqlBuilder.append(visit(predicate.get(1)));
        return sqlBuilder.toString();
    }

    @Override
    public String visitBetweenPredicate(MySqlParser.BetweenPredicateContext ctx) {
        TerminalNode between = ctx.BETWEEN();
        List<MySqlParser.PredicateContext> predicate = ctx.predicate();
        TerminalNode and = ctx.AND();
        if (between == null || predicate.size() != 3 || and == null) {
            return sqlSyntaxError();
        }
        SqlBuilder sqlBuilder = new SqlBuilder();
        this.ruleContextWrapper.addRuntimePredicateExpression();
        sqlBuilder.append(visit(predicate.get(0)));
        sqlBuilder.appendWithSpace(between);
        sqlBuilder.append(visit(predicate.get(1)));
        sqlBuilder.appendWithSpace(and);
        sqlBuilder.append(visit(predicate.get(2)));
        return sqlBuilder.toString();
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
        SqlBuilder sqlBuilder = new SqlBuilder();
        sqlBuilder.appendWithSpace(predicate);
        TerminalNode not = ctx.NOT();
        if (not != null) {
            sqlBuilder.appendWithSpace(not);
        }
        sqlBuilder.appendWithSpace(in)
                .appendWithSpace(lrBracket);
        if (expressions != null) {
            this.ruleContextWrapper.addRuntimePredicateExpression();
            sqlBuilder.append(visit(expressions));
        }
        if (selectStatement != null) {
            this.ruleContextWrapper.switchToSubRuntimeRuleContext(ctx.hashCode() + "");//TODO 临时处理
            sqlBuilder.append(visit(selectStatement));
            this.ruleContextWrapper.switchToParentRuntimeRuleContext();
        }
        sqlBuilder.appendWithSpace(rrBracket);
        return sqlBuilder.toString();
    }

    @Override
    public String visitNestedExpressionAtom(MySqlParser.NestedExpressionAtomContext ctx) {
        TerminalNode lrBracket = ctx.LR_BRACKET();
        List<MySqlParser.ExpressionContext> expression = ctx.expression();
        TerminalNode rrBracket = ctx.RR_BRACKET();
        if (lrBracket == null || expression.size() < 1 || rrBracket == null) {
            return sqlSyntaxError();
        }
        SqlBuilder sqlBuilder = new SqlBuilder();
        if (this.ruleContextWrapper.getRuntimeLogicOperator() == LogicOperator.AND) {
            this.ruleContextWrapper.addRuntimeSubLogicExpression(LogicOperator.AND);
        }
        sqlBuilder.appendWithSpace(lrBracket);//TODO 后面要删除  条件由归并后的 规则  重新生成
        for (MySqlParser.ExpressionContext expressionContext : expression) {
            sqlBuilder.append(visit(expressionContext));
        }
        sqlBuilder.appendWithSpace(rrBracket);//TODO 后面要删除  条件由归并后的 规则  重新生成
        return sqlBuilder.toString();
    }

    @Override
    public String visitConstantExpressionAtom(MySqlParser.ConstantExpressionAtomContext ctx) {
        MySqlParser.ConstantContext constant = ctx.constant();
        if (constant == null) {
            return sqlSyntaxError();
        }
        String value = constant.getText();
        if (this.ruleContextWrapper.runtimePredicateExpressionStage()) {
            this.ruleContextWrapper.setRuntimePredicateExpressionConstantTypeValue(value);
        }
        SqlBuilder sqlBuilder = new SqlBuilder();
        sqlBuilder.appendWithSpace(value);
        return sqlBuilder.toString();
    }

    @Override
    public String visitExpressions(MySqlParser.ExpressionsContext ctx) {
        List<MySqlParser.ExpressionContext> expression = ctx.expression();
        List<TerminalNode> comma = ctx.COMMA();
        int len = comma.size();
        if (len != expression.size() - 1) {
            return sqlSyntaxError();
        }
        SqlBuilder sqlBuilder = new SqlBuilder();
        for (int i = 0; i <= len; i++) {
            sqlBuilder.append(visit(expression.get(i)));
            if (i < len) {
                sqlBuilder.append(comma.get(i));
            }
        }
        return sqlBuilder.toString();
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
        SqlBuilder sqlBuilder = new SqlBuilder();
        sqlBuilder.appendWithSpace(order)
                .appendWithSpace(by);
        for (int i = 0; i <= len; i++) {
            sqlBuilder.append(visit(orderByExpression.get(i)));
            if (i != len) {
                sqlBuilder.append(comma.get(i));
            }
        }
        return sqlBuilder.toString();
    }

    @Override
    public String visitOrderByExpression(MySqlParser.OrderByExpressionContext ctx) {
        MySqlParser.ExpressionContext expression = ctx.expression();
        if (expression == null) {
            return sqlSyntaxError();
        }
        SqlBuilder sqlBuilder = new SqlBuilder();
        sqlBuilder.append(visit(expression));
        TerminalNode asc = ctx.ASC();
        if (asc != null) {
            sqlBuilder.appendWithSpace(asc);
        }
        TerminalNode desc = ctx.DESC();
        if (desc != null) {
            sqlBuilder.appendWithSpace(desc);
        }
        return sqlBuilder.toString();
    }

    @Override
    public String visitLimitClause(MySqlParser.LimitClauseContext ctx) {
        TerminalNode limit = ctx.LIMIT();
        List<MySqlParser.LimitClauseAtomContext> limitClauseAtom = ctx.limitClauseAtom();
        int len = limitClauseAtom.size();
        if (limit == null || len < 1) {
            return sqlSyntaxError();
        }
        SqlBuilder sqlBuilder = new SqlBuilder();
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
        return sqlBuilder.toString();
    }

    private String unsupportedSqlNode() {
        throw new RuntimeException("暂不支持的节点");
    }

    private String sqlSyntaxError(String message) {
        throw new SqlSyntaxErrorException("SQL syntax error" + (message == null ? "" : ": " + message));
    }

    private String sqlSyntaxError() {
        return sqlSyntaxError(null);
    }

    @Override
    public String run() {
        return visit(this.parseTree).trim();
    }

    @Override
    public RuleStore getRuleStore() {
        return this.ruleContextWrapper.getRuntimeRuleStore();
    }

    private final static class SqlBuilder implements Appendable {

        protected final StringBuilder sql;

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
            return sql.toString();
        }
    }
}