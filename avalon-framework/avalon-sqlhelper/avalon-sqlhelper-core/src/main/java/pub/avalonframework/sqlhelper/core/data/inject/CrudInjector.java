package pub.avalonframework.sqlhelper.core.data.inject;

/**
 * @author baichao
 */
public interface CrudInjector<R> extends InsertInjector<R>, SelectInjector<R>, UpdateInjector<R>, DeleteInjector<R> {

}