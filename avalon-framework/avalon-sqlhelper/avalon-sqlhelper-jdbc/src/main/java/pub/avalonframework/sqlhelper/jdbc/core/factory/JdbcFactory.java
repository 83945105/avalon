package pub.avalonframework.sqlhelper.jdbc.core.factory;

import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.jdbc.core.JdbcTemplate;
import pub.avalonframework.sqlhelper.jdbc.core.engine.JdbcSelectEngine;

/**
 * @author baichao
 */
public class JdbcFactory {

    private JdbcTemplate jdbcTemplate;

    public JdbcSelectEngineBuilder select(ColumnHelper<?> columnHelper) {
        return new JdbcSelectEngineBuilder();
    }

    public final class JdbcSelectEngineBuilder {

        public <T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
                TC extends ColumnHelper<TC>,
                TO extends OnHelper<TO>,
                TW extends WhereHelper<TW>,
                TG extends GroupHelper<TG>,
                TH extends HavingHelper<TH>,
                TS extends SortHelper<TS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> from(Class<T> table) {
            return new JdbcSelectEngine<>(JdbcFactory.this.jdbcTemplate, table);
        }
    }
}