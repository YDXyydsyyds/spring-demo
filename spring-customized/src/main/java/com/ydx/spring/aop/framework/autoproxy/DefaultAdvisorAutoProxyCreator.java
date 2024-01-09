package com.ydx.spring.aop.framework.autoproxy;

import com.ydx.spring.aop.Advisor;
import com.ydx.spring.aop.ClassFilter;
import com.ydx.spring.aop.Pointcut;
import com.ydx.spring.aop.TargetSource;
import com.ydx.spring.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.ydx.spring.aop.framework.ProxyFactory;
import com.ydx.spring.beans.BeansException;
import com.ydx.spring.beans.PropertyValues;
import com.ydx.spring.beans.factory.BeanFactory;
import com.ydx.spring.beans.factory.BeanFactoryAware;
import com.ydx.spring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.ydx.spring.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/05/19:42
 * @Description:
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {
    private DefaultListableBeanFactory beanFactory;

    private Set<Object> earlyProxyReferences = new HashSet<>();

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(!earlyProxyReferences.contains(beanName)){
            return wrapIfNecessary(bean, beanName);
        }
        return bean;
    }
   protected Object wrapIfNecessary(Object bean, String beanName){
        //避免死循环
       if(isInfrastructureClass(bean.getClass())){
           return bean;
       }

       Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
       try {
           ProxyFactory proxyFactory = new ProxyFactory();
           for (AspectJExpressionPointcutAdvisor advisor : advisors) {
               ClassFilter classFilter = advisor.getPointcut().getClassFilter();
               if(classFilter.matches(bean.getClass())){
                   TargetSource targetSource = new TargetSource(bean);
                   proxyFactory.setTargetSource(targetSource);
                   proxyFactory.addAdvisor(advisor);
                   proxyFactory.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
               }
           }
           if(!proxyFactory.getAdvisors().isEmpty()){
               return proxyFactory.getProxy();
           }
       }catch (Exception e){
           throw new BeansException("Error create proxy bean for:" + beanName, e);
       }
       return bean;
   }

   private boolean isInfrastructureClass(Class<?> beanClass){
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
   }
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    @Override
    public PropertyValues postProcessProperValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return pvs;
    }

    @Override
    public Object getEarlyBeanReference(Object bean, String name) throws BeansException {
        earlyProxyReferences.add(name);
        return wrapIfNecessary(bean, name);
    }
}
