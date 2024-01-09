package com.ydx.spring.aop;

import com.ydx.spring.aop.framework.AdvisorChainFactory;
import com.ydx.spring.aop.framework.DefaultAdvisorChainFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/04/18:06
 * @Description:
 */
public class AdvisedSupport {
    //是否使用cglib代理
    private boolean proxyTargetClass = true;

    private TargetSource targetSource;

    private MethodMatcher methodMatcher;

    private transient Map<Integer, List<Object>> methodCache;

    AdvisorChainFactory advisorChainFactory = new DefaultAdvisorChainFactory();

   private List<Advisor> advisors = new ArrayList<>();

   public AdvisedSupport(){
       this.methodCache = new ConcurrentHashMap<>(32);
   }
   public boolean isProxyTargetClass(){
       return proxyTargetClass;
   }

    public void setProxyTargetClass(boolean proxyTargetClass){
        this.proxyTargetClass = proxyTargetClass;
    }
    public void addAdvisor(Advisor advisor){
       advisors.add(advisor);
    }

    public List<Advisor> getAdvisors(){
       return advisors;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(Method method, Class<?> targetClass){
       Integer cacheKey = method.hashCode();
       List<Object> cached = this.methodCache.get(cacheKey);
       if(cached == null){
           cached = this.advisorChainFactory.getInterceptorsAndDynamicInterceptionAdvice(this, method, targetClass);
           this.methodCache.put(cacheKey, cached);
       }
       return cached;
    }

}