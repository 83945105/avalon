package pub.avalonframework.cloud.gar.entity;

import pub.avalon.holygrail.response.beans.Status;

/**
 * @author baichao
 * @date 2018/7/24
 */
public interface AutObject {

    /**
     * 唯一标识符
     *
     * @return
     */
    String getId();

    /**
     * 是否禁用
     *
     * @return
     */
    boolean disabled();

    /**
     * 状态
     *
     * @return
     */
    default String getStatus() {
        if (this.disabled()) {
            return Status.DISABLED.name();
        }
        return Status.NORMAL.name();
    }
}
