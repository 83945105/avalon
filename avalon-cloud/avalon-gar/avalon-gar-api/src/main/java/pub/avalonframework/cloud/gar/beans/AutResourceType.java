package pub.avalonframework.cloud.gar.beans;

import pub.avalon.beans.EnumMethods;

/**
 * 资源类型
 *
 * @author 白超
 * @date 2018/11/8
 */
public enum AutResourceType implements EnumMethods {

    /**
     * 统一资源定位符
     */
    URL("统一资源定位符"),
    /**
     * 资源许可
     */
    PERMISSION("资源许可"),
    /**
     * 节点
     */
    NODE("节点");

    public String disc;

    AutResourceType(String disc) {
        this.disc = disc;
    }

    @Override
    public Object getValue() {
        return this.name();
    }

}
