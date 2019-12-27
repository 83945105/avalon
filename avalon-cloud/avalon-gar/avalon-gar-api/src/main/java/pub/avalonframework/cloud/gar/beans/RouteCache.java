package pub.avalonframework.cloud.gar.beans;

import pub.avalon.beans.EnumMethods;

/**
 * 路由缓存
 *
 * @author 白超
 * @date 2019/1/26
 */
public enum RouteCache implements EnumMethods {
    /**
     * 是
     */
    Y,
    /**
     * 否
     */
    N;

    @Override
    public Object getValue() {
        return this.name();
    }
}
