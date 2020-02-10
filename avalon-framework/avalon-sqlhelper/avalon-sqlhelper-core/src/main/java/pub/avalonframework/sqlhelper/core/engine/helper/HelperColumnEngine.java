package pub.avalonframework.sqlhelper.core.engine.helper;

/**
 * @author baichao
 */
public interface HelperColumnEngine<R> extends HelperEngine {

    /**
     * add virtual column sql data
     *
     * @param columnValue column value
     * @param columnAlias column alias
     * @return R
     */
    R virtualColumn(Object columnValue, String columnAlias);
}