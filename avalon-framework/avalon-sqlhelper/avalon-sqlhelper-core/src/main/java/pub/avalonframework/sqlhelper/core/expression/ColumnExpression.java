package pub.avalonframework.sqlhelper.core.expression;

/**
 * @author baichao
 */
public interface ColumnExpression<R> {

    /**
     * Add virtual column data block.
     *
     * @param columnValue column value
     * @param columnAlias column alias
     * @return R
     */
    R virtualColumn(Object columnValue, String columnAlias);
}