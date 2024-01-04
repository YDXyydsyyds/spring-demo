package com.ydx.spring.beans.factory;

import com.ydx.spring.beans.BeansException;
import com.ydx.spring.beans.factory.BeanFactory;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2023/12/29/12:56
 * @Description:
 */
public interface ListableBeanFactory extends BeanFactory {
    /**
     * 返回指定类型的所有实例
     *
     * @param type
     * @return
     * @param <T>
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    String[] getBeanDefinitionNames();
}
