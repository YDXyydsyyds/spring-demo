package com.ydx.spring.aop;

import org.aopalliance.aop.Advice;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/05/14:28
 * @Description:
 */
public interface Advisor {
    Advice getAdvice();
}
