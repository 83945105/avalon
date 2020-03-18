package pub.avalonframework.wechat.official.account.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pub.avalonframework.web.spring.http.response.exception.impl.ErrorMessageException;
import pub.avalonframework.wechat.official.account.core.AccessTokenResponse;

import java.util.Map;

/**
 * @author baichao
 */
public final class ResponseUtils {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final static TypeReference<Map<String, Object>> MAP_TYPE_REFERENCE = new TypeReference<Map<String, Object>>() {
    };

    private ResponseUtils() {
    }

    public static AccessTokenResponse parseAccessTokenResponse(String response) {
        try {
            Map<String, Object> result = OBJECT_MAPPER.readValue(response, MAP_TYPE_REFERENCE);
            Object errcodeObj = result.get("errcode");
            if (errcodeObj != null) {
                Object errmsgObj = result.get("errmsg");
                throw new ErrorMessageException(Integer.parseInt(errcodeObj.toString()), errmsgObj == null ? "errmsg is null." : errmsgObj.toString());
            }
            Object access_token = result.get("access_token");
            Object expires_in = result.get("expires_in");
            if (access_token == null || expires_in == null) {
                throw new ErrorMessageException("access_token or expires_in is null.");
            }
            AccessTokenResponse accessTokenResponse = new AccessTokenResponse();
            accessTokenResponse.setAccess_token(String.valueOf(access_token));
            accessTokenResponse.setExpires_in(Long.parseLong(expires_in.toString()));
            return accessTokenResponse;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ErrorMessageException(e);
        }
    }
}