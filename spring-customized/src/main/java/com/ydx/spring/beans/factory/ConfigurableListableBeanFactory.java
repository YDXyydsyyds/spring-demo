package com.ydx.spring.beans.factory;

import com.ydx.spring.beans.BeansException;
import com.ydx.spring.beans.factory.config.AutowireCapableBeanFactory;
import com.ydx.spring.beans.factory.config.BeanDefinition;
import com.ydx.spring.beans.factory.config.BeanPostProcessor;
import com.ydx.spring.beans.factory.config.ConfigurableBeanFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2023/12/29/12:53
 * @Description:
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    /**
     * 根据名称查找BeanDefinition
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 提前实例化所有单例实例
     * @throws BeansException
     */
    void preInstantiateSingletons() throws BeansException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
