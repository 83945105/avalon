package pub.avalonframework.jdbc.core.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pub.avalonframework.jdbc.core.spring.api.entity.SysUser;
import pub.avalonframework.jdbc.core.spring.api.entity.SysUserHelper;
import pub.avalonframework.jdbc.core.spring.dao.SysUserJdbcDao;
import pub.avalonframework.jdbc.core.spring.service.SysUserService;
import pub.avalonframework.sqlhelper.core.callback.ColumnCallback;
import pub.avalonframework.web.utils.ValidationUtils;

/**
 * @author baichao
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserJdbcDao sysUserJdbcDao;

    @Override
    public SysUser getSysUserByPrimaryKey(String keyValue) throws Exception {
        ValidationUtils.nonNullCheck("keyValue", keyValue);
        return sysUserJdbcDao.queryByPrimaryKey(keyValue);
    }

    @Override
    public SysUser getSysUserByPrimaryKey(String keyValue, ColumnCallback<SysUserHelper.Column> columnCallback) throws Exception {
        ValidationUtils.nonNullCheck("keyValue", keyValue);
        return null;
    }
}