package pub.avalonframework.sqlhelper.core.utils;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import pub.avalonframework.sqlhelper.core.TableColumn;
import pub.avalonframework.sqlhelper.core.data.block.ColumnDataBlock;
import pub.avalonframework.sqlhelper.core.expression.builder.*;
import pub.avalonframework.sqlhelper.core.helper.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author baichao
 */
public final class HelperUtils {

    private HelperUtils() {
    }

    private final static ConcurrentHashMap<Class, ConstructorAccess> CLASS_CONSTRUCTOR_ACCESS_CACHE = new ConcurrentHashMap<>();

    private final static ConcurrentHashMap<Class, TableHelper> DEFAULT_TABLE_HELPER_CACHE = new ConcurrentHashMap<>();

    private final static ConcurrentHashMap<Class, ColumnDataBlockCache> DEFAULT_COLUMN_DATA_CACHE = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static <T extends TableHelper<T, ?, ?, ?, ?, ?, ?>> T defaultTableHelper(Class<?> clazz) {
        TableHelper<T, ?, ?, ?, ?, ?, ?> singleTableHelper = DEFAULT_TABLE_HELPER_CACHE.get(clazz);
        if (singleTableHelper == null) {
            singleTableHelper = instantiateClass((Class<T>) clazz).getDefaultInstance();
            DEFAULT_TABLE_HELPER_CACHE.put(clazz, singleTableHelper);
        }
        return (T) singleTableHelper;
    }

    @SuppressWarnings("unchecked")
    public static <T> T instantiateClass(Class<T> clazz) {
        ConstructorAccess<T> constructorAccess = CLASS_CONSTRUCTOR_ACCESS_CACHE.get(clazz);
        if (constructorAccess == null) {
            constructorAccess = ConstructorAccess.get(clazz);
            CLASS_CONSTRUCTOR_ACCESS_CACHE.put(clazz, constructorAccess);
        }
        return constructorAccess.newInstance();
    }

    @SuppressWarnings("unchecked")
    public static List<ColumnDataBlock> defaultColumnData(Class<?> clazz, String tableAlias) {
        ColumnDataBlockCache columnDataBlockCache = DEFAULT_COLUMN_DATA_CACHE.get(clazz);
        if (columnDataBlockCache == null) {
            columnDataBlockCache = new ColumnDataBlockCache();
            DEFAULT_COLUMN_DATA_CACHE.put(clazz, columnDataBlockCache);
        }
        List<ColumnDataBlock> columnDataBlocks = columnDataBlockCache.get(tableAlias);
        if (columnDataBlocks == null) {
            Set<TableColumn> tableColumns = defaultTableHelper(clazz).getTableColumns();
            if (tableColumns == null) {
                columnDataBlocks = Collections.emptyList();
                columnDataBlockCache.put(tableAlias, columnDataBlocks);
                return columnDataBlocks;
            }
            columnDataBlocks = new ArrayList<>(tableColumns.size());
            for (TableColumn tableColumn : tableColumns) {
                ColumnDataBlock columnDataBlock = new ColumnDataBlock(tableColumn.getTableName(), tableColumn.getTableAlias(), tableColumn.getName(), tableColumn.getAlias(), tableColumn.getAlias());
                columnDataBlock.setTableAlias(tableAlias);
                columnDataBlocks.add(columnDataBlock);
            }
            columnDataBlockCache.put(tableAlias, columnDataBlocks);
        }
        return columnDataBlocks;
    }

    public static List<ColumnDataBlock> defaultColumnData(ColumnHelper<?> columnHelper) {
        return defaultColumnData(columnHelper.getDefaultTableHelper().getClass(), columnHelper.getTableAlias());
    }

    @SuppressWarnings("unchecked")
    public static <T extends ColumnHelper<T>> T findColumnHelperClassFromAncestorsGenericType(ColumnBuilder<T> columnBuilder) {
        return (T) instantiateClass(recursiveFindSortHelperClassFromAncestorsGenericType(columnBuilder.getClass(), ColumnHelper.class));
    }

