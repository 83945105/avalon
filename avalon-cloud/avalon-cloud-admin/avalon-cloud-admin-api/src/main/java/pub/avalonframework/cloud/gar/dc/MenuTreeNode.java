package pub.avalonframework.cloud.gar.dc;

import pub.avalon.holygrail.function.beans.TreeNode;
import pub.avalonframework.cloud.gar.entity.Menu;

/**
 * @author 白超
 */
public class MenuTreeNode extends Menu implements TreeNode<MenuTreeNode> {

    @Override
    public long getSortIndex() {
        return getIndex();
    }

    @Override
    public boolean isBrother(MenuTreeNode node) {
        return this.getParentId().equals(node.getParentId())
                && this.getSubModuleId().equals(node.getSubModuleId())
                && this.getMenuGroupId().equals(node.getMenuGroupId());
    }


}
