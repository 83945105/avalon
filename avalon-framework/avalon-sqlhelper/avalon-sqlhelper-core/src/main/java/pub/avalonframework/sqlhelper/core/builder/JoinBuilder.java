package pub.avalonframework.sqlhelper.core.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.beans.JoinType;
import pub.avalonframework.sqlhelper.core.block.callback.CallbackJoinBlock;
import pub.avalonframework.sqlhelper.core.block.helper.HelperJoinBlock;
import pub.avalonframework.sqlhelper.core.builder.beans.AbstractJoinBuilderBean;
import pub.avalonframework.sqlhelper.core.builder.beans.JoinBuilderBean;
import pub.avalonframework.sqlhelper.core.callback.OnJoinCallback;
import pub.avalonframework.sqlhelper.core.data.JoinTableDatum;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public abstract class JoinBuilder<TO extends OnHelper<TO>> implements HelperJoinBlock<JoinBuilder<TO>>, CallbackJoinBlock<TO, JoinBuilder<TO>> {

    private TO onHelper;
    private String tableAlias;

    {
        this.onHelper = HelperManager.findOnHelperClassFromAncestorsGenericType(this);
    }

    public JoinBuilder() {
        this.tableAlias = this.onHelper.getTableAlias();
    }

    public JoinBuilder(String tableAlias) {
        this.onHelper.setTableAlias(tableAlias);
        this.tableAlias = tableAlias;
    }

    private List<AbstractJoinBuilderBean> joinBuilderBeans = new ArrayList<>(1);

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JoinBuilder<TO> join(JoinType joinType, String joinTableName, Class<S> joinTableHelperClass, String joinTableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        this.joinBuilderBeans.add(new JoinBuilderBean<>(this.onHelper, joinType, joinTableName, joinTableHelperClass, joinTableAlias, onJoinCallback));
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<AbstractJoinBuilderBean> getJoinBuilderBeans() {
        return joinBuilderBeans;
    }

    public List<JoinTableDatum> execute(SqlBuilderConfiguration sqlBuilderConfiguration) {
        return execute(this, sqlBuilderConfiguration);
    }

    public static <FO extends OnHelper<FO>> List<JoinTableDatum> execute(JoinBuilder<FO> joinBuilder, SqlBuilderConfiguration sqlBuilderConfiguration) {
        return joinBuilder.getJoinBuilderBeans().stream().map(sqlJoinBean -> sqlJoinBean.execute(sqlBuilderConfiguration)).collect(Collectors.toList());
    }
}