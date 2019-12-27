package pub.avalonframework.cloud.gar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalonframework.cloud.gar.service.GarService;
import pub.avalonframework.cloud.gar.utils.RequestUtils;
import pub.avalonframework.security.core.api.config.SecurityConfiguration;

import java.util.Map;

/**
 * @author 白超
 * @date 2018/11/26
 */
public class DefaultGarServiceImpl implements GarService {

    @Autowired
    private SecurityConfiguration securityConfiguration;

    @Override
    public boolean getIsOnline() throws Exception {
        if (!securityConfiguration.getEnabled()) {
            ExceptionUtil.throwInfoException("权限功能未开启,无法获取登录状态");
        }
        return RequestUtils.isAuthenticated();
    }

    @Override
    public Map<String, Object> getOnline() throws Exception {
        ExceptionUtil.throwFailException("若想使用该功能请实现GarService接口");
        return null;
    }

    @Override
    public Map<String, Object> getLogout() throws Exception {
        ExceptionUtil.throwFailException("若想使用该功能请实现GarService接口");
        return null;
    }

}
