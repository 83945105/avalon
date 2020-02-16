package pub.avalonframework.sqlhelper.core.api.config;

import pub.avalonframework.sqlhelper.core.beans.ComparisonRule;
import pub.avalonframework.sqlhelper.core.sqlbuilder.template.DataBlockBuilderTemplate;

/**
 * @author baichao
 */
public class DataBlockBuilderConfiguration {

    private DataBlockBuilderTemplate mysqlDataBlockBuilderTemplate;

    private Boolean selectAllColumnForMainTable;

    private Boolean selectAllColumnForJoinTable;

    private ComparisonRule whereComparisonRule;

    private ComparisonRule onComparisonRule;

    private ComparisonRule havingComparisonRule;

    public DataBlockBuilderTemplate getMysqlDataBlockBuilderTemplate() {
        return mysqlDataBlockBuilderTemplate;
    }

    public DataBlockBuilderConfiguration setMysqlDataBlockBuilderTemplate(DataBlockBuilderTemplate mysqlDataBlockBuilderTemplate) {
        this.mysqlDataBlockBuilderTemplate = mysqlDataBlockBuilderTemplate;
        return this;
    }

    public Boolean getSelectAllColumnForMainTable() {
        return selectAllColumnForMainTable;
    }

    public DataBlockBuilderConfiguration setSelectAllColumnForMainTable(Boolean selectAllColumnForMainTable) {
        this.selectAllColumnForMainTable = selectAllColumnForMainTable;
        return this;
    }

    public Boolean getSelectAllColumnForJoinTable() {
        return selectAllColumnForJoinTable;
    }

    public DataBlockBuilderConfiguration setSelectAllColumnForJoinTable(Boolean selectAllColumnForJoinTable) {
        this.selectAllColumnForJoinTable = selectAllColumnForJoinTable;
        return this;
    }

    public ComparisonRule getWhereComparisonRule() {
        return whereComparisonRule;
    }

    public DataBlockBuilderConfiguration setWhereComparisonRule(ComparisonRule whereComparisonRule) {
        this.whereComparisonRule = whereComparisonRule;
        return this;
    }

    public ComparisonRule getOnComparisonRule() {
        return onComparisonRule;
    }

    public DataBlockBuilderConfiguration setOnComparisonRule(ComparisonRule onComparisonRule) {
        this.onComparisonRule = onComparisonRule;
        return this;
    }

    public ComparisonRule getHavingComparisonRule() {
        return havingComparisonRule;
    }

    public DataBlockBuilderConfiguration setHavingComparisonRule(ComparisonRule havingComparisonRule) {
        this.havingComparisonRule = havingComparisonRule;
        return this;
    }
}