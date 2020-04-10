package pub.avalonframework.wechat.official.account.spring.boot;

import pub.avalonframework.core.api.config.EhCacheConfiguration;
import pub.avalonframework.core.yaml.swapper.impl.EhCacheConfigurationYamlSwapper;
import pub.avalonframework.wechat.official.account.core.api.config.CustomMenuConfiguration;
import pub.avalonframework.wechat.official.account.core.api.config.WebPageAuthorizationConfiguration;
import pub.avalonframework.wechat.official.account.core.api.config.WechatOfficialAccountConfiguration;
import pub.avalonframework.wechat.official.account.core.yaml.swapper.impl.CustomMenuConfigurationYamlSwapper;
import pub.avalonframework.wechat.official.account.core.yaml.swapper.impl.WebPageAuthorizationConfigurationYamlSwapper;
import pub.avalonframework.wechat.official.account.core.yaml.swapper.impl.WechatOfficialAccountConfigurationYamlSwapper;
import pub.avalonframework.wechat.official.account.spring.web.controller.WechatOfficialAccountEntranceController;
import pub.avalonframework.wechat.official.account.spring.web.controller.WechatOfficialAccountWebPageAuthorizationController;
import pub.avalonframework.wechat.official.account.spring.web.service.WechatOfficialAccountWebPageAuthorizationService;
import pub.avalonframework.wechat.official.account.spring.web.service.impl.WechatOfficialAccountWebPageAuthorizationServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Wechat official account spring boot configuration.
 *
 * @author baichao
 */
@ConditionalOnProperty(name = "spring.avalon.wechat.official-account.enabled", havingValue = "true")
@Configuration
@EnableConfigurationProperties({
        SpringBootCustomMenuConfigurationProperties.class,
        SpringBootApiOauth2StateCacheConfigurationProperties.class,
        SpringBootWebPageAuthorizationConfigurationProperties.class,
        SpringBootWechatOfficialAccountConfigurationProperties.class
})
public class WechatOfficialAccountSpringBootConfiguration implements EnvironmentAware {

    private final SpringBootCustomMenuConfigurationProperties customMenuConfigurationProperties;

    private final SpringBootApiOauth2StateCacheConfigurationProperties apiOauth2StateCacheConfigurationProperties;

    private final SpringBootWebPageAuthorizationConfigurationProperties webPageAuthorizationConfigurationProperties;

    private final SpringBootWechatOfficialAccountConfigurationProperties wechatOfficialAccountConfigurationProperties;

    public WechatOfficialAccountSpringBootConfiguration(SpringBootCustomMenuConfigurationProperties customMenuConfigurationProperties, SpringBootApiOauth2StateCacheConfigurationProperties apiOauth2StateCacheConfigurationProperties, SpringBootWebPageAuthorizationConfigurationProperties webPageAuthorizationConfigurationProperties, SpringBootWechatOfficialAccountConfigurationProperties wechatOfficialAccountConfigurationProperties) {
        this.customMenuConfigurationProperties = customMenuConfigurationProperties;
        this.apiOauth2StateCacheConfigurationProperties = apiOauth2StateCacheConfigurationProperties;
        this.webPageAuthorizationConfigurationProperties = webPageAuthorizationConfigurationProperties;
        this.wechatOfficialAccountConfigurationProperties = wechatOfficialAccountConfigurationProperties;
    }

    @Bean
    @ConditionalOnMissingBean(CustomMenuConfiguration.class)
    public CustomMenuConfiguration customMenuConfiguration() {
        return new CustomMenuConfigurationYamlSwapper().swap(customMenuConfigurationProperties);
    }

    @Bean("apiOauth2StateCache")
    @ConditionalOnMissingBean(name = "apiOauth2StateCache")
    public EhCacheConfiguration apiOauth2StateCache() {
        return new EhCacheConfigurationYamlSwapper().swap(apiOauth2StateCacheConfigurationProperties);
    }

    @Bean
    @ConditionalOnMissingBean(WebPageAuthorizationConfiguration.class)
    public WebPageAuthorizationConfiguration webPageAuthorizationConfiguration(@Qualifier("apiOauth2StateCache") EhCacheConfiguration apiOauth2StateCache) {
        WebPageAuthorizationConfiguration webPageAuthorizationConfiguration = new WebPageAuthorizationConfigurationYamlSwapper().swap(webPageAuthorizationConfigurationProperties);
        webPageAuthorizationConfiguration.setApiOauth2StateCache(apiOauth2StateCache);
        return webPageAuthorizationConfiguration;
    }

    @Bean
    @ConditionalOnMissingBean(WechatOfficialAccountConfiguration.class)
    public WechatOfficialAccountConfiguration wechatOfficialAccountConfiguration(CustomMenuConfiguration customMenuConfiguration,
                                                                                 WebPageAuthorizationConfiguration webPageAuthorizationConfiguration) {
        WechatOfficialAccountConfiguration wechatOfficialAccountConfiguration = new WechatOfficialAccountConfigurationYamlSwapper().swap(wechatOfficialAccountConfigurationProperties);
        wechatOfficialAccountConfiguration.setCustomMenu(customMenuConfiguration);
        wechatOfficialAccountConfiguration.setWebPageAuthorization(webPageAuthorizationConfiguration);
        return wechatOfficialAccountConfiguration;
    }

    @Bean
    @ConditionalOnMissingBean(WechatOfficialAccountEntranceController.class)
    public WechatOfficialAccountEntranceController wechatOfficialAccountEntranceController() {
        return new WechatOfficialAccountEntranceController();
    }

    @Bean
    @ConditionalOnMissingBean(WechatOfficialAccountWebPageAuthorizationService.class)
    public WechatOfficialAccountWebPageAuthorizationService wechatOfficialAccountWebPageAuthorizationService() {
        return new WechatOfficialAccountWebPageAuthorizationServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(WechatOfficialAccountWebPageAuthorizationController.class)
    public WechatOfficialAccountWebPageAuthorizationController wechatOfficialAccountWebPageAuthorizationController() {
        return new WechatOfficialAccountWebPageAuthorizationController();
    }

    @Override
    public void setEnvironment(Environment environment) {

    }
}