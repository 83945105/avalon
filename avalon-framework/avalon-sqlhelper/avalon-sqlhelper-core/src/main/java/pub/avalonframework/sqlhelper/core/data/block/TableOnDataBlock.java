package pub.avalonframework.sqlhelper.core.data.block;

import pub.avalonframework.sqlhelper.core.helper.TableAliasInconsistentException;
import pub.avalonframework.sqlhelper.core.helper.TableAliasNullException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class TableOnDataBlock {

    private String tableAlias;

    private List<ComparisonDataBlockLinker> comparisonDataBlockLinkers;

    public TableOnDataBlock(String tableAlias, List<ComparisonDataBlockLinker> comparisonDataBlockLinkers) {
        if (tableAlias == null) {
            throw new TableAliasNullException();
        }
        this.tableAlias = tableAlias;
        this.comparisonDataBlockLinkers = comparisonDataBlockLinkers;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<ComparisonDataBlockLinker> getComparisonDataBlockLinkers() {
        return comparisonDataBlockLinkers;
    }

    public void merge(TableOnDataBlock tableOnDataBlock) {
        if (tableOnDataBlock == null) {
            return;
        }
        if (!this.getTableAlias().equals(tableOnDataBlock.getTableAlias())) {
            throw new TableAliasInconsistentException();
        }
        this.addAllComparisonDataBlockLinkers(tableOnDataBlock.getComparisonDataBlockLinkers());
    }

    public void addAllComparisonDataBlockLinkers(List<ComparisonDataBlockLinker> comparisonDataBlockLinkers) {
        if (comparisonDataBlockLinkers == null || comparisonDataBlockLinkers.size() == 0) {
            return;
        }
        if (this.comparisonDataBlockLinkers == null) {
            this.comparisonDataBlockLinkers = new ArrayList<>(comparisonDataBlockLinkers.size());
        }
        this.comparisonDataBlockLinkers.addAll(comparisonDataBlockLinkers);
    }
}