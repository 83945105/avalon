package pub.avalonframework.wechat.official.account.core.yaml.swapper.impl;

import pub.avalonframework.core.yaml.swapper.YamlSwapper;
import pub.avalonframework.wechat.official.account.core.api.config.CustomMenuConfiguration;
import pub.avalonframework.wechat.official.account.core.yaml.config.YamlCustomMenuConfiguration;

/**
 * @author baichao
 */
public final class CustomMenuConfigurationYamlSwapper implements YamlSwapper<YamlCustomMenuConfiguration, CustomMenuConfiguration> {

    private final static String DEFAULT_MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create";

    @Override
    public YamlCustomMenuConfiguration swap(CustomMenuConfiguration data) {
        data = data == null ? new CustomMenuConfiguration() : data;
        YamlCustomMenuConfiguration configuration = new YamlCustomMenuConfiguration();
        String menuCreateUrl = data.getMenuCreateUrl();
        configuration.setMenuCreateUrl(menuCreateUrl == null ? DEFAULT_MENU_CREATE_URL : menuCreateUrl);
        return configuration;
    }

    @Override
    public CustomMenuConfiguration swap(YamlCustomMenuConfiguration yamlConfiguration) {
        yamlConfiguration = yamlConfiguration == null ? new YamlCustomMenuConfiguration() : yamlConfiguration;
        CustomMenuConfiguration configuration = new CustomMenuConfiguration();
        String menuCreateUrl = yamlConfiguration.getMenuCreateUrl();
        configuration.setMenuCreateUrl(menuCreateUrl == null ? DEFAULT_MENU_CREATE_URL : menuCreateUrl);
        return configuration;
    }
}