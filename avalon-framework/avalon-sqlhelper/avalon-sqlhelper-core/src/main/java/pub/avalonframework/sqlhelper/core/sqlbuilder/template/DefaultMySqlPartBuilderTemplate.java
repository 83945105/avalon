package pub.avalonframework.sqlhelper.core.sqlbuilder.template;

import pub.avalonframework.sqlhelper.core.api.config.DataBlockBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.beans.LinkType;
import pub.avalonframework.sqlhelper.core.data.*;
import pub.avalonframework.sqlhelper.core.data.block.*;
import pub.avalonframework.sqlhelper.core.data.consume.CrudConsumer;
import pub.avalonframework.sqlhelper.core.exception.SqlException;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.FinalSqlBuilderResult;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.*;

/**
 * @author baichao
 */
public final class DefaultMySqlPartBuilderTemplate implements MySqlPartBuilderTemplate {

    @Override
    public SqlBuilderResult buildSelectColumn(CrudConsumer consumer) {
        List<TableColumnDataBlock> tableColumnData = consumer.getSelectTableColumnData();
        boolean hasC = tableColumnData != null && tableColumnData.size() != 0;
        StringBuilder sql = new StringBuilder(128);
        List<Object> args = new ArrayList<>(16);
        if (!hasC) {
            SqlhelperConfiguration configuration = consumer.getConfiguration();
            DataBlockBuilderConfiguration dataBlockBuilder = configuration.getSqlBuilder().getDataBlockBuilder();
            boolean selectAllColumnForMainTable = dataBlockBuilder.getSelectAllColumnForMainTable();
            boolean selectAllColumnForJoinTable = dataBlockBuilder.getSelectAllColumnForJoinTable();
            if (!selectAllColumnForMainTable && !selectAllColumnForJoinTable) {
                ExceptionUtils.selectColumnNullException();
            }
            if (selectAllColumnForMainTable) {
                this.appendColumnSqlArgs(sql, args, HelperManager.defaultColumnData(consumer.getMainTableDatum().getTableHelperClass(), consumer.getMainTableDatum().getTableAlias()));
            }
            if (selectAllColumnForJoinTable) {
                LinkedHashMap<String, TableJoinDataBlock> aliasJoinTableData = consumer.getAliasJoinTableData();
                if (aliasJoinTableData != null && aliasJoinTableData.size() > 0) {
                    for (Map.Entry<String, TableJoinDataBlock> entry : aliasJoinTableData.entrySet()) {
                        this.appendColumnSqlArgs(sql, args, HelperManager.defaultColumnData(entry.getValue().getTableHelperClass(), entry.getKey()));
                    }
                }
            }
            return FinalSqlBuilderResult.newInstance(sql.toString(), args);
        }
        this.appendTableColumnSqlArgs(sql, args, tableColumnData);
        return FinalSqlBuilderResult.newInstance(sql.toString(), args);
    }

    @Override
    public SqlBuilderResult buildJoin(CrudConsumer consumer) {
        LinkedHashMap<String, TableJoinDataBlock> joinTableDataAliasMap = consumer.getAliasJoinTableData();
        if (joinTableDataAliasMap == null || joinTableDataAliasMap.size() == 0) {
            return FinalSqlBuilderResult.NONE;
        }
        StringBuilder sql = new StringBuilder(128);
        List<Object> args = new ArrayList<>(16);
        TableJoinDataBlock joinTableDatum;
        for (Map.Entry<String, TableJoinDataBlock> entry : joinTableDataAliasMap.entrySet()) {
            joinTableDatum = entry.getValue();
            switch (joinTableDatum.getJoinType()) {
                case INNER:
                    sql.append(" inner join ");
                    break;
                case LEFT:
                    sql.append(" left join ");
                    break;
                case RIGHT:
                    sql.append(" right join ");
                    break;
                default:
                    continue;
            }
            sql.append("`")
                    .append(joinTableDatum.getTableName())
                    .append("` ")
                    .append(joinTableDatum.getTableAlias());
            List<ComparisonDataBlockLinker> comparisonSqlPartDataLinkers = joinTableDatum.getTableOnDatum().getComparisonSqlPartDataLinkers();
            if (comparisonSqlPartDataLinkers != null && comparisonSqlPartDataLinkers.size() > 0) {
                sql.append(" on ");
                this.appendComparisonSqlPartDataLinkersSqlArgs(sql, args, comparisonSqlPartDataLinkers, LinkType.AND, false);
            }
        }
        return FinalSqlBuilderResult.newInstance(sql.toString(), args);
    }

