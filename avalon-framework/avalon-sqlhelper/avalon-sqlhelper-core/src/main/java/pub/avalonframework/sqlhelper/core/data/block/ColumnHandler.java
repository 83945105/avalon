package pub.avalonframework.sqlhelper.core.data.block;

/**
 * @author baichao
 */
@FunctionalInterface
public interface ColumnHandler {

    String apply(String columnSql);
}