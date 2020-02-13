package pub.avalonframework.sqlhelper.core.sqlbuilder.beans;

import java.util.List;

/**
 * @author baichao
 */
public interface SqlBuilderResult {

    /**
     * Has prepared statement sql
     *
     * @return true or false
     */
    boolean hasPreparedStatementSql();

    /**
     * Has prepared statement args
     *
     * @return true or false
     */
    boolean hasPreparedStatementArgs();

    /**
     * Get prepared statement sql
     *
     * @return prepared statement sql
     */
    String getPreparedStatementSql();

    /**
     * Get prepared statement args
     *
     * @return prepared statement args
     */
    List<Object> getPreparedStatementArgs();
}