package com.ydx.spring.beans.factory.support;

import com.ydx.spring.beans.BeansException;
import com.ydx.spring.beans.factory.DisposableBean;
import com.ydx.spring.beans.factory.ObjectFactory;
import com.ydx.spring.beans.factory.config.SingletonBeanRegistry;

import java.util.ArrayList;
import java.util.Arrays;
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
    private Map<String, ObjectFactory<?>>  singletonFactories = new HashMap<String, ObjectFactory<?>>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();
    @Override
    public Object getSingleton(String beanName) {
        Object singleonObject = singletonObjects.get(beanName);
        if(singleonObject == null){
            singleonObject = earlySingletonObjects.get(beanName);
            if(singleonObject == null){
                ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                if(singletonFactory != null){
                    singleonObject = singletonFactory.getObject();
                    //从三级缓存放进二级缓存
                    earlySingletonObjects.put(beanName, singleonObject);
                    singletonFactories.remove(beanName);
                }
            }
        }
        return singleonObject;
    }

    @Override
    public void addSingleton(String beanName, Object singletonObject) {
         singletonObjects.put(beanName, singletonObject); //1
         earlySingletonObjects.remove(beanName);//2
         singletonFactories.remove(beanName);//3
    }
    protected void addSingletonFactory(String beanName, ObjectFactory<?> singleFactory){
        singletonFactories.put(beanName, singleFactory);
    }
    public void registerDisposableBean(String beanName, DisposableBean bean){
        disposableBeans.put(beanName, bean);
    }
    public void destroySingletons(){
        ArrayList<String> beanNames = new ArrayList<>(disposableBeans.keySet());
        for (String beanName : beanNames) {
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            }catch (Exception e){
                throw new BeansException("Destroy method on bean with name'" + beanName + "' threw an exception", e);
            }
        }
    }
}
