package pub.avalonframework.sqlhelper.core.sqlbuilder;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import pub.avalonframework.sqlhelper.core.data.SqlData;
import pub.avalonframework.sqlhelper.core.exception.SqlException;

import java.lang.reflect.Method;

/**
 * @author baichao
 */
public final class CrudSqlBuilderProxyBuilder implements MethodInterceptor {

    private CrudSqlBuilder crudSqlBuilder;

    private SqlData sqlData;

    public CrudSqlBuilderProxyBuilder(SqlData sqlData) {
        this.sqlData = sqlData;
        this.crudSqlBuilder = new SupperCrudSqlBuilder(sqlData);
    }

    public CrudSqlBuilder createCrudSqlBuilder() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.crudSqlBuilder.getClass());
        enhancer.setCallback(this);
        return (CrudSqlBuilder) enhancer.create(new Class[]{SqlData.class}, new Object[]{sqlData});
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        switchSqlBuilderTemplate((SupperCrudSqlBuilder) obj);
        return methodProxy.invokeSuper(obj, args);
    }

    private void switchSqlBuilderTemplate(SupperCrudSqlBuilder supperCrudSqlBuilder) {
        switch (sqlData.getConfiguration().getDatabaseType()) {
            case MYSQL:
                supperCrudSqlBuilder.sqlBuilderTemplate = sqlData.getConfiguration().getSqlBuilder().getMysqlSqlBuilderTemplate();
                supperCrudSqlBuilder.sqlBuilderTemplate.setSqlPartBuilderTemplate(sqlData.getConfiguration().getSqlBuilder().getMysqlSqlPartBuilderTemplate());
                break;
            default:
                throw new SqlException("SqlBuilder do not support this database type temporarily.");
        }
    }
}