package pub.avalonframework.wechat.official.account.spring.web.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pub.avalonframework.common.utils.HttpUtils;
import pub.avalonframework.web.spring.http.response.exception.impl.ErrorMessageException;
import pub.avalonframework.wechat.official.account.core.api.config.CustomMenuConfiguration;
import pub.avalonframework.wechat.official.account.core.api.config.WechatOfficialAccountConfiguration;
import pub.avalonframework.wechat.official.account.core.custommenu.Menu;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.util.Map;

/**
 * @author baichao
 */
public final class WOAUtils {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final static TypeReference<Map<String, Object>> MAP_TYPE_REFERENCE = new TypeReference<Map<String, Object>>() {
    };

    private final CustomMenuConfiguration customMenuConfiguration;

    private final WechatOfficialAccountConfiguration wechatOfficialAccountConfiguration;

    private WOAUtils(WechatOfficialAccountConfiguration wechatOfficialAccountConfiguration) {
        this.customMenuConfiguration = wechatOfficialAccountConfiguration.getCustomMenu();
        this.wechatOfficialAccountConfiguration = wechatOfficialAccountConfiguration;
    }

    public static WOAUtils getInstance(WechatOfficialAccountConfiguration wechatOfficialAccountConfiguration) {
        return new WOAUtils(wechatOfficialAccountConfiguration);
    }

    /**
     * 新建菜单
     *
     * @param menu 菜单数据
     */
    public void createMenu(Menu menu) {
        try {
            String menuCreateUrl = customMenuConfiguration.getAutoAssembleMenuCreateUrlWithAccessToken(wechatOfficialAccountConfiguration.getAccessToken());
            StringEntity stringEntity = new StringEntity(OBJECT_MAPPER.writeValueAsString(menu), "utf-8");
            stringEntity.setContentType("application/json");
            HttpResponse httpResponse = HttpUtils.getInstance().doPost(menuCreateUrl, stringEntity);
            String response = EntityUtils.toString(httpResponse.getEntity());
            Map<String, Object> result = OBJECT_MAPPER.readValue(response, MAP_TYPE_REFERENCE);
            Object errcodeObj = result.get("errcode");
            if (errcodeObj == null) {
                Object errmsgObj = result.get("errmsg");
                throw new ErrorMessageException(-1, errmsgObj == null ? "errmsg is null." : errmsgObj.toString());
            }
            int errcode = Integer.parseInt(errcodeObj.toString());
            if (errcode != 0) {
                Object errmsgObj = result.get("errmsg");
                throw new ErrorMessageException(errcode, errmsgObj == null ? "errmsg is null." : errmsgObj.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorMessageException(e);
        }
    }
}