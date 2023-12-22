package com.ydx.spring.beans.factory;

import com.ydx.spring.beans.factory.config.BeanPostProcessor;
import com.ydx.spring.beans.factory.config.SingletonBeanRegistry;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例
     */
    void destroySingleton();


}
