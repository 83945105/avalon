package pub.avalonframework.sqlhelper.jdbc.core;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author baichao
 */
public class ColumnOneGroupValueMapper<V> implements GroupValueMapper<V> {

    private final Integer columnIndex;

    private final String columnName;

    private final Class<V> columnType;

    public ColumnOneGroupValueMapper(Integer columnIndex, Class<V> columnType) {
        this.columnIndex = columnIndex;
        this.columnName = null;
        this.columnType = columnType;
    }

    public ColumnOneGroupValueMapper(String columnName, Class<V> columnType) {
        this.columnIndex = null;
        this.columnName = columnName;
        this.columnType = columnType;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V mapColumn(ResultSet rs, V previousValue) throws SQLException {
        if (previousValue != null) {
            throw new IncorrectResultSizeDataAccessException(1, 2);
        }
        if (this.columnIndex != null) {
            return (V) JdbcUtils.getResultSetValue(rs, this.columnIndex, this.columnType);
        }
        if (this.columnName != null) {
            String columnName;
            ResultSetMetaData rsd = rs.getMetaData();
            int columnCount = rsd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnName = JdbcUtils.lookupColumnName(rsd, i);
                if (columnName.equals(this.columnName)) {
                    return (V) JdbcUtils.getResultSetValue(rs, i, this.columnType);
                }
            }
        }
        return null;
    }
}