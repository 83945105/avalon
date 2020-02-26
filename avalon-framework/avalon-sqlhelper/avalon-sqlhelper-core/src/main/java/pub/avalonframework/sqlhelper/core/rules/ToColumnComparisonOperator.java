package pub.avalonframework.sqlhelper.core.rules;

import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;
import pub.avalonframework.sqlhelper.core.helper.Helper;

/**
 * @author baichao
 */
public interface ToColumnComparisonOperator<T> {

    /**
     * Equal to
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T eq(ColumnHelper<?> columnHelper);

    /**
     * Not equal to
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T neq(ColumnHelper<?> columnHelper);

    /**
     * Greater than
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T gt(ColumnHelper<?> columnHelper);

    /**
     * Greater than or equal to
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T gte(ColumnHelper<?> columnHelper);

    /**
     * Less than
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T lt(ColumnHelper<?> columnHelper);

    /**
     * Less than or equal to
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T lte(ColumnHelper<?> columnHelper);

    /**
     * Between ... and ...
     *
     * @param columnHelper       extends {@link ColumnHelper} object
     * @param secondColumnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T bt(ColumnHelper<?> columnHelper, ColumnHelper<?> secondColumnHelper);

    /**
     * Like
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T lk(ColumnHelper<?> columnHelper);

    /**
     * In
     *
     * @param columnHelpers extends {@link ColumnHelper} objects
     * @return extends {@link Helper} object
     */
    T in(ColumnHelper<?>... columnHelpers);

    /**
     * Not in
     *
     * @param columnHelpers extends {@link ColumnHelper} objects
     * @return extends {@link Helper} object
     */
    T nin(ColumnHelper<?>... columnHelpers);
}