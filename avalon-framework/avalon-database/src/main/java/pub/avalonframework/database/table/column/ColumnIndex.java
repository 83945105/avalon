package pub.avalonframework.database.table.column;

/**
 * The column index.
 *
 * @author baichao
 */
public interface ColumnIndex {

    /**
     * Get index name.
     *
     * @return The index name.
     */
    String getName();

    /**
     * Get index type.
     *
     * @return The index type.
     */
    ColumnIndexType getType();

    /**
     * Get index method.
     *
     * @return The index method.
     */
    ColumnIndexMethod getMethod();

    /**
     * Get comment.
     *
     * @return The comment.
     */
    String getComment();
}