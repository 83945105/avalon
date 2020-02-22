package pub.avalonframework.sqlhelper.jdbc.core.factory;

import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.jdbc.core.JdbcHelper;
import pub.avalonframework.sqlhelper.jdbc.core.engine.JdbcSelectEngine;

/**
 * @author baichao
 */
public final class JdbcFactory {

    private JdbcHelper jdbcHelper;

    public JdbcFactory(JdbcHelper jdbcHelper) {
        this.jdbcHelper = jdbcHelper;
    }

    public JdbcSelectEngineBuilder select(ColumnHelper<?>... columnHelpers) {
        return new JdbcSelectEngineBuilder(columnHelpers);
    }

    public <T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
            TC extends ColumnHelper<TC>,
            TO extends OnHelper<TO>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> selectFrom(Class<T> table) {
        return select().from(table);
    }

    public final class JdbcSelectEngineBuilder {

        private ColumnHelper<?>[] columnHelpers;

        public JdbcSelectEngineBuilder(ColumnHelper<?>[] columnHelpers) {
            this.columnHelpers = columnHelpers;
        }

        public <T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
                TC extends ColumnHelper<TC>,
                TO extends OnHelper<TO>,
                TW extends WhereHelper<TW>,
                TG extends GroupHelper<TG>,
                TH extends HavingHelper<TH>,
                TS extends SortHelper<TS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> from(Class<T> table) {
            return new JdbcSelectEngine<>(JdbcFactory.this.jdbcHelper, table).select(columnHelpers);
        }
    }
}