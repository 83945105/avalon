package pub.avalonframework.jdbc.core.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pub.avalonframework.jdbc.core.spring.api.entity.SysUser;
import pub.avalonframework.jdbc.core.spring.dao.SysUserJdbcDao;
import pub.avalonframework.jdbc.core.spring.service.SysUserService;

/**
 * @author baichao
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserJdbcDao sysUserJdbcDao;

    @Override
    public SysUser getSysUserByPrimary(String keyValue) throws Exception {
        return null;
    }
}