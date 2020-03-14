package pub.avalonframework.cloud.gar.dc;

import pub.avalon.holygrail.utils.StringUtil;
import pub.avalonframework.cloud.gar.entity.AutResource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 资源提交
 *
 * @author 白超
 */
public class AutResourcePost extends AutResource {

    /**
     * 使用该资源的角色ID
     */
    private String roleIdSet;

    public Set<String> getRoleIdSet() {
        return StringUtil.isEmpty(roleIdSet) ? new HashSet<>(0) : new HashSet<>(Arrays.asList(roleIdSet.split(",")));
    }

    public void setRoleIdSet(String roleIdSet) {
        this.roleIdSet = roleIdSet;
    }

    /**
     * 使用该资源的上级资源ID
     */
    private String parentIdSet;

    public Set<String> getParentIdSet() {
        return StringUtil.isEmpty(parentIdSet) ? new HashSet<>(0) : new HashSet<>(Arrays.asList(parentIdSet.split(",")));
    }

    public void setParentIdSet(String parentIdSet) {
        this.parentIdSet = parentIdSet;
    }
}