    @Override
    public SqlBuilderResult buildWhere(CrudConsumer consumer) {
        List<TableWhereDataBlock> tableWhereData = consumer.getTableWhereData();
        if (tableWhereData == null || tableWhereData.size() == 0) {
            return FinalSqlBuilderResult.NONE;
        }
        StringBuilder sql = new StringBuilder(128);
        List<Object> args = new ArrayList<>(16);
        sql.append(" where ");
        int i = 0;
        for (TableWhereDataBlock tableWhereDatum : tableWhereData) {
            if (i++ > 0) {
                sql.append(" and ");
            }
            this.appendComparisonSqlPartDataLinkersSqlArgs(sql, args, tableWhereDatum.getComparisonSqlPartDataLinkers(), LinkType.AND, tableWhereData.size() > 1);
        }
        return FinalSqlBuilderResult.newInstance(sql.toString(), args);
    }

    @Override
    public SqlBuilderResult buildGroup(CrudConsumer consumer) {
        List<TableGroupDataBlock> tableGroupData = consumer.getTableGroupData();
        if (tableGroupData == null || tableGroupData.size() == 0) {
            return FinalSqlBuilderResult.NONE;
        }
        StringBuilder sql = new StringBuilder(32);
        sql.append(" group by ");
        int i = 0;
        List<GroupDataBlock> groupDataBlocks;
        for (TableGroupDataBlock tableGroupDatum : tableGroupData) {
            groupDataBlocks = tableGroupDatum.getGroupDataBlocks();
            if (groupDataBlocks == null || groupDataBlocks.size() == 0) {
                continue;
            }
            for (GroupDataBlock columnName : groupDataBlocks) {
                if (i++ > 0) {
                    sql.append(",");
                }
                sql.append(columnName.getTableAlias())
                        .append(".`")
                        .append(columnName.getColumnName())
                        .append("`");
            }
        }
        return FinalSqlBuilderResult.newInstance(sql.toString());
    }

    @Override
    public SqlBuilderResult buildHaving(CrudConsumer consumer) {
        List<TableHavingDataBlock> tableHavingData = consumer.getTableHavingData();
        if (tableHavingData == null || tableHavingData.size() == 0) {
            return FinalSqlBuilderResult.NONE;
        }
        StringBuilder sql = new StringBuilder(128);
        List<Object> args = new ArrayList<>(16);
        sql.append(" having ");
        int i = 0;
        for (TableHavingDataBlock tableHavingDatum : tableHavingData) {
            if (i++ > 0) {
                sql.append(" and ");
            }
            this.appendComparisonSqlPartDataLinkersSqlArgs(sql, args, tableHavingDatum.getComparisonSqlPartDataLinkers(), LinkType.AND, tableHavingData.size() > 1);
        }
        return FinalSqlBuilderResult.newInstance(sql.toString(), args);
    }

    @Override
    public SqlBuilderResult buildSort(CrudConsumer consumer) {
        List<TableSortDataBlock> tableSortData = consumer.getTableSortData();
        if (tableSortData == null || tableSortData.size() == 0) {
            return FinalSqlBuilderResult.NONE;
        }
        StringBuilder sql = new StringBuilder(32);
        sql.append(" order by ");
        int i = 0;
        List<SortDataBlock> sortDataBlocks;
        for (TableSortDataBlock tableSortDatum : tableSortData) {
            sortDataBlocks = tableSortDatum.getSortDataBlocks();
            if (sortDataBlocks == null || sortDataBlocks.size() == 0) {
                continue;
            }
            for (SortDataBlock sortDataBlock : sortDataBlocks) {
                if (i++ > 0) {
                    sql.append(",");
                }
                sql.append(sortDataBlock.getTableAlias())
                        .append(".`")
                        .append(sortDataBlock.getColumnName())
                        .append("`");
                switch (sortDataBlock.getSortType()) {
                    case ASC:
                        sql.append(" asc");
                        continue;
                    case DESC:
                        sql.append(" desc");
                        continue;
                    default:
                        throw new SqlException("the SortType is wrong.");
                }
            }
        }
        return FinalSqlBuilderResult.newInstance(sql.toString());
    }

    @Override
    public SqlBuilderResult buildLimit(CrudConsumer consumer) {
        Long limit = consumer.getLimit();
        Long offset = consumer.getOffset();
        if (limit == null) {
            return FinalSqlBuilderResult.NONE;
        }
        if (offset == null) {
            return FinalSqlBuilderResult.newInstance(" limit ?", Collections.singletonList(limit));
        }
        return FinalSqlBuilderResult.newInstance(" limit ? offset ?", Arrays.asList(limit, offset));
    }

