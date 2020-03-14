package pub.avalonframework.cloud.gar.controller;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalonframework.cloud.gar.api.GarApi;
import pub.avalonframework.cloud.gar.service.GarService;
import pub.avalonframework.cloud.gar.service.impl.DefaultGarServiceImpl;
import pub.avalonframework.core.beans.MessageConstant;
import pub.avalonframework.web.spring.http.response.HttpResultInfo;
import pub.avalonframework.web.spring.http.response.view.impl.DefaultMessageView;
import pub.avalonframework.web.spring.http.response.view.impl.EntityMessageView;

import java.util.Map;

/**
 * @author 白超
 */
@RestController
@RequestMapping(GarApi.ROOT_PATH)
public class GarController implements GarApi, InitializingBean {

    @Autowired(required = false)
    private GarService garService;

    @Override
    @RequestMapping(value = "/get/isOnline")
    public DefaultMessageView getIsOnline() throws Exception {
        boolean online = this.garService.getIsOnline();
        if (!online) {
            ExceptionUtil.throwNeedLoginException(MessageConstant.EXCEPTION_NEED_LOGIN_MESSAGE);
        }
        return new DefaultMessageView(new HttpResultInfo(HttpStatus.OK));
    }

    @Override
    @RequestMapping(value = "/get/online")
    public EntityMessageView<Map<String, Object>> getOnline() throws Exception {
        Map<String, Object> map = this.garService.getOnline();
        return new EntityMessageView<>(map, new HttpResultInfo(HttpStatus.OK));
    }

    @RequestMapping("/get/logout")
    @Override
    public EntityMessageView<Map<String, Object>> getLogout() throws Exception {
        Map<String, Object> map = this.garService.getLogout();
        return new EntityMessageView<>(map, new HttpResultInfo(HttpStatus.OK, "您已退出登录"));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.garService == null) {
            this.garService = new DefaultGarServiceImpl();
        }
    }
}