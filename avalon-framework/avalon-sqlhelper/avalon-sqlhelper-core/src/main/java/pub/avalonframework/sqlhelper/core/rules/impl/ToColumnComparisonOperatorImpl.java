package pub.avalonframework.sqlhelper.core.rules.impl;

import pub.avalonframework.sqlhelper.core.data.AbstractComparisonSqlPartDatum;
import pub.avalonframework.sqlhelper.core.data.ComparisonType;
import pub.avalonframework.sqlhelper.core.data.block.AbstractDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.ColumnDataBlock;
import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.rules.ToColumnComparisonOperator;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public interface ToColumnComparisonOperatorImpl<T, S extends AbstractComparisonSqlPartDatum<S>> extends ToColumnComparisonOperator<T> {

    /**
     * get helper
     *
     * @return extends {@link Helper} object
     */
    T getHelper();

    /**
     * get clone comparison sql part datum
     *
     * @return extends {@link AbstractComparisonSqlPartDatum}
     */
    AbstractComparisonSqlPartDatum<S> getCloneComparisonSqlPartDatum();

    /**
     * Add abstract comparison sql part datum.
     *
     * @param abstractComparisonSqlPartDatum Implements {@link AbstractComparisonSqlPartDatum} object.
     */
    void addAbstractComparisonSqlPartDatum(AbstractComparisonSqlPartDatum<S> abstractComparisonSqlPartDatum);

    @Override
    default T equalTo(ColumnHelper<?> columnHelper) {
        List<ColumnDataBlock> columnDataBlocks = columnHelper.takeoutSqlPartData();
        if (columnDataBlocks == null || columnDataBlocks.size() == 0) {
            return this.getHelper();
        }
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            this.addAbstractComparisonSqlPartDatum(this.getCloneComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.EQUAL, columnDataBlock));
        }
        return this.getHelper();
    }

    @Override
    default T notEqualTo(ColumnHelper<?> columnHelper) {
        List<ColumnDataBlock> columnDataBlocks = columnHelper.takeoutSqlPartData();
        if (columnDataBlocks == null || columnDataBlocks.size() == 0) {
            return this.getHelper();
        }
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            this.addAbstractComparisonSqlPartDatum(this.getCloneComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.NOT_EQUAL, columnDataBlock));
        }
        return this.getHelper();
    }

    @Override
    default T greaterThan(ColumnHelper<?> columnHelper) {
        List<ColumnDataBlock> columnDataBlocks = columnHelper.takeoutSqlPartData();
        if (columnDataBlocks == null || columnDataBlocks.size() == 0) {
            return this.getHelper();
        }
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            this.addAbstractComparisonSqlPartDatum(this.getCloneComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.GREATER, columnDataBlock));
        }
        return this.getHelper();
    }

    @Override
    default T greaterThanAndEqualTo(ColumnHelper<?> columnHelper) {
        List<ColumnDataBlock> columnDataBlocks = columnHelper.takeoutSqlPartData();
        if (columnDataBlocks == null || columnDataBlocks.size() == 0) {
            return this.getHelper();
        }
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            this.addAbstractComparisonSqlPartDatum(this.getCloneComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.GREATER_EQUAL, columnDataBlock));
        }
        return this.getHelper();
    }

    @Override
    default T lessThan(ColumnHelper<?> columnHelper) {
        List<ColumnDataBlock> columnDataBlocks = columnHelper.takeoutSqlPartData();
        if (columnDataBlocks == null || columnDataBlocks.size() == 0) {
            return this.getHelper();
        }
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            this.addAbstractComparisonSqlPartDatum(this.getCloneComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.LESS, columnDataBlock));
        }
        return this.getHelper();
    }

    @Override
    default T lessThanAndEqualTo(ColumnHelper<?> columnHelper) {
        List<ColumnDataBlock> columnDataBlocks = columnHelper.takeoutSqlPartData();
        if (columnDataBlocks == null || columnDataBlocks.size() == 0) {
            return this.getHelper();
        }
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            this.addAbstractComparisonSqlPartDatum(this.getCloneComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.LESS_EQUAL, columnDataBlock));
        }
        return this.getHelper();
    }

    @Override
    default T between(ColumnHelper<?> columnHelper, ColumnHelper<?> secondColumnHelper) {
        List<ColumnDataBlock> columnDataBlocks = columnHelper.takeoutSqlPartData();
        List<ColumnDataBlock> secondColumnDataBlocks = secondColumnHelper.takeoutSqlPartData();
        if (columnDataBlocks == null || columnDataBlocks.size() == 0 || secondColumnDataBlocks == null || secondColumnDataBlocks.size() == 0) {
            return this.getHelper();
        }
        if (columnDataBlocks.size() != secondColumnDataBlocks.size()) {
            ExceptionUtils.columnDataBlocksSizeNotEqualException();
        }
        int size = columnDataBlocks.size();
        for (int i = 0; i < size; i++) {
            this.addAbstractComparisonSqlPartDatum(this.getCloneComparisonSqlPartDatum().setTargetPairSqlPartDatum(ComparisonType.BETWEEN, columnDataBlocks.get(i), secondColumnDataBlocks.get(i)));
        }
        return this.getHelper();
    }

    @Override
    default T like(ColumnHelper<?> columnHelper) {
        List<ColumnDataBlock> columnDataBlocks = columnHelper.takeoutSqlPartData();
        if (columnDataBlocks == null || columnDataBlocks.size() == 0) {
            return this.getHelper();
        }
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            this.addAbstractComparisonSqlPartDatum(this.getCloneComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.LIKE, columnDataBlock));
        }
        return this.getHelper();
    }

    @Override
    default T in(ColumnHelper<?>... columnHelpers) {
        List<AbstractDataBlock> dataBlocks = new ArrayList<>(columnHelpers.length * 2);
        for (ColumnHelper<?> columnHelper : columnHelpers) {
            List<ColumnDataBlock> columnDataBlocks = columnHelper.takeoutSqlPartData();
            if (columnDataBlocks == null || columnDataBlocks.size() == 0) {
                continue;
            }
            dataBlocks.addAll(columnDataBlocks);
        }
        this.addAbstractComparisonSqlPartDatum(this.getCloneComparisonSqlPartDatum().setTargetMultiSqlPartDatum(ComparisonType.IN, dataBlocks));
        return this.getHelper();
    }

    @Override
    default T notIn(ColumnHelper<?>... columnHelpers) {
        List<AbstractDataBlock> dataBlocks = new ArrayList<>(columnHelpers.length * 2);
        for (ColumnHelper<?> columnHelper : columnHelpers) {
            List<ColumnDataBlock> columnDataBlocks = columnHelper.takeoutSqlPartData();
            if (columnDataBlocks == null || columnDataBlocks.size() == 0) {
                continue;
            }
            dataBlocks.addAll(columnDataBlocks);
        }
        this.addAbstractComparisonSqlPartDatum(this.getCloneComparisonSqlPartDatum().setTargetMultiSqlPartDatum(ComparisonType.NOT_IN, dataBlocks));
        return this.getHelper();
    }
}