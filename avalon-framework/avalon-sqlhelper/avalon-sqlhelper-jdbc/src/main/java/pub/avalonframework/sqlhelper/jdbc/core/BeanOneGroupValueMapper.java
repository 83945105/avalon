package pub.avalonframework.sqlhelper.jdbc.core;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author baichao
 */
public class BeanOneGroupValueMapper<V> extends BeanPropertyRowMapper<V> implements GroupValueMapper<V> {

    public BeanOneGroupValueMapper() {
    }

    public BeanOneGroupValueMapper(Class<V> mappedClass) {
        super(mappedClass);
    }

    public BeanOneGroupValueMapper(Class<V> mappedClass, boolean checkFullyPopulated) {
        super(mappedClass, checkFullyPopulated);
    }

    @Override
    public V mapColumn(ResultSet rs, V previousValue) throws SQLException {
        if (previousValue != null) {
            throw new IncorrectResultSizeDataAccessException(1, 2);
        }
        return mapRow(rs, 0);
    }
}