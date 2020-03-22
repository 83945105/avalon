package pub.avalonframework.database.mysql.table.column;

import pub.avalonframework.database.table.column.ColumnType;

/**
 * The mysql column type.
 *
 * @author baichao
 */
public enum MysqlColumnType implements ColumnType {
    /**
     *
     */
    tinyint("tinyint"),
    smallint("smallint"),
    mediumint("mediumint"),
    int_("int"),
    integer_("integer"),
    bigint("bigint"),
    bit("bit"),
    real("real"),
    double_("double"),
    float_("float"),
    decimal("decimal"),
    numeric("numeric"),
    char_("char"),
    varchar("varchar"),
    date("date"),
    time("time"),
    year("year"),
    timestamp("timestamp"),
    datetime("datetime"),
    tinyblob("tinyblob"),
    blob("blob"),
    mediumblob("mediumblob"),
    longblob("longblob"),
    tinytext("tinytext"),
    text("text"),
    mediumtext("mediumtext"),
    longtext("longtext"),
    enum_("enum"),
    set("set"),
    binary("binary"),
    varbinary("varbinary"),
    point("point"),
    linestring("linestring"),
    polygon("polygon"),
    geometry("geometry"),
    multipoint("multipoint"),
    multilinestring("multilinestring"),
    multipolygon("multipolygon"),
    geometrycollection("geometrycollection");

    public String value;

    MysqlColumnType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static MysqlColumnType parseMysqlColumnType(String value) {
        if(value == null) {
            return null;
        }
        for (MysqlColumnType each : MysqlColumnType.values()) {
            if(value.equals(each.value)) {
                return each;
            }
        }
        return null;
    }
}