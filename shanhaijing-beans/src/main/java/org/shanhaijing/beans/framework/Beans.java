package org.shanhaijing.beans.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Beans {

    /**
     * bean列表
     */
    protected static List<BeanDefinition> beanDefinitions = new ArrayList<BeanDefinition>();

    /**
     * bean集合
     */
    protected static Map<String,BeanDefinition> beanDefinitionMap = new HashMap<String,BeanDefinition>();

    public static Object getBean(String benaName){
        return beanDefinitionMap.get(benaName).getObject();
    }


}
