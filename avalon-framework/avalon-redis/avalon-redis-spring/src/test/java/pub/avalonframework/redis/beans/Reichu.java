package pub.avalonframework.redis.beans;

/**
 * Created by 白超 on 2019/12/3.
 */
public class Reichu extends Pikachu {

    private final static String NAME = "雷丘";

    private String name = NAME;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        this.name = NAME;
    }
}