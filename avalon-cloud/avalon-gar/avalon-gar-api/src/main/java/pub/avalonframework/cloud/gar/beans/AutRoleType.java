package pub.avalonframework.cloud.gar.beans;

import pub.avalon.beans.EnumMethods;

/**
 * 角色类型
 *
 * @author 白超
 * @date 2018/11/8
 */
public enum AutRoleType implements EnumMethods {
    /**
     * 本地角色
     */
    LOCAL("本地角色"),
    /**
     * 其它
     */
    OTHER("其它");

    public String disc;

    AutRoleType(String disc) {
        this.disc = disc;
    }

    @Override
    public Object getValue() {
        return this.name();
    }

}
