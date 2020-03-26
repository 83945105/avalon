package pub.avalonframework.web.spring.api.config;

/**
 * @author baichao
 */
public class ResourceConfiguration {

    private String accessAddress;

    private String uploadAddress;

    public String getAccessAddress() {
        return accessAddress;
    }

    public void setAccessAddress(String accessAddress) {
        this.accessAddress = accessAddress;
    }

    public String getUploadAddress() {
        return uploadAddress;
    }

    public void setUploadAddress(String uploadAddress) {
        this.uploadAddress = uploadAddress;
    }
}