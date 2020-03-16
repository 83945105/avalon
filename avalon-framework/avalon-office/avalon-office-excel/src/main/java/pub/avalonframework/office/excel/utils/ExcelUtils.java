package pub.avalonframework.office.excel.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The excel utils.
 *
 * @author baichao
 */
public final class ExcelUtils {

    private ExcelUtils() {
    }

    private final static ConcurrentHashMap<Class<?>, List<Field>> CLASS_LIST_FIELD_CACHE = new ConcurrentHashMap<>();

    private final static ConcurrentHashMap<Class<?>, Method> CLASS_METHOD_CONCURRENT_CACHE = new ConcurrentHashMap<>();

    /**
     * 获取所有Field
     *
     * @param clazz 类
     * @return 属性集合
     */
    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = CLASS_LIST_FIELD_CACHE.get(clazz);
        if (fields == null) {
            fields = new ArrayList<>();
            for (Class<?> cla = clazz; cla != Object.class; cla = cla.getSuperclass()) {
                fields.addAll(Arrays.asList(cla.getDeclaredFields()));
            }
            CLASS_LIST_FIELD_CACHE.put(clazz, fields);
        }
        return fields;
    }

    /**
     * 获取对象指定方法
     *
     * @param clazz      类
     * @param methodName 方法名
     * @return 方法
     */
    public static Method getMethod(Class<?> clazz, String methodName) {
        Method method = CLASS_METHOD_CONCURRENT_CACHE.get(clazz);
        if (method == null) {
            for (Class<?> cla = clazz; cla != Object.class; cla = cla.getSuperclass()) {
                for (Method each : cla.getDeclaredMethods()) {
                    if (methodName.equals(each.getName())) {
                        CLASS_METHOD_CONCURRENT_CACHE.put(clazz, each);
                        return each;
                    }
                }
            }
        }
        return method;
    }

    private static final int MIN_CALCULATE_NUMBER = 2;

    /**
     * 根据数字计算出字符串
     * 规则同Excel列名,如一个集合区间为[A-Z],那么传入num数字得到的结果如下
     * 1  => A
     * 2  => B
     * 26 => Z
     * 27 => AA
     * 29 => AC
     *
     * @param num        数字
     * @param collection 集合
     * @return 字符串
     */
    public static String calculateStrByNumber(int num, Object[] collection) {
        if (collection.length < MIN_CALCULATE_NUMBER) {
            throw new RuntimeException("collection length must great and then 2");
        }
        return calculateStrByNumber(num, collection, new StringBuilder()).toString();
    }

    public static StringBuilder calculateStrByNumber(int num, Object[] collection, StringBuilder rs) {
        if (num < collection.length) {
            return rs.insert(0, collection[num - 1]);
        }
        int idx = num % collection.length - 1;
        rs.insert(0, collection[idx >= 0 ? idx : idx + collection.length]);
        num = (num - idx) / collection.length;
        return calculateStrByNumber(num, collection, rs);
    }

    /**
     * calculateStrByNumber的反向计算
     *
     * @param str        字符串
     * @param collection 集合
     * @return 数字
     */
    public static Long calculateNumByString(String str, Object[] collection) {
        if (collection.length < MIN_CALCULATE_NUMBER) {
            throw new RuntimeException("collection length must great and then 2");
        }
        String[] strs = str.trim().split("");
        long rs = 0L;
        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < collection.length; j++) {
                if (!strs[i].equals(collection[j])) {
                    continue;
                }
                if (i != strs.length - 1) {
                    rs += collection.length * (strs.length - i - 1) * (j + 1);
                } else {
                    rs += (j + 1);
                }
                break;
            }
        }
        return rs;
    }
}