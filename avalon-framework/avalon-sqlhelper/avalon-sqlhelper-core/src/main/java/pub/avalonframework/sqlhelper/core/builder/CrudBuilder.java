package pub.avalonframework.sqlhelper.core.builder;

/**
 * @author baichao
 */
public interface CrudBuilder<R> extends InsertBuilder<R>, SelectBuilder<R>, UpdateBuilder<R>, DeleteBuilder<R> {

}