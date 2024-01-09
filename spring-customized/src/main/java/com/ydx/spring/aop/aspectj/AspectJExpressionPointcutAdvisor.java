package com.ydx.spring.aop.aspectj;

import com.ydx.spring.aop.Pointcut;
import com.ydx.spring.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/05/16:56
 * @Description:
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    private AspectjExpressionPointcut pointcut;

    private Advice advice;

    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }
    public void setAdvice(Advice advice){
        this.advice = advice;
    }
    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if(pointcut == null){
            pointcut = new AspectjExpressionPointcut(expression);
        }
        return pointcut;
    }
}
