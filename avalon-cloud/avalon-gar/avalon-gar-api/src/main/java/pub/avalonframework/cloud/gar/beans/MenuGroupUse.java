package pub.avalonframework.cloud.gar.beans;

import pub.avalon.beans.EnumMethods;

/**
 * @author 白超
 * @date 2018/12/14
 */
public enum MenuGroupUse implements EnumMethods {

    /**
     * 使用
     */
    TRUE,

    /**
     * 不使用
     */
    FALSE;

    @Override
    public Object getValue() {
        return this.name();
    }
}
