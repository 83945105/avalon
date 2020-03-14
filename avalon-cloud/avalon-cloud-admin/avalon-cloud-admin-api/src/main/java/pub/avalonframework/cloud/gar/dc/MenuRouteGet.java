package pub.avalonframework.cloud.gar.dc;

import pub.avalon.holygrail.utils.StringUtil;
import pub.avalonframework.cloud.gar.entity.MenuRoute;

/**
 * @author 白超
 */
public class MenuRouteGet extends MenuRoute {

    private String likeText;

    public String getLikeText() {
        return StringUtil.isEmpty(likeText) ? null : "%" + likeText.trim() + "%";
    }

    public void setLikeText(String likeText) {
        this.likeText = likeText;
    }

}
