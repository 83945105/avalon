package pub.avalonframework.sqlhelper.jdbc.core.engine;

import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.builder.InsertColumnBuilder;
import pub.avalonframework.sqlhelper.core.data.block.TableColumnDataBlock;
import pub.avalonframework.sqlhelper.core.engine.AbstractInsertEngine;
import pub.avalonframework.sqlhelper.core.expression.lambda.ColumnLambdaCallable;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.jdbc.core.JdbcHelper;

import java.util.Collection;

/**
 * @author baichao
 */
public final class JdbcInsertEngine<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractInsertEngine<T, TC, TO, TW, TG, TH, TS> {

    private JdbcHelper jdbcHelper;

    public JdbcInsertEngine(JdbcHelper jdbcHelper, Class<T> tableHelperClass) {
        super(jdbcHelper.getDatabaseType(), tableHelperClass);
    }

    public JdbcInsertEngine(JdbcHelper jdbcHelper, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        super(jdbcHelper.getDatabaseType(), tableHelperClass, configuration);
    }

    public JdbcInsertEngine(JdbcHelper jdbcHelper, String tableName, Class<T> tableHelperClass) {
        super(jdbcHelper.getDatabaseType(), tableName, tableHelperClass);
    }

    public JdbcInsertEngine(JdbcHelper jdbcHelper, String tableName, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        super(jdbcHelper.getDatabaseType(), tableName, tableHelperClass, configuration);
    }

    public JdbcInsertEngine(JdbcHelper jdbcHelper, Class<T> tableHelperClass, String tableAlias) {
        super(jdbcHelper.getDatabaseType(), tableHelperClass, tableAlias);
    }

    public JdbcInsertEngine(JdbcHelper jdbcHelper, String tableName, Class<T> tableHelperClass, String tableAlias) {
        super(jdbcHelper.getDatabaseType(), tableName, tableHelperClass, tableAlias);
    }

    public JdbcInsertEngine(JdbcHelper jdbcHelper, String tableName, Class<T> tableHelperClass, String tableAlias, SqlhelperConfiguration configuration) {
        super(jdbcHelper.getDatabaseType(), tableName, tableHelperClass, tableAlias, configuration);
    }

    public JdbcHelper getJdbcHelper() {
        return jdbcHelper;
    }

    @Override
    public JdbcInsertEngine<T, TC, TO, TW, TG, TH, TS> addInsertTableColumnDataBlock(TableColumnDataBlock tableColumnDataBlock) {
        super.addInsertTableColumnDataBlock(tableColumnDataBlock);
        return this;
    }

    @Override
    public JdbcInsertEngine<T, TC, TO, TW, TG, TH, TS> setConfiguration(SqlhelperConfiguration configuration) {
        super.setConfiguration(configuration);
        return this;
    }

    @Override
    public JdbcInsertEngine<T, TC, TO, TW, TG, TH, TS> insert(ColumnHelper<?>... columnHelpers) {
        super.insert(columnHelpers);
        return this;
    }

    @Override
    public JdbcInsertEngine<T, TC, TO, TW, TG, TH, TS> insert(ColumnLambdaCallable<TC> columnLambdaCallable) {
        super.insert(columnLambdaCallable);
        return this;
    }

    @Override
    public <FC extends ColumnHelper<FC>> JdbcInsertEngine<T, TC, TO, TW, TG, TH, TS> buildInsertColumnExpression(InsertColumnBuilder<FC> insertColumnBuilder) {
        super.buildInsertColumnExpression(insertColumnBuilder);
        return this;
    }

    public int execute(Object javaBean, boolean selective) {
        return selective ? this.jdbcHelper.insertJavaBeanSelective(javaBean, this) : this.jdbcHelper.insertJavaBean(javaBean, this);
    }

    public int execute(Object javaBean) {
        return execute(javaBean, false);
    }

    public int executeBatch(Collection<?> javaBeans) {
        return this.jdbcHelper.batchInsertJavaBeans(javaBeans, this);
    }
}