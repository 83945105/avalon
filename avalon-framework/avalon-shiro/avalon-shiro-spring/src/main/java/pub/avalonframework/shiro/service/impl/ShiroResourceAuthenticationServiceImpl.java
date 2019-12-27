package pub.avalonframework.shiro.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.avalonframework.security.core.api.config.SecurityConfiguration;
import pub.avalonframework.security.core.api.service.ResourceAuthenticationService;
import pub.avalonframework.security.core.api.service.WebService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @author baichao
 */
public class ShiroResourceAuthenticationServiceImpl implements ResourceAuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(ShiroResourceAuthenticationServiceImpl.class);

    private final static String SERVICE_NAME = "SHIRO";

    @Override
    public String getServiceName() {
        return SERVICE_NAME;
    }

    @Override
    public Set<String> getReleaseResources(Map<String, Object> parameters, SecurityConfiguration securityConfiguration) {
        return Collections.emptySet();
    }

    @Override
    public void onAccessDenied(WebService webService, HttpServletRequest request, HttpServletResponse response, Map<String, Object> parameters, SecurityConfiguration securityConfiguration) {
        logger.debug("You can implement the pub.avalonframework.security.core.api.service.ResourceAuthenticationService interface and register as a spring bean to achieve this function.");
    }
}