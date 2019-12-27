package pub.avalonframework.shiro.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import pub.avalonframework.security.core.beans.MapPrincipal;
import pub.avalonframework.security.core.beans.Principal;
import pub.avalonframework.security.core.spi.service.SecurityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @author baichao
 */
public class ShiroSecurityServiceImpl implements SecurityService {

    public final static String SERVICE_NAME = "SHIRO";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private String toJSONString(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private <T> T caseValue(Object value, TypeReference<T> typeReference) {
        if (value == null) {
            return null;
        }
        if (value.getClass() == typeReference.getType()) {
            return (T) value;
        }
        try {
            return objectMapper.readValue(toJSONString(value), typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private <T> T caseValue(Object value, Class<T> valueType) {
        if (value == null) {
            return null;
        }
        if (value.getClass() == valueType) {
            return (T) value;
        }
        try {
            return objectMapper.readValue(toJSONString(value), valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getServiceName() {
        return SERVICE_NAME;
    }

    @Override
    public void login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password));
    }

    @Override
    public HttpSession getSession(HttpServletRequest request, HttpServletResponse response) {
        Session session = SecurityUtils.getSubject().getSession();
        return session == null ? null : new ShiroHttpSession(session, request, request.getServletContext());
    }

    @Override
    public HttpSession getSession(String sessionId, HttpServletRequest request, HttpServletResponse response) {
        SessionKey key = new WebSessionKey(sessionId, request, response);
        Session session = SecurityUtils.getSecurityManager().getSession(key);
        return session == null ? null : new ShiroHttpSession(session, request, request.getServletContext());
    }

    @Override
    public boolean isAuthenticated(HttpServletRequest request, HttpServletResponse response) {
        return SecurityUtils.getSubject().isAuthenticated();
    }

    @Override
    public Principal getPrincipal(HttpServletRequest request, HttpServletResponse response) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            Object value = subject.getPrincipal();
            if (value instanceof Map) {
                return caseValue(value, MapPrincipal.class);
            }
            return (Principal) value;
        }
        return null;
    }

    @Override
    public <T extends Principal> T getPrincipal(HttpServletRequest request, HttpServletResponse response, Class<T> valueType) {
        Principal principal = getPrincipal(request, response);
        return caseValue(principal, valueType);
    }

    @Override
    public <T extends Principal> T getPrincipal(HttpServletRequest request, HttpServletResponse response, TypeReference<T> valueTypeReference) {
        Principal principal = getPrincipal(request, response);
        return caseValue(principal, valueTypeReference);
    }

    @Override
    public Principal getPrincipal(String sessionId, HttpServletRequest request, HttpServletResponse response) {
        SessionKey key = new WebSessionKey(sessionId, request, response);
        Session session = SecurityUtils.getSecurityManager().getSession(key);
        if (session == null) {
            return null;
        }
        Object value = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        if (value instanceof SimplePrincipalCollection) {
            return (Principal) ((SimplePrincipalCollection) value).getPrimaryPrincipal();
        }
        return null;
    }

    @Override
    public <T extends Principal> T getPrincipal(String sessionId, HttpServletRequest request, HttpServletResponse response, Class<T> valueType) {
        Principal principal = getPrincipal(sessionId, request, response);
        return caseValue(principal, valueType);
    }

    @Override
    public <T extends Principal> T getPrincipal(String sessionId, HttpServletRequest request, HttpServletResponse response, TypeReference<T> valueTypeReference) {
        Principal principal = getPrincipal(sessionId, request, response);
        return caseValue(principal, valueTypeReference);
    }

    @Override
    public Set<String> getRoleIdentifierSet(Principal principal, HttpServletRequest request, HttpServletResponse response) {
        return principal.getRoleIdentifierSet();
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityUtils.getSubject().logout();
    }
}