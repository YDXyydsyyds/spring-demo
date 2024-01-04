package com.ydx.spring.context.support;

import com.ydx.spring.beans.BeansException;
import com.ydx.spring.beans.factory.config.BeanPostProcessor;
import com.ydx.spring.context.ApplicationContext;
import com.ydx.spring.context.ApplicationContextAware;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/03/20:01
 * @Description:
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
