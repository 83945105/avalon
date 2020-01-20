package pub.avalonframework.core.yaml.swapper;

import pub.avalonframework.core.yaml.config.YamlConfiguration;

/**
 * @author baichao
 */
public interface YamlSwapper<Y extends YamlConfiguration, T> {

    /**
     * Swap data to Yaml configuration.
     *
     * @param data The data.
     * @return Swapped yaml configuration.
     */
    Y swap(T data);

    /**
     * Swap yaml configuration to object.
     *
     * @param yamlConfiguration The yaml configuration.
     * @return Swapped object.
     */
    T swap(Y yamlConfiguration);
}