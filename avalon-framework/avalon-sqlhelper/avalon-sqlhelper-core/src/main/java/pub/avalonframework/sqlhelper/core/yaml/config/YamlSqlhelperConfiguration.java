package pub.avalonframework.sqlhelper.core.yaml.config;

import pub.avalonframework.core.yaml.config.YamlConfiguration;
import pub.avalonframework.database.DatabaseType;

/**
 * @author baichao
 */
public class YamlSqlhelperConfiguration implements YamlConfiguration {

    private DatabaseType databaseType;

    private YamlPrintConfiguration print;

    private YamlSqlBuilderConfiguration sqlBuilder;

    private YamlJdbcConfiguration jdbc;

    public DatabaseType getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(DatabaseType databaseType) {
        this.databaseType = databaseType;
    }

    public YamlPrintConfiguration getPrint() {
        return print;
    }

    public void setPrint(YamlPrintConfiguration print) {
        this.print = print;
    }

    public YamlSqlBuilderConfiguration getSqlBuilder() {
        return sqlBuilder;
    }

    public void setSqlBuilder(YamlSqlBuilderConfiguration sqlBuilder) {
        this.sqlBuilder = sqlBuilder;
    }

    public YamlJdbcConfiguration getJdbc() {
        return jdbc;
    }

    public void setJdbc(YamlJdbcConfiguration jdbc) {
        this.jdbc = jdbc;
    }
}