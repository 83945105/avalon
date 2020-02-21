package pub.avalonframework.web.spring.api.config;

/**
 * @author baichao
 */
public class WebSpringConfiguration {

    private CorsConfiguration cors;

    private ResourceConfiguration resource;

    public CorsConfiguration getCors() {
        return cors;
    }

    public void setCors(CorsConfiguration cors) {
        this.cors = cors;
    }

    public ResourceConfiguration getResource() {
        return resource;
    }

    public void setResource(ResourceConfiguration resource) {
        this.resource = resource;
    }
}