package pub.avalonframework.wechat.official.account.spring.boot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import pub.avalonframework.wechat.official.account.core.api.config.WebPageAuthorizationConfiguration;
import pub.avalonframework.wechat.official.account.core.api.config.WechatOfficialAccountConfiguration;
import pub.avalonframework.wechat.official.account.core.yaml.swapper.impl.WebPageAuthorizationConfigurationYamlSwapper;
import pub.avalonframework.wechat.official.account.core.yaml.swapper.impl.WechatOfficialAccountConfigurationYamlSwapper;
import pub.avalonframework.wechat.official.account.spring.web.EntranceController;

/**
 * Wechat official account spring boot configuration.
 *
 * @author baichao
 */
@ConditionalOnProperty(name = "spring.avalon.wechat.official-account.enabled", havingValue = "true")
@Configuration
@EnableConfigurationProperties({
        SpringBootWebPageAuthorizationConfigurationProperties.class,
        SpringBootWechatOfficialAccountConfigurationProperties.class
})
public class WechatOfficialAccountSpringBootConfiguration implements EnvironmentAware {

    private final SpringBootWebPageAuthorizationConfigurationProperties webPageAuthorizationConfigurationProperties;

    private final SpringBootWechatOfficialAccountConfigurationProperties wechatOfficialAccountConfigurationProperties;

    public WechatOfficialAccountSpringBootConfiguration(SpringBootWebPageAuthorizationConfigurationProperties webPageAuthorizationConfigurationProperties, SpringBootWechatOfficialAccountConfigurationProperties wechatOfficialAccountConfigurationProperties) {
        this.webPageAuthorizationConfigurationProperties = webPageAuthorizationConfigurationProperties;
        this.wechatOfficialAccountConfigurationProperties = wechatOfficialAccountConfigurationProperties;
    }

    @Bean
    @ConditionalOnMissingBean(WebPageAuthorizationConfiguration.class)
    public WebPageAuthorizationConfiguration webPageAuthorizationConfiguration() {
        return new WebPageAuthorizationConfigurationYamlSwapper().swap(webPageAuthorizationConfigurationProperties);
    }

    @Bean
    @ConditionalOnMissingBean(WechatOfficialAccountConfiguration.class)
    public WechatOfficialAccountConfiguration wechatOfficialAccountConfiguration(WebPageAuthorizationConfiguration webPageAuthorizationConfiguration) {
        WechatOfficialAccountConfiguration wechatOfficialAccountConfiguration = new WechatOfficialAccountConfigurationYamlSwapper().swap(wechatOfficialAccountConfigurationProperties);
        wechatOfficialAccountConfiguration.setWebPageAuthorization(webPageAuthorizationConfiguration);
        return wechatOfficialAccountConfiguration;
    }

    @Bean
    @ConditionalOnMissingBean(EntranceController.class)
    public EntranceController entranceController() {
        return new EntranceController();
    }

    @Override
    public void setEnvironment(Environment environment) {

    }
}