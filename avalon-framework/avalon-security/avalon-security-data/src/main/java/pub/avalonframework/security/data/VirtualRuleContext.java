package pub.avalonframework.security.data;

import pub.avalonframework.security.data.sql.MysqlCacheJdbcOperations;

/**
 * @author baichao
 */
public class VirtualRuleContext extends RuleContext {

    private TableColumnNamesInjector tableColumnNamesInjector;

    public VirtualRuleContext(MysqlCacheJdbcOperations jdbcOperations, TableColumnNamesInjector tableColumnNamesInjector) {
        super(jdbcOperations);
        this.tableColumnNamesInjector = tableColumnNamesInjector;
    }

    public VirtualRuleContext(RuleContext parent, MysqlCacheJdbcOperations jdbcOperations, TableColumnNamesInjector tableColumnNamesInjector) {
        super(parent, jdbcOperations);
        this.tableColumnNamesInjector = tableColumnNamesInjector;
    }
}