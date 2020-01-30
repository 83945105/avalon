package pub.avalonframework.cloud.gar.utils;

import com.alibaba.fastjson.JSONObject;
import pub.avalonframework.cloud.gar.beans.AccountNumber;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author baichao
 * @date 2018/12/29
 */
public class GarUtils {

    /**
     * 用户请求头name
     */
    public static final String ACCOUNT_NUMBER_HEADER_NAME = "X-AUTH-NAME";

    /**
     * 用户属性Key
     */
    public static final String ACCOUNT_NUMBER_ATTRIBUTE_KEY = "X-AUTH-KEY";

    /**
     * 角色标识符集合请求头name
     */
    public static final String ROLE_VALUES_HEADER_NAME = "X-ROLE-VALUES-NAME";

    /**
     * 角色标识符集合属性Key
     */
    public static final String ROLE_VALUES_ATTRIBUTE_KEY = "X-ROLE-VALUES-KEY";

    public static final Set<String> RESOURCE_TABLE_NAMES = Collections.synchronizedSet(new HashSet<>());
    public static final Set<String> ROLE_TABLE_NAMES = Collections.synchronizedSet(new HashSet<>());
    public static final Set<String> ROLE_RESOURCE_TABLE_NAMES = Collections.synchronizedSet(new HashSet<>());
    public static final Set<String> OBJECT_ROLE_TABLE_NAMES = Collections.synchronizedSet(new HashSet<>());
    public static final Set<String> ROLE_MENU_TABLE_NAMES = Collections.synchronizedSet(new HashSet<>());
    public static final Set<String> ROLE_ROUTE_VIEW_TABLE_NAMES = Collections.synchronizedSet(new HashSet<>());

    private GarUtils() {
    }

    /**
     * 从请求头中获取请求的模块ID
     *
     * @param request
     * @return
     */
    public static String getModuleId(HttpServletRequest request) {
        return request.getHeader(TableUtils.MODULE_ID_KEY);
    }

    /**
     * 从请求头中获取当前用户
     *
     * @param returnType
     * @param request
     * @param <T>
     * @return
     */
    public static <T extends AccountNumber> T getAccountNumber(Class<T> returnType, HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        String principalString = request.getHeader(ACCOUNT_NUMBER_HEADER_NAME);
        if (principalString == null) {
            Object principalObject = request.getAttribute(ACCOUNT_NUMBER_ATTRIBUTE_KEY);
            principalString = principalObject == null ? null : principalObject.toString();
        }
        if (principalString == null) {
            return null;
        }
        return JSONObject.parseObject(principalString, returnType);
    }

    /**
     * 从请求头中获取当前用户拥有的角色标识符
     *
     * @param request
     * @return
     */
    public static Collection<String> getRoleValues(HttpServletRequest request) {
        String roleValuesString = request.getHeader(ROLE_VALUES_HEADER_NAME);
        if (roleValuesString == null) {
            Object roleValuesObject = request.getAttribute(ROLE_VALUES_ATTRIBUTE_KEY);
            roleValuesString = roleValuesObject == null ? null : roleValuesObject.toString();
        }
        if (roleValuesString == null) {
            return new HashSet<>(0);
        }
        return JSONObject.parseArray(roleValuesString, String.class);
    }

}
