package pub.avalonframework.web.utils;

import pub.avalonframework.web.spring.http.response.exception.impl.ErrorMessageException;
import pub.avalonframework.web.spring.http.response.exception.impl.FailMessageException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author baichao
 */
public class ValidationUtilsTest {

    static class User {

        @NotBlank(message = "用户名不能为空")
        private String name;

        @NotNull(message = "姓名不能为空")
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

    @Test
    void nonNullCheck() {
        Object value1 = null;
        Assertions.assertThrows(ErrorMessageException.class, () ->
                ValidationUtils.nonNullCheck(value1));
        String value2 = "";
        Assertions.assertThrows(ErrorMessageException.class, () ->
                ValidationUtils.nonNullCheck(value2));
        String value3 = " ";
        Assertions.assertThrows(ErrorMessageException.class, () ->
                ValidationUtils.nonNullCheck(value3));
        List<?> value4 = new ArrayList<>(0);
        Assertions.assertThrows(ErrorMessageException.class, () ->
                ValidationUtils.nonNullCheck(value4));
        Map<?, ?> value5 = new HashMap<>(0);
        Assertions.assertThrows(ErrorMessageException.class, () ->
                ValidationUtils.nonNullCheck(value5));
        Object[] value6 = new Object[0];
        // 注意, 由于不定参特性, 只有一个数组参数时要这么传
        Assertions.assertThrows(ErrorMessageException.class, () ->
                ValidationUtils.nonNullCheck(new Object[]{value6}));

        Object value7 = new User();
        Assertions.assertDoesNotThrow(() ->
                ValidationUtils.nonNullCheck(value7));
        String value8 = "1";
        Assertions.assertDoesNotThrow(() ->
                ValidationUtils.nonNullCheck(value8));
        String value9 = " 1";
        Assertions.assertDoesNotThrow(() ->
                ValidationUtils.nonNullCheck(value9));
        List<String> value10 = new ArrayList<String>() {{
            add("1");
        }};
        Assertions.assertDoesNotThrow(() ->
                ValidationUtils.nonNullCheck(value10));
        Map<String, String> value11 = new HashMap<String, String>() {{
            put("key", "value");
        }};
        Assertions.assertDoesNotThrow(() ->
                ValidationUtils.nonNullCheck(value11));
        Object[] value12 = new Object[]{"1"};
        // 注意, 由于不定参特性, 只有一个数组参数时要这么传
        Assertions.assertDoesNotThrow(() ->
                ValidationUtils.nonNullCheck(new Object[]{value12}));
    }

    @Test
    void validateBean() {
        User user = new User();
        // 校验失败默认抛出 FailMessageException
        Assertions.assertThrows(FailMessageException.class, () ->
                ValidationUtils.FastValidator.validateBean(user));
        // 自定义校验失败异常
        Assertions.assertThrows(ErrorMessageException.class, () ->
                ValidationUtils.FastValidator.validateBean(user, errorField -> {
                    throw new ErrorMessageException(errorField.getMessage());
                }));
    }

    @Test
    void validateBeanProperty() {
        User user = new User();
        user.setName("张三");
        Assertions.assertDoesNotThrow(() ->
                ValidationUtils.FastValidator.validateBeanProperty(user, "name"));
        Assertions.assertThrows(FailMessageException.class, () ->
                ValidationUtils.FastValidator.validateBeanProperty(user, "age"));
    }

    @Test
    void validateBeanProperties() {
        User user = new User();
        user.setName("张三");
        user.setAge(18);
        Assertions.assertDoesNotThrow(() ->
                ValidationUtils.FastValidator.validateBeanProperty(user, "name"));
        Assertions.assertDoesNotThrow(() ->
                ValidationUtils.FastValidator.validateBeanProperty(user, "age"));
    }
}