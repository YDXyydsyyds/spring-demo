package com.ydx.spring.aop;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/04/16:53
 * @Description:
 */
public interface MethodMatcher {
    boolean matches(Method method, Class<?> targetClass);
}
