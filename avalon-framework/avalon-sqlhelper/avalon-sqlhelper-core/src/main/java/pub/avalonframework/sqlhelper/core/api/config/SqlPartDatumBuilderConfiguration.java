package pub.avalonframework.sqlhelper.core.api.config;

import pub.avalonframework.sqlhelper.core.beans.ComparisonRule;

/**
 * @author baichao
 */
public class SqlPartDatumBuilderConfiguration {

    private Boolean selectAllColumnForMainTable;

    private Boolean selectAllColumnForJoinTable;

    private ComparisonRule whereComparisonRule;

    private ComparisonRule onComparisonRule;

    private ComparisonRule havingComparisonRule;

    public Boolean getSelectAllColumnForMainTable() {
        return selectAllColumnForMainTable;
    }

    public void setSelectAllColumnForMainTable(Boolean selectAllColumnForMainTable) {
        this.selectAllColumnForMainTable = selectAllColumnForMainTable;
    }

    public Boolean getSelectAllColumnForJoinTable() {
        return selectAllColumnForJoinTable;
    }

    public void setSelectAllColumnForJoinTable(Boolean selectAllColumnForJoinTable) {
        this.selectAllColumnForJoinTable = selectAllColumnForJoinTable;
    }

    public ComparisonRule getWhereComparisonRule() {
        return whereComparisonRule;
    }

    public void setWhereComparisonRule(ComparisonRule whereComparisonRule) {
        this.whereComparisonRule = whereComparisonRule;
    }

    public ComparisonRule getOnComparisonRule() {
        return onComparisonRule;
    }

    public void setOnComparisonRule(ComparisonRule onComparisonRule) {
        this.onComparisonRule = onComparisonRule;
    }

    public ComparisonRule getHavingComparisonRule() {
        return havingComparisonRule;
    }

    public void setHavingComparisonRule(ComparisonRule havingComparisonRule) {
        this.havingComparisonRule = havingComparisonRule;
    }
}