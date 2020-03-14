package pub.avalonframework.office.excel.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The excel utils.
 *
 * @author baichao
 */
public final class ExcelUtils {

    private ExcelUtils() {
    }

    /**
     * 获取所有Field
     *
     * @param clazz
     * @return
     */
    public static ArrayList<Field> getAllFields(Class<?> clazz) {
        ArrayList<Field> rs = new ArrayList<>();
        for (Class<?> cla = clazz; cla != Object.class; cla = cla.getSuperclass()) {
            rs.addAll(Arrays.asList(cla.getDeclaredFields()));
        }
        return rs;
    }

    /**
     * 获取对象指定方法
     *
     * @param clazz
     * @param methodName
     * @return
     */
    public static Method getMethod(Class<?> clazz, String methodName) {
        for (Class<?> cla = clazz; cla != Object.class; cla = cla.getSuperclass()) {
            for (Method method : cla.getDeclaredMethods()) {
                if (methodName.equals(method.getName())) {
                    return method;
                }
            }
        }
        return null;
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