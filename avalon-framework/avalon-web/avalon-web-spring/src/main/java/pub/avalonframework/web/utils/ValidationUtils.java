package pub.avalonframework.web.utils;

import pub.avalonframework.web.spring.http.response.exception.impl.ErrorMessageException;
import pub.avalonframework.web.spring.http.response.exception.impl.FailMessageException;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author baichao
 * Bean Validation 中内置的 constraint
 * @Null 被注释的元素必须为 null
 * @NotNull 被注释的元素必须不为 null
 * @AssertTrue 被注释的元素必须为 true
 * @AssertFalse 被注释的元素必须为 false
 * @Min(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 * @Max(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 * @DecimalMin(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 * @DecimalMax(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 * @Size(max=, min=)   被注释的元素的大小必须在指定的范围内
 * @Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内
 * @Past 被注释的元素必须是一个过去的日期
 * @Future 被注释的元素必须是一个将来的日期
 * @Pattern(regex=,flag=) 被注释的元素必须符合指定的正则表达式
 * Hibernate Validator 附加的 constraint
 * @NotBlank(message =)   验证字符串非null，且长度必须大于0
 * @Email 被注释的元素必须是电子邮箱地址
 * @Length(min=,max=) 被注释的字符串的大小必须在指定的范围内
 * @NotEmpty 被注释的字符串的必须非空
 * @Range(min=,max=,message=) 被注释的元素必须在合适的范围内
 */
public final class ValidationUtils {

    private ValidationUtils() {
    }

    @FunctionalInterface
    public interface CheckRule {

        /**
         * 提供校验规则
         *
         * @param name  值名称
         * @param value 校验值
         * @return true - 校验成功 | false - 校验失败
         */
        boolean apply(String name, Object value);
    }

    @FunctionalInterface
    public interface ErrorValueHandler {

        /**
         * 接收验证失败值
         *
         * @param name  错误名称
         * @param value 错误值
         */
        void accept(String name, Object value);
    }

    private final static CheckRule DEFAULT_CHECK_RULE = (name, value) -> defaultCheck(value);

    private final static ErrorValueHandler DEFAULT_ERROR_VALUE_HANDLER = (name, value) -> {
        throw new ErrorMessageException("The" + (name == null ? "" : " " + name) + " value is empty.");
    };

    private static boolean defaultCheck(Object value) {
        if (value == null) {
            return false;
        }
        if (value instanceof String) {
            int len;
            if ((len = ((String) value).length()) == 0) {
                return false;
            }
            for (int i = 0; i < len; i++) {
                if ((!Character.isWhitespace(((String) value).charAt(i)))) {
                    return true;
                }
            }
            return false;
        }
        if (value instanceof Collection) {
            return ((Collection<?>) value).size() != 0;
        }
        if (value instanceof Map) {
            return ((Map<?, ?>) value).size() != 0;
        }
        if (value.getClass().isArray()) {
            return ((Object[]) value).length != 0;
        }
        return true;
    }

    /**
     * 非空校验
     *
     * @param value             校验对象
     * @param checkRule         校验规则
     * @param errorValueHandler 错误处理
     */
    public static void nonNullCheck(Object value, CheckRule checkRule, ErrorValueHandler errorValueHandler) {
        if (!checkRule.apply(null, value)) {
            errorValueHandler.accept(null, value);
        }
    }

    /**
     * 非空校验
     *
     * @param name              校验对象名
     * @param value             校验对象
     * @param checkRule         校验规则
     * @param errorValueHandler 错误处理
     */
    public static void nonNullCheck(String name, Object value, CheckRule checkRule, ErrorValueHandler errorValueHandler) {
        if (!checkRule.apply(name, value)) {
            errorValueHandler.accept(name, value);
        }
    }

    /**
     * 非空校验
     * null => null <p/>
     * "" => null <p/>
     * " " => null <p/>
     * Collection(0) => null <p/>
     * Map(0) => null <p/>
     * Array(0) => null <p/>
     *
     * @param value             校验对象
     * @param errorValueHandler 错误处理
     */
    public static void nonNullCheck(Object value, ErrorValueHandler errorValueHandler) {
        nonNullCheck(value, DEFAULT_CHECK_RULE, errorValueHandler);
    }

    /**
     * 非空校验
     * null => null <p/>
     * "" => null <p/>
     * " " => null <p/>
     * Collection(0) => null <p/>
     * Map(0) => null <p/>
     * Array(0) => null <p/>
     *
     * @param name              校验对象名
     * @param value             校验对象
     * @param errorValueHandler 错误处理
     */
    public static void nonNullCheck(String name, Object value, ErrorValueHandler errorValueHandler) {
        nonNullCheck(name, value, DEFAULT_CHECK_RULE, errorValueHandler);
    }

    /**
     * 非空校验
     * null => null <p/>
     * "" => null <p/>
     * " " => null <p/>
     * Collection(0) => null <p/>
     * Map(0) => null <p/>
     * Array(0) => null <p/>
     * 校验失败默认抛出 {@link ErrorMessageException}
     *
     * @param value 校验对象
     */
    public static void nonNullCheck(Object value) {
        nonNullCheck(value, DEFAULT_ERROR_VALUE_HANDLER);
    }

    /**
     * 非空校验
     * null => null <p/>
     * "" => null <p/>
     * " " => null <p/>
     * Collection(0) => null <p/>
     * Map(0) => null <p/>
     * Array(0) => null <p/>
     * 校验失败默认抛出 {@link ErrorMessageException}
     *
     * @param name  校验对象名
     * @param value 校验对象
     */
    public static void nonNullCheck(String name, Object value) {
        nonNullCheck(name, value, DEFAULT_ERROR_VALUE_HANDLER);
    }

    /**
     * 非空校验
     *
     * @param values            校验对象
     * @param checkRule         校验规则
     * @param errorValueHandler 错误处理
     */
    public static void nonNullChecks(Collection<Object> values, CheckRule checkRule, ErrorValueHandler errorValueHandler) {
        for (Object value : values) {
            nonNullCheck(value, checkRule, errorValueHandler);
        }
    }

    /**
     * 非空校验
     *
     * @param checkRule         校验规则
     * @param errorValueHandler 错误处理
     * @param values            校验对象
     */
    public static void nonNullChecks(CheckRule checkRule, ErrorValueHandler errorValueHandler, Object... values) {
        nonNullChecks(Arrays.asList(values), checkRule, errorValueHandler);
    }

    /**
     * 非空校验
     *
     * @param values            校验对象
     * @param checkRule         校验规则
     * @param errorValueHandler 错误处理
     */
    public static void nonNullChecks(Map<String, Object> values, CheckRule checkRule, ErrorValueHandler errorValueHandler) {
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            if (!checkRule.apply(entry.getKey(), entry.getValue())) {
                errorValueHandler.accept(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 非空校验 <p/>
     * null => null <p/>
     * "" => null <p/>
     * " " => null <p/>
     * Collection(0) => null <p/>
     * Map(0) => null <p/>
     * Array(0) => null <p/>
     *
     * @param values            校验对象
     * @param errorValueHandler 错误处理
     */
    public static void nonNullChecks(Collection<Object> values, ErrorValueHandler errorValueHandler) {
        nonNullChecks(values, DEFAULT_CHECK_RULE, errorValueHandler);
    }

    /**
     * 非空校验 <p/>
     * null => null <p/>
     * "" => null <p/>
     * " " => null <p/>
     * Collection(0) => null <p/>
     * Map(0) => null <p/>
     * Array(0) => null <p/>
     *
     * @param errorValueHandler 错误处理
     * @param values            校验对象
     */
    public static void nonNullChecks(ErrorValueHandler errorValueHandler, Object... values) {
        nonNullChecks(DEFAULT_CHECK_RULE, errorValueHandler, values);
    }

    /**
     * 非空校验 <p/>
     * null => null <p/>
     * "" => null <p/>
     * " " => null <p/>
     * Collection(0) => null <p/>
     * Map(0) => null <p/>
     * Array(0) => null <p/>
     *
     * @param values            校验对象
     * @param errorValueHandler 错误处理
     */
    public static void nonNullChecks(Map<String, Object> values, ErrorValueHandler errorValueHandler) {
        nonNullChecks(values, DEFAULT_CHECK_RULE, errorValueHandler);
    }

    /**
     * 非空校验 <p/>
     * null => null <p/>
     * "" => null <p/>
     * " " => null <p/>
     * Collection(0) => null <p/>
     * Map(0) => null <p/>
     * Array(0) => null <p/>
     * 校验失败默认抛出 {@link ErrorMessageException}
     *
     * @param values 校验对象
     */
    public static void nonNullChecks(Collection<Object> values) {
        nonNullChecks(values, DEFAULT_ERROR_VALUE_HANDLER);
    }

    /**
     * 非空校验 <p/>
     * null => null <p/>
     * "" => null <p/>
     * " " => null <p/>
     * Collection(0) => null <p/>
     * Map(0) => null <p/>
     * Array(0) => null <p/>
     * 校验失败默认抛出 {@link ErrorMessageException}
     *
     * @param values 校验对象
     */
    public static void nonNullChecks(Map<String, Object> values) {
        nonNullChecks(values, DEFAULT_ERROR_VALUE_HANDLER);
    }

    /**
     * 快速验证
     */
    public final static class FastValidator {

        private FastValidator() {
        }

        /**
         * 开启快速结束模式 failFast (true)
         */
        private final static Validator VALIDATOR = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

        /**
         * 校验bean
         *
         * @param bean              bean
         * @param errorFieldHandler 错误属性处理
         * @param groups            校验组
         */
        public static <T> void validateBean(T bean, ErrorFieldHandler errorFieldHandler, Class<?>... groups) {
            Set<ConstraintViolation<T>> violationSet = VALIDATOR.validate(bean, groups);
            boolean hasError = violationSet != null && violationSet.size() > 0;
            if (hasError) {
                for (ConstraintViolation<T> violation : violationSet) {
                    errorFieldHandler.accept(new ErrorField(violation.getPropertyPath().toString(), violation.getMessage()));
                }
            }
        }

        /**
         * 校验bean
         * 校验失败默认抛出 {@link FailMessageException}
         *
         * @param bean   bean
         * @param groups 校验组
         */
        public static <T> void validateBean(T bean, Class<?>... groups) {
            validateBean(bean, errorField -> {
                throw new FailMessageException(errorField.getMessage());
            }, groups);
        }

        /**
         * 校验bean的某一个属性
         *
         * @param bean              bean
         * @param propertyName      属性名称
         * @param errorFieldHandler 错误属性处理
         */
        public static <T> void validateBeanProperty(T bean, String propertyName, ErrorFieldHandler errorFieldHandler) {
            Set<ConstraintViolation<T>> violationSet = VALIDATOR.validateProperty(bean, propertyName);
            boolean hasError = violationSet != null && violationSet.size() > 0;
            if (hasError) {
                for (ConstraintViolation<T> violation : violationSet) {
                    errorFieldHandler.accept(new ErrorField(violation.getPropertyPath().toString(), violation.getMessage()));
                }
            }
        }

        /**
         * 校验bean的某一个属性
         * 校验失败默认抛出 {@link FailMessageException}
         *
         * @param bean         bean
         * @param propertyName 属性名称
         */
        public static <T> void validateBeanProperty(T bean, String propertyName) {
            validateBeanProperty(bean, propertyName, errorField -> {
                throw new FailMessageException(errorField.getMessage());
            });
        }

        /**
         * 校验bean的指定属性
         *
         * @param bean              bean
         * @param errorFieldHandler 错误属性处理
         * @param propertyNames     属性名称
         */
        public static <T> void validateBeanProperties(T bean, ErrorFieldHandler errorFieldHandler, String... propertyNames) {
            Stream.of(propertyNames).forEach(propertyName -> validateBeanProperty(bean, propertyName, errorFieldHandler));
        }

        /**
         * 校验bean的指定属性
         * 校验失败默认抛出 {@link FailMessageException}
         *
         * @param bean          bean
         * @param propertyNames 属性名称
         */
        public static <T> void validateBeanProperties(T bean, String... propertyNames) {
            Stream.of(propertyNames).forEach(propertyName -> validateBeanProperty(bean, propertyName, errorField -> {
                throw new FailMessageException(errorField.getMessage());
            }));
        }
    }

    @FunctionalInterface
    public interface ErrorFieldHandler {

        /**
         * 接收验证失败字段
         *
         * @param errorField 错误字段
         */
        void accept(ErrorField errorField);
    }

    public final static class ErrorField {

        private String fieldName;

        private String message;

        public ErrorField(String fieldName, String message) {
            this.fieldName = fieldName;
            this.message = message;
        }

        public String getFieldName() {
            return fieldName;
        }

        public String getMessage() {
            return message;
        }
    }
}