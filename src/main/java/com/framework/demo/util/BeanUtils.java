package com.framework.demo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
public class BeanUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanUtils.setApplicationContextStatic(applicationContext);
    }

    private static void setApplicationContextStatic(ApplicationContext context){
        if(BeanUtils.applicationContext==null){
            BeanUtils.applicationContext = context;
        }
    }
    public static Object getBean(String name){
        System.out.println(name.substring(0,1).toLowerCase()+name.substring(1));
        System.out.println(applicationContext.getBean(name.substring(0,1).toLowerCase()+name.substring(1)));
        return applicationContext.getBean(name.substring(0,1).toLowerCase()+name.substring(1));
//        return new ClassPathXmlApplicationContext("name");
    }

    public static String getClassNameLowercase(Class<?> clazz){
        return clazz.getSimpleName().toLowerCase();
    }
}
