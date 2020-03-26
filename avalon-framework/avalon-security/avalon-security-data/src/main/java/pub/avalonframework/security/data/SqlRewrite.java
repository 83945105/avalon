package pub.avalonframework.security.data;

/**
 * @author baichao
 */
public interface SqlRewrite {

    String run();

    RuleStore getRuleStore();
}