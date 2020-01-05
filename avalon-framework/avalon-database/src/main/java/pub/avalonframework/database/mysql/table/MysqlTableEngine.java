package pub.avalonframework.database.mysql.table;

import pub.avalonframework.database.table.TableEngine;

/**
 * The mysql table engine.
 *
 * @author baichao
 */
public enum MysqlTableEngine implements TableEngine {
    /**
     *
     */
    ARCHIVE,
    BLACKHOLE,
    CSV,
    InnoDB,
    MEMORY,
    MRG_MYISAM,
    MyISAM,
    PERFORMANCE_SCHEMA
}