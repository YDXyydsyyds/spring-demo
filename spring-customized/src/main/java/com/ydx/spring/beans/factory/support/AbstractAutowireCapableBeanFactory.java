package com.ydx.spring.beans.factory.support;

import com.ydx.spring.beans.BeansException;
import com.ydx.spring.beans.factory.config.BeanDefinition;

public class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    public <T> T getBean(Class<T> requireType) throws BeansException {
        return null;
    }

    @Override
    public void destroySingleton() {

    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        return null;
    }

    @Override
    protected boolean containsBeanDefinition(String beanName) {
        return false;
    }

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        return null;
    }
}
