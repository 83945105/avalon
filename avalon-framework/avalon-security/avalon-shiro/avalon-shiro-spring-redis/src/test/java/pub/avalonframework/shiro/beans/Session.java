package pub.avalonframework.shiro.beans;

import java.io.Serializable;

/**
 * Created by 白超 on 2019/11/18.
 */
public class Session implements Serializable {

    private String id;

    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}