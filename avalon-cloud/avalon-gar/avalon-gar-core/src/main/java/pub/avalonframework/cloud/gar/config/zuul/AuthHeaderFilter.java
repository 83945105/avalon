/*
package pub.avalonframework.cloud.gar.config.zuul;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import pub.avalonframework.cloud.gar.beans.DynamicRouteLocator;
import pub.avalonframework.cloud.gar.utils.GarUtils;
import pub.avalonframework.cloud.gar.utils.RequestUtils;
import pub.avalonframework.cloud.gar.utils.TableUtils;
import pub.avalonframework.security.beans.Principal;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * 用户过滤器
 *
 * @author 白超
 * @date 2018/6/14
 *//*

@Component
public class AuthHeaderFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        Principal principal = null;
        HttpServletRequest request = requestContext.getRequest();
        try {
            principal = RequestUtils.getPrincipal();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (principal != null) {
            Map<String, Object> defaultPrincipal = new HashMap<>(2);
            defaultPrincipal.put("id", principal.getId());
            defaultPrincipal.put("username", principal.getName());
            defaultPrincipal.put("attributes", principal.getAttributes());
            requestContext.addZuulRequestHeader(GarUtils.ACCOUNT_NUMBER_HEADER_NAME, JSONObject.toJSONString(defaultPrincipal, SerializerFeature.BrowserCompatible));
        }
        Collection roleValues = this.securityModule.getRoleValues(principal, request, requestContext.getResponse());
        requestContext.addZuulRequestHeader(GarUtils.ROLE_VALUES_HEADER_NAME, JSONObject.toJSONString(roleValues, SerializerFeature.BrowserCompatible));
        //获取模块ID传递给微服务,微服务需要配置 FeignConfiguration 实现微服务之间headers传递
        String uri = request.getRequestURI();
        for (Map.Entry<String, DynamicRouteLocator.ZuulRoute> entry : DynamicRouteLocator.DB_ROUTE_MAP.entrySet()) {
            if (pathMatcher.match(entry.getKey(), uri)) {
                requestContext.addZuulRequestHeader(TableUtils.MODULE_ID_KEY, entry.getValue().getId());
                break;
            }
        }
        return null;
    }

}
*/
