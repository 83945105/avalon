package pub.avalonframework.sqlhelper.core.sqlbuilder.template;

import pub.avalonframework.common.utils.BeanUtils;
import pub.avalonframework.sqlhelper.core.data.block.ColumnDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.TableColumnDataBlock;
import pub.avalonframework.sqlhelper.core.data.consume.CrudConsumer;
import pub.avalonframework.sqlhelper.core.exception.SqlException;
import pub.avalonframework.sqlhelper.core.helper.TableHelper;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.*;
import pub.avalonframework.sqlhelper.core.utils.HelperUtils;

import java.util.*;

/**
 * @author baichao
 */
public final class DefaultMySqlSqlBuilderTemplate implements MySqlSqlBuilderTemplate {

    private DataBlockBuilderTemplate dataBlockBuilderTemplate;

    @Override
    public void setDataBlockBuilderTemplate(DataBlockBuilderTemplate dataBlockBuilderTemplate) {
        this.dataBlockBuilderTemplate = dataBlockBuilderTemplate;
    }

    @Override
    public TableSqlBuilderResult buildCopyTable(CrudConsumer consumer, String targetTableName, boolean copyData) {
        String tableName = consumer.getTableMainDataBlock().getTableName();
        StringBuilder preparedStatementSql = new StringBuilder(128);
        preparedStatementSql.append("create table `")
                .append(targetTableName)
                .append("` like `")
                .append(tableName)
                .append("`");
        if (copyData) {
            preparedStatementSql.append("; insert into `")
                    .append(targetTableName)
                    .append("` select * from `")
                    .append(tableName)
                    .append("`");
            return FinalSqlBuilderResult.newInstance(preparedStatementSql.toString());
        }
        return FinalSqlBuilderResult.newInstance(preparedStatementSql.toString());
    }

    @Override
    public TableSqlBuilderResult buildDeleteTable(CrudConsumer consumer) {
        return FinalSqlBuilderResult.newInstance("drop table `" +
                consumer.getTableMainDataBlock().getTableName() +
                "`");
    }

    @Override
    public TableSqlBuilderResult buildRenameTable(CrudConsumer consumer, String newTableName) {
        return FinalSqlBuilderResult.newInstance("rename table `" +
                consumer.getTableMainDataBlock().getTableName() +
                "` to `" +
                newTableName +
                "`");
    }

    @Override
    public TableSqlBuilderResult buildIsTableExist(CrudConsumer consumer) {
        return FinalSqlBuilderResult.newInstance("select table_name from information_schema.TABLES where table_name = '" +
                consumer.getTableMainDataBlock().getTableName() +
                "' and table_schema = (select database())");
    }

    private List<ColumnDataBlock> getOnlyInsertTableDefaultColumnData(CrudConsumer consumer) {
        List<ColumnDataBlock> columnDataBlocks;
        List<TableColumnDataBlock> tableColumnDataBlocks = consumer.getInsertTableColumnDataBlocks();
        if (tableColumnDataBlocks == null || tableColumnDataBlocks.size() == 0) {
            return HelperUtils.defaultColumnData(consumer.getTableMainDataBlock().getTableHelperClass(), consumer.getTableMainDataBlock().getTableAlias());
        }
        if (tableColumnDataBlocks.size() > 1) {
            throw new MultiTableColumnException();
        }
        columnDataBlocks = tableColumnDataBlocks.iterator().next().getColumnDataBlocks();
        if (columnDataBlocks == null || columnDataBlocks.size() == 0) {
            return HelperUtils.defaultColumnData(consumer.getTableMainDataBlock().getTableHelperClass(), consumer.getTableMainDataBlock().getTableAlias());
        }
        return columnDataBlocks;
    }

    private List<ColumnDataBlock> getOnlyUpdateTableDefaultColumnData(CrudConsumer consumer) {
        List<ColumnDataBlock> columnDataBlocks;
        List<TableColumnDataBlock> tableColumnDataBlocks = consumer.getUpdateTableColumnDataBlocks();
        if (tableColumnDataBlocks == null || tableColumnDataBlocks.size() == 0) {
            return HelperUtils.defaultColumnData(consumer.getTableMainDataBlock().getTableHelperClass(), consumer.getTableMainDataBlock().getTableAlias());
        }
        if (tableColumnDataBlocks.size() > 1) {
            throw new MultiTableColumnException();
        }
        columnDataBlocks = tableColumnDataBlocks.iterator().next().getColumnDataBlocks();
        if (columnDataBlocks == null || columnDataBlocks.size() == 0) {
            return HelperUtils.defaultColumnData(consumer.getTableMainDataBlock().getTableHelperClass(), consumer.getTableMainDataBlock().getTableAlias());
        }
        return columnDataBlocks;
    }

