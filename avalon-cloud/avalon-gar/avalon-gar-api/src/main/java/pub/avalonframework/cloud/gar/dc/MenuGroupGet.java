package pub.avalonframework.cloud.gar.dc;

import pub.avalon.holygrail.utils.StringUtil;
import pub.avalonframework.cloud.gar.entity.MenuGroup;

/**
 * @author 白超
 * @date 2018/12/14
 */
public class MenuGroupGet extends MenuGroup {

    /**
     * 模糊查询条件
     */
    private String likeText;

    public String getLikeText() {
        return StringUtil.isEmpty(likeText) ? null : "%" + likeText.trim() + "%";
    }

    public void setLikeText(String likeText) {
        this.likeText = likeText;
    }

    /**
     * 拥有的根菜单数量
     */
    private Integer rootMenuCount;

    public Integer getRootMenuCount() {
        return rootMenuCount;
    }

    public void setRootMenuCount(Integer rootMenuCount) {
        this.rootMenuCount = rootMenuCount;
    }
}
