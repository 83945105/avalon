package pub.avalonframework.cloud.gar.entity;

public class Menu {
    private String id;

    private String moduleId;

    private String subModuleId;

    private String subModuleName;

    private String menuGroupId;

    private String name;

    private String value;

    private String iconName;

    private String parentId;

    private String parentIds;

    private String useTab;

    private String initOpenInTab;

    private String closableInTab;

    private String cacheInTab;

    private Long index;

    private String status;

    private String createTime;

    private String updateTime;

    private String deleteTime;

    private Long createTimeStamp;

    private Long updateTimeStamp;

    private Long deleteTimeStamp;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId == null ? null : moduleId.trim();
    }

    public String getSubModuleId() {
        return subModuleId;
    }

    public void setSubModuleId(String subModuleId) {
        this.subModuleId = subModuleId == null ? null : subModuleId.trim();
    }

    public String getSubModuleName() {
        return subModuleName;
    }

    public void setSubModuleName(String subModuleName) {
        this.subModuleName = subModuleName == null ? null : subModuleName.trim();
    }

    public String getMenuGroupId() {
        return menuGroupId;
    }

    public void setMenuGroupId(String menuGroupId) {
        this.menuGroupId = menuGroupId == null ? null : menuGroupId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName == null ? null : iconName.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds == null ? null : parentIds.trim();
    }

    public String getUseTab() {
        return useTab;
    }

    public void setUseTab(String useTab) {
        this.useTab = useTab == null ? null : useTab.trim();
    }

    public String getInitOpenInTab() {
        return initOpenInTab;
    }

    public void setInitOpenInTab(String initOpenInTab) {
        this.initOpenInTab = initOpenInTab == null ? null : initOpenInTab.trim();
    }

    public String getClosableInTab() {
        return closableInTab;
    }

    public void setClosableInTab(String closableInTab) {
        this.closableInTab = closableInTab == null ? null : closableInTab.trim();
    }

    public String getCacheInTab() {
        return cacheInTab;
    }

    public void setCacheInTab(String cacheInTab) {
        this.cacheInTab = cacheInTab == null ? null : cacheInTab.trim();
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}