package com.ydx.spring.beans.factory.annotation;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.TypeUtil;
import com.ydx.spring.beans.BeansException;
import com.ydx.spring.beans.PropertyValues;
import com.ydx.spring.beans.factory.BeanFactory;
import com.ydx.spring.beans.factory.BeanFactoryAware;
import com.ydx.spring.beans.factory.ConfigurableListableBeanFactory;
import com.ydx.spring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.ydx.spring.core.convert.ConversionService;

import java.io.File;
import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/07/13:32
 * @Description:
 */
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
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
        //处理@Value注解
        Class<?> clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Value valueAnnotation = field.getAnnotation(Value.class);
            if(valueAnnotation != null){
                Object value = valueAnnotation.value();
                value = beanFactory.resolveEmbeddedValue((String) value);

                //类型转换
                Class<?> sourceType = value.getClass();
                Class<?> targetType = (Class<?>) TypeUtil.getType(field);
                ConversionService conversionService = beanFactory.getConversionService();
                if(conversionService != null){
                    if(conversionService.canConvert(sourceType, targetType)){
                        value = conversionService.convert(value, targetType);
                    }
                }
                BeanUtil.setFieldValue(bean, field.getName(), value);
            }
        }

        //处理@Autowired注解
        for (Field field : fields) {
            Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);
            if(autowiredAnnotation != null){
                Class<?> fieldType = field.getType();
                String dependentBeanName = null;
                Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
                Object dependentBean = null;
                if(qualifierAnnotation != null){
                    dependentBeanName = qualifierAnnotation.value();
                    dependentBean = beanFactory.getBean(dependentBeanName, fieldType);
                }else {
                    dependentBean = beanFactory.getBean(fieldType);
                }
                BeanUtil.setFieldValue(bean, field.getName(), dependentBean);
            }
        }
        return pvs;
    }
}
