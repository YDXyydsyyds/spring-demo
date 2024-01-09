package com.ydx.spring.aop.framework;

import com.ydx.spring.aop.AdvisedSupport;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/05/16:22
 * @Description:
 */
public class ProxyFactory extends AdvisedSupport {
    public ProxyFactory() {
    }

    public Object getProxy(){
        return createAopProxy().getProxy();
    }
    private AopProxy createAopProxy(){
        if(this.isProxyTargetClass() || this.getTargetSource().getTargetClass().length == 0){
            return new CglibAopProxy(this);
        }
        return new JdkDynamicAopProxy(this);
    }
}
