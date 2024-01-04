package com.ydx.spring.beans.factory.config;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2023/12/26/15:02
 * @Description:一个bean对另一个bean的引用
 */
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
