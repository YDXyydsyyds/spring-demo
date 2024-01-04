package com.ydx.spring.beans.factory.config;

import com.ydx.spring.beans.BeansException;
import com.ydx.spring.beans.PropertyValues;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2023/12/26/13:13
 * @Description:
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{
    /**
     * 在bean实例化之前执行
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    /**
     * bean实例化之后， 设置属性之前执行
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException;

    /**
     * bean实例化之后， 设置属性之后执行
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    PropertyValues postProcessProperValues(PropertyValues pvs, Object bean, String beanName) throws BeansException;

    /**
     * 提前暴露bean
     * @param bean
     * @param name
     * @return
     * @throws BeansException
     */
    default Object getEarlyBeanReference(Object bean, String name) throws BeansException{
        return bean;
    }
}
