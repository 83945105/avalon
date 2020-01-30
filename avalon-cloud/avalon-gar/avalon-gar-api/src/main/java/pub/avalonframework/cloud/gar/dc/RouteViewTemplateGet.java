package pub.avalonframework.cloud.gar.dc;

import pub.avalonframework.cloud.gar.entity.AutRole;
import pub.avalonframework.cloud.gar.entity.RouteViewTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 * @date 2018/12/7
 */
public class RouteViewTemplateGet extends RouteViewTemplate {

    /**
     * 模板所属子模块ID
     */
    private String templateSubModuleId;

    public String getTemplateSubModuleId() {
        return templateSubModuleId;
    }

    public void setTemplateSubModuleId(String templateSubModuleId) {
        this.templateSubModuleId = templateSubModuleId;
    }

    /**
     * 模板类型
     */
    private String templateType;

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    /**
     * 模板描述
     */
    private String templateDescription;

    public String getTemplateDescription() {
        return templateDescription;
    }

    public void setTemplateDescription(String templateDescription) {
        this.templateDescription = templateDescription;
    }

    /**
     * 授予的角色
     */
    private List<AutRole> roles;

    public List<AutRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AutRole> roles) {
        this.roles = roles;
    }

    public void addRole(AutRole role) {
        if (this.roles == null) {
            this.roles = new ArrayList<>();
        }
        this.roles.add(role);
    }
}
