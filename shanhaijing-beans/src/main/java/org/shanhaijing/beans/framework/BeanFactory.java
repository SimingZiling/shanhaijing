package org.shanhaijing.beans.framework;

import org.shanhaijing.beans.framework.annotation.Autowirted;
import org.shanhaijing.beans.framework.annotation.Bean;
import org.shanhaijing.beans.framework.annotation.Controller;
import org.shanhaijing.beans.framework.annotation.Service;
import org.shanhaijing.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yiming.localtools.basics.ClassUtil;
import org.yiming.localtools.conversion.StringUtil;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * Bean工厂，用于创造Bean
 */
public class BeanFactory {

    // 日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Configuration configuration = new Configuration();

    /**
     * 初始化IOC容器
     */
    public void doInitIoc(){

        logger.info("开始加载配置文件");
        configuration.doLoadApplicationConfig();


        logger.info("开始扫描包。");
        try {
            scanPackages();
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("开始实例化。");
        doInstance();

        logger.info("开始依赖注入");
        doAutowirted();

    }

    /**
     * 依赖注入对象
     */
    private void doAutowirted() {
        Beans.beanDefinitionMap.forEach((key, value) -> {
            // 获取到所有的属性
            Field[] fields = value.getClazz().getDeclaredFields();
            for (Field field : fields){
                // 设置允许设置私有属性
                field.setAccessible(true);
                // 判断是否有Autowirted注解
                if(field.isAnnotationPresent(Autowirted.class)){
                    Autowirted autowirted = field.getAnnotation(Autowirted.class);
                    // 判断该属性是否需要注入
                    if(autowirted.required()){
                        try {
                            // 判断Autowirted注解是否有名称，如果有则使用注解名称注入，没有则使用属性名称注入
                            if(!autowirted.value().equals("")){
                                if(!Beans.beanDefinitionMap.containsKey(autowirted.value())){
                                    throw new Exception("没有名字为："+autowirted.value()+" 的Bean，问题出现在"+value.getClassName());
                                }
                                field.set(value.getObject(),Beans.beanDefinitionMap.get(autowirted.value()).getObject());
                            } else {
                                if(!Beans.beanDefinitionMap.containsKey(field.getName())){
                                    throw new Exception("没有名字为："+field.getName()+" 的Bean，问题出现在"+value.getClassName());
                                }
                                field.set(value.getObject(),Beans.beanDefinitionMap.get(field.getName()).getObject());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    /**
     * 实例化对象
     */
    private void doInstance() {

        for (BeanDefinition beanDefinition : Beans.beanDefinitions){
            try {
                Class<?> clazz = beanDefinition.getClazz();
                // 实例化对象
                Object object = clazz.getDeclaredConstructor().newInstance();
                // 获取bean名称
                String beanName = getBeanName(clazz,object);
                // 判断beanNmae中是否重复
                if(Beans.beanDefinitionMap.containsKey(beanName)){
                    throw new Exception("Bean:"+beanName+" 名称重复！");
                }
                beanDefinition.setObject(object);
                Beans.beanDefinitionMap.put(beanName,beanDefinition);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取bean名称
     * @param clazz 类
     * @param object 初始化HandlerMapping时需要用到的对象
     * @return bean名称
     */
    private String getBeanName(Class<?> clazz,Object object){
        String beanName = StringUtil.initialLowercase(clazz.getSimpleName());
        // 判断注解是否有名字
        if(clazz.isAnnotationPresent(Controller.class)){
            Controller controller = clazz.getAnnotation(Controller.class);
            if(!controller.value().equals("")) {
                beanName = controller.value();
            }
            // TODO 看看是否能够将初始化HandlerMapping 的操作踢出去做
            HandlerMapping handlerMapping = new HandlerMapping();
            handlerMapping.doInitHandlerMapping(object);
        }else if(clazz.isAnnotationPresent(Service.class)) {
            Service service = clazz.getAnnotation(Service.class);
            if (!service.value().equals("")) {
                beanName = service.value();
            }
            // TODO 未考虑多继承
            else {
                Class<?>[] clazzs = clazz.getInterfaces();
                if (clazzs.length > 0) {
                    beanName = StringUtil.initialLowercase(clazz.getInterfaces()[0].getSimpleName());
                }
            }
        }else if(clazz.isAnnotationPresent(Bean.class)){
            Bean bean = clazz.getAnnotation(Bean.class);
            if(!bean.value().equals("")) {
                beanName = bean.value();
            }
        }
        return beanName;
    }

    /**
     * 扫描包
     */
    private void scanPackages() throws Exception {

        // 判断scanPackages参数是否存在，如果不存在则无法进行包扫描
        if(!Configuration.verifyConfig("scanPackages")){
            throw new Exception("scanPackages参数不存在！");
        }

        // 获取配置文件中更需要扫描的包
        String[] packages = String.valueOf(Configuration.getConfig("scanPackages")).split(",");

        Set<Class<?>> clazzs = new ClassUtil().scanClases(packages);
        clazzs.forEach(clazz ->{
            // 判断当clazz 不是注解的时候记录到bean中
            if(!clazz.isAnnotation()){
                // 迭代判断是否具有Bean注解
                if(ClassUtil.verifyAnnotation(clazz, Bean.class,true)) {
                    // 封装BeanDefinition
                    BeanDefinition beanDefinition = new BeanDefinition();
                    beanDefinition.setClassName(clazz.getName());
                    beanDefinition.setClazz(clazz);
                    Beans.beanDefinitions.add(beanDefinition);
                }
            }
        });
    }



}
