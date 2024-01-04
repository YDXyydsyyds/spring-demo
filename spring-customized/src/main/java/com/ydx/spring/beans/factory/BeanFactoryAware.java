package com.ydx.spring.beans.factory;

import com.ydx.spring.beans.BeansException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2023/12/26/15:48
 * @Description:
 */
public interface BeanFactoryAware extends Aware{
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
