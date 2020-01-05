package pub.avalonframework.database.mysql.table.column;

import pub.avalonframework.database.table.column.ColumnIndexType;

/**
 * The mysql index type.
 *
 * @author baichao
 */
public enum MysqlColumnIndexType implements ColumnIndexType {
    /**
     * The normal index.
     */
    Normal,
    /**
     * The unique index.
     */
    Unique
}