/*
 * Reference org.mybatis.spring.annotation.MapperScan
 */

package pub.avalonframework.sqlhelper.jdbc.core.spring.context.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author baichao
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(JdbcScannerRegistrar.class)
public @interface JdbcScan {

    String[] value() default {};

    String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};

    Class<? extends Annotation> annotationClass() default Annotation.class;

    Class<?> markerInterface() default Class.class;

    String lazyInitialization() default "";
}