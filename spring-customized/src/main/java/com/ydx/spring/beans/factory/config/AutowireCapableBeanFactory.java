package com.ydx.spring.beans.factory.config;

import com.ydx.spring.beans.BeansException;
import com.ydx.spring.beans.factory.BeanFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2023/12/26/11:35
 * @Description:
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    /**
     * 执行applyBeanPostProcessors的postProcessBeforeInitialization方法
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 执行applyBeanPostProcessors的postProcessAfterInitialization方法
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
