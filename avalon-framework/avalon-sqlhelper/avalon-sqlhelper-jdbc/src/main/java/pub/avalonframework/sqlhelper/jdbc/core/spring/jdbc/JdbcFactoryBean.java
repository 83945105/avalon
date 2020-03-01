package pub.avalonframework.sqlhelper.jdbc.core.spring.jdbc;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.jdbc.core.JdbcTemplate;
import pub.avalonframework.sqlhelper.jdbc.core.JdbcHelper;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Type;

/**
 * @author baichao
 */
public class JdbcFactoryBean<T extends BaseJdbc<?, ?, ?, ?, ?, ?, ?, ?>> implements FactoryBean<T> {

    private Class<T> jdbcInterface;

    private JdbcHelper jdbcHelper;

    public JdbcFactoryBean() {
    }

    public JdbcFactoryBean(Class<T> jdbcInterface) {
        this.jdbcInterface = jdbcInterface;
    }

    @Override
    public T getObject() throws Exception {
        return createJdbcImpl();
    }

    @SuppressWarnings("unchecked")
    private T createJdbcImpl() throws IllegalAccessException, InstantiationException {
        BaseJdbc<?, ?, ?, ?, ?, ?, ?, ?> baseJdbc = jdbcInterface.newInstance();
        baseJdbc.setJdbcHelper(jdbcHelper);
        Type type = jdbcInterface.getGenericSuperclass();
        if (type instanceof ParameterizedTypeImpl) {
            Type[] actualTypeArguments = ((ParameterizedTypeImpl) type).getActualTypeArguments();
            baseJdbc.setBeanType((Class) actualTypeArguments[0]);
            baseJdbc.setTableHelperClass((Class) actualTypeArguments[1]);
        }
        return (T) baseJdbc;
    }

    @Override
    public Class<?> getObjectType() {
        return jdbcInterface;
    }

    public Class<T> getJdbcInterface() {
        return jdbcInterface;
    }

    public void setJdbcInterface(Class<T> jdbcInterface) {
        this.jdbcInterface = jdbcInterface;
    }

    public JdbcHelper getJdbcHelper() {
        return jdbcHelper;
    }

    public void setJdbcHelper(JdbcHelper jdbcHelper) {
        this.jdbcHelper = jdbcHelper;
    }
}