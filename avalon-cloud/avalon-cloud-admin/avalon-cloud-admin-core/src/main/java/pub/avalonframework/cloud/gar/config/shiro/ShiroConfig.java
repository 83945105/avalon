package pub.avalonframework.cloud.gar.config.shiro;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pub.avalonframework.cloud.gar.service.impl.GarLoginAuthenticationServiceImpl;
import pub.avalonframework.cloud.gar.service.impl.GarResourceAuthenticationServiceImpl;
import pub.avalonframework.security.core.api.service.LoginAuthenticationService;
import pub.avalonframework.security.core.api.service.ResourceAuthenticationService;

/**
 * Shiro整合
 *
 * @author 白超
 * @date 2018/6/25
 */
@Configuration
public class ShiroConfig {

    //TODO 待迁移至 AvalonCloudConfiguration
    @Bean
    public LoginAuthenticationService loginAuthenticationService() {
        return new GarLoginAuthenticationServiceImpl();
    }

    //TODO 待迁移至 AvalonCloudConfiguration
    @Bean
    public ResourceAuthenticationService resourceAuthenticationService() {
        return new GarResourceAuthenticationServiceImpl();
    }
}