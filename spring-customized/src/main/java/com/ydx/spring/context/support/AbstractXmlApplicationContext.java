package com.ydx.spring.context.support;

import com.ydx.spring.beans.factory.support.DefaultListableBeanFactory;
import com.ydx.spring.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/04/15:09
 * @Description:
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{

    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory){
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if(configLocations != null){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
