package pub.avalonframework.jdbc.core.spring.service;

import pub.avalonframework.jdbc.core.spring.api.entity.SysUser;
import pub.avalonframework.jdbc.core.spring.api.entity.SysUserHelper;
import pub.avalonframework.sqlhelper.core.expression.lambda.ColumnLambdaCallable;

/**
 * @author baichao
 */
public interface SysUserService {

    SysUser getSysUserByPrimaryKey(String keyValue) throws Exception;

    SysUser getSysUserByPrimaryKey(String keyValue, ColumnLambdaCallable<SysUserHelper.Column> columnLambdaCallable) throws Exception;
}