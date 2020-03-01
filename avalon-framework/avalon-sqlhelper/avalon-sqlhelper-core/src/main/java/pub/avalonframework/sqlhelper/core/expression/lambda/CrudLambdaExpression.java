package pub.avalonframework.sqlhelper.core.expression.lambda;

import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface CrudLambdaExpression<TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>, R> extends InsertLambdaExpression<TC, R>, SelectLambdaExpression<TC, TO, TW, TG, TH, TS, R>, UpdateLambdaExpression<TC, TO, TW, R>, DeleteLambdaExpression<TO, TW, R> {

}