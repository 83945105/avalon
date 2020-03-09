package pub.avalonframework.common.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author baichao
 */
public class StringUtilsTest {

    @Test
    void Test_isEmpty() {
        Assertions.assertTrue(StringUtils.isEmpty(null));
        Assertions.assertTrue(StringUtils.isEmpty(""));
        Assertions.assertTrue(StringUtils.isEmpty(" "));
        Assertions.assertTrue(StringUtils.isEmpty(new ArrayList<>(0)));
        Assertions.assertTrue(StringUtils.isEmpty(new HashMap<>(0)));
        Assertions.assertTrue(StringUtils.isEmpty(new Object[0]));

        Assertions.assertFalse(StringUtils.isEmpty("1"));
        Assertions.assertFalse(StringUtils.isEmpty(" 1 "));
        Assertions.assertFalse(StringUtils.isEmpty(new ArrayList<Integer>(1) {{
            add(1);
        }}));
        Assertions.assertFalse(StringUtils.isEmpty(new HashMap<String, Object>(1) {{
            put("key", "value");
        }}));
        Assertions.assertFalse(StringUtils.isEmpty(new Object[]{"2"}));
    }
}