package com.ydx.spring.aop;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/05/16:57
 * @Description:
 */
public interface PointcutAdvisor extends Advisor{
      Pointcut getPointcut();
}
