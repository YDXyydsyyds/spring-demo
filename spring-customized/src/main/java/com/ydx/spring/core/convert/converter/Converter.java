package com.ydx.spring.core.convert.converter;

/**
 * Created with IntelliJ IDEA.
 *
 * 类型转换抽象接口
 *
 * @Author: ydx
 * @Date: 2024/01/07/13:59
 * @Description:
 */
public interface Converter<S, T> {

    T convert(S source);
}