    @SuppressWarnings("unchecked")
    public static <T extends ColumnHelper<T>> T findColumnHelperClassFromAncestorsGenericType(InsertColumnBuilder<T> insertColumnBuilder) {
        return (T) instantiateClass(recursiveFindSortHelperClassFromAncestorsGenericType(insertColumnBuilder.getClass(), ColumnHelper.class));
    }

    @SuppressWarnings("unchecked")
    public static <T extends ColumnHelper<T>> T findColumnHelperClassFromAncestorsGenericType(SelectColumnBuilder<T> selectColumnBuilder) {
        return (T) instantiateClass(recursiveFindSortHelperClassFromAncestorsGenericType(selectColumnBuilder.getClass(), ColumnHelper.class));
    }

    @SuppressWarnings("unchecked")
    public static <T extends ColumnHelper<T>> T findColumnHelperClassFromAncestorsGenericType(UpdateColumnBuilder<T> updateColumnBuilder) {
        return (T) instantiateClass(recursiveFindSortHelperClassFromAncestorsGenericType(updateColumnBuilder.getClass(), ColumnHelper.class));
    }

    @SuppressWarnings("unchecked")
    public static <T extends OnHelper<T>> T findOnHelperClassFromAncestorsGenericType(JoinBuilder<T> joinBuilder) {
        return (T) instantiateClass(recursiveFindSortHelperClassFromAncestorsGenericType(joinBuilder.getClass(), OnHelper.class));
    }

    @SuppressWarnings("unchecked")
    public static <T extends OnHelper<T>> T findOnHelperClassFromAncestorsGenericType(OnBuilder<T> onBuilder) {
        return (T) instantiateClass(recursiveFindSortHelperClassFromAncestorsGenericType(onBuilder.getClass(), OnHelper.class));
    }

    @SuppressWarnings("unchecked")
    public static <T extends WhereHelper<T>> T findWhereHelperClassFromAncestorsGenericType(WhereBuilder<T> whereBuilder) {
        return (T) instantiateClass(recursiveFindSortHelperClassFromAncestorsGenericType(whereBuilder.getClass(), WhereHelper.class));
    }

    @SuppressWarnings("unchecked")
    public static <T extends GroupHelper<T>> T findGroupHelperClassFromAncestorsGenericType(GroupBuilder<T> groupBuilder) {
        return (T) instantiateClass(recursiveFindSortHelperClassFromAncestorsGenericType(groupBuilder.getClass(), GroupHelper.class));
    }

    @SuppressWarnings("unchecked")
    public static <T extends HavingHelper<T>> T findHavingHelperClassFromAncestorsGenericType(HavingBuilder<T> havingBuilder) {
        return (T) instantiateClass(recursiveFindSortHelperClassFromAncestorsGenericType(havingBuilder.getClass(), HavingHelper.class));
    }

    @SuppressWarnings("unchecked")
    public static <T extends SortHelper<T>> T findSortHelperClassFromAncestorsGenericType(SortBuilder<T> sortBuilder) {
        return (T) instantiateClass(recursiveFindSortHelperClassFromAncestorsGenericType(sortBuilder.getClass(), SortHelper.class));
    }

    @SuppressWarnings("unchecked")
    private static <T> Class<T> recursiveFindSortHelperClassFromAncestorsGenericType(Class<?> clazz, Class<T> expectClass) {
        if (clazz == Object.class) {
            return null;
        }
        Type genType = clazz.getGenericSuperclass();
        if (genType instanceof ParameterizedType) {
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            for (Type param : params) {
                if (param instanceof Class) {
                    if (expectClass.isAssignableFrom((Class<?>) param)) {
                        return (Class<T>) param;
                    }
                }
            }

        }
        return recursiveFindSortHelperClassFromAncestorsGenericType(clazz.getSuperclass(), expectClass);
    }

    private final static class ColumnDataBlockCache extends ConcurrentHashMap<String, List<ColumnDataBlock>> {

    }
}