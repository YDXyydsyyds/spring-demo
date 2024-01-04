package com.ydx.spring.beans.factory.support;

import com.ydx.spring.beans.BeansException;
import com.ydx.spring.core.io.Resource;
import com.ydx.spring.core.io.ResourceLoader;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2023/12/29/13:54
 * @Description:
 */
public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;
    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String[] locations) throws BeansException;
}
