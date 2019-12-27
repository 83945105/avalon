package pub.avalonframework.cloud.gar.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalonframework.security.core.beans.Principal;
import pub.avalonframework.security.core.spi.utils.SecurityUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author baichao
 */
public class RequestUtils {

    private RequestUtils() {
    }

    public static HttpServletRequest getCurrentHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        ExceptionUtil.throwErrorException("不支持的Request类型.");
        return null;
    }

    public static HttpServletResponse getCurrentHttpServletResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletWebRequest) {
            return ((ServletWebRequest) requestAttributes).getResponse();
        }
        ExceptionUtil.throwErrorException("不支持的Response类型.");
        return null;
    }

    public static boolean isAuthenticated() {
        return SecurityUtils.isAuthenticated(getCurrentHttpServletRequest(), null);
    }

    public static void logout() {
        SecurityUtils.logout(getCurrentHttpServletRequest(), null);
    }

    public static Principal getPrincipal() {
        return SecurityUtils.getPrincipal(getCurrentHttpServletRequest(), null);
    }

    public static <T extends Principal> T getPrincipal(Class<T> valueType) {
        return SecurityUtils.getPrincipal(getCurrentHttpServletRequest(), null, valueType);
    }

    public static <T extends Principal> T getPrincipal(TypeReference<T> valueTypeReference) {
        return SecurityUtils.getPrincipal(getCurrentHttpServletRequest(), null, valueTypeReference);
    }

    public static Principal getPrincipal(String sessionId) {
        return SecurityUtils.getPrincipal(sessionId, getCurrentHttpServletRequest(), null);
    }

    public static <T extends Principal> T getPrincipal(String sessionId, Class<T> valueType) {
        return SecurityUtils.getPrincipal(sessionId, getCurrentHttpServletRequest(), null, valueType);
    }

    public static <T extends Principal> T getPrincipal(String sessionId, TypeReference<T> valueTypeReference) {
        return SecurityUtils.getPrincipal(sessionId, getCurrentHttpServletRequest(), null, valueTypeReference);
    }

    public static HttpSession getHttpSession(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getSession();
    }

    public static HttpSession getCurrentHttpSession() {
        HttpServletRequest request = getCurrentHttpServletRequest();
        return request == null ? null : request.getSession();
    }

    public static String getCurrentRequestedSessionId() {
        HttpServletRequest request = getCurrentHttpServletRequest();
        return request == null ? null : request.getRequestedSessionId();
    }

    public static void setAttribute(ServletRequest servletRequest, String key, Object value) {
        servletRequest.setAttribute(key, value);
    }

    public static void setAttribute(HttpSession httpSession, String key, Object value) {
        httpSession.setAttribute(key, value);
    }

    public static void setAttribute2CurrentServletRequest(String key, Object value) {
        ServletRequest request = getCurrentHttpServletRequest();
        if (request == null) {
            return;
        }
        setAttribute(request, key, value);
    }

    public static void setAttribute2CurrentHttpSession(String key, Object value) {
        HttpSession session = getCurrentHttpSession();
        if (session == null) {
            return;
        }
        setAttribute(session, key, value);
    }

    public static Object getAttribute(ServletRequest servletRequest, String key) {
        return servletRequest == null ? null : servletRequest.getAttribute(key);
    }

    public static Object getAttribute(HttpSession httpSession, String key) {
        return httpSession == null ? null : httpSession.getAttribute(key);
    }

    public static Object getCurrentServletRequestAttribute(String key) {
        return getAttribute(getCurrentHttpServletRequest(), key);
    }

    public static Object getCurrentHttpSessionAttribute(String key) {
        return getAttribute(getCurrentHttpSession(), key);
    }
}