package pub.avalonframework.cloud.gar.dc;

import pub.avalon.holygrail.utils.StringUtil;

/**
 * @author 白超
 */
public class AutObjectGet {

    private String likeText;

    public String getLikeText() {
        return StringUtil.isEmpty(likeText) ? null : "%" + likeText + "%";
    }

    public void setLikeText(String likeText) {
        this.likeText = likeText;
    }
}
