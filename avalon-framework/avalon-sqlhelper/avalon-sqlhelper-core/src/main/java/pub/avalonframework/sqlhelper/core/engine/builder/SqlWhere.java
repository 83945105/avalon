package pub.avalonframework.sqlhelper.core.engine.builder;

import pub.avalonframework.sqlhelper.core.callback.WhereCallback;
import pub.avalonframework.sqlhelper.core.callback.WhereJoinCallback;
import pub.avalonframework.sqlhelper.core.data.SqlDataCrudProducer;
import pub.avalonframework.sqlhelper.core.engine.builder.beans.AbstractSqlWhereBean;
import pub.avalonframework.sqlhelper.core.engine.builder.beans.SqlWhereBean;
import pub.avalonframework.sqlhelper.core.engine.builder.beans.SqlWhereBeanJoin;
import pub.avalonframework.sqlhelper.core.engine.callback.WhereCallbackEngine;
import pub.avalonframework.sqlhelper.core.engine.helper.HelperWhereEngine;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author baichao
 */
public abstract class SqlWhere<TW extends WhereHelper<TW>> implements HelperWhereEngine<SqlWhere<TW>>, WhereCallbackEngine<TW, SqlWhere<TW>> {

    private TW whereHelper;
    private String tableAlias;

    {
        this.whereHelper = HelperManager.findWhereHelperClassFromAncestorsGenericType(this);
    }

    public SqlWhere() {
        this.tableAlias = this.whereHelper.getTableAlias();
    }

    public SqlWhere(String tableAlias) {
        this.whereHelper.setTableAlias(tableAlias);
        this.tableAlias = tableAlias;
    }

    private List<AbstractSqlWhereBean> sqlWhereBeans = new ArrayList<>(1);

    @Override
    public SqlWhere<TW> where(WhereHelper<?>... whereHelpers) {
        this.sqlWhereBeans.add(new SqlWhereBean<>(this.whereHelper, this.tableAlias).setWhereHelpers(whereHelpers));
        return this;
    }

    @Override
    public SqlWhere<TW> where(WhereCallback<TW> whereCallback) {
        this.sqlWhereBeans.add(new SqlWhereBean<>(this.whereHelper, this.tableAlias).setWhereCallback(whereCallback));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlWhere<TW> where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<TW, SW> whereJoinCallback) {
        this.sqlWhereBeans.add(new SqlWhereBeanJoin<>(this.whereHelper, tableHelperClass, tableAlias, whereJoinCallback));
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractSqlWhereBean> getSqlWhereBeans() {
        return sqlWhereBeans;
    }

    public void execute(SqlBuilderOptions sqlBuilderOptions, Supplier<SqlDataCrudProducer> supplier) {
        execute(this, sqlBuilderOptions, supplier);
    }

    public static <FW extends WhereHelper<FW>> void execute(SqlWhere<FW> sqlWhere, SqlBuilderOptions sqlBuilderOptions, Supplier<SqlDataCrudProducer> supplier) {
        if (supplier == null) {
            return;
        }
        SqlDataCrudProducer sqlDataCrudProducer = supplier.get();
        if (sqlDataCrudProducer == null) {
            return;
        }
        sqlWhere.getSqlWhereBeans().forEach(sqlWhereBean -> sqlWhereBean.execute(sqlBuilderOptions).forEach(sqlDataCrudProducer::addTableWhereDatum));
    }
}