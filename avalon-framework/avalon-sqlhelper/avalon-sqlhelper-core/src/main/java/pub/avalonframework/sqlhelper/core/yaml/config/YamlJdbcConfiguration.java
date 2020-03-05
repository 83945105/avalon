package pub.avalonframework.sqlhelper.core.yaml.config;

import pub.avalonframework.core.yaml.config.YamlConfiguration;

/**
 * @author baichao
 */
public class YamlJdbcConfiguration implements YamlConfiguration {

    private Boolean allowReturnNull;

    private Long maxCurrentPage;

    private Long maxPageSize;

    public Boolean getAllowReturnNull() {
        return allowReturnNull;
    }

    public void setAllowReturnNull(Boolean allowReturnNull) {
        this.allowReturnNull = allowReturnNull;
    }

    public Long getMaxCurrentPage() {
        return maxCurrentPage;
    }

    public void setMaxCurrentPage(Long maxCurrentPage) {
        this.maxCurrentPage = maxCurrentPage;
    }

    public Long getMaxPageSize() {
        return maxPageSize;
    }

    public void setMaxPageSize(Long maxPageSize) {
        this.maxPageSize = maxPageSize;
    }
}