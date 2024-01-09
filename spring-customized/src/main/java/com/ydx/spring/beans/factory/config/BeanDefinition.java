package com.ydx.spring.beans.factory.config;

import com.ydx.spring.beans.PropertyValues;

import java.util.Objects;

public class BeanDefinition {
    public static String SCOPE_SINGLETON = "singleton";
    public static String SCOPE_PROTOTYPE = "prototype";
    /**
     * bean的class类
     */
    private Class beanClass;
    /**
     * class 属性值
     */
    private PropertyValues propertyValues;
    /**
     * 通过反射 初始化方法名称
     */
    private String initMethodName;
    /**
     * 销毁方法名称
     */
    private String destroyMethodName;
    /**
     * 作用域 默认单例Bean
     */
    private String scope = SCOPE_SINGLETON;

    private boolean singleton = true;
    private boolean prototype = false;
    /**
     * 懒加载
     */
    private boolean lazyInit = false;

    public BeanDefinition(Class beanClass) {
        this(beanClass, null);
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public void setScope(String scope){
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }
    public boolean isSingleton(){
        return this.singleton;
    }
    public boolean isPrototype(){
        return this.prototype;
    }
    public Class getBeanClass(){
        return beanClass;
    }
    public void setBeanClass(Class beanClass){
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }
    @Override
    public int hashCode(){
        return Objects.hash(beanClass);
    }
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        BeanDefinition that = (BeanDefinition) o;
        return beanClass.equals(that.beanClass);
    }
}