    @Override
    public InsertSqlBuilderResult buildInsertArgs(CrudConsumer consumer, Object... args) {
        StringBuilder preparedStatementSql = new StringBuilder(512);
        List<Object> preparedStatementArgs = new ArrayList<>(args.length);
        preparedStatementSql.append("insert into `")
                .append(consumer.getTableMainDataBlock().getTableName())
                .append("` (");
        int i = 0;
        List<ColumnDataBlock> columnDataBlocks = getOnlyInsertTableDefaultColumnData(consumer);
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            preparedStatementArgs.add(args[i]);
            if (i++ > 0) {
                preparedStatementSql.append(",");
            }
            preparedStatementSql.append("`").append(columnDataBlock.getColumnName()).append("`");
        }
        preparedStatementSql.append(") values (");
        for (; i > 0; i--) {
            if (i == 1) {
                preparedStatementSql.append("?");
            } else {
                preparedStatementSql.append("?,");
            }
        }
        preparedStatementSql.append(")");
        return FinalSqlBuilderResult.newInstance(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public InsertSqlBuilderResult buildInsertJavaBean(CrudConsumer consumer, Object javaBean) {
        StringBuilder preparedStatementSql = new StringBuilder(512);
        List<Object> preparedStatementArgs = new ArrayList<>(64);
        preparedStatementSql.append("insert into `")
                .append(consumer.getTableMainDataBlock().getTableName())
                .append("` (");
        int i = 0;
        List<ColumnDataBlock> columnDataBlocks = getOnlyInsertTableDefaultColumnData(consumer);
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            if (i++ > 0) {
                preparedStatementSql.append(",");
            }
            preparedStatementSql.append("`").append(columnDataBlock.getColumnName()).append("`");
            preparedStatementArgs.add(BeanUtils.getPropertyValue(javaBean, columnDataBlock.getColumnAlias()));
        }
        preparedStatementSql.append(") values (");
        for (; i > 0; i--) {
            if (i == 1) {
                preparedStatementSql.append("?");
            } else {
                preparedStatementSql.append("?,");
            }
        }
        preparedStatementSql.append(")");
        return FinalSqlBuilderResult.newInstance(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public InsertSqlBuilderResult buildInsertJavaBeanSelective(CrudConsumer consumer, Object javaBean) {
        StringBuilder preparedStatementSql = new StringBuilder(512);
        List<Object> preparedStatementArgs = new ArrayList<>(64);
        preparedStatementSql.append("insert into `")
                .append(consumer.getTableMainDataBlock().getTableName())
                .append("` (");
        int i = 0;
        Object value;
        List<ColumnDataBlock> columnDataBlocks = getOnlyInsertTableDefaultColumnData(consumer);
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            value = BeanUtils.getPropertyValue(javaBean, columnDataBlock.getColumnAlias());
            if (value == null) {
                continue;
            }
            if (i++ > 0) {
                preparedStatementSql.append(",");
            }
            preparedStatementSql.append("`").append(columnDataBlock.getColumnName()).append("`");
            preparedStatementArgs.add(value);
        }
        preparedStatementSql.append(") values (");
        for (; i > 0; i--) {
            if (i == 1) {
                preparedStatementSql.append("?");
            } else {
                preparedStatementSql.append("?,");
            }
        }
        preparedStatementSql.append(")");
        return FinalSqlBuilderResult.newInstance(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public InsertSqlBuilderResult buildBatchInsertJavaBeans(CrudConsumer consumer, Collection<?> javaBeans) {
        StringBuilder preparedStatementSql = new StringBuilder(2048);
        List<Object> preparedStatementArgs = new ArrayList<>(64 * javaBeans.size());
        preparedStatementSql.append("insert into `")
                .append(consumer.getTableMainDataBlock().getTableName())
                .append("` (");
        int i = 0;
        List<ColumnDataBlock> columnDataBlocks = getOnlyInsertTableDefaultColumnData(consumer);
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            if (i++ > 0) {
                preparedStatementSql.append(",");
            }
            preparedStatementSql.append("`").append(columnDataBlock.getColumnName()).append("`");
        }
        preparedStatementSql.append(") values ");
        StringBuilder valPart = new StringBuilder(34).append("(");
        for (; i > 0; i--) {
            if (i == 1) {
                valPart.append("?)");
            } else {
                valPart.append("?,");
            }
        }
        for (Object javaBean : javaBeans) {
            if (i++ > 0) {
                preparedStatementSql.append(",");
            }
            preparedStatementSql.append(valPart.toString());
            for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
                preparedStatementArgs.add(BeanUtils.getPropertyValue(javaBean, columnDataBlock.getColumnAlias()));
            }
        }
        return FinalSqlBuilderResult.newInstance(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public DeleteSqlBuilderResult buildDelete(CrudConsumer consumer) {
        StringBuilder preparedStatementSql = new StringBuilder(512);
        List<Object> preparedStatementArgs = new ArrayList<>(64);
        String tableAlias = consumer.getTableMainDataBlock().getTableAlias();
        preparedStatementSql.append("delete ")
                .append(tableAlias)
                .append(" from `")
                .append(consumer.getTableMainDataBlock().getTableName())
                .append("` ")
                .append(tableAlias);
        FinalSqlBuilderResult sqlBuilderResult = FinalSqlBuilderResult.newInstance(preparedStatementSql.toString(), preparedStatementArgs);
        sqlBuilderResult.append(this.dataBlockBuilderTemplate.buildJoin(consumer));
        sqlBuilderResult.append(this.dataBlockBuilderTemplate.buildWhere(consumer));
        return sqlBuilderResult;
    }

    @Override
    public DeleteSqlBuilderResult buildDeleteByPrimaryKey(CrudConsumer consumer, Object primaryKeyValue) {
        StringBuilder preparedStatementSql = new StringBuilder(128);
        List<Object> preparedStatementArgs = Collections.singletonList(primaryKeyValue);
        preparedStatementSql.append("delete from `")
                .append(consumer.getTableMainDataBlock().getTableName())
                .append("` where `")
                .append(HelperUtils.defaultTableHelper(consumer.getTableMainDataBlock().getTableHelperClass()).getPrimaryKeyName())
                .append("` = ?");
        return FinalSqlBuilderResult.newInstance(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public DeleteSqlBuilderResult buildBatchDeleteByPrimaryKeys(CrudConsumer consumer, Object... primaryKeyValues) {
        StringBuilder preparedStatementSql = new StringBuilder(512);
        List<Object> preparedStatementArgs = Arrays.asList(primaryKeyValues);
        preparedStatementSql.append("delete from `")
                .append(consumer.getTableMainDataBlock().getTableName())
                .append("` where `")
                .append(HelperUtils.defaultTableHelper(consumer.getTableMainDataBlock().getTableHelperClass()).getPrimaryKeyName())
                .append("` in (");
        int size = primaryKeyValues.length;
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                preparedStatementSql.append(",");
            }
            preparedStatementSql.append("?");
        }
        preparedStatementSql.append(")");
        return FinalSqlBuilderResult.newInstance(preparedStatementSql.toString(), preparedStatementArgs);
    }

    @Override
    public UpdateSqlBuilderResult buildUpdateJavaBean(CrudConsumer consumer, Object javaBean) {
        FinalSqlBuilderResult sqlBuilderResult = FinalSqlBuilderResult.init(new StringBuilder(512), new ArrayList<>(64));
        String tableAlias = consumer.getTableMainDataBlock().getTableAlias();
        sqlBuilderResult.appendSqlPart("update `")
                .appendSqlPart(consumer.getTableMainDataBlock().getTableName())
                .appendSqlPart("` ")
                .appendSqlPart(tableAlias);
        sqlBuilderResult.append(this.dataBlockBuilderTemplate.buildJoin(consumer));
        sqlBuilderResult.appendSqlPart(" set ");
        int i = 0;
        List<ColumnDataBlock> columnDataBlocks = getOnlyUpdateTableDefaultColumnData(consumer);
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            if (i++ > 0) {
                sqlBuilderResult.appendSqlPart(",");
            }
            sqlBuilderResult.appendSqlPart(tableAlias).appendSqlPart(".`").appendSqlPart(columnDataBlock.getColumnName()).appendSqlPart("`").appendSqlPart(" = ?");
            sqlBuilderResult.appendArg(BeanUtils.getPropertyValue(javaBean, columnDataBlock.getColumnAlias()));
        }
        sqlBuilderResult.append(this.dataBlockBuilderTemplate.buildWhere(consumer));
        return sqlBuilderResult;
    }

    @Override
    public UpdateSqlBuilderResult buildUpdateJavaBeanSelective(CrudConsumer consumer, Object javaBean) {
        FinalSqlBuilderResult sqlBuilderResult = FinalSqlBuilderResult.init(new StringBuilder(512), new ArrayList<>(64));
        String tableAlias = consumer.getTableMainDataBlock().getTableAlias();
        sqlBuilderResult.appendSqlPart("update `")
                .appendSqlPart(consumer.getTableMainDataBlock().getTableName())
                .appendSqlPart("` ")
                .appendSqlPart(tableAlias);
        sqlBuilderResult.append(this.dataBlockBuilderTemplate.buildJoin(consumer));
        sqlBuilderResult.appendSqlPart(" set ");
        int i = 0;
        Object value;
        List<ColumnDataBlock> columnDataBlocks = getOnlyUpdateTableDefaultColumnData(consumer);
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            value = BeanUtils.getPropertyValue(javaBean, columnDataBlock.getColumnAlias());
            if (value == null) {
                continue;
            }
            if (i++ > 0) {
                sqlBuilderResult.appendSqlPart(",");
            }
            sqlBuilderResult.appendSqlPart(tableAlias).appendSqlPart(".`").appendSqlPart(columnDataBlock.getColumnName()).appendSqlPart("`").appendSqlPart(" = ?");
            sqlBuilderResult.appendArg(value);
        }
        sqlBuilderResult.append(this.dataBlockBuilderTemplate.buildWhere(consumer));
        return sqlBuilderResult;
    }

    @Override
    public UpdateSqlBuilderResult buildUpdateArgsByPrimaryKey(CrudConsumer consumer, Object primaryKeyValue, Object... args) {
        FinalSqlBuilderResult sqlBuilderResult = FinalSqlBuilderResult.init(new StringBuilder(512), new ArrayList<>(args.length + 1));
        sqlBuilderResult.appendSqlPart("update `")
                .appendSqlPart(consumer.getTableMainDataBlock().getTableName())
                .appendSqlPart("` set ");
        String primaryKeyName = HelperUtils.defaultTableHelper(consumer.getTableMainDataBlock().getTableHelperClass()).getPrimaryKeyName();
        int i = 0;
        List<ColumnDataBlock> columnDataBlocks = getOnlyUpdateTableDefaultColumnData(consumer);
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            if (columnDataBlock.getColumnName().equals(primaryKeyName)) {
                continue;
            }
            if (i++ > 0) {
                sqlBuilderResult.appendSqlPart(",");
            }
            sqlBuilderResult.appendSqlPart("`").appendSqlPart(columnDataBlock.getColumnName()).appendSqlPart("`").appendSqlPart(" = ?");
        }
        sqlBuilderResult.appendSqlPart(" where `")
                .appendSqlPart(primaryKeyName)
                .appendSqlPart("` = ?");
        sqlBuilderResult.appendArgs(Arrays.asList(args));
        sqlBuilderResult.appendArg(primaryKeyValue);
        return sqlBuilderResult;
    }

