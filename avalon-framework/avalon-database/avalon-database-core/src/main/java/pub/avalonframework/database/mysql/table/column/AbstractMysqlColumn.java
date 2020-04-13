package pub.avalonframework.database.mysql.table.column;

import pub.avalonframework.database.table.column.Column;

/**
 * The base mysql column.
 *
 * @author baichao
 */
public abstract class AbstractMysqlColumn implements Column {

    protected String name;

    protected boolean primaryKey;

    protected boolean autoIncrement;

    protected Integer autoIncrementStartValue;

    protected Boolean unsigned;

    protected Boolean zerofill;

    protected MysqlColumnType columnType;

    protected Integer length;

    protected Integer scale;

    protected boolean nullable;

    protected String defaultValue;

    protected String comment;

    protected MysqlColumnIndex index;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    @Override
    public Integer getAutoIncrementStartValue() {
        return autoIncrementStartValue;
    }

    public void setAutoIncrementStartValue(Integer autoIncrementStartValue) {
        this.autoIncrementStartValue = autoIncrementStartValue;
    }

    @Override
    public Boolean getUnsigned() {
        return unsigned;
    }

    public void setUnsigned(Boolean unsigned) {
        this.unsigned = unsigned;
    }

    @Override
    public Boolean getZerofill() {
        return zerofill;
    }

    public void setZerofill(Boolean zerofill) {
        this.zerofill = zerofill;
    }

    @Override
    public MysqlColumnType getColumnType() {
        return columnType;
    }

    public void setColumnType(MysqlColumnType columnType) {
        this.columnType = columnType;
    }

    @Override
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    @Override
    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    @Override
    public String getDefault() {
        return defaultValue;
    }

    public void setDefault(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public MysqlColumnIndex getIndex() {
        return index;
    }

    public void setIndex(MysqlColumnIndex index) {
        this.index = index;
    }
}