package com.ydx.spring.context.event;

import com.ydx.spring.beans.BeansException;
import com.ydx.spring.beans.factory.BeanFactory;
import com.ydx.spring.beans.factory.BeanFactoryAware;
import com.ydx.spring.context.ApplicationEvent;
import com.ydx.spring.context.ApplicationListener;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/03/21:01
 * @Description:
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {
    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new HashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
         applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
          applicationListeners.remove(listener);
    }
}
