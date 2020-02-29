package pub.avalonframework.sqlhelper.core.yaml.swapper.impl;

import pub.avalonframework.core.yaml.swapper.YamlSwapper;
import pub.avalonframework.sqlhelper.core.api.config.DataBlockBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.expression.ComparisonRule;
import pub.avalonframework.sqlhelper.core.sqlbuilder.template.DataBlockBuilderTemplate;
import pub.avalonframework.sqlhelper.core.sqlbuilder.template.DefaultMySqlDataBlockBuilderTemplate;
import pub.avalonframework.sqlhelper.core.yaml.config.YamlDataBlockBuilderConfiguration;

/**
 * @author baichao
 */
public final class DataBlockBuilderConfigurationYamlSwapper implements YamlSwapper<YamlDataBlockBuilderConfiguration, DataBlockBuilderConfiguration> {

    private final static String DEFAULT_MYSQL_DATA_BLOCK_BUILDER_TEMPLATE_NAME = DefaultMySqlDataBlockBuilderTemplate.class.getName();

    private final static DataBlockBuilderTemplate DEFAULT_MYSQL_DATA_BLOCK_BUILDER_TEMPLATE = new DefaultMySqlDataBlockBuilderTemplate();

    private final static Boolean DEFAULT_SELECT_ALL_COLUMN_FOR_MAIN_TABLE = Boolean.FALSE;

    private final static Boolean DEFAULT_SELECT_ALL_COLUMN_FOR_JOIN_TABLE = Boolean.FALSE;

    private final static ComparisonRule DEFAULT_ON_COMPARISON_RULE = ComparisonRule.NULL_SKIP;

    private final static ComparisonRule DEFAULT_WHERE_COMPARISON_RULE = ComparisonRule.NULL_SKIP;

    private final static ComparisonRule DEFAULT_HAVING_COMPARISON_RULE = ComparisonRule.NULL_SKIP;

    @Override
    public YamlDataBlockBuilderConfiguration swap(DataBlockBuilderConfiguration data) {
        data = data == null ? new DataBlockBuilderConfiguration() : data;
        YamlDataBlockBuilderConfiguration configuration = new YamlDataBlockBuilderConfiguration();
        DataBlockBuilderTemplate mysqlDataBlockBuilderTemplate = data.getMysqlDataBlockBuilderTemplate();
        configuration.setMysqlDataBlockBuilderTemplateName(mysqlDataBlockBuilderTemplate == null ? DEFAULT_MYSQL_DATA_BLOCK_BUILDER_TEMPLATE_NAME : mysqlDataBlockBuilderTemplate.getClass().getName());
        Boolean selectAllColumnForMainTable = data.getSelectAllColumnForMainTable();
        configuration.setSelectAllColumnForMainTable(selectAllColumnForMainTable == null ? DEFAULT_SELECT_ALL_COLUMN_FOR_MAIN_TABLE : selectAllColumnForMainTable);
        Boolean selectAllColumnForJoinTable = data.getSelectAllColumnForJoinTable();
        configuration.setSelectAllColumnForJoinTable(selectAllColumnForJoinTable == null ? DEFAULT_SELECT_ALL_COLUMN_FOR_JOIN_TABLE : selectAllColumnForJoinTable);
        ComparisonRule onComparisonRule = data.getOnComparisonRule();
        configuration.setOnComparisonRule(onComparisonRule == null ? DEFAULT_ON_COMPARISON_RULE : onComparisonRule);
        ComparisonRule whereComparisonRule = data.getWhereComparisonRule();
        configuration.setWhereComparisonRule(whereComparisonRule == null ? DEFAULT_WHERE_COMPARISON_RULE : whereComparisonRule);
        ComparisonRule havingComparisonRule = data.getHavingComparisonRule();
        configuration.setHavingComparisonRule(havingComparisonRule == null ? DEFAULT_HAVING_COMPARISON_RULE : havingComparisonRule);
        return configuration;
    }

    @Override
    public DataBlockBuilderConfiguration swap(YamlDataBlockBuilderConfiguration yamlConfiguration) {
        yamlConfiguration = yamlConfiguration == null ? new YamlDataBlockBuilderConfiguration() : yamlConfiguration;
        DataBlockBuilderConfiguration configuration = new DataBlockBuilderConfiguration();
        String mysqlDataBlockBuilderTemplateName = yamlConfiguration.getMysqlDataBlockBuilderTemplateName();
        configuration.setMysqlDataBlockBuilderTemplate(mysqlDataBlockBuilderTemplateName == null ? DEFAULT_MYSQL_DATA_BLOCK_BUILDER_TEMPLATE : createDataBlockBuilderTemplate(mysqlDataBlockBuilderTemplateName));
        Boolean selectAllColumnForMainTable = yamlConfiguration.getSelectAllColumnForMainTable();
        configuration.setSelectAllColumnForMainTable(selectAllColumnForMainTable == null ? DEFAULT_SELECT_ALL_COLUMN_FOR_MAIN_TABLE : selectAllColumnForMainTable);
        Boolean selectAllColumnForJoinTable = yamlConfiguration.getSelectAllColumnForJoinTable();
        configuration.setSelectAllColumnForJoinTable(selectAllColumnForJoinTable == null ? DEFAULT_SELECT_ALL_COLUMN_FOR_JOIN_TABLE : selectAllColumnForJoinTable);
        ComparisonRule onComparisonRule = yamlConfiguration.getOnComparisonRule();
        configuration.setOnComparisonRule(onComparisonRule == null ? DEFAULT_ON_COMPARISON_RULE : onComparisonRule);
        ComparisonRule whereComparisonRule = yamlConfiguration.getWhereComparisonRule();
        configuration.setWhereComparisonRule(whereComparisonRule == null ? DEFAULT_WHERE_COMPARISON_RULE : whereComparisonRule);
        ComparisonRule havingComparisonRule = yamlConfiguration.getHavingComparisonRule();
        configuration.setHavingComparisonRule(havingComparisonRule == null ? DEFAULT_HAVING_COMPARISON_RULE : havingComparisonRule);
        return configuration;
    }

    private DataBlockBuilderTemplate createDataBlockBuilderTemplate(String dataBlockBuilderTemplateName) {
        try {
            return dataBlockBuilderTemplateName == null ? null : (DataBlockBuilderTemplate) Class.forName(dataBlockBuilderTemplateName).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}