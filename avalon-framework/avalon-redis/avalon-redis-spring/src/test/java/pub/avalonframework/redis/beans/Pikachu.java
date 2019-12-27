package pub.avalonframework.redis.beans;

/**
 * Created by 白超 on 2019/11/27.
 */
public class Pikachu implements Pokemon {

    private final static String NAME = "皮卡丘";

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