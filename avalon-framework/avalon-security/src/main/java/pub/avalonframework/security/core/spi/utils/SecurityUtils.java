package pub.avalonframework.security.core.spi.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.avalonframework.core.spi.beans.SpiDuplicateInstantiationException;
import pub.avalonframework.core.spi.beans.SpiNoneInstantiationException;
import pub.avalonframework.security.core.beans.Principal;
import pub.avalonframework.security.core.spi.service.SecurityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.Set;

/**
 * Security utils.
 *
 * @author baichao
 */
public final class SecurityUtils {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUtils.class);

    private SecurityUtils() {
    }

    private final static SecurityService securityService;

    static {
        Iterator<SecurityService> iterator = ServiceLoader.load(SecurityService.class).iterator();
        if (iterator.hasNext()) {
            securityService = iterator.next();
        } else {
            throw new SpiNoneInstantiationException(SecurityService.class, SecurityUtils.class, null);
        }
        if (iterator.hasNext()) {
            throw new SpiDuplicateInstantiationException(securityService.getClass(), iterator.next().getClass(), SecurityService.class, SecurityUtils.class, null);
        }
        logger.info("SPI SecurityUtils load class " + securityService.getClass().getName() + " for interface " + SecurityService.class.getName());
    }

    /**
     * Login.
     *
     * @param username The username.
     * @param password The password.
     * @param request  The http servlet request.
     * @param response The http servlet response.
     */
    public static void login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        securityService.login(username, password, request, response);
    }

    /**
     * Get session
     *
     * @param request  The http servlet request.
     * @param response The http servlet response.
     * @return Http session.
     */
    public static HttpSession getSession(HttpServletRequest request, HttpServletResponse response) {
        return securityService.getSession(request, response);
    }

    /**
     * Get session by session id.
     *
     * @param sessionId The session id.
     * @param request   The http servlet request.
     * @param response  The http servlet response.
     * @return Http session.
     */
    public static HttpSession getSession(String sessionId, HttpServletRequest request, HttpServletResponse response) {
        return securityService.getSession(sessionId, request, response);
    }

    /**
     * Is authenticated.
     *
     * @param request  The http servlet request.
     * @param response The http servlet response.
     * @return true or false.
     */
    public static boolean isAuthenticated(HttpServletRequest request, HttpServletResponse response) {
        return securityService.isAuthenticated(request, response);
    }

    /**
     * Get principal.
     *
     * @param request  The http servlet request.
     * @param response The http servlet response.
     * @return The principal.
     */
    public static Principal getPrincipal(HttpServletRequest request, HttpServletResponse response) {
        return securityService.getPrincipal(request, response);
    }

    /**
     * Get principal.
     *
     * @param request   The http servlet request.
     * @param response  The http servlet response.
     * @param valueType The value type.
     * @return The implements principal object.
     */
    public static <T extends Principal> T getPrincipal(HttpServletRequest request, HttpServletResponse response, Class<T> valueType) {
        return securityService.getPrincipal(request, response, valueType);
    }

    /**
     * Get principal.
     *
     * @param request            The http servlet request.
     * @param response           The http servlet response.
     * @param valueTypeReference The value type.
     * @return The implements principal object.
     */
    public static <T extends Principal> T getPrincipal(HttpServletRequest request, HttpServletResponse response, TypeReference<T> valueTypeReference) {
        return securityService.getPrincipal(request, response, valueTypeReference);
    }

    /**
     * Get principal by session id.
     *
     * @param sessionId The session id.
     * @param request   The http servlet request.
     * @param response  The http servlet response.
     * @return The principal.
     */
    public static Principal getPrincipal(String sessionId, HttpServletRequest request, HttpServletResponse response) {
        return securityService.getPrincipal(sessionId, request, response);
    }

    /**
     * Get principal.
     *
     * @param sessionId The session id.
     * @param request   The http servlet request.
     * @param response  The http servlet response.
     * @param valueType The value type.
     * @return The implements principal object.
     */
    public static <T extends Principal> T getPrincipal(String sessionId, HttpServletRequest request, HttpServletResponse response, Class<T> valueType) {
        return securityService.getPrincipal(sessionId, request, response, valueType);
    }

    /**
     * Get principal.
     *
     * @param sessionId          The session id.
     * @param request            The http servlet request.
     * @param response           The http servlet response.
     * @param valueTypeReference The value type.
     * @return The implements principal object.
     */
    public static <T extends Principal> T getPrincipal(String sessionId, HttpServletRequest request, HttpServletResponse response, TypeReference<T> valueTypeReference) {
        return securityService.getPrincipal(sessionId, request, response, valueTypeReference);
    }

    /**
     * Get role identifier set.
     *
     * @param principal The principal.
     * @param request   The http servlet request.
     * @param response  The http servlet response.
     * @return The role identifier set.
     */
    public static Set<String> getRoleIdentifierSet(Principal principal, HttpServletRequest request, HttpServletResponse response) {
        return securityService.getRoleIdentifierSet(principal, request, response);
    }

    /**
     * Log out.
     *
     * @param request  The http servlet request.
     * @param response The http servlet response.
     */
    public static void logout(HttpServletRequest request, HttpServletResponse response) {
        securityService.logout(request, response);
    }
}