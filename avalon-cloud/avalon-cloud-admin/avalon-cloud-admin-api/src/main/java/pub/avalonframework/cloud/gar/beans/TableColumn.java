package pub.avalonframework.cloud.gar.beans;

/**
 * 对象列
 *
 * @author 白超
 */
public class TableColumn {

    private String label;

    private String prop;

    private String width;

    public TableColumn() {
    }

    public TableColumn(String label, String prop) {
        this.label = label;
        this.prop = prop;
    }

    public TableColumn(String label, String prop, String width) {
        this.label = label;
        this.prop = prop;
        this.width = width;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }
}
