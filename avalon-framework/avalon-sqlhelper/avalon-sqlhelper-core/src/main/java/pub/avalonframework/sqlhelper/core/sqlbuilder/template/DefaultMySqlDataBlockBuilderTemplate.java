package pub.avalonframework.sqlhelper.core.sqlbuilder.template;

import pub.avalonframework.sqlhelper.core.api.config.DataBlockBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.data.*;
import pub.avalonframework.sqlhelper.core.data.block.*;
import pub.avalonframework.sqlhelper.core.data.consume.CrudConsumer;
import pub.avalonframework.sqlhelper.core.exception.SqlException;
import pub.avalonframework.sqlhelper.core.expression.AndOr;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.FinalSqlBuilderResult;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.*;

/**
 * @author baichao
 */
public final class DefaultMySqlDataBlockBuilderTemplate implements MySqlDataBlockBuilderTemplate {

    @Override
    public SqlBuilderResult buildSelectColumn(CrudConsumer consumer) {
        List<TableColumnDataBlock> tableColumnDataBlocks = consumer.getSelectTableColumnDataBlocks();
        boolean hasC = tableColumnDataBlocks != null && tableColumnDataBlocks.size() != 0;
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
                this.appendSqlArgsWithColumnDataBlocks(sql, args, HelperManager.defaultColumnData(consumer.getTableMainDataBlock().getTableHelperClass(), consumer.getTableMainDataBlock().getTableAlias()));
            }
            if (selectAllColumnForJoinTable) {
                LinkedHashMap<String, TableJoinDataBlock> aliasTableJoinDataBlockMap = consumer.getAliasTableJoinDataBlockMap();
                if (aliasTableJoinDataBlockMap != null && aliasTableJoinDataBlockMap.size() > 0) {
                    for (Map.Entry<String, TableJoinDataBlock> entry : aliasTableJoinDataBlockMap.entrySet()) {
                        this.appendSqlArgsWithColumnDataBlocks(sql, args, HelperManager.defaultColumnData(entry.getValue().getTableHelperClass(), entry.getKey()));
                    }
                }
            }
            return FinalSqlBuilderResult.newInstance(sql.toString(), args);
        }
        this.appendSqlArgsWithTableColumnDataBlocks(sql, args, tableColumnDataBlocks);
        return FinalSqlBuilderResult.newInstance(sql.toString(), args);
    }

    @Override
    public SqlBuilderResult buildJoin(CrudConsumer consumer) {
        LinkedHashMap<String, TableJoinDataBlock> aliasTableJoinDataBlockMap = consumer.getAliasTableJoinDataBlockMap();
        if (aliasTableJoinDataBlockMap == null || aliasTableJoinDataBlockMap.size() == 0) {
            return FinalSqlBuilderResult.NONE;
        }
        StringBuilder sql = new StringBuilder(128);
        List<Object> args = new ArrayList<>(16);
        TableJoinDataBlock tableJoinDataBlock;
        for (Map.Entry<String, TableJoinDataBlock> entry : aliasTableJoinDataBlockMap.entrySet()) {
            tableJoinDataBlock = entry.getValue();
            switch (tableJoinDataBlock.getJoinType()) {
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
                    .append(tableJoinDataBlock.getTableName())
                    .append("` ")
                    .append(tableJoinDataBlock.getTableAlias());
            List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = tableJoinDataBlock.getTableOnDataBlock().getComparisonDataBlockLinkers();
            if (comparisonDataBlockLinkers != null && comparisonDataBlockLinkers.size() > 0) {
                sql.append(" on ");
                this.appendSqlArgsWithComparisonDataBlockLinkers(sql, args, comparisonDataBlockLinkers, AndOr.AND, false);
            }
        }
        return FinalSqlBuilderResult.newInstance(sql.toString(), args);
    }

    @Override
    public SqlBuilderResult buildWhere(CrudConsumer consumer) {
        List<TableWhereDataBlock> tableWhereDataBlocks = consumer.getTableWhereDataBlocks();
        if (tableWhereDataBlocks == null || tableWhereDataBlocks.size() == 0) {
            return FinalSqlBuilderResult.NONE;
        }
        StringBuilder sql = new StringBuilder(128);
        List<Object> args = new ArrayList<>(16);
        sql.append(" where ");
        int i = 0;
        for (TableWhereDataBlock tableWhereDataBlock : tableWhereDataBlocks) {
            if (i++ > 0) {
                sql.append(" and ");
            }
            this.appendSqlArgsWithComparisonDataBlockLinkers(sql, args, tableWhereDataBlock.getComparisonDataBlockLinkers(), AndOr.AND, tableWhereDataBlocks.size() > 1);
        }
        return FinalSqlBuilderResult.newInstance(sql.toString(), args);
    }

    @Override
    public SqlBuilderResult buildGroup(CrudConsumer consumer) {
        List<TableGroupDataBlock> tableGroupDataBlocks = consumer.getTableGroupDataBlocks();
        if (tableGroupDataBlocks == null || tableGroupDataBlocks.size() == 0) {
            return FinalSqlBuilderResult.NONE;
        }
        StringBuilder sql = new StringBuilder(32);
        sql.append(" group by ");
        int i = 0;
        List<GroupDataBlock> groupDataBlocks;
        for (TableGroupDataBlock tableGroupDataBlock : tableGroupDataBlocks) {
            groupDataBlocks = tableGroupDataBlock.getGroupDataBlocks();
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
        List<TableHavingDataBlock> tableHavingDataBlocks = consumer.getTableHavingDataBlocks();
        if (tableHavingDataBlocks == null || tableHavingDataBlocks.size() == 0) {
            return FinalSqlBuilderResult.NONE;
        }
        StringBuilder sql = new StringBuilder(128);
        List<Object> args = new ArrayList<>(16);
        sql.append(" having ");
        int i = 0;
        for (TableHavingDataBlock tableHavingDataBlock : tableHavingDataBlocks) {
            if (i++ > 0) {
                sql.append(" and ");
            }
            this.appendSqlArgsWithComparisonDataBlockLinkers(sql, args, tableHavingDataBlock.getComparisonDataBlockLinkers(), AndOr.AND, tableHavingDataBlocks.size() > 1);
        }
        return FinalSqlBuilderResult.newInstance(sql.toString(), args);
    }

    @Override
    public SqlBuilderResult buildSort(CrudConsumer consumer) {
        List<TableSortDataBlock> tableSortDataBlocks = consumer.getTableSortDataBlocks();
        if (tableSortDataBlocks == null || tableSortDataBlocks.size() == 0) {
            return FinalSqlBuilderResult.NONE;
        }
        StringBuilder sql = new StringBuilder(32);
        sql.append(" order by ");
        int i = 0;
        List<SortDataBlock> sortDataBlocks;
        for (TableSortDataBlock tableSortDataBlock : tableSortDataBlocks) {
            sortDataBlocks = tableSortDataBlock.getSortDataBlocks();
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

    private void appendSqlArgsWithColumnDataBlocks(StringBuilder sql, List<Object> args, List<ColumnDataBlock> columnDataBlocks) {
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
                        sql.append(columnValue);
                    } else if (columnValue instanceof Long) {
                        sql.append(columnValue);
                    } else if (columnValue instanceof Double) {
                        sql.append(columnValue);
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

    private void appendSqlArgsWithTableColumnDataBlocks(StringBuilder sql, List<Object> args, List<TableColumnDataBlock> tableColumnDataBlocks) {
        int i = 0;
        List<ColumnDataBlock> columnDataBlocks;
        for (TableColumnDataBlock tableColumnDataBlock : tableColumnDataBlocks) {
            columnDataBlocks = tableColumnDataBlock.getColumnDataBlocks();
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
                            sql.append(columnValue);
                        } else if (columnValue instanceof Long) {
                            sql.append(columnValue);
                        } else if (columnValue instanceof Double) {
                            sql.append(columnValue);
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

    private void appendSqlWithComparisonDataBlockType(StringBuilder sql, AbstractComparisonDataBlock<?> comparisonDataBlock) {
        Type type = comparisonDataBlock.getType();
        switch (type) {
            case DEFAULT:
                return;
            case SQL_PART:
                sql.append(comparisonDataBlock.getSqlPart());
                return;
            default:
                ExceptionUtils.unsupportedTypeException(type);
        }
    }

    private void appendSqlWithComparisonDataBlockColumnType(StringBuilder sql, AbstractComparisonDataBlock<?> comparisonDataBlock) {
        ColumnType columnType = comparisonDataBlock.getColumnType();
        switch (columnType) {
            case DEFAULT:
                sql.append(comparisonDataBlock.getTableAlias())
                        .append(".`")
                        .append(comparisonDataBlock.getColumnName())
                        .append("`");
                return;
            case HANDLER:
                sql.append(comparisonDataBlock.getColumnHandler().execute(comparisonDataBlock.getTableAlias() + ".`" + comparisonDataBlock.getColumnName() + "`"));
                return;
            default:
                ExceptionUtils.unsupportedColumnTypeException(columnType);
        }
    }

    private void appendSqlWithComparisonDataBlockComparisonType(StringBuilder sql, AbstractComparisonDataBlock<?> comparisonDataBlock) {
        ComparisonType comparisonType = comparisonDataBlock.getComparisonType();
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
    private void appendSqlWithComparisonDataBlockValueType(StringBuilder sql, List<Object> args, AbstractComparisonDataBlock<?> comparisonDataBlock) {
        ValueType valueType = comparisonDataBlock.getValueType();
        switch (valueType) {
            case NONE_VALUE:
                return;
            case SINGLE_VALUE:
                sql.append("?");
                args.add(comparisonDataBlock.getTargetValue());
                return;
            case PAIR_VALUE:
                sql.append("? and ?");
                args.add(comparisonDataBlock.getTargetValue());
                args.add(comparisonDataBlock.getTargetSecondValue());
                return;
            case MULTI_VALUE:
                Object value = comparisonDataBlock.getTargetValue();
                sql.append("(");
                int i = 0;
                if (value instanceof Collection) {
                    for (Object val : (Collection<?>) value) {
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
                SqlBuilderResult sqlBuilderResult = comparisonDataBlock.getTargetSubQuery();
                sql.append("(").append(sqlBuilderResult.getPreparedStatementSql()).append(")");
                args.addAll(sqlBuilderResult.getPreparedStatementArgs());
                return;
            case SQL_PART:
                sql.append(comparisonDataBlock.getTargetSqlPart());
                return;
            case SINGLE_DATA_BLOCK:
                AbstractDataBlock<?> targetDataBlock = comparisonDataBlock.getTargetDataBlock();
                sql.append(targetDataBlock.getTableAlias())
                        .append(".`")
                        .append(targetDataBlock.getColumnName())
                        .append("`");
                return;
            case PAIR_DATA_BLOCK:
                targetDataBlock = comparisonDataBlock.getTargetDataBlock();
                AbstractDataBlock<?> targetSecondDataBlock = comparisonDataBlock.getTargetSecondDataBlock();
                sql.append(targetDataBlock.getTableAlias())
                        .append(".`")
                        .append(targetDataBlock.getColumnName())
                        .append("` and ")
                        .append(targetSecondDataBlock.getTableAlias())
                        .append(".`")
                        .append(targetSecondDataBlock.getColumnName())
                        .append("`");
                return;
            case MULTI_DATA_BLOCK:
                List<AbstractDataBlock<?>> multiDataBlocks = comparisonDataBlock.getTargetMultiDataBlock();
                sql.append("(");
                int j = 0;
                for (AbstractDataBlock<?> multiDataBlock : multiDataBlocks) {
                    if (j++ > 0) {
                        sql.append(",");
                    }
                    sql.append(multiDataBlock.getTableAlias())
                            .append(".`")
                            .append(multiDataBlock.getColumnName())
                            .append("`");
                }
                sql.append(")");
                return;
            default:
                ExceptionUtils.unsupportedValueTypeException(valueType);
        }
    }

    private void appendSqlArgsWithComparisonDataBlocks(StringBuilder sql, List<Object> args, List<? extends AbstractComparisonDataBlock<?>> comparisonDataBlocks, AndOr andOr) {
        if (comparisonDataBlocks == null || comparisonDataBlocks.size() == 0) {
            return;
        }
        if (andOr == AndOr.OR && comparisonDataBlocks.size() > 1) {
            sql.append("(");
        }
        int i = 0;
        for (AbstractComparisonDataBlock<?> comparisonDataBlock : comparisonDataBlocks) {
            if (i++ > 0) {
                sql.append(" and ");
            }
            appendSqlWithComparisonDataBlockType(sql, comparisonDataBlock);
            appendSqlWithComparisonDataBlockColumnType(sql, comparisonDataBlock);
            appendSqlWithComparisonDataBlockComparisonType(sql, comparisonDataBlock);
            appendSqlWithComparisonDataBlockValueType(sql, args, comparisonDataBlock);
        }
        if (andOr == AndOr.OR && comparisonDataBlocks.size() > 1) {
            sql.append(")");
        }
    }

    private void appendSqlArgsWithComparisonDataBlockLinkers(StringBuilder sql, List<Object> args, List<ComparisonDataBlockLinker> comparisonDataBlockLinkers, AndOr andOr, boolean checkBrackets) {
        if (comparisonDataBlockLinkers == null || comparisonDataBlockLinkers.size() == 0) {
            return;
        }
        int length = sql.length();
        List<? extends AbstractComparisonDataBlock<?>> comparisonDataBlocks;
        int i = 0;
        boolean brackets = false;
        for (ComparisonDataBlockLinker comparisonDataBlockLinker : comparisonDataBlockLinkers) {
            comparisonDataBlocks = comparisonDataBlockLinker.getComparisonDataBlocks();
            List<ComparisonDataBlockLinker> childComparisonDataBlockLinkers = comparisonDataBlockLinker.getComparisonDataBlockLinkers();
            if (comparisonDataBlocks != null && comparisonDataBlocks.size() > 0) {
                switch (comparisonDataBlockLinker.getAndOr()) {
                    case AND:
                        if (i++ > 0) {
                            sql.append(" and ");
                        }
                        this.appendSqlArgsWithComparisonDataBlocks(sql, args, comparisonDataBlocks, AndOr.AND);
                        continue;
                    case OR:
                        if (i++ > 0) {
                            sql.append(" or ");
                            brackets = checkBrackets;
                        }
                        this.appendSqlArgsWithComparisonDataBlocks(sql, args, comparisonDataBlocks, AndOr.OR);
                        continue;
                    default:
                        //TODO
                        throw new SqlException("the LinkType is wrong.");
                }
            } else if (childComparisonDataBlockLinkers != null && childComparisonDataBlockLinkers.size() > 0) {
                switch (comparisonDataBlockLinker.getAndOr()) {
                    case AND:
                        if (i++ > 0) {
                            sql.append(" and ");
                        }
                        this.appendSqlArgsWithComparisonDataBlockLinkers(sql, args, childComparisonDataBlockLinkers, AndOr.AND, true);
                        continue;
                    case OR:
                        if (i++ > 0) {
                            sql.append(" or ");
                            brackets = checkBrackets;
                        }
                        this.appendSqlArgsWithComparisonDataBlockLinkers(sql, args, childComparisonDataBlockLinkers, AndOr.OR, true);
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
        brackets = brackets || andOr == AndOr.OR && i > 1;
        if (!brackets) {
            return;
        }
        sql.insert(length, "(").append(")");
    }
}