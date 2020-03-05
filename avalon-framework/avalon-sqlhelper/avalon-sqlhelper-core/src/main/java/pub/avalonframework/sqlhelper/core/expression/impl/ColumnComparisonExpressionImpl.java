package pub.avalonframework.sqlhelper.core.expression.impl;

import pub.avalonframework.sqlhelper.core.data.block.*;
import pub.avalonframework.sqlhelper.core.expression.ColumnComparisonExpression;
import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;
import pub.avalonframework.sqlhelper.core.helper.Helper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public interface ColumnComparisonExpressionImpl<T, S extends AbstractComparisonDataBlock<S>> extends ColumnComparisonExpression<T> {

    /**
     * Get helper.
     *
     * @return extends {@link Helper} object
     */
    T getHelper();

    /**
     * Get clone comparison data block.
     *
     * @return extends {@link AbstractComparisonDataBlock}
     */
    AbstractComparisonDataBlock<S> getCloneComparisonDataBlock();

    /**
     * Add comparison data block.
     *
     * @param comparisonDataBlock Implements {@link AbstractComparisonDataBlock} object.
     */
    void addComparisonDataBlock(AbstractComparisonDataBlock<S> comparisonDataBlock);

    @Override
    default T eq(ColumnHelper<?> columnHelper) {
        List<ColumnDataBlock> columnDataBlocks = columnHelper.takeoutColumnDataBlocks();
        if (columnDataBlocks == null || columnDataBlocks.size() == 0) {
            return this.getHelper();
        }
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            this.addComparisonDataBlock(this.getCloneComparisonDataBlock().setTargetSingleDataBlock(ComparisonType.EQUAL, columnDataBlock));
        }
        return this.getHelper();
    }

    @Override
    default T neq(ColumnHelper<?> columnHelper) {
        List<ColumnDataBlock> columnDataBlocks = columnHelper.takeoutColumnDataBlocks();
        if (columnDataBlocks == null || columnDataBlocks.size() == 0) {
            return this.getHelper();
        }
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            this.addComparisonDataBlock(this.getCloneComparisonDataBlock().setTargetSingleDataBlock(ComparisonType.NOT_EQUAL, columnDataBlock));
        }
        return this.getHelper();
    }

    @Override
    default T gt(ColumnHelper<?> columnHelper) {
        List<ColumnDataBlock> columnDataBlocks = columnHelper.takeoutColumnDataBlocks();
        if (columnDataBlocks == null || columnDataBlocks.size() == 0) {
            return this.getHelper();
        }
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            this.addComparisonDataBlock(this.getCloneComparisonDataBlock().setTargetSingleDataBlock(ComparisonType.GREATER, columnDataBlock));
        }
        return this.getHelper();
    }

    @Override
    default T gte(ColumnHelper<?> columnHelper) {
        List<ColumnDataBlock> columnDataBlocks = columnHelper.takeoutColumnDataBlocks();
        if (columnDataBlocks == null || columnDataBlocks.size() == 0) {
            return this.getHelper();
        }
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            this.addComparisonDataBlock(this.getCloneComparisonDataBlock().setTargetSingleDataBlock(ComparisonType.GREATER_EQUAL, columnDataBlock));
        }
        return this.getHelper();
    }

    @Override
    default T lt(ColumnHelper<?> columnHelper) {
        List<ColumnDataBlock> columnDataBlocks = columnHelper.takeoutColumnDataBlocks();
        if (columnDataBlocks == null || columnDataBlocks.size() == 0) {
            return this.getHelper();
        }
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            this.addComparisonDataBlock(this.getCloneComparisonDataBlock().setTargetSingleDataBlock(ComparisonType.LESS, columnDataBlock));
        }
        return this.getHelper();
    }

    @Override
    default T lte(ColumnHelper<?> columnHelper) {
        List<ColumnDataBlock> columnDataBlocks = columnHelper.takeoutColumnDataBlocks();
        if (columnDataBlocks == null || columnDataBlocks.size() == 0) {
            return this.getHelper();
        }
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            this.addComparisonDataBlock(this.getCloneComparisonDataBlock().setTargetSingleDataBlock(ComparisonType.LESS_EQUAL, columnDataBlock));
        }
        return this.getHelper();
    }

    @Override
    default T bt(ColumnHelper<?> columnHelper, ColumnHelper<?> secondColumnHelper) {
        List<ColumnDataBlock> columnDataBlocks = columnHelper.takeoutColumnDataBlocks();
        List<ColumnDataBlock> secondColumnDataBlocks = secondColumnHelper.takeoutColumnDataBlocks();
        if (columnDataBlocks == null || columnDataBlocks.size() == 0 || secondColumnDataBlocks == null || secondColumnDataBlocks.size() == 0) {
            return this.getHelper();
        }
        if (columnDataBlocks.size() != secondColumnDataBlocks.size()) {
            throw new ColumnDataBlockInconsistentLengthException();
        }
        int size = columnDataBlocks.size();
        for (int i = 0; i < size; i++) {
            this.addComparisonDataBlock(this.getCloneComparisonDataBlock().setTargetPairDataBlock(ComparisonType.BETWEEN, columnDataBlocks.get(i), secondColumnDataBlocks.get(i)));
        }
        return this.getHelper();
    }

    @Override
    default T lk(ColumnHelper<?> columnHelper) {
        List<ColumnDataBlock> columnDataBlocks = columnHelper.takeoutColumnDataBlocks();
        if (columnDataBlocks == null || columnDataBlocks.size() == 0) {
            return this.getHelper();
        }
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            this.addComparisonDataBlock(this.getCloneComparisonDataBlock().setTargetSingleDataBlock(ComparisonType.LIKE, columnDataBlock));
        }
        return this.getHelper();
    }

    @Override
    default T in(ColumnHelper<?>... columnHelpers) {
        List<AbstractDataBlock<?>> dataBlocks = new ArrayList<>(columnHelpers.length * 2);
        for (ColumnHelper<?> columnHelper : columnHelpers) {
            List<ColumnDataBlock> columnDataBlocks = columnHelper.takeoutColumnDataBlocks();
            if (columnDataBlocks == null || columnDataBlocks.size() == 0) {
                continue;
            }
            dataBlocks.addAll(columnDataBlocks);
        }
        this.addComparisonDataBlock(this.getCloneComparisonDataBlock().setTargetMultiDataBlock(ComparisonType.IN, dataBlocks));
        return this.getHelper();
    }

    @Override
    default T nin(ColumnHelper<?>... columnHelpers) {
        List<AbstractDataBlock<?>> dataBlocks = new ArrayList<>(columnHelpers.length * 2);
        for (ColumnHelper<?> columnHelper : columnHelpers) {
            List<ColumnDataBlock> columnDataBlocks = columnHelper.takeoutColumnDataBlocks();
            if (columnDataBlocks == null || columnDataBlocks.size() == 0) {
                continue;
            }
            dataBlocks.addAll(columnDataBlocks);
        }
        this.addComparisonDataBlock(this.getCloneComparisonDataBlock().setTargetMultiDataBlock(ComparisonType.NOT_IN, dataBlocks));
        return this.getHelper();
    }
}