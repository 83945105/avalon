package pub.avalonframework.sqlhelper.core.data.block;

import pub.avalonframework.sqlhelper.core.data.ComparisonDataBlockLinker;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class TableOnDataBlock {

    private String tableAlias;

    private List<ComparisonDataBlockLinker> comparisonSqlPartDataLinkers;

    public TableOnDataBlock(String tableAlias, List<ComparisonDataBlockLinker> comparisonSqlPartDataLinkers) {
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableAlias = tableAlias;
        this.comparisonSqlPartDataLinkers = comparisonSqlPartDataLinkers;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<ComparisonDataBlockLinker> getComparisonSqlPartDataLinkers() {
        return comparisonSqlPartDataLinkers;
    }

    public void merge(TableOnDataBlock tableOnDatum) {
        if (tableOnDatum == null) {
            return;
        }
        if (!this.getTableAlias().equals(tableOnDatum.getTableAlias())) {
            ExceptionUtils.inconsistentAliasException();
        }
        this.addAllComparisonSqlPartDataLinkers(tableOnDatum.getComparisonSqlPartDataLinkers());
    }

    public void addAllComparisonSqlPartDataLinkers(List<ComparisonDataBlockLinker> comparisonSqlPartDataLinkers) {
        if (comparisonSqlPartDataLinkers == null || comparisonSqlPartDataLinkers.size() == 0) {
            return;
        }
        if (this.comparisonSqlPartDataLinkers == null) {
            this.comparisonSqlPartDataLinkers = new ArrayList<>(comparisonSqlPartDataLinkers.size());
        }
        this.comparisonSqlPartDataLinkers.addAll(comparisonSqlPartDataLinkers);
    }
}