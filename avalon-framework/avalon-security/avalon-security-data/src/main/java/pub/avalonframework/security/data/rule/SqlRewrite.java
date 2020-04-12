package pub.avalonframework.security.data.rule;

/**
 * @author baichao
 */
public interface SqlRewrite {

    String run();

    RuleStore getRuleStore();
}