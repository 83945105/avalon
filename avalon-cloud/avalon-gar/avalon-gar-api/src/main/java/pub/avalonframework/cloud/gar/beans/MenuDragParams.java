package pub.avalonframework.cloud.gar.beans;

/**
 * @author 白超
 * @date 2019/1/21
 */
public class MenuDragParams {

    private String moduleId;

    private String dragSubModuleId;

    private String dropSubModuleId;

    private String dragMenuGroupId;

    private String dropMenuGroupId;

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getDragSubModuleId() {
        return dragSubModuleId;
    }

    public void setDragSubModuleId(String dragSubModuleId) {
        this.dragSubModuleId = dragSubModuleId;
    }

    public String getDropSubModuleId() {
        return dropSubModuleId;
    }

    public void setDropSubModuleId(String dropSubModuleId) {
        this.dropSubModuleId = dropSubModuleId;
    }

    public String getDragMenuGroupId() {
        return dragMenuGroupId;
    }

    public void setDragMenuGroupId(String dragMenuGroupId) {
        this.dragMenuGroupId = dragMenuGroupId;
    }

    public String getDropMenuGroupId() {
        return dropMenuGroupId;
    }

    public void setDropMenuGroupId(String dropMenuGroupId) {
        this.dropMenuGroupId = dropMenuGroupId;
    }
}
