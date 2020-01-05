package pub.avalonframework.database.mysql.table;

import pub.avalonframework.database.mysql.table.column.MysqlColumn;
import pub.avalonframework.database.table.Table;

import java.util.List;

/**
 * The base mysql table.
 *
 * @author baichao
 */
public abstract class AbstractMysqlTable implements Table {

    protected String name;

    protected MysqlColumn primaryKeyColumn;

    protected MysqlColumn autoIncrementColumn;

    protected List<MysqlColumn> columns;

    protected MysqlTableEngine engine;

    protected MysqlTableCharacterSet characterSet;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public MysqlColumn getPrimaryKeyColumn() {
        return primaryKeyColumn;
    }

    public void setPrimaryKeyColumn(MysqlColumn primaryKeyColumn) {
        this.primaryKeyColumn = primaryKeyColumn;
    }

    @Override
    public MysqlColumn getAutoIncrementColumn() {
        return autoIncrementColumn;
    }

    public void setAutoIncrementColumn(MysqlColumn autoIncrementColumn) {
        this.autoIncrementColumn = autoIncrementColumn;
    }

    @Override
    public List<MysqlColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<MysqlColumn> columns) {
        this.columns = columns;
    }

    @Override
    public MysqlTableEngine getEngine() {
        return engine;
    }

    public void setEngine(MysqlTableEngine engine) {
        this.engine = engine;
    }

    @Override
    public MysqlTableCharacterSet getCharacterSet() {
        return characterSet;
    }

    public void setCharacterSet(MysqlTableCharacterSet characterSet) {
        this.characterSet = characterSet;
    }
}