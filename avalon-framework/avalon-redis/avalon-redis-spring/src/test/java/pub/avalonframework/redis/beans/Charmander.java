package pub.avalonframework.redis.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by 白超 on 2019/12/3.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Charmander implements Pokemon {

    private final static String NAME = "小火龙";

    private String name = NAME;

    private Attribute[] attributes = new Attribute[]{Attribute.ELECTRICITY};

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = NAME;
    }

    @Override
    public Attribute[] getAttributes() {
        return attributes;
    }

    public void setAttributes(Attribute[] attributes) {
        this.attributes = attributes;
    }
}