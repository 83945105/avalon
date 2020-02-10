package pub.avalonframework.sqlhelper.core.engine;

/**
 * @author baichao
 */
public interface LimitEngine<R> {

    R limit(Long limit);

    R offset(Long offset);
}