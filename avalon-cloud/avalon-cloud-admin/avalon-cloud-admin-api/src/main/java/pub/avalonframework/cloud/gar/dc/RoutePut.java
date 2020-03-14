package pub.avalonframework.cloud.gar.dc;

import pub.avalon.holygrail.utils.StringUtil;
import pub.avalonframework.cloud.gar.entity.Route;

/**
 * @author 白超
 */
public class RoutePut extends Route {

    /**
     * 路由视图数据
     */
    private String routeViewJson;

    public String getRouteViewJson() {
        return StringUtil.isEmpty(routeViewJson) ? null : routeViewJson.trim();
    }

    public void setRouteViewJson(String routeViewJson) {
        this.routeViewJson = routeViewJson;
    }

}
