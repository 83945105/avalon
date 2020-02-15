package pub.avalonframework.sqlhelper.jdbc.core.utils;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * @author baichao
 */
public final class JdbcTools {

    private JdbcTools() {
    }

    public static <T> T nullableSingleResult(Collection<T> results) throws IncorrectResultSizeDataAccessException {
        if (CollectionUtils.isEmpty(results)) {
            return null;
        }
        if (results.size() > 1) {
            throw new IncorrectResultSizeDataAccessException(1, results.size());
        }
        return results.iterator().next();
    }

    public static <T> T countSingleResult(Collection<T> results) throws IncorrectResultSizeDataAccessException {
        if (CollectionUtils.isEmpty(results)) {
            throw new EmptyResultDataAccessException(1);
        }
        if (results.size() > 1) {
            throw new IncorrectResultSizeDataAccessException(1, results.size());
        }
        return results.iterator().next();
    }
}