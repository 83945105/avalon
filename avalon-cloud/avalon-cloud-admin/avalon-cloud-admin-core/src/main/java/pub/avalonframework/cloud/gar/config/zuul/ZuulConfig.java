package pub.avalonframework.cloud.gar.config.zuul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import pub.avalonframework.cloud.gar.beans.DynamicRouteLocator;

/**
 * @author 白超
 */
@Configuration
public class ZuulConfig {

    @Autowired
    ZuulProperties zuulProperties;
    @Autowired
    ServerProperties serverProperties;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Bean
    public DynamicRouteLocator routeLocator() {
        DynamicRouteLocator routeLocator = new DynamicRouteLocator(this.serverProperties.getServlet().getContextPath(), this.zuulProperties);
        routeLocator.setJdbcTemplate(jdbcTemplate);
        return routeLocator;
    }
}
