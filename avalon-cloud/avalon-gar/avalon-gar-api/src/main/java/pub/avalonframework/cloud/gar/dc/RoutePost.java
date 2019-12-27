package pub.avalonframework.cloud.gar.dc;

import pub.avalon.holygrail.utils.StringUtil;
import pub.avalonframework.cloud.gar.entity.Route;

/**
 * @author 白超
 * @date 2018/12/7
 */
public class RoutePost extends Route {

    /**
     * 上级路由ID
     */
    private String parentId;

    @Override
    public String getParentId() {
        return StringUtil.isEmpty(parentId) ? null : parentId.trim();
    }

    @Override
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

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
