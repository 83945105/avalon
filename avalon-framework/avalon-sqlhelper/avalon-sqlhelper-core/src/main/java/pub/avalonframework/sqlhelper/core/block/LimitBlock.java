package pub.avalonframework.sqlhelper.core.block;

/**
 * @author baichao
 */
public interface LimitBlock<R> {

    R limit(Long limit);

    R offset(Long offset);
}