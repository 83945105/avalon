package pub.avalonframework.sqlhelper.core.yaml.config;

import pub.avalonframework.core.yaml.config.YamlConfiguration;
import pub.avalonframework.database.DatabaseType;

/**
 * @author baichao
 */
public class YamlSqlhelperConfiguration implements YamlConfiguration {

    private DatabaseType databaseType;

    private YamlSqlPrintConfiguration sqlPrint;

    private YamlSqlBuilderConfiguration sqlBuilder;

    public DatabaseType getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(DatabaseType databaseType) {
        this.databaseType = databaseType;
    }

    public YamlSqlPrintConfiguration getSqlPrint() {
        return sqlPrint;
    }

    public void setSqlPrint(YamlSqlPrintConfiguration sqlPrint) {
        this.sqlPrint = sqlPrint;
    }

    public YamlSqlBuilderConfiguration getSqlBuilder() {
        return sqlBuilder;
    }

    public void setSqlBuilder(YamlSqlBuilderConfiguration sqlBuilder) {
        this.sqlBuilder = sqlBuilder;
    }
}