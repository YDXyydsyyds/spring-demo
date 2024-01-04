package com.ydx.spring.beans.factory.config;

import com.ydx.spring.beans.BeansException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/03/17:59
 * @Description:
 */
public interface BeanFactoryPostProcessor {

   void postProcessBeanFactory(ConfigurableBeanFactory beanFactory) throws BeansException;
}
