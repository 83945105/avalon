package pub.avalonframework.jdbc.core.spring.dao;

import com.google.common.base.CaseFormat;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;

/**
 * @author baichao
 */
@Aspect
public class SqlConditionRewriteAspect {

    @Pointcut("@annotation(pub.avalonframework.jdbc.core.spring.dao.SqlConditionRewrite)")
    public void sqlConditionRewrite() {
    }

    @Before("sqlConditionRewrite()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        LinkedHashMap<String, TableRule> rules = new LinkedHashMap<>();
        TableRule tableRule;
        ColumnRule columnRule;
        Class<?> clazz;
        Table table;
        Field[] fields;
        Table fieldTable;
        String fieldTableName;
        for (Object arg : args) {
            clazz = arg.getClass();
            if (clazz.getClassLoader() == null) {
                continue;
            }
            table = clazz.getAnnotation(Table.class);
            fields = clazz.getFields();
            for (Field field : fields) {
                fieldTable = field.getAnnotation(Table.class);
                if (fieldTable == null && table == null) {
                    // 取类名转小写+下划线拼接
                    fieldTableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, clazz.getSimpleName());
                }
            }
        }
    }


}