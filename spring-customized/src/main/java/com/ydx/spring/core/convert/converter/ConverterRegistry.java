package com.ydx.spring.core.convert.converter;

/**
 * Created with IntelliJ IDEA.
 *类型转换器注册接口
 *
 * @Author: ydx
 * @Date: 2024/01/07/14:05
 * @Description:
 */
public interface ConverterRegistry {
    void addConverter(Converter<?, ?> converter);

    void addConverterFactory(ConverterFactory<?, ?> converterFactory);
    void addConverter(GenericConverter converter);
}
