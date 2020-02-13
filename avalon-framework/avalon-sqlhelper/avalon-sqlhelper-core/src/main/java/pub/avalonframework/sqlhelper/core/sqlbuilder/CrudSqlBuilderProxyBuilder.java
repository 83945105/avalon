package pub.avalonframework.sqlhelper.core.sqlbuilder;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import pub.avalonframework.sqlhelper.core.data.store.DataStore;
import pub.avalonframework.sqlhelper.core.exception.SqlException;

import java.lang.reflect.Method;

/**
 * @author baichao
 */
public final class CrudSqlBuilderProxyBuilder implements MethodInterceptor {

    private CrudSqlBuilder crudSqlBuilder;

    private DataStore dataStore;

    public CrudSqlBuilderProxyBuilder(DataStore dataStore) {
        this.dataStore = dataStore;
        this.crudSqlBuilder = new SupperCrudSqlBuilder(dataStore);
    }

    public CrudSqlBuilder createCrudSqlBuilder() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.crudSqlBuilder.getClass());
        enhancer.setCallback(this);
        return (CrudSqlBuilder) enhancer.create(new Class[]{DataStore.class}, new Object[]{dataStore});
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        switchSqlBuilderTemplate((SupperCrudSqlBuilder) obj);
        return methodProxy.invokeSuper(obj, args);
    }

    private void switchSqlBuilderTemplate(SupperCrudSqlBuilder supperCrudSqlBuilder) {
        switch (dataStore.getConfiguration().getDatabaseType()) {
            case MYSQL:
                supperCrudSqlBuilder.sqlBuilderTemplate = dataStore.getConfiguration().getSqlBuilder().getMysqlSqlBuilderTemplate();
                supperCrudSqlBuilder.sqlBuilderTemplate.setSqlPartBuilderTemplate(dataStore.getConfiguration().getSqlBuilder().getMysqlDataBlockBuilderTemplate());
                break;
            default:
                throw new SqlException("SqlBuilder do not support this database type temporarily.");
        }
    }
}