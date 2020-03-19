package pub.avalonframework.security.core.spi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import pub.avalonframework.core.spi.beans.SpiService;
import pub.avalonframework.security.core.beans.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * Security service.
 *
 * @author baichao
 */
public interface SecurityService extends SpiService {

    /**
     * Login.
     *
     * @param username The username.
     * @param password The password.
     * @param request  The http servlet request.
     * @param response The http servlet response.
     */
    void login(String username, String password, HttpServletRequest request, HttpServletResponse response);

    /**
     * Get session
     *
     * @param request  The http servlet request.
     * @param response The http servlet response.
     * @return Http session.
     */
    HttpSession getSession(HttpServletRequest request, HttpServletResponse response);

    /**
     * Get session by session id.
     *
     * @param sessionId The session id.
     * @param request   The http servlet request.
     * @param response  The http servlet response.
     * @return Http session.
     */
    HttpSession getSession(String sessionId, HttpServletRequest request, HttpServletResponse response);

    /**
     * Is authenticated.
     *
     * @param request  The http servlet request.
     * @param response The http servlet response.
     * @return true or false.
     */
    boolean isAuthenticated(HttpServletRequest request, HttpServletResponse response);

    /**
     * Get principal.
     *
     * @param request  The http servlet request.
     * @param response The http servlet response.
     * @return The principal.
     */
    Principal getPrincipal(HttpServletRequest request, HttpServletResponse response);

    /**
     * Get principal.
     *
     * @param request   The http servlet request.
     * @param response  The http servlet response.
     * @param valueType The value type.
     * @return The implements principal object.
     */
    <T extends Principal> T getPrincipal(HttpServletRequest request, HttpServletResponse response, Class<T> valueType);

    /**
     * Get principal.
     *
     * @param request            The http servlet request.
     * @param response           The http servlet response.
     * @param valueTypeReference The value type.
     * @return The implements principal object.
     */
    <T extends Principal> T getPrincipal(HttpServletRequest request, HttpServletResponse response, TypeReference<T> valueTypeReference);

    /**
     * Get principal by session id.
     *
     * @param sessionId The session id.
     * @param request   The http servlet request.
     * @param response  The http servlet response.
     * @return The principal.
     */
    Principal getPrincipal(String sessionId, HttpServletRequest request, HttpServletResponse response);

    /**
     * Get principal.
     *
     * @param sessionId The session id.
     * @param request   The http servlet request.
     * @param response  The http servlet response.
     * @param valueType The value type.
     * @return The implements principal object.
     */
    <T extends Principal> T getPrincipal(String sessionId, HttpServletRequest request, HttpServletResponse response, Class<T> valueType);

    /**
     * Get principal.
     *
     * @param sessionId          The session id.
     * @param request            The http servlet request.
     * @param response           The http servlet response.
     * @param valueTypeReference The value type.
     * @return The implements principal object.
     */
    <T extends Principal> T getPrincipal(String sessionId, HttpServletRequest request, HttpServletResponse response, TypeReference<T> valueTypeReference);

    /**
     * Get role identifier set.
     *
     * @param principal The principal.
     * @param request   The http servlet request.
     * @param response  The http servlet response.
     * @return The role identifier set.
     */
    Set<String> getRoleIdentifierSet(Principal principal, HttpServletRequest request, HttpServletResponse response);

    /**
     * Log out.
     *
     * @param request  The http servlet request.
     * @param response The http servlet response.
     */
    void logout(HttpServletRequest request, HttpServletResponse response);

    /**
     * MD5 encryption with salt
     *
     * @param source The value.
     * @param salt   The encryption salt.
     * @return The encryption value.
     */
    String md5(String source, String salt);
}