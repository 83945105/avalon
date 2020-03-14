package pub.avalonframework.cloud.gar.dc;

import pub.avalon.holygrail.utils.StringUtil;
import pub.avalonframework.cloud.gar.entity.Route;
import pub.avalonframework.cloud.gar.entity.RouteView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 白超
 */
public class RouteGet extends Route {

    /**
     * 模糊查询条件
     */
    private String likeText;

    public String getLikeText() {
        return StringUtil.isEmpty(likeText) ? null : "%" + likeText.trim() + "%";
    }

    public void setLikeText(String likeText) {
        this.likeText = likeText;
    }

    /**
     * 拥有的路由视图
     */
    private List<RouteView> routeViews;

    public List<RouteView> getRouteViews() {
        return routeViews;
    }

    public void setRouteViews(List<RouteView> routeViews) {
        this.routeViews = routeViews;
    }

    public void addRouteView(RouteView routeView) {
        if (this.routeViews == null) {
            this.routeViews = new ArrayList<>();
        }
        this.routeViews.add(routeView);
    }

    /**
     * 父级路由名称
     */
    private String parentRouteName;

    public String getParentRouteName() {
        return parentRouteName;
    }

    public void setParentRouteName(String parentRouteName) {
        this.parentRouteName = parentRouteName;
    }

    /**
     * 子路由数量
     */
    private Integer childCount;

    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    /**
     * 与菜单的关系
     */
    private String menuRelation;

    public String getMenuRelation() {
        return menuRelation;
    }

    public void setMenuRelation(String menuRelation) {
        this.menuRelation = menuRelation;
    }

    /**
     * 子路由
     */
    private List<Route> subRoutes;

    public List<Route> getSubRoutes() {
        return subRoutes;
    }

    public void setSubRoutes(List<Route> subRoutes) {
        this.subRoutes = subRoutes;
    }

    public void addSubRoute(Route route) {
        if (this.subRoutes == null) {
            this.subRoutes = new ArrayList<>();
        }
        this.subRoutes.add(route);
    }

}
