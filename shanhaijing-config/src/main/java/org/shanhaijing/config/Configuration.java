package org.shanhaijing.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class Configuration {

    // 日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Properties properties = new Properties();

    // 配置信息Map
    private static Map<String,Object> applicationConfig = new HashMap<>();

    public final static String classPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
            .getResource("")).getPath();

    /**
     * 获取配置信息
     * @param configName 配置名称
     * @return 配置信息
     */
    public static Object getConfig(String  configName){
        return applicationConfig.get(configName);
    }

    public static boolean verifyConfig(String configName){
        return applicationConfig.containsKey(configName);
    }

    /**
     * 加载Application配置文件
     */
    public void doLoadApplicationConfig(){
        // 读取文件流
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(classPath+"application.properties");
            properties.load(inputStream);
            applicationConfig = new HashMap<String,Object>((Map)properties);
        } catch (FileNotFoundException e){
            logger.error("application文件不存在！");
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
            e.printStackTrace();
        } finally {
            // 当inputStream不为空的时候 关闭inputStream
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error(e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}
