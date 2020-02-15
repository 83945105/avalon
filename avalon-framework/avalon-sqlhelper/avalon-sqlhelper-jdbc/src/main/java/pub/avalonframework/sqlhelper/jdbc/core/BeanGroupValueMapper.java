package pub.avalonframework.sqlhelper.jdbc.core;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public class BeanGroupValueMapper<V> extends BeanPropertyRowMapper<V> implements GroupValueMapper<List<V>> {

    public BeanGroupValueMapper() {
    }

    public BeanGroupValueMapper(Class<V> mappedClass) {
        super(mappedClass);
    }

    public BeanGroupValueMapper(Class<V> mappedClass, boolean checkFullyPopulated) {
        super(mappedClass, checkFullyPopulated);
    }

    @Override
    public List<V> mapColumn(ResultSet rs, List<V> previousValue) throws SQLException {
        if (previousValue == null) {
            previousValue = new ArrayList<>();
        }
        previousValue.add(mapRow(rs, 0));
        return previousValue;
    }
}