package pub.avalonframework.jdbc.core.spring.service;

import pub.avalonframework.jdbc.core.spring.api.entity.SysUser;
import pub.avalonframework.jdbc.core.spring.api.entity.SysUserHelper;
import pub.avalonframework.sqlhelper.core.expression.lambda.ColumnLambdaCallable;

import java.util.List;

/**
 * @author baichao
 */
public interface SysUserService {

    SysUser getSysUserById(String id) throws Exception;

    <T> T getSysUserById(String id, Class<T> returnType) throws Exception;

    SysUser getSysUserById(String id, ColumnLambdaCallable<SysUserHelper.Column> columnLambdaCallable) throws Exception;

    <T> T getSysUserById(String id, ColumnLambdaCallable<SysUserHelper.Column> columnLambdaCallable, Class<T> returnType) throws Exception;

    List<SysUser> getListSysUser() throws Exception;
}