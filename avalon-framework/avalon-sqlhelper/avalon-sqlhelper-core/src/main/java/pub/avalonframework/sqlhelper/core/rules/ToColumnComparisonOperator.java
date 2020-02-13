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
    T equalTo(ColumnHelper<?> columnHelper);

    /**
     * Not equal to
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T notEqualTo(ColumnHelper<?> columnHelper);

    /**
     * Greater than
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T greaterThan(ColumnHelper<?> columnHelper);

    /**
     * Greater than or equal to
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T greaterThanAndEqualTo(ColumnHelper<?> columnHelper);

    /**
     * Less than
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T lessThan(ColumnHelper<?> columnHelper);

    /**
     * Less than or equal to
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T lessThanAndEqualTo(ColumnHelper<?> columnHelper);

    /**
     * Between ... and ...
     *
     * @param columnHelper       extends {@link ColumnHelper} object
     * @param secondColumnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T between(ColumnHelper<?> columnHelper, ColumnHelper<?> secondColumnHelper);

    /**
     * Like
     *
     * @param columnHelper extends {@link ColumnHelper} object
     * @return extends {@link Helper} object
     */
    T like(ColumnHelper<?> columnHelper);

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
    T notIn(ColumnHelper<?>... columnHelpers);
}