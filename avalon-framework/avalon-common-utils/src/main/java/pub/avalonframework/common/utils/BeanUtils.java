package pub.avalonframework.common.utils;

import com.esotericsoftware.reflectasm.MethodAccess;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean utils.
 *
 * @author baichao
 */
public class BeanUtils {

    private BeanUtils() {
    }

    private static final Map<Class, MethodAccess> CLASS_METHOD_ACCESS_CACHE = new ConcurrentHashMap<>();

    private static final Map<Class, Map<String, BeanPropertyInfo>> CLASS_BEAN_PROPERTY_INFO_CACHE = new ConcurrentHashMap<>();

    private static MethodAccess getMethodAccess(Class clazz) {
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

    private static BeanPropertyInfo initPropertyInfo(Class clazz, Field field) {
        BeanPropertyInfo beanPropertyInfo = new BeanPropertyInfo();
        String name = field.getName();
        beanPropertyInfo.setName(name);
        beanPropertyInfo.setField(field);
        beanPropertyInfo.setType(field.getType());
        boolean isBoolean = field.getType() == boolean.class;
        beanPropertyInfo.setBoolean(isBoolean);
        String getterMethodName = getGetterMethodName(name, isBoolean);
        beanPropertyInfo.setGetterMethodName(getterMethodName);
        String setterMethodName = getSetterMethodName(name);
        beanPropertyInfo.setSetterMethodName(setterMethodName);
        return beanPropertyInfo;
    }

    private static BeanPropertyInfo getBeanPropertyInfo(Class clazz, String propertyName) {
        if (clazz == null || propertyName == null || propertyName.trim().length() == 0) {
            return null;
        }
        Map<String, BeanPropertyInfo> nameBeanPropertyInfoMap = CLASS_BEAN_PROPERTY_INFO_CACHE.get(clazz);
        if (nameBeanPropertyInfoMap == null) {
            nameBeanPropertyInfoMap = new ConcurrentHashMap<>(8);
            for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    nameBeanPropertyInfoMap.put(field.getName(), initPropertyInfo(clazz, field));
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
     * Get property.
     *
     * @param javaBean     The java bean.
     * @param propertyName The property name.
     * @return The property.
     */
    public static Object getProperty(Object javaBean, String propertyName) {
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
     * Set property.
     *
     * @param javaBean      The java bean.
     * @param propertyName  The property name.
     * @param propertyValue The property value.
     */
    @SuppressWarnings("unchecked")
    public static void setProperty(Object javaBean, String propertyName, Object propertyValue) {
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
        methodAccess.invoke(javaBean, beanPropertyInfo.getSetterMethodName(), propertyValue);
    }

    private static class BeanPropertyInfo {

        private String name;

        private Field field;

        private boolean isBoolean;

        private Class type;

        private String getterMethodName;

        private String setterMethodName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Field getField() {
            return field;
        }

        public void setField(Field field) {
            this.field = field;
        }

        public boolean isBoolean() {
            return isBoolean;
        }

        public void setBoolean(boolean aBoolean) {
            isBoolean = aBoolean;
        }

        public Class getType() {
            return type;
        }

        public void setType(Class type) {
            this.type = type;
        }

        public String getGetterMethodName() {
            return getterMethodName;
        }

        public void setGetterMethodName(String getterMethodName) {
            this.getterMethodName = getterMethodName;
        }

        public String getSetterMethodName() {
            return setterMethodName;
        }

        public void setSetterMethodName(String setterMethodName) {
            this.setterMethodName = setterMethodName;
        }
    }
}