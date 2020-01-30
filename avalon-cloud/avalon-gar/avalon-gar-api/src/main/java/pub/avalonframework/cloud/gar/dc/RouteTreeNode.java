package pub.avalonframework.cloud.gar.dc;

import pub.avalon.holygrail.function.beans.TreeNode;
import pub.avalonframework.cloud.gar.entity.Route;

/**
 * @author baichao
 * @date 2019/1/19
 */
public class RouteTreeNode extends Route implements TreeNode<RouteTreeNode> {

    @Override
    public long getSortIndex() {
        return getIndex();
    }

    @Override
    public boolean isBrother(RouteTreeNode node) {
        return this.getParentId().equals(node.getParentId()) && this.getSubModuleId().equals(node.getSubModuleId());
    }


}
