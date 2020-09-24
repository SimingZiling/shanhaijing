package org.shanhaijing.framework.beans;

/**
 * bean信息
 */
public class BeanInfo {

    /**
     * BeanClass名称
     */
    private String className;

    /**
     * Bean类
     */
    private Class<?> clazz;

    /**
     * Bean对象
     */
    private Object object;

    /*
    set/get方法
     */
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
