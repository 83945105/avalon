package pub.avalonframework.core.yaml.config;

/**
 * @author baichao
 */
public class YamlEhCacheConfiguration implements YamlConfiguration {

    private String alias;

    private Long heapSize;

    private Long offheapSize;

    private Long diskSize;

    private Long timeToLiveMilliseconds;

    private Long timeToIdleMilliseconds;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Long getHeapSize() {
        return heapSize;
    }

    public void setHeapSize(Long heapSize) {
        this.heapSize = heapSize;
    }

    public Long getOffheapSize() {
        return offheapSize;
    }

    public void setOffheapSize(Long offheapSize) {
        this.offheapSize = offheapSize;
    }

    public Long getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(Long diskSize) {
        this.diskSize = diskSize;
    }

    public Long getTimeToLiveMilliseconds() {
        return timeToLiveMilliseconds;
    }

    public void setTimeToLiveMilliseconds(Long timeToLiveMilliseconds) {
        this.timeToLiveMilliseconds = timeToLiveMilliseconds;
    }

    public Long getTimeToIdleMilliseconds() {
        return timeToIdleMilliseconds;
    }

    public void setTimeToIdleMilliseconds(Long timeToIdleMilliseconds) {
        this.timeToIdleMilliseconds = timeToIdleMilliseconds;
    }
}