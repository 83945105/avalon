package pub.avalonframework.sqlhelper.jdbc.core;

import org.springframework.jdbc.support.JdbcUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public class ColumnGroupValueMapper<V> implements GroupValueMapper<List<V>> {

    private final Integer columnIndex;

    private final String columnName;

    private final Class<V> columnType;

    public ColumnGroupValueMapper(Integer columnIndex, Class<V> columnType) {
        this.columnIndex = columnIndex;
        this.columnName = null;
        this.columnType = columnType;
    }

    public ColumnGroupValueMapper(String columnName, Class<V> columnType) {
        this.columnIndex = null;
        this.columnName = columnName;
        this.columnType = columnType;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<V> mapColumn(ResultSet rs, List<V> previousValue) throws SQLException {
        if (previousValue == null) {
            previousValue = new ArrayList<>();
        }
        if (this.columnIndex != null) {
            previousValue.add((V) JdbcUtils.getResultSetValue(rs, this.columnIndex, this.columnType));
            return previousValue;
        }
        if (this.columnName != null) {
            String columnName;
            ResultSetMetaData rsd = rs.getMetaData();
            int columnCount = rsd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnName = JdbcUtils.lookupColumnName(rsd, i);
                if (columnName.equals(this.columnName)) {
                    previousValue.add((V) JdbcUtils.getResultSetValue(rs, i, this.columnType));
                    return previousValue;
                }
            }
        }
        return previousValue;
    }
}