package pub.avalonframework.cloud.gar.controller;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.avalon.holygrail.response.utils.DataViewUtil;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalon.holygrail.response.views.MessageView;
import pub.avalonframework.cloud.gar.api.GarApi;
import pub.avalonframework.cloud.gar.service.GarService;
import pub.avalonframework.cloud.gar.service.impl.DefaultGarServiceImpl;
import pub.avalonframework.core.beans.MessageConstant;
import pub.avalonframework.web.spring.http.response.HttpResultInfo;
import pub.avalonframework.web.spring.http.response.view.impl.DefaultMessageView;

import java.util.Map;

/**
 * @author 白超
 * @date 2018/6/11
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
    public DataView getOnline() throws Exception {
        Map<String, Object> map = this.garService.getOnline();
        return DataViewUtil.getModelViewSuccess(map);
    }

    @RequestMapping("/get/logout")
    @Override
    public DataView getLogout() throws Exception {
        Map<String, Object> map = this.garService.getLogout();
        return DataViewUtil.getModelViewSuccess("您已退出登录", map);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.garService == null) {
            this.garService = new DefaultGarServiceImpl();
        }
    }
}