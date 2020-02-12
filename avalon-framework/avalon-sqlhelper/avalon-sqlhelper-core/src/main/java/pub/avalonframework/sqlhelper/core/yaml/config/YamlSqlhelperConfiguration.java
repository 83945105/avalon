package pub.avalonframework.sqlhelper.core.yaml.config;

import pub.avalonframework.core.yaml.config.YamlConfiguration;

/**
 * @author baichao
 */
public class YamlSqlhelperConfiguration implements YamlConfiguration {

    private YamlSqlPrintConfiguration sqlPrint;

    private YamlSqlBuilderConfiguration sqlBuilder;

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