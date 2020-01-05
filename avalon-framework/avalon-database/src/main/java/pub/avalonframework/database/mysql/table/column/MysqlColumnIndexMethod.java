package pub.avalonframework.database.mysql.table.column;

import pub.avalonframework.database.table.column.ColumnIndexMethod;

/**
 * The mysql column index method.
 *
 * @author baichao
 */
public enum MysqlColumnIndexMethod implements ColumnIndexMethod {
    /**
     *
     */
    BTREE,
    HASH
}