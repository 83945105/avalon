package pub.avalonframework.sqlhelper.core.data.inject;

/**
 * @author baichao
 */
public interface DeleteInjector<R> extends ConfigurationInjector<R>, JoinDataInjector<R>, OnDataInjector<R>, WhereDataInjector<R> {

}