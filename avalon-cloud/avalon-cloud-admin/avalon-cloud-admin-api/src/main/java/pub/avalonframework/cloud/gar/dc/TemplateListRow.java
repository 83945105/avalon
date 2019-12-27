package pub.avalonframework.cloud.gar.dc;

import pub.avalon.holygrail.function.beans.ListRow;
import pub.avalonframework.cloud.gar.entity.Template;

/**
 * @author 白超
 * @date 2019/5/9
 */
public class TemplateListRow extends Template implements ListRow {
    @Override
    public long getSortIndex() {
        return getIndex();
    }
}
