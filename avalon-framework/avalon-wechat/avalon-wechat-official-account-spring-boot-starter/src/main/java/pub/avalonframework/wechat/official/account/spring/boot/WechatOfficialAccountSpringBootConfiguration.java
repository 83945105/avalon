package pub.avalonframework.wechat.official.account.spring.boot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import pub.avalonframework.wechat.official.account.core.api.config.WechatOfficialAccountConfiguration;
import pub.avalonframework.wechat.official.account.core.yaml.swapper.impl.WechatOfficialAccountConfigurationYamlSwapper;
import pub.avalonframework.wechat.official.account.spring.mvc.EntranceController;

/**
 * Wechat official account spring boot configuration.
 *
 * @author baichao
 */
@ConditionalOnProperty(name = "spring.avalon.wechat.official-account.enabled", havingValue = "true")
@Configuration
@EnableConfigurationProperties({
        SpringBootWechatOfficialAccountConfigurationProperties.class
})
public class WechatOfficialAccountSpringBootConfiguration implements EnvironmentAware {

    private final SpringBootWechatOfficialAccountConfigurationProperties wechatOfficialAccountConfigurationProperties;

    public WechatOfficialAccountSpringBootConfiguration(SpringBootWechatOfficialAccountConfigurationProperties wechatOfficialAccountConfigurationProperties) {
        this.wechatOfficialAccountConfigurationProperties = wechatOfficialAccountConfigurationProperties;
    }

    @Bean
    @ConditionalOnMissingBean(WechatOfficialAccountConfiguration.class)
    public WechatOfficialAccountConfiguration wechatOfficialAccountConfiguration() {
        return new WechatOfficialAccountConfigurationYamlSwapper().swap(wechatOfficialAccountConfigurationProperties);
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