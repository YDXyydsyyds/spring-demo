package com.ydx.spring.beans.factory.support;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.ydx.spring.beans.BeansException;
import com.ydx.spring.beans.factory.DisposableBean;
import com.ydx.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2023/12/28/20:11
 * @Description:
 */
public class DisposableBeanAdapter implements DisposableBean {
    private final Object bean;
    private final String beanName;
    private final String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    public void destroy() throws Exception {
        if(bean instanceof DisposableBean){
            ((DisposableBean) bean).destroy();
        }

        //避免同时继承自DisposableBean，且定义方法与disposableBean方法同名，销毁执行两次的情况
        if(StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))){
            //执行自定义方法
            Method destroyMethod = ClassUtil.getPublicMethod(bean.getClass(), destroyMethodName);
            if(destroyMethod == null){
                throw new BeansException("Couldn't find a destroy method named'" + destroyMethodName + "' on bean with name'" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}
