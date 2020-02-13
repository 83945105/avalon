package pub.avalonframework.sqlhelper.core.helper;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.beans.LinkType;
import pub.avalonframework.sqlhelper.core.data.ComparisonSqlPartDataLinker;
import pub.avalonframework.sqlhelper.core.data.TableOnDatum;
import pub.avalonframework.sqlhelper.core.data.block.OnDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.builder.OnDataBlockBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public abstract class OnHelper<T extends OnHelper<T>> extends Helper {

    private OnDataBlockBuilder<T> onDataBlockBuilder;

    @SuppressWarnings("unchecked")
    public OnHelper(String tableAlias) {
        super(tableAlias);
        this.onDataBlockBuilder = new OnDataBlockBuilder<>(tableAlias, (T) this);
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        this.onDataBlockBuilder.setTableAlias(tableAlias);
    }

    protected OnDataBlockBuilder<T> apply(String templateTableName, String templateTableAlias, String sqlPart) {
        this.onDataBlockBuilder.accept(templateTableName, templateTableAlias, sqlPart);
        return this.onDataBlockBuilder;
    }

    protected OnDataBlockBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        this.onDataBlockBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
        return this.onDataBlockBuilder;
    }

    public List<OnDataBlock> takeoutSqlPartData() {
        return this.onDataBlockBuilder.takeoutSqlPartData();
    }

    public void setSqlBuilderConfiguration(SqlBuilderConfiguration sqlBuilderConfiguration) {
        this.onDataBlockBuilder.setSqlBuilderConfiguration(sqlBuilderConfiguration);
    }

    public TableOnDatum execute() {
        return execute(this);
    }

    public static TableOnDatum execute(OnHelper<?> onHelper) {
        List<OnDataBlock> onDataBlocks = onHelper.takeoutSqlPartData();
        if (onDataBlocks == null || onDataBlocks.size() == 0) {
            return null;
        }
        return new TableOnDatum(onHelper.getTableAlias(),
                Collections.singletonList(new ComparisonSqlPartDataLinker(LinkType.AND).setComparisonSqlPartData(onDataBlocks)));
    }

    public static List<TableOnDatum> execute(OnHelper<?>... onHelpers) {
        if (onHelpers == null || onHelpers.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(onHelpers).map(onHelper -> OnHelper.execute(onHelper)).collect(Collectors.toList());
    }
}