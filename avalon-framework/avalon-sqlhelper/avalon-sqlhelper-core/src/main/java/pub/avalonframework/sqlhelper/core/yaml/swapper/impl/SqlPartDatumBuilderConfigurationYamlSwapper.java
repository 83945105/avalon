package pub.avalonframework.sqlhelper.core.yaml.swapper.impl;

import pub.avalonframework.core.yaml.swapper.YamlSwapper;
import pub.avalonframework.sqlhelper.core.api.config.SqlPartDatumBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.beans.ComparisonRule;
import pub.avalonframework.sqlhelper.core.yaml.config.YamlSqlPartDatumBuilderConfiguration;

/**
 * @author baichao
 */
public final class SqlPartDatumBuilderConfigurationYamlSwapper implements YamlSwapper<YamlSqlPartDatumBuilderConfiguration, SqlPartDatumBuilderConfiguration> {

    private final static Boolean DEFAULT_SELECT_ALL_COLUMN_FOR_MAIN_TABLE = Boolean.FALSE;

    private final static Boolean DEFAULT_SELECT_ALL_COLUMN_FOR_JOIN_TABLE = Boolean.FALSE;

    private final static ComparisonRule DEFAULT_ON_COMPARISON_RULE = ComparisonRule.NULL_SKIP;

    private final static ComparisonRule DEFAULT_WHERE_COMPARISON_RULE = ComparisonRule.NULL_SKIP;

    private final static ComparisonRule DEFAULT_HAVING_COMPARISON_RULE = ComparisonRule.NULL_SKIP;

    @Override
    public YamlSqlPartDatumBuilderConfiguration swap(SqlPartDatumBuilderConfiguration data) {
        data = data == null ? new SqlPartDatumBuilderConfiguration() : data;
        YamlSqlPartDatumBuilderConfiguration configuration = new YamlSqlPartDatumBuilderConfiguration();
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
    public SqlPartDatumBuilderConfiguration swap(YamlSqlPartDatumBuilderConfiguration yamlConfiguration) {
        yamlConfiguration = yamlConfiguration == null ? new YamlSqlPartDatumBuilderConfiguration() : yamlConfiguration;
        SqlPartDatumBuilderConfiguration configuration = new SqlPartDatumBuilderConfiguration();
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
}