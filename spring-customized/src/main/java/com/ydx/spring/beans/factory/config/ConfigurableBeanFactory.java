package com.ydx.spring.beans.factory.config;

import com.ydx.spring.beans.factory.HierarchicalBeanFactory;
import com.ydx.spring.beans.factory.config.BeanPostProcessor;
import com.ydx.spring.beans.factory.config.SingletonBeanRegistry;
import com.ydx.spring.core.convert.ConversionService;
import com.ydx.spring.util.StringValueResolver;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例
     */
    void destroySingletons();

    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    String resolveEmbeddedValue(String value);

    void setConversionService(ConversionService conversionService);

    ConversionService getConversionService();

}
