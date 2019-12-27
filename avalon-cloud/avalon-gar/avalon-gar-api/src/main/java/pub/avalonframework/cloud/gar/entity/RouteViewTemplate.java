package pub.avalonframework.cloud.gar.entity;

public class RouteViewTemplate {
    private Long id;

    private String moduleId;

    private String routeId;

    private String routeName;

    private String routeValue;

    private String routePath;

    private String routeViewId;

    private String routeViewName;

    private String routeViewValue;

    private String templateId;

    private String templateName;

    private String templateValue;

    private String status;

    private String createTime;

    private String updateTime;

    private String deleteTime;

    private Long createTimeStamp;

    private Long updateTimeStamp;

    private Long deleteTimeStamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId == null ? null : moduleId.trim();
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId == null ? null : routeId.trim();
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName == null ? null : routeName.trim();
    }

    public String getRouteValue() {
        return routeValue;
    }

    public void setRouteValue(String routeValue) {
        this.routeValue = routeValue == null ? null : routeValue.trim();
    }

    public String getRoutePath() {
        return routePath;
    }

    public void setRoutePath(String routePath) {
        this.routePath = routePath == null ? null : routePath.trim();
    }

    public String getRouteViewId() {
        return routeViewId;
    }

    public void setRouteViewId(String routeViewId) {
        this.routeViewId = routeViewId == null ? null : routeViewId.trim();
    }

    public String getRouteViewName() {
        return routeViewName;
    }

    public void setRouteViewName(String routeViewName) {
        this.routeViewName = routeViewName == null ? null : routeViewName.trim();
    }

    public String getRouteViewValue() {
        return routeViewValue;
    }

    public void setRouteViewValue(String routeViewValue) {
        this.routeViewValue = routeViewValue == null ? null : routeViewValue.trim();
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId == null ? null : templateId.trim();
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public String getTemplateValue() {
        return templateValue;
    }

    public void setTemplateValue(String templateValue) {
        this.templateValue = templateValue == null ? null : templateValue.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime == null ? null : deleteTime.trim();
    }

    public Long getCreateTimeStamp() {
        return createTimeStamp;
    }

    public void setCreateTimeStamp(Long createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }

    public Long getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public void setUpdateTimeStamp(Long updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }

    public Long getDeleteTimeStamp() {
        return deleteTimeStamp;
    }

    public void setDeleteTimeStamp(Long deleteTimeStamp) {
        this.deleteTimeStamp = deleteTimeStamp;
    }
}