    private void appendColumnSqlArgs(StringBuilder sql, List<Object> args, List<ColumnDataBlock> columnDataBlocks) {
        int i = 0;
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            if (i++ > 0) {
                sql.append(",");
            } else {
                sql.append(" ");
            }
            switch (columnDataBlock.getType()) {
                case DEFAULT:
                    sql.append(columnDataBlock.getTableAlias())
                            .append(".`")
                            .append(columnDataBlock.getColumnName())
                            .append("` `")
                            .append(columnDataBlock.getColumnAlias())
                            .append("`");
                    continue;
                case VIRTUAL:
                    Object columnValue = columnDataBlock.getColumnValue();
                    if (columnValue == null) {
                        sql.append("null");
                    } else if (columnValue instanceof Integer) {
                        sql.append(String.valueOf(columnValue));
                    } else if (columnValue instanceof Long) {
                        sql.append(String.valueOf(columnValue));
                    } else if (columnValue instanceof Double) {
                        sql.append(String.valueOf(columnValue));
                    } else {
                        sql.append("'").append(columnValue).append("'");
                    }
                    sql.append(" `").append(columnDataBlock.getColumnAlias()).append("`");
                    continue;
                case SUB_QUERY:
                    sql.append("(");
                    SqlBuilderResult sqlBuilderResult = columnDataBlock.getSqlBuilderResult();
                    sql.append(sqlBuilderResult.getPreparedStatementSql()).append(") `").append(columnDataBlock.getColumnAlias()).append("`");
                    args.addAll(sqlBuilderResult.getPreparedStatementArgs());
                    continue;
                case HANDLER:
                    ColumnHandler[] columnHandlers = columnDataBlock.getColumnHandlers();
                    String columnSql = columnDataBlock.getTableAlias() + ".`" + columnDataBlock.getColumnName() + "`";
                    if (columnHandlers != null && columnHandlers.length > 0) {
                        for (ColumnHandler columnHandler : columnHandlers) {
                            columnSql = columnHandler.execute(columnSql);
                        }
                    }
                    sql.append(columnSql)
                            .append(" `")
                            .append(columnDataBlock.getColumnAlias())
                            .append("`");
                    continue;
                case SQL_PART:
                    sql.append(columnDataBlock.getSqlPart());
                    continue;
                default:
                    ExceptionUtils.columnTypeNotSupportException();
            }
        }
    }

    private void appendTableColumnSqlArgs(StringBuilder sql, List<Object> args, List<TableColumnDataBlock> tableColumnData) {
        int i = 0;
        List<ColumnDataBlock> columnDataBlocks;
        for (TableColumnDataBlock tableColumnDatum : tableColumnData) {
            columnDataBlocks = tableColumnDatum.getColumnDataBlocks();
            if (columnDataBlocks.size() == 0) {
                continue;
            }
            for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
                if (i++ > 0) {
                    sql.append(",");
                } else {
                    sql.append(" ");
                }
                switch (columnDataBlock.getType()) {
                    case DEFAULT:
                        sql.append(columnDataBlock.getTableAlias())
                                .append(".`")
                                .append(columnDataBlock.getColumnName())
                                .append("` `")
                                .append(columnDataBlock.getColumnAlias())
                                .append("`");
                        continue;
                    case VIRTUAL:
                        Object columnValue = columnDataBlock.getColumnValue();
                        if (columnValue == null) {
                            sql.append("null");
                        } else if (columnValue instanceof Integer) {
                            sql.append(String.valueOf(columnValue));
                        } else if (columnValue instanceof Long) {
                            sql.append(String.valueOf(columnValue));
                        } else if (columnValue instanceof Double) {
                            sql.append(String.valueOf(columnValue));
                        } else {
                            sql.append("'").append(columnValue).append("'");
                        }
                        sql.append(" `").append(columnDataBlock.getColumnAlias()).append("`");
                        continue;
                    case SUB_QUERY:
                        sql.append("(");
                        SqlBuilderResult sqlBuilderResult = columnDataBlock.getSqlBuilderResult();
                        sql.append(sqlBuilderResult.getPreparedStatementSql()).append(") `").append(columnDataBlock.getColumnAlias()).append("`");
                        args.addAll(sqlBuilderResult.getPreparedStatementArgs());
                        continue;
                    case HANDLER:
                        ColumnHandler[] columnHandlers = columnDataBlock.getColumnHandlers();
                        String columnSql = columnDataBlock.getTableAlias() + ".`" + columnDataBlock.getColumnName() + "`";
                        if (columnHandlers != null && columnHandlers.length > 0) {
                            for (ColumnHandler columnHandler : columnHandlers) {
                                columnSql = columnHandler.execute(columnSql);
                            }
                        }
                        sql.append(columnSql)
                                .append(" `")
                                .append(columnDataBlock.getColumnAlias())
                                .append("`");
                        continue;
                    case SQL_PART:
                        sql.append(columnDataBlock.getSqlPart());
                        continue;
                    default:
                        ExceptionUtils.columnTypeNotSupportException();
                }
            }
        }
    }

    private void appendType(StringBuilder sql, AbstractComparisonDataBlock dataBlock) {
        Type type = dataBlock.getType();
        switch (type) {
            case DEFAULT:
                return;
            case SQL_PART:
                sql.append(dataBlock.getSqlPart());
                return;
            default:
                ExceptionUtils.unsupportedTypeException(type);
        }
    }

    private void appendColumnType(StringBuilder sql, AbstractComparisonDataBlock dataBlock) {
        ColumnType columnType = dataBlock.getColumnType();
        switch (columnType) {
            case DEFAULT:
                sql.append(dataBlock.getTableAlias())
                        .append(".`")
                        .append(dataBlock.getColumnName())
                        .append("`");
                return;
            case HANDLER:
                sql.append(dataBlock.getColumnHandler().execute(dataBlock.getTableAlias() + ".`" + dataBlock.getColumnName() + "`"));
                return;
            default:
                ExceptionUtils.unsupportedColumnTypeException(columnType);
        }
    }

    private void appendComparisonType(StringBuilder sql, AbstractComparisonDataBlock dataBlock) {
        ComparisonType comparisonType = dataBlock.getComparisonType();
        switch (comparisonType) {
            case NONE:
                return;
            case IS_NULL:
                sql.append(" is null");
                return;
            case IS_NOT_NULL:
                sql.append(" is not null");
                return;
            case EQUAL:
                sql.append(" = ");
                return;
            case NOT_EQUAL:
                sql.append(" != ");
                return;
            case GREATER:
                sql.append(" > ");
                return;
            case GREATER_EQUAL:
                sql.append(" >= ");
                return;
            case LESS:
                sql.append(" < ");
                return;
            case LESS_EQUAL:
                sql.append(" <= ");
                return;
            case BETWEEN:
                sql.append(" between ");
                return;
            case LIKE:
                sql.append(" like ");
                return;
            case IN:
                sql.append(" in ");
                return;
            case NOT_IN:
                sql.append(" not in ");
                return;
            default:
                ExceptionUtils.unsupportedComparisonTypeException(comparisonType);
        }
    }

    @SuppressWarnings("unchecked")
    private void appendValueType(StringBuilder sql, List<Object> args, AbstractComparisonDataBlock dataBlock) {
        ValueType valueType = dataBlock.getValueType();
        switch (valueType) {
            case NONE_VALUE:
                return;
            case SINGLE_VALUE:
                sql.append("?");
                args.add(dataBlock.getTargetValue());
                return;
            case PAIR_VALUE:
                sql.append("? and ?");
                args.add(dataBlock.getTargetValue());
                args.add(dataBlock.getTargetSecondValue());
                return;
            case MULTI_VALUE:
                Object value = dataBlock.getTargetValue();
                sql.append("(");
                int i = 0;
                if (value instanceof Collection) {
                    for (Object val : (Collection) value) {
                        if (i++ > 0) {
                            sql.append(",");
                        }
                        sql.append("?");
                        args.add(val);
                    }
                } else if (value.getClass().isArray()) {
                    for (Object val : (Object[]) value) {
                        if (i++ > 0) {
                            sql.append(",");
                        }
                        sql.append("?");
                        args.add(val);
                    }
                } else {
                    ExceptionUtils.errorValueTypeException(value);
                }
                sql.append(")");
                return;
            case SUB_QUERY:
                SqlBuilderResult sqlBuilderResult = dataBlock.getTargetSubQuery();
                sql.append("(").append(sqlBuilderResult.getPreparedStatementSql()).append(")");
                args.addAll(sqlBuilderResult.getPreparedStatementArgs());
                return;
            case SQL_PART:
                sql.append(dataBlock.getTargetSqlPart());
                return;
            case SINGLE_SQL_PART_DATUM:
                AbstractDataBlock targetSqlPartDatum = dataBlock.getTargetSqlPartDatum();
                sql.append(targetSqlPartDatum.getTableAlias())
                        .append(".`")
                        .append(targetSqlPartDatum.getColumnName())
                        .append("`");
                return;
            case PAIR_SQL_PART_DATUM:
                targetSqlPartDatum = dataBlock.getTargetSqlPartDatum();
                AbstractDataBlock targetSecondSqlPartDatum = dataBlock.getTargetSecondSqlPartDatum();
                sql.append(targetSqlPartDatum.getTableAlias())
                        .append(".`")
                        .append(targetSqlPartDatum.getColumnName())
                        .append("` and ")
                        .append(targetSecondSqlPartDatum.getTableAlias())
                        .append(".`")
                        .append(targetSecondSqlPartDatum.getColumnName())
                        .append("`");
                return;
            case MULTI_SQL_PART_DATUM:
                List<AbstractDataBlock> dataBlocks = dataBlock.getTargetMultiSqlPartDatum();
                sql.append("(");
                int j = 0;
                for (AbstractDataBlock db : dataBlocks) {
                    if (j++ > 0) {
                        sql.append(",");
                    }
                    sql.append(db.getTableAlias())
                            .append(".`")
                            .append(db.getColumnName())
                            .append("`");
                }
                sql.append(")");
                return;
            default:
                ExceptionUtils.unsupportedValueTypeException(valueType);
        }
    }

    private void appendSqlPartData(StringBuilder sql, List<Object> args, List<? extends AbstractComparisonDataBlock> dataBlocks, LinkType linkType) {
        if (dataBlocks == null || dataBlocks.size() == 0) {
            return;
        }
        if (linkType == LinkType.OR && dataBlocks.size() > 1) {
            sql.append("(");
        }
        int i = 0;
        for (AbstractComparisonDataBlock dataBlock : dataBlocks) {
            if (i++ > 0) {
                sql.append(" and ");
            }
            appendType(sql, dataBlock);
            appendColumnType(sql, dataBlock);
            appendComparisonType(sql, dataBlock);
            appendValueType(sql, args, dataBlock);
        }
        if (linkType == LinkType.OR && dataBlocks.size() > 1) {
            sql.append(")");
        }
    }

    private void appendComparisonSqlPartDataLinkersSqlArgs(StringBuilder sql, List<Object> args, List<ComparisonDataBlockLinker> onDataLinkers, LinkType linkType, boolean checkBrackets) {
        if (onDataLinkers == null || onDataLinkers.size() == 0) {
            return;
        }
        int length = sql.length();
        List<? extends AbstractComparisonDataBlock> comparisonSqlPartData;
        int i = 0;
        boolean brackets = false;
        for (ComparisonDataBlockLinker comparisonSqlPartDataLinker : onDataLinkers) {
            comparisonSqlPartData = comparisonSqlPartDataLinker.getComparisonSqlPartData();
            List<ComparisonDataBlockLinker> childComparisonSqlPartDataLinkers = comparisonSqlPartDataLinker.getComparisonSqlPartDataLinkers();
            if (comparisonSqlPartData != null && comparisonSqlPartData.size() > 0) {
                switch (comparisonSqlPartDataLinker.getLinkType()) {
                    case AND:
                        if (i++ > 0) {
                            sql.append(" and ");
                        }
                        this.appendSqlPartData(sql, args, comparisonSqlPartData, LinkType.AND);
                        continue;
                    case OR:
                        if (i++ > 0) {
                            sql.append(" or ");
                            brackets = checkBrackets;
                        }
                        this.appendSqlPartData(sql, args, comparisonSqlPartData, LinkType.OR);
                        continue;
                    default:
                        //TODO
                        throw new SqlException("the LinkType is wrong.");
                }
            } else if (childComparisonSqlPartDataLinkers != null && childComparisonSqlPartDataLinkers.size() > 0) {
                switch (comparisonSqlPartDataLinker.getLinkType()) {
                    case AND:
                        if (i++ > 0) {
                            sql.append(" and ");
                        }
                        this.appendComparisonSqlPartDataLinkersSqlArgs(sql, args, childComparisonSqlPartDataLinkers, LinkType.AND, true);
                        continue;
                    case OR:
                        if (i++ > 0) {
                            sql.append(" or ");
                            brackets = checkBrackets;
                        }
                        this.appendComparisonSqlPartDataLinkersSqlArgs(sql, args, childComparisonSqlPartDataLinkers, LinkType.OR, true);
                        continue;
                    default:
                        //TODO
                        throw new SqlException("the LinkType is wrong.");
                }
            }
        }
        if (!checkBrackets) {
            return;
        }
        brackets = brackets || linkType == LinkType.OR && i > 1;
        if (!brackets) {
            return;
        }
        sql.insert(length, "(").append(")");
    }
}