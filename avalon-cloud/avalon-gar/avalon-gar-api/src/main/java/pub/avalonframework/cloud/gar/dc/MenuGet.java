package pub.avalonframework.cloud.gar.dc;

import pub.avalon.holygrail.utils.StringUtil;
import pub.avalonframework.cloud.gar.entity.AutRole;
import pub.avalonframework.cloud.gar.entity.Menu;
import pub.avalonframework.cloud.gar.entity.Route;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 * @date 2018/12/10
 */
public class MenuGet extends Menu {

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
     * 父级菜单名称
     */
    private String parentMenuName;

    public String getParentMenuName() {
        return parentMenuName;
    }

    public void setParentMenuName(String parentMenuName) {
        this.parentMenuName = parentMenuName;
    }

    /**
     * 子菜单数量
     */
    private Integer childCount;

    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    /**
     * 菜单选中的路由
     */
    private List<Route> menuSelectedRoutes;

    public List<Route> getMenuSelectedRoutes() {
        return menuSelectedRoutes;
    }

    public void setMenuSelectedRoutes(List<Route> menuSelectedRoutes) {
        this.menuSelectedRoutes = menuSelectedRoutes;
    }

    public void addMenuSelectedRoute(Route route) {
        if (this.menuSelectedRoutes == null) {
            this.menuSelectedRoutes = new ArrayList<>();
        }
        this.menuSelectedRoutes.add(route);
    }

    /**
     * 点击菜单跳转的路由
     */
    private Route menuClickToRoute;

    public Route getMenuClickToRoute() {
        return menuClickToRoute;
    }

    public void setMenuClickToRoute(Route menuClickToRoute) {
        this.menuClickToRoute = menuClickToRoute;
    }

    /**
     * 菜单授予的角色
     */
    private List<AutRole> roles;

    public List<AutRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AutRole> roles) {
        this.roles = roles;
    }

    public void addRole(AutRole role) {
        if (this.roles == null) {
            this.roles = new ArrayList<>();
        }
        this.roles.add(role);
    }

    /**
     * 子菜单
     */
    private List<Menu> subMenus;

    public List<Menu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<Menu> subMenus) {
        this.subMenus = subMenus;
    }

    public void addSubMenu(Menu menu) {
        if (this.subMenus == null) {
            this.subMenus = new ArrayList<>();
        }
        this.subMenus.add(menu);
    }

}
