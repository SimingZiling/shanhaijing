package org.shanhaijing.config;

import java.util.Map;

/**
 *  TODO 暂时放这里
 *  请求
 */
public class Request {

    private Map<String,String[]> requestParameterMap ;

    private Map<String,String> requestHeaderMap ;

    public Map<String, String> getRequestHeaderMap() {
        return requestHeaderMap;
    }

    public void setRequestHeaderMap(Map<String, String> requestHeaderMap) {
        this.requestHeaderMap = requestHeaderMap;
    }

    public Map<String, String[]> getRequestParameterMap() {
        return requestParameterMap;
    }

    public void setRequestParameterMap(Map<String, String[]> requestParameterMap) {
        this.requestParameterMap = requestParameterMap;
    }

    public String[] getRequestParameter(String parameterName){
        return this.requestParameterMap.get(parameterName);
    }

    public String getRequestHeader(String headerName){
        return this.requestHeaderMap.get(headerName);
    }

}
