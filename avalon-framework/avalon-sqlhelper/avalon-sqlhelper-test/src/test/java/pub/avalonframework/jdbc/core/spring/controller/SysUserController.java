package pub.avalonframework.jdbc.core.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.avalonframework.jdbc.core.spring.api.SysUserApi;
import pub.avalonframework.jdbc.core.spring.api.entity.SysUser;
import pub.avalonframework.jdbc.core.spring.service.SysUserService;

/**
 * @author baichao
 */
@RequestMapping(SysUserApi.ROOT_PATH)
@RestController
public class SysUserController implements SysUserApi {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public SysUser getSysUserById(String id) throws Exception {
        return sysUserService.getSysUserById(id);
    }
}
