package pub.avalonframework.cloud.gar.config.shiro;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pub.avalonframework.cloud.gar.service.impl.GarLoginAuthenticationServiceImpl;
import pub.avalonframework.cloud.gar.service.impl.GarResourceAuthenticationServiceImpl;
import pub.avalonframework.security.core.api.service.LoginAuthenticationService;
import pub.avalonframework.security.core.api.service.ResourceAuthenticationService;
import pub.avalonframework.security.core.api.service.WebService;
import pub.avalonframework.shiro.service.impl.ShiroWebServiceImpl;

/**
 * Shiro整合
 *
 * @author 白超
 * @date 2018/6/25
 */
@Configuration
public class ShiroConfig {

    @Bean
    public WebService webService() {
        return new ShiroWebServiceImpl();
    }

    @Bean
    public LoginAuthenticationService loginAuthenticationService() {
        return new GarLoginAuthenticationServiceImpl();
    }

    @Bean
    public ResourceAuthenticationService resourceAuthenticationService() {
        return new GarResourceAuthenticationServiceImpl();
    }
}