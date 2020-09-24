package org.shanhaijing.framework.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * beans
 */
public class Beans {

    /**
     * bean信息列表
     */
    protected static List<BeanInfo> beanInfos = new ArrayList<>();

    /**
     * bean信息集合，key为bean名称
     */
    protected static Map<String,BeanInfo> beanInfoMap = new HashMap<>();

    /**
     * 获取bean对象
     * @param benaName bean名称
     * @return bean对象
     */
    public static Object getBeanObject(String benaName){
        return beanInfoMap.get(benaName).getObject();
    }
}
