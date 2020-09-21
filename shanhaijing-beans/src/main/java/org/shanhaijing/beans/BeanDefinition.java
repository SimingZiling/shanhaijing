package org.shanhaijing.beans;

/**
 * 构造Bean基础信息
 */
public class BeanDefinition {

    private String className;

    private Class<?> clazz;

    private Object object;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
