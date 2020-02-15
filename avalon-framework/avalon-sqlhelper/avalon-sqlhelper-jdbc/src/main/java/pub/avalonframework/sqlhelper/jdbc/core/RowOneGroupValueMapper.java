package pub.avalonframework.sqlhelper.jdbc.core;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author baichao
 */
public class RowOneGroupValueMapper implements GroupValueMapper<Map<String, Object>> {

    @Override
    public Map<String, Object> mapColumn(ResultSet rs, Map<String, Object> previousValue) throws SQLException {
        if (previousValue != null) {
            throw new IncorrectResultSizeDataAccessException(1, 2);
        }
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        Map<String, Object> rowMap = createColumnMap(columnCount);
        for (int i = 1; i <= columnCount; i++) {
            String column = JdbcUtils.lookupColumnName(rsmd, i);
            rowMap.put(getColumnKey(column), getColumnValue(rs, i));
        }
        return rowMap;
    }

    protected Map<String, Object> createColumnMap(int columnCount) {
        return new LinkedCaseInsensitiveMap<>(columnCount);
    }

    protected String getColumnKey(String columnName) {
        return columnName;
    }

    protected Object getColumnValue(ResultSet rs, int index) throws SQLException {
        return JdbcUtils.getResultSetValue(rs, index);
    }
}
