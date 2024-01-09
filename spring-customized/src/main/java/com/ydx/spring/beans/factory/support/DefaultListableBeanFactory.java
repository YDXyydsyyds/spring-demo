package com.ydx.spring.beans.factory.support;

import com.ydx.spring.beans.BeansException;
import com.ydx.spring.beans.factory.ConfigurableListableBeanFactory;
import com.ydx.spring.beans.factory.config.BeanDefinition;

import java.util.*;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements ConfigurableListableBeanFactory, BeanDefinitionRegistry {


    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition == null){
            throw new BeansException("No bean named'" + beanName + "' is defined");
        }

        return beanDefinition;
    }

    @Override
    public void preInstantiateSingletons() throws BeansException {
         beanDefinitionMap.forEach((beanName, beanDefinition) -> {
             //只有当bean是单例且不为懒加载才会被创建
             if(beanDefinition.isSingleton() && !beanDefinition.isLazyInit()){
                 getBean(beanName);
             }
         });
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class beanClass = beanDefinition.getBeanClass();
            if(type.isAssignableFrom(beanClass)){
                T bean = (T) getBean(beanName);
                result.put(beanName, bean);
            }
        });
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        Set<String> beanNames = beanDefinitionMap.keySet();
        return beanNames.toArray(new String[beanNames.size()]);
    }


    @Override
    public <T> T getBean(Class<T> requireType) throws BeansException {
        List<String> beanNames = new ArrayList<>();
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            Class beanClass = entry.getValue().getBeanClass();
            if(requireType.isAssignableFrom(beanClass)){
                beanNames.add(entry.getKey());
            }
        }
        if(beanNames.size() == 1){
            return getBean(beanNames.get(0), requireType);
        }
        throw new BeansException(requireType + "expected single bean but found " + beanNames.size() + ":" + beanNames);
    }
}
