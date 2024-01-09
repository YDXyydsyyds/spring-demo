package com.ydx.spring.core.convert.converter;

/**
 * Created with IntelliJ IDEA.
 *
 * 类型转换工厂
 *
 * @Author: ydx
 * @Date: 2024/01/07/14:02
 * @Description:
 */
public interface ConverterFactory <S, R>{
    <T extends R> Converter<S, T> getConverter(Class<T> targetType);
}
