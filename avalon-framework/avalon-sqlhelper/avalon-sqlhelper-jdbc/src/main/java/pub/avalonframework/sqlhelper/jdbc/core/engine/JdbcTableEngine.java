package pub.avalonframework.sqlhelper.jdbc.core.engine;

import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.engine.AbstractTableEngine;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.sqlbuilder.TableSqlBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.TableSqlBuilderResult;
import pub.avalonframework.sqlhelper.jdbc.core.JdbcTemplate;

/**
 * @author baichao
 */
public final class JdbcTableEngine<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractTableEngine<T, TC, TO, TW, TG, TH, TS> {

    private JdbcTemplate jdbcTemplate;

    public JdbcTableEngine(JdbcTemplate jdbcTemplate, Class<T> tableHelperClass) {
        super(jdbcTemplate.getDatabaseType(), tableHelperClass);
    }

    public JdbcTableEngine(JdbcTemplate jdbcTemplate, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        super(jdbcTemplate.getDatabaseType(), tableHelperClass, configuration);
    }

    public JdbcTableEngine(JdbcTemplate jdbcTemplate, String tableName, Class<T> tableHelperClass) {
        super(jdbcTemplate.getDatabaseType(), tableName, tableHelperClass);
    }

    public JdbcTableEngine(JdbcTemplate jdbcTemplate, String tableName, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        super(jdbcTemplate.getDatabaseType(), tableName, tableHelperClass, configuration);
    }

    public JdbcTableEngine(JdbcTemplate jdbcTemplate, Class<T> tableHelperClass, String tableAlias) {
        super(jdbcTemplate.getDatabaseType(), tableHelperClass, tableAlias);
    }

    public JdbcTableEngine(JdbcTemplate jdbcTemplate, String tableName, Class<T> tableHelperClass, String tableAlias) {
        super(jdbcTemplate.getDatabaseType(), tableName, tableHelperClass, tableAlias);
    }

    public JdbcTableEngine(JdbcTemplate jdbcTemplate, String tableName, Class<T> tableHelperClass, String tableAlias, SqlhelperConfiguration configuration) {
        super(jdbcTemplate.getDatabaseType(), tableName, tableHelperClass, tableAlias, configuration);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}