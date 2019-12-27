package pub.avalonframework.cloud.gar.beans;

import pub.avalon.beans.EnumMethods;

/**
 * 菜单组类型
 *
 * @author 白超
 * @date 2018/12/10
 */
public enum MenuGroupType implements EnumMethods {
    /**
     * 头部菜单
     */
    HEADER_MENU,
    /**
     * 左侧菜单
     */
    LEFT_MENU;

    @Override
    public Object getValue() {
        return this.name();
    }
}
