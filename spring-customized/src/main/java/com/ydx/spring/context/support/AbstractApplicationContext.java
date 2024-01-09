package com.ydx.spring.context.support;

import com.ydx.spring.beans.BeansException;
import com.ydx.spring.beans.factory.ConfigurableListableBeanFactory;
import com.ydx.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.ydx.spring.beans.factory.config.BeanPostProcessor;
import com.ydx.spring.context.ApplicationEvent;
import com.ydx.spring.context.ApplicationListener;
import com.ydx.spring.context.ConfigurableApplicationContext;
import com.ydx.spring.context.event.ApplicationEventMulticaster;
import com.ydx.spring.context.event.ContextCloseEvent;
import com.ydx.spring.context.event.ContextRefreshedEvent;
import com.ydx.spring.context.event.SimpleApplicationEventMulticaster;
import com.ydx.spring.core.convert.ConversionService;
import com.ydx.spring.core.io.DefaultResourceLoader;
import com.ydx.spring.core.io.Resource;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *抽象应用上下文
 *
 * @Author: ydx
 * @Date: 2024/01/03/19:46
 * @Description:
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    public static final String CONVERSION_SERVICE_BEAN_NAME = "conversionService";

    @Override
    public void refresh() throws BeansException {
        //创建BeanFactory，并加载BeanDefinition
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        //添加ApplicationContextAwareProcessor，让继承自ApplicationContextAware的bean能感知bean
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        //在bean实例化之前，执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessor(beanFactory);

        //BeanPostProcessor需要提前与其他bean实例化之前注册
        registerBeanPostProcessors(beanFactory);

        //初始化事件发布者
        initApplicationEventMulticaster();

        //注册事件监听器
        registerListeners();

        //注册类型转换器和提前实例化单例bean
        finishBeanFactoryInitialization(beanFactory);

        //发布容器刷新完成事件
        finishRefresh();
    }

    /**
     * 创建BeanFactory，并加载BeanDefinition
     *
     * @throws BeansException
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    /**
     * 在bean实例化之前，执行BeanFactoryPostProcessor
     *
     * @param beanFactory
     */
    protected void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap =  beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 注册BeanPostProcessor
     *
     * @param beanFactory
     */
    protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    /**
     * 初始化事件发布者
     */
    protected void initApplicationEventMulticaster(){
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.addSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    protected void registerListeners(){
        for (ApplicationListener applicationListener : getBeansOfType(ApplicationListener.class).values()) {
            applicationEventMulticaster.addApplicationListener(applicationListener);
        }
    }

    protected void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory){
        //设置类型转换器
        if(beanFactory.containsBean(CONVERSION_SERVICE_BEAN_NAME)){
            Object conversionService = beanFactory.getBean(CONVERSION_SERVICE_BEAN_NAME);
            if(conversionService instanceof ConversionService){
                beanFactory.setConversionService((ConversionService) conversionService);
            }
        }
        //提前实例化单bean
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 发布容器刷新完成事件
     */
    protected void finishRefresh(){
        publishEvent(new ContextRefreshedEvent(this));
    }
    public abstract ConfigurableListableBeanFactory getBeanFactory();

    protected void doClose(){
        //发布器关闭事件
        publishEvent(new ContextCloseEvent(this));

        //执行单例bean的销毁方法
        destroyBeans();
    }
    public void close(){
        doClose();
    }
     protected void destroyBeans(){
        getBeanFactory().destroySingletons();
     }

    @Override
    public <T> T getBean(String name, Class<T> requireType) throws BeansException {
        return getBeanFactory().getBean(name, requireType);
    }

    @Override
    public boolean containsBean(String name) {
        return getBeanFactory().containsBean(name);
    }


    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public <T> T getBean(Class<T> requireType) throws BeansException {
        return getBeanFactory().getBean(requireType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
         applicationEventMulticaster.multicastEvent(event);
    }

    @Override
    public Resource getResource(String location) {
        return super.getResource(location);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    public void registerShutdownHook(){
        Thread shutdownHook = new Thread() {
            public void run() {
                doClose();
            }
        };
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }
}
