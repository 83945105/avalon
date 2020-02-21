package pub.avalonframework.redis.beans;

/**
 * @author baichao
 * @date 2018/8/2
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