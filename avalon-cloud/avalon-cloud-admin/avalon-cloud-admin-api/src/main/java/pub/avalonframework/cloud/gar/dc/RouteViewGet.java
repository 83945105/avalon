package pub.avalonframework.cloud.gar.dc;

import pub.avalon.holygrail.utils.StringUtil;
import pub.avalonframework.cloud.gar.entity.RouteView;
import pub.avalonframework.cloud.gar.entity.Template;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 白超
 */
public class RouteViewGet extends RouteView {

    private String likeText;

    public String getLikeText() {
        return StringUtil.isEmpty(likeText) ? null : "%" + likeText.trim() + "%";
    }

    public void setLikeText(String likeText) {
        this.likeText = likeText;
    }

    /**
     * 拥有的模板
     */
    private List<Template> templates;

    public List<Template> getTemplates() {
        return templates;
    }

    public void setTemplates(List<Template> templates) {
        this.templates = templates;
    }

    public void addTemplate(Template template) {
        if (this.templates == null) {
            this.templates = new ArrayList<>();
        }
        this.templates.add(template);
    }

}
