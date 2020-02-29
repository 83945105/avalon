package pub.avalonframework.jdbc.core.spring.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pub.avalonframework.jdbc.core.spring.api.entity.SysUser;

/**
 * @author baichao
 */
public interface SysUserApi {

    String ROOT_PATH = "/sqlhelper/sysUser";

    @RequestMapping(value = "/get/sysUserById/{id}")
    SysUser getSysUserById(@PathVariable("id") String id) throws Exception;
}