package pub.avalonframework.cloud.gar.beans;

import pub.avalon.beans.EnumMethods;

/**
 * 视图类型
 *
 * @author 白超
 */
public enum TemplateType implements EnumMethods {
    /**
     * vue-组件
     */
    VUE_COMPONENT;

    @Override
    public Object getValue() {
        return this.name();
    }
}
