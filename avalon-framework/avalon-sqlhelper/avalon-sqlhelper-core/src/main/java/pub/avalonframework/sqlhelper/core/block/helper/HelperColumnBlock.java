package pub.avalonframework.sqlhelper.core.block.helper;

/**
 * @author baichao
 */
public interface HelperColumnBlock<R> extends HelperBlock {

    /**
     * Add virtual column data block.
     *
     * @param columnValue column value
     * @param columnAlias column alias
     * @return R
     */
    R virtualColumn(Object columnValue, String columnAlias);
}