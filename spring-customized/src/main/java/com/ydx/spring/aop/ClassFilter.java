package com.ydx.spring.aop;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/04/16:55
 * @Description:
 */
public interface ClassFilter {
    boolean matches(Class<?>  clazz);
}
