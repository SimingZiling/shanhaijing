package org.shanhaijing.framework.request;



import org.shanhaijing.framework.beans.annotation.RequestParam;
import org.shanhaijing.framework.request.annotation.RequestMethod;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 映射
 */
public class Handler {


    private Object object;
    private Method method;
    private RequestMethod[] requestMethods;


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

    public RequestMethod[] getRequestMethods() {
        return requestMethods;
    }

    public void setRequestMethods(RequestMethod[] requestMethods) {
        this.requestMethods = requestMethods;
    }

    /**
     * 执行方法
     * @param request 请求参数
     * @return 执行结果
     */
    public Object performMethod(Request request) throws Exception {

        // 判断请求方式
        boolean isMethod = true;
        for (RequestMethod requestMethod : requestMethods){
            if(!requestMethod.getMethod().equals(request.getRequestMethod())){
                isMethod = false;
            }
        }
        if(!isMethod){
            throw new Exception("请求方式不正确！");
        }

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
