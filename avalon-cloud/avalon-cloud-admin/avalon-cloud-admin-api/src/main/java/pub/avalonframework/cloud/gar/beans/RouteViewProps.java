package pub.avalonframework.cloud.gar.beans;

import pub.avalon.beans.EnumMethods;

/**
 * 路由视图props
 *
 * @author 白超
 */
public enum RouteViewProps implements EnumMethods {
    /**
     * 使用
     */
    TRUE("true"),
    /**
     * 不使用
     */
    FALSE("false");

    public String value;

    RouteViewProps(String value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return this.value;
    }
}
