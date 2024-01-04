package com.ydx.spring.beans.factory.support;

import com.ydx.spring.beans.BeansException;
import com.ydx.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2023/12/26/13:54
 * @Description:
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy{
    /**
     * 简单的bean实例化策略，根据bean的无参构造函数实例化对象
     *
     * @param beanDefinition
     * @return
     * @throws BeansException
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
        Class beanClass = beanDefinition.getBeanClass();
        try {
            //TODO 为什么要用无参构造器创建实例
            Constructor constructor = beanClass.getDeclaredConstructor();
            return constructor.newInstance();
        }catch (Exception e){
            throw new BeansException("Failed to instantiate [" + beanClass.getName() + "]", e);
        }
    }
}
