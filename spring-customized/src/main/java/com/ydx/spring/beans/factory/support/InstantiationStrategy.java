package com.ydx.spring.beans.factory.support;

import com.ydx.spring.beans.BeansException;
import com.ydx.spring.beans.factory.config.BeanDefinition;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2023/12/26/13:52
 * @Description: Bean的实例化策略
 */
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition) throws BeansException;
}
