package org.shanhaijing.beans;


import org.shanhaijing.beans.annotation.RequestParam;
import org.shanhaijing.config.Request;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 映射
 */
public class Handler {


    private Object object;
    private Method method;


    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    /**
     * 执行方法
     * @param request 请求参数
     * @return 执行结果
     */
    public Object performMethod(Request request) throws InvocationTargetException, IllegalAccessException {
        // 获取参数名称
        Parameter[] parameters = method.getParameters();
        Object[] objects = new Object[parameters.length];
        for (int i = 0;i< parameters.length;i++) {
            Parameter parameter = parameters[i];
            if(parameter.isAnnotationPresent(RequestParam.class)){
                RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
                if(!requestParam.value().equals("")){
                    String parameName = requestParam.value();
                    // TODO 参数类型未能判断
                    if(request.getRequestParameter(parameName) == null){
                        objects[i] = null;
                    }else {
                        // TODO 参数类型未能判断 目前返回为数组的固定值
                        objects[i] = request.getRequestParameter(parameName)[0];
                    }
                }else {
                    objects[i] = null;
                }
            }
        }
        return method.invoke(object,objects);
    }
}
