package pub.avalonframework.database.table;

import pub.avalonframework.database.table.column.Column;

import java.util.List;

/**
 * The table.
 *
 * @author baichao
 */
public interface Table {

    /**
     * Get table name.
     *
     * @return The table name.
     */
    String getName();

    /**
     * Get primary key column.
     *
     * @return The primary key column.
     */
    Column getPrimaryKeyColumn();

    /**
     * Get auto increment column.
     *
     * @return The auto increment column.
     */
    Column getAutoIncrementColumn();

    /**
     * Get columns.
     *
     * @return The columns.
     */
    List<? extends Column> getColumns();

    /**
     * Get table engine.
     *
     * @return The table engine.
     */
    TableEngine getEngine();

    /**
     * Get table character set.
     *
     * @return The table character set.
     */
    TableCharacterSet getCharacterSet();
}