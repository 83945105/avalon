package pub.avalonframework.wechat.official.account.spring.web.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pub.avalonframework.common.utils.HttpUtils;
import pub.avalonframework.web.spring.http.response.exception.impl.ErrorMessageException;
import pub.avalonframework.wechat.official.account.core.api.config.WebPageAuthorizationConfiguration;
import pub.avalonframework.wechat.official.account.core.api.config.WechatOfficialAccountConfiguration;
import pub.avalonframework.wechat.official.account.core.webpage.UserInfoResponse;
import pub.avalonframework.wechat.official.account.core.webpage.WebPageAccessTokenRefreshResponse;
import pub.avalonframework.wechat.official.account.core.webpage.WebPageAccessTokenResponse;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.Map;

/**
 * 微信公众号Web工具
 *
 * @author baichao
 */
public final class WOAWebUtils {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final static TypeReference<Map<String, Object>> MAP_TYPE_REFERENCE = new TypeReference<Map<String, Object>>() {
    };

    private final WebPageAuthorizationConfiguration webPageAuthorizationConfiguration;

    private final WechatOfficialAccountConfiguration wechatOfficialAccountConfiguration;

    private WOAWebUtils(WechatOfficialAccountConfiguration wechatOfficialAccountConfiguration) {
        this.webPageAuthorizationConfiguration = wechatOfficialAccountConfiguration.getWebPageAuthorization();
        this.wechatOfficialAccountConfiguration = wechatOfficialAccountConfiguration;
    }

    public static WOAWebUtils getInstance(WechatOfficialAccountConfiguration wechatOfficialAccountConfiguration) {
        return new WOAWebUtils(wechatOfficialAccountConfiguration);
    }

    /**
     * 获取自动组装开放授权地址
     *
     * @return 开放授权地址
     */
    public String getAutoAssembleOauth2Path() {
        return webPageAuthorizationConfiguration.getAutoAssembleOauth2Path();
    }

    /**
     * 获取自动组装开放授权地址
     *
     * @param state 传递的参数
     * @return 开放授权地址
     */
    public String getAutoAssembleOauth2PathWithState(String state) {
        return webPageAuthorizationConfiguration.getAutoAssembleOauth2PathWithState(state);
    }

    /**
     * 获取网页授权 access_token
     *
     * @param code code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。
     * @return WebPageAccessTokenResponse
     */
    public WebPageAccessTokenResponse getWebPageAccessToken(String code) {
        try {
            HttpResponse httpResponse = HttpUtils.getInstance().doGet(webPageAuthorizationConfiguration.getAutoAssembleAccessTokenGetUrlWithWechatOfficialAccountConfigurationAndCode(wechatOfficialAccountConfiguration, code));
            String response = EntityUtils.toString(httpResponse.getEntity());
            Map<String, Object> result = OBJECT_MAPPER.readValue(response, MAP_TYPE_REFERENCE);
            Object errcodeObj = result.get("errcode");
            if (errcodeObj != null) {
                Object errmsgObj = result.get("errmsg");
                throw new ErrorMessageException(Integer.parseInt(errcodeObj.toString()), errmsgObj == null ? "errmsg is null." : errmsgObj.toString());
            }
            Object access_token = result.get("access_token");
            Object expires_in = result.get("expires_in");
            Object refresh_token = result.get("refresh_token");
            Object openid = result.get("openid");
            Object scope = result.get("scope");
            WebPageAccessTokenResponse webPageAccessTokenResponse = new WebPageAccessTokenResponse();
            webPageAccessTokenResponse.setAccess_token(String.valueOf(access_token));
            webPageAccessTokenResponse.setExpires_in(Long.parseLong(expires_in.toString()));
            webPageAccessTokenResponse.setRefresh_token(String.valueOf(refresh_token));
            webPageAccessTokenResponse.setOpenid(String.valueOf(openid));
            webPageAccessTokenResponse.setScope(String.valueOf(scope));
            return webPageAccessTokenResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorMessageException(e);
        }
    }

