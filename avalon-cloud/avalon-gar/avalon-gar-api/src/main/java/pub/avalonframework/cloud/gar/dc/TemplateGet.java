package pub.avalonframework.cloud.gar.dc;

import pub.avalon.holygrail.utils.StringUtil;
import pub.avalonframework.cloud.gar.entity.AutRole;
import pub.avalonframework.cloud.gar.entity.Template;

import java.util.*;

/**
 * @author 白超
 * @date 2018/12/7
 */
public class TemplateGet extends Template {

    /**
     * 子模块唯一标识符
     */
    private String subModuleValue;

    public String getSubModuleValue() {
        return subModuleValue;
    }

    public void setSubModuleValue(String subModuleValue) {
        this.subModuleValue = subModuleValue;
    }

    /**
     * 模糊查询
     */
    private String likeText;

    public String getLikeText() {
        return StringUtil.isEmpty(likeText) ? null : "%" + likeText.trim() + "%";
    }

    public void setLikeText(String likeText) {
        this.likeText = likeText;
    }

    /**
     * 子模块ID
     */
    private String subModuleIdSet;

    public Set<String> getSubModuleIdSet() {
        return StringUtil.isEmpty(subModuleIdSet) ? null : new HashSet<>(Arrays.asList(subModuleIdSet.split(",")));
    }

    public void setSubModuleIdSet(String subModuleIdSet) {
        this.subModuleIdSet = subModuleIdSet;
    }

    /**
     * 不匹配ID
     */
    private String notInIdSet;

    public Set<String> getNotInIdSet() {
        return StringUtil.isEmpty(notInIdSet) ? null : new HashSet<>(Arrays.asList(notInIdSet.split(",")));
    }

    public void setNotInIdSet(String notInIdSet) {
        this.notInIdSet = notInIdSet;
    }

    /**
     * 授予的角色
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

}
