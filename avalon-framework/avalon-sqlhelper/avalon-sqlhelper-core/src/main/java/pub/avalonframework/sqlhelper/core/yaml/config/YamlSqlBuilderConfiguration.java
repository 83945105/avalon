package pub.avalonframework.sqlhelper.core.yaml.config;

import pub.avalonframework.core.yaml.config.YamlConfiguration;

/**
 * @author baichao
 */
public class YamlSqlBuilderConfiguration implements YamlConfiguration {

    private String mysqlSqlBuilderTemplateName;

    private YamlDataBlockBuilderConfiguration dataBlockBuilder;

    public String getMysqlSqlBuilderTemplateName() {
        return mysqlSqlBuilderTemplateName;
    }

    public void setMysqlSqlBuilderTemplateName(String mysqlSqlBuilderTemplateName) {
        this.mysqlSqlBuilderTemplateName = mysqlSqlBuilderTemplateName;
    }

    public YamlDataBlockBuilderConfiguration getDataBlockBuilder() {
        return dataBlockBuilder;
    }

    public void setDataBlockBuilder(YamlDataBlockBuilderConfiguration dataBlockBuilder) {
        this.dataBlockBuilder = dataBlockBuilder;
    }
}