    /**
     * 刷新网页授权 access_token
     *
     * @param refreshToken 用于刷新的token
     * @return WebPageAccessTokenRefreshResponse
     */
    public WebPageAccessTokenRefreshResponse refreshWebPageAccessToken(String refreshToken) {
        try {
            HttpResponse httpResponse = HttpUtils.getInstance().doGet(webPageAuthorizationConfiguration.getAutoAssembleAccessTokenRefreshUrlWithWechatOfficialAccountConfigurationAndRefreshToken(wechatOfficialAccountConfiguration, refreshToken));
            String response = EntityUtils.toString(httpResponse.getEntity());
            Map<String, Object> result = OBJECT_MAPPER.readValue(response, MAP_TYPE_REFERENCE);
            Object errcodeObj = result.get("errcode");
            if (errcodeObj != null) {
                Object errmsgObj = result.get("errmsg");
                throw new ErrorMessageException(Integer.parseInt(errcodeObj.toString()), errmsgObj == null ? "errmsg is null." : errmsgObj.toString());
            }
            Object access_token = result.get("access_token");
            Object expires_in = result.get("expires_in");
            Object refresh_token = result.get("refresh_token");
            Object openid = result.get("openid");
            Object scope = result.get("scope");
            WebPageAccessTokenRefreshResponse webPageAccessTokenRefreshResponse = new WebPageAccessTokenRefreshResponse();
            webPageAccessTokenRefreshResponse.setAccess_token(String.valueOf(access_token));
            webPageAccessTokenRefreshResponse.setExpires_in(Long.parseLong(expires_in.toString()));
            webPageAccessTokenRefreshResponse.setRefresh_token(String.valueOf(refresh_token));
            webPageAccessTokenRefreshResponse.setOpenid(String.valueOf(openid));
            webPageAccessTokenRefreshResponse.setScope(String.valueOf(scope));
            return webPageAccessTokenRefreshResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorMessageException(e);
        }
    }

    /**
     * 获取用户信息
     *
     * @param webPageAccessToken 网页授权 access_token
     * @param openId             微信用户openid
     * @return UserInfoResponse
     */
    public UserInfoResponse getUserInfo(String webPageAccessToken, String openId) {
        try {
            HttpResponse httpResponse = HttpUtils.getInstance().doGet(webPageAuthorizationConfiguration.getAutoAssembleUserInfoGetUrlWithAccessTokenAndOpenId(webPageAccessToken, openId));
            String response = EntityUtils.toString(httpResponse.getEntity());
            Map<String, Object> result = OBJECT_MAPPER.readValue(response, MAP_TYPE_REFERENCE);
            Object errcodeObj = result.get("errcode");
            if (errcodeObj != null) {
                Object errmsgObj = result.get("errmsg");
                throw new ErrorMessageException(Integer.parseInt(errcodeObj.toString()), errmsgObj == null ? "errmsg is null." : errmsgObj.toString());
            }
            Object openid = result.get("openid");
            Object nickname = result.get("nickname");
            Object sex = result.get("sex");
            Object province = result.get("province");
            Object city = result.get("city");
            Object country = result.get("country");
            Object headimgurl = result.get("headimgurl");
            Object privilege = result.get("privilege");
            Object unionid = result.get("unionid");
            UserInfoResponse userInfoResponse = new UserInfoResponse();
            userInfoResponse.setOpenid(String.valueOf(openid));
            userInfoResponse.setNickname(String.valueOf(nickname));
            userInfoResponse.setSex(String.valueOf(sex));
            userInfoResponse.setProvince(String.valueOf(province));
            userInfoResponse.setCity(String.valueOf(city));
            userInfoResponse.setCountry(String.valueOf(country));
            userInfoResponse.setHeadimgurl(String.valueOf(headimgurl));
            userInfoResponse.setPrivilege(String.valueOf(privilege));
            userInfoResponse.setUnionid(String.valueOf(unionid));
            return userInfoResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorMessageException(e);
        }
    }

    /**
     * 检验网页授权 access_token
     *
     * @param webPageAccessToken 网页授权 access_token
     * @param openId             微信用户openid
     * @return true | false
     */
    public boolean validationWebPageAccessToken(String webPageAccessToken, String openId) {
        try {
            HttpResponse httpResponse = HttpUtils.getInstance().doGet(webPageAuthorizationConfiguration.getAutoAssembleAccessTokenValidationUrlWithAccessTokenAndOpenId(webPageAccessToken, openId));
            String response = EntityUtils.toString(httpResponse.getEntity());
            Map<String, Object> result = OBJECT_MAPPER.readValue(response, MAP_TYPE_REFERENCE);
            Object errcodeObj = result.get("errcode");
            Object errmsgObj = result.get("errmsg");
            if (errcodeObj != null) {
                if ("0".equals(errcodeObj)) {
                    return "ok".equals(errmsgObj);
                }
                throw new ErrorMessageException(Integer.parseInt(errcodeObj.toString()), errmsgObj == null ? "errmsg is null." : errmsgObj.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorMessageException(e);
        }
        throw new ErrorMessageException("errcode is null.");
    }
}