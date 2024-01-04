package com.ydx.spring.beans.factory;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2023/12/26/16:10
 * @Description:
 */
public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
