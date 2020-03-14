package pub.avalonframework.cloud.gar.dc;

import pub.avalon.holygrail.utils.StringUtil;
import pub.avalonframework.cloud.gar.entity.SubModule;

/**
 * @author 白超
 */
public class SubModuleGet extends SubModule {

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
    private String subModuleId;

    public String getSubModuleId() {
        return StringUtil.isEmpty(subModuleId) ? null : subModuleId.trim();
    }

    public void setSubModuleId(String subModuleId) {
        this.subModuleId = subModuleId;
    }
}
