package pub.avalonframework.cloud.gar.beans;

import pub.avalon.beans.EnumMethods;

/**
 * 菜单路由关系
 *
 * @author 白超
 * @date 2018/12/13
 */
public enum MenuRouteRelation implements EnumMethods {

    /**
     * 菜单选中
     * 拥有该关系的路由,会让菜单处于选中状态
     */
    MENU_SELECTED,
    /**
     * 点击菜单时跳转到该路由
     * 理论一个菜单节点只能拥有一个该属性路由
     */
    MENU_CLICK_TO_ROUTE;

    @Override
    public Object getValue() {
        return this.name();
    }
}
