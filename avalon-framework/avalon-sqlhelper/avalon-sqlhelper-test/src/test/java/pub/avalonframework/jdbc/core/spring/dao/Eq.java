package pub.avalonframework.jdbc.core.spring.dao;

import javax.persistence.Table;
import java.lang.annotation.*;

/**
 * @author baichao
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Eq {

    Table table() default @Table();

    boolean required() default false;
}