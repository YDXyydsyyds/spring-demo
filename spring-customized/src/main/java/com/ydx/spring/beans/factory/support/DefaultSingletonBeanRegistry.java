package com.ydx.spring.beans.factory.support;

import com.ydx.spring.beans.factory.DisposableBean;
import com.ydx.spring.beans.factory.ObjectFactory;
import com.ydx.spring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 一级缓存
     */
    private Map<String, Object> singletonObjects = new HashMap<>();
    /**
     * 二级缓存
     */
    private Map<String, Object> earlySingletonObjects = new HashMap<>();
    /**
     * 三级缓存
     */
    private Map<String, ObjectFactory<?>>  stringObjectFactories = new HashMap<String, ObjectFactory<?>>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();
    @Override
    public Object getSingleton(String beanName) {
        return null;
    }

    @Override
    public void addSingleton(String beanName, Object singletonObject) {

    }
}
