package pub.avalonframework.web.utils;

import pub.avalonframework.web.spring.http.response.exception.impl.ErrorMessageException;
import pub.avalonframework.web.spring.http.response.exception.impl.FailMessageException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

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
        Assertions.assertThrows(ErrorMessageException.class, () ->
                ValidationUtils.nonNullChecks(Collections.singletonList(value1)));

        String value2 = "";
        Assertions.assertThrows(ErrorMessageException.class, () ->
                ValidationUtils.nonNullCheck(value2));
        Assertions.assertThrows(ErrorMessageException.class, () ->
                ValidationUtils.nonNullChecks(Collections.singletonList(value2)));

        String value3 = " ";
        Assertions.assertThrows(ErrorMessageException.class, () ->
                ValidationUtils.nonNullCheck(value3));
        Assertions.assertThrows(ErrorMessageException.class, () ->
                ValidationUtils.nonNullChecks(Collections.singletonList(value3)));

        List<?> value4 = new ArrayList<>(0);
        Assertions.assertThrows(ErrorMessageException.class, () ->
                ValidationUtils.nonNullCheck(value4));
        Assertions.assertThrows(ErrorMessageException.class, () ->
                ValidationUtils.nonNullChecks(Collections.singletonList(value4)));

        Map<?, ?> value5 = new HashMap<>(0);
        Assertions.assertThrows(ErrorMessageException.class, () ->
                ValidationUtils.nonNullCheck(value5));
        Assertions.assertThrows(ErrorMessageException.class, () ->
                ValidationUtils.nonNullChecks(Collections.singletonList(value5)));

        Object[] value6 = new Object[0];
        Assertions.assertThrows(ErrorMessageException.class, () ->
                ValidationUtils.nonNullCheck(value6));
        Assertions.assertThrows(ErrorMessageException.class, () ->
                ValidationUtils.nonNullChecks(Collections.singletonList(value6)));

        Assertions.assertThrows(ErrorMessageException.class, () ->
                ValidationUtils.nonNullChecks(Collections.singletonMap("id", null)));

        Object value11 = new User();
        Assertions.assertDoesNotThrow(() ->
                ValidationUtils.nonNullCheck(value11));
        Assertions.assertDoesNotThrow(() ->
                ValidationUtils.nonNullChecks(Collections.singletonList(value11)));

        String value12 = "1";
        Assertions.assertDoesNotThrow(() ->
                ValidationUtils.nonNullCheck(value12));
        Assertions.assertDoesNotThrow(() ->
                ValidationUtils.nonNullChecks(Collections.singletonList(value12)));

        String value13 = " 1";
        Assertions.assertDoesNotThrow(() ->
                ValidationUtils.nonNullCheck(value13));
        Assertions.assertDoesNotThrow(() ->
                ValidationUtils.nonNullChecks(Collections.singletonList(value13)));

        List<String> value14 = new ArrayList<String>() {{
            add("1");
        }};
        Assertions.assertDoesNotThrow(() ->
                ValidationUtils.nonNullCheck(value14));
        Assertions.assertDoesNotThrow(() ->
                ValidationUtils.nonNullChecks(Collections.singletonList(value14)));

        Map<String, String> value15 = new HashMap<String, String>() {{
            put("key", "value");
        }};
        Assertions.assertDoesNotThrow(() ->
                ValidationUtils.nonNullCheck(value15));
        Assertions.assertDoesNotThrow(() ->
                ValidationUtils.nonNullChecks(Collections.singletonList(value15)));

        Object[] value16 = new Object[]{"1"};
        Assertions.assertDoesNotThrow(() ->
                ValidationUtils.nonNullCheck(value16));
        Assertions.assertDoesNotThrow(() ->
                ValidationUtils.nonNullChecks(Collections.singletonList(value16)));

        Assertions.assertDoesNotThrow(() ->
                ValidationUtils.nonNullChecks(Collections.singletonMap("id", 1)));
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