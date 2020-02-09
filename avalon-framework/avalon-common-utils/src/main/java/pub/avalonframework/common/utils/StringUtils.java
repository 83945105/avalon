package pub.avalonframework.common.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @author baichao
 */
public final class StringUtils {

    private StringUtils() {
    }

    /**
     * Verify that the object is empty.
     * null => true
     * "" => true
     * " " => true
     * Collection size == 0 => true
     * Map size == 0 => true
     * Array length == 0 => true
     *
     * @param object The object.
     * @return empty => true | nonEmpty => false
     */
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof String) {
            int len;
            if ((len = ((String) object).length()) == 0) {
                return true;
            }
            for (int i = 0; i < len; i++) {
                if ((!Character.isWhitespace(((String) object).charAt(i)))) {
                    return false;
                }
            }
            return true;
        }
        if (object instanceof Collection) {
            return ((Collection) object).size() == 0;
        }
        if (object instanceof Map) {
            return ((Map) object).size() == 0;
        }
        if (object.getClass().isArray()) {
            return ((Object[]) object).length == 0;
        }
        return false;
    }
}