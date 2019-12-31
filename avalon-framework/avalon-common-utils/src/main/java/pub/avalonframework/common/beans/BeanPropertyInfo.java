package pub.avalonframework.common.beans;

import pub.avalonframework.common.utils.BeanUtils;

import java.lang.reflect.Field;

/**
 * @author baichao
 */
public final class BeanPropertyInfo {

    private String name;

    private Field field;

    private boolean isBoolean;

    private Class type;

    private String getterMethodName;

    private String setterMethodName;

    public BeanPropertyInfo(Field field) {
        this.name = field.getName();
        this.field = field;
        this.isBoolean = field.getType() == boolean.class;
        this.type = field.getType();
        this.getterMethodName = BeanUtils.getGetterMethodName(this.name, this.isBoolean);
        this.setterMethodName = BeanUtils.getSetterMethodName(this.name);
    }

    public Object getValue(Object javaBean) {
        return BeanUtils.getProperty(javaBean, this.name);
    }

    public void setValue(Object javaBean, Object value) {
        BeanUtils.setProperty(javaBean, this.name, value);
    }

    public String getName() {
        return name;
    }

    public Field getField() {
        return field;
    }

    public boolean isBoolean() {
        return isBoolean;
    }

    public Class getType() {
        return type;
    }

    public String getGetterMethodName() {
        return getterMethodName;
    }

    public String getSetterMethodName() {
        return setterMethodName;
    }
}