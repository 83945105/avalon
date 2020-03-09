package pub.avalonframework.jdbc.core.spring.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pub.avalonframework.jdbc.core.spring.api.entity.SysUser;
import pub.avalonframework.jdbc.core.spring.api.entity.dto.SysUserDTO;

import java.util.List;

/**
 * @author baichao
 */
public interface SysUserApi {

    String ROOT_PATH = "/sqlhelper/sysUser";

    @RequestMapping(value = "/get/sysUserById/{id}")
    SysUser getSysUserById(@PathVariable("id") String id) throws Exception;

    @RequestMapping(value = "/get/page/list/sysUser")
    List<SysUser> getPageListSysUser(SysUserDTO sysUserDTO,
                                     Long currentPage,
                                     Long pageSize) throws Exception;
}