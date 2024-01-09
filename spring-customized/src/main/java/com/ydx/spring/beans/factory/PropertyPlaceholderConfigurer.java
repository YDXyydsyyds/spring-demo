package com.ydx.spring.beans.factory;

import com.ydx.spring.beans.BeansException;
import com.ydx.spring.beans.PropertyValue;
import com.ydx.spring.beans.PropertyValues;
import com.ydx.spring.beans.factory.config.BeanDefinition;
import com.ydx.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.ydx.spring.beans.factory.config.ConfigurableBeanFactory;
import com.ydx.spring.core.io.DefaultResourceLoader;
import com.ydx.spring.core.io.Resource;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/06/10:46
 * @Description:
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    public static final String PLACEHOLDER_PREFIX = "${";

    public static final String PLACEHOLDER_SUFFIX = "}";

    private String location;
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
         //加载属性配置文件
        Properties properties = loadProperties();

        //属性值替换占位符
        processProperties(beanFactory, properties);

    }

    /**
     * 加载属性配置文件
     *
     * @return
     */
    private Properties loadProperties(){
        try {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());
            return properties;
        }catch (IOException e){
            throw new BeansException("Could not load properties", e);
        }
    }

    /**
     * 属性值替换占位符
     *
     * @param beanFactory
     * @param properties
     * @throws BeansException
     */
    private void processProperties(ConfigurableListableBeanFactory beanFactory, Properties properties) throws BeansException{
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
            resolvePropertyValues(beanDefinition, properties);
        }
    }

    private void resolvePropertyValues(BeanDefinition beanDefinition, Properties properties){
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
            Object value = propertyValue.getValue();
            if(value instanceof String){
                value = resolvePlaceholder((String) value, properties);
                propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), value));
            }
        }
    }

    private String resolvePlaceholder(String value, Properties properties){
        //TODO 仅简单支持一个占位符的格式
        String stVal = value;
        StringBuffer buf = new StringBuffer();
        int startIndex = stVal.indexOf(PLACEHOLDER_PREFIX);
        int endIndex = stVal.indexOf(PLACEHOLDER_SUFFIX);
        if(startIndex != -1 && endIndex != -1 && startIndex < endIndex){
            String propkey = stVal.substring(startIndex + 2, endIndex);
            String propVal = properties.getProperty(propkey);
            buf.replace(startIndex, endIndex + 1, propVal);
        }
        return buf.toString();
    }
}
