package pub.avalonframework.sqlhelper.jdbc.core;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author baichao
 */
public class GroupByColumnMapperResultSetExtractor<K, V> implements ResultSetExtractor<Map<K, V>> {

    private final Integer keyIndex;

    private final String keyColumnName;

    private final Class<K> keyColumnType;

    private final GroupValueMapper<V> groupValueMapper;

    private final int groupsExpected;

    public GroupByColumnMapperResultSetExtractor(int keyIndex, Class<K> keyColumnType, GroupValueMapper<V> groupValueMapper) {
        this(keyIndex, keyColumnType, groupValueMapper, 0);
    }

    public GroupByColumnMapperResultSetExtractor(int keyIndex, Class<K> keyColumnType, GroupValueMapper<V> groupValueMapper, int groupsExpected) {
        this.keyIndex = keyIndex;
        this.keyColumnName = null;
        this.keyColumnType = keyColumnType;
        this.groupValueMapper = groupValueMapper;
        this.groupsExpected = groupsExpected;
    }

    public GroupByColumnMapperResultSetExtractor(String keyColumnName, Class<K> keyColumnType, GroupValueMapper<V> groupValueMapper) {
        this(keyColumnName, keyColumnType, groupValueMapper, 0);
    }

    public GroupByColumnMapperResultSetExtractor(String keyColumnName, Class<K> keyColumnType, GroupValueMapper<V> groupValueMapper, int groupsExpected) {
        this.keyIndex = null;
        this.keyColumnName = keyColumnName;
        this.keyColumnType = keyColumnType;
        this.groupValueMapper = groupValueMapper;
        this.groupsExpected = groupsExpected;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<K, V> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<K, V> result = this.groupsExpected > 0 ? new LinkedHashMap<>(this.groupsExpected) : new LinkedHashMap<>();
        K keyValue;
        if (this.keyIndex != null) {
            while (rs.next()) {
                keyValue = (K) JdbcUtils.getResultSetValue(rs, this.keyIndex, this.keyColumnType);
                result.put(keyValue, this.groupValueMapper.mapColumn(rs, result.get(keyValue)));
            }
            return result;
        }
        if (this.keyColumnName != null) {
            String columnName;
            while (rs.next()) {
                ResultSetMetaData rsd = rs.getMetaData();
                int columnCount = rsd.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    columnName = JdbcUtils.lookupColumnName(rsd, i);
                    if (columnName.equals(this.keyColumnName)) {
                        keyValue = (K) JdbcUtils.getResultSetValue(rs, i, this.keyColumnType);
                        result.put(keyValue, this.groupValueMapper.mapColumn(rs, result.get(keyValue)));
                        return result;
                    }
                }
            }
        }
        return result;
    }
}