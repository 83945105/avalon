package pub.avalonframework.cloud.gar.dc;

import pub.avalon.holygrail.utils.StringUtil;
import pub.avalonframework.cloud.gar.entity.AutResource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 资源查询
 *
 * @author baichao
 * @date 2018/6/13
 */
public class AutResourceGet extends AutResource {

    private String likeText;

    public String getLikeText() {
        return StringUtil.isEmpty(likeText) ? null : "%" + likeText.trim() + "%";
    }

    public void setLikeText(String likeText) {
        this.likeText = likeText;
    }

    private String parentIdSet;

    public Set<String> getParentIdSet() {
        return StringUtil.isEmpty(parentIdSet) ? new HashSet<>(0) : new HashSet<>(Arrays.asList(parentIdSet.split(",")));
    }

    public void setParentIdSet(String parentIdSet) {
        this.parentIdSet = parentIdSet;
    }

    private String typeSet;

    public Set<String> getTypeSet() {
        return StringUtil.isEmpty(typeSet) ? new HashSet<>(0) : new HashSet<>(Arrays.asList(typeSet.split(",")));
    }

    public void setTypeSet(String typeSet) {
        this.typeSet = typeSet;
    }

    private String childTypeSet;

    public Set<String> getChildTypeSet() {
        return StringUtil.isEmpty(childTypeSet) ? new HashSet<>(0) : new HashSet<>(Arrays.asList(childTypeSet.split(",")));
    }

    public void setChildTypeSet(String childTypeSet) {
        this.childTypeSet = childTypeSet;
    }

    /**
     * 是否可以编辑
     */
    private boolean canEdit = true;

    private List<AutResourceGet> children;

    private Long childCount;

    private String parentResourceName;

    private boolean open;

    public String getLocationName() {
        return this.getName() + " (" + this.getParentResourceName() + ")";
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public List<AutResourceGet> getChildren() {
        return children;
    }

    public void setChildren(List<AutResourceGet> children) {
        this.children = children;
    }

    public Long getChildCount() {
        return childCount;
    }

    public void setChildCount(Long childCount) {
        this.childCount = childCount;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getParentResourceName() {
        return parentResourceName;
    }

    public void setParentResourceName(String parentResourceName) {
        this.parentResourceName = parentResourceName;
    }
}
