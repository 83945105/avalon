package pub.avalonframework.common.utils;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.TypeUtils;
import com.esotericsoftware.reflectasm.FieldAccess;
import com.esotericsoftware.reflectasm.MethodAccess;
import pub.avalonframework.common.beans.BeanPropertyInfo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Bean utils.
 *
 * @author baichao
 */
public class BeanUtils {

    private BeanUtils() {
    }

    private final static ConcurrentHashMap<Class, FieldAccess> CLASS_FIELD_ACCESS_CACHE = new ConcurrentHashMap<>(64);

    private final static ConcurrentHashMap<Class, MethodAccess> CLASS_METHOD_ACCESS_CACHE = new ConcurrentHashMap<>(64);

    private final static ConcurrentHashMap<Class, Map<String, BeanPropertyInfo>> CLASS_BEAN_PROPERTY_INFO_CACHE = new ConcurrentHashMap<>(64);

    public static List<String> getPropertyNames(Class clazz, boolean parentProperty) {
        if (clazz == null) {
            return null;
        }
        List<String> propertyNames = new ArrayList<>();
        Class parentClass = parentProperty ? Object.class : clazz.getSuperclass();
        for (Class cla = clazz; cla != parentClass; cla = cla.getSuperclass()) {
            Field[] fields = cla.getDeclaredFields();
            for (Field field : fields) {
                propertyNames.add(field.getName());
            }
        }
        return propertyNames;
    }

    public static List<String> getPropertyNames(Class clazz) {
        return getPropertyNames(clazz, false);
    }

    public static FieldAccess getFieldAccess(Class clazz) {
        if (clazz == null) {
            return null;
        }
        FieldAccess fieldAccess = CLASS_FIELD_ACCESS_CACHE.get(clazz);
        if (fieldAccess == null) {
            fieldAccess = FieldAccess.get(clazz);
            CLASS_FIELD_ACCESS_CACHE.put(clazz, fieldAccess);
        }
        return fieldAccess;
    }

    public static MethodAccess getMethodAccess(Class clazz) {
        if (clazz == null) {
            return null;
        }
        MethodAccess methodAccess = CLASS_METHOD_ACCESS_CACHE.get(clazz);
        if (methodAccess == null) {
            methodAccess = MethodAccess.get(clazz);
            CLASS_METHOD_ACCESS_CACHE.put(clazz, methodAccess);
        }
        return methodAccess;
    }

    public static BeanPropertyInfo getBeanPropertyInfo(Class clazz, String propertyName) {
        if (clazz == null || propertyName == null || propertyName.trim().length() == 0) {
            return null;
        }
        Map<String, BeanPropertyInfo> nameBeanPropertyInfoMap = CLASS_BEAN_PROPERTY_INFO_CACHE.get(clazz);
        if (nameBeanPropertyInfoMap == null) {
            nameBeanPropertyInfoMap = new ConcurrentHashMap<>(8);
            for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    nameBeanPropertyInfoMap.put(field.getName(), new BeanPropertyInfo(field));
                }
            }
            CLASS_BEAN_PROPERTY_INFO_CACHE.put(clazz, nameBeanPropertyInfoMap);
        }
        return nameBeanPropertyInfoMap.get(propertyName);
    }

    /**
     * Get getter method name.
     *
     * @param propertyName The property name.
     * @param isBoolean    Is boolean.
     * @return The getter name.
     */
    public static String getGetterMethodName(String propertyName, boolean isBoolean) {
        if (propertyName == null || "".equals(propertyName.trim())) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(propertyName);
        if (Character.isLowerCase(sb.charAt(0))) {
            if (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1))) {
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            }
        }
        if (isBoolean) {
            sb.insert(0, "is");
        } else {
            sb.insert(0, "get");
        }
        return sb.toString();
    }

    /**
     * Get setter method name.
     *
     * @param propertyName The property name.
     * @return The setter method name.
     */
    public static String getSetterMethodName(String propertyName) {
        if (propertyName == null || "".equals(propertyName.trim())) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(propertyName);
        if (Character.isLowerCase(sb.charAt(0))) {
            if (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1))) {
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            }
        }
        sb.insert(0, "set");
        return sb.toString();
    }

    /**
     * Get property value.
     *
     * @param javaBean     The java bean.
     * @param propertyName The property name.
     * @return The property.
     */
    public static Object getPropertyValue(Object javaBean, String propertyName) {
        if (javaBean instanceof Map) {
            return ((Map) javaBean).get(propertyName);
        }
        Class clazz = javaBean.getClass();
        MethodAccess methodAccess = getMethodAccess(clazz);
        BeanPropertyInfo beanPropertyInfo = getBeanPropertyInfo(clazz, propertyName);
        if (beanPropertyInfo == null) {
            return null;
        }
        return methodAccess.invoke(javaBean, beanPropertyInfo.getGetterMethodName());
    }

    /**
     * Set property value.
     *
     * @param javaBean      The java bean.
     * @param propertyName  The property name.
     * @param propertyValue The property value.
     */
    @SuppressWarnings("unchecked")
    public static void setPropertyValue(Object javaBean, String propertyName, Object propertyValue) {
        if (javaBean instanceof Map) {
            ((Map) javaBean).put(propertyName, propertyValue);
            return;
        }
        Class clazz = javaBean.getClass();
        MethodAccess methodAccess = getMethodAccess(clazz);
        BeanPropertyInfo beanPropertyInfo = getBeanPropertyInfo(clazz, propertyName);
        if (beanPropertyInfo == null) {
            return;
        }
        if (propertyValue.getClass() == beanPropertyInfo.getType()) {
            methodAccess.invoke(javaBean, beanPropertyInfo.getSetterMethodName(), propertyValue);
            return;
        }
        methodAccess.invoke(javaBean, beanPropertyInfo.getSetterMethodName(), TypeUtils.cast(propertyValue, beanPropertyInfo.getType(), ParserConfig.getGlobalInstance()));
    }

    public static Map<String, Object> getProperties(Object javaBean, boolean parentProperty, Function<Object, Boolean> filter) {
        List<String> propertyNames = getPropertyNames(javaBean.getClass(), parentProperty);
        if (propertyNames == null) {
            return null;
        }
        Map<String, Object> map = new LinkedHashMap<>(propertyNames.size());
        Object value;
        for (String propertyName : propertyNames) {
            value = getPropertyValue(javaBean, propertyName);
            if (filter.apply(value)) {
                map.put(propertyName, value);
            }
        }
        return map;
    }

    public static Map<String, Object> getProperties(Object javaBean) {
        return getProperties(javaBean, false, val -> true);
    }

    public static Map<String, Object> getProperties(Object javaBean, boolean parentProperty) {
        return getProperties(javaBean, parentProperty, val -> true);
    }

    public static Map<String, Object> getProperties(Object javaBean, Function<Object, Boolean> filter) {
        return getProperties(javaBean, false, filter);
    }
}