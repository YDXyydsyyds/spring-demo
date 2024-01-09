package com.ydx.spring.core.convert;

/**
 * Created with IntelliJ IDEA.
 *
 *类型转换抽象接口
 *
 * @Author: ydx
 * @Date: 2024/01/07/13:58
 * @Description:
 */
public interface ConversionService {
    boolean canConvert(Class<?> sourceType, Class<?> targetType);

    <T> T convert(Object source, Class<T> targetClass);
}
