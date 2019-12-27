package pub.avalonframework.cloud.gar.beans;

import pub.avalon.beans.EnumMethods;

/**
 * 菜单是否使用选项卡功能
 *
 * @author 白超
 * @date 2018/12/10
 */
public enum MenuUseTab implements EnumMethods {

    /**
     * 使用
     */
    TRUE("true"),
    /**
     * 不使用
     */
    FALSE("false");

    public String value;

    MenuUseTab(String value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

}