    @Override
    public UpdateSqlBuilderResult buildUpdateJavaBeanByPrimaryKey(CrudConsumer consumer, Object primaryKeyValue, Object javaBean) {
        FinalSqlBuilderResult sqlBuilderResult = FinalSqlBuilderResult.init(new StringBuilder(512), new ArrayList<>(65));
        sqlBuilderResult.appendSqlPart("update `")
                .appendSqlPart(consumer.getTableMainDataBlock().getTableName())
                .appendSqlPart("` set ");
        String primaryKeyName = HelperUtils.defaultTableHelper(consumer.getTableMainDataBlock().getTableHelperClass()).getPrimaryKeyName();
        int i = 0;
        List<ColumnDataBlock> columnDataBlocks = getOnlyUpdateTableDefaultColumnData(consumer);
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            if (columnDataBlock.getColumnName().equals(primaryKeyName)) {
                continue;
            }
            if (i++ > 0) {
                sqlBuilderResult.appendSqlPart(",");
            }
            sqlBuilderResult.appendSqlPart("`").appendSqlPart(columnDataBlock.getColumnName()).appendSqlPart("`").appendSqlPart(" = ?");
            sqlBuilderResult.appendArg(BeanUtils.getPropertyValue(javaBean, columnDataBlock.getColumnAlias()));
        }
        sqlBuilderResult.appendSqlPart(" where `")
                .appendSqlPart(primaryKeyName)
                .appendSqlPart("` = ?");
        sqlBuilderResult.appendArg(primaryKeyValue);
        return sqlBuilderResult;
    }

    @Override
    public UpdateSqlBuilderResult buildUpdateJavaBeanByPrimaryKeySelective(CrudConsumer consumer, Object primaryKeyValue, Object javaBean) {
        FinalSqlBuilderResult sqlBuilderResult = FinalSqlBuilderResult.init(new StringBuilder(512), new ArrayList<>(65));
        sqlBuilderResult.appendSqlPart("update `")
                .appendSqlPart(consumer.getTableMainDataBlock().getTableName())
                .appendSqlPart("` set ");
        String primaryKeyName = HelperUtils.defaultTableHelper(consumer.getTableMainDataBlock().getTableHelperClass()).getPrimaryKeyName();
        int i = 0;
        Object value;
        List<ColumnDataBlock> columnDataBlocks = getOnlyUpdateTableDefaultColumnData(consumer);
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            if (columnDataBlock.getColumnName().equals(primaryKeyName)) {
                continue;
            }
            value = BeanUtils.getPropertyValue(javaBean, columnDataBlock.getColumnAlias());
            if (value == null) {
                continue;
            }
            if (i++ > 0) {
                sqlBuilderResult.appendSqlPart(",");
            }
            sqlBuilderResult.appendSqlPart("`").appendSqlPart(columnDataBlock.getColumnName()).appendSqlPart("`").appendSqlPart(" = ?");
            sqlBuilderResult.appendArg(value);
        }
        sqlBuilderResult.appendSqlPart(" where `")
                .appendSqlPart(primaryKeyName)
                .appendSqlPart("` = ?");
        sqlBuilderResult.appendArg(primaryKeyValue);
        return sqlBuilderResult;
    }

    @Override
    public UpdateSqlBuilderResult buildBatchUpdateJavaBeansByPrimaryKeys(CrudConsumer consumer, Collection<?> javaBeans) {
        FinalSqlBuilderResult sqlBuilderResult = FinalSqlBuilderResult.init(new StringBuilder(2048), new ArrayList<>(128));
        String tableAlias = consumer.getTableMainDataBlock().getTableAlias();
        sqlBuilderResult.appendSqlPart("update `")
                .appendSqlPart(consumer.getTableMainDataBlock().getTableName())
                .appendSqlPart("` ")
                .appendSqlPart(tableAlias);
        sqlBuilderResult.append(this.dataBlockBuilderTemplate.buildJoin(consumer));
        sqlBuilderResult.appendSqlPart(" set ");
        int i = 0;
        TableHelper<?, ?, ?, ?, ?, ?, ?> tableHelper = HelperUtils.defaultTableHelper(consumer.getTableMainDataBlock().getTableHelperClass());
        String primaryKeyName = tableHelper.getPrimaryKeyName();
        String primaryKeyAlias = tableHelper.getPrimaryKeyAlias();
        Object keyValue;
        StringBuilder whenSql = new StringBuilder(128);
        StringBuilder inSql = new StringBuilder(32);
        List<Object> inArgs = new ArrayList<>(64);
        List<ColumnDataBlock> columnDataBlocks = getOnlyUpdateTableDefaultColumnData(consumer);
        // 遍历所有bean,计算出where条件的sql和参数
        // 计算出when条件sql
        for (Object javaBean : javaBeans) {
            if (javaBean instanceof Map) {
                keyValue = ((Map<?, ?>) javaBean).get(primaryKeyAlias);
                if (i++ > 0) {
                    inSql.append(",");
                }
                inSql.append("?");
                inArgs.add(keyValue);
            } else {
                keyValue = BeanUtils.getPropertyValue(javaBean, primaryKeyAlias);
                if (i++ > 0) {
                    inSql.append(",");
                }
                inSql.append("?");
                inArgs.add(keyValue);
            }
            if (keyValue == null) {
                throw new SqlException("the primaryKey value can not be null.");
            }
            whenSql.append("when '").append(keyValue.toString()).append("' then ? ");
        }
        i = 0;
        //遍历所有属性
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            // 主键略过
            if (columnDataBlock.getColumnAlias().equals(primaryKeyAlias)) {
                continue;
            }
            // 非主键计算sql
            if (i++ > 0) {
                sqlBuilderResult.appendSqlPart(",");
            }
            sqlBuilderResult.appendSqlPart(tableAlias)
                    .appendSqlPart(".`")
                    .appendSqlPart(columnDataBlock.getColumnName())
                    .appendSqlPart("`=case ")
                    .appendSqlPart(tableAlias)
                    .appendSqlPart(".`")
                    .appendSqlPart(primaryKeyName)
                    .appendSqlPart("` ")
                    .appendSqlPart(whenSql.toString())
                    .appendSqlPart(" end");
            // 非主键计算参数
            for (Object javaBean : javaBeans) {
                if (javaBean instanceof Map) {
                    sqlBuilderResult.appendArg(((Map<?, ?>) javaBean).get(columnDataBlock.getColumnAlias()));
                } else {
                    sqlBuilderResult.appendArg(BeanUtils.getPropertyValue(javaBean, columnDataBlock.getColumnAlias()));
                }
            }
        }
        //拼接上最后的where条件
        sqlBuilderResult.appendSqlPart(" where ")
                .appendSqlPart(tableAlias)
                .appendSqlPart(".`")
                .appendSqlPart(primaryKeyName)
                .appendSqlPart("` in (")
                .appendSqlPart(inSql.toString())
                .appendSqlPart(")");
        sqlBuilderResult.appendArgs(inArgs);
        return sqlBuilderResult;
    }

    @Override
    public UpdateSqlBuilderResult buildUpdateOrInsertJavaBeans(CrudConsumer consumer, Collection<?> javaBeans) {
        FinalSqlBuilderResult sqlBuilderResult = FinalSqlBuilderResult.init(new StringBuilder(2048), new ArrayList<>(128));
        sqlBuilderResult.appendSqlPart("insert into ")
                .appendSqlPart(consumer.getTableMainDataBlock().getTableName())
                .appendSqlPart(" (");
        int i = 0;
        StringBuilder onSql = new StringBuilder(64);
        List<ColumnDataBlock> columnDataBlocks = getOnlyUpdateTableDefaultColumnData(consumer);
        for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
            if (i++ > 0) {
                sqlBuilderResult.appendSqlPart(",");
                onSql.append(",");
            }
            sqlBuilderResult.appendSqlPart("`").appendSqlPart(columnDataBlock.getColumnName()).appendSqlPart("`");
            onSql.append("`").append(columnDataBlock.getColumnName()).append("` = values(`").append(columnDataBlock.getColumnName()).append("`)");
        }
        sqlBuilderResult.appendSqlPart(") values ");
        StringBuilder valueSql = new StringBuilder(32).append("(");
        for (; i > 0; i--) {
            if (i == 1) {
                valueSql.append("?)");
            } else {
                valueSql.append("?,");
            }
        }
        i = 0;
        for (Object javaBean : javaBeans) {
            if (i++ > 0) {
                sqlBuilderResult.appendSqlPart(",");
            }
            sqlBuilderResult.appendSqlPart(valueSql.toString());
            for (ColumnDataBlock columnDataBlock : columnDataBlocks) {
                sqlBuilderResult.appendArg(BeanUtils.getPropertyValue(javaBean, columnDataBlock.getColumnAlias()));
            }
        }
        sqlBuilderResult.appendSqlPart(" on duplicate key update ").appendSqlPart(onSql.toString());
        return sqlBuilderResult;
    }

    @Override
    public SelectSqlBuilderResult buildQuery(CrudConsumer consumer) {
        FinalSqlBuilderResult sqlBuilderResult = FinalSqlBuilderResult.init(new StringBuilder(1024), new ArrayList<>(32));
        sqlBuilderResult.appendSqlPart("select");
        sqlBuilderResult.append(this.dataBlockBuilderTemplate.buildSelectColumn(consumer));
        sqlBuilderResult.appendSqlPart(" from `")
                .appendSqlPart(consumer.getTableMainDataBlock().getTableName())
                .appendSqlPart("` ")
                .appendSqlPart(consumer.getTableMainDataBlock().getTableAlias());
        sqlBuilderResult.append(this.dataBlockBuilderTemplate.buildJoin(consumer));
        sqlBuilderResult.append(this.dataBlockBuilderTemplate.buildWhere(consumer));
        sqlBuilderResult.append(this.dataBlockBuilderTemplate.buildGroup(consumer));
        sqlBuilderResult.append(this.dataBlockBuilderTemplate.buildHaving(consumer));
        sqlBuilderResult.append(this.dataBlockBuilderTemplate.buildSort(consumer));
        sqlBuilderResult.append(this.dataBlockBuilderTemplate.buildLimit(consumer));
        return sqlBuilderResult;
    }

    @Override
    public SelectSqlBuilderResult buildQueryCount(CrudConsumer consumer) {
        FinalSqlBuilderResult sqlBuilderResult = FinalSqlBuilderResult.init(new StringBuilder(1024), new ArrayList<>(32));
        SqlBuilderResult group = this.dataBlockBuilderTemplate.buildGroup(consumer);
        SqlBuilderResult limit = this.dataBlockBuilderTemplate.buildLimit(consumer);
        boolean hasGroup = group.hasPreparedStatementSql();
        boolean hasLimit = limit.hasPreparedStatementSql();
        if (hasGroup || hasLimit) {
            sqlBuilderResult.appendSqlPart("select count(1) from (select ")
                    .appendSqlPart(consumer.getTableMainDataBlock().getTableAlias())
                    .appendSqlPart(".`")
                    .appendSqlPart(HelperUtils.defaultTableHelper(consumer.getTableMainDataBlock().getTableHelperClass()).getPrimaryKeyName())
                    .appendSqlPart("` from `");
        } else {
            sqlBuilderResult.appendSqlPart("select count(1) from `");
        }
        sqlBuilderResult.appendSqlPart(consumer.getTableMainDataBlock().getTableName())
                .appendSqlPart("` ")
                .appendSqlPart(consumer.getTableMainDataBlock().getTableAlias());
        sqlBuilderResult.append(this.dataBlockBuilderTemplate.buildJoin(consumer));
        sqlBuilderResult.append(this.dataBlockBuilderTemplate.buildWhere(consumer));
        if (hasGroup || hasLimit) {
            if (hasGroup) {
                sqlBuilderResult.append(group);
            }
            if (hasLimit) {
                sqlBuilderResult.append(limit);
            }
            sqlBuilderResult.appendSqlPart(") C");
        }
        return sqlBuilderResult;
    }

    @Override
    public SelectSqlBuilderResult buildQueryByPrimaryKey(CrudConsumer consumer, Object primaryKeyValue) {
        FinalSqlBuilderResult sqlBuilderResult = FinalSqlBuilderResult.init(new StringBuilder(128), Collections.singletonList(primaryKeyValue));
        sqlBuilderResult.appendSqlPart("select");
        sqlBuilderResult.append(this.dataBlockBuilderTemplate.buildSelectColumn(consumer));
        sqlBuilderResult.appendSqlPart(" from `")
                .appendSqlPart(consumer.getTableMainDataBlock().getTableName())
                .appendSqlPart("` ")
                .appendSqlPart(consumer.getTableMainDataBlock().getTableAlias())
                .appendSqlPart(" where ")
                .appendSqlPart(consumer.getTableMainDataBlock().getTableAlias())
                .appendSqlPart(".`")
                .appendSqlPart(HelperUtils.defaultTableHelper(consumer.getTableMainDataBlock().getTableHelperClass()).getPrimaryKeyName())
                .appendSqlPart("` = ?");
        return sqlBuilderResult;
    }
}