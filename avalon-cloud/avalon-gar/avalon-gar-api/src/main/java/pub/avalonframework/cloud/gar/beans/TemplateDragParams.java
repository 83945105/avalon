package pub.avalonframework.cloud.gar.beans;

/**
 * @author 白超
 * @date 2019/1/21
 */
public class TemplateDragParams {

    private String moduleId;

    private String dragSubModuleId;

    private String dropSubModuleId;

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

}
