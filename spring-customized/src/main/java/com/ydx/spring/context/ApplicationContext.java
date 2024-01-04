package com.ydx.spring.context;

import com.ydx.spring.beans.factory.HierarchicalBeanFactory;
import com.ydx.spring.beans.factory.ListableBeanFactory;
import com.ydx.spring.core.io.ResourceLoader;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/03/19:30
 * @Description:
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {

}
