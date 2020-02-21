package pub.avalonframework.web.spring.yaml.swapper.impl;

import pub.avalonframework.core.yaml.swapper.YamlSwapper;
import pub.avalonframework.web.spring.api.config.CorsConfiguration;
import pub.avalonframework.web.spring.yaml.config.YamlCorsConfiguration;

import java.util.Collections;
import java.util.List;

/**
 * @author baichao
 */
public final class CorsConfigurationYamlSwapper implements YamlSwapper<YamlCorsConfiguration, CorsConfiguration> {

    private final static Boolean DEFAULT_ENABLED = Boolean.TRUE;

    private final static String DEFAULT_MAPPING = "/**";

    private final static List<String> DEFAULT_ALLOWED_ORIGINS = Collections.singletonList("*");

    private final static Boolean DEFAULT_ALLOW_CREDENTIALS = Boolean.TRUE;

    private final static List<String> DEFAULT_ALLOWED_METHODS = Collections.singletonList("*");

    private final static Long DEFAULT_MAX_AGE = 3600L;

    @Override
    public YamlCorsConfiguration swap(CorsConfiguration data) {
        data = data == null ? new CorsConfiguration() : data;
        YamlCorsConfiguration configuration = new YamlCorsConfiguration();
        Boolean enabled = data.getEnabled();
        configuration.setEnabled(enabled == null ? DEFAULT_ENABLED : enabled);
        String mapping = data.getMapping();
        configuration.setMapping(mapping == null ? DEFAULT_MAPPING : mapping);
        List<String> allowedOrigins = data.getAllowedOrigins();
        configuration.setAllowedOrigins(allowedOrigins == null ? DEFAULT_ALLOWED_ORIGINS : allowedOrigins);
        Boolean allowCredentials = data.getAllowCredentials();
        configuration.setAllowCredentials(allowCredentials == null ? DEFAULT_ALLOW_CREDENTIALS : allowCredentials);
        List<String> allowedMethods = data.getAllowedMethods();
        configuration.setAllowedMethods(allowedMethods == null ? DEFAULT_ALLOWED_METHODS : allowedMethods);
        Long maxAge = data.getMaxAge();
        configuration.setMaxAge(maxAge == null ? DEFAULT_MAX_AGE : maxAge);
        return configuration;
    }

    @Override
    public CorsConfiguration swap(YamlCorsConfiguration yamlConfiguration) {
        yamlConfiguration = yamlConfiguration == null ? new YamlCorsConfiguration() : yamlConfiguration;
        CorsConfiguration configuration = new CorsConfiguration();
        Boolean enabled = yamlConfiguration.getEnabled();
        configuration.setEnabled(enabled == null ? DEFAULT_ENABLED : enabled);
        String mapping = yamlConfiguration.getMapping();
        configuration.setMapping(mapping == null ? DEFAULT_MAPPING : mapping);
        List<String> allowedOrigins = yamlConfiguration.getAllowedOrigins();
        configuration.setAllowedOrigins(allowedOrigins == null ? DEFAULT_ALLOWED_ORIGINS : allowedOrigins);
        Boolean allowCredentials = yamlConfiguration.getAllowCredentials();
        configuration.setAllowCredentials(allowCredentials == null ? DEFAULT_ALLOW_CREDENTIALS : allowCredentials);
        List<String> allowedMethods = yamlConfiguration.getAllowedMethods();
        configuration.setAllowedMethods(allowedMethods == null ? DEFAULT_ALLOWED_METHODS : allowedMethods);
        Long maxAge = yamlConfiguration.getMaxAge();
        configuration.setMaxAge(maxAge == null ? DEFAULT_MAX_AGE : maxAge);
        return configuration;
    }
}