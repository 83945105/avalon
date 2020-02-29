package pub.avalonframework.jdbc.core.spring.service;

import pub.avalonframework.jdbc.core.spring.api.entity.SysUser;

/**
 * @author baichao
 */
public interface SysUserService {

    SysUser getSysUserByPrimary(String keyValue) throws Exception;
}