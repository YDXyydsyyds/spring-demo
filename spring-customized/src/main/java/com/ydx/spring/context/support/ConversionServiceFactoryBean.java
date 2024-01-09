package com.ydx.spring.context.support;

import com.ydx.spring.beans.factory.FactoryBean;
import com.ydx.spring.beans.factory.InitializingBean;
import com.ydx.spring.core.convert.ConversionService;
import com.ydx.spring.core.convert.converter.Converter;
import com.ydx.spring.core.convert.converter.ConverterFactory;
import com.ydx.spring.core.convert.converter.ConverterRegistry;
import com.ydx.spring.core.convert.converter.GenericConverter;
import com.ydx.spring.core.convert.support.DefaultConversionService;
import com.ydx.spring.core.convert.support.GenericConversionService;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/08/19:01
 * @Description:
 */
public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {
    private Set<?> converters;

    private GenericConversionService conversionService;
    @Override
    public ConversionService getObject() throws Exception {
        return conversionService;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        conversionService = new DefaultConversionService();
        registerConverters(converters, conversionService);
    }
    private void registerConverters(Set<?> converters, ConverterRegistry registry){
        if(converters != null){
            for (Object converter : converters) {
                if(converters instanceof GenericConverter){
                    registry.addConverter((GenericConverter) converter);
                }else if(converter instanceof Converter<?, ?>){
                    registry.addConverter((Converter<?, ?>) converter);
                }else if(converter instanceof ConverterFactory<?, ?>){
                    registry.addConverterFactory((ConverterFactory<?, ?>) converter);
                }else {
                    throw new IllegalArgumentException("Each converter object must implement one of the " +
                            "Converter, ConverterFactory, or GenericConverter interfaces");
                }
            }
        }
    }
    public void setConverters(Set<?> converters){
        this.converters = converters;
    }
}
