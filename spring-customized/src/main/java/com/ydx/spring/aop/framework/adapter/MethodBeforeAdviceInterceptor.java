package com.ydx.spring.aop.framework.adapter;

import com.ydx.spring.aop.BeforeAdvice;
import com.ydx.spring.aop.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/05/16:47
 * @Description:
 */
public class MethodBeforeAdviceInterceptor implements BeforeAdvice, MethodInterceptor {

    private MethodBeforeAdvice advice;

    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
        this.advice = advice;
    }
    public MethodBeforeAdviceInterceptor() {
    }
    public void setAdvice(MethodBeforeAdvice advice) {
        this.advice = advice;
    }
    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        //在执行被代理方法之前，先执行before advice操作
        this.advice.before(mi.getMethod(), mi.getArguments(), mi.getThis());
        return mi.proceed();
    }

}
