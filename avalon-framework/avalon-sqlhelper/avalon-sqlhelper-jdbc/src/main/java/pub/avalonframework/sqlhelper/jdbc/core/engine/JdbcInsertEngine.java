package pub.avalonframework.sqlhelper.jdbc.core.engine;

import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.builder.InsertColumnBuilder;
import pub.avalonframework.sqlhelper.core.callback.ColumnCallback;
import pub.avalonframework.sqlhelper.core.data.block.TableColumnDataBlock;
import pub.avalonframework.sqlhelper.core.engine.AbstractInsertEngine;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.jdbc.core.JdbcTemplate;

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

    private JdbcTemplate jdbcTemplate;

    public JdbcInsertEngine(JdbcTemplate jdbcTemplate, Class<T> tableHelperClass) {
        super(jdbcTemplate.getDatabaseType(), tableHelperClass);
    }

    public JdbcInsertEngine(JdbcTemplate jdbcTemplate, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        super(jdbcTemplate.getDatabaseType(), tableHelperClass, configuration);
    }

    public JdbcInsertEngine(JdbcTemplate jdbcTemplate, String tableName, Class<T> tableHelperClass) {
        super(jdbcTemplate.getDatabaseType(), tableName, tableHelperClass);
    }

    public JdbcInsertEngine(JdbcTemplate jdbcTemplate, String tableName, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        super(jdbcTemplate.getDatabaseType(), tableName, tableHelperClass, configuration);
    }

    public JdbcInsertEngine(JdbcTemplate jdbcTemplate, Class<T> tableHelperClass, String tableAlias) {
        super(jdbcTemplate.getDatabaseType(), tableHelperClass, tableAlias);
    }

    public JdbcInsertEngine(JdbcTemplate jdbcTemplate, String tableName, Class<T> tableHelperClass, String tableAlias) {
        super(jdbcTemplate.getDatabaseType(), tableName, tableHelperClass, tableAlias);
    }

    public JdbcInsertEngine(JdbcTemplate jdbcTemplate, String tableName, Class<T> tableHelperClass, String tableAlias, SqlhelperConfiguration configuration) {
        super(jdbcTemplate.getDatabaseType(), tableName, tableHelperClass, tableAlias, configuration);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
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
    public JdbcInsertEngine<T, TC, TO, TW, TG, TH, TS> insert(ColumnCallback<TC> columnCallback) {
        super.insert(columnCallback);
        return this;
    }

    @Override
    public <FC extends ColumnHelper<FC>> JdbcInsertEngine<T, TC, TO, TW, TG, TH, TS> buildInsertColumn(InsertColumnBuilder<FC> insertColumnBuilder) {
        super.buildInsertColumn(insertColumnBuilder);
        return this;
    }

    public int execute(Object javaBean, boolean selective) {
        return selective ? this.jdbcTemplate.insertJavaBeanSelective(javaBean, this) : this.jdbcTemplate.insertJavaBean(javaBean, this);
    }

    public int execute(Object javaBean) {
        return execute(javaBean, false);
    }

    public int executeBatch(Collection<?> javaBeans) {
        return this.jdbcTemplate.batchInsertJavaBeans(javaBeans, this);
    }
}