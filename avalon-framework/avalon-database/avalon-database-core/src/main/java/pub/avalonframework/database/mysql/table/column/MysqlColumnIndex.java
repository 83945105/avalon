package pub.avalonframework.database.mysql.table.column;

import pub.avalonframework.database.table.column.ColumnIndex;

/**
 * The mysql index.
 *
 * @author baichao
 */
public class MysqlColumnIndex implements ColumnIndex {

    private String name;

    private MysqlColumnIndexType type;

    private MysqlColumnIndexMethod method;

    private String comment;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public MysqlColumnIndexType getType() {
        return type;
    }

    public void setType(MysqlColumnIndexType type) {
        this.type = type;
    }

    @Override
    public MysqlColumnIndexMethod getMethod() {
        return method;
    }

    public void setMethod(MysqlColumnIndexMethod method) {
        this.method = method;
    }

    @Override
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}