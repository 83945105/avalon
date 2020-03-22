package pub.avalonframework.database.table.column;

/**
 * The column.
 *
 * @author baichao
 */
public interface Column {

    /**
     * Get column name.
     *
     * @return The column name.
     */
    String getName();

    /**
     * Is primary key.
     *
     * @return Is primary key.
     */
    boolean isPrimaryKey();

    /**
     * Is auto increment.
     *
     * @return Is auto increment.
     */
    boolean isAutoIncrement();

    /**
     * Get auto increment start value.
     *
     * @return The auto increment start value.
     */
    Integer getAutoIncrementStartValue();

    /**
     * Get unsigned.
     *
     * @return The unsigned.
     */
    Boolean getUnsigned();

    /**
     * Get zerofill.
     *
     * @return The zerofill.
     */
    Boolean getZerofill();

    /**
     * Get column type.
     *
     * @return The column type.
     */
    ColumnType getColumnType();

    /**
     * Get column length.
     *
     * @return The column length.
     */
    Integer getLength();

    /**
     * Get column scale.
     *
     * @return The column scale.
     */
    Integer getScale();

    /**
     * Can it be null.
     *
     * @return true | false
     */
    boolean isNullable();

    /**
     * Get default.
     *
     * @return The default.
     */
    String getDefault();

    /**
     * Get comment.
     *
     * @return The comment.
     */
    String getComment();

    /**
     * Get index.
     *
     * @return The index.
     */
    ColumnIndex getIndex();
}