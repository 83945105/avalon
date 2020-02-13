package pub.avalonframework.sqlhelper.core.data.block;

/**
 * @author baichao
 */
public abstract class AbstractDataBlock<T extends AbstractDataBlock<T>> implements DataBlock {

    private String templateTableName;

    private String templateTableAlias;

    private String templateColumnName;

    private String templateColumnAlias;

    private String tableName;

    private String tableAlias;

    private String columnName;

    private String columnAlias;

    public AbstractDataBlock(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        this(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, templateColumnAlias);
    }

    public AbstractDataBlock(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        this.templateTableName = templateTableName;
        this.templateTableAlias = templateTableAlias;
        this.templateColumnName = templateColumnName;
        this.templateColumnAlias = templateColumnAlias;
        this.tableName = templateTableName;
        this.tableAlias = templateTableAlias;
        this.columnName = templateColumnName;
        this.columnAlias = mappingFieldName;
    }

    public AbstractDataBlock<T> setTableName(String tableName) {
        if (tableName == null) {
            return this;
        }
        this.tableName = tableName;
        return this;
    }

    public AbstractDataBlock<T> setTableAlias(String tableAlias) {
        if (tableAlias == null) {
            return this;
        }
        this.tableAlias = tableAlias;
        return this;
    }

    public AbstractDataBlock<T> setColumnName(String columnName) {
        if (columnName == null) {
            return this;
        }
        this.columnName = columnName;
        return this;
    }

    public AbstractDataBlock<T> setColumnAlias(String columnAlias) {
        if (columnAlias == null) {
            return this;
        }
        this.columnAlias = columnAlias;
        return this;
    }

    public String getTemplateTableName() {
        return templateTableName;
    }

    public String getTemplateTableAlias() {
        return templateTableAlias;
    }

    public String getTemplateColumnName() {
        return templateColumnName;
    }

    public String getTemplateColumnAlias() {
        return templateColumnAlias;
    }

    public String getTableName() {
        return tableName;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnAlias() {
        return columnAlias;
    }
}