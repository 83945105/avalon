package pub.avalonframework.sqlhelper.jdbc.core;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public class ColumnRowMapperResultSetExtractor<T> implements ResultSetExtractor<List<T>> {

    private final Integer columnIndex;

    private final String columnName;

    private final Class<T> columnType;

    private final int rowsExpected;

    public ColumnRowMapperResultSetExtractor(Integer columnIndex, Class<T> columnType) {
        this(columnIndex, columnType, 0);
    }

    public ColumnRowMapperResultSetExtractor(Integer columnIndex, Class<T> columnType, int rowsExpected) {
        this.columnIndex = columnIndex;
        this.columnName = null;
        this.columnType = columnType;
        this.rowsExpected = rowsExpected;
    }

    public ColumnRowMapperResultSetExtractor(String columnName, Class<T> columnType) {
        this(columnName, columnType, 0);
    }

    public ColumnRowMapperResultSetExtractor(String columnName, Class<T> columnType, int rowsExpected) {
        this.columnIndex = null;
        this.columnName = columnName;
        this.columnType = columnType;
        this.rowsExpected = rowsExpected;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<T> results = this.rowsExpected > 0 ? new ArrayList<>(this.rowsExpected) : new ArrayList<>();
        if (this.columnIndex != null) {
            while (rs.next()) {
                results.add((T) JdbcUtils.getResultSetValue(rs, this.columnIndex, this.columnType));
            }
            return results;
        }
        if (this.columnName != null) {
            String columnName;
            ResultSetMetaData rsd;
            int columnCount;
            while (rs.next()) {
                rsd = rs.getMetaData();
                columnCount = rsd.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    columnName = JdbcUtils.lookupColumnName(rsd, i);
                    if (columnName.equals(this.columnName)) {
                        results.add((T) JdbcUtils.getResultSetValue(rs, i, this.columnType));
                        break;
                    }
                }
            }
            return results;
        }
        return results;
    }
}