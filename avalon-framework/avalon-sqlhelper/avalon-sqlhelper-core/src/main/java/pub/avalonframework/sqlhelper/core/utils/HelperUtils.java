package pub.avalonframework.sqlhelper.core.utils;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import org.ehcache.CacheManager;
import org.springframework.beans.BeanUtils;
import pub.avalonframework.cache.core.Cache;
import pub.avalonframework.cache.core.mgt.EhCacheManager;
import pub.avalonframework.core.yaml.config.YamlEhCacheConfiguration;
import pub.avalonframework.core.yaml.swapper.impl.EhCacheConfigurationYamlSwapper;
import pub.avalonframework.sqlhelper.core.TableColumn;
import pub.avalonframework.sqlhelper.core.cache.ClassCacheManager;
import pub.avalonframework.sqlhelper.core.cache.core.CacheConfigurationBuilder;
import pub.avalonframework.sqlhelper.core.cache.core.CacheManagerBuilder;
import pub.avalonframework.sqlhelper.core.data.block.ColumnDataBlock;
import pub.avalonframework.sqlhelper.core.expression.builder.*;
import pub.avalonframework.sqlhelper.core.helper.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author baichao
 */
public final class HelperUtils {

    private HelperUtils() {
    }

    private final static String DEFAULT_CLASS_CONSTRUCTOR_ACCESS_CACHE = "SQLHELPER_CLASS_CONSTRUCTOR_ACCESS_CACHE";

    private final static Cache<Class, ConstructorAccess> CLASS_CONSTRUCTOR_ACCESS_CACHE;

    private final static String DEFAULT_TABLE_HELPER_CACHE_NAME = "SQLHELPER_DEFAULT_TABLE_HELPER_CACHE";

    private final static Cache<Class, TableHelper> DEFAULT_TABLE_HELPER_CACHE;

    private final static String DEFAULT_COLUMN_DATA_CACHE_NAME = "SQLHELPER_DEFAULT_COLUMN_DATA_CACHE";

    private final static Cache<Class, ColumnDataCache> DEFAULT_COLUMN_DATA_CACHE;


    static {
        EhCacheManager cacheManager = new EhCacheManager();
        CLASS_CONSTRUCTOR_ACCESS_CACHE = cacheManager.createCache(Class.class, ConstructorAccess.class, new EhCacheConfigurationYamlSwapper().swap(new YamlEhCacheConfiguration() {{
            setAlias(DEFAULT_CLASS_CONSTRUCTOR_ACCESS_CACHE);
        }}));
        DEFAULT_TABLE_HELPER_CACHE = cacheManager.createCache(Class.class, TableHelper.class, new EhCacheConfigurationYamlSwapper().swap(new YamlEhCacheConfiguration() {{
            setAlias(DEFAULT_TABLE_HELPER_CACHE_NAME);
        }}));
        DEFAULT_COLUMN_DATA_CACHE = cacheManager.createCache(Class.class, ColumnDataCache.class, new EhCacheConfigurationYamlSwapper().swap(new YamlEhCacheConfiguration() {{
            setAlias(DEFAULT_COLUMN_DATA_CACHE_NAME);
        }}));
    }

    @SuppressWarnings("unchecked")
    public static <T extends TableHelper<T, ?, ?, ?, ?, ?, ?>> T defaultTableHelper(Class<?> clazz) {
        TableHelper<T, ?, ?, ?, ?, ?, ?> singleTableHelper = DEFAULT_TABLE_HELPER_CACHE.get(clazz);
        if (singleTableHelper == null) {
            singleTableHelper = instantiateClass(clazz).getDefaultInstance();
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
    public static <T extends TableHelper<T, ?, ?, ?, ?, ?, ?>> List<ColumnDataBlock> defaultColumnData(Class<?> clazz, String tableAlias) {
        ColumnDataCache columnDataCache = DEFAULT_COLUMN_DATA_CACHE.get(clazz);
        if (columnDataCache == null) {
            columnDataCache = new ColumnDataCache();
        }
        ColumnDataBlockList columnDataBlockList = columnDataCache.get(tableAlias);
        if (columnDataBlockList == null) {
            Set<TableColumn> tableColumns = defaultTableHelper(clazz).getTableColumns();
            if (tableColumns == null) {
                return Collections.emptyList();
            }
            columnDataBlockList = new ColumnDataBlockList(tableColumns.size());
            for (TableColumn tableColumn : tableColumns) {
                ColumnDataBlock columnDataBlock = new ColumnDataBlock(tableColumn.getTableName(), tableColumn.getTableAlias(), tableColumn.getName(), tableColumn.getAlias(), tableColumn.getAlias());
                columnDataBlock.setTableAlias(tableAlias);
                columnDataBlockList.add(columnDataBlock);
            }
            columnDataCache.put(tableAlias, columnDataBlockList);
        }
        return columnDataBlockList;
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

    private final static class ColumnDataBlockList extends ArrayList<ColumnDataBlock> {
        private ColumnDataBlockList(int initialCapacity) {
            super(initialCapacity);
        }
    }

    private final static class ColumnDataCache implements Cache<String, ColumnDataBlockList> {

        private final static String COLUMN_DATA_CACHE_NAME = "COLUMN_DATA_CACHE";

        private final Cache<String, ColumnDataBlockList> columnDataCache;

        public ColumnDataCache(CacheManager cacheManager) {
            columnDataCache = CacheManagerBuilder.newCacheManagerBuilder().build()
                    .createCache(COLUMN_DATA_CACHE_NAME, CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, ColumnDataBlockList.class));
        }

        @Override
        public ColumnDataBlockList get(String key) {
            return columnDataCache.get(key);
        }

        @Override
        public void put(String key, ColumnDataBlockList value) {
            columnDataCache.put(key, value);
        }

        @Override
        public boolean containsKey(String key) {
            return columnDataCache.containsKey(key);
        }

        @Override
        public void remove(String key) {
            columnDataCache.remove(key);
        }

        @Override
        public Map<String, ColumnDataBlockList> getAll(Set<? extends String> keys) {
            return columnDataCache.getAll(keys);
        }

        @Override
        public void putAll(Map<? extends String, ? extends ColumnDataBlockList> entries) {
            columnDataCache.putAll(entries);
        }

        @Override
        public void removeAll(Set<? extends String> keys) {
            columnDataCache.removeAll(keys);
        }

        @Override
        public void clear() {
            columnDataCache.clear();
        }

        @Override
        public ColumnDataBlockList putIfAbsent(String key, ColumnDataBlockList value) {
            return columnDataCache.putIfAbsent(key, value);
        }

        @Override
        public boolean remove(String key, ColumnDataBlockList value) {
            return columnDataCache.remove(key, value);
        }

        @Override
        public ColumnDataBlockList replace(String key, ColumnDataBlockList value) {
            return columnDataCache.replace(key, value);
        }

        @Override
        public boolean replace(String key, ColumnDataBlockList oldValue, ColumnDataBlockList newValue) {
            return columnDataCache.replace(key, oldValue, newValue);
        }
    }
}