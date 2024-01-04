package com.ydx.spring.context.support;

import com.ydx.spring.beans.BeansException;
import com.ydx.spring.beans.factory.support.DefaultListableBeanFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/03/21:45
 * @Description:
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{
    private DefaultListableBeanFactory beanFactory;

    /**
     * 创建beanFactory并加载BeanDefinition
     * @throws BeansException
     */
    protected final void refreshBeanFactory() throws BeansException{
         DefaultListableBeanFactory beanFactory = createBeanFactory();
         loadBeanDefinitions(beanFactory);
         this.beanFactory = beanFactory;
    }

    /**
     * 创建bean工厂
     *
     * @return
     */
    protected DefaultListableBeanFactory createBeanFactory(){
         return new DefaultListableBeanFactory();
    }

    /**
     * 加载BeanDefinition
     *
     * @param beanFactory
     * @throws BeansException
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException;

    public DefaultListableBeanFactory getBeanFactory(){
        return beanFactory;
    }
}
