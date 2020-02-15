package pub.avalonframework.sqlhelper.jdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author baichao
 */
@FunctionalInterface
public interface GroupValueMapper<T> {

    T mapColumn(ResultSet rs, T previousValue) throws SQLException;
}