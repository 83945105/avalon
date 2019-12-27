package pub.avalonframework.cloud.gar.dc;

import pub.avalon.holygrail.utils.StringUtil;
import pub.avalonframework.cloud.gar.entity.AutRole;

/**
 * @author 白超
 * @version 1.0
 * @see
 * @since 2018/7/12
 */
public class AutRoleGet extends AutRole {

    /**
     * 模糊匹配
     */
    private String likeText;

    public String getLikeText() {
        return StringUtil.isEmpty(likeText) ? null : "%" + likeText.trim() + "%";
    }

    public void setLikeText(String likeText) {
        this.likeText = likeText;
    }

    /**
     * 是否可以编辑
     */
    private boolean canEdit = true;

    /**
     * 是否可以改变状态
     */
    private boolean canChangeStatus = true;

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public boolean isCanChangeStatus() {
        return canChangeStatus;
    }

    public void setCanChangeStatus(boolean canChangeStatus) {
        this.canChangeStatus = canChangeStatus;
    }
}
