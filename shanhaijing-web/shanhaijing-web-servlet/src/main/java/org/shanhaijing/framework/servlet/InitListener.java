package org.shanhaijing.framework.servlet;


import org.shanhaijing.framework.beans.BeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitListener implements ServletContextListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BeanFactory beanFactory = new BeanFactory();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("程序开始");
        beanFactory.doInftBean();
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("程序结束");
    }

}
