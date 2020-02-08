package pub.avalonframework.cloud.gar.api;

import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalonframework.web.spring.http.response.view.impl.DefaultMessageView;

/**
 * 网关权限路由接口
 *
 * @author 白超
 * @date 2018/6/11
 */
@FeignClient(name = "${feign.gar.gar-api-service-name:gar-service}", path = "${feign.gar.gar-api-service-path:/api-gar}")
public interface GarApi {

    String ROOT_PATH = "/gar/api-gar";

    /**
     * 判断是否在线
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/isOnline")
    @RequestLine("GET " + ROOT_PATH + "/get/isOnline")
    DefaultMessageView getIsOnline() throws Exception;

    /**
     * 获取当前在线信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/online")
    @RequestLine("GET " + ROOT_PATH + "/get/online")
    DataView getOnline() throws Exception;

    /**
     * 登出
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/logout")
    @RequestLine("GET " + ROOT_PATH + "/get/logout")
    DataView getLogout() throws Exception;

}
